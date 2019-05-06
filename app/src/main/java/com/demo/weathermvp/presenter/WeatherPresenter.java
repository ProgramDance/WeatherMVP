package com.demo.weathermvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.demo.weathermvp.R;
import com.demo.weathermvp.contract.WeatherContract;
import com.demo.weathermvp.model.GetRemoteListener;
import com.demo.weathermvp.model.WeatherModel;
import com.demo.weathermvp.model.bean.WeatherInfo;

public class WeatherPresenter implements WeatherContract.Presenter {
    private WeatherModel model;
    private WeatherContract.View view;

    public WeatherPresenter(WeatherContract.View view) {
        this.view = view;
        model = new WeatherModel();
    }

    @Override
    public void getWeatherInfo(String city) {
        if(TextUtils.isEmpty(city)){
            view.showErrInfo("城市名不能为空");
            return;
        }

        view.showLoading(true);
        view.clearWeatherInfo();

        model.getWeatherInfo(city, new GetRemoteListener<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                view.showLoading(false);
                if(weatherInfo.getError_code() == 0) {
                    String format = ((Context) view).getString(R.string.weather_show_format);
                    WeatherInfo.ResultBean.RealtimeBean realtime = weatherInfo.getResult().getRealtime();
                    String showReslut = String.format(format, realtime.getTemperature(), realtime.getHumidity(), realtime.getAqi(),
                            realtime.getDirect(), realtime.getPower());
                    view.showWeatherInfo(showReslut);
                }else{
                    view.showErrInfo(weatherInfo.getReason());
                }
            }

            @Override
            public void onError(String msg) {
                view.showLoading(false);
                view.showErrInfo(msg);
            }
        });
    }

    @Override
    public void start() {

    }
}
