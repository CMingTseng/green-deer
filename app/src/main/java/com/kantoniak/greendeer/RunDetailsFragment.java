package com.kantoniak.greendeer;

import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.protobuf.Timestamp;
import com.kantoniak.greendeer.proto.Run;

import java.util.Date;

public class RunDetailsFragment extends Fragment {

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    private final DatePickerFragment datePickerFragment = new DatePickerFragment();

    private Button mDatePickButton;
    private TextView mDateViewText;
    private EditText mDistanceField;
    private EditText mTimeField;
    private EditText mWeightField;

    private Run editedRun = Run.newBuilder().setTimeFinished(Timestamp.newBuilder().setSeconds(new Date().getTime() / 1000)).build();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run_details, container, false);

        mDatePickButton = (Button) view.findViewById(R.id.date_pick);
        mDateViewText = (TextView) view.findViewById(R.id.date_view);
        mDistanceField = (EditText) view.findViewById(R.id.distance);
        mTimeField = (EditText) view.findViewById(R.id.time);
        mWeightField = (EditText) view.findViewById(R.id.weight);

        mDatePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        updateDateTextView();

        return view;
    }

    public void showDatePicker(){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    public void setDate(final Calendar calendar) {
        Date toSet = calendar.getTime();
        editedRun = Run.newBuilder(editedRun)
                .setTimeFinished(Timestamp.newBuilder().setSeconds(toSet.getTime() / 1000))
                .build();
        updateDateTextView();
    }

    private void updateDateTextView() {
        mDateViewText.setText(
                dateFormat.format(new Date(editedRun.getTimeFinished().getSeconds() * 1000)));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Performs validation and sets errors if needed.
     * @return true if data is valid
     */
    public boolean runValidation() {

        mDistanceField.setError(null);
        mTimeField.setError(null);
        mWeightField.setError(null);

        Run.Builder runBuilder = Run.newBuilder(editedRun);
        boolean isValid = true;

        String distanceValue = mDistanceField.getText().toString();
        if (distanceValue == null || distanceValue.isEmpty()) {
            mDistanceField.setError("Please enter distance.");
            isValid = false;
        } else {
            try {
                runBuilder.setMeters(Integer.parseInt(distanceValue));
            } catch (NumberFormatException e) {
                mDistanceField.setError("Please enter a number.");
                isValid = false;
            }
        }

        String timeValue = mTimeField.getText().toString();
        if (timeValue == null || timeValue.isEmpty()) {
            mTimeField.setError("Please enter time.");
            isValid = false;
        } else if (!timeValue.matches("\\d+:[0-5]\\d")) {
            mTimeField.setError("Please enter time like MM:SS.");
            isValid = false;
        } else {
            int timeInSeconds = Integer.parseInt(timeValue.substring(timeValue.length()-2)) +
                                Integer.parseInt(timeValue.substring(0, timeValue.length()-3)) * 60;
            runBuilder.setTimeInSeconds(timeInSeconds);
        }

        String weightValue = mWeightField.getText().toString();
        if (weightValue != null && !weightValue.isEmpty()) {
            try {
                runBuilder.setWeight(Float.parseFloat(weightValue));
                runBuilder.setHasWeight(true);
            } catch (NumberFormatException e) {
                mWeightField.setError("Please enter weight, e.g. 80.3");
                isValid = false;
            }
        }

        editedRun = runBuilder.build();
        return isValid;
    }

    public Run getEditedRun() {
        return editedRun;
    }
}
