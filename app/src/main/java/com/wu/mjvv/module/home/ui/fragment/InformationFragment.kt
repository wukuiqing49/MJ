package com.wu.mjvv.module.home.ui.fragment

import android.os.Bundle
import android.view.View
import com.wkq.base.frame.fragment.MvpBindingFragment
import com.wu.mjvv.R
import com.wu.mjvv.databinding.FragmentInformationBinding
import com.wu.mjvv.databinding.FragmentInformationListBinding
import com.wu.mjvv.module.home.frame.presenter.InfomationPresenter
import com.wu.mjvv.module.home.frame.view.InformationView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InformationFragment :MvpBindingFragment<InformationView, InfomationPresenter, FragmentInformationBinding>() {


    companion object {
        fun newInstance(): InformationFragment {
            var bandle: Bundle = Bundle()
            var home: InformationFragment = InformationFragment()
            home.arguments = bandle
            return home
        }
    }
    override fun getLayoutId(): Int {
        return  R.layout.fragment_information
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(mvpView!=null)mvpView.initView()
    }
}