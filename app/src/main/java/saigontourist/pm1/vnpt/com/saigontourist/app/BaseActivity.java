package saigontourist.pm1.vnpt.com.saigontourist.app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.user.UserInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.TinyDB;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetTokenDevPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;
import timber.log.Timber;

/**
 * Created by linhl on 4/13/2018.
 */

public class BaseActivity extends AppCompatActivity implements View,
        Validator.ValidationListener {
    private KProgressHUD hud;
    private static final String TAG = "BaseActivity";
    private Toast toast;
    public Validator validator;
    protected boolean isPassedValidate;
    public TinyDB tinyDB;
    TextView tvToolbarTitle;
    Toolbar toolbarMain;

    @Inject
    GetTokenDevPresenter getTokenDevPresenter;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        injectViews();
        configureToolbar();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        injectDependencies();
        getTokenDevPresenter.setView(this);
        getTokenDevPresenter.onViewCreate();
        validator = new Validator(this);
        validator.setValidationListener(this);
        tinyDB = new TinyDB(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getTokenDevPresenter.onViewDestroy();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar() {
        toolbarMain = (Toolbar)findViewById(R.id.toolbar_main);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        if (toolbarMain != null) {
            setSupportActionBar(toolbarMain);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void setTitleToobar(String title) {
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setText(title);
        }
    }
    protected void injectDependencies() {
        ((SaiGonTouristApplication) getApplication()).inject(this);
    }

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected void initProgress() {
        hud = KProgressHUD.create(this)
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
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } catch (Exception ex) {

        }

    }
    public void getTokenDevIfNeed() {
        getTokenDevPresenter.getTokenDev(getString(R.string.gettoken_param1),
                getString(R.string.gettoken_param2));
    }
    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showLongToast(String message) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }


    public void dilogThongBao(String noiDung){
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
            }
        });

    }


    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        Timber.tag(TAG).d(tokenDev);
//        AppDef.TOKEN_DEV = tokenDev;
        tinyDB.putString(getString(R.string.TOKEN_DEV), tokenDev);
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
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    public static String convertDateToString(Date date, String format) {
        String dateStr = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            dateStr = df.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateStr;
    }
}
