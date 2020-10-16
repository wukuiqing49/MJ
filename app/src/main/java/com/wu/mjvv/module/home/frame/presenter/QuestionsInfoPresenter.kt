package com.wu.mjvv.module.home.frame.presenter

import com.wkq.base.frame.mosby.MvpBasePresenter
import com.wu.mjvv.module.home.frame.model.QuestionInfo
import com.wu.mjvv.module.home.frame.view.QuestionsInfoView
import com.wu.mjvv.module.home.ui.activity.QuestionsInfoActivity
import com.wu.mjvv.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsInfoPresenter : MvpBasePresenter<QuestionsInfoView>() {
    fun getQuestions(questionsInfoActivity: QuestionsInfoActivity, index: String?) {
        var questions: List<QuestionInfo>
        var db = DBUtlis(questionsInfoActivity)
        if (index!!.toInt() < 35) {
            questions = db.getQuestionList(index)
        } else {
            questions = db.getOtherQuestionList(index)
        }

        if (view != null) view.showData(questions)

    }

}