package com.ece656.house.biz.mapper;

import com.ece656.house.common.model.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommunityMapper {
    @Select("SELECT * FROM vw_community")
    List<Community> selectAllCommunity();

    @Select("SELECT COUNT(*) FROM vw_community")
    Long selectCount();

    @Insert("INSERT INTO vw_community(,NAME,CITY_NAME) VALUES(#{cityCode},#{name},#{cityName})")
    Integer insert(Community community);

    @Select("SELECT * FROM vw_community WHERE ID = #{id}")
    Community getCommunityById(@Param("id") Integer id);

    @Update("UPDATE vw_community SET CITY_CODE = #{cityCode},NAME=#{name},CITY_NAME =#{cityName} WHERE ID = #{id}")
    void update(Community community);

    @Delete("DELETE FROM vw_community WHERE ID = #{id}")
    void delete(Integer id);
}
