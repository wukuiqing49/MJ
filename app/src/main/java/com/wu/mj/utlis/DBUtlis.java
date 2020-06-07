package com.wu.mj.utlis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020/6/5
 * <p>
 * 用途: sql3 数据库操作
 */


public class DBUtlis extends SQLiteOpenHelper {
    private static String DB_PATH = "data/data/com.wu.mj/databases/";
    private static String DB_NAME = "qh";
    private SQLiteDatabase dbObj;
    public final Context context;

    public DBUtlis(Context context) {
        super(context, DB_NAME, null, 3);
        this.context = context;
        try {
            if (checkDB()) {

            } else {
                copyDB();
                String myPath = DB_PATH + DB_NAME;
                dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 检测数据库是否存在
     *
     * @return
     */
    private boolean checkDB() {
        SQLiteDatabase checkDB = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if (checkDB != null) {
            } else {
                return false;
            }

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    /**
     * 见assets目录下的文件拷贝到sd上
     *
     * @return 存储数据库的地址
     */

    public void copyDB() throws IOException {
        InputStream ip = context.getAssets().open(DB_NAME + ".sqlite3");
        File dir = new File(DB_PATH);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        File file = new File(dir, DB_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            OutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = ip.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            ip.close();
        } catch (Exception e) {
            ip.close();
        }

    }

    public SQLiteDatabase getDatabase() {
        String myPath = DB_PATH + DB_NAME;
        dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        return dbObj;
    }

    public void openDB() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (dbObj != null)
            dbObj.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
