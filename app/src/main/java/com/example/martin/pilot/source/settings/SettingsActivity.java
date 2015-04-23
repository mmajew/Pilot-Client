package com.example.martin.pilot.source.settings;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.main.BaseActivity;
import com.example.martin.pilot.source.tools.DialogFactory;


public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        readSettings();

        final Button connectButton = (Button) findViewById(R.id.connectButton);
        if(ConnectionManager.getInstance().isConnected()) {
            enableDisconnectButton();
        } else {
            enableConnectButton();
        }

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ConnectionManager.getInstance().isConnected()) {
                    saveSettings();
                    connect();
                    connectButton.setEnabled(false);
                }
                else {
                    ConnectionManager.getInstance().closeClient();
                    updateSubtitle();
                    connectButton.setText("Połącz");
                }
            }
        });

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("CONNECTION_LOST")) {
                if(extras.getBoolean("CONNECTION_LOST", false))
                    DialogFactory.getConnectionlostDialog(this, extras.getString("CAUSE")).show();
            }
        }
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
        settingsManager.saveIsUdpAllowed(isUdpAllowed);
        if(isUdpAllowed) {
            Log.e("TCP Client", "C: UDP Was checked");
            EditText serverUdpPort = (EditText) findViewById(R.id.editUdpPort);
            settingsManager.saveUdpPort(Integer.parseInt(serverUdpPort.getText().toString()));
        }
    }

    private void connect() {
        ProgressDialog dialog = DialogFactory.getAwaitingConnectionDialog(this, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                ConnectionManager.getInstance().closeClient();
                enableConnectButton();
            }
        });

        dialog.show();
        ConnectionManager.getInstance().attemptConnection(this, dialog);
    }

    private void readSettings() {
        SettingsManager settingsManager = SettingsManager.getInstance();
        String deviceName = settingsManager.getDeviceName();
        String serverAddress = settingsManager.getServerIp();

        Integer tcpPort = settingsManager.getTcpPort();
        Integer udpPort = settingsManager.getUdpPort();

        boolean isUdpAllowed = settingsManager.getIsUdpEnabled();

        ((EditText) findViewById(R.id.editDeviceName)).setText(deviceName);
        ((EditText) findViewById(R.id.editServerAddress)).setText(serverAddress);
        ((EditText) findViewById(R.id.editTcpPort)).setText(tcpPort.toString());
        ((EditText) findViewById(R.id.editUdpPort)).setText(udpPort.toString());
        ((CheckBox) findViewById(R.id.checkBoxUdp)).setChecked(isUdpAllowed);
    }

    public void enableDisconnectButton() {
        updateSubtitle();
        final Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setText("Rozłącz");
        connectButton.setEnabled(true);
    }

    public void enableConnectButton() {
        updateSubtitle();
        final Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setText("Połącz");
        connectButton.setEnabled(true);
    }
}
