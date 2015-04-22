package com.example.martin.pilot.source.messages;

import android.util.Log;

import com.example.martin.pilot.source.connection.Client;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.handlers.PingHandler;
import com.example.martin.pilot.source.handlers.TaskHandler;


public class MessageReceiver {
    private PingHandler pingHandler;
    private Client client;

    public MessageReceiver(Client client) {
        TaskHandler.initialize(client);

        this.client = client;
        pingHandler = new PingHandler();
    }

    public void receiveMessage(String message) {
        String [] splitMessage = message.split(";");
        String header = splitMessage[0];

        switch(header) {
            case ServerMessages.CONNECTION_ACK:
                ConnectionManager.getInstance().confirmConnection();
                pingHandler.initializeTimer();
                break;

            case ServerMessages.CONNECTION_NACK:
                client.close();
                break;

            case ServerMessages.SERVER_PONG:
                pingHandler.handle();

            default:
                break;
        }
    }

    public void stopHandlers() {
        Log.e("Cancelling task", "Stopping handlers");
        pingHandler.stopHandler();
    }
}
