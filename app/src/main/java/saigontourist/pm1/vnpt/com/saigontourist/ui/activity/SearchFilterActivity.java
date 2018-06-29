package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.adapter.SearchFilterPagerAdapter;

public class SearchFilterActivity extends BaseActivity {
    @BindView(R.id.tvSearch)
    EditText tvSearch;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabsStrip;
    @BindView(R.id.viewPager)
    ViewPager pager;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private String strSearch = "";

    private enum StateFragment {
        SaigonTouris, Vpoint
    }

    private StateFragment state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        initView();

    }

    private void initView() {
        state = StateFragment.SaigonTouris;
        tabsStrip.setShouldExpand(true);
        tabsStrip.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        pager.setAdapter(buildAdapter());
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(pager);
        addListener();
    }

    private void addListener() {
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    strSearch = s.toString().trim();
                } else {
                    strSearch = "";
                }
            }
        });
        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    startActivity(new Intent(TimKiemFilterActivity.this, TimKiemsActivity.class));
//                    finish();

                    sendEventToSearch();
                    return true;
                }
                return false;
            }
        });
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    state = StateFragment.SaigonTouris;
                } else {
                    state = StateFragment.Vpoint;
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }

    @OnClick({R.id.imageView, R.id.iv_back})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                sendEventToSearch();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }

    }

    private void sendEventToSearch() {
        if (isTabSaigon()) {
            EventBus.getDefault().postSticky(new MessageEvent.StringSearchFilter(strSearch, 0));
        } else {
            EventBus.getDefault().postSticky(new MessageEvent.StringSearchFilter(strSearch, 1));
        }
    }

    private PagerAdapter buildAdapter() {
        return (new SearchFilterPagerAdapter(this, getSupportFragmentManager()));
    }

    private boolean isTabSaigon() {
        switch (state) {
            case SaigonTouris:
                return true;
            case Vpoint:
                return false;
            default:
                return true;
        }
    }
}
