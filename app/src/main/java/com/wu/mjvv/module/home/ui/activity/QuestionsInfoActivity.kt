package com.wu.mjvv.module.home.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ActivityQuestionInfoBinding
import com.wu.mjvv.module.home.frame.presenter.QuestionsInfoPresenter
import com.wu.mjvv.module.home.frame.view.QuestionsInfoView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsInfoActivity : MvpBindingActivity<QuestionsInfoView, QuestionsInfoPresenter, ActivityQuestionInfoBinding>() {

    var title: String? = null
    var index: String? = null

    companion object {
        fun newInstance(context: Context, title: String?, index: String?) {
            var intnet = Intent(context, QuestionsInfoActivity().javaClass)
            intnet.putExtra("title", title)
            intnet.putExtra("index", index)
            context.startActivity(intnet)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_question_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = intent.getStringExtra("title")
        index = intent.getStringExtra("index")
        if (mvpView!=null)mvpView.initView()
        if(presenter!=null)presenter.getQuestions(this,index)
    }
}