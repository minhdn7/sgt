package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseSpecialsSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersObject;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.SpecialOffersAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.PromotionEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.PromotionEnterpriseView;

public class ListPromotionEnterpriseFragment extends BaseFragment implements PromotionEnterpriseView {
    private List<SpecialOffersObject> dataList = new ArrayList<>();
    private static final String TAG = ListPromotionEnterpriseFragment.class.getSimpleName();

    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_null)
    TextView loNull;
    private SpecialOffersAdapter adapter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    PromotionEnterprisePresenter promotionPresenter;

    private int typeDetail = 0;

    private int idEnterprise = 0;
    private int startIndex = 0;
    private int pageSize = 100;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion_enterprise, container, false);
        ButterKnife.bind(this, view);
        promotionPresenter.setView(this);
        promotionPresenter.onViewCreate();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (typeDetail) {
                    case 0:
                        promotionPresenter.getListPromotionOfEnterpriseSaigon(idEnterprise, startIndex, pageSize);
                        break;
                    case 1:
                        promotionPresenter.getListPromotionOfEnterpriseVpoint(idEnterprise);
                        break;
                }

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    private void showListPromotionOfEnterprise() {
        if (dataList != null && dataList.size() > 0) {
            adapter = new SpecialOffersAdapter(getActivity(), dataList);
            rcvDanhSach.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
            rcvDanhSach.setAdapter(adapter);
        }

    }

    @Override
    public void onGetPromotionEnterpriseVpointSuccses(DataCurrentOfEnterpriseVpointResult dataResult) {
        hideProgressBar();
        if (dataResult.getListPromotionEnterprise() != null && dataResult.getListPromotionEnterprise().size() > 0) {
            dataList.addAll(dataResult.getListPromotionEnterprise());
            rcvDanhSach.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            showListPromotionOfEnterprise();
        } else {
            rcvDanhSach.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetPromotionEnterpriseVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetPromotionEnterpriseSaigonSuccses(EnterpriseSpecialsSaigonResult dataResult) {
        hideProgressBar();
        if (dataResult.getListData() != null && dataResult.getListData().size() > 0) {
            dataList.clear();
            dataList.addAll(dataResult.getListData());
            rcvDanhSach.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            showListPromotionOfEnterprise();
        }
        else {
            rcvDanhSach.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetPromotionEnterpriseSaigonFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        promotionPresenter.onViewDestroy();
        EventBus.getDefault().unregister(this);
        hideProgressBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageIdDetailAtivity event) {
        /* Do something */
        if (event != null) {
            idEnterprise = event.getIdEnterprise();
            typeDetail = event.getTypeDetail();
            switch (event.getTypeDetail()) {
                case 0:
                    //SaigonTouris
                    showProgressBar();
                    promotionPresenter.getListPromotionOfEnterpriseSaigon(idEnterprise, startIndex, pageSize);
                    break;
                case 1:
                    //Vpoint
                    showProgressBar();
                    promotionPresenter.getListPromotionOfEnterpriseVpoint(idEnterprise);
                    break;
            }
        }
    }
}
