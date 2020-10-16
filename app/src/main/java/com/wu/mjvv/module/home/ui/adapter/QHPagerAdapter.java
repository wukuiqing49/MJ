package com.wu.mjvv.module.home.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wu.mjvv.module.home.ui.fragment.InformationListFragment;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class QHPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public QHPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = InformationListFragment.Companion.newInstance("考试动态");
                break;
            case 1:
                fragment = InformationListFragment.Companion.newInstance("考试大纲");
                break;
            case 2:
                fragment = InformationListFragment.Companion.newInstance("辅导资料");
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
