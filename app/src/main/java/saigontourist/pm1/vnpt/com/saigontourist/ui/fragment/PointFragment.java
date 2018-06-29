package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.AccountInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.HistoryRankActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.LoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.HousePointAdapter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.AccountInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.AccountInfoView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PointFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PointFragment extends BaseFragment implements AccountInfoView {


    RelativeLayout relativeMainDiem;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imgLoading)
    LinearLayout imgLoading;
    @BindView(R.id.title_tool_bar)
    TextView titleToolBar;
    @BindView(R.id.tv_null)
    TextView tvNull;
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.lo_da_dang_nhap)
    LinearLayout loDaDangNhap;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tv_gioithieu)
    TextView tvGioithieu;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.lo_chua_dang_nhap)
    LinearLayout loChuaDangNhap;

    LinearLayout loToolbar;

    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.tv_label_now_point)
    TextView tvLabelNowPoint;
    @BindView(R.id.tv_now_point)
    TextView tvNowPoint;
    @BindView(R.id.tv_label_rank_point)
    TextView tvLabelRankPoint;
    @BindView(R.id.tv_rank_point)
    TextView tvRankPoint;
    @BindView(R.id.tv_label_point)
    TextView tvLabelPoint;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.tv_label_period_point)
    TextView tvLabelPeriodPoint;
    @BindView(R.id.tv_period_point)
    TextView tvPeriodPoint;
    @BindView(R.id.tv_label_check_rank_point)
    TextView tvLabelCheckRankPoint;
    @BindView(R.id.tv_check_rank_point)
    TextView tvCheckRankPoint;
    @BindView(R.id.tv_label_condition_maintain_point)
    TextView tvLabelConditionMaintainPoint;
    @BindView(R.id.tv_condition_maintain_point)
    TextView tvConditionMaintainPoint;
    @BindView(R.id.tv_label_condition_update_point)
    TextView tvLabelConditionUpdatePoint;
    @BindView(R.id.tv_condition_update_point)
    TextView tvConditionUpdatePoint;
    @BindView(R.id.tv_label_date_check)
    TextView tvLabelDateCheck;
    @BindView(R.id.tv_date_check)
    TextView tvDateCheck;
    @BindView(R.id.tv_type_point)
    TextView tvTypePoint;
    @BindView(R.id.img_card_view)
    ImageView imgCardView;


    private OnFragmentInteractionListener mListener;
    private List<AccountInfoResponse.ThongTinDiem> housePointModelList = new ArrayList<>();


    @Inject
    AccountInfoPresenter accountInfoPresenter;

    public PointFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PointFragment newInstance(String param1, String param2) {
        PointFragment fragment = new PointFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_point, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initControls();
        checkLogin();
        return view;
    }


    private void initControls() {
        //set fonts
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaBook.TTF");
        tvGioithieu.setTypeface(face);
        titleToolBar.setTypeface(face);
        text1.setTypeface(face);
        tvLabelNowPoint.setTypeface(face);

        tvLabelConditionMaintainPoint.setTypeface(face);
        tvConditionMaintainPoint.setTypeface(face);
        tvLabelConditionUpdatePoint.setTypeface(face);
        tvLabelDateCheck.setTypeface(face);
        tvLabelPeriodPoint.setTypeface(face);
        tvPeriodPoint.setTypeface(face);
        tvLabelCheckRankPoint.setTypeface(face);
        tvCheckRankPoint.setTypeface(face);

        Typeface FACE = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaHeavy.TTF");
        text2.setTypeface(FACE);
        text3.setTypeface(FACE);
        text4.setTypeface(FACE);
        tvTypePoint.setTypeface(FACE);

        tvRankPoint.setTypeface(FACE);
        tvPoint.setTypeface(FACE);
        tvConditionMaintainPoint.setTypeface(FACE);
        tvConditionUpdatePoint.setTypeface(FACE);
        tvPeriodPoint.setTypeface(FACE);
        tvCheckRankPoint.setTypeface(FACE);
        tvDateCheck.setTypeface(FACE);
    }

    private void initView() {
        accountInfoPresenter.setView(this);
        accountInfoPresenter.onViewCreate();
        housePointModelList = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnLogin, R.id.tv_rank_point})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rank_point:
                startActivity(new Intent(getActivity(), HistoryRankActivity.class));
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }

    }


    private void displayHousePoint() {
        if (housePointModelList.size() > 0) {
            HousePointAdapter housePointAdapter = new HousePointAdapter(getActivity(), housePointModelList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            rcvDanhSach.setLayoutManager(mLayoutManager);
            rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
            rcvDanhSach.setAdapter(housePointAdapter);

        } else {
            rcvDanhSach.setVisibility(View.GONE);
            tvNull.setVisibility(View.VISIBLE);
            tvNull.setText("Không có dữ liệu");
        }
    }

    @Override
    public void onAccountInforSuccess(AccountInfoResponse response) {
        hideProgressBar();
        if (housePointModelList.size() > 0) {
            housePointModelList.clear();
        }

        // TODO: fill data tài khoản
        if (AppUtil.checkNull(response.getData().getAnhDaiDien())) {
            String urlImage = getString(R.string.api_base_url_saigon_image) + response.getData().getAnhDaiDien();

            Glide.with(this)
                    .load(urlImage)
                    .apply(new RequestOptions().signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
                                                .error(R.mipmap.ic_card_demo))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imgCardView);
        }

        if (AppUtil.checkNull(response.getData().getTenHangHoiVien())) {
            tvRankPoint.setText(response.getData().getTenHangHoiVien());
        }
        if (AppUtil.checkNull(response.getData().getDiemtichluy())) {
            tvPoint.setText(String.valueOf(response.getData().getDiemVpoint()));
        }
        String thoiHanSuDung = response.getData().getTuNgay() + " - " + response.getData().getDenNgay();
        tvPeriodPoint.setText(thoiHanSuDung);

        if (AppUtil.checkNull(response.getData().getDiemtichluy())) {
            tvCheckRankPoint.setText(String.valueOf(response.getData().getDiemtichluy()));
        }

        if (AppUtil.checkNull(response.getData().getDieuKienDuyTriHang())) {
            tvConditionMaintainPoint.setText(response.getData().getDieuKienDuyTriHang());
        }

        if (AppUtil.checkNull(response.getData().getDiemconthieu())) {
            tvConditionUpdatePoint.setText(response.getData().getDiemconthieu());
        }

//        if(AppUtil.checkNull(response.getData().getThoiGianXetHang())){
//            tvDateCheck.setText(response.getData().getThoiGianXetHang());
//        }
        String kyXetHang = response.getData().getKyTu() + " - " + response.getData().getKyDen();
        tvDateCheck.setText(kyXetHang);

        try {
            if (response.getData().getThongTinDiem() != null && response.getData().getThongTinDiem().size() > 0) {
                housePointModelList.addAll(response.getData().getThongTinDiem());
                displayHousePoint();
            } else {

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccountInfoFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onAccountInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    private void checkLogin() {
        if (tinyDB.getBoolean(getString(R.string.IS_LOGIN))) {
            loChuaDangNhap.setVisibility(View.GONE);
            loDaDangNhap.setVisibility(View.VISIBLE);
            showProgressBar();
            accountInfoPresenter.getAccountInfo(tinyDB.getString(getString(R.string.TOKEN_USER)));

        } else {
            loChuaDangNhap.setVisibility(View.VISIBLE);
            loDaDangNhap.setVisibility(View.GONE);
        }
    }
}
