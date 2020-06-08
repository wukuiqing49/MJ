package com.wu.mj.utlis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.wu.mj.module.home.frame.model.ChapterInfo;
import com.wu.mj.module.home.frame.model.QuestionInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获学习章节的目录数据
     *
     * @return
     */
    public List<ChapterInfo> getChapterList() {

        // 第一个参数String：表名
        // 第二个参数String[]:要查询的列名 new String[] { "id",
        ////                "name" }
        // 第三个参数String：查询条件  "id=?"
        // 第四个参数String[]：查询条件的参数  new String[] { "1" }
        // 第五个参数String:对查询的结果进行分组
        // 第六个参数String：对分组的结果进行限制
        // 第七个参数String：对查询的结果进行排序
//        Cursor cursor = sqliteDatabase.query("user", new String[] { "id",
//                "name" }, "id=?", new String[] { "1" }, null, null, null);
//        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
//        while (cursor.moveToNext()) {
//            id = cursor.getString(cursor.getColumnIndex("id"));
//            name = cursor.getString(cursor.getColumnIndex("name"));
//        }

        List<ChapterInfo> infos = new ArrayList<>();

        Cursor cursor = dbObj.query("chapter", null, null, null, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            ChapterInfo info = new ChapterInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String index = cursor.getString(cursor.getColumnIndex("index"));
            info.setId(id);
            info.setIndex(index);
            info.setProgress(0);
            info.setTitle(title);
            infos.add(info);
        }

        return infos;
    }

    /**
     * 获学习章节的目录数据
     *
     * @return
     */
    public List<QuestionInfo> getQuestionList(String index) {

        // 第一个参数String：表名
        // 第二个参数String[]:要查询的列名 new String[] { "id",
        ////                "name" }
        // 第三个参数String：查询条件  "id=?"
        // 第四个参数String[]：查询条件的参数  new String[] { "1" }
        // 第五个参数String:对查询的结果进行分组
        // 第六个参数String：对分组的结果进行限制
        // 第七个参数String：对查询的结果进行排序
//        Cursor cursor = sqliteDatabase.query("user", new String[] { "id",
//                "name" }, "id=?", new String[] { "1" }, null, null, null);
//        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
//        while (cursor.moveToNext()) {
//            id = cursor.getString(cursor.getColumnIndex("id"));
//            name = cursor.getString(cursor.getColumnIndex("name"));
//        }

        List<QuestionInfo> infos = new ArrayList<>();

        Cursor cursor = dbObj.query("problem", null, "exam_id=?", new String[]{index}, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            QuestionInfo info = new QuestionInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String indexs = cursor.getString(cursor.getColumnIndex("index"));

            String type = cursor.getString(cursor.getColumnIndex("type"));
//            String rig = cursor.getString(cursor.getColumnIndex("rig"));

            String knowledge_point = cursor.getString(cursor.getColumnIndex("knowledge_point"));
            String explain = cursor.getString(cursor.getColumnIndex("explain"));

            String exam_id = cursor.getString(cursor.getColumnIndex("exam_id"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));

            info.setId(id);
            info.setIndex(indexs);
            info.setTitle(title);
            info.setExam_id(exam_id);
            info.setType(type);
//            info.setRig(rig);
            info.setKnowledge_point(knowledge_point);
            info.setExplain(explain);
            info.setMy_answer(my_answer);
            infos.add(info);
        }

        return infos;
    }


}
