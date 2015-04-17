package com.example.martin.pilot.source.connection;

/**
 * Created by marmajew on 4/17/2015.
 */
public class MessageReceiver {

    public void receiveMessage(String message) {
        String [] splitMessage = message.split(";");
        String header = splitMessage[0];

        switch(header) {
            case "S:ACK":
                ConnectionManager.getInstance().confirmConnection();
                break;

            default:
                break;
        }
    }
}
