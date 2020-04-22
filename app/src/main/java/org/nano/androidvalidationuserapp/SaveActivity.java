package org.nano.androidvalidationuserapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SaveActivity extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setTextSize(24);
        textView.setPadding(32, 32, 32, 32);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String name = bundle.getString("name");
            String email = bundle.getString("email");
            String password = bundle.getString("password");
            textView.setText("Name: " + name + "\nEmail: " + email + "\nPassword: " + password);
        }

        setContentView(textView);
    }
}