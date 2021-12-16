package com.ece656.house.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ece656.house.common.model.City;

@Service
public class CityService {
    public List<City> getAllCitys() {
        City city = new City();
        city.setCityCode("512");
        city.setCityName("Austin");
        city.setId(1);
        return Lists.newArrayList(city);
    }
}
