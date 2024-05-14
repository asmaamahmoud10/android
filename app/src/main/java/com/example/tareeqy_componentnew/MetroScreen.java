package com.example.tareeqy_componentnew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class MetroScreen extends AppCompatActivity {
    DataBase db;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_screen);




        Button busDetailsButton = findViewById(R.id.button3);

        busDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroScreen.this, MetroDetails.class);
                startActivity(intent);
            }
        });



        db = new DataBase(this);
        String[] lines1 = {"New El Marg", "El Marg", "Ezbet El Nakhl","Ain Shams" ,"Matarya" , "Helmeyet El Zaton", "Hadayek El Zaton", "Saray El Qubba", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] lines2 = { "Shubra El Khema", "Koleyet El Zeraa", "El Mezalat", "El Khalafawy", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] lines3 = { "Adly Mansour", "Haikstep", "Omar Bn El Khattab", "Qebaa", "Hesham Barakat", "Nozha", "El Shams Club", "Alf Maskan", "Heliopoles", "Haroun", "Kolleyet ElBanat", "Stadum", "Cairo Fair", "Abbasiya", "Abdo Pasha", "El Geish", "Bab El Shaariya", "", "", "", "", "", "", ""};
        db.insertValues(lines1, lines2, lines3);

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
        String query = "SELECT " + DataBase.COLUMN_LINE1 + ", " + DataBase.COLUMN_LINE2 + ", " + DataBase.COLUMN_LINE3 + " FROM " + DataBase.MetroLines;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);



        // Add all items of line1[] first
        for (String line1 : lines1) {
            spinnerAdapter.add(line1);
        }

        // Then add all items of line2[]
        for (String line2 : lines2) {
            spinnerAdapter.add(line2);
        }

        // Finally, add all items of line3[]
        for (String line3 : lines3) {
            spinnerAdapter.add(line3);
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spinnerAdapter.clear();
    }
}