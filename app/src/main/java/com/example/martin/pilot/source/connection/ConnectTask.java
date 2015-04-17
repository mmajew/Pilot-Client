package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by marmajew on 4/17/2015.
 */
public class ConnectTask extends AsyncTask<String,String,TCPClient> {
    private TCPClient TCPClient;
    private MessageReceiver messageReceiver;

    public ConnectTask(TCPClient client) {
        TCPClient = client;
    }

    @Override
    protected TCPClient doInBackground(String... message) {
        messageReceiver = new MessageReceiver();
        TCPClient.setMessageListener(new TCPClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                if(isCancelled()) {
                    Log.e("Cancelling task", "Stopping server");
                    TCPClient.stopClient();
                }
                publishProgress(message);
            }
        });
        TCPClient.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        messageReceiver.receiveMessage(values[0]);
    }
}
