package org.nano.androidvalidationuserapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class SaveActivity extends Activity {

    private String name;
    private String email;
    private String password;

    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPassword = findViewById(R.id.textViewPassword);

        Intent intent = getIntent();

        name = intent.getStringExtra(name);
        email = intent.getStringExtra(email);
        password = intent.getStringExtra(password);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textViewName.setText(name);
        textViewEmail.setText(email);
        textViewPassword.setText(password);
    }
}
