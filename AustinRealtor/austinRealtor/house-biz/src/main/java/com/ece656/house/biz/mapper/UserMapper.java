package com.ece656.house.biz.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ece656.house.common.model.User;

import java.util.List;

public interface UserMapper {

    public List<User> selectUsers();

    public int insert(User account);

    public void delete(String email);

    public int update(User updateUser);

    public List<User> selectUsersByQuery(User user);

    public long selectUserCount();
}
