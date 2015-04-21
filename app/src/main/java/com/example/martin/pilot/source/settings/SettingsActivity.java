package com.example.martin.pilot.source.settings;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
                    ConnectionManager.getInstance().closeTcpClient();
                    connectButton.setText("Połącz");
                }
            }
        });

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("CONNECTION_LOST")) {
                if(extras.getBoolean("CONNECTION_LOST", false))
                    DialogFactory.getConnectionlostDialog(this).show();
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
        if(isUdpAllowed) {
            EditText serverUdpPort = (EditText) findViewById(R.id.editUdpPort);
            settingsManager.saveUdpPort(Integer.parseInt(serverUdpPort.getText().toString()));
            settingsManager.saveIsUdpAllowed(isUdpAllowed);
        }
    }

    private void connect() {
        ProgressDialog dialog = DialogFactory.getAwaitingConnectionDialog(this, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                ConnectionManager.getInstance().closeTcpClient();
                enableConnectButton();
                updateSubtitle();
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

        boolean isUdpAllowed = settingsManager.getIsUdpAllowed();

        ((EditText) findViewById(R.id.editDeviceName)).setText(deviceName);
        ((EditText) findViewById(R.id.editServerAddress)).setText(serverAddress);
        ((EditText) findViewById(R.id.editTcpPort)).setText(tcpPort.toString());
        ((EditText) findViewById(R.id.editUdpPort)).setText(udpPort.toString());
        ((CheckBox) findViewById(R.id.checkBoxUdp)).setChecked(isUdpAllowed);
    }

    public void enableDisconnectButton() {
        final Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setText("Rozłącz");
        connectButton.setEnabled(true);
    }

    public void enableConnectButton() {
        final Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setText("Połącz");
        connectButton.setEnabled(true);
    }
}
