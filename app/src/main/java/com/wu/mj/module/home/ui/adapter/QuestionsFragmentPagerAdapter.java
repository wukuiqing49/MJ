package com.wu.mj.module.home.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wu.mj.module.home.frame.model.QuestionInfo;
import com.wu.mj.module.home.ui.fragment.HomeFragment;
import com.wu.mj.module.home.ui.fragment.QuestionsDetailFragment;
import com.wu.mj.module.mine.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class QuestionsFragmentPagerAdapter extends FragmentPagerAdapter {

    Context context;
    List<QuestionInfo> mFragents = new ArrayList<>();
    String title;

    public QuestionsFragmentPagerAdapter(Context context, @NonNull FragmentManager fm, String title, List<QuestionInfo> list) {
        super(fm);
        this.context = context;
        this.title = title;
        mFragents=list;
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {

        Fragment fragment = QuestionsDetailFragment.Companion.newInstance(title, mFragents.get(position));


        return fragment;
    }

    @Override
    public int getCount() {
        return mFragents.size();
    }
}
