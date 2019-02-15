package com.myriadandroidchallenge.fargoevents.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by disen on 2/13/2019.
 */

public class loginProvider extends ContentProvider {
    UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    final int login = 100;
    final int recipe_id = 101;
    SQLiteDatabase sqLiteDatabase;
    loginHelper loginHelper;
    @Override
    public boolean onCreate() {
        loginHelper = new loginHelper(getContext(),null);
        uriMatcher.addURI(loginContract.loginAuthority,loginContract.path,login);
        //uriMatcher.addURI(loginContract.loginAuthority,loginContract.path+"#",recipe_id);
        return true;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        int recipe_path = uriMatcher.match(uri);
        sqLiteDatabase = loginHelper.getReadableDatabase();
        Cursor cursor;
        switch (recipe_path){
            case 100:
                cursor = sqLiteDatabase.query(loginContract.loginEntry.db_name,strings,s,strings1,null,null,null);
                break;
            default:
                throw new IllegalArgumentException("Can't perform request");
        }
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
        sqLiteDatabase = loginHelper.getWritableDatabase();
        int path = uriMatcher.match(uri);
        Long ID = null;
        switch (path){
            case login:
                ID = sqLiteDatabase.insert(loginContract.loginEntry.db_name,null,contentValues);
                break;
            default:
                throw new IllegalArgumentException("Couldn't perform insertion");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,ID);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int path = uriMatcher.match(uri);
        int deletion_id = 0;
        sqLiteDatabase = loginHelper.getWritableDatabase();
        switch (path){
            case login:
                deletion_id = sqLiteDatabase.delete(loginContract.loginEntry.db_name,s,strings);
                break;
            default:
                throw new IllegalArgumentException("Couldn't perform deletion");
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return deletion_id;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
