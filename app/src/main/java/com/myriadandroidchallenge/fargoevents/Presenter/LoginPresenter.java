package com.myriadandroidchallenge.fargoevents.Presenter;

import com.myriadandroidchallenge.fargoevents.Model.User;
import com.myriadandroidchallenge.fargoevents.View.ILoginView;

/**
 * Created by disen on 2/6/2019.
 */

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;
    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void Onlogin(String username, String password) {
        User user = new User(username,password);
        int isLoginCode = user.isValidData();
        if(isLoginCode == 0){
            loginView.OnLoginError("Enter an email");
        }
        else if(isLoginCode == 1){
            loginView.OnLoginError("You must enter a valid email");
        }
        else if(isLoginCode == 2){
            loginView.OnLoginError("Password length should be greater than 6");
        }
        else if(isLoginCode == 3){
            loginView.OnLoginError("Enter at least one special characters");
        }
        else{
            loginView.OnLoginSuccess("Welcome");
        }
    }
}
