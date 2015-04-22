package com.example.martin.pilot.source.handlers;

import com.example.martin.pilot.source.connection.Client;


public class TaskHandler {
    protected static Client client;

    public static void initialize(Client tcpClient) {
        client = tcpClient;
    }
}
