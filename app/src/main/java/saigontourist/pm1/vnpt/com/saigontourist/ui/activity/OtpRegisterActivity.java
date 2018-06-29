package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.SubmitRegisterPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.SubmitRegisterView;

public class OtpRegisterActivity extends BaseActivity implements SubmitRegisterView {

    @BindView(R.id.txtOtp)
    @NotEmpty(messageResId = R.string.str_check_otp)
    EditText txtOtp;
    String registerToken = "";

    @Inject
    SubmitRegisterPresenter submitRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_register);
        ButterKnife.bind(this);
        registerToken = getIntent().getStringExtra("TOKEN_REGISTER");
        initView();
    }

    private void initView() {
        submitRegisterPresenter.setView(this);
        submitRegisterPresenter.onViewCreate();
    }


    @Override
    public void onSubmitRegisterSuccses(CommonApiResult response) {
        hideProgressBar();
        dilogRegisterSuccess(response.getErrorDesc());
    }

    @Override
    public void onSubmitRegisterFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onSubmitRegisterError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    public void dilogRegisterSuccess(String noiDung) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_show_toast, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        TextView text_message = (TextView) dialogView.findViewById(R.id.text_message);
        Button btnYes = (Button) dialogView.findViewById(R.id.btnYes);

        text_message.setText(noiDung);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
                Intent intent = new Intent(OtpRegisterActivity.this, LoginActivity.class);
                intent.putExtra("RegisterEvent", "RegisterEvent");
                finish();
                startActivity(intent);
            }
        });

    }

    @OnClick({R.id.btnBack, R.id.btn_send_otp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                this.finish();
                break;
            case R.id.btn_send_otp:
                validator.validate();
                if (isPassedValidate) {
                    showProgressBar();
                    submitRegisterPresenter.submitRegister(registerToken, txtOtp.getText().toString().trim());
                }
                break;
        }
    }
}
