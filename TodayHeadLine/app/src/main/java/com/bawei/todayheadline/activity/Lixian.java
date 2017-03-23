package com.bawei.todayheadline.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.todayheadline.R;
import com.bawei.todayheadline.bean.TabBean;
import com.bawei.todayheadline.utils.IsnetWork;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * 类的用途：
 * Created by ：杨珺达
 * date：2017/3/17
 */

public class Lixian extends Activity {
    private List<TabBean.DataBeanX.DataBean> data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lixian);
        listView = (ListView) findViewById(R.id.list_lixian);
        ImageView tui = (ImageView) findViewById(R.id.tui);
        TextView teloading = (TextView) findViewById(R.id.loading);
        tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lixian.this,MainActivity.class));
                finish();
            }
        });
        teloading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNet = IsnetWork.IsNet(Lixian.this);
                boolean wifi = IsnetWork.isWIFI(Lixian.this);
                boolean mobile = IsnetWork.isMobile(Lixian.this);
                if (isNet){
                    if (wifi){
                    getLoading();
                    }
                    if (mobile){
                        AlertDialog.Builder bu = new AlertDialog.Builder(Lixian.this);
                        bu.setTitle("请考虑");
                        bu.setMessage("也许会消耗大量流量");
                        bu.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Lixian.this, "您以取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                        bu.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getLoading();
                            }
                        });
                    }
                }else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Lixian.this);
                    builder.setTitle("提示");
                    builder.setMessage("请开启网络连接");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.create().dismiss();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //通过隐式开启
                            Intent intent=null;
                            //有个版本号的判断
                            if(android.os.Build.VERSION.SDK_INT>10){
                                intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            }else {
                                intent = new Intent();
                                intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");

                                intent.setAction("android.intent.action.VIEW");

                            }
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                }
            }
        });
        //解析数据
        RequestParams params = new RequestParams("http://ic.snssdk.com/article/category/get/v2/?user_city=%E5%AE%89%E9%98%B3&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465099837&categories=%5B%22video%22%2C%22news_hot%22%2C%22news_local%22%2C%22news_society%22%2C%22subscription%22%2C%22news_entertainment%22%2C%22news_tech%22%2C%22news_car%22%2C%22news_sports%22%2C%22news_finance%22%2C%22news_military%22%2C%22news_world%22%2C%22essay_joke%22%2C%22image_funny%22%2C%22image_ppmm%22%2C%22news_health%22%2C%22positive%22%2C%22jinritemai%22%2C%22news_house%22%5D&version=17375902057%7C14%7C1465030267&iid=4471477475&device_id=17375902057&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Samsung+Galaxy+S3+-+4.3+-+API+18+-+720x1280&os_api=18&os_version=4.3&openudid=7036bc89d44f680c");
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TabBean tabBean = gson.fromJson(result, TabBean.class);
                data = tabBean.getData().getData();
                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return data.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return data.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        convertView = View.inflate(Lixian.this,R.layout.lixian_other,null);
                        TextView te_li = (TextView) convertView.findViewById(R.id.te_li);
                        CheckBox ch = (CheckBox) convertView.findViewById(R.id.te_c);

                        te_li.setText(data.get(position).getName());
                        return convertView;
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    private void getLoading() {
        RequestParams params = new RequestParams("http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html");
x.http().post(params, new Callback.ProgressCallback<File>() {


    @Override
    public void onSuccess(File result) {
      Gson gson = new Gson();

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {

    }
});
    }


}
