package com.ece656.house.admin.controller;

import com.ece656.house.biz.service.CommentService;
import com.ece656.house.biz.service.UserService;
import com.ece656.house.common.model.Comment;
import com.ece656.house.common.model.User;
import com.ece656.house.common.result.ResultMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admincomment/list")
    public String comment_list(ModelMap modelMap) {
        List<Comment> comments = commentService.getAllComments();
        for(int i=0;i<comments.size();i++)
        {
            User user = userService.getUserById(comments.get(i).getUserId());
            comments.get(i).setUserName(user.getName());
        }
        Long count = commentService.getCount();
        modelMap.put("comments", comments);
        modelMap.put("count", count);
        return "/admin/feedback-list";
    }

    @RequestMapping("/admincomment/delete")
    public String deletecomment(@Param("id") Integer id) {
        commentService.deleteCommentById(id);
        return "redirect:/admin/admincomment/list?id=" + id + ResultMsg.successMsg("删除成功").asUrlParams();
    }
}
