package com.longkhoa.fpolyfindroom.presenter.location;

import com.longkhoa.fpolyfindroom.model.City;

import java.util.List;

public interface LocationInterface {
    void getCity(List<City> listCity);
    void getDistrictByCity(List<City> listCity);
    void getWardByDistrict(List<City> listCity);
}
