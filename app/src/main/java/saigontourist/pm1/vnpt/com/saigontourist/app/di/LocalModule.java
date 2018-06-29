package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.local.LocalInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.local.LocalInteractorImpl;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.api.LocalApi;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.DistrictPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.DistrictPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.ProvincePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.ProvincePresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.VillagePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.local.VillagePresenterImpl;

/**
 * Created by MinhDN on 4/5/2018.
 */
@Module(complete = false, library = true)
public class LocalModule {
    @Provides
    LocalInteractor localInteractor(LocalInteractorImpl localInteractor) {
        return localInteractor;
    }

    @Provides
    LocalApi provideLocalApi(@Named("server_saigon") Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(LocalApi.class);
    }

    @Provides
    ProvincePresenter provincePresenter(ProvincePresenterImpl provincePresenter) {
        return provincePresenter;
    }

    @Provides
    DistrictPresenter districtPresenter(DistrictPresenterImpl districtPresenter) {
        return districtPresenter;
    }

    @Provides
    VillagePresenter villagePresenter(VillagePresenterImpl villagePresenter) {
        return villagePresenter;
    }
}
