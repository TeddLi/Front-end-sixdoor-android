package com.example.focuslee.helloworldactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
//// TODO: 2016/3/14 将button改成imagebutton会报错
/**
 * Created by focus.lee on 2016/3/13.
 */
public class nightModeActivity extends AppCompatActivity implements View.OnClickListener{

    FrameLayout fram1,fram2,fram3;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);
        Button mbutton1=(Button)findViewById(R.id.njump1);
        Button mbutton2=(Button)findViewById(R.id.njump2);
        Button mbutton3=(Button)findViewById(R.id.njump3);
        Button mbutton4=(Button)findViewById(R.id.njump4);
        Button mbutton5=(Button)findViewById(R.id.njump5);
        Button mbutton6=(Button)findViewById(R.id.njump6);
        mbutton1.setOnClickListener(this);
        mbutton2.setOnClickListener(this);
        mbutton3.setOnClickListener(this);
        mbutton4.setOnClickListener(this);
        mbutton5.setOnClickListener(this);
        mbutton6.setOnClickListener(this);
           fram1=(FrameLayout)findViewById(R.id.F1);
          fram2=(FrameLayout)findViewById(R.id.F2);
          fram3=(FrameLayout)findViewById(R.id.F3);

        //fragment 上的组件

      /*  mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fram1.setVisibility(View.VISIBLE);
                fram2.setVisibility(View.INVISIBLE);
                fram3.setVisibility(View.INVISIBLE);

            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(nightModeActivity.this,dataModeActivity.class);
                startActivity(_intent);
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(nightModeActivity.this,taxiModeActivity.class);
                startActivity(_intent);
            }
        });
    */
    }
    public void onClick(View v) {
     switch (v.getId()) {
         case R.id.njump1:
             fram1.setVisibility(View.VISIBLE);
             fram2.setVisibility(View.INVISIBLE);
             fram3.setVisibility(View.INVISIBLE);
             break;
         case R.id.njump2:
             fram1.setVisibility(View.INVISIBLE);
             fram2.setVisibility(View.VISIBLE);
             fram3.setVisibility(View.INVISIBLE);
             break;
         case R.id.njump3:
             fram1.setVisibility(View.INVISIBLE);
             fram2.setVisibility(View.INVISIBLE);
             fram3.setVisibility(View.VISIBLE);
             break;
     }

    }
}
