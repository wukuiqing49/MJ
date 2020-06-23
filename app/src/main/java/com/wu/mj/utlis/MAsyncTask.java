package com.wu.mj.utlis;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.wkq.database.utils.DataBaseUtils;
import com.wu.mj.module.launch.ui.activity.ForceUpdateActivity;
import com.wu.mj.module.launch.ui.activity.H5Activity;
import com.wu.mj.module.login.ui.activity.LoginActivity;
import com.wu.mj.module.main.ui.activity.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020/6/22
 * <p>
 * 用途:
 */


public class MAsyncTask extends AsyncTask<Integer, Integer, String> {
    Activity mContxt;

    public MAsyncTask(Activity context) {
        mContxt = context;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        return download();
    }

    private String download() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
//apiUrl我们这边提供
            URL url = new URL("http://mock-api.com/rnNWPJKl.mock/appconfig");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in = connection.getInputStream();
            //对获取到的输入流进行读取
            reader = new BufferedReader(new InputStreamReader(in));
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    @Override
    protected void onPostExecute(String s) {
        showResponse(s);

    }

    @Override
    protected void onCancelled(String s) {
    }

    public void showResponse(final String response) {
        //在这里进行UI操作，将结果显示到界面上
        try {
            JSONObject jsonObject = new JSONObject(response);
            String isUpdate = "0";
            String isWap = "0";
            if (jsonObject.has("isUpdate")) {
                isUpdate = jsonObject.getString("isUpdate");
            }
            if (jsonObject.has("isWap")) {
                isWap = jsonObject.getString("isWap");
            }
            if ("1".equals(isWap) && jsonObject.has("wapUrl")) {
                Intent intent = new Intent(mContxt, H5Activity.class);
                intent.putExtra("url", jsonObject.getString("wapUrl"));
                mContxt.startActivity(intent);
                mContxt.finish();
                return;
            }
            if ("1".equals(isUpdate)) {
                Intent intent = new Intent(mContxt, ForceUpdateActivity.class);
                intent.putExtra("result", response);
                mContxt.startActivity(intent);
                mContxt.finish();
                return;
            }
            jump();
        } catch (Exception e) {
            jump();
            e.printStackTrace();
        }
    }

    public void jump() {
        if (DataBaseUtils.isLogin(mContxt)) {
            Intent intent = new Intent();
            intent.setClass(mContxt, MainActivity.class);
            mContxt.startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.setClass(mContxt, LoginActivity.class);
            mContxt.startActivity(intent);
        }
        mContxt.finish();
    }

}