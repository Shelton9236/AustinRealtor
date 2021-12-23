package com.ece656.house.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.ece656.house.common.model.Community;
import com.ece656.house.common.model.House;
import com.ece656.house.common.model.HouseUser;
import com.ece656.house.common.model.UserMsg;
import com.ece656.house.common.model.User;
import com.ece656.house.common.page.PageParams;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

public interface HouseMapper {
    public List<House> selectPageHouses(@Param("house") House house, @Param("pageParams") PageParams pageParams);

    public Long selectPageCount(@Param("house") House query);

    public int insert(User account);

    public List<Community> selectCommunity(Community community);

    public int insert(House house);

    public HouseUser selectHouseUser(@Param("userId") Long userId, @Param("id") Long houseId, @Param("type") Integer
            value);

    public HouseUser selectSaleHouseUser(@Param("id") Long houseId);

    public int insertHouseUser(HouseUser houseUser);

    public int insertUserMsg(UserMsg userMsg);

    public int updateHouse(House updateHouse);

    public int updateHouseAll(House updateHouse);

    public int downHouse(Long id);

    public int deleteHouseUser(@Param("id") Long id, @Param("userId") Long userId, @Param("type") Integer value);

    public List<House> selectHouses();

    boolean batchImport(String fileName, MultipartFile file) throws Exception;

    public House selectHouseById(@Param("id") Long id);

    public List<String> selectNationCount();

    @Select("SELECT COUNT(*) FROM vw_house")
    public int selectHouseCount();

    @Delete("DELETE FROM vw_house WHERE ID = #{id}")
    void delete(Integer id);

}
