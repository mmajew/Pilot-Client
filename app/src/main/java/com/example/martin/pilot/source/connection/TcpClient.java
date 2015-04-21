package com.example.martin.pilot.source.connection;

import android.util.Log;

import com.example.martin.pilot.source.settings.SettingsManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Martin on 17/04/2015.
 */
public class TCPClient {
    private boolean isRunning = false;
    private OnMessageReceived messageListener = null;
    private String serverMessage;
    Socket socket;

    PrintWriter out;
    BufferedReader in;

    public void setMessageListener(OnMessageReceived listener) {
        messageListener = listener;
    }

    public void sendMessage(String message){
        if (out != null && !out.checkError()) {
            Log.e("TCP Client", "C: Sending: " + message);
            out.println(message);
            out.flush();
        }
    }

    public void close(){
        Log.e("TCP", "S: Closing");
        isRunning = false;

        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        messageListener.stop();
    }

    public void run() {
        isRunning = true;
        try {
            Log.e("TCP Client", "C: Connecting...");

            InetAddress serverAddress = InetAddress.getByName(SettingsManager.getInstance().getServerIp());
            socket = new Socket(serverAddress, SettingsManager.getInstance().getTcpPort());

            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Log.e("TCP Client", "C: Initialized.");

                sendMessage("C:CONN;" + SettingsManager.getInstance().getDeviceName());

                while (isRunning) {
                    serverMessage = in.readLine();

                    if (serverMessage != null && messageListener != null) {
                        Log.e("TCP Client", "C: Received: " + serverMessage);
                        messageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;
                }
            } finally {
                close();
                Log.e("TCP", "S: Closed");
            }
        } catch (Exception e) {
            Log.e("TCP E: ", e.getMessage());
        }
    }

    public interface OnMessageReceived {
        public void messageReceived(String message);
        public void stop();
    }
}
