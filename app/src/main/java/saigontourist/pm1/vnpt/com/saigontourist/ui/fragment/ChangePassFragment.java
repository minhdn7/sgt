package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

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
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangePassRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.PointInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePasswordPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangePasswordView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangePassFragment extends BaseFragment implements ChangePasswordView{
    Unbinder unbinder;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.text)
    TextView text;
    @NotEmpty(messageResId = R.string.str_check_mat_khau)
    @Length(min = 8, max = 20, messageResId = R.string.str_min_max_6_20)
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @NotEmpty(messageResId = R.string.str_check_mat_khau)
    @Length(min = 8, max = 20, messageResId = R.string.str_min_max_6_20)
    @BindView(R.id.txtPasswordNew)
    EditText txtPasswordNew;

    @NotEmpty(messageResId = R.string.str_check_mat_khau)
    @Length(min = 8, max = 20, messageResId = R.string.str_min_max_6_20)
    @BindView(R.id.txtRePassword)
    EditText txtRePassword;

    @BindView(R.id.tv_change_pass)
    Button tvChangePass;

    @Inject
    ChangePasswordPresenter changePasswordPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        changePasswordPresenter.setView(this);
        changePasswordPresenter.onViewCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnBack, R.id.tv_change_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getFragmentManager().beginTransaction().remove(ChangePassFragment.this).commit();
                break;
            case R.id.tv_change_pass:
                validator.validate();
                if(isPassedValidate){
                    if(tinyDB.getBoolean(getString(R.string.IS_LOGIN))){
                        showProgressBar();
                        ChangePassRequest changePassRequest = new ChangePassRequest();
                        changePassRequest.setTokenhoivien(tinyDB.getString(getString(R.string.TOKEN_USER)));
                        changePassRequest.setMatKhauHientai(txtPassword.getText().toString().trim());
                        changePassRequest.setMatKhauMoi(txtPasswordNew.getText().toString().trim());
                        changePassRequest.setXacNhanMatKhauMoi(txtRePassword.getText().toString().trim());
                        changePasswordPresenter.changePassword(changePassRequest);
                    }

                }
                break;
        }
    }

    @Override
    public void onChangePasswordSuccses(CommonApiResult response) {
        hideProgressBar();
        dilogChangePassSuccess(response.getErrorDesc());
    }

    @Override
    public void onChangePasswordFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onChangePasswordError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    public void dilogChangePassSuccess(String message){
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
                getFragmentManager().beginTransaction().remove(ChangePassFragment.this).commit();
            }
        });

    }
}
