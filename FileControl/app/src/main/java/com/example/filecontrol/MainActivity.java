package com.example.filecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testFileDemo();
//        try {
//            testAssets();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        testSDCard();
    }

    /**
     * demo for test file
     */
    private void testFileDemo(){
        String string = "Our teacher is handsome!";

        //父路径为getFileDir返回，名称为test.txt，在internal目录中创建test.tst文件
        File file = new File(getFilesDir(), "test.txt");
        try {
            boolean isSuccess = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = openFileOutput("test2.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //检测外部文件存储是否可用
        String state = Environment.getExternalStorageState();
        if(TextUtils.equals(state, Environment.MEDIA_MOUNTED)){

        }

    }

    void testAssets() throws IOException {
        //读取到webView中
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/test.html");

        //直接读取文件进行操作
        try {
            InputStream inputStream = getResources().getAssets().open("test.html");
        } catch (IOException e) {
            e.printStackTrace();
            //打开失败，使用Toast显示
            Toast.makeText(MainActivity.this, "文件读取异常", Toast.LENGTH_SHORT).show();
        }

        //读取文件夹，其中内容存储到列表中
        String[] fileNames;
        try {
            //共有两个，若用images作为文件夹名称则会将SDK中的assets中的images文件夹内容也读入！
            fileNames = getAssets().list("myImages");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取图片，放入imageView中
        InputStream ips;
        ips = getAssets().open("images/nik_1.jpg");
        Bitmap bitmap = BitmapFactory.decodeStream(ips);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);

        //读音频
        AssetFileDescriptor assetFileDescriptor = getAssets().openFd("the_loneliest_girl.mp3");
        MediaPlayer player = new MediaPlayer();
        player.reset();
        player.setDataSource(
                assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(),
                assetFileDescriptor.getLength()
        );
        player.prepare();
        player.start();

    }

    /**
     * res/raw文件使用
     */
    void testResFile(){
        InputStream inputStream = getResources().openRawResource(R.raw.the_loneliest_girl);

        int color = getResources().getColor(R.color.colorAccent);
        String string = getResources().getString(R.string.app_name);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);
        XmlResourceParser xmlResourceParser = getResources().getLayout(R.layout.activity_main);
    }

    /**
     * SD卡文件
     */
    void testSDCard(){
        //不使用这种方法，不同厂商文件系统路径名不同！
//        File file = new File("/sdcard/test/a.txt");

        //获取各个文件夹的父目录！
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();//外部存储父目录（被拒绝）
        Log.i(TAG, "path: " + filePath);
        filePath = Environment.getDataDirectory().getAbsolutePath(); //获取Android中的data数据目录父目录
        Log.i(TAG, "path: " + filePath);
        filePath = Environment.getDownloadCacheDirectory().getAbsolutePath();//获取下载目录父目录
        Log.i(TAG, "path: " + filePath);
    }
}
