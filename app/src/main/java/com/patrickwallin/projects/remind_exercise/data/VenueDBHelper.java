package com.patrickwallin.projects.remind_exercise.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by piwal on 10/28/2017.
 */

public class VenueDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "venueinfo.db";
    private static int DATABASE_VERSION = 1;

    public VenueDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VenueContract.getCreateTableStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VenueContract.VenueBookmarkEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
