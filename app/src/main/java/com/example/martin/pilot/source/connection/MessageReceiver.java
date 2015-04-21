package com.example.martin.pilot.source.connection;

import android.util.Log;

import com.example.martin.pilot.source.handlers.PingHandler;
import com.example.martin.pilot.source.handlers.TaskHandler;

/**
 * Created by marmajew on 4/17/2015.
 */
public class MessageReceiver {
    private PingHandler pingHandler;

    public MessageReceiver(TCPCLient client) {
        TaskHandler.initialize(client);

        pingHandler = new PingHandler();
    }

    public void receiveMessage(String message) {
        String [] splitMessage = message.split(";");
        String header = splitMessage[0];

        switch(header) {
            case "S:ACK":
                ConnectionManager.getInstance().confirmConnection();
                pingHandler.initializeTimer();
                break;

            case "S:NACK":
                ConnectionManager.getInstance().closeTcpClient();
                break;

            case "S:PONG":
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
