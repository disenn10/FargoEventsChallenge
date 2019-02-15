package com.myriadandroidchallenge.fargoevents.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by disen on 2/13/2019.
 */

public class loginContract {


    public static String loginAuthority = "com.myriadandroidchallenge.fargoevents";
    public static Uri baseLogin = Uri.parse("content://"+loginAuthority);
    public static String path = "login";
    public static Uri contentLogin = Uri.withAppendedPath(baseLogin,path);

    public static class loginEntry implements BaseColumns {
        public static String db_name = "logintoken";
        public static String session_id = BaseColumns._ID;
        public static String token = "token";

    }
}
