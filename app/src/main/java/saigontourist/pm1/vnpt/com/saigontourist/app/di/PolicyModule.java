package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.policy.PolicyInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.policy.PolicyInteractorImpl;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.PolicyApi;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.FAQPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.FAQPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.PolicyPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.policy.PolicyPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.LoginUserPresenterImpl;

/**
 * Created by MinhDN on 23/4/2018.
 */
@Module(complete = false, library = true)
public class PolicyModule {
    @Provides
    PolicyInteractor providePolicyInteractor(PolicyInteractorImpl policyInteractor) {
        return policyInteractor;
    }

    @Provides
    PolicyApi providePolicyApi(@Named("server_saigon") Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(PolicyApi.class);
    }

    @Provides
    FAQPresenter faqPresenter(FAQPresenterImpl faqPresenter) {
        return faqPresenter;
    }


    @Provides
    PolicyPresenter policyPresenter(PolicyPresenterImpl policyPresenter) {
        return policyPresenter;
    }
}
