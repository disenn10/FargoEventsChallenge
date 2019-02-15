package com.myriadandroidchallenge.fargoevents.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myriadandroidchallenge.fargoevents.Presenter.EventListPresenter;
import com.myriadandroidchallenge.fargoevents.Presenter.ISpeakerListPresenter;
import com.myriadandroidchallenge.fargoevents.Presenter.SpeakerListPresenter;
import com.myriadandroidchallenge.fargoevents.R;

/**
 * Created by disen on 2/11/2019.
 */

public class SpeakersRecyclerAdapter extends RecyclerView.Adapter<SpeakersRecyclerAdapter.SpeakerHolder> {
    private final SpeakerListPresenter speakerListPresenter;
    Context context;

    public SpeakersRecyclerAdapter(Context context, SpeakerListPresenter speakerListPresenter) {
        this.speakerListPresenter = speakerListPresenter;
        this.context = context;
    }

    @Override
    public SpeakersRecyclerAdapter.SpeakerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SpeakersRecyclerAdapter.SpeakerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.speaker_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SpeakersRecyclerAdapter.SpeakerHolder holder, int position) {
        speakerListPresenter.onBindEventRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return speakerListPresenter.getEventsRowsCount();
    }

    public class SpeakerHolder extends RecyclerView.ViewHolder implements ISpeakerListPresenter {
        TextView name_view;
        TextView bio_view;
        public SpeakerHolder(View itemView) {
            super(itemView);
            name_view = itemView.findViewById(R.id.speaker_name);
            bio_view = itemView.findViewById(R.id.speaker_bio);
        }

        @Override
        public void setName(String name) {
            name_view.setText(name);
        }

        @Override
        public void setBio(String name) {
            bio_view.setText(name);
        }
    }
}
