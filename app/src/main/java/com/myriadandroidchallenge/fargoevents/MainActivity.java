package com.myriadandroidchallenge.fargoevents;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myriadandroidchallenge.fargoevents.Interfaces.Api;
import com.myriadandroidchallenge.fargoevents.Model.User;
import com.myriadandroidchallenge.fargoevents.Presenter.LoginPresenter;
import com.myriadandroidchallenge.fargoevents.Utilities.Utils;
import com.myriadandroidchallenge.fargoevents.View.ILoginView;
import com.myriadandroidchallenge.fargoevents.data.loginContract;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ILoginView {
    EditText userName, password;
    Button loginButton;
    LoginPresenter loginPresenter;
    Call<User> call;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        loginPresenter = new LoginPresenter(this);
        view = (LinearLayout) findViewById(R.id.rootView);

        //if user is already logged in go to events activity otherwise bring login interface
        if (Utils.checkIfLogin(this)) {
            Intent intent = new Intent(this, EventsActivity.class);
            intent.putExtra("message", getString(R.string.welcomeBack));
            startActivity(intent);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                loginPresenter.Onlogin(userName.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

    //Create user if the login was successful
    @Override
    public void OnLoginSuccess(String message) {
        createUser(message);
    }

    //Show the error if login infos are incorrect
    @Override
    public void OnLoginError(String message) {
        Snackbar snack = Snackbar.make(view, "" + message, Snackbar.LENGTH_LONG);
        snack.show();
    }

    // create user using retrofit and store token locally
    public void createUser(final String message) {
        call = RetrofitClient
                .getInstance()
                .getApi()
                .CreateUser(userName.getText().toString().trim(), password.getText().toString().trim());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String result = null;
                if (response.isSuccessful()) {
                    Utils.GotoEventsActivity(MainActivity.this, response.body().getToken(), message);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("MainActivity.Class", "onFailure: " + t.getMessage());
            }
        });
    }


}
