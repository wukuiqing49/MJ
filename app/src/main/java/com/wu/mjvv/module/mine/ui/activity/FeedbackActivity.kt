package com.wu.mjvv.module.mine.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ActivityFeeedBackBinding
import com.wu.mjvv.module.mine.frame.presenter.FeedbackPresenter
import com.wu.mjvv.module.mine.frame.view.FeedbackView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class FeedbackActivity :MvpBindingActivity<FeedbackView,FeedbackPresenter,ActivityFeeedBackBinding>() {

    companion object {
        fun newInstance(context: Context) {
            var intnet = Intent(context, FeedbackActivity().javaClass)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_feeed_back
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mvpView!=null)mvpView.initView()
    }
}