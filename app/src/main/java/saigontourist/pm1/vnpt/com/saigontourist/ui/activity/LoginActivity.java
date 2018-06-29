package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.login.LoginResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.AppState;
import saigontourist.pm1.vnpt.com.saigontourist.ui.event.UserInfoEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SendTokenFirebasePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.LoginUserView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.SendTokenFirebaseView;

public class LoginActivity extends BaseActivity implements LoginUserView, GetUserInfoView, SendTokenFirebaseView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.font_text1)
    TextView fontText1;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;

    @NotEmpty(message = "Tên đăng nhập không được để trống!")
    @BindView(R.id.txtUser)
    EditText txtUser;

    @NotEmpty(message = "Mật khẩu không được để trống!")
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.tvKhongTheDangNhap)
    TextView tvKhongTheDangNhap;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.font_text)
    TextView fontText;
    @BindView(R.id.tvDangKy)
    TextView tvDangKy;
    @Inject
    LoginUserPresenter loginPresenter;
    @Inject
    GetUserInfoPresenter getUserInfoPresenter;
    @Inject
    SendTokenFirebasePresenter sendTokenFirebasePresenter;
    @Inject
    AppState appState;
    private String registerEvent = "";
    private String tokenFirebase = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        registerEvent = getIntent().getStringExtra("RegisterEvent");
    }

    @OnClick({R.id.btnBack, R.id.btnLogin, R.id.tvDangKy, R.id.tvKhongTheDangNhap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                this.finish();
                break;
            case R.id.btnLogin:
                validator.validate();
                if (isPassedValidate) {
                    showProgressBar();
                    LoginRequest request = new LoginRequest(txtUser.getText().toString().trim(),
                            txtPassword.getText().toString().trim(),
                            tinyDB.getString(getString(R.string.DEVICE_ID)));
                    loginPresenter.loginUser(request);
                }
                break;
            case R.id.tvDangKy:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tvKhongTheDangNhap:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }
    }

    @Override
    public void onLoginUserSuccses(LoginResponse response) {
        hideProgressBar();
//        AppDef.TOKEN_USER = response.getToken();
//        AppDef.IS_LOGIN = true;

        tinyDB.putString(getString(R.string.TOKEN_USER), response.getToken());
        tinyDB.putBoolean(getString(R.string.IS_LOGIN), true);
        appState.setState(AppState.PREF_KEY_STATUS_LOGIN_USER,true);
        appState.setState(AppState.PREF_KEY_TOKEN_LOGIN_USER,response.getToken());
        hideProgressBar();
//        getUserInfoPresenter.getUserInfo(AppDef.TOKEN_USER, AppDef.DEVICE_ID);

        getUserInfoPresenter.getUserInfo(tinyDB.getString(getString(R.string.TOKEN_USER)), tinyDB.getString(getString(R.string.DEVICE_ID)));
//        onBackPressed();
    }

    @Override
    public void onLoginUserFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onLoginUserError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    private void initView() {
        loginPresenter.setView(this);
        loginPresenter.onViewCreate();
        getUserInfoPresenter.setView(this);
        getUserInfoPresenter.onViewCreate();
        sendTokenFirebasePresenter.setView(this);
        sendTokenFirebasePresenter.onViewCreate();
    }

    @Override
    public void onGetUserInfoSuccses(UserInfoResponse response) {
        hideProgressBar();
        EventBus.getDefault().removeAllStickyEvents();
        UserInfoEvent userInfoEvent = new UserInfoEvent(response);
        EventBus.getDefault().postSticky(userInfoEvent);
//        AppDef.USER_PHONE = response.getData().getThongtin().get(0).getSoDienThoai();
//        AppDef.USER_NAME = response.getData().getThongtin().get(0).getTenDangNhap();

        tokenFirebase = tinyDB.getString(getString(R.string.TOKEN_FIREBASE));
        sendTokenFirebasePresenter.sendTokenFirebase(response.getData().getThongtin().get(0).getSoDienThoai(),
                                                    getString(R.string.OS),
                                                    tokenFirebase,
                                                    tinyDB.getString(getString(R.string.DEVICE_ID)),
                                                    tinyDB.getString(getString(R.string.TOKEN_USER)));

        tinyDB.putString(getString(R.string.USER_PHONE), response.getData().getThongtin().get(0).getSoDienThoai());
        tinyDB.putString(getString(R.string.USER_NAME), response.getData().getThongtin().get(0).getTenDangNhap());
        if(registerEvent != null && registerEvent.equals("RegisterEvent")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("RegisterEvent", registerEvent);
            startActivity(intent);
            this.finish();
        }else {
            onBackPressed();
        }

    }

    @Override
    public void onGetUserInfoFailed(String message) {
        hideProgressBar();
//        dilogThongBao(message);
    }

    @Override
    public void onGetUserInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onSendTokenFirebaseSuccess(CommonApiResult response) {
//        showLongToast(response.errorDesc);
    }

    @Override
    public void onSendTokenFirebaseFailed(String message) {

//        showLongToast(message);
    }

    @Override
    public void onSendTokenFirebaseError(Throwable e) {

        showToast(e.toString());
    }
}
