package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePhoneNumberRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.UserInfoActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.event.UserInfoEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeEmailPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePhoneNumberPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.CreateOtpPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.VerifyOtpPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeEmailView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePhoneNumberView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.VerifyOtpView;

/**
 * Created by MinhDN on 2/5/2018.
 */
public class SubmitOtpFragment extends BaseFragment implements VerifyOtpView, ChangeEmailView, ChangePhoneNumberView, GetUserInfoView {
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_tieu_de)
    TextView tvTieuDe;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.text1)
    TextView text1;

    @BindView(R.id.txtOtp)
    @NotEmpty(messageResId = R.string.str_check_otp)
    EditText txtOtp;

    @BindView(R.id.btn_send_otp)
    Button btnSendOtp;

    private String typeChange = "";
    private String changeEmail = "";
    private String changePhone = "";

    @Inject
    VerifyOtpPresenter verifyOtpPresenter;
    @Inject
    ChangePhoneNumberPresenter changePhoneNumberPresenter;
    @Inject
    ChangeEmailPresenter changeEmailPresenter;

    @Inject
    GetUserInfoPresenter getUserInfoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_otp, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        verifyOtpPresenter.setView(this);
        verifyOtpPresenter.onViewCreate();
        changeEmailPresenter.setView(this);
        changeEmailPresenter.onViewCreate();
        changePhoneNumberPresenter.setView(this);
        changePhoneNumberPresenter.onViewCreate();
        getUserInfoPresenter.setView(this);
        getUserInfoPresenter.onViewCreate();
    }

    private void initData() {
        Bundle args = getArguments();
        if (args != null) {
            typeChange = args.getString("CHANGE_TYPE");
            if(typeChange.equals("email")){
                changeEmail = args.getString("CHANGE_EMAIL");
                tvTieuDe.setText(getString(R.string.str_change_email));
            }else if(typeChange.equals("phone")){
                changePhone = args.getString("CHANGE_PHONE");
                tvTieuDe.setText(getString(R.string.str_change_phone));
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnBack, R.id.btn_send_otp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getFragmentManager().beginTransaction().remove(SubmitOtpFragment.this).commit();
                break;
            case R.id.btn_send_otp:
                validator.validate();
                if(isPassedValidate){
                    showProgressBar();
                    verifyOtpPresenter.verifyOtp(tinyDB.getString(getString(R.string.TOKEN_USER)), txtOtp.getText().toString().trim(), changePhone);
                }
                break;
        }
    }

    @Override
    public void onVerifyOtpSuccess(CommonApiResult response) {
        if(typeChange.equals("email")){
            ChangeEmailRequest changeEmailRequest = new ChangeEmailRequest();
            changeEmailRequest.setEmail(changeEmail);
            changeEmailRequest.setMaMayUuid(tinyDB.getString(getString(R.string.DEVICE_ID)));
            changeEmailRequest.setTokenhoivien(tinyDB.getString(getString(R.string.TOKEN_USER)));
            changeEmailPresenter.changeEmail(changeEmailRequest);
        }else if(typeChange.equals("phone")){
            ChangePhoneNumberRequest changePhoneNumberRequest = new ChangePhoneNumberRequest();
            changePhoneNumberRequest.setSoDienThoai(changePhone);
            changePhoneNumberRequest.setMaMayUuid(tinyDB.getString(getString(R.string.DEVICE_ID)));
            changePhoneNumberRequest.setTokenhoivien(tinyDB.getString(getString(R.string.TOKEN_USER)));
            changePhoneNumberPresenter.changePhoneNumber(changePhoneNumberRequest);
        }
    }

    @Override
    public void onVerifyOtpFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onVerifyOtpError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onChangeEmailSuccses(CommonApiResult response) {
        hideProgressBar();
        dilogChangeInfoSuccess("Đổi thông tin Email thành công!");

    }

    @Override
    public void onChangeEmailFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onChangeEmailError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onChangePhoneNumberSuccses(CommonApiResult response) {
        hideProgressBar();
        dilogChangeInfoSuccess("Đổi số điện thoại thành công!");
    }

    @Override
    public void onChangePhoneNumberFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onChangePhoneNumberError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onGetUserInfoSuccses(UserInfoResponse response) {
        hideProgressBar();
        UserInfoEvent userInfoEvent = new UserInfoEvent(response);
        EventBus.getDefault().postSticky(userInfoEvent);

//        AppDef.USER_PHONE = response.getData().getThongtin().get(0).getSoDienThoai();
//        AppDef.USER_NAME = response.getData().getThongtin().get(0).getTenDangNhap();
        tinyDB.putString(getString(R.string.USER_PHONE), response.getData().getThongtin().get(0).getSoDienThoai());
        tinyDB.putString(getString(R.string.USER_NAME), response.getData().getThongtin().get(0).getTenDangNhap());

        getFragmentManager().beginTransaction().remove(SubmitOtpFragment.this).commit();
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

    public void dilogChangeInfoSuccess(String message){
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
