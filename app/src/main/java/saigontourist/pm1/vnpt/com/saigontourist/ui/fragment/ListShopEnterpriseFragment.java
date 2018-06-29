package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataShopDetail;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataStoreCurrentOfEnterpriseVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.EnterpriseShopSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.ListShopDetailSpecialAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.ShopEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.ShopEnterpriseView;

public class ListShopEnterpriseFragment extends BaseFragment implements ShopEnterpriseView {
    private List<DataShopDetail> dataList = new ArrayList<>();
    private static final String TAG = ListShopEnterpriseFragment.class.getSimpleName();

    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_null)
    TextView loNull;
    @Inject
    ShopEnterprisePresenter shopPresenter;

    private ListShopDetailSpecialAdapter listShopEnterpriseAdapter;
    private int idEnterprise = 0;

    public ListShopEnterpriseFragment() {
    }

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
        View view = inflater.inflate(R.layout.fragment_shop_enterprise, container, false);
        ButterKnife.bind(this, view);
        shopPresenter.setView(this);
        shopPresenter.onViewCreate();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void showList() {
        if (dataList != null && dataList.size() > 0) {
            rcvDanhSach.setLayoutManager(new LinearLayoutManager(getActivity()));
            listShopEnterpriseAdapter = new ListShopDetailSpecialAdapter(getActivity(), getActivity(), dataList);
            rcvDanhSach.setAdapter(listShopEnterpriseAdapter);
        }
    }


    @Override
    public void onGetShopEnterpriseVpointSuccses(DataStoreCurrentOfEnterpriseVpointResult dataResult) {
        hideProgressBar();
        if (dataResult.getDataShopResponses() != null && dataResult.getDataShopResponses().size() > 0) {
            rcvDanhSach.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            dataList.addAll(dataResult.getDataShopResponses());
            showList();
        } else {
            rcvDanhSach.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetShopEnterpriseVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetShopEnterpriseSaigonSuccses(EnterpriseShopSaigonResult dataResult) {
        hideProgressBar();
        if (dataResult.getListData() != null && dataResult.getListData().size() > 0) {
            dataList.clear();
            rcvDanhSach.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            dataList.addAll(dataResult.getListData());
            showList();
        } else {
            rcvDanhSach.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetShopEnterpriseSaigonFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageIdDetailAtivity event) {
        /* Do something */
        if (event != null) {
            idEnterprise = event.getIdEnterprise();
            switch (event.getTypeDetail()) {
                case 0:
                    //SaigonTouris
                    showProgressBar();
                    shopPresenter.getListShopOfEnterpriseSaigon(idEnterprise);
                    break;
                case 1:
                    //Vpoint
                    showProgressBar();
                    shopPresenter.getListShopOfEnterpriseVpoint(idEnterprise);
                    break;
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        shopPresenter.onViewDestroy();
        EventBus.getDefault().unregister(this);
        hideProgressBar();
    }
}
