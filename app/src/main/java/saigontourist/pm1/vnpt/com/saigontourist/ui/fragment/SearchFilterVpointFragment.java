package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.CategorySpecialAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategoryVpointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;

public class SearchFilterVpointFragment extends BaseFragment implements SpecialCategoryVpointView {


    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;


    @BindView(R.id.nam_km)
    TextView namKM;
    @BindView(R.id.tam_km)
    TextView tamKM;
    @BindView(R.id.muoi_km)
    TextView muoiKM;
    @BindView(R.id.hon_muoi_km)
    TextView honMuoiKM;

    @BindView(R.id.lo_linh_vuc)
    LinearLayout loLinhVuc;
    @BindView(R.id.lo_hien_linh_vuc)
    LinearLayout loHienLinhVuc;

    @BindView(R.id.btn_hien)
    Button btnHien;
    @BindView(R.id.btn_an)
    Button btnAn;

    @BindView(R.id.btn_amthuc)
    Button btnAmThuc;
//    @BindView(R.id.btn_amthuc_hien)
//    Button btnAmThucHien;

    @BindView(R.id.btn_muasam)
    Button btnMuaSam;
    @BindView(R.id.btn_muasam_hien)
    Button btnMuaSamHien;

    @BindView(R.id.btn_suckhoe)
    Button btnSucKhoe;
    @BindView(R.id.btn_suckhoe_hien)
    Button btnSucKhoeHien;

