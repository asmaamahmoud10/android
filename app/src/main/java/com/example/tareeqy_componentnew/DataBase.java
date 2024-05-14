package com.example.tareeqy_componentnew;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import androidx.annotation.Nullable;
public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Metro";
    public static final String MetroLines = "Metro_Lines";
    private static final String Column_StationID = "Line_ID";
    public static final String COLUMN_LINE1 = "Line1";
    public static final String COLUMN_LINE2 = "Line2";
    public static final String COLUMN_LINE3 = "Line3";
    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + MetroLines + " ( " + Column_StationID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LINE1 + " String(50), " + COLUMN_LINE2 + " String(50), " + COLUMN_LINE3 + " String(50)) ";
        sqLiteDatabase.execSQL(createTableStatement);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertValues(String[] lines1, String[] lines2, String[] lines3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < 5; i++) {
            values.put(COLUMN_LINE1, lines1[i]);
            values.put(COLUMN_LINE2, lines2[i]);
            values.put(COLUMN_LINE3, lines3[i]);
            db.insert(MetroLines, null, values);
        }
    }





}
