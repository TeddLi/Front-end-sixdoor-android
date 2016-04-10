package com.nku.liushanmen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.focuslee.helloworldactivity.R.layout.activity_main);
        ImageButton mbutton1=(ImageButton)findViewById(com.example.focuslee.helloworldactivity.R.id.pic1);
        ImageButton mbutton2=(ImageButton)findViewById(com.example.focuslee.helloworldactivity.R.id.pic2);
        ImageButton mbutton3=(ImageButton)findViewById(com.example.focuslee.helloworldactivity.R.id.pic3);
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            //把这个按钮的功能设置为跳转
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"xxx",Toast.LENGTH_SHORT).show();
                Intent _intent = new Intent(MainActivity.this, nightModeActivity.class);
                startActivity(_intent);
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            //把这个按钮的功能设置为跳转
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"xxx",Toast.LENGTH_SHORT).show();
                Intent _intent = new Intent(MainActivity.this,registerActivity.class);
                startActivity(_intent);
            }
        });
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            //把这个按钮的功能设置为跳转
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"xxx",Toast.LENGTH_SHORT).show();
                Intent _intent = new Intent(MainActivity.this,Loginpage1Activity.class);
                startActivity(_intent);
            }
        });
    }
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.pic1:
//                Intent _intent2 = new Intent(MainActivity.this, loginactivity.class);
//                startActivity(_intent2);
//                break;
//            case R.id.pic2:
//                Intent _intent1 = new Intent(MainActivity.this, nightModeActivity.class);
//                startActivity(_intent1);
//                break;
//            case R.id.pic3:
//                Intent _intent = new Intent(MainActivity.this, nightModeActivity.class);
//                startActivity(_intent);
//                break;
//
//        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.focuslee.helloworldactivity.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.focuslee.helloworldactivity.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
