package com.bawei.todayheadline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.todayheadline.R;

/**
 * 类的用途：
 * Created by ：杨珺达
 * date：2017/3/10
 */

public class Frag_tui extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag, null);
        return view;
    }
}
