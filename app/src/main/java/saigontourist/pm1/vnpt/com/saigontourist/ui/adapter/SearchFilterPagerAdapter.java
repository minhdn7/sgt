package saigontourist.pm1.vnpt.com.saigontourist.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchFilterSaigonFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.SearchFilterVpointFragment;

/**
 * Created by linhl on 4/16/2018.
 */

public class SearchFilterPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"SaiGonTourist", "Vpoint"};
    Context ctxt = null;

    public SearchFilterPagerAdapter(Context ctxt, FragmentManager fm) {
        super(fm);
        this.ctxt = ctxt;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SearchFilterSaigonFragment();
                break;
            case 1:
                fragment = new SearchFilterVpointFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
