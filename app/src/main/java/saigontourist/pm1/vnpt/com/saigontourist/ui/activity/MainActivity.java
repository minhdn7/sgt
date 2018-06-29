package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessaging;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.AppState;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.InforCardMemberFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.MenuFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.PointFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchMenuFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SpecialOffersFragment;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import android.provider.Settings.Secure;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @BindView(R.id.space)
    SpaceNavigationView spaceNavigationView;
    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    SpecialOffersFragment specialOffersFragment;
    MenuFragment menuFragment;
    PointFragment pointFragment;
    SearchMenuFragment searchMenuFragment;
    InforCardMemberFragment inforCardMemberFragment;


    private String registerEvent = "";
    //    private  MenuFragment menuFragment = new MenuFragment();
    @Inject
    AppState appState;

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        // register firebase topic
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        // end
        registerEvent = getIntent().getStringExtra("RegisterEvent");
        //Remove login state
        if (registerEvent != null && registerEvent.equals("RegisterEvent")) {

        } else {
            appState.removeValue(AppState.PREF_KEY_STATUS_LOGIN_USER);
            appState.removeValue(AppState.PREF_KEY_TOKEN_LOGIN_USER);
            spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

            tinyDB.remove(getString(R.string.IS_LOGIN));
            tinyDB.remove(getString(R.string.TOKEN_USER));
//            tinyDB.clear();
        }
        initView();
        adListener();
        addControls();
        replaceFramgment(specialOffersFragment);
    }

    private void addControls() {
//        AppDef.DEVICE_ID = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        tinyDB.putString(getString(R.string.DEVICE_ID), Secure.getString(this.getContentResolver(), Secure.ANDROID_ID));
    }

    private void initView() {
        spaceNavigationView.setCentreButtonSelectable(true);
        spaceNavigationView.addSpaceItem(new SpaceItem("Ưu đãi", R.mipmap.icon_uu_dai));
        spaceNavigationView.addSpaceItem(new SpaceItem("Tài khoản", R.mipmap.icon_diem));
        spaceNavigationView.addSpaceItem(new SpaceItem("Tìm kiếm", R.mipmap.icon_tim_kiem));
        spaceNavigationView.addSpaceItem(new SpaceItem("Menu", R.mipmap.icon_menu));
        spaceNavigationView.setCentreButtonIcon(R.mipmap.navbar_matichdiem);
        specialOffersFragment = new SpecialOffersFragment();
        menuFragment = new MenuFragment();
        pointFragment = new PointFragment();
        searchMenuFragment = new SearchMenuFragment();
        inforCardMemberFragment = new InforCardMemberFragment();
    }

    private void adListener() {
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                replaceFramgment(inforCardMemberFragment);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
//                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                switch (itemName) {
                    case "Menu":
                        replaceFramgment(menuFragment);
                        break;
                    case "Tìm kiếm":
                        replaceFramgment(searchMenuFragment);
                        break;
                    case "Tài khoản":
                        replaceFramgment(pointFragment);
                        break;
                    case "Ưu đãi":
                        replaceFramgment(specialOffersFragment);
                        break;
                }


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
//                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void replaceFramgment(Fragment fragment) {
        FragmentTransaction transList = getSupportFragmentManager()
                .beginTransaction();
        transList.replace(R.id.frame_content, fragment);
        transList.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.ReplaceFragmentMessage event) {
        if (event != null) {
            replaceFramgment(specialOffersFragment);
        }
    }
}
