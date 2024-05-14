package com.example.tareeqy_componentnew;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelperV extends SQLiteOpenHelper {

    private static MyDbHelperV instance;

    // Database and table details
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "speech";
    private static final String COLUMN_SPEECH = "speech";

    // SQL statement to create the table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_SPEECH + " TEXT)";

    // Constructor (private to prevent instantiation)
    private MyDbHelperV(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Singleton pattern getInstance method
    public static synchronized MyDbHelperV getInstance(Context context) {
        if (instance == null) {
            instance = new MyDbHelperV(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    // Method to insert speech into the database
    public long insertSpeech(String speech) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPEECH, speech);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if necessary
    }
}
