package com.wu.mj.module.home.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mj.R
import com.wu.mj.databinding.FragmentExamAnnouncementBinding
import com.wu.mj.module.home.frame.presenter.ExamAnnouncementPresenter
import com.wu.mj.module.home.frame.view.ExamAnnouncementView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/15
 *
 * 用途:
 */


class ExamAnnouncementFragment :MvpBindingFragment<ExamAnnouncementView, ExamAnnouncementPresenter,FragmentExamAnnouncementBinding>() {

    companion object {

        fun newInstance(): ExamAnnouncementFragment {
            var bandle: Bundle = Bundle()
            var home: ExamAnnouncementFragment = ExamAnnouncementFragment()
            home.arguments = bandle
            return home
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_exam_announcement
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(mvpView!=null)mvpView.initView()
        if(presenter!=null)presenter.getData(activity as Context)
    }


}