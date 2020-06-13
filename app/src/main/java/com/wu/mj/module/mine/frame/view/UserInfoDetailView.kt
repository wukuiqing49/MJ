package com.wu.mj.module.mine.frame.view

import android.util.Log
import android.view.View
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.mine.ui.activity.UserInfoDetailActivity
import com.wu.mj.utlis.RedBookPresenter
import com.ypx.imagepicker.ImagePicker
import com.ypx.imagepicker.bean.MimeType
import com.ypx.imagepicker.bean.SelectMode
import com.ypx.imagepicker.data.OnImagePickCompleteListener


/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020/6/13
 *
 * 用途:
 */


class UserInfoDetailView : MvpView, View.OnClickListener {
    var mActivity: UserInfoDetailActivity

    constructor(mActivity: UserInfoDetailActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("用户信息")
        mActivity.binding.rlIcon.setOnClickListener(this)
    }

    private fun initToolBar(title: String?) {
        StatusBarUtil.setStatusBarDarkMode(mActivity)
        StatusBarUtil.setColor(mActivity, mActivity!!.getResources().getColor(R.color.color_2b2b2b), 0)
        var toolbar = mActivity!!.binding.includeToolbar as QMUITopBar
        toolbar.setTitle(title)
        toolbar.setBackgroundColor(mActivity!!.resources.getColor(R.color.color_2b2b2b))
        toolbar.addLeftImageButton(R.drawable.ic_arrow_back_white_24dp, R.id.qmui_topbar_item_left_back).setOnClickListener { mActivity!!.finish() }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_icon ->{
                showAlbum()
            }
        }
    }

    private fun showAlbum() {

        ImagePicker.withMulti(RedBookPresenter())//指定presenter
//设置选择的最大数
                .setMaxCount(9) //设置列数
                .setColumnCount(4) //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.ofAll()) //设置需要过滤掉加载的文件类型
                .filterMimeTypes(MimeType.GIF)
                .showCamera(true) //显示拍照
                .setPreview(true) //开启预览
//大图预览时是否支持预览视频
                .setPreviewVideo(true) //设置视频单选
                .setVideoSinglePick(true) //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true) //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(false) //显示原图
                .setOriginal(true) //显示原图时默认原图选项开关
                .setDefaultOriginal(false) //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .setSelectMode(SelectMode.MODE_SINGLE) //设置视频可选取的最大时长,同时也是视频可录制的最大时长
                .setMaxVideoDuration(1200000L) //设置视频可选取的最小时长
                .setMinVideoDuration(60000L) //设置上一次操作的图片列表，下次选择时默认恢复上一次选择的状态

                .pick(mActivity, OnImagePickCompleteListener {
                    //图片选择回调，主线程

                    Log.e("","")
                })
    }
}