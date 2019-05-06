package com.demo.weathermvp.contract;

import com.demo.weathermvp.base.BasePresenter;
import com.demo.weathermvp.base.BaseView;

import java.util.List;

public class SupportCityContract {
    public interface View extends BaseView{
        /**
         * 是否显示正在加载
         * @param isShow
         */
        void showLoading(boolean isShow);

        void refreshCityList(List<String> citys);

        void showErrInfo(String info);
    }

    public interface Presenter extends BasePresenter{
        /**
         * 加载支持的城市
         */
        void loadSupportCitys();
    }
}
