package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {

    private TextView textViewUserInfo, textViewRoute;
    private Button btnSetPath, btnCallTaxi;
    private static final int REQUEST_CODE_ROUTE = 1;
    private String route, datetime, passengers, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("Lifecycle", "UserActivity: onCreate");

        textViewUserInfo = findViewById(R.id.textViewUserInfo);
        textViewRoute = findViewById(R.id.textViewRoute);
        btnSetPath = findViewById(R.id.btnSetPath);
        btnCallTaxi = findViewById(R.id.btnCallTaxi);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");

        textViewUserInfo.setText("Name: " + name + " " + surname + "\nPhone: " + phone);

        btnSetPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent routeIntent = new Intent(UserActivity.this, RouteActivity.class);
                startActivityForResult(routeIntent, REQUEST_CODE_ROUTE);
            }
        });

        btnCallTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallTaxiDialog();
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

    private void showCallTaxiDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Ваше такси в пути")
                .setMessage("Ваше такси находится в пути. Ожидайте!")
                .setPositiveButton("ОК", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ROUTE && resultCode == RESULT_OK && data != null) {
            route = data.getStringExtra("route");
            datetime = data.getStringExtra("datetime");
            passengers = data.getStringExtra("passengers");
            notes = data.getStringExtra("notes");
            textViewRoute.setText("Route: " + route + "\n" + "Date: " + datetime + "\n" + "Passengers: " + passengers + "\n" + "Additional Notes: " + notes);
            btnCallTaxi.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "UserActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "UserActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "UserActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "UserActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "UserActivity: onDestroy");
    }
}