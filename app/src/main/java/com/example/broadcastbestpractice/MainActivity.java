package com.example.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOffline = findViewById(R.id.btn_offline);
        btnOffline.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_offline:
                Intent intent = new Intent("com.example.broadcasebestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
                break;
            default:
                break;
        }
    }
}
