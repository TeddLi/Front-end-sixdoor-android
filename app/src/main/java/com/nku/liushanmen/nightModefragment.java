package com.nku.liushanmen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by focus.lee on 2016/3/14.
 */
//todo 是不是也可以在这里实现fragment的监听
public class nightModefragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container ,Bundle savedInstanceState)
    {
        return inflater.inflate(com.example.focuslee.helloworldactivity.R.layout.nightfragment,container,false);

    }
}
