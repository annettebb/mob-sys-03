package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhone, editTextName, editTextSurname;
    private Button btnRegister;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("Lifecycle", "MainActivity: onCreate");

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        btnRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String phone = sharedPreferences.getString("phone", null);
        String name = sharedPreferences.getString("name", null);
        String surname = sharedPreferences.getString("surname", null);

        if (phone != null && name != null && surname != null) {
            editTextPhone.setText(phone);
            editTextName.setText(name);
            editTextSurname.setText(surname);
            btnRegister.setText("Log in");
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editTextPhone.getText().toString();
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phone", phone);
                editor.putString("name", name);
                editor.putString("surname", surname);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("name", name);
                intent.putExtra("surname", surname);
                startActivity(intent);
            }
        });

        findViewById(R.id.infoButton).setOnClickListener(v -> showAuthorDialog());
    }

    private void showAuthorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("О разработчике")
                .setMessage("Будник Анна Андреевна")
                .setPositiveButton("ОК", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "MainActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "MainActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "MainActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "MainActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "MainActivity: onDestroy");
    }
}