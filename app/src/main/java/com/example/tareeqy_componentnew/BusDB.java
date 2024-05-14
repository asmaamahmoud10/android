package com.example.tareeqy_componentnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
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

    }


