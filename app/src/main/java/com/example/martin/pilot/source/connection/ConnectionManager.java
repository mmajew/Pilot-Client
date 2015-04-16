package com.example.martin.pilot.source.connection;

import android.util.Log;

/**
 * Created by marmajew on 4/16/2015.
 */
public class ConnectionManager {
    static private ConnectionManager m_self;

    public static void initialize() {
        if(m_self == null)
            m_self = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        if(m_self == null)
            Log.e("SettingsManager", "Uninitialized reference to ConnectionManager");
        return m_self;
    }

    public void initializeTcpClient() {

    }

    public void initializeUdpClient() {

    }

    public void sendTcpMessage() {

    }

    public void sendUdpMessage() {

    }
}
