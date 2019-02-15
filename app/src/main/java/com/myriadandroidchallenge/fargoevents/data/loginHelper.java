package com.myriadandroidchallenge.fargoevents.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by disen on 2/13/2019.
 */

public class loginHelper extends SQLiteOpenHelper {
    public static String db_name = loginContract.loginEntry.db_name;
    public static int version = 1;
    public loginHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context,db_name,factory, version);
    }
    public static String CreateEntries = "CREATE TABLE " + loginContract.loginEntry.db_name+"("
            +loginContract.loginEntry.session_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +loginContract.loginEntry.token+ " TEXT) ";
    public static String deleteTable = "DROP TABLE IF EXISTS "+ db_name;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CreateEntries);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(deleteTable);
        onCreate(sqLiteDatabase);
    }
}
