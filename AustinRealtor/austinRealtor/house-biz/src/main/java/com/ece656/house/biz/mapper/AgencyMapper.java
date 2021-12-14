package com.ece656.house.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ece656.house.common.model.Agency;
import com.ece656.house.common.model.User;
import com.ece656.house.common.page.PageParams;

public interface AgencyMapper {

    List<Agency> select(Agency agency);

    int insert(Agency agency);

    List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

    Long selectAgentCount(@Param("user") User user);
}
