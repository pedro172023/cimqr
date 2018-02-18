package com.example.pedro.cimqr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pedro on 22/10/2017.
 */

public class BaseHelper extends SQLiteOpenHelper{

    String tabla="CREATE TABLE PERSONAS(ID INTEGER PRIMARY KEY,NOMBRE TEXT,APELLIDO TEXT)";
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table personas");
        db.execSQL(tabla);
    }
}
