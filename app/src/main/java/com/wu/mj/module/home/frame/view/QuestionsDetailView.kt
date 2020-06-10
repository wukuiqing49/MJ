package com.wu.mj.module.home.frame.view

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wu.common.utils.AlertUtil
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.AnwserInfo
import com.wu.mj.module.home.ui.adapter.QuestionAnwserAdapter
import com.wu.mj.module.home.ui.fragment.QuestionsDetailFragment
import com.wu.mj.utlis.DBUtlis

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsDetailView : MvpView {
    var mAdapter: QuestionAnwserAdapter? = null
    var mFragment: QuestionsDetailFragment

    constructor(mFragment: QuestionsDetailFragment) {
        this.mFragment = mFragment
    }

    fun initView() {
        mAdapter = QuestionAnwserAdapter(mFragment.activity as Context, R.layout.item_anwers)
        mFragment.binding.rvContent.layoutManager = LinearLayoutManager(mFragment.activity)
        mFragment.binding.rvContent.adapter = mAdapter

        mAdapter!!.setOnViewClickListener(object : KtDataBindingAdapter.OnAdapterViewClickListener<AnwserInfo> {
            override fun onViewClick(v: View?, program: AnwserInfo?) {

                mAdapter?.itemList?.forEach {
                    if (it.id != program?.id) {
                        it.setIsCheck(false)
                    }
                }
                mAdapter?.notifyDataSetChanged()
                var dbUtlis = DBUtlis(mFragment.activity)
                if (dbUtlis.database.isOpen)
                    dbUtlis.updateAnwser(program?.problemId, program?.values)
            }
        })
    }

    fun showData(anwers: List<AnwserInfo>) {
        mAdapter!!.addItems(anwers.toMutableList())
    }


    fun showMessage(message: String?) {
        if (TextUtils.isEmpty(message) || mFragment.activity == null) return
        AlertUtil.showDeftToast(mFragment.activity, message)

    }

}