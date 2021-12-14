package com.ece656.house.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ece656.house.common.model.Comment;
import com.ece656.house.common.model.Community;
import com.ece656.house.common.model.House;
import com.ece656.house.common.model.UserMsg;
import com.ece656.house.common.page.PageParams;
import org.apache.ibatis.annotations.Select;


public interface CommentMapper {

    List<House> selectHouse(@Param("house") House query, @Param("pageParams") PageParams pageParams);

    Long selectHouseCount(@Param("house") House query);

    List<Community> selectCommunity(Community community);

    int insertUserMsg(UserMsg userMsg);

    int updateHouse(House house);

    int insert(Comment comment);

    List<Comment> selectComments(@Param("houseId") long houseId, @Param("size") int size);

    @Select("SELECT * FROM comment")
    List<Comment> selectAllComments();

    @Select("SELECT COUNT(*) FROM comment")
    Long selectCount();

    @Delete("DELETE FROM comment WHERE ID = #{id}")
    void delete(Integer id);
}
