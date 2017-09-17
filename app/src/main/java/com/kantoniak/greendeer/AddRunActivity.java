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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRunActivity extends AppCompatActivity implements RunDetailsFragment.OnFragmentInteractionListener, DatePickerDialog.OnDateSetListener {

    private static final Logger logger = Logger.getLogger(AddRunActivity.class.getName());

    private RunDetailsFragment mRunDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_run);

        mRunDetailsFragment = (RunDetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_run, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_run) {
            if (mRunDetailsFragment.runValidation()) {
                logger.log(Level.INFO, "Saving new run: " + mRunDetailsFragment.getEditedRun());
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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
