package saigontourist.pm1.vnpt.com.saigontourist.app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.TinyDB;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetTokenDevPresenter;

/**
 * Created by linhl on 4/13/2018.
 */

public class BaseFragment extends Fragment implements saigontourist.pm1.vnpt.com.saigontourist.ui.view.View, Validator.ValidationListener {
    private KProgressHUD hud;
    @Inject
    GetTokenDevPresenter getTokenDevPresenter;
    private Toast toast;
    public Validator validator;
    protected boolean isPassedValidate;
    public TinyDB tinyDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        initProgress();
        getTokenDevPresenter.setView(this);
        getTokenDevPresenter.onViewCreate();
        validator = new Validator(this);
        validator.setValidationListener(this);
        tinyDB = new TinyDB(getActivity());
    }

    protected void injectDependencies() {
        ((SaiGonTouristApplication) getActivity().getApplication()).inject(this);
    }

    protected void injectViews(View view) {
        ButterKnife.bind(this, view);

    }

    protected void initProgress() {
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDetailsLabel("Đang kết nối...")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
    }

    public void showProgressBar() {
        try {
            if (hud != null && !hud.isShowing())
                hud.show();
        } catch (Exception ex) {

        }

    }

    public void hideProgressBar() {
        try {
            if (hud != null && hud.isShowing())
                hud.dismiss();
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } catch (Exception ex) {

        }

    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
//        AppDef.TOKEN_DEV = tokenDev;
        tinyDB.putString(getString(R.string.TOKEN_DEV), tokenDev);

    }
    public void getTokenDevIfNeed() {
        getTokenDevPresenter.getTokenDev(getString(R.string.gettoken_param1),
                getString(R.string.gettoken_param2));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getTokenDevPresenter.onViewDestroy();
    }

    public void dilogThongBao(String noiDung){
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

        text_message.setText(noiDung);

        btnYes.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                b.dismiss();
            }
        });

    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onValidationSucceeded() {
        isPassedValidate = true;
        Log.e("Valid: ", "Valid Success");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isPassedValidate = false;
        Log.e("Valid: ", "" + isPassedValidate);
        for (ValidationError error : errors) {
            android.view.View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }
}

