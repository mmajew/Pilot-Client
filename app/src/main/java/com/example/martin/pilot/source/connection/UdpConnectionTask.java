package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;


public class UdpConnectionTask extends AsyncTask<String, Void, UdpClient> {
    private UdpClient udpClient;

    public UdpConnectionTask(UdpClient udpClient) {
        this.udpClient = udpClient;
    }

    protected UdpClient doInBackground(String... message) {
        udpClient.sendUdpMessage(message[0]);
        return null;
    }
}
