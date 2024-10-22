package com.example.exemplo_sqlite_02.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exemplo_sqlite_02.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase db;


    public UserDAO(SQLiteDatabase db) {


        this.db = db;


    }


    public long insertUser(User user) {
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put(UserContract.COLUMN_NAME, user.getName());
            values.put(UserContract.COLUMN_AGE, user.getAge());
            long id = db.insert(UserContract.TABLE_NAME, null, values);

            return id;
        } else {
            return -1;
        }
    }

    public int deleteUser(Long id) {
        String where = UserContract._ID + "= ?";
        String[] args = {String.valueOf(id)};

        return db.delete(UserContract.TABLE_NAME, where, args);
    }

    public int editUser(User user) {

        String name = user.getName();
        String age = String.valueOf(user.getAge());
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.COLUMN_NAME,name);
        contentValues.put(UserContract.COLUMN_AGE,age);

        String where = UserContract._ID + "= ?";
        String[] args = { String.valueOf(user.getId())};

        return db.update(UserContract.TABLE_NAME,contentValues,where,args);
    }

    public List<User> getAll() {
        if (db != null) {
            List<User> users = new ArrayList<>();
            String[] columns = {UserContract._ID, UserContract.COLUMN_NAME, UserContract.COLUMN_AGE};
            Cursor cursor = db.query(UserContract.TABLE_NAME, columns,
                    null, null, null, null,
                    null);
            while (cursor.moveToNext()) {
                int id = cursor.getColumnIndex(UserContract._ID);
                long id_user = cursor.getLong(id);
                int name = cursor.getColumnIndex(UserContract.COLUMN_NAME);
                String name_user = cursor.getString(name);
                int age = cursor.getColumnIndex(UserContract.COLUMN_AGE);
                int age_user = cursor.getInt(age);
                users.add(new User(id_user, name_user, age_user));
            }//while
            cursor.close();


            return users;
        } else {
            return null;
        }
    }//

    public User getUser(long id) {
        String[] columns = {UserContract._ID, UserContract.COLUMN_NAME, UserContract.COLUMN_AGE};
        String where = UserContract._ID + "= ?";
        String[] args = { String.valueOf(id)};

        Cursor cursor = db.query(UserContract.TABLE_NAME,columns,where,args,null,null,null,null); // limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setId(cursor.getLong(0));
        user.setName(cursor.getString(1));
        user.setAge(cursor.getInt(2));

        return user;
    }


}//class