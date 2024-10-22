package com.example.exemplo_sqlite_02.view;

import android.content.Context;

import com.example.exemplo_sqlite_02.dao.UserDAO;
import com.example.exemplo_sqlite_02.model.User;

import java.util.List;

public class Lista {

    private MyAdapter myAdapter;
    private Context context;


    public Lista(Context context) {
        this.context = context;
    }

    public void criarList(List<User>users, UserDAO userDAO) {
        myAdapter = new MyAdapter(context, users,userDAO);
    }

    public MyAdapter getMyAdapter() {
        return myAdapter;
    }
}
