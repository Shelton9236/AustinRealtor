package com.ece656.house.biz.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.ece656.house.biz.mapper.UserMapper;
import com.ece656.house.common.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Value("${domain.name}")
    private String domainName;


    @Autowired
    private UserMapper userMapper;


    private final Cache<String, String> registerCache =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES)
                    .removalListener(new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, String> notification) {
                            String email = notification.getValue();
                            User user = new User();
                            user.setEmail(email);
                            List<User> targetUser = userMapper.selectUsersByQuery(user);
                            if (!targetUser.isEmpty() && Objects.equal(targetUser.get(0).getEnable(), 0)) {
                                userMapper.delete(email);
                            }

                        }
                    }).build();


    private final Cache<String, String> resetCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15,
            TimeUnit.MINUTES).build();

    @Async
    public void sendMail(String title, String url, String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(url, true);
        mailSender.send(mimeMessage);
    }

    @Async
    public void registerNotify(String email) throws MessagingException {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        StringBuilder url = new StringBuilder();
        url.append("<a href=\"http://");
        url.append(domainName);
        url.append("/accounts/verify?key=");
        url.append(randomKey+"\">click link to activate</a>");
        sendMail("activation email from AustinRealtor", url.toString(), email);
    }

    @Async
    public void resetNotify(String email) throws MessagingException {
        String randomKey = RandomStringUtils.randomAlphanumeric(10);
        resetCache.put(randomKey, email);
        StringBuilder url = new StringBuilder();
        url.append("<a href=\"http://");
        url.append(domainName);
        url.append("/accounts/reset?key=");
        url.append(randomKey+"\">click the link to reset</a>");
        sendMail("reset password email from AustinRealtor", url.toString(), email);
    }

    public String getResetEmail(String key) {
        return resetCache.getIfPresent(key);
    }

    public void invalidateRestKey(String key) {
        resetCache.invalidate(key);
    }

    public boolean enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email)) {
            return false;
        }
        User updateUser = new User();
        updateUser.setEmail(email);
        updateUser.setEnable(1);
        userMapper.update(updateUser);
        registerCache.invalidate(key);
        return true;
    }
}
