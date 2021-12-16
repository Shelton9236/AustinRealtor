package com.ece656.house.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ece656.house.biz.service.CommentService;
import com.ece656.house.common.model.User;
import com.ece656.house.web.interceptor.UserContext;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "comment/leaveComment", method = {RequestMethod.POST, RequestMethod.GET})
    public String leaveComment(String content, Long houseId, ModelMap modelMap) {
        User user = UserContext.getUser();
        Long userId = user.getId();
        commentService.addHouseComment(houseId, content, userId);
        return "redirect:/house/detail?id=" + houseId;
    }
}
