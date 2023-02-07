package com.example.demoapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import com.example.demoapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtLogin, edtPswd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLogin = findViewById(R.id.edtLogin);
        edtPswd = findViewById(R.id.edtPswd);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);


    }


    private void saveTask() {
        final String login = edtLogin.getText().toString().trim();
        final String pswd = edtPswd.getText().toString().trim();

        if (login.isEmpty()) {
            edtLogin.setError("login required");
            edtLogin.requestFocus();
            return;
        }

        if (pswd.isEmpty()) {
            edtPswd.setError("pswd required");
            edtPswd.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                User user = new User();
                user.setEmailId(login);
                user.setPassword(pswd);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                startActivity(new Intent(getApplicationContext(), UserListActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:

                if (DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .isDataExist(edtLogin.getText().toString()) == 1) {
                    Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show();

                } else {
                    saveTask();
                }


                break;
        }

    }
}