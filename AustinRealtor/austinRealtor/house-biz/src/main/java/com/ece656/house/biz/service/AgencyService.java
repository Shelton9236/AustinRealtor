package com.ece656.house.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ece656.house.biz.mapper.AgencyMapper;
import com.ece656.house.common.model.Agency;
import com.ece656.house.common.model.User;
import com.ece656.house.common.page.PageData;
import com.ece656.house.common.page.PageParams;

@Service
public class AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.prefix}")
    private String imgPrefix;

    public User getAgentDetail(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, PageParams.build(1, 1));
        setImg(list);
        if (!list.isEmpty()) {
            User agent = list.get(0);
            Agency agency = new Agency();
            agency.setId(agent.getAgencyId().intValue());
            List<Agency> agencies = agencyMapper.select(agency);
            if (!agencies.isEmpty()) {
                agent.setAgencyName(agencies.get(0).getName());
            }
            return agent;
        }
        return null;
    }

    private void setImg(List<User> list) {
        list.forEach(i -> {
            i.setAvatar(imgPrefix + i.getAvatar());
        });

    }

    public PageData<User> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        Long count = agencyMapper.selectAgentCount(new User());
        return PageData.buildPage(agents, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    public Agency getAgency(Integer id) {
        Agency agency = new Agency();
        agency.setId(id);
        List<Agency> agencies = agencyMapper.select(agency);
        if (agencies.isEmpty()) {
            return null;
        }
        return agencies.get(0);
    }

    public List<Agency> getAllAgency() {
        return agencyMapper.select(new Agency());
    }

    public int add(Agency agency) {
        return agencyMapper.insert(agency);
    }

    public void sendMsg(User agent, String msg, String name, String email) {

    }

    public long selectAgentCount(User user){
        return agencyMapper.selectAgentCount(new User());
    }
}