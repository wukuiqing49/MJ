package com.wu.mjvv.utlis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.wu.mjvv.module.home.frame.model.AnwserInfo;
import com.wu.mjvv.module.home.frame.model.ChapterInfo;
import com.wu.mjvv.module.home.frame.model.InfomationInfo;
import com.wu.mjvv.module.home.frame.model.ProgressInfo;
import com.wu.mjvv.module.home.frame.model.QuestionInfo;
import com.wu.mjvv.module.home.frame.model.AnnouncementInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static String DB_PATH = "data/data/com.wu.mjvv/databases/";
    private static String DB_NAME = "qh";
    String path = DB_PATH + DB_NAME;
    private SQLiteDatabase dbObj;
    public final Context context;
    private static String Lock = "dblock";

    public DBUtlis(Context context) {
        super(context, DB_NAME, null, 3);
        this.context = context;
        try {
            if (!checkDB()) copyDB();
            String myPath = DB_PATH + DB_NAME;
            dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
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

    public void openDB() {
        try {
            if (dbObj != null && !dbObj.isOpen()) {
                String myPath = DB_PATH + DB_NAME;
                dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            }
        } catch (Exception e) {

        }

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
        openDB();
        List<ChapterInfo> infos = new ArrayList<>();

        Cursor cursor = dbObj.query("chapter", null, null, null, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            ChapterInfo info = new ChapterInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String index = cursor.getString(cursor.getColumnIndex("chapter_index"));
            info.setId(id);
            info.setIndex(index);
            info.setTitle(title);
            infos.add(info);
        }
        return infos;
    }


    /**
     * 获学习章节的目录数据
     *
     * @return
     * @ type  HISTORY  历史题      SIMULATION真题
     */
    public List<ChapterInfo> getHistoryChapterList(String type) {
        openDB();
        List<ChapterInfo> infos = new ArrayList<>();
        String sql = "SELECT * FROM exam WHERE type=?";
        String[] selectionArgs = new String[]{type};
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            ChapterInfo info = new ChapterInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            info.setId(id);
            info.setIndex(id);
            info.setTitle(title);
            infos.add(info);
        }
        return infos;
    }

    /**
     * 获学习章节的目录数据
     * Cursor cursor = sqliteDatabase.query("user", new String[] { "id","name" }, "id=?", new String[] { "1" }, null, null, null
     * 第一个参数String：表名
     * 第二个参数String[]:要查询的列名 new String[] { "id""name" }
     * 第三个参数String：查询条件  "id=?"
     * 第四个参数String[]：查询条件的参数  new String[] { "1" }
     * 第五个参数String:对查询的结果进行分组
     * 第六个参数String：对分组的结果进行限制
     * 第七个参数String：对查询的结果进行排序
     *
     * @return
     */
    public List<QuestionInfo> getQuestionList(String index) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{index + ""};
//        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM chapter,section,problem WHERE chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem  JOIN section  JOIN chapter ON chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";

        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String indexs = cursor.getString(cursor.getColumnIndex("chapter_index"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String knowledge_point = cursor.getString(cursor.getColumnIndex("knowledge_point"));
            String explain = cursor.getString(cursor.getColumnIndex("problem_explain"));
            String exam_id = cursor.getString(cursor.getColumnIndex("exam_id"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));

            QuestionInfo info = new QuestionInfo();
            info.setId(id);
            info.setIndex(indexs);
            info.setTitle(title);
            info.setExam_id(exam_id);
            info.setType(type);
            info.setRight_answer(right_answer);
            info.setKnowledge_point(knowledge_point);
            info.setExplain(explain);
            info.setMy_answer(my_answer);
            infos.add(info);

        }
        return infos;
    }

    public List<QuestionInfo> getOtherQuestionList(String index) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{index + ""};
//        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM chapter,section,problem WHERE chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer  FROM problem WHERE exam_id=? ORDER BY problem.id";

        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String indexs = cursor.getString(cursor.getColumnIndex("chapter_index"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String knowledge_point = cursor.getString(cursor.getColumnIndex("knowledge_point"));
            String explain = cursor.getString(cursor.getColumnIndex("problem_explain"));
            String exam_id = cursor.getString(cursor.getColumnIndex("exam_id"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));

            QuestionInfo info = new QuestionInfo();
            info.setId(id);
            info.setIndex(indexs);
            info.setTitle(title);
            info.setExam_id(exam_id);
            info.setType(type);
            info.setRight_answer(right_answer);
            info.setKnowledge_point(knowledge_point);
            info.setExplain(explain);
            info.setMy_answer(my_answer);
            infos.add(info);

        }
        return infos;
    }


    public List<AnwserInfo> getAnwserList(String problemId) {
        openDB();
        List<AnwserInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{problemId + ""};
        String sql = "SELECT * FROM option WHERE problem_id= ?";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String value = cursor.getString(cursor.getColumnIndex("value"));
            String answer = cursor.getString(cursor.getColumnIndex("answer"));
            String problem_id = cursor.getString(cursor.getColumnIndex("problem_id"));
            AnwserInfo info = new AnwserInfo();
            info.setId(id);
            info.setAnwer(answer);
            info.setProblemId(problem_id);
            info.setValues(value);
            infos.add(info);
        }
        return infos;
    }

    /**
     * table为表名
     * <p>
     * values为ContentValues对象！该对象存储方式为Key-value，Key表示表中的字段名称
     * <p>
     * value表示该字段对应的值，第三个参数为条件例如：_ID = ? ,第四个参数为第三个参数的值！
     *
     * @param problemId 问题id
     * @param answer    我的答案
     */

    public void updateAnwser(String problemId, String answer) {
        openDB();

        dbObj.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("my_answer", answer);
            String[] whereArgs = new String[]{problemId};
            dbObj.update("problem", contentValues, "id=?", whereArgs);
            dbObj.setTransactionSuccessful();// 设置事务执行成功
        } finally {
            dbObj.endTransaction();
        }
    }


    public boolean isAnswer(String index) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{index + ""};
        //String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM chapter,section,problem WHERE chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem  JOIN section  JOIN chapter ON chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";

        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String indexs = cursor.getString(cursor.getColumnIndex("chapter_index"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String knowledge_point = cursor.getString(cursor.getColumnIndex("knowledge_point"));
            String explain = cursor.getString(cursor.getColumnIndex("problem_explain"));
            String exam_id = cursor.getString(cursor.getColumnIndex("exam_id"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));

            QuestionInfo info = new QuestionInfo();
            info.setId(id);
            info.setIndex(indexs);
            info.setTitle(title);
            info.setExam_id(exam_id);
            info.setType(type);
            info.setRight_answer(right_answer);
            info.setKnowledge_point(knowledge_point);
            info.setExplain(explain);
            if (TextUtils.isEmpty(my_answer)) {
                close();
                return false;

            }
            info.setMy_answer(my_answer);
            infos.add(info);

        }
        return true;
    }

    int nowProgress;

    public ProgressInfo getProgress(String chapterId) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{chapterId + ""};
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem  JOIN section  JOIN chapter ON chapter.id=section.chapter_id and section.exam_id=problem.exam_id  and section.chapter_id =? ORDER BY problem.id";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));
            QuestionInfo info = new QuestionInfo();
            info.setRight_answer(right_answer);
            info.setMy_answer(my_answer);
            infos.add(info);
        }

        ProgressInfo progressInfo = new ProgressInfo();
        progressInfo.setTotalProgress(infos.size());

        for (QuestionInfo info : infos) {
            if (!TextUtils.isEmpty(info.getMy_answer())) {
                nowProgress += 1;
            }
        }
        progressInfo.setNowProgress(nowProgress);
        nowProgress = 0;
        close();
        return progressInfo;
    }

    public ProgressInfo getProgressOther(String chapterId) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{chapterId + ""};
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem  where problem.exam_id =? ORDER BY problem.id";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));
            QuestionInfo info = new QuestionInfo();
            info.setRight_answer(right_answer);
            info.setMy_answer(my_answer);
            infos.add(info);
        }

        ProgressInfo progressInfo = new ProgressInfo();
        progressInfo.setTotalProgress(infos.size());

        for (QuestionInfo info : infos) {
            if (!TextUtils.isEmpty(info.getMy_answer())) {
                nowProgress += 1;
            }
        }
        progressInfo.setNowProgress(nowProgress);
        nowProgress = 0;
        close();
        return progressInfo;
    }

    /**
     * 资讯
     *
     * @param type
     * @return
     */

    public List<InfomationInfo> getInfomationInfoList(String type) {
        openDB();
        List<InfomationInfo> infos = new ArrayList<>();
        String sql = "SELECT * FROM zixun WHERE type=?";
        String[] selectionArgs = new String[]{type};
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            InfomationInfo info = new InfomationInfo();

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));

            String time = cursor.getString(cursor.getColumnIndex("time"));

            String desc = cursor.getString(cursor.getColumnIndex("desc"));

            String types = cursor.getString(cursor.getColumnIndex("type"));
            String icon = cursor.getString(cursor.getColumnIndex("icon"));

            info.setId(id);
            info.setDesc(desc);
            info.setIcon(icon);
            info.setTime(time);
            info.setType(types);
            info.setTitle(title);
            infos.add(info);

        }
        return infos;
    }

    /**
     * 资讯详情
     *
     * @param type
     * @return
     */

    public InfomationInfo getInfomationDetail(String type) {

        openDB();

        String sql = "SELECT * FROM zixun WHERE id=?";
        String[] selectionArgs = new String[]{type};
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        InfomationInfo info = null;

        while (cursor.moveToNext()) {
            info = new InfomationInfo();

            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String desc = cursor.getString(cursor.getColumnIndex("desc"));
            String types = cursor.getString(cursor.getColumnIndex("type"));
            String icon = cursor.getString(cursor.getColumnIndex("icon"));

            info.setId(id);
            info.setDesc(desc);
            info.setIcon(icon);
            info.setTime(time);
            info.setType(types);
            info.setTitle(title);
        }
        return info;
    }


    public ProgressInfo getTypeProgress(String type) {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{type + ""};
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem   JOIN exam ON exam.id=problem.exam_id  and exam.type =? ORDER BY problem.id";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));
            QuestionInfo info = new QuestionInfo();
            info.setRight_answer(right_answer);
            info.setMy_answer(my_answer);
            infos.add(info);
        }
        ProgressInfo progressInfo = new ProgressInfo();
        progressInfo.setTotalProgress(infos.size());

        for (QuestionInfo info : infos) {
            if (info.getMy_answer().equals(info.getRight_answer())) {
                nowProgress += 1;
            }
        }
        progressInfo.setNowProgress(nowProgress);
        nowProgress = 0;
        close();
        return progressInfo;
    }


    public ProgressInfo getLXProgress() {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{""};
        String sql = "SELECT problem.id,problem.title,problem.type,problem.chapter_index,problem.right_answer,problem.knowledge_point,problem.problem_explain,problem.exam_id,problem.my_answer FROM problem   JOIN chapter ON chapter.id=problem.exam_id  ORDER BY problem.id";
        Cursor cursor = dbObj.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));
            QuestionInfo info = new QuestionInfo();
            info.setRight_answer(right_answer);
            info.setMy_answer(my_answer);
            infos.add(info);
        }
        ProgressInfo progressInfo = new ProgressInfo();
        progressInfo.setTotalProgress(infos.size());

        for (QuestionInfo info : infos) {
            if (info.getMy_answer().equals(info.getRight_answer())) {
                nowProgress += 1;
            }
        }
        progressInfo.setNowProgress(nowProgress);
        nowProgress = 0;
        close();
        return progressInfo;
    }

    public List<AnnouncementInfo> getAnnouncement() {
        openDB();

        List<AnnouncementInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{"new"};
        String sql = "SELECT * FROM announcement where a_type= ?";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            AnnouncementInfo info = new AnnouncementInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String a_time = cursor.getString(cursor.getColumnIndex("a_time"));
            String href = cursor.getString(cursor.getColumnIndex("href"));
            String post_time = cursor.getString(cursor.getColumnIndex("post_time"));

            info.setId(id);
            info.setTitle(title);
            info.setA_time(a_time);
            info.setPost_time(post_time);
            info.setHref(href);

            infos.add(info);
        }

        return infos;
    }

    public ProgressInfo getTotalProgress() {
        openDB();
        List<QuestionInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{""};
        String sql = "SELECT problem.right_answer,problem.my_answer FROM problem";
        Cursor cursor = dbObj.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String right_answer = cursor.getString(cursor.getColumnIndex("right_answer"));
            String my_answer = cursor.getString(cursor.getColumnIndex("my_answer"));
            QuestionInfo info = new QuestionInfo();
            info.setRight_answer(right_answer);
            info.setMy_answer(my_answer);
            infos.add(info);
        }
        ProgressInfo progressInfo = new ProgressInfo();
        progressInfo.setTotalProgress(infos.size());

        for (QuestionInfo info : infos) {
            if (info.getMy_answer().equals(info.getRight_answer())) {
                nowProgress += 1;
            }
        }
        progressInfo.setNowProgress(nowProgress);
        nowProgress = 0;
        close();
        return progressInfo;
    }

    /**
     * 清空答题记录
     */
    public void clearCache() {
        openDB();
        dbObj.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("my_answer", "");
            dbObj.update("problem", contentValues, null, null);
            dbObj.setTransactionSuccessful();// 设置事务执行成功
        } finally {
            dbObj.endTransaction();
        }
    }

    /**
     * 获取指定类型的数据
     *
     * @param type
     * @return
     */
    public List<AnnouncementInfo> getNews(String type) {
        openDB();

        List<AnnouncementInfo> infos = new ArrayList<>();
        String[] selectionArgs = new String[]{type};
        String sql = "SELECT * FROM announcement where a_type= ?";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            AnnouncementInfo info = new AnnouncementInfo();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String a_time = cursor.getString(cursor.getColumnIndex("a_time"));
            String href = cursor.getString(cursor.getColumnIndex("href"));
            String post_time = cursor.getString(cursor.getColumnIndex("post_time"));

            info.setId(id);
            info.setTitle(title);
            info.setA_time(a_time);
            info.setPost_time(post_time);
            info.setHref(href);

            infos.add(info);
        }

        return infos;
    }


    public AnnouncementInfo getNewInfo(String ids) {
        openDB();


        String[] selectionArgs = new String[]{ids};
        String sql = "SELECT * FROM announcement where id= ?";
        Cursor cursor = dbObj.rawQuery(sql, selectionArgs);
        AnnouncementInfo info = new AnnouncementInfo();
        while (cursor.moveToNext()) {
            String desc = cursor.getString(cursor.getColumnIndex("a_desc"));

            info.setA_desc(desc);

        }

        return info;
    }


}
