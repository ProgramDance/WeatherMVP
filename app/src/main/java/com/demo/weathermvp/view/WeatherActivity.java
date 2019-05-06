package com.demo.weathermvp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.weathermvp.BuildConfig;
import com.demo.weathermvp.R;
import com.demo.weathermvp.contract.WeatherContract;
import com.demo.weathermvp.presenter.WeatherPresenter;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View, View.OnClickListener {
    private MaterialDialog dialog;
    private TextView tvResult;
    private LinearLayout llErrInfo;
    private TextView tvErrInfo;
    private EditText etCity;
    private WeatherContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        setListener();
        createPresenter();
    }

    private void initView() {
        initDialog();
        tvResult = (TextView) findViewById(R.id.tv_result);
        llErrInfo = (LinearLayout) findViewById(R.id.ll_err);
        tvErrInfo = (TextView) findViewById(R.id.tv_err_info);
        etCity = (EditText) findViewById(R.id.et_city);
    }

    private void setListener() {
        findViewById(R.id.btn_request).setOnClickListener(this);
        findViewById(R.id.btn_support_city).setOnClickListener(this);
    }

    private void initDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        dialog = builder.content("拼命加载中...")
                .progress(true, 0)
                .build();
    }

    @Override
    public void createPresenter() {
        presenter = new WeatherPresenter(this);
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void showWeatherInfo(String info) {
        showWeatherOrErrInfo(true);
        tvResult.setText(info);
    }

    @Override
    public void clearWeatherInfo() {
        showWeatherOrErrInfo(true);
        tvResult.setText("");
    }

    @Override
    public void showErrInfo(String info) {
        showWeatherOrErrInfo(false);
        tvErrInfo.setText(info);
    }

    private void showWeatherOrErrInfo(boolean isShowWeather) {
        if (isShowWeather) {
            tvResult.setVisibility(View.VISIBLE);
            llErrInfo.setVisibility(View.GONE);
        } else {
            tvResult.setVisibility(View.GONE);
            llErrInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request:
                presenter.getWeatherInfo(etCity.getText().toString());
                break;
            case R.id.btn_support_city:
                startActivity(new Intent(this, SupportActivity.class));
                break;
        }
    }
}
