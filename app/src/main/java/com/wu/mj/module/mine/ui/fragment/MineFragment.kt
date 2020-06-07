package com.wu.mj.module.mine.ui.fragment

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
}