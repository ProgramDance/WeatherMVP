package com.demo.weathermvp.http;

import com.demo.weathermvp.model.bean.SupportCitys;
import com.demo.weathermvp.model.bean.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("query")
    Observable<WeatherInfo> getWeather(@Query("city") String city, @Query("key") String key);

    @GET("cityList")
    Observable<SupportCitys> getSupportCitys(@Query("key") String key);
}
