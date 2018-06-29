package saigontourist.pm1.vnpt.com.saigontourist.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.InforEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.InforEnterpriseView;

public class InfoEnterpriseFragment extends BaseFragment implements InforEnterpriseView {

    private InfoEnterpriseResult infoEnterpriseResponse;
    private static final String TAG = InfoEnterpriseFragment.class.getSimpleName();
    @BindView(R.id.item_mo_ta)
    WebView itemMoTa;
    @BindView(R.id.lo_null)
    TextView loNull;

    @Inject
    InforEnterprisePresenter inforPresenter;

    private int idEnterprise = 0;

    public InfoEnterpriseFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_enterprise, container, false);
        ButterKnife.bind(this, view);
        inforPresenter.setView(this);
        inforPresenter.onViewCreate();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void showInforEnterprise() {
        if (infoEnterpriseResponse.getInfoEnterpriseModel() != null) {
            InfoEnterpriseResult.InfoEnterpriseModel infoEnterpriseModel = infoEnterpriseResponse.getInfoEnterpriseModel();
            if (infoEnterpriseModel.getDescription() != null ||
                    !TextUtils.isEmpty(infoEnterpriseModel.getDescription())) {
                itemMoTa.loadDataWithBaseURL("", StringUtils.START_HTML + infoEnterpriseModel.getDescription() + StringUtils.END_HTML, "text/html", "UTF-8", "");
            } else {
                itemMoTa.setVisibility(View.GONE);
                loNull.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showInforEnterpriseSaigon(InfoEnterpriseSaigonResult dataResult) {
        if (dataResult != null) {
            InfoEnterpriseSaigonResult.Data infoEnterpriseModel = dataResult.getData();
            if (infoEnterpriseModel.getMoTa() != null ||
                    !TextUtils.isEmpty(infoEnterpriseModel.getMoTa())) {
                itemMoTa.loadDataWithBaseURL("", StringUtils.START_HTML + infoEnterpriseModel.getMoTa() + StringUtils.END_HTML,
                        "text/html", "UTF-8", "");
            } else {
                itemMoTa.setVisibility(View.GONE);
                loNull.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onGetInfoEnterpriseVpointSuccses(InfoEnterpriseResult dataResult) {
        hideProgressBar();
        infoEnterpriseResponse = dataResult;
        if (infoEnterpriseResponse.getInfoEnterpriseModel() != null) {
            itemMoTa.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            showInforEnterprise();
        } else {
            itemMoTa.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetInfoEnterpriseVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetInfoEnterpriseSaigonSuccses(InfoEnterpriseSaigonResult dataResult) {
        hideProgressBar();
        if (dataResult != null) {
            itemMoTa.setVisibility(View.VISIBLE);
            loNull.setVisibility(View.GONE);
            showInforEnterpriseSaigon(dataResult);
        } else {
            itemMoTa.setVisibility(View.GONE);
            loNull.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onGetInfoEnterpriseSaigonFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MessageIdDetailAtivity event) {
        /* Do something */
        if (event != null) {
            idEnterprise = event.getIdEnterprise();
            switch (event.getTypeDetail()) {
                case 0:
                    //SaigonTouris
                    showProgressBar();
                    inforPresenter.getDetailInfoEnterpriseSaigon(idEnterprise);
                    break;
                case 1:
                    //Vpoint
                    showProgressBar();
                    inforPresenter.getDetailInfoEnterpriseVpoint(idEnterprise);
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        inforPresenter.onViewDestroy();
        EventBus.getDefault().unregister(this);
        hideProgressBar();
    }
}
