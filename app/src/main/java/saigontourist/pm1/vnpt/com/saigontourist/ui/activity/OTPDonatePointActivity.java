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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.DonatePointRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.DonatePointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.DonatePointView;

public class OTPDonatePointActivity extends BaseActivity implements DonatePointView {

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
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.txtOtp)
    @NotEmpty(trim = true, messageResId = R.string.str_check_otp)
    EditText txtOtp;
    @BindView(R.id.btn_send_otp)
    Button btnSendOtp;
    private DonatePointRequest donatePointRequest;

    @Inject
    DonatePointPresenter donatePointPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpdonate_point);
        ButterKnife.bind(this);
        try {
            donatePointRequest = (DonatePointRequest) getIntent().getSerializableExtra("DONATE_REQUEST");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
        initControls();
    }

    private void initControls() {
        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        textView5.setTypeface(face);
        txtOtp.setTypeface(face);
    }

    private void initView() {
        donatePointPresenter.setView(this);
        donatePointPresenter.onViewCreate();
    }

    @OnClick({R.id.btnBack, R.id.btn_send_otp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                finish();
                break;
            case R.id.btn_send_otp:
                validator.validate();
                if(isPassedValidate){
                    if(donatePointRequest != null){
                        showProgressBar();
                        donatePointRequest.setMaXacThuc(txtOtp.getText().toString().trim());
                        donatePointPresenter.sendPoint(donatePointRequest);
                    }

                }
                break;
        }
    }

    @Override
    public void onDonatePointSuccess(CommonApiResult response) {
        hideProgressBar();
        dialogDonatePointSuccess(response.errorDesc);
    }

    private void dialogDonatePointSuccess(String message) {
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
                OTPDonatePointActivity.this.finish();
                startActivity(new Intent(OTPDonatePointActivity.this, MainActivity.class));

            }
        });
    }

    @Override
    public void onSendOTPSuccess(CommonApiResult response) {
        hideProgressBar();
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
}
