package com.example.martin.pilot.source.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.settings.SettingsActivity;
import com.example.martin.pilot.source.settings.SettingsManager;


public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.mainGridView);
        gridview.setAdapter(new MainAdapter(this));
        gridview.setOnItemClickListener(new MainOnClickListener(this));

        initializeManagers();

        if(!SettingsManager.getInstance().isConfigurationPresent()) {
            launchFirstConfiguration();
        }
    }

    public void launchFirstConfiguration() {
        Log.w("Settings", "No configuration available");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void initializeManagers() {
        SettingsManager.initialize(this);
    }
}
