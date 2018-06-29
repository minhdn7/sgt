package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Optional;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserRequest;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.RegisterUserResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.RegisterUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.LoginUserView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.RegisterUserView;

public class RegisterActivity extends BaseActivity implements RegisterUserView,
                        com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener{

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.font_gioithieu_dang_ky)
    TextView fontGioithieuDangKy;


    @NotEmpty(trim = true, messageResId = R.string.str_thieu_ten_nguoi_dung)
    @Length(min = 1, max = 100, messageResId = R.string.str_min_max_1_100)
    @BindView(R.id.txtName)
    EditText txtName;

    @BindView(R.id.img_name)
    TextView imgName;

    @NotEmpty(trim = true, messageResId = R.string.str_thieu_ten_dang_nhap)
    @Length(min = 6, max = 20, messageResId = R.string.str_username_min_max_6_20)
    @BindView(R.id.txtUser)
    EditText txtUser;

    @BindView(R.id.img_user)
    TextView imgUser;

    @NotEmpty(trim = true, messageResId = R.string.str_check_mat_khau)
    @Length(min = 8, max = 20, messageResId = R.string.str_min_max_6_20)
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.img_pass)
    TextView imgPass;

    @NotEmpty(trim = true, messageResId = R.string.str_check_mat_khau)
    @Length(min = 8, max = 20, messageResId = R.string.str_min_max_6_20)
    @BindView(R.id.txtRePassword)
    EditText txtRePassword;

    @BindView(R.id.img_re_pass)
    TextView imgRePass;

    @NotEmpty(trim = true, messageResId = R.string.str_check_ngay_sinh)
    @BindView(R.id.txtBirthday)
    TextView txtBirthday;

    @BindView(R.id.img_nam_sinh)
    TextView imgNamSinh;

    @BindView(R.id.txtEmail)
//    @NotEmpty(trim = true, messageResId = R.string.str_check_email_3)
//    @Email(messageResId = R.string.str_check_email_3)
    EditText txtEmail;

    @Length(trim = true, min = 10, max = 11, messageResId = R.string.str_sai_dinh_dang_so_dien_thoai)
    @BindView(R.id.txtPhoneNumber)
    EditText txtPhoneNumber;

    @BindView(R.id.img_sdt)
    TextView imgSdt;

    @Length(max = 200, messageResId = R.string.str_diachi)
