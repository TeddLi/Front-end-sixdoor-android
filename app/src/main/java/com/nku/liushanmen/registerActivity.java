package com.nku.liushanmen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by focus.lee on 2016/3/18.
 */
public class registerActivity   extends AppCompatActivity {
    String phone_num,yan_zheng,set_password,confirm_password,yanzhengget="";
    protected void  onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.example.focuslee.helloworldactivity.R.layout.activity_register);
        Button button1=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.page2fasongyanzhengma);
        Button button2=(Button)findViewById(com.example.focuslee.helloworldactivity.R.id.page2finish);
        EditText phonenum = (EditText) findViewById(com.example.focuslee.helloworldactivity.R.id.phonenum);
        EditText yanzh= (EditText) findViewById(com.example.focuslee.helloworldactivity.R.id.yanzheng);
        EditText setpass= (EditText) findViewById(com.example.focuslee.helloworldactivity.R.id.setpassword);
        EditText confirmpass= (EditText) findViewById(com.example.focuslee.helloworldactivity.R.id.confirmpassword);
        phone_num=phonenum.getText().toString();
        yan_zheng=yanzh.getText().toString();
        set_password=setpass.getText().toString();
        confirm_password=confirmpass.getText().toString();
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           new yanzhengThread(phone_num).execute();
                                       }
                                   }
        );
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           new registerThread(phone_num,yan_zheng,set_password,confirm_password).execute();
                                       }
                                   }
        );
    }
    private class registerThread extends AsyncTask {
        private final String phonenumber1;
        private final String yanzheng1;
        private final String setpassword1;
        private final String confirmpassword1;
        int passflag=0,yanflag=0;

        public registerThread(String ph,String yan,String setpa,String confi) {
            phonenumber1=ph;
            yanzheng1=yan;
            setpassword1=setpa;
            confirmpassword1=confi;
            if(setpa.equals(confi))
                passflag=1;
            if(yan.equals(yanzhengget))
                yanflag=1;

        }

        @Override
        protected Object doInBackground(Object[] params) {
            String param = "";
            param = phonenumber1+" "+yanzheng1+" "+setpassword1+" "+confirmpassword1;
            try {
                param = URLEncoder.encode(param.trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://famelguarder.applinzi.com/register/";
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
            if(passflag==0) {
                Toast.makeText(getApplicationContext(), "设置密码与确认密码不一致", Toast.LENGTH_SHORT).show();
            }
            if(yanflag==0)
            {
                Toast.makeText(getApplicationContext(), "请输入正确验证码", Toast.LENGTH_SHORT).show();

            }
            JSONArray array = (JSONArray) o;
            Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();

        }


    }
    private class yanzhengThread extends AsyncTask {
        private final String phonenumber;

        public yanzhengThread(String AC_n) {
            phonenumber=AC_n;

        }

        @Override
        protected Object doInBackground(Object[] params) {
            String param = "";
            param = phonenumber;
            try {
                param = URLEncoder.encode(param.trim(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String url = "http://famelguarder.applinzi.com/register/";
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
            yanzhengget=o.toString();
            JSONArray array = (JSONArray) o;
            Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();

        }


    }








}
