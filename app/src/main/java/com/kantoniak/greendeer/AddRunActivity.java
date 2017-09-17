package com.kantoniak.greendeer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

public class AddRunActivity extends AppCompatActivity implements RunDetailsFragment.OnFragmentInteractionListener, DatePickerDialog.OnDateSetListener {

    private RunDetailsFragment mRunDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_run);

        mRunDetailsFragment = (RunDetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        mRunDetailsFragment.setDate(cal);
    }
}
