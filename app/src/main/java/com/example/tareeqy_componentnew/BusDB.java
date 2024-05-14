package com.example.tareeqy_componentnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BusDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Buses";
    public static final String Buses = "Buses";
    private static final String Column_BusID = "Bus_ID";
    public static final String M31 = "M31";
    public static final String M30 = "M30";
    public static final String M29 = "M29";
    public BusDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + Buses + " ( " + Column_BusID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + M31 + " String(50), " + M30 + " String(50), " + M29 + " String(50)) ";
        sqLiteDatabase.execSQL(createTableStatement);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertValues(String[] m31, String[] m30, String[] m29) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < 5; i++) {
            values.put(M31, m31[i]);
            values.put(M30, m30[i]);
            values.put(M29, m29[i]);
            db.insert(Buses, null, values);
        }
    }
    public ArrayList<String> getBuses(String selectedValue1, String selectedValue2) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> allColumns = getAllColumns(db, Buses);
        ArrayList<String> matchingBuses = new ArrayList<>();
        // Iterate through each column
        for (String column : allColumns) {
            // Construct the query to find rows containing both selected values in the same column
            String selection = column + "=? OR " + column + "=?";

            String[] selectionArgs = {selectedValue1, selectedValue2};

            // Execute the query
            Cursor cursor = db.query(Buses, new String[]{column}, selection, selectionArgs, null, null, null);

            // Check if the query returned any results
            if (cursor.moveToFirst()) {
                // Retrieve the values from the current row
                String columnName = cursor.getColumnName(cursor.getColumnIndexOrThrow(column));
                matchingBuses.add(columnName);
                Log.d("BusScreen", "Bus found: " + columnName);
            }
            // Close the cursor
            cursor.close();
        }
        for (String bus : matchingBuses){
            Log.d("bus", "matching buses" + bus);
        }
        return matchingBuses;

    }

    private List<String> getAllColumns(SQLiteDatabase db, String tableName) {
        List<String> columns = new ArrayList<>();
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);
        if (cursor.moveToFirst()) {
            do {
                columns.add(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return columns;
    }


}