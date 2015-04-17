package com.example.martin.pilot.source.settings;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.connection.ConnectionManager;

public class SettingsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        readSettings();

        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    private void saveSettings() {
        EditText deviceName = (EditText) findViewById(R.id.editDeviceName);
        EditText serverIp = (EditText) findViewById(R.id.editServerAddress);
        EditText serverTcpPort = (EditText) findViewById(R.id.editTcpPort);

        SettingsManager settingsManager = SettingsManager.getInstance();
        settingsManager.saveDeviceName(deviceName.getText().toString());
        settingsManager.saveServerIp(serverIp.getText().toString());
        settingsManager.saveTcpPort(Integer.parseInt(serverTcpPort.getText().toString()));

        CheckBox udpCheckBox = (CheckBox) findViewById(R.id.checkBoxUdp);
        boolean isUdpAllowed = udpCheckBox.isChecked();
        if(isUdpAllowed) {
            EditText serverUdpPort = (EditText) findViewById(R.id.editUdpPort);
            settingsManager.saveUdpPort(Integer.parseInt(serverUdpPort.getText().toString()));
            settingsManager.saveIsUdpAllowed(isUdpAllowed);
        }

        ConnectionManager.getInstance().attemptConnection(this);
    }

    private void readSettings() {
        SettingsManager settingsManager = SettingsManager.getInstance();
        String deviceName = settingsManager.getDeviceName();
        String serverAddress = settingsManager.getServerIp();

        Integer tcpPort = settingsManager.getTcpPort();
        Integer udpPort = settingsManager.getUdpPort();

        boolean isUdpAllowed = settingsManager.getIsUdpAllowed();

        ((EditText) findViewById(R.id.editDeviceName)).setText(deviceName);
        ((EditText) findViewById(R.id.editServerAddress)).setText(serverAddress);
        ((EditText) findViewById(R.id.editTcpPort)).setText(tcpPort.toString());
        ((EditText) findViewById(R.id.editUdpPort)).setText(udpPort.toString());
        ((CheckBox) findViewById(R.id.checkBoxUdp)).setChecked(isUdpAllowed);
    }
}
