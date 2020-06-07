package com.wkq.database.utils;

import android.content.Context;
import android.text.TextUtils;

import com.wkq.database.dao.UserInfo;
import com.wkq.database.dao.UserInfoDao;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介: 数据库操作工具基类
 */
public class DataBaseUtils {


    /**
     * 插入用户数据
     *
     * @param context
     */

    public static void insertHomeTopData(Context context, String phoneNum, String nickName, String pwd, String path) {
        UserInfoDao dao = DaoHelper.getInstance(context).getUserInfoDao();
        UserInfo info = new UserInfo();
        info.setUserIcon(path);
        info.setUserName(nickName);
        info.setUserPhoneNum(phoneNum);
        info.setUserPwd(pwd);
        info.setIsLogout(true);
        dao.insertOrReplace(info);
    }

    /**
     * 判断用户是否存在
     *
     * @param context
     * @param phoneNum
     * @return
     */
    public static boolean isExistUser(Context context, String phoneNum) {
        UserInfoDao dao = DaoHelper.getInstance(context).getUserInfoDao();
        UserInfo info = dao.load(phoneNum);
        return info == null ? false : true;
    }

    /**
     * 匹配账户
     *
     * @param context
     * @param phoneNum
     * @param pwd
     * @return
     */
    public static boolean checkUser(Context context, String phoneNum, String pwd) {
        UserInfoDao dao = DaoHelper.getInstance(context).getUserInfoDao();
        UserInfo info = dao.load(phoneNum);
        if (info == null) return false;
        if (pwd.equals(info.getUserPwd())) return true;
        return false;
    }

    /**
     * 获取用户数据
     *
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        UserInfoDao dao = DaoHelper.getInstance(context).getUserInfoDao();
        List<UserInfo> infos = dao.loadAll();
        if (infos == null) return false;
        for (UserInfo info : infos) {
            if (info.getIsLogout()) {
                return true;
            }
        }
        return false;
    }


}
