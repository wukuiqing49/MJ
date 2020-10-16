package com.wu.mjvv.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mjvv.R
import com.wu.mjvv.databinding.FragmentQuestionsDetailBinding
import com.wu.mjvv.module.home.frame.model.QuestionInfo
import com.wu.mjvv.module.home.frame.presenter.QuestionsDetailPresenter
import com.wu.mjvv.module.home.frame.view.QuestionsDetailView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsDetailFragment : MvpBindingFragment<QuestionsDetailView, QuestionsDetailPresenter, FragmentQuestionsDetailBinding>() {

    var title: String? = null
    var info: QuestionInfo? = null

    companion object {

        fun newInstance(title: String, index: QuestionInfo?): QuestionsDetailFragment {

            var bandle: Bundle = Bundle()
            var home: QuestionsDetailFragment = QuestionsDetailFragment()
            bandle.putString("title", title)
            bandle.putSerializable("info", index)
            home.arguments = bandle
            return home

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_questions_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info = arguments!!.getSerializable("info") as QuestionInfo
        if (mvpView != null) mvpView.initView()
        if (presenter != null) presenter.getQuestionData(activity, info!!.id)
        binding.data = info
    }


}