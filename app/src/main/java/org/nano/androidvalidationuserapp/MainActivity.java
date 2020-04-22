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
            textInputLayoutName.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateName();
        }

        @Override
        public void afterTextChanged(Editable s) {
            onConfirm();
        }
    };

    private TextWatcher watcherEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            textInputLayoutEmail.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateEmail();
        }

        @Override
        public void afterTextChanged(Editable s) {
            onConfirm();
        }
    };

    private TextWatcher watcherPassword = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            textInputLayoutPassword.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validatePassword();
        }

        @Override
        public void afterTextChanged(Editable s) {
            onConfirm();
        }
    };

    private TextWatcher watcherPasswordConfirm = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            textInputLayoutPasswordConfirm.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validatePasswordConfirm();
        }

        @Override
        public void afterTextChanged(Editable s) {
            onConfirm();
        }
    };

    private View.OnFocusChangeListener onConfirmName = (v, hasFocus) -> {
        if (!hasFocus) {
            String inputName = Objects.requireNonNull(textInputLayoutName.getEditText()).getText().toString().trim();
            if (inputName.isEmpty()) {
                textInputLayoutName.setError("Field can't be empty");
            } else if (inputName.length() > 8) {
                textInputLayoutName.setError("Name too long");
            } else if (inputName.length() <= 1) {
                textInputLayoutName.setError("Name must be longer than 1 charter");
                isConfirmName = false;
            } else {
                textInputLayoutName.setError(null);
            }
        } else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmEmail = (v, hasFocus) -> {
        if (!hasFocus) {
            String inputEmail = Objects.requireNonNull(textInputLayoutEmail.getEditText()).getText().toString().trim();
            if (inputEmail.isEmpty()) {
                textInputLayoutEmail.setError("Field can't be empty");
            } else {
                textInputLayoutEmail.setError(null);
            }
        } else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmPassword = (v, hasFocus) -> {
        if (!hasFocus) {
            String inputPassword = Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim();
            if (inputPassword.isEmpty()) {
                textInputLayoutPassword.setError("Field can't be empty");
            } else {
                textInputLayoutPassword.setError(null);
            }
        } else
            onConfirm();
    };

    private View.OnFocusChangeListener onConfirmPasswordTwo = (v, hasFocus) -> {
        if (!hasFocus) {
            String inputPasswordConfirm = Objects.requireNonNull(textInputLayoutPasswordConfirm.getEditText()).getText().toString().trim();
            if (inputPasswordConfirm.isEmpty()) {
                textInputLayoutPasswordConfirm.setError("Field can't be empty");
            } else if (!inputPasswordConfirm.equals(Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim())) {
                textInputLayoutPasswordConfirm.setError("Passwords is different");
            } else {
                textInputLayoutPasswordConfirm.setError(null);
            }
        } else
            onConfirm();
    };

    private void validateName() {
        String inputName = Objects.requireNonNull(textInputLayoutName.getEditText()).getText().toString().trim();
        if (inputName.isEmpty()) {
            isConfirmName = false;
        } else if (inputName.length() > 8) {
            isConfirmName = false;
        } else isConfirmName = inputName.length() > 1;
    }

    private void validateEmail() {
        String inputEmail = Objects.requireNonNull(textInputLayoutEmail.getEditText()).getText().toString().trim();
        isConfirmEmail = !inputEmail.isEmpty();
    }

    private void validatePassword() {
        String inputPassword = Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim();
        isConfirmPassword = !inputPassword.isEmpty();
    }

    private void validatePasswordConfirm() {
        String inputPasswordConfirm = Objects.requireNonNull(textInputLayoutPasswordConfirm.getEditText()).getText().toString().trim();
        if (inputPasswordConfirm.isEmpty()) {
            isConfirmPasswordTwo = false;
        } else isConfirmPasswordTwo = inputPasswordConfirm.equals(Objects.requireNonNull(textInputLayoutPassword.getEditText()).getText().toString().trim());
    }

    private void onConfirm() {
        if (isConfirmName & isConfirmEmail & isConfirmPassword & isConfirmPasswordTwo)
            buttonSave.setEnabled(true);
        else
            buttonSave.setEnabled(false);
    }

    public void onClickSave(View view) {

    }
}
