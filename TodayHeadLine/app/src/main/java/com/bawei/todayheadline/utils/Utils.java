package com.bawei.todayheadline.utils;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * 类的用途：
 * Created by ：杨珺达
 * date：2017/3/10
 */

public class Utils extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
       x.Ext.setDebug(false);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
}


}
