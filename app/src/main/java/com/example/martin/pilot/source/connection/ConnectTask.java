package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by marmajew on 4/17/2015.
 */
public class ConnectTask extends AsyncTask<String,String,TCPClient> {
    private TCPClient client;
    private MessageReceiver messageReceiver;

    public ConnectTask(TCPClient client) {
        this.client = client;
    }

    @Override
    protected TCPClient doInBackground(String... message) {
        messageReceiver = new MessageReceiver(client);
        client.setMessageListener(new TCPClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                if (isCancelled()) {
                    Log.e("Cancelling task", "Stopping server");
                    ConnectionManager.getInstance().closeConnection();
                }
                publishProgress(message);
            }
        });
        client.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        messageReceiver.receiveMessage(values[0]);
    }
}
