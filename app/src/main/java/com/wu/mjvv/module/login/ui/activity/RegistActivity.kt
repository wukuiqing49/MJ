package com.wu.mjvv.module.login.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ActivityRegistBinding
import com.wu.mjvv.module.login.frame.presenter.RegistPresenter
import com.wu.mjvv.module.login.frame.view.RegistView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class RegistActivity : MvpBindingActivity<RegistView, RegistPresenter, ActivityRegistBinding>() {

    companion object{

        fun newInstance(context: Context) {
            var intnet = Intent(context, RegistActivity().javaClass)
            context.startActivity(intnet)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_regist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (view!=null)view.initView()
    }
}