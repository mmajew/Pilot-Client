package com.example.martin.pilot.source.messages;

import android.util.Log;

import com.example.martin.pilot.source.connection.TcpClient;
import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.connection.UdpClient;
import com.example.martin.pilot.source.handlers.PingHandler;
import com.example.martin.pilot.source.handlers.TaskHandler;
import com.example.martin.pilot.source.handlers.UdpConnectionHandler;
import com.example.martin.pilot.source.settings.SettingsManager;


public class MessageReceiver {
    private PingHandler pingHandler;
    private UdpConnectionHandler udpConnectionHandler;

    private TcpClient tcpClient;
    private UdpClient udpClient;

    public MessageReceiver(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        this.udpClient = new UdpClient();

        TaskHandler.initialize(tcpClient, udpClient);

        pingHandler = new PingHandler();
        udpConnectionHandler = new UdpConnectionHandler();
    }

    public void receiveMessage(String message) {
        String [] splitMessage = message.split(";");
        String header = splitMessage[0];

        switch(header) {
            case ServerMessages.CONNECTION_ACK:
                if(SettingsManager.getInstance().getIsUdpEnabled()) {
                    udpConnectionHandler.handleRequesting();
                }
                else {
                    confirmConnection();
                }
                break;

            case ServerMessages.UDP_ACK:
                udpConnectionHandler.handleAck();
                confirmConnection();
                break;

            case ServerMessages.UDP_NACK:
                Log.e("UDP Client", "Received NACK from the server.");
                SettingsManager.getInstance().saveIsUdpAllowed(false);
                confirmConnection();
                break;

            case ServerMessages.CONNECTION_NACK:
                ConnectionManager.getInstance().closeClient();
                ConnectionManager.getInstance().notifyConnectionLost("Serwer zakończył połączenie.");
                break;

            case ServerMessages.SERVER_PONG:
                pingHandler.handle();

            default:
                break;
        }
    }

    public void confirmConnection() {
        pingHandler.initializeTimer();
        ConnectionManager.getInstance().confirmConnection();
    }

    public void stopHandlers() {
        Log.e("Cancelling task", "Stopping handlers");
        pingHandler.stopHandler();
    }
}
