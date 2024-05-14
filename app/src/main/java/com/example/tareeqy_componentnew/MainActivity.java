package com.example.tareeqy_componentnew;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button metroButton; // Declare button as a class field
    private Button busButton; // Declare button2 as a class field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Find the buttons
        metroButton = findViewById(R.id.button);
        busButton = findViewById(R.id.button2);

        // Set click listeners
        metroButton.setOnClickListener(new MetroButtonClickListener());
        busButton.setOnClickListener(new BusButtonClickListener());
    }

    private class MetroButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MetroScreen.class);
            startActivity(intent);
        }
    }

    private class BusButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, customComponent.class);
            startActivity(intent);
        }
    }
}
