package com.ece656.house.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ece656.house.common.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ece656.house.biz.service.AgencyService;
import com.ece656.house.biz.service.UserService;
import com.ece656.house.common.constants.CommonConstants;
import com.ece656.house.common.model.User;
import com.ece656.house.common.result.ResultMsg;
import com.ece656.house.common.utils.HashUtils;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AgencyService agencyService;

    @RequestMapping("accounts/register")
    public String accountsRegister(User account, ModelMap modelMap) {
        if (account == null || account.getName() == null) {
            modelMap.put("agencyList", agencyService.getAllAgency());
            return "/user/accounts/register";
        }
        ResultMsg resultMsg = com.ece656.house.web.controller.UserHelper.validate(account);
        if (resultMsg.isSuccess() && userService.addAccount(account)) {
            modelMap.put("email", account.getEmail());
            return "/user/accounts/registerSubmit";
        } else {
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }
    }

    @RequestMapping("accounts/verify")
    public String verify(String key) {
        boolean result = userService.enable(key);
        if (result) {
            return "redirect:/index?" + ResultMsg.successMsg("success").asUrlParams();
        } else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("fail,link is not valid");
        }
    }

    @RequestMapping("/accounts/signin")
    public String signin(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String target = req.getParameter("target");
        if (username == null || password == null) {
            req.setAttribute("target", target);
            return "/user/accounts/signin";
        }
        User user = userService.auth(username, password);
        if (user == null) {
            return "redirect:/accounts/signin?" + "target=" + target + "&username=" + username + "&"
                    + ResultMsg.errorMsg("Wrong Username or Password").asUrlParams();
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute(CommonConstants.USER_ATTRIBUTE, user);
            return StringUtils.isNoneBlank(target) ? "redirect:" + target : "redirect:/index";
        }
    }

    @RequestMapping("accounts/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(CommonConstants.USER_ATTRIBUTE);
            session.invalidate();
        }
        return "redirect:/index";
    }

    @RequestMapping("accounts/profile")
    public String profile(HttpServletRequest req, User updateUser, ModelMap model) {
        if (updateUser.getEmail() == null) {
            return "/user/accounts/profile";
        }
        userService.updateUser(updateUser, updateUser.getEmail());
        User query = new User();
        query.setEmail(updateUser.getEmail());
        List<User> users = userService.getUserByQuery(query);
        req.getSession(true).setAttribute(CommonConstants.USER_ATTRIBUTE, users.get(0));
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("Success").asUrlParams();
    }

    @RequestMapping("accounts/changePassword")
    public String changePassword(String email, String password, String newPassword,
                                 String confirmPassword, ModelMap mode) {
        User user = userService.auth(email, password);
        if (user == null || !confirmPassword.equals(newPassword)) {
            return "redirct:/accounts/profile?" + ResultMsg.errorMsg("Wrong Password").asUrlParams();
        }
        User updateUser = new User();
        updateUser.setPasswd(HashUtils.encryPassword(newPassword));
        userService.updateUser(updateUser, email);
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("Success").asUrlParams();
    }

    @RequestMapping("accounts/remember")
    public String remember(String username, ModelMap modelMap) {
        if (StringUtils.isBlank(username)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("Blank Email").asUrlParams();
        }
        userService.resetNotify(username);
        modelMap.put("email", username);
        return "/user/accounts/remember";
    }

    @RequestMapping("accounts/reset")
    public String reset(String key, ModelMap modelMap) {
        String email = userService.getResetEmail(key);
        if (StringUtils.isBlank(email)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("Expired Link").asUrlParams();
        }
        modelMap.put("email", email);
        modelMap.put("success_key", key);
        return "/user/accounts/reset";
    }

    @RequestMapping(value = "accounts/resetSubmit")
    public String resetSubmit(HttpServletRequest req, User user) {
        ResultMsg retMsg = com.ece656.house.web.controller.UserHelper.validateResetPassword(user.getKey(), user
                .getPasswd(), user.getConfirmPasswd());
        if (!retMsg.isSuccess()) {
            String suffix = "";
            if (StringUtils.isNotBlank(user.getKey())) {
                suffix = "email=" + userService.getResetEmail(user.getKey()) + "&key=" + user.getKey() + "&";
            }
            return "redirect:/accounts/reset?" + suffix + retMsg.asUrlParams();
        }
        User updatedUser = userService.reset(user.getKey(), user.getPasswd());
        req.getSession(true).setAttribute(CommonConstants.USER_ATTRIBUTE, updatedUser);
        return "redirect:/index?" + retMsg.asUrlParams();
    }
}
