package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.app.SaiGonTouristApplication;
import saigontourist.pm1.vnpt.com.saigontourist.app.http.interceptor.AddCookieInterceptor;
import saigontourist.pm1.vnpt.com.saigontourist.app.http.interceptor.ReceiveCookieInterceptor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.CategorySpecialSaigonActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.CategorySpecialVpointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.CheckLoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.DetailSpecialOffersActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.DonatePointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.FAQActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.ForgotPasswordActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.HistoryPointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.HistoryRankActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.InfoEnterpriseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.LoginActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.MainActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.NotificationActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.OTPDonatePointActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.OtpRegisterActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.PolicyActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.RegisterActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.SearchFilterActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.SearchFilterLocationActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.activity.UserInfoActivity;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.SpecialOffersAdapter;

import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.InfoEnterpriseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.InforCardMemberFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ListPromotionEnterpriseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ListShopEnterpriseFragment;

import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangeEmailFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangePassFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangePhoneFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangeUserInfoDetailFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.MenuFragment;

import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.PageSaigonFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.PageVpointFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchFilterSaigonFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchFilterVpointFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchMenuFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SpecialOffersFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.PointFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SubmitOtpFragment;

/**
 * Created by linhl on 4/13/2018.
 */

@Module(
        //Module
        includes = {
                UserModule.class,
                RepositoryModule.class,
                RestAdapterProviderModule.class,
                SpecialOffersModule.class,
                PresenterModule.class,
                PolicyModule.class,
                LocalModule.class
        },
        injects = {
                // app
                SaiGonTouristApplication.class,
                // - view
                BaseActivity.class,
                BaseFragment.class,


                // -- activity
                MainActivity.class,
                RegisterActivity.class,
                LoginActivity.class,
                CheckLoginActivity.class,
                FAQActivity.class,
                HistoryPointActivity.class,
                OtpRegisterActivity.class,
                CategorySpecialSaigonActivity.class,
                CategorySpecialVpointActivity.class,
                DetailSpecialOffersActivity.class,
                InfoEnterpriseActivity.class,
                UserInfoActivity.class,
                SearchFilterActivity.class,

                NotificationActivity.class,
                ForgotPasswordActivity.class,
                DonatePointActivity.class,
                OTPDonatePointActivity.class,
                SearchFilterLocationActivity.class,
                PolicyActivity.class,
                HistoryRankActivity.class,
                // -- fragment
                MenuFragment.class,
                PageSaigonFragment.class,
                PageVpointFragment.class,
                SpecialOffersFragment.class,
                PointFragment.class,
                InfoEnterpriseFragment.class,
                ListPromotionEnterpriseFragment.class,
                ListShopEnterpriseFragment.class,
                ChangeEmailFragment.class,
                ChangePhoneFragment.class,
                ChangePassFragment.class,
                ChangeUserInfoDetailFragment.class,
                SearchMenuFragment.class,
                SearchFilterSaigonFragment.class,
                SearchFilterVpointFragment.class,
                SubmitOtpFragment.class,
                InforCardMemberFragment.class,
                // -- adapter
                SpecialOffersAdapter.class,
                // -- dialog
        },
        library = true
)
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.context;
    }

    @Provides
    public AddCookieInterceptor provideAddCookieInterceptor(LoginUserCookies loginUserCookies) {
        return new AddCookieInterceptor(loginUserCookies);
    }

    @Provides
    public ReceiveCookieInterceptor provideReceiveCookieInterceptor(LoginUserCookies loginUserCookies) {
        return new ReceiveCookieInterceptor(loginUserCookies);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(AddCookieInterceptor addCookieInterceptor,
                                            ReceiveCookieInterceptor receiveCookieInterceptor) {

        return new OkHttpClient.Builder()
                .connectTimeout(TimeUnit.MINUTES.toMillis(5L), TimeUnit.MILLISECONDS)
                .readTimeout(TimeUnit.MINUTES.toMillis(10L), TimeUnit.MILLISECONDS)
                .addInterceptor(addCookieInterceptor)
                .addInterceptor(receiveCookieInterceptor)
                .build();
    }

}
