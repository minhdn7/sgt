package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.DonatePointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.FAQActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.HistoryPointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.HistoryRankActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.LoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.PolicyActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.RegisterActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.UserInfoActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.event.UserInfoEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends BaseFragment implements GetUserInfoView {

    Unbinder unbinder;
    @BindView(R.id.txXinchao)
    TextView txXinchao;
    @BindView(R.id.btnLogin)
    TextView btnLogin;
    @BindView(R.id.tvDangKy)
    TextView tvDangKy;
    @BindView(R.id.loChuaDangNhap)
    LinearLayout loChuaDangNhap;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtMemNo)
    TextView txtMemNo;
    @BindView(R.id.tvMemberNo)
    TextView tvMemberNo;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.loXemHoSoCaNhan)
    LinearLayout loXemHoSoCaNhan;
    @BindView(R.id.loDangNhap)
    LinearLayout loDangNhap;
    @BindView(R.id.font_tangdiem)
    TextView fontTangdiem;
    @BindView(R.id.loTangDiem)
    LinearLayout loTangDiem;
    @BindView(R.id.font_lichsudiem)
    TextView fontLichsudiem;
    @BindView(R.id.loLichSuDiem)
    LinearLayout loLichSuDiem;
    @BindView(R.id.font_moibanbe)
    TextView fontMoibanbe;
    @BindView(R.id.loMoiBanBe)
    LinearLayout loMoiBanBe;
    @BindView(R.id.font_nhantinnhan)
    TextView fontNhantinnhan;
    @BindView(R.id.loNhanTin)
    LinearLayout loNhanTin;
    @BindView(R.id.font_trogiup)
    TextView fontTrogiup;
    @BindView(R.id.loTroGiup)
    LinearLayout loTroGiup;
    @BindView(R.id.font_faq)
    TextView fontFaq;
    @BindView(R.id.loFAQ)
    LinearLayout loFAQ;
    @BindView(R.id.font_huytaikhoan)
    TextView fontHuytaikhoan;
    @BindView(R.id.loHuyTaiKhoan)
    LinearLayout loHuyTaiKhoan;
    @BindView(R.id.thanhtoan_text)
    TextView thanhtoanText;
    @BindView(R.id.lo_thanhtoan_vnpt_pay)
    LinearLayout loThanhtoanVnptPay;
    @BindView(R.id.font_thoat)
    TextView fontThoat;
    @BindView(R.id.loThoat)
    LinearLayout loThoat;

    @Inject
    GetUserInfoPresenter getUserInfoPresenter;
    @BindView(R.id.font_lichsuhang)
    TextView fontLichsuhang;
    @BindView(R.id.loLichSuHang)
    LinearLayout loLichSuHang;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        checkLogin();
        initControls();
        initView();
        return view;
    }

    private void initView() {
        getUserInfoPresenter.setView(this);
        getUserInfoPresenter.onViewCreate();
        try {
            if (tinyDB.getBoolean(getString(R.string.IS_LOGIN)) && tinyDB.getString(getString(R.string.TOKEN_USER)) != null && tinyDB.getString(getString(R.string.DEVICE_ID)) != null) {
                showProgressBar();
                getUserInfoPresenter.getUserInfo(tinyDB.getString(getString(R.string.TOKEN_USER)), tinyDB.getString(getString(R.string.DEVICE_ID)));
            } else {
                tinyDB.putBoolean(getString(R.string.IS_LOGIN), false);
                checkLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
            tinyDB.putBoolean(getString(R.string.IS_LOGIN), false);
            checkLogin();
        }

    }

    private void initControls() {
        //set fonts
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaBook.TTF");
        fontTangdiem.setTypeface(face);
        fontLichsudiem.setTypeface(face);
        fontLichsuhang.setTypeface(face);
        fontMoibanbe.setTypeface(face);
        fontNhantinnhan.setTypeface(face);
        fontTrogiup.setTypeface(face);
        fontFaq.setTypeface(face);
        fontHuytaikhoan.setTypeface(face);
        fontThoat.setTypeface(face);
        btnLogin.setTypeface(face);
        tvDangKy.setTypeface(face);
        text.setTypeface(face);
        txtName.setTypeface(face);
        txXinchao.setTypeface(face);
        tvMemberNo.setTypeface(face);
        txtMemNo.setTypeface(face);
//        font_lpoint.setTypeface(face);
        thanhtoanText.setTypeface(face);
    }

    private void checkLogin() {
        if (tinyDB.getBoolean(getString(R.string.IS_LOGIN))) {
            loChuaDangNhap.setVisibility(View.GONE);
            loDangNhap.setVisibility(View.VISIBLE);
            loThoat.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            loLichSuHang.setVisibility(View.VISIBLE);
            try {
                UserInfoResponse userInfoResponse = EventBus.getDefault().getStickyEvent(UserInfoEvent.class).getUserInfoResponse();
                String maHoiVien = getString(R.string.str_memberNo) + ": " + userInfoResponse.getData().getThongtin().get(0).getMaHoiVien();
                txtName.setText(userInfoResponse.getData().getThongtin().get(0).getTenHoiVien());
                txtMemNo.setText(maHoiVien);

                if (userInfoResponse.getData().getThongtin().get(0).getAnhDaiDien() != null) {
                    String urlImage = getString(R.string.api_base_url_saigon_tourist) + "/" + userInfoResponse.getData().getThongtin().get(0).getAnhDaiDien();
                    Glide.with(this)
                            .load(urlImage)
                            .into(imageView);
                } else {
                    imageView.setImageResource(R.drawable.ic_avatar);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loChuaDangNhap.setVisibility(View.VISIBLE);
            loDangNhap.setVisibility(View.GONE);
            loThoat.setVisibility(View.GONE);
            loLichSuHang.setVisibility(View.GONE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnLogin, R.id.tvDangKy, R.id.loChuaDangNhap, R.id.loXemHoSoCaNhan, R.id.loDangNhap, R.id.loTangDiem, R.id.loLichSuDiem, R.id.loMoiBanBe, R.id.loNhanTin, R.id.loTroGiup, R.id.loHuyTaiKhoan, R.id.lo_thanhtoan_vnpt_pay, R.id.loThoat,
            R.id.loFAQ, R.id.loLichSuHang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDangKy:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.btnLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.loChuaDangNhap:
                break;
            case R.id.loXemHoSoCaNhan:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.loDangNhap:
                break;
            case R.id.loTangDiem:
                startActivity(new Intent(getActivity(), DonatePointActivity.class));
                break;
            case R.id.loLichSuDiem:
                startActivity(new Intent(getActivity(), HistoryPointActivity.class));
                break;
            case R.id.loLichSuHang:
                startActivity(new Intent(getActivity(), HistoryRankActivity.class));
                break;
            case R.id.loMoiBanBe:
                break;
            case R.id.loNhanTin:
                break;
            case R.id.loTroGiup:
                startActivity(new Intent(getActivity(), PolicyActivity.class));
                break;
            case R.id.loHuyTaiKhoan:
                break;
            case R.id.loFAQ:
                startActivity(new Intent(getActivity(), FAQActivity.class));
                break;
            case R.id.loThoat:
                showDialogLogout();
                break;
        }
    }

    private void showDialogLogout() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View deleteDialogView = factory.inflate(R.layout.custom_dialog_logout, null);
        final AlertDialog yesDialog = new AlertDialog.Builder(getActivity()).create();
        yesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        yesDialog.setView(deleteDialogView);
        TextView text = deleteDialogView.findViewById(R.id.text);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        deleteDialogView.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppDef.IS_LOGIN = false;
                tinyDB.clear();
                yesDialog.dismiss();
                checkLogin();
            }
        });
        deleteDialogView.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
            }
        });

        yesDialog.show();
    }

    @Override
    public void onGetUserInfoSuccses(UserInfoResponse response) {
        hideProgressBar();
        UserInfoEvent userInfoEvent = new UserInfoEvent(response);
        EventBus.getDefault().postSticky(userInfoEvent);
        tinyDB.putString(getString(R.string.USER_PHONE), response.getData().getThongtin().get(0).getSoDienThoai());
        tinyDB.putString(getString(R.string.USER_NAME), response.getData().getThongtin().get(0).getTenDangNhap());
        tinyDB.putBoolean(getString(R.string.IS_LOGIN), true);
        checkLogin();
    }

    @Override
    public void onGetUserInfoFailed(String message) {
        hideProgressBar();
        tinyDB.putBoolean(getString(R.string.IS_LOGIN), false);
        checkLogin();
    }

    @Override
    public void onGetUserInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
        tinyDB.putBoolean(getString(R.string.IS_LOGIN), false);
        checkLogin();
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
}
