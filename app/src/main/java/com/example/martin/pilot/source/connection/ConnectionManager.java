package com.example.martin.pilot.source.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.martin.pilot.source.main.ActivityBase;
import com.example.martin.pilot.source.main.MainActivity;
import com.example.martin.pilot.source.settings.SettingsManager;

/**
 * Created by marmajew on 4/16/2015.
 */
public class ConnectionManager {
    private TCPClient TCPClient;
    static private ConnectionManager m_self;
    private boolean isConnected = false;
    private ActivityBase context;

    ProgressDialog dialog;

    public boolean isConnected() {
        return isConnected;
    }

    public void setDisconnected() {
        isConnected = false;
        context.updateSubtitle();
    }

    public String getConnectionState() {
        return isConnected ? "Połączono z " + SettingsManager.getInstance().getServerIp() : "Brak połączenia";
    }

    public static ConnectionManager getInstance() {
        if(m_self == null)
            m_self = new ConnectionManager();
        return m_self;
    }

    public void attemptConnectionWithDialog(Context context) {

    }

    public void attemptConnection(ActivityBase context) {
        this.context = context;

        if(TCPClient != null) {
            TCPClient.close();
        }

        TCPClient = new TCPClient();
        final ConnectTask task = new ConnectTask(TCPClient);
        task.execute("");

        dialog = new ProgressDialog(this.context, ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Łączenie");
        dialog.setMessage("Oczekiwanie na odpowiedź serwera");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Anuluj", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                task.cancel(true);
            }
        });

        dialog.show();
    }

    public void confirmConnection() {
        if(dialog != null && dialog.isShowing())
            dialog.dismiss();
        isConnected = true;

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void closeConnection() {
        if(TCPClient != null) {
            TCPClient.close();
        }
    }

    public void initializeUdpClient() {

    }

    public void sendTcpMessage() {

    }

    public void sendUdpMessage() {

    }
}
