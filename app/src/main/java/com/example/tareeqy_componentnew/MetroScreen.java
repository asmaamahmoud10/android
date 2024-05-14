package com.example.tareeqy_componentnew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MetroScreen extends AppCompatActivity {
    MetroDB db;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_screen);









        db = new MetroDB(this);
        db.deleteAllRows();
        String[] lines1 = {"Helwan", "Ain Helwan", "Helwan University", "Wadi Hof", "Hadayeq Helwan",
                "El-Maasara", "Tora", "El-Asmant", "Kozzika", "Tora El-Balad",
                "Thakanat El-Maadi", "Maadi", "Hadayeq El-Maadi", "Dar El-Salam",
                "El-Zahraa'", "Mar Girgis", "El-Malek El-Saleh", "AlSayyeda Zeinab",
                "Saad Zaghloul", "Sadat", "Gamal AbdAl", "Urabi", "Al Shohadaa",
                "Ghamra", "El-Demerdash", "Manshiet El-Sadr", "Kobri El-Qobba",
                "Hammamat El-Qobba", "Saray El-Qobba", "Hadayeq El-Zaitoun",
                "Helmeyet El-Zaitoun", "El-Matareyya", "Ain Shams", "Ezbet El-Nakhl",
                "El-Marg", "New El-Marg"};
        db.insertValues(lines1);

        // Create a new ArrayAdapter for the Spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        // Set the layout for the Spinner dropdown
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Find the Spinner view by ID
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        // Set the ArrayAdapter as the adapter for the Spinner
        spinner.setAdapter(spinnerAdapter);
        spinner2.setAdapter(spinnerAdapter);


        // Query the database for the metro line data
        String query = "SELECT " + MetroDB.COLUMN_LINE1 + " FROM " + MetroDB.MetroLines;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);



        // Add all items of line1[] first
        if (cursor.moveToFirst()) {
            do {
                // Get the value of the column for the current row
                String line1 = cursor.getString(cursor.getColumnIndexOrThrow(MetroDB.COLUMN_LINE1));
                // Add the value to the spinner adapter
                spinnerAdapter.add(line1);
            } while (cursor.moveToNext()); // Move to the next row
        }
        cursor.close();
        Button metroDetailsButton = findViewById(R.id.button3);
        metroDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> stations = db.getStations(spinner.getSelectedItem().toString(), spinner2.getSelectedItem().toString());
                Intent intent = new Intent(MetroScreen.this, MetroDetails.class);
                intent.putStringArrayListExtra("stations", stations);
                startActivity(intent);

            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        spinnerAdapter.clear();
    }
}