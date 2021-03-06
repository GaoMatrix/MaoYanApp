package com.mao.movie;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by GaoMatrix on 2016/11/16.
 */
public class App extends Application {
    public String regId = "";
    public static App sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // zxing
        ZXingLibrary.initDisplayOpinion(this);

        // 极光
        JPushInterface.setDebugMode(true);     // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);             // 初始化 JPush

        // Logger
        Logger.init("Gao")                 // default PRETTYLOGGER or use just init()
              .methodCount(1)                 // default 2
              .hideThreadInfo()  ;             // default shown
              //.logLevel(LogLevel.NONE)        // default LogLevel.FULL
              //.methodOffset(2);                // default 0
              //.logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
    }
}

