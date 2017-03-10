package com.bawei.todayheadline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.todayheadline.R;

import java.util.Timer;
import java.util.TimerTask;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Navigation.this,MainActivity.class));
                        timer.cancel();
                        finish();
                    }
                });
            }
        };
        timer.schedule(task,2000,1000);
    }
}
