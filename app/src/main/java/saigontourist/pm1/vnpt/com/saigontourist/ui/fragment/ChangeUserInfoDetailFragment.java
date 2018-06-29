package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.local.LocalResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeUserInfoRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.UserInfoActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.event.UserInfoEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.DistrictPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.ProvincePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.VillagePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetDistrictView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetProvinceView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.local.GetVillageView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeUserInfoView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangeUserInfoDetailFragment extends BaseFragment implements ChangeUserInfoView, GetUserInfoView,
        GetProvinceView, GetDistrictView, GetVillageView,
        Validator.ValidationListener,
        DatePickerDialog.OnDateSetListener {
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.text1)
    TextView text1;

//    @NotEmpty(messageResId = R.string.str_check_null)
//    @Length(min = 1, max = 100, messageResId = R.string.str_min_max_1_100)
    @BindView(R.id.txtUser)
    TextView txtUser;

    @Length(min = 1, max = 100, messageResId = R.string.str_min_max_1_100)
    @NotEmpty(messageResId = R.string.str_check_null)
    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.txtBirthday)
    TextView txtBirthday;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.rbNam)
    RadioButton rbNam;
    @BindView(R.id.rbNu)
    RadioButton rbNu;
    @BindView(R.id.rbKhac)
    RadioButton rbKhac;
    @BindView(R.id.rbGroupGioiTinh)
    RadioGroup rbGroupGioiTinh;
    @BindView(R.id.tv_tinh)
    TextView tvTinh;
    @BindView(R.id.lo_tinh_thanhpho)
    LinearLayout loTinhThanhpho;
    @BindView(R.id.tv_quan_huyen)
    TextView tvQuanHuyen;
    @BindView(R.id.lo_quan_huyen)
    LinearLayout loQuanHuyen;
    @BindView(R.id.tv_phuong_xa)
    TextView tvPhuongXa;
    @BindView(R.id.lo_phuong_xa)
    LinearLayout loPhuongXa;

    @Length(max = 200, messageResId = R.string.str_diachi)
    @BindView(R.id.item_dia_chi)
    EditText itemDiaChi;

    @Length(max = 100, messageResId = R.string.str_toi_da_100)
    @BindView(R.id.txt_so_CMT)
    EditText txtSoCMT;

    @BindView(R.id.txt_ngay_cap)
    TextView txtNgayCap;

    @Length(max = 100, messageResId = R.string.str_toi_da_40)
    @BindView(R.id.txt_noi_cap)
    EditText txtNoiCap;

    @Length(max = 100, messageResId = R.string.str_toi_da_20)
    @BindView(R.id.txt_nghe_nghiep)
    EditText txtNgheNghiep;

    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.sp_Province)
    Spinner spProvince;
    @BindView(R.id.sp_District)
    Spinner spDistrict;
    @BindView(R.id.sp_Village)
    Spinner spVillage;

    private int numberDate;
    private int mYear, mMonth, mDay;
    SimpleDateFormat simpleDateFormat;

    private UserInfoEvent userInfoEvent;
    private ChangeUserInfoRequest changeUserInfoRequest;
    @Inject
    ChangeUserInfoPresenter changeUserInfoPresenter;
    @Inject
    GetUserInfoPresenter getUserInfoPresenter;

    @Inject
    ProvincePresenter provincePresenter;

    @Inject
    DistrictPresenter districtPresenter;

    @Inject
    VillagePresenter villagePresenter;

    private Integer iSex = 0;
    private List<LocalResponse.Data> listProvince, listDistrict, listVillage;
    private ArrayAdapter<LocalResponse.Data> adapterProvince, adapterDistrict, adapterVillage;
    private Integer idProvince = 0;
    private Integer idDistrict = 0;
    private Integer idVillage = 0;
    private String nameProvince = "";
    private String nameDistrict= "";
    private String nameVillage = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_user_info_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initControls();
        initData();
        return view;
    }

    private void initData() {
        try {
            userInfoEvent = EventBus.getDefault().getStickyEvent(UserInfoEvent.class);
            if (userInfoEvent != null) {
                txtUser.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTenDangNhap());
                txtName.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTenHoiVien());
                txtBirthday.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getNgaySinh());
                itemDiaChi.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getDiaChi());
                txtSoCMT.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getSoDinhDanh());
                txtNgheNghiep.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getNgheNghiep());
                txtNgayCap.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getNgayCapGiayToDinhDanh());
                txtNoiCap.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getNoiCapGiayToDinhDanh());

                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh() != null){
                    nameProvince = userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh();
                }
                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getQuan() != null){
                    nameDistrict = userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getQuan();
                }
                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getPhuong() != null){
                    nameVillage = userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getPhuong();
                }
                iSex = userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getGioiTinh();

                if (iSex == 1) {
                    rbNu.setChecked(true);
                } else if (iSex == 0) {
                    rbNam.setChecked(true);
                } else {
                    rbKhac.setChecked(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControls() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar cStart = Calendar.getInstance();
        mYear = cStart.get(Calendar.YEAR);
        mMonth = cStart.get(Calendar.MONTH);
        mDay = cStart.get(Calendar.DAY_OF_MONTH);
        LocalResponse.Data startData = new LocalResponse.Data();
        startData.setId(0);
        startData.setName("Không chọn");
        listProvince = new ArrayList<>();
        listDistrict = new ArrayList<>();
        listVillage = new ArrayList<>();
        listProvince.add(0, startData);
        listDistrict.add(0, startData);
        listVillage.add(0, startData);

        adapterProvince = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listProvince);
        adapterProvince.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spProvince.setAdapter(adapterProvince);

        adapterDistrict = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listDistrict);
        adapterDistrict.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spDistrict.setAdapter(adapterDistrict);

        adapterVillage = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listVillage);
        adapterVillage.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spVillage.setAdapter(adapterVillage);
    }

    private void initView() {
        changeUserInfoPresenter.setView(this);
        changeUserInfoPresenter.onViewCreate();
        getUserInfoPresenter.setView(this);
        getUserInfoPresenter.onViewCreate();

        provincePresenter.setView(this);
        provincePresenter.onViewCreate();
        districtPresenter.setView(this);
        districtPresenter.onViewCreate();
        villagePresenter.setView(this);
        villagePresenter.onViewCreate();

        getTokenDevIfNeed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnBack, R.id.rbNam, R.id.rbNu, R.id.rbKhac, R.id.lo_tinh_thanhpho, R.id.lo_quan_huyen, R.id.lo_phuong_xa,
            R.id.btnUpdate, R.id.txt_ngay_cap, R.id.txtBirthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getFragmentManager().beginTransaction().remove(ChangeUserInfoDetailFragment.this).commit();
                break;
            case R.id.rbNam:
                iSex = 0;
                break;
            case R.id.rbNu:
                iSex = 1;
                break;
            case R.id.rbKhac:
                iSex = 2;
                break;
            case R.id.lo_tinh_thanhpho:
                break;
            case R.id.lo_quan_huyen:
                break;
            case R.id.lo_phuong_xa:
                break;
            case R.id.btnUpdate:
                validator.validate();
                if (isPassedValidate && tinyDB.getBoolean(getString(R.string.IS_LOGIN))) {
                    showProgressBar();
                    changeUserInfoRequest = new ChangeUserInfoRequest();
//                    changeUserInfoRequest.setMaMayUuid(AppDef.DEVICE_ID);
//                    changeUserInfoRequest.setTokenhoivien(AppDef.TOKEN_USER);
                    changeUserInfoRequest.setMaMayUuid(tinyDB.getString(getString(R.string.DEVICE_ID)));
                    changeUserInfoRequest.setTokenhoivien(tinyDB.getString(getString(R.string.TOKEN_USER)));
                    changeUserInfoRequest.setTendangnhap(txtUser.getText().toString().trim());
                    changeUserInfoRequest.setHoten(txtName.getText().toString().trim());
                    changeUserInfoRequest.setNgaysinh(txtBirthday.getText().toString().trim());
                    changeUserInfoRequest.setSex(iSex);
                    changeUserInfoRequest.setTinh(nameProvince);
                    changeUserInfoRequest.setQuanhuyen(nameDistrict);
                    changeUserInfoRequest.setPhuongxa(nameVillage);

                    changeUserInfoRequest.setDiachi(itemDiaChi.getText().toString().trim());
                    changeUserInfoRequest.setSodinhdanh(txtSoCMT.getText().toString().trim());
                    changeUserInfoRequest.setNgaycapgiaytodinhdanh(txtNgayCap.getText().toString().trim());
                    changeUserInfoRequest.setNoicapgiaytodinhdanh(txtNoiCap.getText().toString().trim());
                    changeUserInfoRequest.setNghenghiep(txtNgheNghiep.getText().toString().trim());

                    changeUserInfoPresenter.changeUserInfo(changeUserInfoRequest);
                }
                break;
            case R.id.txtBirthday:
                numberDate = 1;
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;
            case R.id.txt_ngay_cap:
                numberDate = 2;
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;
        }
    }

    @Override
    public void onChangeUserDetailSuccses(CommonApiResult response) {
        hideProgressBar();
        dialogChangeUserInfoSuccess(response.getErrorDesc());

    }

    @Override
    public void onChangeUserDetailFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onChangeUserDetailError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(getActivity())
                .callback(this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        if (numberDate == 1) {
            txtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
        } else if (numberDate == 2) {
            txtNgayCap.setText(simpleDateFormat.format(calendar.getTime()));
        }
    }

    @Override
    public void onGetUserInfoSuccses(UserInfoResponse response) {
        hideProgressBar();
        UserInfoEvent userInfoEvent = new UserInfoEvent(response);
        EventBus.getDefault().postSticky(userInfoEvent);
//        getFragmentManager().beginTransaction().remove(ChangeUserInfoDetailFragment.this).commit();
        Intent refresh = new Intent(getActivity(), UserInfoActivity.class);
        getActivity().finish();
        startActivity(refresh);
    }

    @Override
    public void onGetUserInfoFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onGetUserInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }


    @Override
    public void onGetProvinceSuccses(LocalResponse response) {
        hideProgressBar();
        if(listProvince.size() > 1){
            listProvince.subList(1, listProvince.size()).clear();
        }
        if(response.data.size() > 0){

            listProvince.addAll(response.getData());
            adapterProvince.notifyDataSetChanged();
            spProvince.setAdapter(adapterProvince);

            for(int i = 0; i < listProvince.size(); i++){
                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh() != null){
                    if(listProvince.get(i).getName().equals(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh())){
                        spProvince.setSelection(i);
                    }
                }
            }
            spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i > 0){
                        showProgressBar();
                        idProvince = listProvince.get(i).getId();
                        nameProvince = listProvince.get(i).getName();
                        districtPresenter.getDistrict(listProvince.get(i).getId());
                        spVillage.setSelection(0);
                        if(listVillage.size() > 1){
                            listVillage.subList(1, listVillage.size()).clear();
                        }
                    }else {
                        spDistrict.setSelection(0);
                        spVillage.setSelection(0);
                        if(listVillage.size() > 1){
                            listVillage.subList(1, listVillage.size()).clear();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    @Override
    public void onGetProvinceFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetProvinceError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onGetDistrictSuccses(LocalResponse response) {
        hideProgressBar();
        if(listDistrict.size() > 1){
            listDistrict.subList(1, listDistrict.size()).clear();
        }
        if(response.data.size() > 0) {
            listDistrict.addAll(response.getData());
            adapterDistrict.notifyDataSetChanged();
            spDistrict.setAdapter(adapterDistrict);

            for(int i = 0; i < listDistrict.size(); i++){
                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh() != null){
                    if(listDistrict.get(i).getName().equals(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getQuan())){
                        spDistrict.setSelection(i);
                    }
                }
            }
            spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i > 0){
                        showProgressBar();
                        idDistrict = listDistrict.get(i).getId();
                        nameDistrict = listDistrict.get(i).getName();
                        villagePresenter.getVillage(listDistrict.get(i).getId());
                    }else {
                        spVillage.setSelection(0);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    @Override
    public void onGetDistrictFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetDistrictError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onGetVillageSuccses(LocalResponse response) {
        hideProgressBar();
        if(listVillage.size() > 1){
            listVillage.subList(1, listVillage.size()).clear();
        }
        if(response.data.size() > 0) {

            listVillage.addAll(response.getData());
            adapterVillage.notifyDataSetChanged();
            spVillage.setAdapter(adapterVillage);
            for(int i = 0; i < listVillage.size(); i++){
                if(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTinh() != null){
                    if(listVillage.get(i).getName().equals(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getPhuong())){
                        spVillage.setSelection(i);
                    }
                }
            }
            spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i > 0){
                        idVillage = listVillage.get(i).getId();
                        nameVillage = listVillage.get(i).getName();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    @Override
    public void onGetVillageFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetVillageError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
//        AppDef.TOKEN_DEV = tokenDev;
        tinyDB.putString(getString(R.string.TOKEN_DEV), tokenDev);
        provincePresenter.getProvince(tokenDev);
    }

    public void dialogChangeUserInfoSuccess(String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        final android.view.View dialogView = inflater.inflate(R.layout.custom_dialog_show_toast, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        TextView text_message = (TextView) dialogView.findViewById(R.id.text_message);
        Button btnYes = (Button) dialogView.findViewById(R.id.btnYes);

        text_message.setText(message);

        btnYes.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                b.dismiss();
                showProgressBar();
                getUserInfoPresenter.getUserInfo(tinyDB.getString(getString(R.string.TOKEN_USER)), tinyDB.getString(getString(R.string.DEVICE_ID)));
            }
        });
    }

}