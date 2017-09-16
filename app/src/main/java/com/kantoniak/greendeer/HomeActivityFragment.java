package com.kantoniak.greendeer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        com.kantoniak.greendeer.proto.Run sampleRun = com.kantoniak.greendeer.proto.Run.newBuilder()
                .setMeters(10).setWeight(100).build();
        TextView textView = (TextView)view.findViewById(R.id.hello_world);
        textView.setText("Hello, " + sampleRun + "!");

        return view;
    }
}
