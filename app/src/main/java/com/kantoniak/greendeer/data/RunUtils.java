package com.kantoniak.greendeer.data;

import android.content.Context;
import android.text.format.DateUtils;

import com.kantoniak.greendeer.proto.Run;

/**
 * Provider some helper methods
 */
public class RunUtils {

    public static String kilometersAsString(Run run) {
        return String.format("%d.%02d km", run.getMeters() / 1000, (run.getMeters() % 1000) / 10);
    }

    public static String timeInSecondsAsString(Run run) {
        return String.format("%2d:%02d", run.getTimeInSeconds() / 60, run.getTimeInSeconds() % 60);
    }

    public static String averageTimeInSecondsAsString(Run run) {
        int average = run.getTimeInSeconds() * 1000 / run.getMeters();
        return String.format("%2d:%02d", average / 60, average % 60);
    }

    public static String getDateAsString(Run run, Context context) {
        return DateUtils.formatDateTime(context, run.getTimeFinished().getSeconds() * 1000, DateUtils.FORMAT_SHOW_YEAR);
    }

    public static String getWeightAsString(Run run) {
        return String.format("%.1f kg", run.getWeight());
    }

}
