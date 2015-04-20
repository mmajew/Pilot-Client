package com.example.martin.pilot.source.handlers;

import com.example.martin.pilot.source.connection.TCPClient;

/**
 * Created by marmajew on 4/20/2015.
 */
public class TaskHandler {
    public static TCPClient client;

    public static void initialize(TCPClient tcpClient) {
        client = tcpClient;
    }
}
