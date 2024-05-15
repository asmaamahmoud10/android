package com.example.tareeqy_componentnew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BusScreen extends AppCompatActivity {
    BusDB db;
    ArrayAdapter<String> spinnerAdapter1, spinnerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_screen);

        startService(new Intent(this, audioFragment.class));

        db = new BusDB(this);
        String[] m31 = {"Matarya Square", "El Taawun", "Mostorod", "Ring Road", "Adly Mansour", "Obour Market", "Mostaqbal City", "New Administrative Capital"};
        String[] m30 = {"Ain Shams", "Ain Shams Metro Station", "Al Marj Al Sharqiya stop", "Al Marj  Al Gharbiya stop", "Othman Residences", "Othman Residences", "Ring Road", "Ismailia Desert", "Middle Ring Road", "Bin Zayed North Axis", " New Administrative Capital"};
        String[] m29 = {"Al Marj", "New Al Marj Parking", "Al Marj  Al Sharqiya Parking", "Al Marj Al Sharqiya stop", "Al Marj  Al Gharbiya stop", "Othman Residences", "Zakat Foundation Parking", "Ring Road", "Ismailia Desert", "Middle Ring Road", "Bin Zayed North Axis", " New Administrative Capital"};
        db.insertValues(m31, m30, m29);

        // Create a new ArrayAdapter for the Spinner
        spinnerAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        // Set the layout for the Spinner dropdown
        spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Find the Spinner view by ID
        Spinner spinner1 = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        // Set the ArrayAdapter as the adapter for the Spinner
        spinner1.setAdapter(spinnerAdapter1);
        spinner2.setAdapter(spinnerAdapter2);

        // Add all items of line1[] first
        for (String Bus : m31) {
            spinnerAdapter1.add(Bus);
            spinnerAdapter2.add(Bus);
        }

        // Then add all items of line2[]
        for (String Bus : m30) {
            spinnerAdapter1.add(Bus);
            spinnerAdapter2.add(Bus);
        }

        // Finally, add all items of line3[]
        for (String Bus : m29) {
            spinnerAdapter1.add(Bus);
            spinnerAdapter2.add(Bus);
        }

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (spinner2.getSelectedItem().equals(selectedItem)) {
                    spinnerAdapter2.remove(selectedItem);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (spinner1.getSelectedItem().equals(selectedItem)) {
                    spinnerAdapter1.remove(selectedItem);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button busDetailsButton = findViewById(R.id.button3);
        Button fragment = findViewById(R.id.button4);

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusScreen.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        busDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedValue1 = spinner1.getSelectedItem().toString();
                String selectedValue2 = spinner2.getSelectedItem().toString();

                ArrayList<String> matchingBuses = db.getBuses(selectedValue1, selectedValue2);
                Intent intent = new Intent(BusScreen.this, BusDetails.class);
                intent.putStringArrayListExtra("matching_buses", matchingBuses);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spinnerAdapter1.clear();
        spinnerAdapter2.clear();
    }


}
