package com.wu.mj.module.home.frame.view

import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.mj.module.home.ui.fragment.QuestionsDetailFragment

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/8
 *
 * 用途:
 */


class QuestionsDetailView : MvpView {

    var mFragment: QuestionsDetailFragment

    constructor(mFragment: QuestionsDetailFragment) {
        this.mFragment = mFragment
    }
}