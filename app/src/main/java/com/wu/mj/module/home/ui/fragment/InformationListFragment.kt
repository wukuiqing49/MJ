package com.wu.mj.module.home.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mj.R
import com.wu.mj.databinding.FragmentInformationListBinding
import com.wu.mj.module.home.frame.model.QuestionInfo
import com.wu.mj.module.home.frame.presenter.InfomationListPresenter
import com.wu.mj.module.home.frame.view.InformationListView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InformationListFragment : MvpBindingFragment<InformationListView, InfomationListPresenter, FragmentInformationListBinding>() {

    var type: String? = null

    companion object {
        fun newInstance(type: String?): InformationListFragment {
            var bandle: Bundle = Bundle()
            var home: InformationListFragment = InformationListFragment()
            bandle.putString("type", type)
            home.arguments = bandle
            return home
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_information_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mvpView != null) mvpView.initView()
        type = arguments!!.getString("type")
        if (presenter!=null)presenter.getData(activity as Context,type)
    }
}