package com.example.martin.pilot.source.handlers;

import com.example.martin.pilot.source.connection.TcpClient;
import com.example.martin.pilot.source.connection.UdpClient;


public class TaskHandler {
    protected static TcpClient tcpClient;
    protected static UdpClient udpClient;

    public static void initialize(TcpClient tcpClient, UdpClient udpClient) {
        TaskHandler.tcpClient = tcpClient;
        TaskHandler.udpClient = udpClient;
    }
}
