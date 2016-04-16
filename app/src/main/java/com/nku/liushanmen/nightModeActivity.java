package com.nku.liushanmen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

import  android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.Poi;
import android.app.AlertDialog;
import android.app.Dialog;
//// TODO: 2016/3/14 将button改成imagebutton会报错
/**
 * Created by focus.lee on 2016/3/13.
 */
// TODO: 2016/4/15 把蓝色预警和传感器的逻辑改一下 
public class nightModeActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed1,ed2,e1,e2;
    FrameLayout fram1, fram2, fram3;
    mySensorListener myslistener;
    SensorManager sensorManager;
    //模式标记
    int flag1 = 0;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);
        Button mbutton1 = (Button) findViewById(R.id.njump1);
        Button mbutton2 = (Button) findViewById(R.id.njump2);
        Button mbutton3 = (Button) findViewById(R.id.njump3);
        Button mbutton4 = (Button) findViewById(R.id.njump4);
        Button mbutton5 = (Button) findViewById(R.id.njump5);
        Button mbutton6 = (Button) findViewById(R.id.njump6);
        ed1=(EditText) findViewById(R.id.edcar);
        ed2=(EditText) findViewById(R.id.eddes);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        mbutton1.setOnClickListener(this);
        mbutton2.setOnClickListener(this);
        mbutton3.setOnClickListener(this);
        mbutton4.setOnClickListener(this);
        mbutton5.setOnClickListener(this);
        mbutton6.setOnClickListener(this);
        fram1 = (FrameLayout) findViewById(R.id.F1);
        fram2 = (FrameLayout) findViewById(R.id.F2);
        fram3 = (FrameLayout) findViewById(R.id.F3);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        myslistener = new mySensorListener();
        sensorManager.registerListener(myslistener, accelerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

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

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于10
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClient.setLocOption(option);
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
                LayoutInflater inflater = getLayoutInflater();

                View layout = inflater.inflate(R.layout.carlisence, (ViewGroup) findViewById(R.id.dialog));new AlertDialog.Builder(this).setTitle("自定义布局").setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ed1.setText(e1.getText().toString());
                    ed2.setText(e2.getText().toString());
                }
            }).setNegativeButton("取消", null).show();


                break;
            case R.id.nbt1:
                new bluealarmThread().execute();

        }

    }

    //定位线程
    public class MyLocationListener implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }


    //    todo 改一下蓝色预警的返回信息
    private class bluealarmThread extends AsyncTask {
        private final String status;

        public bluealarmThread() {
            status = "blue";
            flag1 = 1;
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
            if (o == null) {
                Toast.makeText(getApplicationContext(), "联网失败", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONObject JSOB = null;
            try {
                JSOB = new JSONObject(o.toString());
                String result = JSOB.getString("success");
                Log.e("result", result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();

        }

        public void onDestroyy() {
            sensorManager.unregisterListener(myslistener);
        }
    }

    private class mySensorListener implements SensorEventListener {
        long lastGetA;

        public void onSensorChanged(SensorEvent event) {
            final double alpha = 0.8;
            double[] gravity = new double[3];
            double[] linear_acceleration = new double[3];
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];
            // if (System.currentTimeMillis()-lastGetA <1000) return;
            Log.e("sensor", String.format("x:%f,y:%f,z:%f", x, y, z));
            if (linear_acceleration[0] * linear_acceleration[0] + linear_acceleration[1] * linear_acceleration[1] + linear_acceleration[2] * linear_acceleration[2] >= 100) {
                flag1 = 2;
                //建立一个新的页面
            }

            //  lastGetA = System.currentTimeMillis();

            // alarm=1;
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }


    }

    private class yellowalarmThread extends AsyncTask {
        private final String status;
        int alarm = 0;

        public yellowalarmThread() {
            status = "blue";
        }


        @Override
        protected Object doInBackground(Object[] params) {
            String param = "";

            //          todo 这个services_name怎么写  下面五行是传感器的部分
            if (alarm == 1)//如果是快速跑动  静止的部分需要用百度地图实现
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
}






