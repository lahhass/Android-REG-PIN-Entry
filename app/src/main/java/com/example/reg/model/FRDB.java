package com.example.reg.model;

/**
 * Created by Administrator on 2018/2/9.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.reg.db.FROpenHelper;
import com.example.reg.util.LogUtil;
import com.example.reg.util.MyApplication;

public class FRDB {

    public static final String DB_NAME = "ioc";

    public static final int VERSION = 1;

    private static FRDB frDB;

    private SQLiteDatabase db;

    private FRDB(Context context) {
        FROpenHelper dbHelper = new FROpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    public synchronized static FRDB getInstance(Context context) {
        if (frDB == null) {
            frDB = new FRDB(context);
        }
        return frDB;
    }


    public void saveAccount(Account account) {
        if (account != null) {
            ContentValues values = new ContentValues();
            values.put("name", account.getName());
            values.put("password", account.getPassword());
            db.insert("Account", null, values);
        }
    }

    // Login or register validate
    public boolean validate(Account account, int flag) {
        Cursor cursor = db.
                rawQuery("select * from Account where name like ?", new String[]{account.getName()});

        if (cursor == null) {
            LogUtil.d("FRDB", "cursor == null");
            return false;
        }

        //0: register, 1: login
        switch (flag) {
            case 0:
                if (cursor.getCount() == 0) {
                    return true;
                } else {
                    Toast.makeText(MyApplication.getContext(), "The username has been registered.",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 1:
                if (cursor.getCount() == 0 || cursor.getCount() > 1 ) {
                    Toast.makeText(MyApplication.getContext(), "Problem",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    // only one data matched
                    if (cursor.moveToFirst()) {
                        String passwordGet;
                        passwordGet = cursor.getString(cursor.getColumnIndex("password"));
                        if (passwordGet.equals(account.getPassword())) {
                            return true;
                        } else {
                            Toast.makeText(MyApplication.getContext(), "Incorrect username or password.",
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                }
            default:
        }
        return false;
    }

}


