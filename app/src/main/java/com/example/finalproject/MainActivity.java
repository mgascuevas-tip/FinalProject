package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.UserType;

public class MainActivity extends AppCompatActivity {
    private EditText editName, editPass;
    private RadioGroup radioGroup;
    private Button btnLogin;
    private UserType userType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editName = findViewById(R.id.editName);
        editPass = findViewById(R.id.editPass);
        radioGroup = findViewById(R.id.radioGroup);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = editName.getText().toString();
        String password = editPass.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.radioSuperAdmin) {
            if (username.equals("admin") && password.equals("admin")) {
                userType = UserType.SUPER_ADMIN;
                openAdminPage();
            } else {
                showToast("Login Failed");
            }
        } else if (selectedId == R.id.radioAdmin) {
            // Replace "your_admin_username" and "your_admin_password" with appropriate admin credentials
            if (username.equals("your_admin_username") && password.equals("your_admin_password")) {
                userType = UserType.ADMIN;
                openAdminPage();
            } else {
                showToast("Login Failed");
            }
        } else if (selectedId == R.id.radioUser) {
            // You can implement user login based on your requirements here.
            // For simplicity, we'll just redirect to the UserPage directly.
            userType = UserType.USER;
            openUserPage();
        }
    }

    private void openAdminPage() {
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("USER_TYPE", userType.toString());
        startActivity(intent);
        finish();
    }

    private void openUserPage() {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("USER_TYPE", userType.toString());
        startActivity(intent);
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
