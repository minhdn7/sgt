package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;

public class CheckLoginActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.txt_header)
    TextView txtHeader;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.txt_noi_dung)
    TextView txtNoiDung;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.lo_null)
    FrameLayout loNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnBack, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
