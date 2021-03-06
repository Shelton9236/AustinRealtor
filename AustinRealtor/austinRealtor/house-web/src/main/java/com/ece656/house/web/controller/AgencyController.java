package com.ece656.house.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Objects;
import com.ece656.house.biz.service.AgencyService;
import com.ece656.house.biz.service.HouseService;
import com.ece656.house.biz.service.MailService;
import com.ece656.house.biz.service.RecommendService;
import com.ece656.house.common.constants.CommonConstants;
import com.ece656.house.common.model.Agency;
import com.ece656.house.common.model.House;
import com.ece656.house.common.model.User;
import com.ece656.house.common.page.PageData;
import com.ece656.house.common.page.PageParams;
import com.ece656.house.common.result.ResultMsg;
import com.ece656.house.web.interceptor.UserContext;

import javax.mail.MessagingException;

@Controller
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private MailService mailService;

    @RequestMapping("agency/create")
    public String agencyCreate() {
        User user = UserContext.getUser();
        if (user == null || !Objects.equal(user.getEmail(), "jgcs12345@163.com")) {
            return "redirect:/accounts/signin?" + ResultMsg.successMsg("Please Login!").asUrlParams();
        }
        return "/user/agency/create";
    }


    @RequestMapping("/agency/agentList")
    public String agentList(Integer pageSize, Integer pageNum, ModelMap modelMap) {
        if (pageSize == null) {
            pageSize = 6;
        }
        PageData<User> ps = agencyService.getAllAgent(PageParams.build(pageSize, pageNum));
        List<House> houses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("ps", ps);
        return "/user/agent/agentList";
    }

    @RequestMapping("/agency/agentDetail")
    public String agentDetail(Long id, ModelMap modelMap) {
        User user = agencyService.getAgentDetail(id);
        List<House> houses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        House query = new House();
        query.setUserId(id);
        query.setBookmarked(false);
        PageData<House> bindHouse = houseService.queryHouse(query, new PageParams(3, 1));
        if (bindHouse != null) {
            modelMap.put("bindHouses", bindHouse.getList());
        }
        modelMap.put("recomHouses", houses);
        modelMap.put("agent", user);
        modelMap.put("agencyName", user.getAgencyName());
        return "/user/agent/agentDetail";
    }

    @RequestMapping("/agency/agentMsg")
    public String agentMsg(Long id, String msg, String name, String email, ModelMap modelMap) {
        User user = agencyService.getAgentDetail(id);
        try {
            mailService.sendMail("Consulting", "email:" + email + ",msg:" + msg, user.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/agency/agentDetail?id=" + id + "&" + ResultMsg.successMsg("Success").asUrlParams();
    }

    @RequestMapping("/agency/agencyDetail")
    public String agencyDetail(Integer id, ModelMap modelMap) {
        Agency agency = agencyService.getAgency(id);
        List<House> houses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("agency", agency);
        return "/user/agency/agencyDetail";
    }


    @RequestMapping("agency/list")
    public String agencyList(ModelMap modelMap) {
        List<Agency> agencies = agencyService.getAllAgency();
        List<House> houses = recommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("agencyList", agencies);
        return "/user/agency/agencyList";
    }

    @RequestMapping("agency/submit")
    public String agencySubmit(Agency agency) {
        User user = UserContext.getUser();
        if (user == null || !Objects.equal(user.getEmail(), "jgcs12345@163.com")) {
            return "redirect:/accounts/signin?" + ResultMsg.successMsg("Please Login!").asUrlParams();
        }
        agencyService.add(agency);
        return "redirect:/index?" + ResultMsg.successMsg("Success").asUrlParams();
    }
}
