package com.patrickwallin.projects.remind_exercise.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by piwal on 10/29/2017.
 */

public class VenueContentProvider extends ContentProvider {
    public static final int VENUE_BOOKMARK = 100;

    private VenueDBHelper mVenueDBHelper;

    private static UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(VenueContract.CONTENT_AUTHORITY, VenueContract.PATH_VENUE_BOOKMARK, VENUE_BOOKMARK);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mVenueDBHelper = new VenueDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mVenueDBHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);

        Cursor cursor = null;

        switch(match) {
            case VENUE_BOOKMARK:
                cursor = db.query(VenueContract.VenueBookmarkEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw  new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(cursor != null)
            cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mVenueDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);

        Uri returnUri;

        switch (match) {
            case VENUE_BOOKMARK:
                long venueId = db.insert(VenueContract.VenueBookmarkEntry.TABLE_NAME, null, contentValues);
                if(venueId > 0) {
                    returnUri = ContentUris.withAppendedId(VenueContract.VenueBookmarkEntry.CONTENT_URI,venueId);
                }else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mVenueDBHelper.getWritableDatabase();
        int rowsDeleted = 0;

        int match = uriMatcher.match(uri);

        switch (match) {
            case VENUE_BOOKMARK:
                rowsDeleted = db.delete(VenueContract.VenueBookmarkEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;

        SQLiteDatabase db = mVenueDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);

        switch (match) {
            case VENUE_BOOKMARK:
                rowsUpdated = db.update(VenueContract.VenueBookmarkEntry.TABLE_NAME, contentValues, selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);

        return rowsUpdated;
    }
}
