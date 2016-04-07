package com.example.focuslee.helloworldactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by focus.lee on 2016/3/18.
 */
public class Loginpage1Activity  extends AppCompatActivity  {
            String ac_n,passwo;

        protected void  onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            Button button1=(Button)findViewById(R.id.page1_login);
            EditText page1Acc=(EditText)findViewById(R.id.page1_Accountnum);
            EditText page1pas=(EditText)findViewById(R.id.page1_password);

              ac_n=page1Acc.getText().toString();
             passwo=page1pas.getText().toString();
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LoginThread( ac_n,passwo);
                }
            });


        }
        private class LoginThread extends AsyncTask
        {
            private final  String Account_number;
            private final  String Password;
            public LoginThread(String AC_n,String Passw)
            {
                Account_number=AC_n;
                Password=Passw;
            }

            @Override
            protected Object doInBackground(Object[] params) {
                String param="";
                    param=Account_number+" "+Password;
                try {
                    param = URLEncoder.encode(param.trim(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String url = Connection.HOST + "guide/" + reviewObj + "/" + param;
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
                JSONArray array = (JSONArray) o;
                try {
                    list.clear();
                    initList(array);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "联网成功", Toast.LENGTH_SHORT).show();
                    //listView.performItemClick(null, 2, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
        }



}
