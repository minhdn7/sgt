package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.davidmiguel.numberkeyboard.NumberKeyboard;
import com.davidmiguel.numberkeyboard.NumberKeyboardListener;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataMemberCardResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.AppState;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.LoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.membercard.MemberCardPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.membercard.MemberCardView;

public class InforCardMemberFragment extends BaseFragment
        implements NumberKeyboardListener, CompoundButton.OnCheckedChangeListener, MemberCardView {

    @BindView(R.id.numberPinId1)
    ImageView numberPinId1;
    @BindView(R.id.numberPinId2)
    ImageView numberPinId2;
    @BindView(R.id.numberPinId3)
    ImageView numberPinId3;
    @BindView(R.id.numberPinId4)
    ImageView numberPinId4;
    @BindView(R.id.imageViewBarcode)
    ImageView imageViewBarcode;
    @BindView(R.id.llInputPass)
    LinearLayout llInputPass;
    @BindView(R.id.llImageBarcode)
    LinearLayout llImageBarcode;
    @BindView(R.id.llMessage)
    LinearLayout llMessage;
    @BindView(R.id.numberKeyboard)
    NumberKeyboard numberKeyboard;
    @BindView(R.id.quenMaPin)
    TextView quenMaPin;
    @BindView(R.id.txtTitlePass)
    TextView txtTitlePass;
    @BindView(R.id.txtNhapMa)
    TextView txtNhapMa;
    @BindView(R.id.timeCount)
    TextView timeCount;
    @BindView(R.id.txtMessage)
    TextView txtMessage;
    @BindView(R.id.determinateBar)
    ProgressBar determinateBar;
    @BindView(R.id.progressBarImage)
    ProgressBar progressBarImage;
    @BindView(R.id.switch_bar_or_qr)
    SwitchCompat switchBarOrQr;

    String strAmount;
    String maPinStep1, maPinStep2, maPinToGetBarcode;
    @BindView(R.id.tv_toolbar_card)
    TextView tvToolbarCard;
    @BindView(R.id.tv_content_toolbar_card)
    TextView tvContentToolbarCard;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.imageView_captcha)
    ImageView imageViewCaptcha;
    @BindView(R.id.imv_refresh_captcha)
    ImageView imvRefreshCaptcha;
    @BindView(R.id.edt_capcha)
    EditText edtCapcha;
    @BindView(R.id.btn_continue)
    Button btnContinue;
    @BindView(R.id.ll_create_forget_pin)
    LinearLayout llCreateForgetPin;
    @BindView(R.id.lo_nhap_ma_pin)
    LinearLayout loNhapMaPin;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.ll_not_login)
    LinearLayout llNotLogin;
    @BindView(R.id.scrollView_login)
    ScrollView scrollViewLogin;
    private String androidId;
    private String date_time;
    private CountDownTimer countDownTimer;
    private int timeProgress = 0;

    DataMemberCardResult resultResponse;

    @Inject
    AppState appState;
    @Inject
    MemberCardPresenter memberCardPresenter;

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_bar_or_qr:
                if (b) {
                    imageViewBarcode.setImageBitmap(decodeImageBarcode(resultResponse.getUrlQrcode()));
                } else {
                    imageViewBarcode.setImageBitmap(decodeImageBarcode(resultResponse.getUrlBarcode()));
                }
                break;
        }
    }


    private enum State {
        NHAPMAPIN, CAIDATMAPIN, XACNHANMAPIN, KHONGTONTAITHE, IMAGEBARCODE, CREATEORFORGET
    }

    private State screenState;
    private final static String TAG = InforCardMemberFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_card_member, container, false);
        ButterKnife.bind(this, view);
        addControls();
        addEvents();
        return view;
    }


    public void addControls() {
        memberCardPresenter.setView(this);
        memberCardPresenter.onViewCreate();
        strAmount = "";
        switchBarOrQr.setOnCheckedChangeListener(this);
        numberKeyboard.setListener(this);
        if (checkUserLogin()) {
            goToScreenNhapMaPin();
        }
    }

    public void goToScreenCaiDatMaPin() {

        screenState = State.CAIDATMAPIN;

        tvContentToolbarCard.setText(getResources().getString(R.string.str_barcode_qrcode));
        ivBack.setVisibility(View.VISIBLE);
        llCreateForgetPin.setVisibility(View.GONE);
        llImageBarcode.setVisibility(View.GONE);
        llInputPass.setVisibility(View.VISIBLE);
        txtTitlePass.setVisibility(View.VISIBLE);
        llMessage.setVisibility(View.GONE);
        quenMaPin.setVisibility(View.GONE);
        txtNhapMa.setText(R.string.str_vuilongnhapmapin);
        txtTitlePass.setText(R.string.str_caidatmapin);
        disableEnableControls(true, numberKeyboard);
    }

    public void goToScreenXacNhanMaPin() {

        screenState = State.XACNHANMAPIN;

        tvContentToolbarCard.setText(getResources().getString(R.string.str_barcode_qrcode));
        ivBack.setVisibility(View.VISIBLE);
        llCreateForgetPin.setVisibility(View.GONE);
        llImageBarcode.setVisibility(View.GONE);
        llInputPass.setVisibility(View.VISIBLE);
        txtTitlePass.setVisibility(View.VISIBLE);
        llMessage.setVisibility(View.GONE);
        quenMaPin.setVisibility(View.GONE);
        txtNhapMa.setText(R.string.str_vuilongnhaplaimapin);
        txtTitlePass.setText(R.string.str_xacnhanmapin);
        disableEnableControls(true, numberKeyboard);
    }

    public void goToScreenNhapMaPin() {

        screenState = State.NHAPMAPIN;

        tvContentToolbarCard.setText(getResources().getString(R.string.str_barcode_qrcode));
        ivBack.setVisibility(View.GONE);
        llCreateForgetPin.setVisibility(View.GONE);
        llImageBarcode.setVisibility(View.GONE);
        llInputPass.setVisibility(View.VISIBLE);
        txtTitlePass.setVisibility(View.GONE);
        llMessage.setVisibility(View.GONE);
        quenMaPin.setVisibility(View.VISIBLE);
        txtNhapMa.setText(R.string.str_vuilongnhapmapin);
        disableEnableControls(true, numberKeyboard);
    }

    public void goToScreenCreateOrForgetPin() {

        screenState = State.CREATEORFORGET;

        tvContentToolbarCard.setText(getResources().getString(R.string.str_barcode_forget_pin));
        ivBack.setVisibility(View.VISIBLE);
        llCreateForgetPin.setVisibility(View.VISIBLE);
        llImageBarcode.setVisibility(View.GONE);
        llInputPass.setVisibility(View.GONE);
        txtTitlePass.setVisibility(View.GONE);
        llMessage.setVisibility(View.GONE);
        quenMaPin.setVisibility(View.GONE);
        txtNhapMa.setText(R.string.str_vuilongnhapmapin);
        txtTitlePass.setText(R.string.str_caidatmapin);
        disableEnableControls(true, numberKeyboard);
    }

    public void goToScreenXacThucOtp() {
//        Intent intent = new Intent(BarCodeActivity.this, XacThucOtpBarcodeActivity.class);
//        startActivityForResult(intent, StringDef.REQUEST_CODE_BAR_CODE);
    }

    public void goToScreenKhoaThe(boolean isKhoaThe) {

        screenState = State.KHONGTONTAITHE;

        if (isKhoaThe) {
            llImageBarcode.setVisibility(View.GONE);
            llInputPass.setVisibility(View.GONE);
            txtTitlePass.setVisibility(View.GONE);
            quenMaPin.setVisibility(View.GONE);
            llMessage.setVisibility(View.VISIBLE);
        } else {
            llImageBarcode.setVisibility(View.GONE);
            llInputPass.setVisibility(View.VISIBLE);
            txtTitlePass.setVisibility(View.GONE);
            quenMaPin.setVisibility(View.VISIBLE);
            llMessage.setVisibility(View.VISIBLE);
            disableEnableControls(false, numberKeyboard);
        }
    }

    public void goToScreenPrevious() {
        switch (screenState) {
            case CAIDATMAPIN:
                goToScreenCreateOrForgetPin();
                break;
            case NHAPMAPIN:
                break;
            case XACNHANMAPIN:
                goToScreenCaiDatMaPin();
                break;
            case KHONGTONTAITHE:
                break;
            case IMAGEBARCODE:
            case CREATEORFORGET:
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                goToScreenNhapMaPin();
                break;
            default:
                break;
        }
    }

    public void goToScreenImageBarcode() {
        screenState = State.IMAGEBARCODE;

        timeProgress = 0;
        determinateBar.setProgress(timeProgress);
        ivBack.setVisibility(View.VISIBLE);
        llInputPass.setVisibility(View.GONE);
        llImageBarcode.setVisibility(View.VISIBLE);
        txtTitlePass.setVisibility(View.GONE);
        quenMaPin.setVisibility(View.GONE);
        llMessage.setVisibility(View.GONE);
    }

    public void goToNextStep(String strAmount) {
        switch (screenState) {
            case CAIDATMAPIN:
                maPinStep1 = strAmount;
                goToScreenXacNhanMaPin();
                break;
            case XACNHANMAPIN:
                maPinStep2 = strAmount;
                if (maPinStep1 != null && !maPinStep1.isEmpty() && maPinStep2.equals(maPinStep1)) {
                    // call api doi ma pin
                    showProgressBar();
                    getApiPassCode(Integer.valueOf(strAmount));
                } else {
                    //thong bao sai ma pin
                    showDialogXacNhan(getResources().getString(R.string.str_xacnhanmapinkhongdung), 1);
                }
                break;
            case KHONGTONTAITHE:

                break;
            case NHAPMAPIN:
                // call api getBarcode
                showProgressBar();
                if (!appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, "").isEmpty()) {
                    memberCardPresenter.getMemberCardBarQrCode(appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, ""),
                            Integer.valueOf(strAmount));
                }


                break;
            case CREATEORFORGET:
                // call api check pass and captcha
                showProgressBar();

                break;
            default:
                break;
        }
    }

    public Bitmap decodeImageBarcode(String url) {
        final String urlBase64Encoded = url.substring(url.indexOf(",") + 1);
        final byte[] decodedBytes = Base64.decode(urlBase64Encoded, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        return decodedBitmap;
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    public void addEvents() {

        quenMaPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePin();
                //Call api quen ma pin
                showProgressBar();
                memberCardPresenter.getMemberCardCaptcha();

            }
        });
        imvRefreshCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call api quen ma pin
                showProgressBar();
                memberCardPresenter.getMemberCardCaptcha();

            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call api kiem tra captcha
                getAuthentication();

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartInputCaptCha();
                goToScreenPrevious();
            }
        });
        // đếm ngược 2 phút
        countDownTimer = new CountDownTimer(120000, 100) {
            public void onTick(long millisUntilFinished) {

//                count down
//                int seconds = (int) (millisUntilFinished / 1000) % 60;
//                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
//                String time = String.format("0%d phút %02d giây", minutes, seconds);
//                timeCount.setText(time);
//                timeProgress--;
//                determinateBar.setProgress(timeProgress * 100 / (120000 / 1000));
//
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int milliseconds = (int) (millisUntilFinished - (seconds * 1000) - 60 * minutes * 1000) / 100; //10 tich tắc = 1 s

//                int milliseconds = (int) millisUntilFinished  / 100 % 60 %10 ; //10 tich tắc = 1 s

                String time = String.format("0%d : %02d : %02d", minutes, seconds, milliseconds);
                timeCount.setText(time);
                timeProgress++;
                determinateBar.setProgress(timeProgress * 100 / (120000 / 100));
            }

            public void onFinish() {
                timeProgress++;
                determinateBar.setProgress(100);
                if (!getActivity().isFinishing()) {
                    showDialogTimer();
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if (llNotLogin.getVisibility() == View.VISIBLE && checkUserLogin()) {
            goToScreenNhapMaPin();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (screenState == State.IMAGEBARCODE) {
            goToScreenNhapMaPin();
        }
        memberCardPresenter.onViewDestroy();
    }

    @Override
    public void onNumberClicked(int number) {
        strAmount += String.valueOf(number);
        if (strAmount.length() < 5) {
            showAmount();
        }
    }

    @Override
    public void onLeftAuxButtonClicked() {

    }

    @Override
    public void onRightAuxButtonClicked() {
        removePin();
    }

    private void showAmount() {
        switch (strAmount.length()) {
            case 1:
                numberPinId1.setBackgroundResource(R.drawable.ic_checked_pin);
                break;
            case 2:
                numberPinId1.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId2.setBackgroundResource(R.drawable.ic_checked_pin);
                break;
            case 3:
                numberPinId1.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId2.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId3.setBackgroundResource(R.drawable.ic_checked_pin);
                break;
            case 4:
                numberPinId1.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId2.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId3.setBackgroundResource(R.drawable.ic_checked_pin);
                numberPinId4.setBackgroundResource(R.drawable.ic_checked_pin);

                //check next step
                goToNextStep(strAmount);
                removePin();
                break;
        }
    }

    private void removePin() {
        strAmount = "";
        numberPinId1.setBackgroundResource(R.drawable.ic_check_pin);
        numberPinId2.setBackgroundResource(R.drawable.ic_check_pin);
        numberPinId3.setBackgroundResource(R.drawable.ic_check_pin);
        numberPinId4.setBackgroundResource(R.drawable.ic_check_pin);
    }


    public void showDialogXacNhan(String noiDungThongBao, final int flagDialog) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog_notification_confirm, null);
            dialogBuilder.setView(dialogView);
            Button btnCalcel = (Button) dialogView.findViewById(R.id.btnYes);
            TextView txtNoiDungThongBao = (TextView) dialogView.findViewById(R.id.txtNoiDungThongBao);
            final AlertDialog b = dialogBuilder.create();
            b.setCanceledOnTouchOutside(false);
            b.show();

            txtNoiDungThongBao.setText(noiDungThongBao);
            btnCalcel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.dismiss();
                    switch (flagDialog) {
                        //Sai mã Pin
                        case 1:
                            goToScreenCaiDatMaPin();
                            break;
                        //Đổi Mã Pin Thành Công
                        case 2:
                            goToScreenNhapMaPin();
                            break;
                        default:
                            break;
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showDialogTimer() {

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View timerDialogView = factory.inflate(R.layout.dialog_choose_option, null);

        dialog.setView(timerDialogView);
        timerDialogView.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ẩn dialog
                dialog.dismiss();
                removePin();
                goToScreenNhapMaPin();
            }
        });
        timerDialogView.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                removePin();
                EventBus.getDefault().postSticky(new MessageEvent.ReplaceFragmentMessage());
            }
        });

        dialog.show();
    }

    private void updateGUIWrongPass(Intent intent) {
//        if (intent.getExtras() != null) {
//            String millisUntilFinished = intent.getStringExtra("time");
//            boolean booleanFinished = intent.getBooleanExtra("finish_time", false);
//            txtMessage.setText(fromHtml("<font color=#424242>"
//                    + getResources().getString(R.string.strNhapSaiMaPin5Lan)
//                    + "</font>"
//                    + " <font color=#b71c1c>"
//                    + millisUntilFinished
//                    + "</font>"));
//            if (booleanFinished) {
//                goToScreenNhapMaPin();
//            }
//        }
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }


    private void disableEnableControls(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }

    private boolean checkUserLogin() {
        if (!appState.getState(AppState.PREF_KEY_STATUS_LOGIN_USER, false)) {
            llNotLogin.setVisibility(View.VISIBLE);
            scrollViewLogin.setVisibility(View.GONE);
            return false;
        } else {
            llNotLogin.setVisibility(View.GONE);
            scrollViewLogin.setVisibility(View.VISIBLE);
            return true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onGetMemberCardCaptchaSuccses(ResponseBody responseBody) {
        hideProgressBar();
        if (responseBody != null) {
            if (screenState != State.CREATEORFORGET) {
                goToScreenCreateOrForgetPin();
            }
            Bitmap bm = BitmapFactory.decodeStream(responseBody.byteStream());
            imageViewCaptcha.setImageBitmap(bm);

        }
    }

    @Override
    public void onGetMemberCardBarQrCodeSuccses(DataMemberCardResult result) {
        hideProgressBar();
        if (result != null) {
            resultResponse = result;
            countDownTimer.start();
            goToScreenImageBarcode();
            switchBarOrQr.setChecked(false);
            progressBarImage.setVisibility(View.GONE);
            imageViewBarcode.setImageBitmap(decodeImageBarcode(result.getUrlBarcode()));
        }

    }

    @Override
    public void onGetMemberCardPassCodeSuccses(CommonApiResult dataResult) {
        hideProgressBar();
        goToScreenNhapMaPin();
    }

    @Override
    public void onGetMemberCardAuthenticationSuccses(CommonApiResult result) {
        hideProgressBar();
        restartInputCaptCha();
        goToScreenCaiDatMaPin();
    }

    private void restartInputCaptCha() {
        edtCapcha.setText("");
        edtPassword.setText("");
    }

    @Override
    public void onGetMemberCardFailed(String message) {
        hideProgressBar();
        showToast(message);
        if (screenState == State.CREATEORFORGET) {
            showProgressBar();
            memberCardPresenter.getMemberCardCaptcha();
        }

    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();
    }

    private void getAuthentication() {
        String strCaptcha = edtCapcha.getText().toString().trim();
        String strPass = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(strPass)) {
            edtPassword.requestFocus();
            showToast("Mật khẩu không được để trống.");
            return;
        }
        if (TextUtils.isEmpty(strCaptcha)) {
            edtCapcha.requestFocus();
            showToast("Captcha không được để trống.");
            return;
        }
        if (!appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, "").isEmpty()) {
            showProgressBar();
            memberCardPresenter.getMemberCardAuthentication(appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, "")
                    , strPass, strCaptcha);
        }
    }

    private void getApiPassCode(int passCode) {
        if (!appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, "").isEmpty()) {
            showProgressBar();
            memberCardPresenter.getMemberCardPassCode(appState.getState(AppState.PREF_KEY_TOKEN_LOGIN_USER, ""), passCode);
        }

    }
}
