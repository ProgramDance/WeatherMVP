package com.demo.weathermvp.presenter;

import android.util.Log;

import com.demo.weathermvp.contract.SupportCityContract;
import com.demo.weathermvp.model.GetRemoteListener;
import com.demo.weathermvp.model.SupportCityModel;
import com.demo.weathermvp.model.bean.SupportCitys;
import com.demo.weathermvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SupportCityPresenter implements SupportCityContract.Presenter {
    private SupportCityModel model;
    private SupportCityContract.View view;

    public SupportCityPresenter(SupportCityContract.View view) {
        this.view = view;
        model = new SupportCityModel();
    }

    @Override
    public void loadSupportCitys() {
        view.showLoading(true);

        model.getSupportCitys(new GetRemoteListener<SupportCitys>() {
            @Override
            public void onSuccess(SupportCitys supportCitys) {
                view.showLoading(false);

                if (supportCitys.getReason().equals("查询成功")) {
                    view.refreshCityList(getSupportCitys(supportCitys.getResult()));
                } else {
                    view.showErrInfo(supportCitys.getReason());
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
        loadSupportCitys();
    }

    private List<String> getSupportCitys(List<SupportCitys.ResultBean> resultBeans) {
        List<String> citys = new ArrayList<>();

        for (SupportCitys.ResultBean resultBean : resultBeans) {
            String city = resultBean.getCity();
            if (!citys.contains(city)) {
                citys.add(city);
            }
        }

        Log.d(Constants.LOG_TAG, "getSupportCitys>>citys:" + citys.toString());

        return citys;
    }
}
