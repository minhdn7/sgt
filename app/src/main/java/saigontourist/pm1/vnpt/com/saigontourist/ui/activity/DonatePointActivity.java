package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.CheckPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.ChucNangTangDiemPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DieuKienTangDiemPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DonatePointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.ChucNangTangDiemView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DieuKienTangDiemView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DonatePointView;

public class DonatePointActivity extends BaseActivity implements DieuKienTangDiemView, ChucNangTangDiemView, DonatePointView{

    @BindView(R.id.btn_back_chua)
    ImageView btnBackChua;
    @BindView(R.id.font_gioithieu)
    TextView fontGioithieu;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.lo_null)
    FrameLayout loNull;
    @BindView(R.id.lo_chua_dang_nhap)
    LinearLayout loChuaDangNhap;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.txtPoint)
    @NotEmpty(trim = true, messageResId = R.string.str_check_diem)
    EditText txtPoint;


    @BindView(R.id.txt_sdt_nguoi_nhan)
    @NotEmpty(trim = true, messageResId = R.string.str_thieu_so_dien_thoai_email)
    EditText txtSdtNguoiNhan;

    @BindView(R.id.tv_loai_point)
    TextView tvLoaiPoint;
    @BindView(R.id.imageViewShop)
    ImageView imageViewShop;
    @BindView(R.id.lo_chon_point)
    LinearLayout loChonPoint;
    @BindView(R.id.btn_donate_point)
    Button btnDonatePoint;
    @BindView(R.id.lo_da_dang_nhap)
    LinearLayout loDaDangNhap;

    private String maHoiVien = "";
    private String sThucHienTangDiem = "1";
    private DonatePointRequest donatePointRequest;

    @Inject
    DieuKienTangDiemPresenter dieuKienTangDiemPresenter;

    @Inject
    ChucNangTangDiemPresenter chucNangTangDiemPresenter;

    @Inject
    DonatePointPresenter donatePointPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_point);
        ButterKnife.bind(this);
        initView();
        checkLogin();
        initControls();
    }

    private void initControls() {
        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        fontGioithieu.setTypeface(face);
        text.setTypeface(face);
        textView6.setTypeface(face);
        txtPoint.setTypeface(face);
        txtSdtNguoiNhan.setTypeface(face);
        tvLoaiPoint.setTypeface(face);
    }

    private void initView() {
        dieuKienTangDiemPresenter.setView(this);
        dieuKienTangDiemPresenter.onViewCreate();
        chucNangTangDiemPresenter.setView(this);
        chucNangTangDiemPresenter.onViewCreate();
        donatePointPresenter.setView(this);
        donatePointPresenter.onViewCreate();

    }

    @OnClick({R.id.btn_back_chua, R.id.btnBack, R.id.btn_donate_point, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back_chua:
                onBackPressed();
                this.finish();
                break;
            case R.id.btnBack:
                onBackPressed();
                this.finish();
                break;
            case R.id.btn_donate_point:
                validator.validate();
                if(isPassedValidate){
                    showProgressBar();
                    donatePointRequest = new DonatePointRequest();
                    donatePointRequest.setMaMayUuid(tinyDB.getString(getString(R.string.DEVICE_ID)));
                    donatePointRequest.setMaXacThuc("");
                    donatePointRequest.setSoDiemTang(Integer.parseInt(txtPoint.getText().toString().trim()));
                    donatePointRequest.setSoDienThoaiEmail(txtSdtNguoiNhan.getText().toString().trim());
                    donatePointRequest.setToken(tinyDB.getString(getString(R.string.TOKEN_USER)));
                    donatePointRequest.setThucHienTangDiem(sThucHienTangDiem);
                    donatePointPresenter.sendPoint(donatePointRequest);

                }
                break;
            case  R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    private void checkLogin() {
        if(tinyDB.getBoolean(getString(R.string.IS_LOGIN))){
            loChuaDangNhap.setVisibility(View.GONE);
            loDaDangNhap.setVisibility(View.VISIBLE);
            showProgressBar();
            chucNangTangDiemPresenter.checkTangDiem(tinyDB.getString(getString(R.string.TOKEN_USER)));
        }else {
            loChuaDangNhap.setVisibility(View.VISIBLE);
            loDaDangNhap.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChucNangTangDiemSuccses(CommonApiResult response) {
        dieuKienTangDiemPresenter.checkTangDiem(tinyDB.getString(getString(R.string.TOKEN_USER)));
    }

    @Override
    public void onChucNangTangDiemFailed(String message) {
        hideProgressBar();
        dialogDonatePointError(message);
    }

    @Override
    public void onChucNangTangDiemError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onDieuKienTangDiemSuccses(CheckPointResponse response) {
        hideProgressBar();
    }

    @Override
    public void onDieuKienTangDiemFailed(String message) {
        hideProgressBar();
        dialogDonatePointError(message);
    }

    @Override
    public void onDieuKienTangDiemError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onDonatePointSuccess(CommonApiResult response) {
        hideProgressBar();

    }

    @Override
    public void onSendOTPSuccess(CommonApiResult response) {
        hideProgressBar();
        Intent intent = new Intent(this, OTPDonatePointActivity.class);
        intent.putExtra("DONATE_REQUEST", donatePointRequest);
        startActivity(intent);
    }

    @Override
    public void onDonatePointFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onDonatePointError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    public void dialogDonatePointError(String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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
                DonatePointActivity.this.finish();
            }
        });
    }
}
