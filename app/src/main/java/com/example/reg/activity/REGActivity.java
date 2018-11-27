package com.example.reg.activity;

/**
 * Created by Administrator on 2018/2/9.
 */

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.reg.R;
import com.example.reg.model.MyProgress;
import com.example.reg.model.Set;
import com.example.reg.util.LogUtil;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class REGActivity extends BaseActivity {

    private Button one = (Button) findViewById(R.id.one);
    private Button two = (Button) findViewById(R.id.two);
    private Button three = (Button) findViewById(R.id.three);
    private Button four = (Button) findViewById(R.id.four);
    private Button five = (Button) findViewById(R.id.five);
    private Button six = (Button) findViewById(R.id.six);
    private Button seven = (Button) findViewById(R.id.seven);
    private Button eight = (Button) findViewById(R.id.eight);
    private Button nine = (Button) findViewById(R.id.nine);
    private Button zero = (Button) findViewById(R.id.zero);
    private Button del = (Button) findViewById(R.id.del);
    private Button finish = (Button) findViewById(R.id.finish);
    private TextView pwdShow = (TextView) findViewById(R.id.password_show);
    private String value = "";
    private Calendar calendar1;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int pwdLen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "1";
                pwdAdd();
            }
        });
        two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "2";
                pwdAdd();
            }
        });
        three.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "3";
                pwdAdd();
            }
        });
        four.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "4";
                pwdAdd();
            }
        });
        five.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "5";
                pwdAdd();
            }
        });
        six.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "6";
                pwdAdd();
            }
        });
        seven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "7";
                pwdAdd();
            }
        });
        eight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "8";
                pwdAdd();
            }
        });
        nine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "9";
                pwdAdd();
            }
        });
        zero.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + "0";
                pwdAdd();
            }
        });
        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdDel();
            }
        });
        finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                calculate();
                intent.putExtra("pwd_return", value);
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                intent.putExtra("second", second);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    public void pwdAdd() {
        if (pwdLen == 0) {
            calendar1 = Calendar.getInstance();
            pwdLen = 1;
        }
        String data = pwdShow.getText().toString();
        data = data + "●";
        pwdShow.setText(data);
    }


    public void pwdDel() {
        String data = pwdShow.getText().toString();
        if (!data.equals("")) {
            data = data.substring(0, data.length()-1);
        }
        value = value.substring(0, value.length()-1);
        pwdShow.setText(data);
    }

    public void calculate() {
        Calendar calendar2 = Calendar.getInstance();
        hour = calendar2.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY);
        minute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE);
        second = calendar2.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND);
        if (minute < 0) {
            hour = hour - 1;
            minute = minute + 60;
        }
        if (second < 0) {
            minute = minute - 1;
            second = second + 60;
        }
    }
}

