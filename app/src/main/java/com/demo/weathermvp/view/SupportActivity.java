package com.demo.weathermvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.weathermvp.R;
import com.demo.weathermvp.contract.SupportCityContract;
import com.demo.weathermvp.presenter.SupportCityPresenter;

import java.util.ArrayList;
import java.util.List;


public class SupportActivity extends AppCompatActivity implements SupportCityContract.View {
    private RecyclerView rvSupportCity;
    private SupportCityAdapter adapter;
    private MaterialDialog dialog;
    private SupportCityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        initView();
        createPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void initView() {
        initDialog();
        rvSupportCity = (RecyclerView) findViewById(R.id.rv_support_city);
        rvSupportCity.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SupportCityAdapter();
        rvSupportCity.setAdapter(adapter);
    }

    private void initDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        dialog = builder.content("拼命加载中...")
                .progress(true, 0)
                .build();
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
    public void refreshCityList(List<String> citys) {
        adapter.refreshDatas(citys);
    }

    @Override
    public void showErrInfo(String info) {
        Snackbar.make(rvSupportCity, info, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void createPresenter() {
        presenter = new SupportCityPresenter(this);
    }

    class SupprtCityViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;

        public SupprtCityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
        }
    }

    class SupportCityAdapter extends RecyclerView.Adapter<SupprtCityViewHolder> {
        private List<String> datas;

        public SupportCityAdapter() {
            this.datas = new ArrayList<>();
        }

        public void refreshDatas(List<String> datas) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public SupprtCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_support_city, null);
            return new SupprtCityViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SupprtCityViewHolder holder, int position) {
            holder.tvCity.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }
}
