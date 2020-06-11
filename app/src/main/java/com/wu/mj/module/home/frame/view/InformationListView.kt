package com.wu.mj.module.home.frame.view

import android.content.Context
import android.text.TextUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.InfomationInfo
import com.wu.mj.module.home.ui.adapter.InfomationInfoListAdapter
import com.wu.mj.module.home.ui.fragment.InformationListFragment

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InformationListView : MvpView {

    var mAdapter: InfomationInfoListAdapter? = null
    var mFragment: InformationListFragment

    constructor(mFragment: InformationListFragment) {
        this.mFragment = mFragment
    }

    fun initView() {

        mFragment.binding.rvContent.layoutManager = LinearLayoutManager(mFragment.activity)
        mAdapter = InfomationInfoListAdapter(mFragment.activity as Context, R.layout.item_infomation_list)
        mFragment.binding.rvContent.adapter = mAdapter


        mFragment.binding.sfLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mFragment.presenter.getData(mFragment.activity as Context,mFragment.type)

            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mFragment.presenter.getData(mFragment.activity as Context,mFragment.type)
            }

        })

    }

    fun showData(infomationInfoList: List<InfomationInfo>) {
        if (mAdapter!!.itemList!!.size<=0){
            mAdapter!!.addItems(infomationInfoList.toMutableList())
        }

        mFragment.binding.sfLayout.finishRefresh()
        mFragment.binding.sfLayout.finishLoadMore()

    }
}