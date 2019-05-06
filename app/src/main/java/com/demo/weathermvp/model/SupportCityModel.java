package com.demo.weathermvp.model;

import com.demo.weathermvp.http.HttpHelper;
import com.demo.weathermvp.model.bean.SupportCitys;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SupportCityModel {
    public void getSupportCitys(final GetRemoteListener<SupportCitys> listener){
        HttpHelper.getSupportCitysApi().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SupportCitys>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(SupportCitys value) {
                        listener.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
