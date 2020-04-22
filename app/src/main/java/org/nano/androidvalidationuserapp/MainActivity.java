package org.nano.androidvalidationuserapp;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPasswordConfirm;

    protected boolean isConfirmName;
    protected boolean isConfirmEmail;
    protected boolean isConfirmPassword;
    protected boolean isConfirmPasswordTwo;

    private Button buttonSave;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isConfirmName = false;
        isConfirmEmail = false;
        isConfirmPassword = false;
        isConfirmPasswordTwo = false;

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutPasswordConfirm = findViewById(R.id.textInputLayoutPasswordConfirm);

        Objects.requireNonNull(textInputLayoutName.getEditText()).setOnFocusChangeListener(onConfirmName);
        Objects.requireNonNull(textInputLayoutEmail.getEditText()).setOnFocusChangeListener(onConfirmEmail);
        Objects.requireNonNull(textInputLayoutPassword.getEditText()).setOnFocusChangeListener(onConfirmPassword);
        Objects.requireNonNull(textInputLayoutPasswordConfirm.getEditText()).setOnFocusChangeListener(onConfirmPasswordTwo);

        textInputLayoutName.getEditText().addTextChangedListener(watcherName);
        textInputLayoutEmail.getEditText().addTextChangedListener(watcherEmail);
        textInputLayoutPassword.getEditText().addTextChangedListener(watcherPassword);
        textInputLayoutPasswordConfirm.getEditText().addTextChangedListener(watcherPasswordConfirm);

        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setEnabled(false);
    }

    private TextWatcher watcherName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textInputLayoutName.setError(null);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher watcherEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textInputLayoutEmail.setError(null);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher watcherPassword = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textInputLayoutPassword.setError(null);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher watcherPasswordConfirm = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textInputLayoutPasswordConfirm.setError(null);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnFocusChangeListener onConfirmName = (v, hasFocus) -> {
        if (!hasFocus)
            validateName();
        else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmEmail = (v, hasFocus) -> {
        if (!hasFocus)
            validateEmail();
        else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmPassword = (v, hasFocus) -> {
        if (!hasFocus)
            validatePassword();
        else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmPasswordTwo = (v, hasFocus) -> {
        if (!hasFocus)
            validatePasswordConfirm();
        else
            onConfirm();
    };

    private void onConfirm() {
        if (isConfirmName & isConfirmEmail & isConfirmPassword & isConfirmPasswordTwo)
            buttonSave.setEnabled(true);
        else
            buttonSave.setEnabled(false);
    }

    private void validateName() {
        String inputName = Objects.requireNonNull(textInputLayoutName.getEditText()).getText().toString().trim();
        if (inputName.isEmpty()) {
            textInputLayoutName.setError("Field can't be empty");
            isConfirmName = false;
        } else if (inputName.length() > 8) {
            textInputLayoutName.setError("Name too long");
            isConfirmName = false;
        } else if (inputName.length() <= 2) {
            textInputLayoutName.setError("Name too short");
            isConfirmName = false;
        } else {
            textInputLayoutName.setError(null);
            isConfirmName = true;
        }
    }

    private void validateEmail() {
        String inputEmail = Objects.requireNonNull(textInputLayoutEmail.getEditText()).getText().toString().trim();
        if (inputEmail.isEmpty()) {
            textInputLayoutEmail.setError("Field can't be empty");
            isConfirmEmail = false;
        } else {
            textInputLayoutEmail.setError(null);
            isConfirmEmail = true;
        }
    }

    private void validatePassword() {
        String inputPassword = Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim();
        if (inputPassword.isEmpty()) {
            textInputLayoutPassword.setError("Field can't be empty");
            isConfirmPassword = false;
        } else {
            textInputLayoutPassword.setError(null);
            isConfirmPassword = true;
        }
    }

    private void validatePasswordConfirm() {
        String inputPasswordConfirm = Objects.requireNonNull(textInputLayoutPasswordConfirm.getEditText()).getText().toString().trim();
        if (inputPasswordConfirm.isEmpty()) {
            textInputLayoutPasswordConfirm.setError("Field can't be empty");
            isConfirmPasswordTwo = false;
        } else if (!inputPasswordConfirm.equals(Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim())) {
            textInputLayoutPasswordConfirm.setError("Passwords is different");
            isConfirmPasswordTwo = false;
        } else {
            textInputLayoutPasswordConfirm.setError(null);
            isConfirmPasswordTwo = true;
        }
    }

    public void onClickSave(View view) {

    }
}
