package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ForgotPasswordRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ForgotPasswordPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetCapchaPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ForgotPasswordView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetCapChaView;

public class ForgotPasswordActivity extends BaseActivity implements GetCapChaView, ForgotPasswordView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.font_gioithieu)
    TextView fontGioithieu;

    @BindView(R.id.txtUser)
    @NotEmpty(trim = true, messageResId = R.string.str_bat_buoc_khong_duoc_de_trong)
    EditText txtUser;

    @BindView(R.id.imv_refresh_capcha)
    ImageView imvRefreshCapcha;

    @BindView(R.id.txtCapcha)
    @NotEmpty(trim = true, messageResId = R.string.str_captcha_error)
    EditText txtCapcha;

    @BindView(R.id.btnYes)
    Button btnYes;
    @BindView(R.id.img_capcha)
    ImageView imgCapcha;
    @Inject
    GetCapchaPresenter getCapchaPresenter;
    @Inject
    ForgotPasswordPresenter forgotPasswordPresenter;

    private RequestBuilder<PictureDrawable> requestBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        initControls();
        initView();
    }

    private void initView() {
        getTokenDevIfNeed();
        getCapchaPresenter.setView(this);
        getCapchaPresenter.onViewCreate();
        forgotPasswordPresenter.setView(this);
        forgotPasswordPresenter.onViewCreate();
        showProgressBar();
        getCapchaPresenter.getCapcha();
    }

    private void initControls() {
        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        fontGioithieu.setTypeface(face);
        text.setTypeface(face);
        txtUser.setTypeface(face);
        txtCapcha.setTypeface(face);

    }

    @OnClick({R.id.btnBack, R.id.btnYes, R.id.imv_refresh_capcha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnYes:
                validator.validate();
                if(isPassedValidate){
                    showProgressBar();
                    ForgotPasswordRequest request = new ForgotPasswordRequest(tinyDB.getString(getString(R.string.TOKEN_DEV)), txtUser.getText().toString().trim(), txtCapcha.getText().toString().trim());
                    forgotPasswordPresenter.forgotPassword(request);
                }
                break;
            case R.id.imv_refresh_capcha:
                showProgressBar();
                getCapchaPresenter.getCapcha();
                break;
        }
    }

    @Override
    public void onLoadCapChaSuccess(Bitmap bitmap) {
        hideProgressBar();
        imgCapcha.setImageBitmap(bitmap);

    }

    @Override
    public void onLoadCapChaError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onForgotPasswordSuccses(CommonApiResult response) {
        hideProgressBar();
        successForgotPass(response.errorDesc);
    }

    @Override
    public void onForgotPasswordFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onForgotPasswordError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    public void successForgotPass(String message){
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
                ForgotPasswordActivity.this.finish();
            }
        });

    }
}
