package com.myriadandroidchallenge.fargoevents;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.Model.User;
import com.myriadandroidchallenge.fargoevents.Presenter.EventListPresenter;
import com.myriadandroidchallenge.fargoevents.Utilities.Utils;
import com.myriadandroidchallenge.fargoevents.View.EventsRecyclerAdapter;
import com.myriadandroidchallenge.fargoevents.data.loginContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity implements EventsRecyclerAdapter.IeventOnclickListener {
    RecyclerView recyclerView;
    EventsRecyclerAdapter.IeventOnclickListener ieventOnclickListener;
    Call<ArrayList<Events>> call;
    EventsRecyclerAdapter eventsAdapter;
    ArrayList<Events> eventsArrayList;
    String token;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        recyclerView = (RecyclerView) findViewById(R.id.events_recyclerview);
        ieventOnclickListener = this;
        eventsArrayList = new ArrayList<>();
        //retrieve locally saved token
        token = Utils.getToken(this);
        view = findViewById(R.id.events_recyclerview);
        Intent intent = getIntent();
        //Great user - Whether it's their first time or an old timer
        if (intent != null) {
            String message = intent.getStringExtra("message");
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        //get and display events
        GetEvents(token);

    }

    //Once user clicked on an event, the Gotospecific method in the utils class will take the
    //user to another activity that will display infos about the clicked event
    @Override
    public void onclicked(int position) {
        Utils.GotoSpecificEvent(getApplicationContext(), eventsArrayList.get(position).getID(), token);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Logout();
        }
        return super.onOptionsItemSelected(item);
    }

    //Ask the user to confirm logging out
    public void Logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //delete token from database and take user back to login interface
    public void delete() {
        getContentResolver().delete(loginContract.contentLogin, null, null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Method that will get the events using retroft api
    //them display the events in a recyclerview
    public void GetEvents(String token) {
        call = RetrofitClient
                .getInstance()
                .getApi()
                .GetEvents(token);
        call.enqueue(new Callback<ArrayList<Events>>() {
            @Override
            public void onResponse(Call<ArrayList<Events>> call, Response<ArrayList<Events>> response) {
                EventListPresenter eventsPresenter = new EventListPresenter(response.body());
                eventsArrayList = response.body();
                LinearLayoutManager llm = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                eventsAdapter = new EventsRecyclerAdapter(eventsPresenter, getApplicationContext(), ieventOnclickListener);
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(eventsAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Events>> call, Throwable t) {
                Log.e("EventsActivity.Class", "onFailure: " + t.getMessage());
            }
        });
    }
}
