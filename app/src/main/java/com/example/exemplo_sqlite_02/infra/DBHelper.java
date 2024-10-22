package com.example.exemplo_sqlite_02.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.exemplo_sqlite_02.dao.UserContract;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "database_user.db";
    private static final int DATABASE_VERSION = 1;




    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + UserContract.TABLE_NAME + " (" +
                    UserContract._ID + " INTEGER PRIMARY KEY," +
                    UserContract.COLUMN_NAME + " TEXT," +
                    UserContract.COLUMN_AGE + " INTEGER)";


    public DBHelper(Context context) {


        super(context, DATABASE_NAME,
                null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // caso uma coluna seja adicionada,
        // use este método para atualização do banco de dados
    }


}//class

