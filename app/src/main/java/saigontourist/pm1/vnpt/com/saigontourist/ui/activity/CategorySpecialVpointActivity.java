package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.CategorySpecialAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategoryVpointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;

public class CategorySpecialVpointActivity extends BaseActivity implements SpecialCategoryVpointView {
    @BindView(R.id.rcvDanhSach)
    ListView rcvDanhSach;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    SpecialCategoryVpointPresenter specialVpointPresenter;

    private List<DataCategoryResult> dataVpointResults;
    private CategorySpecialAdapter adapter;

    private int TYPE_CATEGORY = 0;

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_special);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        tvTitle.setTypeface(face);
        initView();
        initListener();
    }

    private void initView() {
        setTitleToobar("Tìm kiếm ưu đãi");
        specialVpointPresenter.setView(this);
        specialVpointPresenter.onViewCreate();
        dataVpointResults = new ArrayList<>();
        adapter = new CategorySpecialAdapter(this, dataVpointResults, TYPE_CATEGORY);
        rcvDanhSach.setAdapter(adapter);
    }
    private void setTextTitle() {
        switch (TYPE_CATEGORY) {
            case 0:
                tvTitle.setText("Theo Doanh Nghiệp");
                break;
            case 1:
                tvTitle.setText("Theo Lĩnh Vực");
                break;
            case 2:
                tvTitle.setText("Theo Thành Phố");
                break;
        }
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDataFromServer() {
        showProgressBar();
        switch (TYPE_CATEGORY) {
            case 0:
                specialVpointPresenter.getListEnterprise();
                break;
            case 1:
                specialVpointPresenter.getListFields();
                break;
            case 2:
                specialVpointPresenter.getListCities();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    @Override
    public void onGetListFieldsSuccses(List<DataCategoryResult> listFields) {
        hideProgressBar();
        binDataToAdapter(listFields);

    }

    @Override
    public void onGetListEnterpriseSuccses(List<DataCategoryResult> listEnterprise) {
        hideProgressBar();
        binDataToAdapter(listEnterprise);
    }

    @Override
    public void onGetListCitiesSuccses(List<DataCategoryResult> listCities) {
        hideProgressBar();
        binDataToAdapter(listCities);
    }

    @Override
    public void onGetListFieldsFailed(String message) {
        hideProgressBar();
    }

    @Override
    public void onGetListEnterpriseFailed(String message) {
        hideProgressBar();
    }

    @Override
    public void onGetListCitiesFailed(String message) {
        hideProgressBar();
    }

    @Override
    public void onGetListError(Throwable e) {
        hideProgressBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageTypeCategoryAtivity event) {
        /* Do something */
        if (event != null) {
            setTextTitle();
            TYPE_CATEGORY = event.getTypeCategory();
            adapter.setTypeCategory(TYPE_CATEGORY);
            getDataFromServer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        hideProgressBar();
    }

    private void binDataToAdapter(List<DataCategoryResult> list) {
        if (list != null && list.size() > 0) {
            if (dataVpointResults != null && dataVpointResults.size() > 0) {
                dataVpointResults.clear();
            }
            dataVpointResults.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
