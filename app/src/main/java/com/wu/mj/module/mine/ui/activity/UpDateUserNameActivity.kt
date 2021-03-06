package com.wu.mj.module.mine.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mj.R
import com.wu.mj.databinding.ActivityUpdateUserNameBinding
import com.wu.mj.module.mine.frame.presenter.UpDateUserNamePresenter
import com.wu.mj.module.mine.frame.view.UpDateUserNameView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class UpDateUserNameActivity: MvpBindingActivity<UpDateUserNameView, UpDateUserNamePresenter,ActivityUpdateUserNameBinding>() {

    companion object {
        fun newInstance(context: Context) {
            var intnet = Intent(context, UpDateUserNameActivity().javaClass)
            context.startActivity(intnet)
        }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_update_user_name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(mvpView!=null)mvpView.initView()
    }
}