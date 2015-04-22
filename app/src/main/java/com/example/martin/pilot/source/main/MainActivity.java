package com.example.martin.pilot.source.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.settings.SettingsManager;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.mainGridView);
        gridview.setAdapter(new MainAdapter(this));
        gridview.setOnItemClickListener(new MainOnClickListener(this));

        if(!SettingsManager.getInstance().isConfigurationPresent()) {
            launchConfiguration();
        }
    }

    public void launchConfiguration() {
        Intent settingsIntent = new Intent(this, com.example.martin.pilot.source.settings.SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
