package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.HistoryRankResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.NotificationResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.HistoryRankAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.NotificationAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetNotificationPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.HistoryRankPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.HistoryRankView;

public class HistoryRankActivity extends BaseActivity implements HistoryRankView {

    @BindView(R.id.lo_title_rank)
    LinearLayout loTitleRank;
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_null)
    TextView loNull;
    @Inject
    HistoryRankPresenter historyRankPresenter;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<HistoryRankResponse.LichSu> dataList;
    private HistoryRankAdapter historyRankAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_rank);
        ButterKnife.bind(this);
        setTitle(getString(R.string.str_lich_su_hang));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }


    private void initView() {
        historyRankPresenter.setView(this);
        historyRankPresenter.onViewCreate();

        dataList = new ArrayList<>();
        historyRankAdapter = new HistoryRankAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(historyRankAdapter);

        if(tinyDB.getBoolean(getString(R.string.IS_LOGIN))
                && tinyDB.getString(getString(R.string.TOKEN_USER)) != null){
            String tokenUser = tinyDB.getString(getString(R.string.TOKEN_USER));
            historyRankPresenter.getHistoryRankPresenter(tokenUser);
        }else {
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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

    @Override
    public void onGetHistorySuccess(HistoryRankResponse response) {
        hideProgressBar();
        if (dataList.size() > 0) {
            dataList.clear();
        }
        if(response.getData().getLichSu().size() > 0){
            dataList.addAll(response.getData().getLichSu());
            historyRankAdapter.notifyDataSetChanged();
        }else {
            loNull.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGetHistoryFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onGetHistoryError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }
}
