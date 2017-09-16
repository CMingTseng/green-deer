package com.kantoniak.greendeer.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kantoniak.greendeer.R;
import com.kantoniak.greendeer.data.DataProvider;
import com.kantoniak.greendeer.proto.Run;

import java.util.LinkedList;
import java.util.List;

public class RunAdapter extends RecyclerView.Adapter<RunAdapter.ViewHolder> {

    private final List<Run> runs = new LinkedList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mKilometersText;
        public TextView mTimeText;

        public ViewHolder(View itemView) {
            super(itemView);
            mKilometersText = (TextView) itemView.findViewById(R.id.kilometers);
            mTimeText = (TextView) itemView.findViewById(R.id.time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.run_list_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Run r = runs.get(position);
        holder.mKilometersText.setText(String.format("%d.%02d km", r.getMeters() / 1000, (r.getMeters() % 1000) / 10));
        holder.mTimeText.setText(String.format("Σ: %2d:%02d", r.getTimeInSeconds() / 60, r.getTimeInSeconds() % 60));
    }

    @Override
    public int getItemCount() {
        return runs.size();
    }

    public void updateRuns(List<Run> runs) {
        this.runs.clear();
        this.runs.addAll(runs);
        this.notifyDataSetChanged();
    }
}
