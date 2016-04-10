package com.nku.liushanmen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by focus.lee on 2016/3/13.
 */
public class taxiModeActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.example.focuslee.helloworldactivity.R.layout.activity_taxi);
        Button mbutton1=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.tjump1);
        Button mbutton2=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.tjump2);
        Button mbutton3=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.tjump3);
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(taxiModeActivity.this,MainActivity.class);
                startActivity(_intent);
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(taxiModeActivity.this,dataModeActivity.class);
                startActivity(_intent);
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(taxiModeActivity.this,nightModeActivity.class);
                startActivity(_intent);
            }
        });
    }

}
