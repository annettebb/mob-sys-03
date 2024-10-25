package com.example.lab3;

import android.content.Intent;
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

public class RouteActivity extends AppCompatActivity {

    private EditText editTextStart, editTextDestination, editTextDate, editTextTime, editTextPassengers, editTextNotes;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_route);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("Lifecycle", "RouteActivity: onCreate");

        editTextStart = findViewById(R.id.editTextStart);
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextPassengers = findViewById(R.id.editTextPassengers);
        editTextNotes = findViewById(R.id.editTextNotes);

        btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String start = editTextStart.getText().toString();
                String destination = editTextDestination.getText().toString();
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();
                String passengers = editTextPassengers.getText().toString();
                String notes = editTextNotes.getText().toString();
                String route = "from " + start + " to " + destination;
                String datetime = date + " | " + time;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("route", route);
                resultIntent.putExtra("datetime", datetime);
                resultIntent.putExtra("passengers", passengers);
                resultIntent.putExtra("notes", notes);
                setResult(RESULT_OK, resultIntent);
                finish();
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
        Log.d("Lifecycle", "ThirdActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "ThirdActivity: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "ThirdActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "ThirdActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "ThirdActivity: onDestroy");
    }
}