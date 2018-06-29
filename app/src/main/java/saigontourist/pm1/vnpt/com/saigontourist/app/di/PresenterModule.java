package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import dagger.Module;
import dagger.Provides;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.membercard.MemberCardPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.membercard.MemberCardPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.InforEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.InforEnterprisePresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.PromotionEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.PromotionEnterprisePresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SearchDataMapPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SearchDataMapPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.ShopEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.ShopEnterprisePresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SpecialOffersPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetTokenDevPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetTokenDevPresenterImpl;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.RegisterUserPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.RegisterUserPresenterImpl;

/**
 * Created by linhl on 4/18/2018.
 */
@Module(complete = false, library = true)
public class PresenterModule {
    @Provides
    public GetTokenDevPresenter provideEventLogPresenter(GetTokenDevPresenterImpl impl) {
        return impl;
    }

    @Provides
    public RegisterUserPresenter provideStartPresenter(RegisterUserPresenterImpl impl) {
        return impl;
    }

    @Provides
    public ShopEnterprisePresenter provideShopEnterprisePresenter(ShopEnterprisePresenterImpl impl) {
        return impl;
    }

    @Provides
    public InforEnterprisePresenter provideInforEnterprisePresenter(InforEnterprisePresenterImpl impl) {
        return impl;
    }

    @Provides
    public PromotionEnterprisePresenter providePromotionEnterprisePresenter(PromotionEnterprisePresenterImpl impl) {
        return impl;
    }

    @Provides
    public SearchDataMapPresenter providePromotionEnterprisePresenter(SearchDataMapPresenterImpl impl) {
        return impl;
    }

    @Provides
    public MemberCardPresenter provideMemberCardPresenter(MemberCardPresenterImpl impl) {
        return impl;
    }
}
