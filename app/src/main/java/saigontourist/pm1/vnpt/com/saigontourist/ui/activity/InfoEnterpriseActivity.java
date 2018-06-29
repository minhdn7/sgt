package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.InfoEnterpriseSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.InfoEnterpriseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ListPromotionEnterpriseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ListShopEnterpriseFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.InforEnterprisePresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.InforEnterpriseView;
import timber.log.Timber;

public class InfoEnterpriseActivity extends BaseActivity implements InforEnterpriseView {
    @BindView(R.id.imageView)
    ImageView imageView;
    @Inject
    InforEnterprisePresenter inforPresenter;
    @Inject
    LoginUserCookies loginUserCookies;
    private int idEnterprise = 0;
    private GlideUrl glideUrl;
    private static final String TAG = InfoEnterpriseActivity.class.getSimpleName();
    private InfoEnterpriseResult infoEnterpriseResponse;

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_enterprise);
        setTitleToobar("Thông tin doanh nghiệp");
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        changeTabsFont(InfoEnterpriseActivity.this, tabLayout);
        inforPresenter.setView(this);
        inforPresenter.onViewCreate();

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new InfoEnterpriseFragment(), "Giới thiệu");
        adapter.addFragment(new ListPromotionEnterpriseFragment(), "Ưu đãi hiện có");
        adapter.addFragment(new ListShopEnterpriseFragment(), "DS cửa hàng");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
    }

    public void changeTabsFont(Context context, TabLayout tabLayout) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/SFUFuturaBook.TTF"));
                }
            }
        }
    }

    @Override
    public void onGetInfoEnterpriseVpointSuccses(InfoEnterpriseResult dataResult) {
        hideProgressBar();
        infoEnterpriseResponse = dataResult;
        if (infoEnterpriseResponse.getInfoEnterpriseModel() != null) {
            String urlImage = getString(R.string.api_base_url_vpoint) + StringUtils.GET_IMAGE_NEWS_URL +
                    infoEnterpriseResponse.getInfoEnterpriseModel().getAvatar();
            HashSet<String> cookies = loginUserCookies.get();
            StringBuilder sb = new StringBuilder();
            for (String cookie : cookies) {
                sb.append(cookie).append("; ");
            }

            if (sb.length() > 0) {
                Timber.tag("Cookies").d("sent: %s", sb.toString());
                glideUrl = new GlideUrl(urlImage, new LazyHeaders.Builder()
                        .addHeader(StringUtils.COOKIE, sb.toString())
                        .build());
            }

            Glide.with(InfoEnterpriseActivity.this)
                    .load(glideUrl)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);
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
            String urlAnh = getString(R.string.api_base_url_saigon_image)+ dataResult.getData().getAnhDaiDien();
            Glide.with(InfoEnterpriseActivity.this)
                    .load(urlAnh)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inforPresenter.onViewDestroy();
    }
}
