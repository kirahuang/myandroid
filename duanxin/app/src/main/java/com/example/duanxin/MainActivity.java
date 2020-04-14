package com.example.duanxin;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTextView;
    private Button mBtnButton;//声明添加的Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnButton = findViewById(R.id.button1);//找到按钮
        mBtnTextView = findViewById(R.id.button2);//找到按钮
        //注册监听器
        mBtnButton.setOnClickListener(this);//设置点击事件
        mBtnTextView.setOnClickListener(this);//设置点击事件
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                onClickButton1(view);
                break;
            case R.id.button2:
                onClickButton2(view);
                break;

        }
    }


    private void onClickButton1(View view) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("18930641508", null, "测试短信111", null, null);
        Toast toast = Toast.makeText(getApplicationContext(), "自定义Button111111Toast", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void onClickButton2(View view) {
        //处理逻辑

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);


        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Toast.makeText(getApplicationContext(), "权限失败", Toast.LENGTH_SHORT).show();

            return;
        }



            List<Integer> idList=new ArrayList<Integer>();
            SubscriptionManager subscriptionManager =  SubscriptionManager.from(getApplicationContext());
            List<SubscriptionInfo> infoList = subscriptionManager.getActiveSubscriptionInfoList();
            for (SubscriptionInfo info : infoList) {
                idList.add(info.getSubscriptionId());
            }
            SmsManager.getSmsManagerForSubscriptionId(idList.get(0))
                    .sendTextMessage("18930641508",null,"第二个测试短信消息11",null,null);
            SmsManager.getSmsManagerForSubscriptionId(idList.get(1))
                    .sendTextMessage("18930641508",null,"第二个测试短信消息22",null,null);


            Toast toast= Toast.makeText(getApplicationContext(), "自定义Button22222Toast", Toast.LENGTH_SHORT);
            toast.show();
        }




}
