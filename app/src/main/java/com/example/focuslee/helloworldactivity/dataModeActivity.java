package com.example.focuslee.helloworldactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by focus.lee on 2016/3/13.
 */
public class dataModeActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Button mbutton1=(Button)findViewById(R.id.djump1);
        Button mbutton2=(Button)findViewById(R.id.djump2);
        Button mbutton3=(Button)findViewById(R.id.djump3);
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(dataModeActivity.this,MainActivity.class);
                startActivity(_intent);
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(dataModeActivity.this,nightModeActivity.class);
                startActivity(_intent);
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent=new Intent(dataModeActivity.this,taxiModeActivity.class);
                startActivity(_intent);
            }
        });
    }
}
