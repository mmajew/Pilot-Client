package com.example.martin.pilot.source.connection;

import com.example.martin.pilot.source.handlers.PingHandler;
import com.example.martin.pilot.source.handlers.TaskHandler;

/**
 * Created by marmajew on 4/17/2015.
 */
public class MessageReceiver {
    private PingHandler pingHandler;

    public MessageReceiver(TCPClient client) {
        TaskHandler.initialize(client);

        pingHandler = new PingHandler();
        pingHandler.initializeTimer();
    }

    public void receiveMessage(String message) {
        String [] splitMessage = message.split(";");
        String header = splitMessage[0];

        switch(header) {
            case "S:ACK":
                ConnectionManager.getInstance().confirmConnection();
                break;

            case "S:NACK":
                ConnectionManager.getInstance().closeConnection();
                break;

            case "S:PONG":
                pingHandler.handle();

            default:
                break;
        }
    }
}
