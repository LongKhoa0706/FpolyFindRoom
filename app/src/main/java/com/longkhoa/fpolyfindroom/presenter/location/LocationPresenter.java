package com.longkhoa.fpolyfindroom.presenter.location;

import android.util.Log;

import com.longkhoa.fpolyfindroom.model.City;
import com.longkhoa.fpolyfindroom.model.MyResultCity;
import com.longkhoa.fpolyfindroom.networking.RetrofitClient;
import com.longkhoa.fpolyfindroom.service.LocationService;
import com.longkhoa.fpolyfindroom.service.RoomService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationPresenter {
    LocationInterface locationInterface;
    LocationService locationService;

    public LocationPresenter(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }

    public void createLocation(){
        locationService = RetrofitClient.getRetrofitInstance().newBuilder().baseUrl("https://thongtindoanhnghiep.co/api/").addConverterFactory(GsonConverterFactory.create()).build().create(LocationService.class);
        locationService.getCity().enqueue(new Callback<MyResultCity>() {
            @Override
            public void onResponse(Call<MyResultCity> call, Response<MyResultCity> response) {
                locationInterface.getCity(response.body().getLtsItem());
            }

            @Override
            public void onFailure(Call<MyResultCity> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());
            }
        });
    }

    public void getDistrictByCity(int idCity){
        locationService = RetrofitClient.getRetrofitInstance().newBuilder().baseUrl("https://thongtindoanhnghiep.co/api/").addConverterFactory(GsonConverterFactory.create()).build().create(LocationService.class);
        locationService.getDistrictByCity(idCity).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
              locationInterface.getDistrictByCity(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());

            }
        });
    }


    public void getWardByDistrict(int idDistrict){
        locationService = RetrofitClient.getRetrofitInstance().newBuilder().baseUrl("https://thongtindoanhnghiep.co/api/").addConverterFactory(GsonConverterFactory.create()).build().create(LocationService.class);
        locationService.getWardByDistrict(idDistrict).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                locationInterface.getWardByDistrict(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.d("KETQUA",t.getMessage());

            }
        });
    }
}
