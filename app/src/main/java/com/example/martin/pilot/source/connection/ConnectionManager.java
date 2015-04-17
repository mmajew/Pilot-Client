package com.example.martin.pilot.source.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by marmajew on 4/16/2015.
 */
public class ConnectionManager {
    private TCPClient TCPClient;
    static private ConnectionManager m_self;
    private boolean isConnected = false;

    ProgressDialog dialog;

    public static void initialize() {
        if(m_self == null)
            m_self = new ConnectionManager();
    }

    public boolean isConnected() {
        return isConnected;
    }

    public static ConnectionManager getInstance() {
        if(m_self == null)
            Log.e("SettingsManager", "Uninitialized reference to ConnectionManager");
        return m_self;
    }

    public void attemptConnection(Context context) {
        if(TCPClient != null) {
            TCPClient.stopClient();
        }

        TCPClient = new TCPClient();
        final ConnectTask task = new ConnectTask(TCPClient);
        task.execute("");

        dialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
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
        if(dialog.isShowing())
            dialog.dismiss();
        isConnected = true;
    }

    public void initializeUdpClient() {

    }

    public void sendTcpMessage() {

    }

    public void sendUdpMessage() {

    }
}
