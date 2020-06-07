package com.wu.mj.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mj.R
import com.wu.mj.databinding.FragmentHomeBinding
import com.wu.mj.module.home.frame.presenter.HomePresenter
import com.wu.mj.module.home.frame.view.HomeView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class HomeFragment : MvpBindingFragment<HomeView, HomePresenter, FragmentHomeBinding>() {

    companion object {
        fun newInstance(): HomeFragment {
            var bandle: Bundle = Bundle()

            var home: HomeFragment = HomeFragment()
            home.arguments = bandle
            return home

        }
    }
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(mvpView!=null)mvpView.initView()
    }
}