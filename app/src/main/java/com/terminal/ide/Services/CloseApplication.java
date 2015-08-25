package com.terminal.ide.Services;


import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

/**
 * @author 夏末
 *         2015.3.28
 */
public class CloseApplication extends Application {

    private List<Activity> activityList = new LinkedList<Activity>();
    private static CloseApplication instance;//创建的静态对象，避免每次使用的实例化

    private CloseApplication() {
        //空构造函数
    }

    //实例化一次
    public synchronized static CloseApplication getInstance() {
        if (instance == null) {
            instance = new CloseApplication();
        }
        return instance;
    }

    /**
     * @param activity 将当前activity添加到activity集合中
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);

    }

    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            Log.e("com.termial.ide.Services.Closeapplication", "关闭activity错误：" + e.toString());
        } finally {
            System.exit(0);
        }

    }

    //杀进程
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
