package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;
import android.util.Log;

import com.example.martin.pilot.source.messages.MessageReceiver;


public class ConnectTask extends AsyncTask<String,String,Client> {
    private Client client;
    private MessageReceiver messageReceiver;

    public ConnectTask(Client client) {
        this.client = client;
    }

    @Override
    protected Client doInBackground(String... message) {
        messageReceiver = new MessageReceiver(client);
        client.setMessageListener(new Client.OnMessageReceived() {
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
        client.run();
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        messageReceiver.receiveMessage(values[0]);
    }
}
