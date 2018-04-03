package com.xiaofeng.easypay;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnWx;
    private Button btnAli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        btnWx.setOnClickListener(this);
        btnAli.setOnClickListener(this);
    }

    private void initView() {
        btnWx = findViewById(R.id.btn_wx);
        btnAli = findViewById(R.id.btn_ali);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_wx:
                openWechatScan();
                break;
            case R.id.btn_ali:
                openAliScan();
                break;
        }
    }

    private void openAliScan() {
        try{
            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,R.string.no_ali,Toast.LENGTH_SHORT).show();
        }

    }

    private void openWechatScan(){
        try{
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,R.string.no_wx,Toast.LENGTH_SHORT).show();
        }
    }
}
