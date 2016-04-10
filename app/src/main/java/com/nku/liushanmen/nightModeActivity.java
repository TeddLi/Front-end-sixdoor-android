package com.nku.liushanmen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import  android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
//// TODO: 2016/3/14 将button改成imagebutton会报错
/**
 * Created by focus.lee on 2016/3/13.
 */
public class nightModeActivity extends AppCompatActivity implements View.OnClickListener{

    FrameLayout fram1,fram2,fram3;
    //模式标记
    int flag1=0;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.example.focuslee.helloworldactivity.R.layout.activity_night);
        Button mbutton1=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump1);
        Button mbutton2=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump2);
        Button mbutton3=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump3);
        Button mbutton4=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump4);
        Button mbutton5=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump5);
        Button mbutton6=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.njump6);
        mbutton1.setOnClickListener(this);
        mbutton2.setOnClickListener(this);
        mbutton3.setOnClickListener(this);
        mbutton4.setOnClickListener(this);
        mbutton5.setOnClickListener(this);
        mbutton6.setOnClickListener(this);
           fram1=(FrameLayout)findViewById(com.example.focuslee.helloworldactivity.R.id.F1);
          fram2=(FrameLayout)findViewById(com.example.focuslee.helloworldactivity.R.id.F2);
          fram3=(FrameLayout)findViewById(com.example.focuslee.helloworldactivity.R.id.F3);


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
         case com.example.focuslee.helloworldactivity.R.id.njump1:
             fram1.setVisibility(View.VISIBLE);
             fram2.setVisibility(View.INVISIBLE);
             fram3.setVisibility(View.INVISIBLE);
             break;
         case com.example.focuslee.helloworldactivity.R.id.njump2:
             fram1.setVisibility(View.INVISIBLE);
             fram2.setVisibility(View.VISIBLE);
             fram3.setVisibility(View.INVISIBLE);
             break;
         case com.example.focuslee.helloworldactivity.R.id.njump3:
             fram1.setVisibility(View.INVISIBLE);
             fram2.setVisibility(View.INVISIBLE);
             fram3.setVisibility(View.VISIBLE);
             break;
         case com.example.focuslee.helloworldactivity.R.id.nbt1:
             new bluealarmThread().execute();

     }

    }
//    todo 改一下蓝色预警的返回信息
    private class bluealarmThread extends AsyncTask {
        private final String status;

        public bluealarmThread() {
            status="blue";
            flag1=1;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String param = "";
            param = "status";
            try {
                param = URLEncoder.encode(param.trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://2.famelguarder.applinzi.com/user/login?";
            Log.e("conductor", "SearchResultActivity:" + url);
            Log.e("conductor", "SearchResultActivity,param:" + param);
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();
            Call call = okHttpClient.newCall(request);
            JSONArray array = null;
            try {
                String result = call.execute().body().string();
                Log.e("conductor", "result:" + result);
                array = new JSONArray(result);
            } catch (JSONException | NullPointerException | IOException e) {
                e.printStackTrace();
            }

            return array;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o.equals(null)) {
                Toast.makeText(getApplicationContext(), "联网失败", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONArray array = (JSONArray) o;
           int p = array.getInt();
            Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();

        }


    }
    private class yellowalarmThread extends AsyncTask  {
        private final String status;
        int alarm=0;
        public yellowalarmThread() {
            status="blue";
        }

       //这个是传感器的监听

       private class mySensorListener implements SensorEventListener
       {
           public void onSensorChanged(SensorEvent event) {
               int i = 0;
               for (; flag1==2&&i < 30; i++) {
                   float x = event.values[0];
                   float y = event.values[1];
                   float z = event.values[2];
                   if (x * x + y * y + z * z >= 10) {
                       flag1 = 2;

                   }
                   else
                   {
                       i=0;
                   }
                   try{
                       Thread.currentThread().sleep(1000);
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }
               }
               alarm=1;
           }
           public void onAccuracyChanged(Sensor sensor, int accuracy) {
           }

       }

        @Override
        protected Object doInBackground(Object[] params) {
            String param = "";

            //          todo 这个services_name怎么写  下面五行是传感器的部分
            SensorManager sensorManager = (SensorManager)getSystemService(SERVICE_NAME);
            Sensor accelerSensor = sensorManager.getDefaultSensor(Sensor. TYPE_ACCELEROMETER);
           mySensorListener myslistener=new mySensorListener();
            sensorManager.registerListener(myslistener, accelerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.unregisterListener(myslistener);
            if(alarm==1)//如果是快速跑动  静止的部分需要用百度地图实现
            param = "status";
            try {
                param = URLEncoder.encode(param.trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//            todo 以后需要改一下这里的url
            String url = "http://2.famelguarder.applinzi.com/user/login?";
            Log.e("conductor", "SearchResultActivity:" + url);
            Log.e("conductor", "SearchResultActivity,param:" + param);
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder().url(url).build();
            Call call = okHttpClient.newCall(request);
            JSONArray array = null;
            try {
                String result = call.execute().body().string();
                Log.e("conductor", "result:" + result);
                array = new JSONArray(result);
            } catch (JSONException | NullPointerException | IOException e) {
                e.printStackTrace();
            }

            return array;
        }

        }

        @Override
        protected void onPostExecute(Object o) {
            if (o==null) {
                Toast.makeText(getApplicationContext(), "联网失败", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONArray array = (JSONArray) o;
            JSONObject OBJ1=array.getString("success");
            Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();

        }


    }





}
