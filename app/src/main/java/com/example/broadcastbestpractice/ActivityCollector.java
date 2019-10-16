package com.example.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//活动管理类
public class ActivityCollector {

    //活动容器
    public static List<Activity> activities = new ArrayList<>();

    //往容器总添加活动
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    //从容器中移除活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    //清除所有活动
    public static void finishAll(){
        for(Activity activity: activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
