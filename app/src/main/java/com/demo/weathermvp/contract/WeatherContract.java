package com.demo.weathermvp.contract;

import com.demo.weathermvp.base.BasePresenter;
import com.demo.weathermvp.base.BaseView;

public class WeatherContract {
    public interface Presenter extends BasePresenter{
        void getWeatherInfo(String city);
    }

    public interface View extends BaseView{
        /**
         * 显示正在加载
         * @param isShow
         */
        void showLoading(boolean isShow);

        /**
         * 显示查询的天气信息
         * @param info
         */
        void showWeatherInfo(String info);

        /**
         * 清除显示的天气信息
         */
        void clearWeatherInfo();

        /**
         * 显示错误提示信息
         * @param info
         */
        void showErrInfo(String info);
    }
}
