package com.wu.mjvv.module.mine.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ActivityAboutBinding
import com.wu.mjvv.module.mine.frame.presenter.AboutPresenter
import com.wu.mjvv.module.mine.frame.view.AboutView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class AboutActivity : MvpBindingActivity<AboutView, AboutPresenter, ActivityAboutBinding>() {
    companion object {
        fun newInstance(context: Context) {
            var intnet = Intent(context, AboutActivity().javaClass)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(mvpView!=null)mvpView.initView()
    }
}