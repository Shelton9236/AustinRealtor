package com.ece656.house.web.controller;

import java.util.List;

import com.ece656.house.common.utils.FileUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ece656.house.biz.service.AgencyService;
import com.ece656.house.biz.service.CityService;
import com.ece656.house.biz.service.CommentService;
import com.ece656.house.biz.service.HouseService;
import com.ece656.house.biz.service.RecommendService;
import com.ece656.house.common.constants.CommonConstants;
import com.ece656.house.common.constants.HouseUserType;
import com.ece656.house.common.model.Comment;
import com.ece656.house.common.model.House;
import com.ece656.house.common.model.HouseUser;
import com.ece656.house.common.model.User;
import com.ece656.house.common.model.UserMsg;
import com.ece656.house.common.page.PageData;
import com.ece656.house.common.page.PageParams;
import com.ece656.house.common.result.ResultMsg;
import com.ece656.house.web.interceptor.UserContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/house/list")
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap) {
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        List<House> hotHouses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", hotHouses);
        modelMap.put("ps", ps);
        modelMap.put("vo", query);
        return "house/listing";
    }

    @RequestMapping("/house/toAdd")
    public String toAdd(ModelMap modelMap) {
        modelMap.put("citys", cityService.getAllCitys());
        modelMap.put("communitys", houseService.getAllCommunitys());
        return "/house/add";
    }

    @RequestMapping("/house/add")
    public String doAdd(House house) {
        User user = UserContext.getUser();
        house.setState(CommonConstants.HOUSE_STATE_UP);
        houseService.addHouse(house, user);
        return "redirect:/house/ownlist";
    }

    @RequestMapping("house/ownlist")
    public String ownlist(House house, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(false);
        modelMap.put("ps", houseService.queryHouse(house, PageParams.build(pageSize, pageNum)));
        modelMap.put("pageType", "own");
        return "/house/ownlist";
    }

    @RequestMapping("house/detail")
    public String houseDetail(Long id, ModelMap modelMap) {
        House house = houseService.queryOneHouse(id);
        HouseUser houseUser = houseService.getHouseUser(id);
        recommendService.increase(id);
        List<Comment> comments = commentService.getHouseComments(id, 8);
        if(houseUser != null) {
            if (houseUser.getUserId() != null && !houseUser.getUserId().equals(0)) {
                modelMap.put("agent", agencyService.getAgentDetail(houseUser.getUserId()));
            }
        }
        List<House> rcHouses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", rcHouses);
        modelMap.put("house", house);
        modelMap.put("commentList", comments);
        return "/house/detail";
    }

    @RequestMapping("house/leaveMsg")
    public String houseMsg(UserMsg userMsg) {
        houseService.addUserMsg(userMsg);
        return "redirect:/house/detail?id=" + userMsg.getHouseId() + ResultMsg.successMsg("Success").asUrlParams();
    }

    @ResponseBody
    @RequestMapping("house/rating")
    public ResultMsg houseRate(Double rating, Long id) {
        houseService.updateRating(id, rating);
        return ResultMsg.successMsg("ok");
    }

    @ResponseBody
    @RequestMapping("house/bookmark")
    public ResultMsg bookmark(Long id) {
        User user = UserContext.getUser();
        houseService.bindUser2House(id, user.getId(), true);
        return ResultMsg.successMsg("ok");
    }

    @ResponseBody
    @RequestMapping("house/unbookmark")
    public ResultMsg unbookmark(Long id) {
        User user = UserContext.getUser();
        houseService.unbindUser2House(id, user.getId(), HouseUserType.BOOKMARK);
        return ResultMsg.successMsg("ok");
    }

    @RequestMapping(value = "house/del")
    public String delsale(Long id, String pageType) {
        User user = UserContext.getUser();
        houseService.unbindUser2House(id, user.getId(), pageType.equals("own") ? HouseUserType.SALE : HouseUserType
                .BOOKMARK);
        return "redirect:/house/ownlist";
    }

    @RequestMapping("house/bookmarked")
    public String bookmarked(House house, Integer pageNum, Integer pageSize, ModelMap modelMap) {
        User user = UserContext.getUser();
        house.setBookmarked(true);
        house.setUserId(user.getId());
        modelMap.put("ps", houseService.queryHouse(house, PageParams.build(pageSize, pageNum)));
        modelMap.put("pageType", "book");
        return "/house/ownlist";
    }

    @RequestMapping("/exportHouseinfo")
    public void exportHouse(HttpServletRequest request, HttpServletResponse response) {
        List<House> houseList = houseService.getHouses();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("house info excel");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        String[] head = {"id", "name", "type", "price", "images", "area", "beds", "baths", "rating", "remarks", "properties", "floor plan", "tags",
                "create_time", "city_id", "community_id", "address", "state"};
        HSSFCell cell;
        for (int iHead = 0; iHead < head.length; iHead++) {
            cell = row.createCell(iHead);
            cell.setCellValue(head[iHead]);
            cell.setCellStyle(cellStyle);
        }
        for (int iBody = 0; iBody < houseList.size(); iBody++) {
            row = sheet.createRow(iBody + 1);
            House hs = houseList.get(iBody);
            String[] userArray = new String[18];
            userArray[0] = hs.getId() + "";
            userArray[1] = hs.getName();
            userArray[2] = hs.getType() == 1 ? "For Sale" : "For Rent";
            userArray[3] = hs.getPrice() + "";
            userArray[4] = hs.getImages();
            userArray[5] = hs.getArea() + "";
            userArray[6] = hs.getBeds() + "";
            userArray[7] = hs.getBaths() + "";
            userArray[8] = hs.getRating() + "";
            userArray[9] = hs.getRemarks();
            userArray[10] = hs.getProperties();
            userArray[11] = hs.getFloorPlan();
            userArray[12] = hs.getTags();
            userArray[13] = hs.getCreateTime() + "";
            userArray[14] = hs.getCityId() + "";
            userArray[15] = hs.getCommunityId() + "";
            userArray[16] = hs.getAddress();
            userArray[17] = hs.getState() == 1 ? "on sale" : "not on sale";
            for (int iArray = 0; iArray < userArray.length; iArray++) {
                row.createCell(iArray).setCellValue(userArray[iArray]);
            }

        }
        FileUtil.createFile(response, workbook, "house info excel");
    }

    @PostMapping("/importHouseinfo")
    public boolean addHouseinfo(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = houseService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
