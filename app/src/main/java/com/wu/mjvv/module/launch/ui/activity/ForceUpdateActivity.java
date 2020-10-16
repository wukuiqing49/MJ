package com.wu.mjvv.module.launch.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wu.mjvv.module.main.ui.activity.MainActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 强制更新app界面
 */
public class ForceUpdateActivity extends Activity {

    String isUpdate = "0";
    String updateUrl = "";
    RelativeLayout relativeLayout;
    RelativeLayout rlUpdate;
    ProgressBar progressBarABC;
    TextView tvProgress;
    String localPath;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        result = getIntent().getStringExtra("result");

        View decorView = getWindow().getDecorView();
        if (decorView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) decorView;
            relativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            relativeLayout.setLayoutParams(rlParams);
            rlUpdate = new RelativeLayout(this);
            rlUpdate.setLayoutParams(rlParams);
            try {
                BitmapDrawable bitmapDrawable = new BitmapDrawable
                        (BitmapFactory.decodeStream(getAssets().open("icon_update.png")));
                rlUpdate.setBackgroundDrawable(bitmapDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rlUpdate.setVisibility(View.GONE);
            relativeLayout.addView(rlUpdate);

            RelativeLayout rlProgress = new RelativeLayout(this);
            RelativeLayout.LayoutParams rlParams2 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rlParams2.setMargins(dp2px(50), getScreenHeight(this) / 2 - dp2px(15),
                    dp2px(50), 0);
            rlProgress.setLayoutParams(rlParams2);
            rlUpdate.addView(rlProgress);
            progressBarABC = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            RelativeLayout.LayoutParams rlParams3 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    dp2px(26)
            );
            progressBarABC.setLayoutParams(rlParams3);
            progressBarABC.setMax(100);
            progressBarABC.setProgress(0);
            rlProgress.addView(progressBarABC);

            tvProgress = new TextView(this);
            RelativeLayout rlProgress2 = new RelativeLayout(this);
            RelativeLayout.LayoutParams rlParams4 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            RelativeLayout.LayoutParams rlParams5 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rlParams5.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            tvProgress.setLayoutParams(rlParams4);
            rlProgress2.setLayoutParams(rlParams5);
            rlProgress2.addView(tvProgress);
            tvProgress.setTextSize(14);
            tvProgress.setTextColor(Color.parseColor("#000000"));
            rlProgress.addView(rlProgress2);
            viewGroup.addView(relativeLayout);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        filter.addDataScheme("package");
        registerReceiver(receiver, filter);
        doTask();
    }

    private void doTask() {
        try {
            JSONObject jsonObject = new JSONObject(result);

            if (jsonObject.has("isUpdate")) {
                isUpdate = jsonObject.getString("isUpdate");
            }
            if (jsonObject.has("updateUrl")) {
                updateUrl = jsonObject.getString("updateUrl");
            }
            if ("1".equals(isUpdate) && !TextUtils.isEmpty(updateUrl)) {
                localPath = getAppRoot() + updateUrl.substring(updateUrl.lastIndexOf("/") + 1);
                rlUpdate.setVisibility(View.VISIBLE);
                new HttpUrlConnectionAsyncTask().downloadFile(new OnHttpProgressUtilListener() {
                    @Override
                    public void onError(String e) {
                        finish();
                    }

                    @Override
                    public void onProgress(final Integer length) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvProgress.setText(length + "%");
                                progressBarABC.setProgress(length);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(String json) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBarABC.setProgress(100);
                                tvProgress.setText(100 + "%");
                                installApk();
                            }
                        });
                    }
                }, updateUrl, localPath);
                return;
            }
            intentToMainActivity();
        } catch (Exception e) {
            e.printStackTrace();
            intentToMainActivity();
        }
    }

    private void intentToMainActivity() {
        //使用跳转到自己的首页的代码
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 安装APK文件BuildConfig
     */
    private void installApk() {
        File apkFile = new File(localPath);
        if (!apkFile.exists()) {
            finish();
            return;
        }
        Toast.makeText(this, getPackageName(apkFile.getAbsolutePath()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        if (this.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            this.startActivity(intent);
        }
    }

    public class HttpUrlConnectionAsyncTask extends AsyncTask<Integer, Integer, String> {

        private OnHttpProgressUtilListener onHttpProgressUtilListener;
        private String urlPath;
        private String filePath;

        public void downloadFile(OnHttpProgressUtilListener onHttpProgressUtilListener, String urlPath, String filePath) {
            this.onHttpProgressUtilListener = onHttpProgressUtilListener;
            this.urlPath = urlPath;
            this.filePath = filePath;
            //调用doInBackground方法（方法里面是异步执行）
            execute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return download();
        }

        private String download() {
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            String result = "";
            try {
                //获得URL对象
                URL url = new URL(urlPath);
                //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
                connection = (HttpURLConnection) url.openConnection();
                //建立实际链接
                connection.connect();
                connection.setConnectTimeout(8000);
                inputStream = connection.getInputStream();
                //获取文件长度
                int fileLength = connection.getContentLength();

                outputStream = new FileOutputStream(filePath);
                int count = 0;
                // 计算上传进度
                Long total = 0L;
                byte[] bytes = new byte[4096];
                while ((count = inputStream.read(bytes)) != -1) {
                    total += count;
                    onProgressUpdate((int) (total * 100 / fileLength));
                    outputStream.write(bytes, 0, count);
                }
                result = "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
                onCancelled(e.getMessage());
            } finally {
                //关闭
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            onHttpProgressUtilListener.onProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onHttpProgressUtilListener.onSuccess(s);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            onHttpProgressUtilListener.onError(s);
        }

    }

    public interface OnHttpProgressUtilListener {
        void onError(String e);

        void onProgress(Integer length);

        void onSuccess(String json);
    }

    /**
     * @return
     */
    public String getAppRoot() {
        String sdCardRoot = getSDCardRoot();
        if (sdCardRoot == null) {
            return null;
        }
        String path = getSDCardRoot() + "download/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 隐藏文件夹下的图片
        try {
            new File(path, ".nomedia").createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public String getSDCardRoot() {
        String state = Environment.getExternalStorageState();
        // 判断SdCard是否存在并且是可用的
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (Environment.getExternalStorageDirectory().canWrite()) {
                return Environment.getExternalStorageDirectory()
                        .getPath() + "/";
            }
        }
        return null;
    }

    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }


    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isInstallSuccess) {
            startActivity(new Intent("android.intent.action.DELETE",
                    Uri.parse("package:" + getPackageName())));
        }
    }

    boolean isInstallSuccess;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_PACKAGE_ADDED.equals(action) || Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
                isInstallSuccess = true;
            }
        }
    };

    public String getPackageName(String filePath) {
        String packageName = null;
        PackageManager packageManager = getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            packageName = appInfo.packageName;
        }
        return packageName;
    }
}
