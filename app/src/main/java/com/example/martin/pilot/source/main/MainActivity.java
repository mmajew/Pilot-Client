package com.example.martin.pilot.source.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.settings.SettingsActivity;
import com.example.martin.pilot.source.settings.SettingsManager;


public class MainActivity extends ActionBarActivity {

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
        else {
            Toast.makeText(this, "Trzy", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void launchFirstConfiguration() {
        Log.w("Settings", "No configuration available");
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void initializeManagers() {
        SettingsManager.initialize(this);
        ConnectionManager.initialize();
    }
}
