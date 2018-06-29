package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractorImpl;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.SpecialOffersSaigonApi;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.SpecialOffersVpointApi;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.DetailSpecialOffersPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.DetailSpecialOffersPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategorySaigonPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategoryVpointPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategorySaigonPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialCategoryVpointPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenterImpl;

/**
 * Created by linhl on 4/18/2018.
 */
@Module(complete = false, library = true)
public class SpecialOffersModule {

    @Provides
    SpecialOffersInteractor providesSpecialOffersInteractor(SpecialOffersInteractorImpl specialOffersInteractor) {
        return specialOffersInteractor;
    }

    @Provides
    SpecialOffersSaigonApi provideSpecialOffersApi(@Named("server_saigon") Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(SpecialOffersSaigonApi.class);
    }

    @Provides
    SpecialOffersVpointApi provideSpecialOffersVpointApi(@Named("server_vpoint") Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(SpecialOffersVpointApi.class);
    }

    @Provides
    SpecialOffersPresenter specialOffersPresenter(SpecialOffersPresenterImpl specialOffersPresenter) {
        return specialOffersPresenter;
    }

    @Provides
    SpecialCategoryVpointPresenter specialOffersPresenter(SpecialCategoryVpointPresenterImpl specialOffersPresenter) {
        return specialOffersPresenter;
    }

    @Provides
    SpecialCategorySaigonPresenter specialOffersPresenter(SpecialCategorySaigonPresenterImpl specialOffersPresenter) {
        return specialOffersPresenter;
    }

    @Provides
    DetailSpecialOffersPresenter provideDetailSpecialOffersPresenter(DetailSpecialOffersPresenterImpl specialOffersPresenter) {
        return specialOffersPresenter;
    }
}
