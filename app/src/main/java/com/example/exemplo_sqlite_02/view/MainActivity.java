package com.example.exemplo_sqlite_02.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplo_sqlite_02.R;
import com.example.exemplo_sqlite_02.dao.UserDAO;
import com.example.exemplo_sqlite_02.infra.DBManager;
import com.example.exemplo_sqlite_02.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private DBManager databaseManager;
    private Button buttonInsert, buttonList;
    private TextView textViewResult;
    private EditText editTextNome;
    private EditText editTextAge;
    private Lista lista;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DBManager(this);
        databaseManager.open();
        userDAO = new UserDAO(databaseManager.getDatabase());
        buttonInsert = findViewById(R.id.buttonInsert);
        textViewResult = findViewById(R.id.textViewResult);
        editTextNome = findViewById(R.id.name);
        editTextAge = findViewById(R.id.age);
        listView = findViewById(R.id.listview);

        lista = new Lista(this);


    }

    public void clicar(View view) {
        if (view.getId() == R.id.buttonInsert) {
            User user = new User(editTextNome.getText().toString(), Integer.parseInt(editTextAge.getText().toString()));
            long userId = userDAO.insertUser(user);
            String result = getString(R.string.user_id) + userId;
            textViewResult.setText(result);
            exibirLista();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            exibirLista();
        }
    }

    public void exibirLista() {
        List<User> dados;
        dados = userDAO.getAll();
        lista.criarList(dados, userDAO);
        listView.setAdapter(lista.getMyAdapter());
    }
}//class
