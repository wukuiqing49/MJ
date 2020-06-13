package com.wu.mj.module.home.frame.view

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wu.common.module.ui.dialog.AnswerDialog
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.QuestionInfo
import com.wu.mj.module.home.ui.activity.ResultActivity
import com.wu.mj.module.home.ui.adapter.ResultAnwserAdapter
import java.text.NumberFormat

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/10
 *
 * 用途:
 */


class ResultView : MvpView {
    var mActivity: ResultActivity
    var mAdapter: ResultAnwserAdapter? = null

    constructor(mActivity: ResultActivity) {
        this.mActivity = mActivity
    }


    fun initView() {
        initToolBar("答题结果")
        mAdapter = ResultAnwserAdapter(mActivity, R.layout.item_result)
        mActivity.binding.rvContent.layoutManager = GridLayoutManager(mActivity, 10)
        mActivity.binding.rvContent.adapter = mAdapter

        mAdapter!!.setOnViewClickListener(object : KtDataBindingAdapter.OnAdapterViewClickListener<QuestionInfo> {
            override fun onViewClick(v: View?, program: QuestionInfo?) {
                showAnswer(program)
            }
        })

    }

    //展示答案
    private fun showAnswer(questionInfo: QuestionInfo?) {
        AnswerDialog(mActivity).build().setTitle(questionInfo?.right_answer).setValues(questionInfo?.explain).show()
    }


    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }

    }

    /**
     * 展示结果
     */
    var okResult = 0.00

    fun showResult(results: List<QuestionInfo>) {
        mAdapter!!.addItems(results.toMutableList())
        var okList = mutableListOf<QuestionInfo>()
        results.forEach {
            if (it.right_answer.equals(it.my_answer)) {
                okResult += 1
            }
        }
        var pros = okResult / results.size
        if (pros > 0.9) {
            mActivity.binding.ivLv.setBackgroundResource(R.mipmap.iv_perfect)
        } else if (pros > 0.8) {
            mActivity.binding.ivLv.setBackgroundResource(R.mipmap.iv_good)
        } else if (pros > 0.6) {
            mActivity.binding.ivLv.setBackgroundResource(R.mipmap.iv_success)
        } else {
            mActivity.binding.ivLv.setBackgroundResource(R.mipmap.iv_bad)
        }

        var progress = okResult / results.size * 100
        var numberFormat = NumberFormat.getInstance();
        numberFormat.minimumFractionDigits = 2
        var progressText = numberFormat.format(progress)

        mActivity.binding.tvResult.setText("正确率: " + progressText + "%")

    }
}