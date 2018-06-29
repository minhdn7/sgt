package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.NotificationAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetNotificationPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetNotificationView;

public class NotificationActivity extends BaseActivity implements GetNotificationView {


    @Inject
    GetNotificationPresenter getNotificationPresenter;
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_null)
    TextView loNull;
    @BindView(R.id.lo_da_dang_nhap)
    LinearLayout loDaDangNhap;
    @BindView(R.id.tv_gioithieu)
    TextView tvGioithieu;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.lo_chua_dang_nhap)
    LinearLayout loChuaDangNhap;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NotificationResponse.Result> dataList;
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        setTitle("Thông báo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        checkLogin();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void initView() {
        getNotificationPresenter.setView(this);
        getNotificationPresenter.onViewCreate();

        dataList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(notificationAdapter);
    }

    private void checkLogin() {
        loChuaDangNhap.setVisibility(View.GONE);
        loDaDangNhap.setVisibility(View.VISIBLE);
        showProgressBar();
        if (tinyDB.getBoolean(getString(R.string.IS_LOGIN))) {
            getNotificationPresenter.getNotification(tinyDB.getString(getString(R.string.TOKEN_USER)));
        } else {
            getNotificationPresenter.getNotification("");
        }
    }

    @Override
    public void onNotificationSuccses(NotificationResponse response) {
        hideProgressBar();
        if (dataList.size() > 0) {
            dataList.clear();
        }
        dataList.addAll(response.getResult());
        notificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNotificationFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onNotificationError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }
}
