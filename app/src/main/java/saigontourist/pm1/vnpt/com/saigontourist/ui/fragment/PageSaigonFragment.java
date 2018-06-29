package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersObject;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SpecialOffersVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.SpecialOffersAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialOffersView;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageSaigonFragment extends BaseFragment implements SpecialOffersView {
    @Inject
    SpecialOffersPresenter specialOffersPresenter;
    public static final String ARG_PAGE = "ARG_PAGE";
    @BindView(R.id.recycler_special)
    RecyclerView recyclerSpecial;
    Unbinder unbinder;

    private int mPage;
    SpecialOffersAdapter adapter;
    private List<SpecialOffersObject> datalist;

    //SaigonTouris
    private int startIndex = 0;
    private int pageSize = 10;
    private int provinceCode = 0;
    private int tradeMark = 0;
    private int fieldTouris = 0;


    private String strSearch = "";
    private String tokenDev;


    public static PageSaigonFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageSaigonFragment fragment = new PageSaigonFragment();
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_page_special_offers, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        addControls();
        return view;
    }

    private void initView() {
        specialOffersPresenter.setView(this);
        specialOffersPresenter.onViewCreate();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerSpecial.setLayoutManager(mLayoutManager);
        recyclerSpecial.setItemAnimator(new DefaultItemAnimator());
    }

    private void addControls() {
        datalist = new ArrayList<>();
        adapter = new SpecialOffersAdapter(getActivity(), datalist);
        adapter.setLoadMoreListener(new SpecialOffersAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerSpecial.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!org.apache.commons.lang3.StringUtils.isEmpty(tokenDev) &&
                                datalist.size() >= pageSize) {
                            startIndex = datalist.size();
                            loadMore();
                        }
                    }
                });
            }
        });

        recyclerSpecial.setAdapter(adapter);
        showProgressBar();
        getTokenDevIfNeed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void loadMore() {
        //add loading progress view
        getListSaigonTourist();

    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        super.onLoadTokenDevUser(tokenDev);
        this.tokenDev = tokenDev;
        hideProgressBar();
        getListSaigonTourist();
    }


    @Override
    public void onGetListSaigonSuccses(List<SpecialOffersSaigonResult.SpecialOffer> listSpecial) {
//        hideProgressBar();
        //remove loading view
        datalist.remove(datalist.size() - 1);
        if (listSpecial != null && listSpecial.size() > 0) {
            datalist.addAll(listSpecial);
        } else {
            adapter.setMoreDataAvailable(false);
        }
        adapter.notifyDataChanged();
    }

    @Override
    public void onGetListVpointSuccses(List<SpecialOffersVpointResult.DataOffersVpoint> dataDanhSachTinTucResponeList) {

    }

    @Override
    public void onGetListVpointFailed(String message) {

    }

    @Override
    public void onGetListSaigonFailed(String message) {
//        hideProgressBar();
        Timber.d(message);
    }

    @Override
    public void onGetListError(Throwable e) {
        hideProgressBar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageSpecialSaigonFramgent event) {
        /* Do something */
        if (event != null) {
            provinceCode = event.getProvinceCode();
            tradeMark = event.getTradeMark();
            fieldTouris = event.getFieldTouris();
            strSearch = event.getTextSearch();
            reStartValue();
            if (!org.apache.commons.lang3.StringUtils.isEmpty(tokenDev)) {
                getListSaigonTourist();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        specialOffersPresenter.onViewDestroy();
        EventBus.getDefault().unregister(this);
        hideProgressBar();
    }

    private void getListSaigonTourist() {
//        showProgressBar();
        datalist.add(new SpecialOffersObject(1));
        adapter.notifyItemInserted(datalist.size() - 1);
        specialOffersPresenter.getListSpecialSaigon(tokenDev, StringUtils.MaUngDung,
                provinceCode, tradeMark, fieldTouris, strSearch, startIndex, pageSize);
    }

    private void reStartValue() {
        startIndex = 0;
        if (datalist != null && datalist.size() > 0) {
            datalist.clear();
            adapter.setMoreDataAvailable(true);
            adapter.notifyDataChanged();
        }
    }
}
