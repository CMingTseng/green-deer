package com.kantoniak.greendeer;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.kantoniak.greendeer.data.DataProvider;
import com.kantoniak.greendeer.proto.Run;
import com.kantoniak.greendeer.ui.RunAdapter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity {

    class NetworkUpdatesHandler extends Handler {
        NetworkUpdatesHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg){
            switch (msg.what) {
                case MESSAGE_FETCH_RUNS:
                    logger.log(Level.INFO, "Fetching runs...");
                    final List<Run> runs = dataProvider.getListOfRuns();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runAdapter.updateRuns(runs);
                        }
                    });
                    break;
            }
        }
    };

    private static final Logger logger = Logger.getLogger(HomeActivity.class.getName());
    private static final int MESSAGE_FETCH_RUNS = 1000;

    private final DataProvider dataProvider = new DataProvider();
    private final RunAdapter runAdapter = new RunAdapter();

    private RecyclerView mRecyclerView;

    private final HandlerThread networkUpdatesThread = new HandlerThread("NetworkUpdatesThread");
    private Handler networkUpdatesHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupRecyclerView();

        networkUpdatesThread.start();
        networkUpdatesHandler = new NetworkUpdatesHandler(networkUpdatesThread.getLooper());

        networkUpdatesHandler.sendMessage(
                networkUpdatesHandler.obtainMessage(MESSAGE_FETCH_RUNS));
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.run_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(runAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
