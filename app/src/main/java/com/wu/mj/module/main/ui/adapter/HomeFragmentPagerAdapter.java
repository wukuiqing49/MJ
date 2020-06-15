package com.wu.mj.module.main.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wu.mj.module.home.ui.fragment.ExamAnnouncementFragment;
import com.wu.mj.module.home.ui.fragment.HomeFragment;
import com.wu.mj.module.home.ui.fragment.InformationFragment;
import com.wu.mj.module.mine.ui.fragment.MineFragment;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public HomeFragmentPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {

            case 0:
                fragment = HomeFragment.Companion.newInstance();
                break;
            case 1:
                fragment = InformationFragment.Companion.newInstance();
                break;
            case 2:
                fragment = ExamAnnouncementFragment.Companion.newInstance();
                break;
            case 3:
                fragment = MineFragment.Companion.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
