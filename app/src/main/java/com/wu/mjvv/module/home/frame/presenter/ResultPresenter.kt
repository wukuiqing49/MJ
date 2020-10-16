package com.wu.mjvv.module.home.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mjvv.module.home.frame.view.ResultView
import com.wu.mjvv.module.home.ui.activity.ResultActivity
import com.wu.mjvv.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/10
 *
 * 用途:
 */


class ResultPresenter : MvpBasePresenter<ResultView>() {

    fun getResultData(resultActivity: ResultActivity, index: String?) {
        var results = DBUtlis(resultActivity).getQuestionList(index)

        if (view!=null) view.showResult(results)
    }

}