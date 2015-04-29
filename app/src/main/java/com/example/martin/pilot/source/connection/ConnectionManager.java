package com.example.martin.pilot.source.connection;

import android.app.ProgressDialog;
import android.content.Intent;

import com.example.martin.pilot.source.main.MainActivity;
import com.example.martin.pilot.source.settings.SettingsActivity;
import com.example.martin.pilot.source.settings.SettingsManager;


public class ConnectionManager {
    private TcpClient tcpClient;
    static private ConnectionManager m_self;
    private boolean isConnected = false;
    private SettingsActivity settingsContext;

    ProgressDialog progressDialog;

    public static ConnectionManager getInstance() {
        if(m_self == null)
            m_self = new ConnectionManager();
        return m_self;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getConnectionStateString() {
        return isConnected ? "Połączono z " + SettingsManager.getInstance().getServerIp() : "Brak połączenia";
    }

    public void attemptConnection(SettingsActivity context, ProgressDialog dialog) {
        progressDialog = dialog;
        settingsContext = context;
        tcpClient = new TcpClient();
        new TcpConnectionTask(tcpClient).execute("");
    }

    public void confirmConnection() {
        closeProgressDialog();
        isConnected = true;

        settingsContext.enableDisconnectButton();
        launchMainActivity(false);
    }

    public void confirmConnectionWithUdpFailed() {
        closeProgressDialog();
        isConnected = true;

        settingsContext.enableDisconnectButton();
        launchMainActivity(true);
    }

    public void notifyConnectionLost(String cause) {
        Intent intent = new Intent(settingsContext, SettingsActivity.class);
        intent.putExtra("CONNECTION_LOST", true);
        intent.putExtra("CAUSE", cause);
        settingsContext.startActivity(intent);
    }

    public void notifyConnectionFailed() {
        closeProgressDialog();
        if(settingsContext != null)
            settingsContext.enableConnectButton();
    }

    public void closeClient() {
        isConnected = false;
        if(tcpClient != null)
            tcpClient.stop();
    }

    private void closeProgressDialog() {
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void launchMainActivity(boolean hasUdpFailed) {
        Intent intent = new Intent(settingsContext, MainActivity.class);
        if(hasUdpFailed) {
            intent.putExtra("UDP_FAILED", true);
        }
        settingsContext.startActivity(intent);
    }
}