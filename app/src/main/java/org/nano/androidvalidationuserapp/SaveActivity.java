package org.nano.androidvalidationuserapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

import java.util.Objects;

public class SaveActivity extends Activity {

    private String name;
    private String email;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewPassword = findViewById(R.id.textViewPassword);

        Intent intent = getIntent();

        name = intent.getStringExtra(name);
        email = intent.getStringExtra(email);
        password = intent.getStringExtra(password);

        textViewName.setText(name);
        textViewEmail.setText(email);
        textViewPassword.setText(password);
    }
}
