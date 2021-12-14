package com.ece656.house.biz.mapper;

import com.ece656.house.common.model.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdminMapper {
    public List<Admin> selectAdmins();

    public int insert(Admin admin);

    public int delete(Integer id);

    public int update(Admin updateAdmin);

    public List<Admin> selectAdminsByQuery(Admin admin);

    public List<Admin> selectAdminByName(Admin admin);

    public Admin selectAdminById(Integer id);

    public long selectAdminCount();

    @Select("SELECT * FROM admin WHERE NAME LIKE #{name}")
    List<Admin> getAdminByName(@RequestParam("name") String name);

    @Select("SELECT COUNT(*) FROM admin WHERE NAME LIKE #{name}")
    Long getadminCount(@RequestParam("name") String name);
}
