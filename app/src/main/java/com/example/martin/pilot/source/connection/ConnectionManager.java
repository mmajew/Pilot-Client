package com.example.martin.pilot.source.connection;

import android.app.ProgressDialog;
import android.content.Intent;

import com.example.martin.pilot.source.main.MainActivity;
import com.example.martin.pilot.source.settings.SettingsActivity;
import com.example.martin.pilot.source.settings.SettingsManager;


public class ConnectionManager {
    private Client client;
    static private ConnectionManager m_self;
    private boolean isConnected = false;
    private SettingsActivity settingsContext;

    ProgressDialog progressDialog;

    public boolean isConnected() {
        return isConnected;
    }

    public void confirmConnectionLost() {
        closeClient();

        Intent intent = new Intent(settingsContext, SettingsActivity.class);
        intent.putExtra("CONNECTION_LOST", true);
        settingsContext.startActivity(intent);
    }

    public String getConnectionStateString() {
        return isConnected ? "Połączono z " + SettingsManager.getInstance().getServerIp() : "Brak połączenia";
    }

    public static ConnectionManager getInstance() {
        if(m_self == null)
            m_self = new ConnectionManager();
        return m_self;
    }

    public void attemptConnection(SettingsActivity context, ProgressDialog dialog) {
        progressDialog = dialog;
        settingsContext = context;
        client = new Client();
        new ConnectTask(client).execute("");
    }

    public void confirmConnection() {
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        isConnected = true;

        Intent intent = new Intent(settingsContext, MainActivity.class);
        settingsContext.enableDisconnectButton();
        settingsContext.startActivity(intent);
    }

    public void closeClient() {
        isConnected = false;
        if(client != null)
            client.close();
    }

    public void notifyConnectionFailed() {
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        if(settingsContext != null)
            settingsContext.enableConnectButton();
    }

    public void initializeUdpClient() {

    }

    public void sendTcpMessage() {

    }

    public void sendUdpMessage() {

    }
}
