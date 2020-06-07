package com.wu.mj.module.mine.frame.view

import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.mj.module.mine.ui.fragment.MineFragment

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/5/25
 *
 * 用途:
 */


class MineView : MvpView {
    var mFragment: MineFragment

    constructor(mFragment: MineFragment) {
        this.mFragment = mFragment
    }

}