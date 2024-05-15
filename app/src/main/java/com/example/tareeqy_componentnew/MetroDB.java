package com.example.tareeqy_componentnew;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MetroDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;  // Increment the database version
    private static final String DATABASE_NAME = "Metro";
    public static final String MetroLines = "Metro_Lines";
    private static final String Column_StationID = "Line_ID";
    public static final String COLUMN_LINE1 = "Line1";
    public static final String COLUMN_ORDER_POSITION = "order_position";  // Add the order position column
    public MetroDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + MetroLines + " ( " + Column_StationID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LINE1 + " TEXT, " + COLUMN_ORDER_POSITION + " INTEGER)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            sqLiteDatabase.execSQL("ALTER TABLE " + MetroLines + " ADD COLUMN " + COLUMN_ORDER_POSITION + " INTEGER");
        }
    }

    public void insertValues(String[] lines1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < lines1.length; i++) {
            values.put(COLUMN_LINE1, lines1[i]);
            values.put(COLUMN_ORDER_POSITION, i);  // Add the order position
            db.insert(MetroLines, null, values);
        }
    }

    public void deleteAllRows() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MetroLines, null, null);
        db.close();
    }

    public ArrayList<String> getStations(String selectedValue1, String selectedValue2) {
        ArrayList<String> stations = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Log.d("s1", "selectedValue1" + selectedValue1);
        Log.d("s1", "selectedValue2" + selectedValue2);

        int orderPosition1 = getOrderPosition(db, selectedValue1);
        int orderPosition2 = getOrderPosition(db, selectedValue2);

        // Create the query
        String query;
        Cursor cursor;
        if (orderPosition1 < orderPosition2) {
            query = "SELECT * FROM " + MetroLines + " WHERE (" + COLUMN_ORDER_POSITION + " BETWEEN ? AND ?) ORDER BY " + COLUMN_ORDER_POSITION;
            cursor = db.rawQuery(query, new String[]{String.valueOf(orderPosition1), String.valueOf(orderPosition2)});
        } else {
            query = "SELECT * FROM " + MetroLines + " WHERE (" + COLUMN_ORDER_POSITION + " BETWEEN ? AND ?) ORDER BY " + COLUMN_ORDER_POSITION + " DESC";
            cursor = db.rawQuery(query, new String[]{String.valueOf(orderPosition2), String.valueOf(orderPosition1)});
        }

        while (cursor.moveToNext()) {
            Log.d("" , "cursor: " + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINE1)));
            String station = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LINE1));
            stations.add(station);
        }

        cursor.close();

        return stations;
    }
    private int getOrderPosition(SQLiteDatabase db, String stationName) {
        String query = "SELECT " + COLUMN_ORDER_POSITION + " FROM " + MetroLines + " WHERE " + COLUMN_LINE1 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{stationName});
        int orderPosition = -1;
        if (cursor.moveToFirst()) {
            orderPosition = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ORDER_POSITION));
        }
        cursor.close();
        return orderPosition;
    }


}