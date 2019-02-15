package com.myriadandroidchallenge.fargoevents.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.myriadandroidchallenge.fargoevents.EventDetailActivity;
import com.myriadandroidchallenge.fargoevents.EventsActivity;
import com.myriadandroidchallenge.fargoevents.data.loginContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by disen on 2/12/2019.
 */

public class Utils {
    //start a new intent to make a request on the chosen event
    public static void GotoSpecificEvent(Context context, int id, String token) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("token", token);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //Check if the user is already logged in
    public static boolean checkIfLogin(Context context) {
        String[] projection = new String[]{loginContract.loginEntry.token};
        Cursor cursor = context.getContentResolver().query(loginContract.contentLogin, projection, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //start new intent to move to Events activity and store token retrieved to the DB
    public static void GotoEventsActivity(Context context, String token, String message) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(loginContract.loginEntry.token, token);
        context.getContentResolver().insert(loginContract.contentLogin, contentValues);
        Intent intent = new Intent(context, EventsActivity.class);
        intent.putExtra("message", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    //Retrieve saved token locally from database
    public static String getToken(Context context) {
        String[] projection = new String[]{loginContract.loginEntry.token};
        String token = null;
        Cursor cursor = context.getContentResolver().query(loginContract.contentLogin, projection, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int tokenColumnName = cursor.getColumnIndex(loginContract.loginEntry.token);
            token = cursor.getString(tokenColumnName);
        }
        cursor.close();
        return token;
    }
    //convert the date to the right format
    public static String getDate(String time){
        Date date = null;
        SimpleDateFormat format= new SimpleDateFormat("yy-mm-dd'T'HH:mm:ss");
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(date);
    }
}