    @BindView(R.id.btn_giaoduc)
    Button btnGiaoDuc;
    @BindView(R.id.btn_dienmay)
    Button btnDienMay;
    @BindView(R.id.btn_vienthong)
    Button btnVienThong;
    @BindView(R.id.btn_vantai)
    Button btnVanTai;
    @BindView(R.id.btn_dulich)
    Button btnDuLich;
    @BindView(R.id.btn_dientu)
    Button btnDienTu;
    @BindView(R.id.btn_khac)
    Button btnKhac;
    @BindView(R.id.btn_tatca)
    Button btnTatca;
    @BindView(R.id.btn_tatca_hien)
    Button btn_tatca_hien;

    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.textView4)
    TextView textView4;
    Unbinder unbinder;

    @Inject
    SpecialCategoryVpointPresenter specialVpointPresenter;

    private List<DataCategoryResult> dataEnterpriseResults = new ArrayList<>();
    private CategorySearchFilterAdapter adapter;

    private int fieldId =0;
    private String strFieldId = "Tất cả";
    private int radius = 5;
    private String strRadius = "5km";
    private MessageEvent.ShopOnMapFilter shopOnMapFilter;

    private int TYPE_CATEGORY = 2;

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
        View view = inflater.inflate(R.layout.fragment_search_filter_vpoint, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        specialVpointPresenter.setView(this);
        specialVpointPresenter.onViewCreate();
        getEnterprise();
        return view;
    }

    private void getEnterprise() {
        showProgressBar();
        specialVpointPresenter.getListEnterprise();
    }

    private void initView() {
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaBook.TTF");
        namKM.setTypeface(face);
        tamKM.setTypeface(face);
        muoiKM.setTypeface(face);
        honMuoiKM.setTypeface(face);

        Typeface FACE = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaHeavy.TTF");
        text2.setTypeface(FACE);
        text3.setTypeface(FACE);
        textView4.setTypeface(FACE);
        shopOnMapFilter = new MessageEvent.ShopOnMapFilter();
        rcvDanhSach.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CategorySearchFilterAdapter(getActivity(), dataEnterpriseResults, TYPE_CATEGORY);
        rcvDanhSach.setAdapter(adapter);
        rcvDanhSach.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetListFieldsSuccses(List<DataCategoryResult> listFields) {

    }

    @Override
    public void onGetListEnterpriseSuccses(List<DataCategoryResult> listEnterprise) {
        hideProgressBar();
        binDataToAdapter(listEnterprise);
    }

    @Override
    public void onGetListCitiesSuccses(List<DataCategoryResult> listCities) {

    }

    @Override
    public void onGetListFieldsFailed(String message) {

    }

    @Override
    public void onGetListEnterpriseFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetListCitiesFailed(String message) {

    }

    @Override
    public void onGetListError(Throwable e) {
        hideProgressBar();
    }

    @OnClick({R.id.nam_km, R.id.tam_km, R.id.muoi_km, R.id.hon_muoi_km,
            R.id.btn_hien, R.id.btn_an,
            R.id.btn_amthuc, R.id.btn_tatca_hien, R.id.btn_muasam, R.id.btn_muasam_hien, R.id.btn_suckhoe, R.id.btn_suckhoe_hien,
            R.id.btn_dienmay, R.id.btn_vienthong, R.id.btn_vantai, R.id.btn_dulich, R.id.btn_giaoduc, R.id.btn_dientu, R.id.btn_khac,
            R.id.btn_tatca
    })
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.nam_km:
                radius = 5;
                strRadius = "5km";
                namKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                namKM.setBackgroundResource(R.drawable.background_button_blue);

                tamKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tamKM.setBackgroundResource(R.drawable.background_button);
                muoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                muoiKM.setBackgroundResource(R.drawable.background_button);
                honMuoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                honMuoiKM.setBackgroundResource(R.drawable.background_button);
                break;
            case R.id.tam_km:
                radius = 8;
                strRadius = "8km";
                tamKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                tamKM.setBackgroundResource(R.drawable.background_button_blue);

                namKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                namKM.setBackgroundResource(R.drawable.background_button);
                muoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                muoiKM.setBackgroundResource(R.drawable.background_button);
                honMuoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                honMuoiKM.setBackgroundResource(R.drawable.background_button);
                break;

            case R.id.muoi_km:
                radius = 10;
                strRadius = "10km";
                muoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                muoiKM.setBackgroundResource(R.drawable.background_button_blue);
                tamKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tamKM.setBackgroundResource(R.drawable.background_button);
                namKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                namKM.setBackgroundResource(R.drawable.background_button);
                honMuoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                honMuoiKM.setBackgroundResource(R.drawable.background_button);
                break;

            case R.id.hon_muoi_km:
                radius = 20;
                strRadius = "lớn hơn 10 km";
                honMuoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorWhite));
                honMuoiKM.setBackgroundResource(R.drawable.background_button_blue);

                tamKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                tamKM.setBackgroundResource(R.drawable.background_button);
                muoiKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                muoiKM.setBackgroundResource(R.drawable.background_button);
                namKM.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_main_yes));
                namKM.setBackgroundResource(R.drawable.background_button);
                break;

            case R.id.btn_hien:
                loLinhVuc.setVisibility(View.GONE);
                loHienLinhVuc.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_an:
                loLinhVuc.setVisibility(View.VISIBLE);
                loHienLinhVuc.setVisibility(View.GONE);
                break;

            case R.id.btn_amthuc:
                fieldId = 1;
                strFieldId = "Ẩm thực";
                btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_active);
                changeBackgroundAmThuc();
                break;

            case R.id.btn_tatca_hien:
                fieldId = 0;
                strFieldId = "Tất cả";
                btnTatca.setBackgroundResource(R.mipmap.ic_tatca_active);
                btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_active);
                changeBackgroundTatca();
                break;

            case R.id.btn_tatca:
                fieldId = 0;
                strFieldId = "Tất cả";
                btnTatca.setBackgroundResource(R.mipmap.ic_tatca_active);
                btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_active);
                changeBackgroundTatca();
                break;

            case R.id.btn_muasam:
                fieldId = 2;
                strFieldId = "Mua sắm";
                btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_active);
                btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_active);
                changeBackgroundMuaSam();
                break;
            case R.id.btn_muasam_hien:
                fieldId = 2;
                strFieldId = "Mua sắm";
                btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_active);
                btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_active);
                changeBackgroundMuaSam();
                break;

            case R.id.btn_suckhoe:
                fieldId = 3;
                strFieldId = "Y tế - Giáo dục";
                btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_active);
                btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_active);
                changeBackgroundSucKhoe();
                break;
            case R.id.btn_suckhoe_hien:
                fieldId = 3;
                strFieldId = "Y tế - Giáo dục";
                btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_active);
                btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_active);
                changeBackgroundSucKhoe();
                break;

            case R.id.btn_giaoduc:
                fieldId = 4;
                strFieldId = "Thời trang";
                btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_active);
                changeBackgroundGiaoDuc();
                break;

            case R.id.btn_dienmay:
                fieldId = 5;
                strFieldId = "Tài chính";
                btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_active);
                changeBackgroundDienMay();
                break;

            case R.id.btn_vienthong:
                fieldId = 6;
                strFieldId = "Viễn thông";
                btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_active);
                changeBackgroundVienThong();
                break;

            case R.id.btn_vantai:
                fieldId = 7;
                strFieldId = "Vận tải";
                btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_active);
                changeBackgroundVanTai();
                break;

            case R.id.btn_dulich:
                fieldId = 8;
                strFieldId = "Du lịch - Khách sạn";
                btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_active);
                changeBackgroundDuLich();
                break;

            case R.id.btn_dientu:
                fieldId = 9;
                strFieldId = "Thương mại điện tử";
                btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_active);
                changeBackgroundDienTu();
                break;

            case R.id.btn_khac:
                fieldId = 10;
                strFieldId = "Khác";
                btnKhac.setBackgroundResource(R.mipmap.ic_khac_active);
                changeBackgroundKhac();
                break;
        }
    }

    private void changeBackgroundAmThuc() {
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundMuaSam() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundSucKhoe() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundGiaoDuc() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundDienMay() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundVienThong() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundVanTai() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundDuLich() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundDienTu() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundKhac() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btn_tatca_hien.setBackgroundResource(R.mipmap.ic_tatca_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnTatca.setBackgroundResource(R.mipmap.ic_tatca_normal);
    }

    private void changeBackgroundTatca() {
        btnAmThuc.setBackgroundResource(R.mipmap.ic_am_thuc_normal);
        btnMuaSam.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnMuaSamHien.setBackgroundResource(R.mipmap.ic_mua_sam_normal);
        btnSucKhoe.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnSucKhoeHien.setBackgroundResource(R.mipmap.ic_suc_khoe_normal);
        btnGiaoDuc.setBackgroundResource(R.mipmap.ic_thoitrang_normal);
        btnVienThong.setBackgroundResource(R.mipmap.ic_vien_thong_normal);
        btnDienMay.setBackgroundResource(R.mipmap.ic_taichinh_normal);
        btnVanTai.setBackgroundResource(R.mipmap.ic_van_tai_normal);
        btnDuLich.setBackgroundResource(R.mipmap.ic_du_lich_normal);
        btnDienTu.setBackgroundResource(R.mipmap.ic_thuongmai_dientu_normal);
        btnKhac.setBackgroundResource(R.mipmap.ic_khac_normal);
    }

    private void binDataToAdapter(List<DataCategoryResult> list) {
        if (list != null && list.size() > 0) {
            if (dataEnterpriseResults != null && dataEnterpriseResults.size() > 0) {
                dataEnterpriseResults.clear();
            }
            dataEnterpriseResults.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageCategorySpecial event) {
        if (event != null) {
            if (event.getTypeCategory() == TYPE_CATEGORY) {
                shopOnMapFilter.setEnterpriseId(event.getId());
                shopOnMapFilter.setStrEnterpriseId(event.getNameValue());
            }
        }
    }

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.StringSearchFilter event) {
        if (event != null) {
            //Vpoint
            if (event.getTypeSearch() == 1) {
                shopOnMapFilter.setField(fieldId);
                shopOnMapFilter.setStrField(strFieldId);
                shopOnMapFilter.setRadius(radius);
                shopOnMapFilter.setStrRadius(strRadius);
                shopOnMapFilter.setSearchingText(event.getSearchingText());
                shopOnMapFilter.setTypeSearch(1);
                shopOnMapFilter.setKindOfSearch(0);
                EventBus.getDefault().postSticky(shopOnMapFilter);
                getActivity().startActivity(new Intent(getActivity(), SearchFilterLocationActivity.class));
            }
        }
    }

}
