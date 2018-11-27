package com.example.reg.activity;

/**
 * Created by Administrator on 2018/2/9.
 */

import android.app.Activity;
import android.os.Bundle;

import com.example.reg.util.ActivityCollector;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
