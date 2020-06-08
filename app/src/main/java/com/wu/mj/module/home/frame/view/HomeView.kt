package com.wu.mj.module.home.frame.view

import android.content.Context
import android.text.TextUtils
import android.view.View
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.base.HomeTopBean
import com.wu.common.base.adapter.MoveTopAdapter
import com.wu.common.utils.AlertUtil
import com.wu.mj.R
import com.wu.mj.module.home.ui.activity.ChapterListActivity
import com.wu.mj.module.home.ui.fragment.HomeFragment
import com.youth.banner.indicator.RoundLinesIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.util.BannerUtils

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/7
 *
 * 用途:
 */


class HomeView : MvpView, View.OnClickListener {


    var mFragment: HomeFragment? = null

    constructor(mFragment: HomeFragment) {
        this.mFragment = mFragment

    }

    fun initView() {
        initBannerData();
        mFragment!!.binding.tvChapter.setOnClickListener(this)
        mFragment!!.binding.tvExam.setOnClickListener(this)
        mFragment!!.binding.tvTopic.setOnClickListener(this)


    }

    private fun initBannerData() {

        var topInfos: MutableList<HomeTopBean> = ArrayList()
        var topQh = HomeTopBean();
        topQh.url = "http://www.cfachina.org/"
        topQh.title = "期货协会"
        topQh.path = R.drawable.iv_bm
        topInfos.add(topQh)

        var topBm = HomeTopBean();
        topBm.url = "http://cfa.ata.net.cn/site/#/default/login"
        topBm.title = "期货考试报名"
        topBm.path = R.drawable.iv_bm
        topInfos.add(topBm)

        mFragment!!.binding.bannerMovies.setAdapter(MoveTopAdapter(topInfos))
        mFragment!!.binding.bannerMovies.indicator = RoundLinesIndicator(mFragment!!.activity)
        mFragment!!.binding.bannerMovies.setIndicatorSelectedWidth(BannerUtils.dp2px(15f).toInt())

        mFragment!!.binding.bannerMovies.setOnBannerListener(object : OnBannerListener<HomeTopBean> {
            override fun OnBannerClick(data: HomeTopBean?, position: Int) {
                showMessage(data!!.title)
            }
        });

    }

    fun showMessage(message: String?) {
        if (mFragment == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mFragment!!.activity, message)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_chapter -> {
                jumpChapter()
            }
            R.id.tv_topic -> {
                jumpTopic()
            }
            R.id.tv_exam -> {
                jumpExam()
            }

        }
    }

    /**
     * 章节练习
     */
    private fun jumpChapter() {

        ChapterListActivity.newInstance(mFragment!!.activity as Context)
    }

    /**
     * 模拟考试
     */
    private fun jumpTopic() {


    }

    /**
     * 每年真题
     */
    private fun jumpExam() {


    }
}