//    @NotEmpty(trim = true, messageResId = R.string.str_check_dia_chi)
    @BindView(R.id.txtFullAddress)
    EditText txtFullAddress;

    @BindView(R.id.txtSoDienThoaiNguoiGt)
    EditText txtSoDienThoaiNguoiGt;
    @BindView(R.id.tv_tinh)
    TextView tvTinh;
    @BindView(R.id.imageViewShop)
    ImageView imageViewShop;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.rbNu)
    RadioButton rbNu;
    @BindView(R.id.rbNam)
    RadioButton rbNam;
    @BindView(R.id.rbGroupGioiTinh)
    RadioGroup rbGroupGioiTinh;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.ckbAgree)
    CheckBox ckbAgree;
    @BindView(R.id.text_dong_y)
    TextView textDongY;
    @BindView(R.id.text_dieu_khoan)
    TextView textDieuKhoan;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    SimpleDateFormat simpleDateFormat;
    private int mYear, mMonth, mDay;
    private String ngaySinh;
    private int sex = 0;

    @Inject
    RegisterUserPresenter registerUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initControls();
        addEvents();
    }

    private void addEvents() {
        rbGroupGioiTinh.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rbNu) {
                    sex = 1;
                } else if (checkedId == R.id.rbNam) {
                    sex = 0;

                }
            }
        });
    }

    private void initControls() {
        hideImageEdittext(txtName, imgName);
        hideImageEdittext(txtUser, imgUser);
        hideImageEdittext(txtPassword, imgPass);
        hideImageEdittext(txtRePassword, imgRePass);
        hideImageEdittext(txtPhoneNumber, imgSdt);

        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        fontGioithieuDangKy.setTypeface(face);
        text.setTypeface(face);
        text2.setTypeface(face);
        text3.setTypeface(face);
        ckbAgree.setTypeface(face);
        rbNu.setTypeface(face);
        rbNam.setTypeface(face);

        txtName.setTypeface(face);
        txtUser.setTypeface(face);
        txtPassword.setTypeface(face);
        txtRePassword.setTypeface(face);
        txtEmail.setTypeface(face);
        txtPhoneNumber.setTypeface(face);
        txtFullAddress.setTypeface(face);
        txtSoDienThoaiNguoiGt.setTypeface(face);

        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaHeavy.TTF");
        textDongY.setTypeface(face2);
        textDieuKhoan.setText(Html.fromHtml(getString(R.string.str_dieu_khoan_sudung)));
        textDieuKhoan.setTypeface(face2);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    }

    private void initView() {
        registerUserPresenter.setView(this);
        registerUserPresenter.onViewCreate();
    }

    @OnClick({R.id.btnBack, R.id.btnRegister, R.id.txtBirthday, R.id.loBirthday, R.id.text_dieu_khoan})
    public void onViewClicked(View view) {
        Calendar cNamSinh = Calendar.getInstance();
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnRegister:
                registerUser();
                break;
            case R.id.txtBirthday:
                mYear = cNamSinh.get(Calendar.YEAR);
                mMonth = cNamSinh.get(Calendar.MONTH);
                mDay = cNamSinh.get(Calendar.DAY_OF_MONTH);
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;
            case R.id.loBirthday:
                mYear = cNamSinh.get(Calendar.YEAR);
                mMonth = cNamSinh.get(Calendar.MONTH);
                mDay = cNamSinh.get(Calendar.DAY_OF_MONTH);
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;
            case R.id.text_dieu_khoan:
                startActivity(new Intent(this, PolicyActivity.class));
                break;
        }
    }

    private void registerUser() {
        validator.validate();
        if(isPassedValidate){
            if(!isValidEmail(txtEmail.getText().toString().trim())){
                txtEmail.requestFocus();
                txtEmail.setError(getString(R.string.str_check_email_3));
                return;
            }
            if (!txtPassword.getText().toString().trim().equals(txtRePassword.getText().toString().trim())) {
                txtRePassword.setError("Nhập lại mật khẩu không đúng.");
                txtRePassword.requestFocus();
                return;
            }
            else if (!ckbAgree.isChecked()){
                dilogThongBao(getString(R.string.str_dong_y_dieu_khoan));
            }else {
                RegisterUserRequest registerUserRequest = new RegisterUserRequest();
                registerUserRequest.setHoTen(txtName.getText().toString().trim());
                registerUserRequest.setTenDangNhap(txtUser.getText().toString().trim());
                registerUserRequest.setDiaChi(txtFullAddress.getText().toString().trim());
                registerUserRequest.setEmail(txtEmail.getText().toString().trim());
                registerUserRequest.setMatKhau(txtRePassword.getText().toString().trim());
                registerUserRequest.setSoDienThoai(txtPhoneNumber.getText().toString().trim());
                registerUserRequest.setNgaySinh(txtBirthday.getText().toString().trim());
                registerUserRequest.setGioiTinh(sex);
                showProgressBar();
                registerUserPresenter.registerUser(registerUserRequest);
            }

        }
    }


    private void hideImageEdittext(EditText editText, final TextView img) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    img.setVisibility(View.GONE);
                } else {
                    img.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onRegisterUserSuccses(RegisterUserResponse response) {
        hideProgressBar();
        dilogSendOTP(response.getErrorDesc(), response.getToken());
    }

    @Override
    public void onRegisterUserFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onRegisterUserError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        txtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public void dilogSendOTP(String noiDung, final String tokenRegister){
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

        text_message.setText(noiDung);

        btnYes.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                b.dismiss();
                Intent intent = new Intent(RegisterActivity.this, OtpRegisterActivity.class);
                intent.putExtra("TOKEN_REGISTER", tokenRegister);
                startActivity(intent);
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        if(TextUtils.isEmpty(target)){
            return true;
        }else return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
