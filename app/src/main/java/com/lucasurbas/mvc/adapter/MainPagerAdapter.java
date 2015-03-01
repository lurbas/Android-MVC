package com.lucasurbas.mvc.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.fragment.FavoritesFragment;
import com.lucasurbas.mvc.fragment.HomeFragment;

/**
 * Created by Lucas on 3/1/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.t_home);

            case 1:
            default:
                return context.getString(R.string.t_favorites);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance();

            case 1:
            default:
                return FavoritesFragment.newInstance();
        }
    }
}
