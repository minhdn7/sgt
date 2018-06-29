package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.point.HistoryPointResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.HistoryPointAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.HousePointAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.HistoryPointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.point.PointInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.point.HistoryPointView;

public class HistoryPointActivity extends BaseActivity implements HistoryPointView, com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener{

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.txt_ngay_bat_dau)
    TextView txtNgayBatDau;
    @BindView(R.id.txt_ngay_ket_thuc)
    TextView txtNgayKetThuc;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_null)
    TextView loNull;
    @BindView(R.id.lo_da_dang_nhap)
    LinearLayout loDaDangNhap;
    @BindView(R.id.tv_gioithieu)
    TextView tvGioithieu;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.lo_chua_dang_nhap)
    LinearLayout loChuaDangNhap;

    //load more
    Context context;
    private Integer startIndex = 0;
    private Integer pageSize = 100;
    private String ngayBatDau, ngayKetThuc;
    private int mYear, mMonth, mDay;
    private SimpleDateFormat simpleDateFormat;
    private int numberDate;
    private int pastVisiblesItems , totalItemCount, visibleItemCount;
    private boolean isLoadMore = false;
    private int visibleThreshold = 5;
    private RecyclerView.LayoutManager mLayoutManager;
    @Inject
    HistoryPointPresenter historyPointPresenter;

    private List<HistoryPointResponse.Datum> dataList;
    private HistoryPointAdapter historyPointAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_point);
        ButterKnife.bind(this);
        initView();
        initControls();
        checkLogin();
        addEvents();
    }

    private void addEvents() {
        rcvDanhSach.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems  = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (isLoadMore &&
                            (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        showProgressBar();
                        isLoadMore = false;
                        startIndex += 1;
                        String fromDate = txtNgayBatDau.getText().toString().trim();
                        String toDate = txtNgayKetThuc.getText().toString().trim();
                        historyPointPresenter.getHistoryPoint(tinyDB.getString(getString(R.string.TOKEN_USER)), startIndex, pageSize, fromDate, toDate);
                    }
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    private void initControls() {
        //load more
        context = this;
        imgLoading.setVisibility(View.GONE);

        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/SFUFuturaBook.TTF");

        //tinh ngay hien tai
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ngayKetThuc = sdf.format(new Date());

        // lui 1 thang so voi ngay hien tai
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        ngayBatDau = sdf.format(date);

        txtNgayBatDau.setTypeface(face);
        txtNgayBatDau.setText(ngayBatDau);
        txtNgayKetThuc.setTypeface(face);
        txtNgayKetThuc.setText(ngayKetThuc);
        text.setTypeface(face);
//        text1.setTypeface(face);
        tvGioithieu.setTypeface(face);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar cStart = Calendar.getInstance();
        mYear = cStart.get(Calendar.YEAR);
        mMonth = cStart.get(Calendar.MONTH);
        mDay = cStart.get(Calendar.DAY_OF_MONTH);

        // void
        dataList = new ArrayList<>();
        historyPointAdapter = new HistoryPointAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(historyPointAdapter);


    }

    private void initView() {
        historyPointPresenter.setView(this);
        historyPointPresenter.onViewCreate();
    }

    @OnClick({R.id.btnBack, R.id.btnLogin, R.id.btn_search, R.id.txt_ngay_bat_dau, R.id.txt_ngay_ket_thuc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_search:
                showProgressBar();
                String fromDate = txtNgayBatDau.getText().toString().trim();
                String toDate = txtNgayKetThuc.getText().toString().trim();
                startIndex = 0;
                if(dataList.size() > 0){
                    dataList.clear();
                }
                historyPointPresenter.getHistoryPoint(tinyDB.getString(getString(R.string.TOKEN_USER)), startIndex, pageSize, fromDate, toDate);
                break;
            case R.id.txt_ngay_bat_dau:
                numberDate =1;
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;

            case R.id.txt_ngay_ket_thuc:
                numberDate =2;
                showDate(mYear, mMonth, mDay, R.style.NumberPickerStyle);
                break;
        }
    }

    private void checkLogin() {
        if(tinyDB.getBoolean(getString(R.string.IS_LOGIN))){
            loChuaDangNhap.setVisibility(View.GONE);
            loDaDangNhap.setVisibility(View.VISIBLE);
        }else {
            loChuaDangNhap.setVisibility(View.VISIBLE);
            loDaDangNhap.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    public void onHistoryPointSuccses(HistoryPointResponse response) {
        hideProgressBar();
        if(response.getData().size() > 0){
            if(response.getData().size() >= pageSize){
                isLoadMore = true;
            }else {
                isLoadMore = false;
            }
            dataList.addAll(response.getData());
            historyPointAdapter.notifyDataSetChanged();

        }else {

            isLoadMore = false;
            historyPointAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onHistoryPointFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onHistoryPointError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(HistoryPointActivity.this)
                .callback(HistoryPointActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }

    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        if(numberDate == 1){
            txtNgayBatDau.setText(simpleDateFormat.format(calendar.getTime()));
            ngayBatDau = simpleDateFormat.format(calendar.getTime());
        } else if(numberDate == 2 ){
            txtNgayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));
            ngayKetThuc = simpleDateFormat.format(calendar.getTime());
        }
    }
}
