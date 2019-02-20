package com.myriadandroidchallenge.fargoevents.Model;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by disen on 2/6/2019.
 */

public class User implements IUser {
    private String email,password,token;
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public User(String token){
        this.token = token;
    }
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValidData() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(getPassword());
        if(TextUtils.isEmpty(getEmail())){
            return 0;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            return 1;
        }
        else if(password.length()<6){
            return 2;
        }
        else if(matcher.matches() ){
            return 3;
        }
        else{
            return -1;
        }
        /**return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches() &&
                getPassword().length()>6 ; */
    }

    @Override
    public String getToken() {
        return token;
    }
}
