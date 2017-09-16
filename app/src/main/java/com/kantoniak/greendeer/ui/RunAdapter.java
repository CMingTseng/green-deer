package com.kantoniak.greendeer.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kantoniak.greendeer.R;
import com.kantoniak.greendeer.data.RunUtils;
import com.kantoniak.greendeer.proto.Run;

import java.util.LinkedList;
import java.util.List;

public class RunAdapter extends RecyclerView.Adapter<RunAdapter.ViewHolder> {

    private final Context context;
    private final List<Run> runs = new LinkedList<>();

    public RunAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mDateText;
        public TextView mWeightText;
        public TextView mKilometersText;
        public TextView mAvgTimeText;
        public TextView mTimeText;

        public ViewHolder(View itemView) {
            super(itemView);
            mDateText = (TextView) itemView.findViewById(R.id.date);
            mWeightText = (TextView) itemView.findViewById(R.id.weight);
            mKilometersText = (TextView) itemView.findViewById(R.id.kilometers);
            mAvgTimeText = (TextView) itemView.findViewById(R.id.avg_time);
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
        Run run = runs.get(position);
        holder.mDateText.setText(RunUtils.getDateAsString(run, context));
        holder.mWeightText.setText("(" + RunUtils.getWeightAsString(run) + ")");
        holder.mKilometersText.setText(RunUtils.kilometersAsString(run));
        holder.mAvgTimeText.setText("Avg: " + RunUtils.averageTimeInSecondsAsString(run));
        holder.mTimeText.setText("Σ: " + RunUtils.timeInSecondsAsString(run));
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
