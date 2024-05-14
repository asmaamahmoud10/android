package com.example.tareeqy_componentnew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class BusScreen extends AppCompatActivity {
    BusDB db;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_screen);

        Button busDetailsButton = findViewById(R.id.button3);
        Button camera = findViewById(R.id.button4);

        busDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusScreen.this, BusDetails.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusScreen.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        db = new BusDB(this);
        String[] m31 = {"Matarya Square", "El Taawun", "Mostorod","Ring Road" ,"Adly Mansour" , "Obour Market", "Mostaqbal City", "New Administrative Capital"};
        String[] m30 = { "Ain Shams", "Ain Shams Metro Station", "Al Marj Al Sharqiya stop", " Al Marj  Al Gharbiya stop", "Othman Residences", "Othman Residences", "Ring Road", "Ismailia Desert", "Middle Ring Road", "Bin Zayed North Axis", " New Administrative Capital"};
        String[] m29 = { "Al Marj", " New Al Marj Parking", "Al Marj  Al Sharqiya Parking", "Al Marj Al Sharqiya stop", " Al Marj  Al Gharbiya stop", "Othman Residences", "Zakat Foundation Parking", "Ring Road", "Ismailia Desert", "Middle Ring Road", "Bin Zayed North Axis", " New Administrative Capital"};
        db.insertValues(m31, m30, m29);

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
        String query = "SELECT " + BusDB.M31 + ", " + BusDB.M30 + ", " + BusDB.M29 + " FROM " + BusDB.Buses;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);



        // Add all items of line1[] first
        for (String Bus1 : m31) {
            spinnerAdapter.add(Bus1);
        }

        // Then add all items of line2[]
        for (String Bus2 : m30) {
            spinnerAdapter.add(Bus2);
        }

        // Finally, add all items of line3[]
        for (String Bus3 : m29) {
            spinnerAdapter.add(Bus3);
        }

        cursor.close();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        spinnerAdapter.clear();
    }

}
