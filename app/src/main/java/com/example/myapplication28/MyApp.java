package com.example.myapplication28;

import android.app.Application;

public class MyApp extends Application {
    /**
     * 小工具专用生命周期。
     *
     * @param app
     * @return
     */
    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
