package com.wu.mj.module.mine.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mj.R
import com.wu.mj.databinding.FragmentMineBinding

import com.wu.mj.module.mine.frame.presenter.MinePresenter
import com.wu.mj.module.mine.frame.view.MineView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/25
 *
 * 用途:
 */


class MineFragment : MvpBindingFragment<MineView, MinePresenter, FragmentMineBinding>() {
    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(mvpView!=null)mvpView.initView()

    }

    override fun onResume() {
        super.onResume()
        if(presenter!=null)presenter.getLX(activity as Context)
        if(presenter!=null)presenter.getHistoryData(activity as Context)
        if(presenter!=null)presenter.getSIMULATIONData(activity as Context)
        if(presenter!=null)presenter.getTotal(activity as Context)
    }
}