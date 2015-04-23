package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.martin.pilot.source.messages.MessageReceiver;


public class TcpConnectionTask extends AsyncTask<String,String,TcpClient> {
    private TcpClient tcpClient;
    private MessageReceiver messageReceiver;

    public TcpConnectionTask(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        messageReceiver = new MessageReceiver(tcpClient);
    }

    @Override
    protected TcpClient doInBackground(String... message) {
        tcpClient.setMessageListener(new TcpClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                publishProgress(message);
            }
            @Override
            public void stop() {
                Log.e("Cancelling task", "Stopping server");
                messageReceiver.stopHandlers();
            }
        });
        tcpClient.run();
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        messageReceiver.receiveMessage(values[0]);
    }
}
