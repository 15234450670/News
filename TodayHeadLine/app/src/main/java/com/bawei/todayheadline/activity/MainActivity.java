package com.bawei.todayheadline.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.todayheadline.R;
import com.bawei.todayheadline.bean.TabBean;
import com.bawei.todayheadline.fragment.Frag_tui;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
   List<String>title = new ArrayList<>();
    List<Fragment>list_fr = new ArrayList<>();
    private DrawerLayout dl;
    private ImageView image_cela;
    private TabLayout tab;
    private ListView list_view;
    private ViewPager vp;
    private List<TabBean.DataBeanX.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
       initView();
        getData();

        image_cela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void getData() {

        RequestParams params = new RequestParams("http://ic.snssdk.com/article/category/get/v2/?user_city=%E5%AE%89%E9%98%B3&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465099837&categories=%5B%22video%22%2C%22news_hot%22%2C%22news_local%22%2C%22news_society%22%2C%22subscription%22%2C%22news_entertainment%22%2C%22news_tech%22%2C%22news_car%22%2C%22news_sports%22%2C%22news_finance%22%2C%22news_military%22%2C%22news_world%22%2C%22essay_joke%22%2C%22image_funny%22%2C%22image_ppmm%22%2C%22news_health%22%2C%22positive%22%2C%22jinritemai%22%2C%22news_house%22%5D&version=17375902057%7C14%7C1465030267&iid=4471477475&device_id=17375902057&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Samsung+Galaxy+S3+-+4.3+-+API+18+-+720x1280&os_api=18&os_version=4.3&openudid=7036bc89d44f680c");
        x.http().get(params, new Callback.CacheCallback<String>() {

            @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        TabBean tabBean = gson.fromJson(result, TabBean.class);
                data = tabBean.getData().getData();
                for (int i = 0; i < data.size(); i++) {
                   tab.addTab(tab.newTab().setText(data.get(i).getName()));
                    Log.i("xxx",data.toString());
                }
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

    private void initView() {
        image_cela = (ImageView) findViewById(R.id.image_cela);
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        tab = (TabLayout) findViewById(R.id.tab);
        ListView list_view =  (ListView) findViewById(R.id.list_view);
        vp = (ViewPager) findViewById(R.id.vp);
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fr.get(position);
        }

        @Override
        public int getCount() {
            return list_fr.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
