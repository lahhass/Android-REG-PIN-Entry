package com.example.reg.activity;

/**
 * Created by Administrator on 2018/2/9.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.reg.R;
import com.example.reg.model.Account;
import com.example.reg.model.FRDB;

public class LoginActivity extends BaseActivity {

    private EditText accountLogin;
    private Button inputPwd;
    private String name;
    private String pwd;
    private FRDB frDB;
    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountLogin = (EditText) findViewById(R.id.account_login);
        inputPwd = (Button) findViewById(R.id.password_input);
        frDB = FRDB.getInstance(this);

        inputPwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = accountLogin.getText().toString();
                //跳转输入密码后返回密码再进行验证
                Intent intent = new Intent(LoginActivity.this, REGActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    pwd = data.getStringExtra("pwd_return");
                    hour = data.getIntExtra("hour", 0);
                    minute = data.getIntExtra("minute", 0);
                    second = data.getIntExtra("second", 0);
                    Account newAccount = new Account();
                    newAccount.setName(name);
                    newAccount.setPassword(pwd);
                    if (frDB.validate(newAccount, 1)) {
                        Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("hour", hour);
                        bundle.putInt("minute", minute);
                        bundle.putInt("second", second);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }
        }
    }
}


