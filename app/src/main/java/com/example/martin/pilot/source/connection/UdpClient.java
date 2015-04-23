package com.example.martin.pilot.source.connection;


import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UdpClient {
    private DatagramSocket socket;
    private InetAddress address;
    private Integer port;

    private boolean isInitialized = false;

    public void initialize(String address, Integer port) {
        try {
            this.port = port;
            this.address = InetAddress.getByName(address);
            socket = new DatagramSocket();
            isInitialized = true;
        } catch (SocketException exception) {
            Log.e("UDP Client", "Unable to initialize UDP socket.");
        } catch (UnknownHostException exception) {
            Log.e("UDP Client", "Received wrong IP address.");
        }
    }

    public void sendUdpMessage(String message) {
        byte[] datagram = message.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(datagram, datagram.length, address, port);
        try {
            Log.e("UDP Client", "Sending: " + message);
            socket.send(sendPacket);
        } catch (IOException exception) {
            Log.e("UDP Client", "Unable to send a datagram.");
        }
    }

    public void close() {
        socket.close();
    }
}
