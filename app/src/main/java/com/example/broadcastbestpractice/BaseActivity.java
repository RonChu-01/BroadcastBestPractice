package com.example.broadcastbestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private ForceOfflineBroadcastReceiver forceOfflineBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasebestpractice.FORCE_OFFLINE");
        forceOfflineBroadcastReceiver = new ForceOfflineBroadcastReceiver();
        registerReceiver(forceOfflineBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (forceOfflineBroadcastReceiver != null){
            unregisterReceiver(forceOfflineBroadcastReceiver);
            forceOfflineBroadcastReceiver = null;
        }
    }

    //强制下线广播接收器
    class ForceOfflineBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            //弹框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("下线");
            builder.setMessage("warning: offline!");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();  //销毁活动
                    Intent intent1 = new Intent(context, LoginActivity.class);
                    startActivity(intent1);  //重启活动
                }
            });
            builder.show();
        }
    }
}
