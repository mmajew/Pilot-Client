package com.example.martin.pilot.source.handlers;

import com.example.martin.pilot.source.connection.UdpClient;
import com.example.martin.pilot.source.messages.ClientMessages;
import com.example.martin.pilot.source.settings.SettingsManager;


public class UdpConnectionHandler extends TaskHandler {

    public void handleRequesting() {
        if(SettingsManager.getInstance().getIsUdpEnabled()) {
            Integer requestedPort = SettingsManager.getInstance().getUdpPort();
            tcpClient.sendTcpMessage(ClientMessages.UDP_REQUEST + ";" + requestedPort.toString());
        }
    }

    public void handleAck() {
        String serverAddress = SettingsManager.getInstance().getServerIp();
        Integer requestedPort = SettingsManager.getInstance().getUdpPort();
        udpClient.initialize(serverAddress, requestedPort);
    }

    public void closeUdpClient() {
        if(udpClient != null)
            udpClient.close();
    }
}
