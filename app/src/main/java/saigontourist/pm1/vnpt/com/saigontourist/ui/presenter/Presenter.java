package saigontourist.pm1.vnpt.com.saigontourist.ui.presenter;


import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

public interface Presenter<T extends View> {

    void setView(T view);

    void onViewCreate();

    void onViewStart();

    void onViewResume();

    void onViewPause();

    void onViewStop();

    void onViewDestroy();

}