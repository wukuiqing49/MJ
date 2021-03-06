package com.wkq.database.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wkq.database.dao.DaoMaster;
import com.wkq.database.dao.ExceptionInfoDao;
import com.wkq.database.dao.UserInfoDao;

import org.greenrobot.greendao.database.Database;


/**
 * Createdby PedroOkawa and modified by MBH on 16/08/16.
 */
public final class MigrationHelper extends DaoMaster.OpenHelper {

    public MigrationHelper(Context context, String name) {
        super(context, name);
    }

    public MigrationHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        new UpgradeHelper().migrate(db,
                ExceptionInfoDao.class,
                UserInfoDao.class
        );
    }
}