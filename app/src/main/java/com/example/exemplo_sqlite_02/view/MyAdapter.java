package com.example.exemplo_sqlite_02.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.exemplo_sqlite_02.R;
import com.example.exemplo_sqlite_02.dao.UserDAO;
import com.example.exemplo_sqlite_02.model.User;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<User> userList;
    private Context context;
    private Lista lista;
    private UserDAO dao;



    public MyAdapter(Context context, List<User> users, UserDAO userDAO) {
        this.userList = users;
        this.context = context;
        dao = userDAO;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = this.userList.get(position);
        convertView = inflater.inflate(R.layout.item_lista, null);

        TextView name = convertView.findViewById(R.id.name);
        name.setText(user.getName());

        TextView age = convertView.findViewById(R.id.age);
        // Converter a idade para String antes de definir o texto
        age.setText(String.valueOf(user.getAge()));

        Button buttonEditar = convertView.findViewById(R.id.buttonEdit);
        Button buttonDeletar = convertView.findViewById(R.id.buttonDelete);


        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rows = dao.deleteUser(user.getId());// deleta no banco
                if (rows > 0) {
                    userList.remove(position);//remove da lista
                    notifyDataSetChanged(); // notifica o adapter para que atualize a view
                }
            }
        });
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("USER_ID", user.getId());

                ((Activity) context).startActivityForResult(intent, 1); // request code para atualizar a listview após editUser ser finalizado
            }
        });
        return convertView;
    }
}
