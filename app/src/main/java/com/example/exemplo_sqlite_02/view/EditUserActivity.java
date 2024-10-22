package com.example.exemplo_sqlite_02.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exemplo_sqlite_02.R;
import com.example.exemplo_sqlite_02.dao.UserDAO;
import com.example.exemplo_sqlite_02.infra.DBManager;
import com.example.exemplo_sqlite_02.model.User;

public class EditUserActivity extends Activity {
    private UserDAO dao;
    private User user;
    private DBManager databaseManager;

    private EditText editTextName;
    private EditText editTextAge;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        editTextName = findViewById(R.id.name);
        editTextAge = findViewById(R.id.age);
        databaseManager = new DBManager(this);
        databaseManager.open();

        long userId = getIntent().getLongExtra("USER_ID", -1);
        dao = new UserDAO(databaseManager.getDatabase());

        user = dao.getUser(userId);

        editTextName.setText(user.getName());
        editTextAge.setText(String.valueOf(user.getAge()));

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();


                // Atualizar  o usuário com os novos dados
                user.setName(name);
                user.setAge(Integer.parseInt(age));
                dao.editUser(user); // salvar
                setResult(Activity.RESULT_OK); // codigo para indicar que a edição foi concluida
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        databaseManager.close();
        super.onDestroy();
    }
}
