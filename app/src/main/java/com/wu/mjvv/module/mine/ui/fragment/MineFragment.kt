package com.wu.mjvv.module.mine.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mjvv.R
import com.wu.mjvv.databinding.FragmentMineBinding

import com.wu.mjvv.module.mine.frame.presenter.MinePresenter
import com.wu.mjvv.module.mine.frame.view.MineView
import com.wu.mjvv.utlis.UserObservable
import java.util.*

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/25
 *
 * 用途:
 */


class MineFragment : MvpBindingFragment<MineView, MinePresenter, FragmentMineBinding>(),Observer {

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
        UserObservable.addObserver(this)

    }

    override fun onResume() {
        super.onResume()
        if(presenter!=null)presenter.getLX(activity as Context)
        if(presenter!=null)presenter.getHistoryData(activity as Context)
        if(presenter!=null)presenter.getSIMULATIONData(activity as Context)
        if(presenter!=null)presenter.getTotal(activity as Context)
    }

    override fun onDestroy() {
        super.onDestroy()
        UserObservable.deleteObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is UserObservable){
            var type=arg as Int
            if (type==1){
                mvpView.updateName()
            }
            if (type==2){
                mvpView.updateIcon()
            }
        }
    }
}