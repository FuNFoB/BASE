package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BD bd;
    SQLiteDatabase sqllite;
    public static EditText ed1;
    public static EditText ed2;
    TextView tx;
    Button btn;
    int id=0;
    public static final int CAMERA_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = new BD(this);
        btn = findViewById(R.id.button);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = bd.getWritableDatabase();
        ContentValues conten = new ContentValues();
        String log = ed1.getText().toString();
        String pas = ed2.getText().toString();
        if (ed1.length()<1 && ed2.length()<1){
            //ADD
                conten.put(bd.KEY_LOGIN, log);
                conten.put(bd.KEY_PASSWORD, pas);
                database.insert(bd.TableName,null, conten);
                //READ
            Cursor cursor = database.query(bd.TableName, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(bd.KEY_LOGIN);
                int emailIndex = cursor.getColumnIndex(bd.KEY_PASSWORD);
                do {
                    Log.d("mLog", ", name = " + cursor.getString(nameIndex) + ", email = " + cursor.getString(emailIndex));
                } while (cursor.moveToNext());
            } else
                Log.d("mLog","0 rows");

            cursor.close();
            //ПЕРЕХОД
                Intent intent = new Intent(this, Signin.class);
            startActivity(intent);
        }
        }

    }
