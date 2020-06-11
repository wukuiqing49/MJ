package com.wu.mj.module.home.frame.view

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.home.ui.adapter.QHPagerAdapter
import com.wu.mj.module.home.ui.fragment.InformationFragment
import com.wu.mj.module.home.ui.fragment.InformationListFragment
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/11
 *
 * 用途:
 */


class InformationView : MvpView {

    var titleList = listOf<String>("考试动态", "考试大纲", "辅导资料")

    var mFragment: InformationFragment

    constructor(mFragment: InformationFragment) {
        this.mFragment = mFragment
    }

    fun initView() {
        StatusBarUtil.setDarkMode(mFragment.activity)
        var mAdapter = QHPagerAdapter(mFragment.activity, mFragment.childFragmentManager);
        mFragment.binding.vpInformation.adapter = mAdapter


        val magicIndicator = mFragment.binding.magicIndicator
        val commonNavigator = CommonNavigator(mFragment.activity)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return if (titleList == null) 0 else titleList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView: SimplePagerTitleView =
                        ColorTransitionPagerTitleView(context)
                simplePagerTitleView.setText(titleList.get(index))
                simplePagerTitleView.normalColor = Color.parseColor("#88ffffff")
                simplePagerTitleView.selectedColor = Color.WHITE
                simplePagerTitleView.setOnClickListener {
                    mFragment.binding.vpInformation.currentItem = index
                }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                val indicator = LinePagerIndicator(context)
                indicator.setColors(Color.parseColor("#40c4ff"))
                return null
            }
        }
        magicIndicator.navigator = commonNavigator
        val titleContainer =
                commonNavigator.titleContainer // must after setNavigator

        titleContainer.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        titleContainer.dividerPadding = UIUtil.dip2px(mFragment.activity, 15.0)
        titleContainer.dividerDrawable = mFragment.resources.getDrawable(R.drawable.simple_splitter)

        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, mFragment.binding.vpInformation)

    }
}