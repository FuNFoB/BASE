package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {
    public static final String DBName="student.db";
    public static final String TableName = "BDN";
    public static final int Ver = 1;
    public static final String KEY_LOGIN="login";
    public static final String KEY_PASSWORD="password";
    public BD(@Nullable Context context) {
        super(context, DBName, null, Ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create TABLE "+TableName+"" +
                "(id integer primary key, avatar blob, " +
                KEY_LOGIN+" txt, "+KEY_PASSWORD+" txt)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "drop table if exists "+TableName+"";
        db.execSQL(query);
    }
}
