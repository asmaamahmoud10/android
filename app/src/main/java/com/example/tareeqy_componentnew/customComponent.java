package com.example.tareeqy_componentnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class customComponent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_component);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the new activity
                Intent intent = new Intent(customComponent.this, BusScreen.class);
                startActivity(intent);
                // Finish the current activity
                finish();

            }
        }, 2000);
    }
}
