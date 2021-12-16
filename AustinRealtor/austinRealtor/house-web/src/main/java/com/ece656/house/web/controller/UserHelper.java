package com.ece656.house.web.controller;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.ece656.house.common.model.User;
import com.ece656.house.common.result.ResultMsg;

public class UserHelper {

    public static ResultMsg validate(User accout) {
        if (StringUtils.isBlank(accout.getEmail())) {
            return ResultMsg.errorMsg("invalid Email");
        }
        if (StringUtils.isBlank(accout.getConfirmPasswd()) || StringUtils.isBlank(accout.getPasswd())
                || !accout.getPasswd().equals(accout.getConfirmPasswd())) {
            return ResultMsg.errorMsg("invalid Email");
        }
        if (accout.getPasswd().length() < 6) {
            return ResultMsg.errorMsg("passwords less than 6 digit");
        }
        return ResultMsg.successMsg("");
    }

    public static ResultMsg validateResetPassword(String key, String password, String confirmPassword) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return ResultMsg.errorMsg("parameter invalid");
        }
        if (!Objects.equal(password, confirmPassword)) {
            return ResultMsg.errorMsg("confirm password");
        }
        return ResultMsg.successMsg("");
    }
}
