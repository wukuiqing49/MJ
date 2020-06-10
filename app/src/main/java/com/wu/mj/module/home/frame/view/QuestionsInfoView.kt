package com.wu.mj.module.home.frame.view

import android.text.TextUtils
import androidx.viewpager.widget.ViewPager
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.QuestionInfo
import com.wu.mj.module.home.ui.activity.QuestionsInfoActivity
import com.wu.mj.module.home.ui.adapter.QuestionsFragmentPagerAdapter

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsInfoView : MvpView {

    var mPosition = 0;
    var mActivity: QuestionsInfoActivity

    constructor(mActivity: QuestionsInfoActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar(mActivity.title)

    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }


    }

    fun showData(questions: List<QuestionInfo>) {
        var myAdapter = QuestionsFragmentPagerAdapter(mActivity, mActivity.supportFragmentManager, mActivity.title, questions);
        mActivity.binding.vpContent.adapter = myAdapter
        mActivity.binding.tvCount.setText("1" + "/" + questions.size)
        mActivity.binding.tvType.setText(questions.get(0).type)

        mActivity.binding.vpContent.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position
                var index = (position + 1).toString()
                mActivity.binding.tvCount.setText(index + "/" + questions.size)
            }
        })

        mActivity.binding.rlLeft.setOnClickListener {
            if (mPosition - 1 < 0) {
                showMessage("已到第一页")
            } else {
                mActivity.binding.vpContent.setCurrentItem(mPosition - 1)
            }

        }
        mActivity.binding.rlRight.setOnClickListener {

            if (mPosition + 1 > questions.size) {
                showMessage("已到最后一页")
            } else {
                mActivity.binding.vpContent.setCurrentItem(mPosition + 1)
            }

        }

        mActivity.binding.tvAnwser.setOnClickListener { showAnswer(questions[mPosition]) }

    }

    //展示答案
    private fun showAnswer(questionInfo: QuestionInfo) {



    }

    fun showMessage(message: String?) {
        if (TextUtils.isEmpty(message) || mActivity == null) return
        AlertUtil.showDeftToast(mActivity, message)

    }
}