package com.example.martin.pilot.source.handlers;

import com.example.martin.pilot.source.connection.TCPCLient;

/**
 * Created by marmajew on 4/20/2015.
 */
public class TaskHandler {
    public static TCPCLient client;

    public static void initialize(TCPCLient TCPCLient) {
        client = TCPCLient;
    }
}
