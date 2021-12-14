package com.ece656.house.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ece656.house.biz.mapper.CommentMapper;
import com.ece656.house.common.constants.CommonConstants;
import com.ece656.house.common.model.Comment;
import com.ece656.house.common.model.User;
import com.ece656.house.common.utils.BeanHelper;


@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    public void addHouseComment(Long houseId, String content, Long userId) {
        addComment(houseId, content, userId, 1);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addComment(Long houseId, String content, Long userId, int type) {
        Comment comment = new Comment();
        if (type == 1) {
            comment.setHouseId(houseId);
        }
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setType(type);
        BeanHelper.onInsert(comment);
        BeanHelper.setDefaultProp(comment, Comment.class);
        commentMapper.insert(comment);
    }

    public List<Comment> getHouseComments(long houseId, int size) {
        List<Comment> comments = commentMapper.selectComments(houseId, size);
        comments.forEach(comment -> {
            User user = userService.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUserName(user.getName());
        });
        return comments;
    }


    public List<Comment> getAllComments() {
        return commentMapper.selectAllComments();
    }

    public Long getCount() {
        return commentMapper.selectCount();
    }

    public void deleteCommentById(Integer id) {
        commentMapper.delete(id);
    }
}
