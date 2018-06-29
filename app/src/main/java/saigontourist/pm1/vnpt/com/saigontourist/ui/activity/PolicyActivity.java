package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.PolicyResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.FAQAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.PolicyAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.FAQPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.PolicyPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.PolicyView;

public class PolicyActivity extends BaseActivity implements PolicyView {


    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.tv_null)
    TextView tvNull;
    private List<PolicyResponse.Datum> dataList;
    private PolicyAdapter policyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Inject
    PolicyPresenter policyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        ButterKnife.bind(this);
        setTitle(getString(R.string.str_policy));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initView() {
        policyPresenter.setView(this);
        policyPresenter.onViewCreate();
        showProgressBar();
        policyPresenter.getPolicy();
        // init recyleview
        dataList = new ArrayList<>();
        policyAdapter = new PolicyAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(policyAdapter);
        // end

    }

    private void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPolicySuccess(PolicyResponse response) {
        hideProgressBar();
        if(response.getData() != null && response.getData() != null){
            tvNull.setVisibility(View.GONE);
            dataList.addAll(response.getData());
            policyAdapter.notifyDataSetChanged();
        }else {
            tvNull.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPolicyFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onPolicyError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }
}
