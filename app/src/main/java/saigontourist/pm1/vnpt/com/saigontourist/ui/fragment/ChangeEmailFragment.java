package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.Email;
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
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.ChangeEmailRequest;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeEmailPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangePhoneNumberPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.CreateOtpPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeEmailView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.CreateOtpView;

/**
 * Created by MinhDN on 24/4/2018.
 */
public class ChangeEmailFragment extends BaseFragment implements ChangeEmailView, CreateOtpView{
    Unbinder unbinder;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.text)
    TextView text;

    @NotEmpty(messageResId = R.string.str_check_email)
    @Length(max = 100, messageResId = R.string.str_min_max_1_100)
    @Email(messageResId = R.string.str_check_email_2)
    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.btn_change_email)
    Button btnChangeEmail;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageLoading)
    LinearLayout imageLoading;

    @Inject
    ChangeEmailPresenter changeEmailPresenter;
    @Inject
    CreateOtpPresenter createOtpPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_email, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        changeEmailPresenter.setView(this);
        changeEmailPresenter.onViewCreate();
        createOtpPresenter.setView(this);
        createOtpPresenter.onViewCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnBack, R.id.btn_change_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getFragmentManager().beginTransaction().remove(ChangeEmailFragment.this).commit();
                break;
            case R.id.btn_change_email:
                validator.validate();
                if(isPassedValidate && tinyDB.getBoolean(getString(R.string.IS_LOGIN))){
                    showProgressBar();
                    createOtpPresenter.createOtp(tinyDB.getString(getString(R.string.TOKEN_USER)), "");
                }
                break;
        }
    }

    @Override
    public void onChangeEmailSuccses(CommonApiResult response) {
        hideProgressBar();
        dilogThongBao(response.getErrorDesc());
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
    public void onCreateOtpSuccsess(CommonApiResult response) {
        hideProgressBar();
        Toast.makeText(getActivity(), "Mã OTP được gửi về số điện thoại/email của bạn.", Toast.LENGTH_LONG).show();
        SubmitOtpFragment submitOtpFragment = new SubmitOtpFragment();
        Bundle bundle=new Bundle();
        bundle.putString("CHANGE_TYPE", "email");
        bundle.putString("CHANGE_EMAIL", txtEmail.getText().toString().trim());
        submitOtpFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, submitOtpFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCreateOtpFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onCreateOtpError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }
}
