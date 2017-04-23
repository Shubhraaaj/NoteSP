package com.example.shubhraj.notesp.data;

//This class is for creating and updating SQL Database
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shubhraj.notesp.data.NoteContract.NoteEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class NoteDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = NoteDBHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "notesapp.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link NoteDBHelper}.
     *
     * @param context of the app
     */
    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + NoteEntry.TABLE_NAME + " ("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NoteContract.NoteEntry.COLUMN_NOTE_CONTENT + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}