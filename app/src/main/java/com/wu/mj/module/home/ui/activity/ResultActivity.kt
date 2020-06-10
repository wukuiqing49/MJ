package com.wu.mj.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mj.R
import com.wu.mj.databinding.ActivityResultBinding
import com.wu.mj.module.home.frame.presenter.ResultPresenter
import com.wu.mj.module.home.frame.view.ResultView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/10
 *
 * 用途: 考试结果页
 */


class ResultActivity : MvpBindingActivity<ResultView, ResultPresenter, ActivityResultBinding>() {

    companion object {
        fun newInstance(context: Context, index: String?) {
            var intnet = Intent(context, ResultActivity().javaClass)
            intnet.putExtra("index", index)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var index = intent.getStringExtra("index")
        if (mvpView!=null)mvpView.initView()
        if(presenter!=null)presenter.getResultData(this,index)
    }
}