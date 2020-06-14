package com.wu.mj.module.mine.frame.view

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmuiteam.qmui.widget.QMUITopBar
import com.wkq.base.frame.mosby.delegate.MvpView
import com.wkq.database.utils.DataBaseUtils
import com.wu.common.utils.AlertUtil
import com.wu.common.utils.AndroidQUtil
import com.wu.common.utils.StatusBarUtil
import com.wu.mj.R
import com.wu.mj.module.mine.ui.activity.UpDateUserNameActivity
import com.wu.mj.module.mine.ui.activity.UserInfoDetailActivity
import com.wu.mj.utlis.DBUtlis
import com.wu.mj.utlis.RedBookPresenter
import com.wu.mj.utlis.UserObservable
import com.ypx.imagepicker.ImagePicker
import com.ypx.imagepicker.bean.ImageItem
import com.ypx.imagepicker.bean.MimeType
import com.ypx.imagepicker.bean.SelectMode
import com.ypx.imagepicker.data.OnImagePickCompleteListener
import java.util.ArrayList


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
    var request = RequestOptions.centerCropTransform().error(R.drawable.iv_icon_defult).placeholder(R.drawable.iv_icon_defult)


    constructor(mActivity: UserInfoDetailActivity) {
        this.mActivity = mActivity
    }

    fun initView() {
        initToolBar("用户信息")

        mActivity.binding.rlIcon.setOnClickListener(this)
        mActivity.binding.rlName.setOnClickListener(this)
        mActivity.binding.tvName.setText(DataBaseUtils.getUser(mActivity).userName)
        Glide.with(mActivity).load(DataBaseUtils.getUser(mActivity).userIcon).apply(request).into(mActivity.binding.ivIcon)
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
            R.id.rl_icon -> {
                showAlbum()
            }
            R.id.rl_name -> {
                updateName()
            }
        }
    }


    private fun showAlbum() {

        ImagePicker.withMulti(RedBookPresenter())//指定presenter
                .setMaxCount(1)
                //设置列数
                .setColumnCount(4)
                //设置要加载的文件类型，可指定单一类型
                .mimeTypes(MimeType.JPEG)
                //设置需要过滤掉加载的文件类型
                .filterMimeTypes(MimeType.GIF)
                .showCamera(true) //显示拍照
                .setPreview(true) //开启预览
                .setPreviewVideo(true) //设置视频单选
                .setVideoSinglePick(true) //设置图片和视频单一类型选择
                .setSinglePickImageOrVideoType(true) //当单选或者视频单选时，点击item直接回调，无需点击完成按钮
                .setSinglePickWithAutoComplete(false) //显示原图
                .setOriginal(true) //显示原图时默认原图选项开关
                .setDefaultOriginal(true) //设置单选模式，当maxCount==1时，可执行单选（下次选中会取消上一次选中）
                .pick(mActivity, OnImagePickCompleteListener {
                    //图片选择回调，主线程
                    if (it != null) {
                        if (it != null && !TextUtils.isEmpty(it[0].path))
                            updateIcon(it)
                    }
                })
    }

    private fun updateIcon(it: ArrayList<ImageItem>?) {
        if (AndroidQUtil.isAndroidQ()){
            DataBaseUtils.updateUserIcon(mActivity, it?.get(0)?.uri.toString())
            Glide.with(mActivity).load(it?.get(0)?.uri.toString()).apply(request).into(mActivity.binding.ivIcon)
        }else{
            DataBaseUtils.updateUserIcon(mActivity, it?.get(0)?.path)
            Glide.with(mActivity).load(it?.get(0)?.path).apply(request).into(mActivity.binding.ivIcon)
        }

        UserObservable.update(2)

    }

    private fun updateName() {
        UpDateUserNameActivity.newInstance(mActivity)
    }

    fun showMessage(message: String?) {
        if (mActivity == null || TextUtils.isEmpty(message)) return
        AlertUtil.showDeftToast(mActivity, message)
    }

    fun updateNewName() {
        mActivity.binding.tvName.setText(DataBaseUtils.getUser(mActivity).userName)
        showMessage("更新昵称成功")
    }

    fun updateNewIcon() {
        Glide.with(mActivity).load(DataBaseUtils.getUser(mActivity).userIcon).apply(request).into(mActivity.binding.ivIcon)
    }
}