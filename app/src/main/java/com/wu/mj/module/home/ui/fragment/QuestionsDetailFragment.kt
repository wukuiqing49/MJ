package com.wu.mj.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mj.R
import com.wu.mj.databinding.FragmentQuestionsDetailBinding
import com.wu.mj.module.home.frame.presenter.QuestionsDetailPresenter
import com.wu.mj.module.home.frame.view.QuestionsDetailView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsDetailFragment : MvpBindingFragment<QuestionsDetailView, QuestionsDetailPresenter, FragmentQuestionsDetailBinding>() {

    var index: String? = null

    companion object {
        fun newInstance(title: String, index: String): QuestionsDetailFragment {
            var bandle: Bundle = Bundle()
            var home: QuestionsDetailFragment = QuestionsDetailFragment()
            bandle.putString("title", title)
            bandle.putString("index", index)
            home.arguments = bandle
            return home
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_questions_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (presenter != null) presenter.getQuestionData(activity, index)
    }


}