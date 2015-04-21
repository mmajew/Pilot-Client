package com.example.martin.pilot.source.connection;

import android.os.AsyncTask;
import android.util.Log;


public class ConnectTask extends AsyncTask<String,String,TCPCLient> {
    private TCPCLient client;
    private MessageReceiver messageReceiver;

    public ConnectTask(TCPCLient client) {
        this.client = client;
    }

    @Override
    protected TCPCLient doInBackground(String... message) {
        messageReceiver = new MessageReceiver(client);
        client.setMessageListener(new TCPCLient.OnMessageReceived() {
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
