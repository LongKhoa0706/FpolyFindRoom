package com.longkhoa.fpolyfindroom.service;

import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationService {

    @GET("city")
    Call<MyResultCity> getCity();

    @GET("city/{id}/district")
    Call<List<City>> getDistrictByCity(@Path("id") int idCity);

    @GET("district/{id}/ward")
    Call<List<City>> getWardByDistrict(@Path("id") int idDistrict);
}
