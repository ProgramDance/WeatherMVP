package com.demo.weathermvp.http;


import com.demo.weathermvp.model.bean.SupportCitys;
import com.demo.weathermvp.model.bean.WeatherInfo;
import com.demo.weathermvp.utils.Constants;

import io.reactivex.Observable;

public class HttpHelper {
    public static Observable<WeatherInfo> getWeatherInfoApi(String city){
        return RetrofitClient.getInstance().create(ApiService.class).getWeather(city, Constants.WEATHER_KEY);
    }

    public static Observable<SupportCitys> getSupportCitysApi() {
        return RetrofitClient.getInstance().create(ApiService.class).getSupportCitys(Constants.WEATHER_KEY);
    }
}
