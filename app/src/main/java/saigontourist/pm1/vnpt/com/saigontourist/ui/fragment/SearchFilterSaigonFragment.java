package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.SearchFilterLocationActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.CategorySearchFilterAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategorySaigonPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategorySaigonView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;

public class SearchFilterSaigonFragment extends BaseFragment implements SpecialCategorySaigonView {


    @BindView(R.id.tv_nam_km)
    TextView tvNamKm;
    @BindView(R.id.tv_tam_km)
    TextView tvTamKm;
    @BindView(R.id.tv_muoi_km)
    TextView tvMuoiKm;
    @BindView(R.id.tv_hon_muoi_km)
    TextView tvHonMuoiKm;
    @BindView(R.id.recycler_field)
    RecyclerView recyclerField;
    @BindView(R.id.recycler_enterprise)
    RecyclerView recyclerEnterprise;
    Unbinder unbinder;

    @Inject
    SpecialCategorySaigonPresenter specialSaigonPresenter;

    private int TYPE_CATEGORY_FIELLD = 3;
    private int TYPE_CATEGORY_ENTERPRISE = 4;
    private int radius = 5;
    private String strRadius = "5km";

    private MessageEvent.ShopOnMapFilter shopOnMapFilter;

    private List<DataCategoryResult> dataFieldResults = new ArrayList<>();
    private List<DataCategoryResult> dataEnterpriseResults = new ArrayList<>();
    private CategorySearchFilterAdapter adapterFields;
    private CategorySearchFilterAdapter adapterEnterprise;
    private String tokenDev ="";

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_filter_saigon, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {
        recyclerField.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerEnterprise.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterFields = new CategorySearchFilterAdapter(getActivity(), dataFieldResults, TYPE_CATEGORY_FIELLD);
        adapterEnterprise = new CategorySearchFilterAdapter(getActivity(), dataEnterpriseResults, TYPE_CATEGORY_ENTERPRISE);
        recyclerField.setAdapter(adapterFields);
        recyclerEnterprise.setAdapter(adapterEnterprise);

        recyclerField.setNestedScrollingEnabled(false);
        recyclerEnterprise.setNestedScrollingEnabled(false);
        shopOnMapFilter = new MessageEvent.ShopOnMapFilter();
        specialSaigonPresenter.setView(this);
        specialSaigonPresenter.onViewCreate();
        getTokenDevIfNeed();


    }

    private void getListFields() {
        showProgressBar();
        specialSaigonPresenter.getListFieldSTouris(tokenDev);
    }

    private void getListEnterprise() {
        showProgressBar();
        specialSaigonPresenter.getListTradeMark(tokenDev);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onGetListProvinceCodeSuccses(List<DataCategoryResult> listFields) {

    }

    @Override
    public void onGetListTradeMarkSuccses(List<DataCategoryResult> listEnterprise) {
        hideProgressBar();
        binDataToEnterpriseAdapter(listEnterprise);

    }

    @Override
    public void onGetListFieldSTourisSuccses(List<DataCategoryResult> listCities) {
        hideProgressBar();
        binDataToFieldsAdapter(listCities);
        getListEnterprise();
    }

    @Override
    public void onGetListProvinceCodeFailed(String message) {

    }

    @Override
    public void onGetListTradeMarkFailed(String message) {
        hideProgressBar();
        showToast(message);

    }

    @Override
    public void onGetListFieldSTourisFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetListError(Throwable e) {
        hideProgressBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageCategorySpecial event) {
        if (event != null) {
            if (event.getTypeCategory() == TYPE_CATEGORY_ENTERPRISE) {
                shopOnMapFilter.setEnterpriseId(event.getId());
                shopOnMapFilter.setStrEnterpriseId(event.getNameValue());
            } else if (event.getTypeCategory() == TYPE_CATEGORY_FIELLD) {
                shopOnMapFilter.setField(event.getId());
                shopOnMapFilter.setStrField(event.getNameValue());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.StringSearchFilter event) {
        if (event != null) {
            //Saigon
            if (event.getTypeSearch() == 0) {
                shopOnMapFilter.setRadius(radius);
                shopOnMapFilter.setStrRadius(strRadius);
                shopOnMapFilter.setSearchingText(event.getSearchingText());
                shopOnMapFilter.setTypeSearch(0);
                shopOnMapFilter.setKindOfSearch(0);
                shopOnMapFilter.setChuongTrinhUuDaiId(0);
                EventBus.getDefault().postSticky(shopOnMapFilter);
                getActivity().startActivity(new Intent(getActivity(), SearchFilterLocationActivity.class));
            }
        }
    }

    @OnClick({R.id.tv_nam_km, R.id.tv_tam_km, R.id.tv_muoi_km, R.id.tv_hon_muoi_km})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.tv_nam_km:
                radius = 5;
                strRadius = "5km";
                tvNamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                tvNamKm.setBackgroundResource(R.drawable.background_button_blue);

                tvTamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvTamKm.setBackgroundResource(R.drawable.background_button);
                tvMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvMuoiKm.setBackgroundResource(R.drawable.background_button);
                tvHonMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvHonMuoiKm.setBackgroundResource(R.drawable.background_button);
                break;
            case R.id.tv_tam_km:
                radius = 8;
                strRadius = "8km";
                tvTamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                tvTamKm.setBackgroundResource(R.drawable.background_button_blue);

                tvNamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvNamKm.setBackgroundResource(R.drawable.background_button);
                tvMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvMuoiKm.setBackgroundResource(R.drawable.background_button);
                tvHonMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvHonMuoiKm.setBackgroundResource(R.drawable.background_button);
                break;

            case R.id.tv_muoi_km:
                radius = 10;
                strRadius = "10km";
                tvMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                tvMuoiKm.setBackgroundResource(R.drawable.background_button_blue);
                tvTamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvTamKm.setBackgroundResource(R.drawable.background_button);
                tvNamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvNamKm.setBackgroundResource(R.drawable.background_button);
                tvHonMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvHonMuoiKm.setBackgroundResource(R.drawable.background_button);
                break;

            case R.id.tv_hon_muoi_km:
                radius = 20;
                strRadius = "lớn hơn 10 km";
                tvHonMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                tvHonMuoiKm.setBackgroundResource(R.drawable.background_button_blue);

                tvTamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvTamKm.setBackgroundResource(R.drawable.background_button);
                tvMuoiKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvMuoiKm.setBackgroundResource(R.drawable.background_button);
                tvNamKm.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tvNamKm.setBackgroundResource(R.drawable.background_button);
                break;
        }
    }

    private void binDataToFieldsAdapter(List<DataCategoryResult> list) {
        if (list != null && list.size() > 0) {
            if (dataFieldResults != null && dataFieldResults.size() > 0) {
                dataFieldResults.clear();
            }
            addFirstItem(dataFieldResults);
            dataFieldResults.addAll(list);
            adapterFields.notifyDataSetChanged();
        }
    }

    private void binDataToEnterpriseAdapter(List<DataCategoryResult> list) {
        if (list != null && list.size() > 0) {
            if (dataEnterpriseResults != null && dataEnterpriseResults.size() > 0) {
                dataEnterpriseResults.clear();
            }
            addFirstItem(dataEnterpriseResults);
            dataEnterpriseResults.addAll(list);
            adapterEnterprise.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        super.onLoadTokenDevUser(tokenDev);
        this.tokenDev = tokenDev;
        getListFields();
    }
    private void addFirstItem(List<DataCategoryResult> listData) {
        listData.add(new DataCategoryResult(0, "Tất cả"));

    }
}
