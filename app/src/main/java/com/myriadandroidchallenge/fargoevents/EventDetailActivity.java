package com.myriadandroidchallenge.fargoevents;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.myriadandroidchallenge.fargoevents.Background.BackgroundService;
import com.myriadandroidchallenge.fargoevents.Model.Events;
import com.myriadandroidchallenge.fargoevents.Model.Speaker;
import com.myriadandroidchallenge.fargoevents.Presenter.SpeakerListPresenter;
import com.myriadandroidchallenge.fargoevents.Utilities.Utils;
import com.myriadandroidchallenge.fargoevents.View.Custom_ImageView;
import com.myriadandroidchallenge.fargoevents.View.SpeakersRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity implements BackgroundService.EventDetailInfos {
    static Custom_ImageView imageView;
    static TextView name_view, time_infos_view, event_detail_view, location_detail_view;
    static int position;
    public static int id;
    public static String token;
    static Call<Speaker> call_speaker;
    static ArrayList<Speaker> speakersList;
    static RecyclerView recyclerView;
    static ArrayList<Events> speakerIds;
    static Activity activity;
    public static BackgroundService.EventDetailInfos getEventInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        //instantiate data
        activity = this;
        getEventInfos = this;
        name_view = (TextView) findViewById(R.id.name_detail);
        imageView = (Custom_ImageView) findViewById(R.id.image_detail);
        time_infos_view = (TextView) findViewById(R.id.time_detail);
        event_detail_view = (TextView) findViewById(R.id.event_detail);
        location_detail_view = (TextView) findViewById(R.id.location_detail);
        recyclerView = (RecyclerView) findViewById(R.id.speaker_detail);
        speakerIds = new ArrayList<>();
        speakersList = new ArrayList<>();
        // Retrieve saved token and passed ID in order to make new request
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", 0);
            token = intent.getStringExtra("token");
        }
        //Make a synchronous request in order to make two simultaneous requests(for event infos and speaker(s) infos
        Intent intent1 = new Intent(this, BackgroundService.class);
        startService(intent1);


    }

    //Get speaker(s) information based on the passed ids
    public void addSpeakers(ArrayList<Events> speakerIds) {
        for (int i = 0; i < speakerIds.size(); i++) {
            position = i;
            call_speaker = RetrofitClient
                    .getInstance()
                    .getApi()
                    .GetSpeaker(token, speakerIds.get(i).getID());
            call_speaker.enqueue(new Callback<Speaker>() {
                @Override
                public void onResponse(Call<Speaker> call, Response<Speaker> response) {
                    speakersList.add(response.body());
                    updateSpeakerUI();
                }

                @Override
                public void onFailure(Call<Speaker> call, Throwable t) {
                    Log.e("EventDetailActivity", "onFailure: " + t.getMessage());
                }
            });
        }
    }

    //update speakerUI after the arraylist speakerList has been updated
    public void updateSpeakerUI() {
        SpeakerListPresenter speakerListPresenter = new SpeakerListPresenter(speakersList);
        LinearLayoutManager llm = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        SpeakersRecyclerAdapter speakerAdapter = new SpeakersRecyclerAdapter(this, speakerListPresenter);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(speakerAdapter);
    }

    //Update the Ui with the parameter received, and then make another request for the speakers
    @Override
    public void getEventDetailInfos(final Response<Events> response) {
        speakerIds = response.body().getSpeakers();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Picasso.with(activity).load(response.body().getImage_url()).into(imageView);
                event_detail_view.setText(response.body().getDescription());
                name_view.setText(response.body().getTitle());
                activity.setTitle(response.body().getTitle());
                time_infos_view.setText(Utils.getDate(response.body().getStartDate()) + " - " + Utils.getDate(response.body().getEndDate()));
                location_detail_view.setText(response.body().getLocation());
                addSpeakers(speakerIds);
            }
        });
    }
}
