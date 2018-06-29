package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.policy.FAQResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.FAQAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.HistoryPointAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.PointInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.FAQPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.policy.FAQView;

public class FAQActivity extends BaseActivity implements FAQView {

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;

    private List<FAQResponse.Dsbinhluan> dataList;
    private FAQAdapter faqAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Inject
    FAQPresenter faqPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        faqPresenter.setView(this);
        faqPresenter.onViewCreate();
        //set fonts
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        text1.setTypeface(face);
        // end

        // init recyleview
        dataList = new ArrayList<>();
        faqAdapter = new FAQAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(faqAdapter);
        // end
        showProgressBar();
        getTokenDevIfNeed();

    }

    @OnClick(R.id.btnBack)
    public void onViewClicked() {
        onBackPressed();
        this.finish();
    }

    @Override
    public void onGetFAQInfoSuccses(FAQResponse response) {
        hideProgressBar();
        if(response.getData() != null && response.getData().getDsbinhluan() != null){
            dataList.addAll(response.getData().getDsbinhluan());
            faqAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetFAQInfoFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onGetFAQInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        super.onLoadTokenDevUser(tokenDev);
//        AppDef.TOKEN_DEV = tokenDev;
        tinyDB.putString(getString(R.string.TOKEN_DEV), tokenDev);
        faqPresenter.getFAQ(getString(R.string.MA_UNG_DUNG), tokenDev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
