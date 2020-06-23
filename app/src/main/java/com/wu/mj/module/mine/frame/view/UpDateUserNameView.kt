package com.wu.mj.module.mine.frame.view

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R

import com.wu.mj.module.mine.ui.activity.UpDateUserNameActivity
import com.wu.mj.utlis.UserObservable

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/14
 *
 * 用途:
 */


class UpDateUserNameView : MvpView {


    var mActivity: UpDateUserNameActivity

    constructor(mActivity: UpDateUserNameActivity) {
        this.mActivity = mActivity

    }

    fun initView() {
        initToolBar("修改昵称")

        mActivity.binding.etName.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                var textCount=s?.length
                if (textCount!!.toInt() >10){
                    showMessage("昵称最长10个字符")
                }else{
                    mActivity.binding.tvCount.setText(textCount.toString()+" / "+"10")
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }
        var right=toolbar.addRightTextButton("提交",R.id.done);
        right.setTextColor(mActivity.resources.getColor(R.color.color_white))
        right.setOnClickListener {
            if (!TextUtils.isEmpty(mActivity.binding.etName.text)){
                mActivity.presenter.startTimer()
            }else{
                showMessage("请输入要修改的昵称")
            }

        }
    }

    fun showMessage(message: String?) {
        if (mActivity == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mActivity, message)
    }

    fun finishTimer() {
        DataBaseUtils.updateUserName(mActivity,mActivity.binding.etName.text.toString())
        UserObservable.update(1)
        mActivity.finish()
    }
}