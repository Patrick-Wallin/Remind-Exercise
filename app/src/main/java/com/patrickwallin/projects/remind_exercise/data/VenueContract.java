package com.patrickwallin.projects.remind_exercise.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piwal on 10/28/2017.
 */

public class VenueContract {
    public static final String SCHEME = "content://";
    public static final String CONTENT_AUTHORITY = "com.patrickwallin.projects.remind_exercise";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);
    public static final String PATH_VENUE_BOOKMARK = "venue_bookmark";

    public static final class VenueBookmarkEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VENUE_BOOKMARK).build();

        public static final String TABLE_NAME = PATH_VENUE_BOOKMARK;

        public static final String COLUMN_VENUE_BOOKMARK_ID = "venue_bookmark_id";

        public static final int COL_VENUE_BOOKMARK_ID = 1;
    }

    public static String getCreateTableStatement() {
        StringBuilder createTableTableStatement = new StringBuilder();

        createTableTableStatement.append("CREATE TABLE IF NOT EXISTS ");
        createTableTableStatement.append(PATH_VENUE_BOOKMARK);
        createTableTableStatement.append(" (");
        createTableTableStatement.append(VenueBookmarkEntry._ID);
        createTableTableStatement.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        createTableTableStatement.append(VenueBookmarkEntry.COLUMN_VENUE_BOOKMARK_ID);
        createTableTableStatement.append(" TEXT NOT NULL DEFAULT ''");
        createTableTableStatement.append(")");

        return createTableTableStatement.toString();
    }

    public static boolean hasThisBeenBookmarked(String venueId, Context context) {
        boolean hasThisBeenBookmarked = false;

        Cursor cursor = context.getContentResolver().query(
                VenueBookmarkEntry.CONTENT_URI,
                new String[] {VenueBookmarkEntry.COLUMN_VENUE_BOOKMARK_ID},
                VenueBookmarkEntry.COLUMN_VENUE_BOOKMARK_ID + " = " + android.database.DatabaseUtils.sqlEscapeString(venueId),
                null,
                null);
        if(cursor != null) {
            if(cursor.moveToFirst())
                hasThisBeenBookmarked = true;
            cursor.close();
        }

        return hasThisBeenBookmarked;
    }
}
