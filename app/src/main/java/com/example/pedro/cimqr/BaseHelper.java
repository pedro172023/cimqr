package com.example.pedro.cimqr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pedro on 22/10/2017.
 */

public class BaseHelper extends SQLiteOpenHelper{

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CUENTA(ID INTEGER PRIMARY KEY,NOMBRE TEXT,CONTRASEÑA TEXT,ESTADO TEXT)");
        db.execSQL("INSERT INTO CUENTA VALUES(0,'Admin','Admin','0')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE CUENTA");
        db.execSQL("CREATE TABLE CUENTA(ID INTEGER PRIMARY KEY,NOMBRE TEXT,CONTRASEÑA TEXT,ESTADO TEXT)");
        db.execSQL("INSERT INTO CUENTA VALUES(0,'Admin','Admin','0')");
    }
}
