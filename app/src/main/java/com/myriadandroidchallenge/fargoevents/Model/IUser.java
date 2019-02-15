package com.myriadandroidchallenge.fargoevents.Model;

/**
 * Created by disen on 2/6/2019.
 */

public interface IUser {
    String getEmail();
    String getPassword();
    int isValidData();
    String getToken();
}
