package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.CategorySpecialSaigonActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.CategorySpecialVpointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.MainFragmentPagerAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.NotificationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOffersFragment extends BaseFragment {

    @BindView(R.id.tvSearch)
    EditText tvSearch;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabsStrip;
    @BindView(R.id.viewPager)
    ViewPager pager;
    Unbinder unbinder;
    @BindView(R.id.iv_notification)
    ImageView ivNotification;
    @BindView(R.id.lo_thong_bao)
    View loThongBao;
    @BindView(R.id.tv_content_saigon1)
    TextView tvContentSagon1;
    @BindView(R.id.tv_content_saigon2)
    TextView tvContentSaigon2;
    @BindView(R.id.ll_spinner_saigon2)
    LinearLayout llSpinner2;
    @BindView(R.id.tv_content_saigon3)
    TextView tvContentSaigon3;
    @BindView(R.id.ll_spinner_saigon3)
    LinearLayout llSpinner3;
    @BindView(R.id.ll_spinner_saigon1)
    LinearLayout llSpinnerSaigon1;
    @BindView(R.id.ll_filter_1)
    LinearLayout llFilter1;
    @BindView(R.id.ll_filter_saigon)
    LinearLayout llFilterSaigon;
    @BindView(R.id.tv_content_vpoint1)
    TextView tvContentVpoint1;
    @BindView(R.id.ll_spinner_vpoint1)
    LinearLayout llSpinnerVpoint1;
    @BindView(R.id.tv_content_vpoint2)
    TextView tvContentVpoint2;
    @BindView(R.id.ll_spinner_vpoint2)
    LinearLayout llSpinnerVpoint2;
    @BindView(R.id.tv_content_vpoint3)
    TextView tvContentVpoint3;
    @BindView(R.id.ll_spinner_vpoint3)
    LinearLayout llSpinnerVpoint3;
    @BindView(R.id.ll_filter2)
    LinearLayout llFilter2;
    @BindView(R.id.ll_filter_vpoint)
    LinearLayout llFilterVpoint;
    private boolean isClickSearchSaigon = true;
    private boolean isClickSearchVpoint = true;

    private int intEnterprisSaigon = 0;
    private int intFieldsSaigon = 0;
    private int intCitieSaigon = 0;

    private int intEnterprisVpoint = 0;
    private int intFieldsVpoint = 0;
    private int intCitieVpoint = 0;

    private int typeView = 0;

    private enum StateFragment {
        SaigonTouris, Vpoint
    }

    private StateFragment state;

    public SpecialOffersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_special_offers, container, false);
        unbinder = ButterKnife.bind(this, result);
        initView();
        return result;
    }

    private void initView() {
        state = StateFragment.SaigonTouris;
        tabsStrip.setShouldExpand(true);
        tabsStrip.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        pager.setAdapter(buildAdapter());
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(pager);
        llFilter1.setVisibility(View.GONE);
        reSetDataFillter();
        addListener();
    }

    private void addListener() {
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reFreshDataFragment();
                swipeToRefresh.setRefreshing(false);
            }
        });
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
//                reSetDataFillter();
                if (position == 0) {
                    state = StateFragment.SaigonTouris;
                } else if (position == 1) {
                    state = StateFragment.Vpoint;
                }
                switchTapFillter();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
                enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });
    }

    @OnClick({R.id.tvSearch, R.id.imageView, R.id.ll_spinner_saigon1, R.id.ll_spinner_saigon2, R.id.ll_spinner_saigon3
            , R.id.ll_spinner_vpoint1, R.id.ll_spinner_vpoint2, R.id.ll_spinner_vpoint3, R.id.iv_notification})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                switch (state) {
                    case SaigonTouris:
                        if (isClickSearchSaigon) {
                            llFilter1.setVisibility(View.VISIBLE);
                            isClickSearchSaigon = false;
                        } else {
                            llFilter1.setVisibility(View.GONE);
                            isClickSearchSaigon = true;
                        }
                        break;
                    case Vpoint:
                        if (isClickSearchVpoint) {
                            llFilter2.setVisibility(View.VISIBLE);
                            isClickSearchVpoint = false;
                        } else {
                            llFilter2.setVisibility(View.GONE);
                            isClickSearchVpoint = true;
                        }
                        break;
                }

                break;
            case R.id.imageView:
                reFreshDataFragment();
                break;
            case R.id.ll_spinner_saigon1:
            case R.id.ll_spinner_vpoint1:
                EventBus.getDefault().postSticky(new MessageEvent.MessageTypeCategoryAtivity(0));
                nextToActivity();
                break;
            case R.id.ll_spinner_saigon2:
            case R.id.ll_spinner_vpoint2:
                EventBus.getDefault().postSticky(new MessageEvent.MessageTypeCategoryAtivity(1));
                nextToActivity();
                break;
            case R.id.ll_spinner_saigon3:
            case R.id.ll_spinner_vpoint3:
                EventBus.getDefault().postSticky(new MessageEvent.MessageTypeCategoryAtivity(2));
                nextToActivity();
                break;
            case R.id.iv_notification:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
        }
    }

    private void enableDisableSwipeRefresh(boolean enable) {
        if (swipeToRefresh != null) {
            swipeToRefresh.setEnabled(enable);
        }
    }

    private PagerAdapter buildAdapter() {
        return (new MainFragmentPagerAdapter(getActivity(), getChildFragmentManager()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void reFreshDataFragment() {
        switch (state) {
            case Vpoint:
                MessageEvent.MessageSpecialVpointFramgent messageVpoint = new MessageEvent.MessageSpecialVpointFramgent();
                messageVpoint.setCity(intCitieVpoint);
                messageVpoint.setEnterPrise(intEnterprisVpoint);
                messageVpoint.setFieldVpoint(intFieldsVpoint);
                messageVpoint.setTextSearch(tvSearch.getText().toString().trim());
                EventBus.getDefault().postSticky(messageVpoint);
                break;
            case SaigonTouris:
                MessageEvent.MessageSpecialSaigonFramgent messageSaigon = new MessageEvent.MessageSpecialSaigonFramgent();
                messageSaigon.setFieldTouris(intFieldsSaigon);
                messageSaigon.setProvinceCode(intCitieSaigon);
                messageSaigon.setTradeMark(intEnterprisSaigon);
                messageSaigon.setTextSearch(tvSearch.getText().toString().trim());
                EventBus.getDefault().postSticky(messageSaigon);
                break;
        }
    }

    private void reSetDataFillter() {
        llFilterSaigon.setVisibility(View.VISIBLE);
        llFilterVpoint.setVisibility(View.GONE);

        tvContentSagon1.setText("Tất cả");
        tvContentSaigon2.setText("Tất cả");
        tvContentSaigon3.setText("Tất cả");
        intEnterprisSaigon = 0;
        intFieldsSaigon = 0;
        intCitieSaigon = 0;

        tvContentVpoint1.setText("Tất cả");
        tvContentVpoint2.setText("Tất cả");
        tvContentVpoint3.setText("Tất cả");
        intEnterprisVpoint = 0;
        intFieldsVpoint = 0;
        intCitieVpoint = 0;
    }

    private void switchTapFillter() {
        switch (state) {
            case Vpoint:
                llFilterVpoint.setVisibility(View.VISIBLE);
                llFilterSaigon.setVisibility(View.GONE);
                break;
            case SaigonTouris:
                llFilterVpoint.setVisibility(View.GONE);
                llFilterSaigon.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void nextToActivity() {
        switch (state) {
            case SaigonTouris:
                startActivity(new Intent(getActivity(), CategorySpecialSaigonActivity.class));
                break;
            case Vpoint:
                startActivity(new Intent(getActivity(), CategorySpecialVpointActivity.class));
                break;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageCategorySpecial event) {
        /* Do something */
        if (event != null) {
            typeView = event.getTypeCategory();
            switch (typeView) {
                case 0:
                    if (isTabSaiGon()) {
                        tvContentSagon1.setText(event.getNameValue());
                        tvContentSagon1.setSelected(true);
                        intEnterprisSaigon = event.getId();
                    } else {
                        tvContentVpoint1.setText(event.getNameValue());
                        intEnterprisVpoint = event.getId();
                        tvContentVpoint1.setSelected(true);
                    }

                    break;
                case 1:
                    if (isTabSaiGon()) {
                        tvContentSaigon2.setText(event.getNameValue());
                        tvContentSaigon2.setSelected(true);
                        intFieldsSaigon = event.getId();
                    } else {
                        tvContentVpoint2.setText(event.getNameValue());
                        intFieldsVpoint = event.getId();
                        tvContentVpoint2.setSelected(true);
                    }
                    break;
                case 2:
                    if (isTabSaiGon()) {
                        tvContentSaigon3.setSelected(true);
                        tvContentSaigon3.setText(event.getNameValue());
                        intCitieSaigon = event.getId();
                    } else {
                        tvContentVpoint3.setSelected(true);
                        tvContentVpoint3.setText(event.getNameValue());
                        intCitieVpoint = event.getId();
                    }

                    break;
            }
        }
    }

    private boolean isTabSaiGon() {
        switch (state) {
            case Vpoint:
                return false;
            case SaigonTouris:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Glide.with(getActivity().getApplicationContext()).pauseRequests();
    }
}
