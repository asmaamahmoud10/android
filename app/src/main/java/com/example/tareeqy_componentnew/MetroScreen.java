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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MetroScreen extends AppCompatActivity {
    MetroDB db;
    ArrayAdapter<String> spinnerAdapter;
    private String selectedValueSpinner1 = null;
    private String selectedValueSpinner2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_screen);

        db = new MetroDB(this);
        /*db.deleteAllRows();
        String[] lines1 = {"Helwan", "Ain Helwan", "Helwan University", "Wadi Hof", "Hadayeq Helwan",
                "El-Maasara", "Tora", "El-Asmant", "Kozzika", "Tora El-Balad",
                "Thakanat El-Maadi", "Maadi", "Hadayeq El-Maadi", "Dar El-Salam",
                "El-Zahraa'", "Mar Girgis", "El-Malek El-Saleh", "AlSayyeda Zeinab",
                "Saad Zaghloul", "Sadat", "Gamal AbdAl", "Urabi", "Al Shohadaa",
                "Ghamra", "El-Demerdash", "Manshiet El-Sadr", "Kobri El-Qobba",
                "Hammamat El-Qobba", "Saray El-Qobba", "Hadayeq El-Zaitoun",
                "Helmeyet El-Zaitoun", "El-Matareyya", "Ain Shams", "Ezbet El-Nakhl",
                "El-Marg", "New El-Marg"};
        db.insertValues(lines1);*/

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String query = "SELECT " + MetroDB.COLUMN_LINE1 + " FROM " + MetroDB.MetroLines;
        Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String line1 = cursor.getString(cursor.getColumnIndexOrThrow(MetroDB.COLUMN_LINE1));
                spinnerAdapter.add(line1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        spinner.setAdapter(spinnerAdapter);
        spinner2.setAdapter(spinnerAdapter);
        spinner.setSelection(0);
        spinner2.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newValue = parent.getItemAtPosition(position).toString();
                if (newValue.equals(selectedValueSpinner2)) {
                    Toast.makeText(MetroScreen.this, "You cannot select the same station for both spinners.", Toast.LENGTH_SHORT).show();
                    int spinner1Position = spinnerAdapter.getPosition(selectedValueSpinner1);
                    spinner.setSelection(spinner1Position, false);
                } else {
                    selectedValueSpinner1 = newValue;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newValue = parent.getItemAtPosition(position).toString();
                if (newValue.equals(selectedValueSpinner1)) {
                    Toast.makeText(MetroScreen.this, "You cannot select the same station for both spinners.", Toast.LENGTH_SHORT).show();
                    int spinner2Position = spinnerAdapter.getPosition(selectedValueSpinner2);
                    spinner2.setSelection(spinner2Position, false);
                } else {
                    selectedValueSpinner2 = newValue;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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