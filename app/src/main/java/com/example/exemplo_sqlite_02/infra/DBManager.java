package com.example.exemplo_sqlite_02.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }


    public void open() {
        database = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }
    public SQLiteDatabase getDatabase(){
        return database;
    }


}
