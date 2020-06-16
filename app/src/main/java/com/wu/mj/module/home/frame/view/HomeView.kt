package com.wu.mj.module.home.frame.view

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.lib_base.adapter.KtDataBindingAdapter
import com.wu.common.base.HomeTopBean
import com.wu.common.base.adapter.MoveTopAdapter
import com.wu.common.module.ui.WebViewActivity
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.SharedPreferencesHelper
import com.wu.mj.R
import com.wu.mj.module.home.frame.model.AnnouncementInfo
import com.wu.mj.module.home.ui.activity.ChapterListActivity
import com.wu.mj.module.home.ui.activity.WebViewNewsActivity
import com.wu.mj.module.home.ui.adapter.ExamAnnouncementAdapter
import com.wu.mj.module.home.ui.adapter.TopLineAdapter
import com.wu.mj.module.home.ui.fragment.HomeFragment
import com.wu.mj.utlis.DBUtlis
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.transformer.AlphaPageTransformer
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

    var mFragment: HomeFragment

    constructor(mFragment: HomeFragment) {
        this.mFragment = mFragment
    }

    fun initView() {
        initBannerData()
        mFragment.binding.cdChapter.setOnClickListener(this)
        mFragment.binding.cdExam.setOnClickListener(this)
        mFragment.binding.cdTopic.setOnClickListener(this)


        var nowTime = System.currentTimeMillis()
        var firstEnterTime = SharedPreferencesHelper.getInstance(mFragment.activity).getLong("startTime")
        var day = 1000 * 60 * 60 * 24

        var days = (nowTime - firstEnterTime) / day as Int + 1

        mFragment.binding.tvDays.setText(days.toString())

        mFragment.binding.bannerHot.setAdapter(TopLineAdapter(DBUtlis(mFragment.activity).getNews("hot")))
                .setOrientation(Banner.VERTICAL)
                .setPageTransformer(AlphaPageTransformer())
                .setOnBannerListener(OnBannerListener<AnnouncementInfo> { data: AnnouncementInfo, position: Int ->
                    WebViewNewsActivity.newInstance(mFragment.activity as Context, data.id, data.title)
                })

        mFragment.binding.rvNews.layoutManager = LinearLayoutManager(mFragment.activity)
        var mAdapter = ExamAnnouncementAdapter(mFragment.activity as Context, R.layout.item_exam_annnouncement)
        mFragment.binding.rvNews.adapter = mAdapter

        mAdapter.setOnViewClickListener(object : KtDataBindingAdapter.OnAdapterViewClickListener<AnnouncementInfo> {
            override fun onViewClick(v: View?, program: AnnouncementInfo?) {
                WebViewActivity.newInstance(mFragment.activity as Context, program?.href, program?.title)
            }
        })
        mAdapter.addItems(DBUtlis(mFragment.activity).getNews("new").toMutableList())

    }

    private fun initBannerData() {
        var topInfos: MutableList<HomeTopBean> = ArrayList()
        var topQh = HomeTopBean()
        topQh.url = "http://www.cfachina.org/"
        topQh.title = "期货协会官网"
        topQh.path = R.drawable.iv_bg_1
        topInfos.add(topQh)

        var topBm = HomeTopBean();
        topBm.url = "http://cfa.ata.net.cn/site/#/default/login"
        topBm.title = "期货考试报名"
        topBm.path = R.drawable.iv_bg_2
        topInfos.add(topBm)
        mFragment.binding.bannerMovies.setAdapter(MoveTopAdapter(topInfos))
        mFragment.binding.bannerMovies.setIndicator(mFragment.binding.indicator, false)
        mFragment.binding.bannerMovies.setIndicatorSelectedWidth(BannerUtils.dp2px(15f).toInt())

        mFragment.binding.bannerMovies.setOnBannerListener(object : OnBannerListener<HomeTopBean> {
            override fun OnBannerClick(data: HomeTopBean?, position: Int) {
                openWeb(data)
            }
        })

    }

    private fun openWeb(data: HomeTopBean?) {
        WebViewActivity.newInstance(mFragment?.activity as Context, data?.url, data?.title)
    }

    fun showMessage(message: String?) {
        if (mFragment == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mFragment!!.activity, message)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cd_chapter -> {
                jumpChapter()
            }
            R.id.cd_topic -> {
                jumpTopic()
            }
            R.id.cd_exam -> {
                jumpExam()
            }

        }
    }

    /**
     * 章节练习
     */
    private fun jumpChapter() {
        ChapterListActivity.newInstance(mFragment.activity as Context)
    }

    /**
     * 模拟考试
     */
    private fun jumpTopic() {
        ChapterListActivity.newInstance(mFragment.activity as Context, "HISTORY")

    }

    /**
     * 每年真题
     */
    private fun jumpExam() {
        ChapterListActivity.newInstance(mFragment.activity as Context, "SIMULATION")

    }
}