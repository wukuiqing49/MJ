package com.wu.mjvv.module.launch.ui.activity

import android.Manifest
import android.app.Dialog
import android.os.Bundle
import com.wkq.base.frame.activity.MvpBindingActivity
import com.wu.mjvv.R
import com.wu.mjvv.databinding.ActivityLaunchBinding

import com.wu.mjvv.module.launch.frame.presenter.LaunchPresenter
import com.wu.mjvv.module.launch.frame.view.LaunchView


/**
 * 作者: 吴奎庆
 *
 * 时间: 2020/5/14
 *
 * 简介:
 */
class LaunchActivity : MvpBindingActivity<LaunchView, LaunchPresenter, ActivityLaunchBinding>() {

    var dialog: Dialog?=null

    var permissionsREAD = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

            Manifest.permission.ACCESS_FINE_LOCATION)
    var REQUEST_CODE_LAUNCH = 10011

    override fun getLayoutId(): Int {
        return R.layout.activity_launch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mvpView != null) mvpView.checkPermissions()
        if (mvpView != null) mvpView.initView()

    }

    override fun onResume() {
        super.onResume()
        if (getPresenter() != null) getPresenter().resume()
    }

    override fun onStop() {
        super.onStop()
        if (getPresenter() != null) getPresenter().stop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LAUNCH) {
            val hasPermissions = getPresenter().onRequestPermissionsResult(this, requestCode, permissions, grantResults)
            if (hasPermissions!![0]) {
                if (mvpView != null) mvpView.checkPermissions()
            } else {
                if (hasPermissions[1]) {
                    mvpView.showPermissionPerpetual(requestCode)
                } else {
                    if (mvpView != null) mvpView.checkPermissions()
                }
            }
        }
    }
}