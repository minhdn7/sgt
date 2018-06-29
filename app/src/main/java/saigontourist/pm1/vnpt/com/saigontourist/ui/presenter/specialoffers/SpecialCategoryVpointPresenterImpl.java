package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.specialoffers.SpecialOffersInteractor;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.DataCategoryVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SpecialCategoryVpointView;
import timber.log.Timber;

/**
 * Created by linhl on 4/20/2018.
 */

public class SpecialCategoryVpointPresenterImpl implements SpecialCategoryVpointPresenter {
    SpecialCategoryVpointView view;
    private CompositeSubscription subscription;

    @Inject
    SpecialOffersInteractor specialOffersInteractor;

    @Override
    public void setView(SpecialCategoryVpointView view) {
        this.view = view;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {
        subscription.unsubscribe();

    }

    @Override
    public void getListFields() {
        subscription.add(specialOffersInteractor.getListFieldsFromService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategoryVpointResult>() {
                    @Override
                    public void call(DataCategoryVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetListFieldsSuccses(result.getDataListResult());
                        } else {
                            view.onGetListFieldsFailed(result.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListEnterprise() {
        subscription.add(specialOffersInteractor.getListEnterprise()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategoryVpointResult>() {
                    @Override
                    public void call(DataCategoryVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetListEnterpriseSuccses(result.getDataListResult());
                        } else {
                            view.onGetListEnterpriseFailed(result.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListCities() {
        subscription.add(specialOffersInteractor.getListCities(0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataCategoryVpointResult>() {
                    @Override
                    public void call(DataCategoryVpointResult result) {
                        if (result.getStatus() == 200) {
                            view.onGetListCitiesSuccses(result.getDataListResult());
                        } else {
                            view.onGetListCitiesFailed(result.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        Timber.e(e, e.getMessage());
                        view.onGetListError(e);
                    }
                }));
    }
}
