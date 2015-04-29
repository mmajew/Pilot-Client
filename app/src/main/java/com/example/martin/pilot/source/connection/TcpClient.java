package com.example.martin.pilot.source.connection;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.martin.pilot.source.messages.ClientMessages;
import com.example.martin.pilot.source.settings.SettingsManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class TcpClient {
    private boolean isRunning;
    private OnMessageReceived messageListener;
    Socket socket;

    PrintWriter out;
    BufferedReader in;

    public void setMessageListener(OnMessageReceived listener) {
        messageListener = listener;
        isRunning = false;
    }

    public void sendTcpMessage(String message){
        if (out != null && !out.checkError()) {
            Log.e("TCP Client", "C: Sending: " + message);
            out.println(message);
            out.flush();
        }
    }

    private void cleanup() {

    }

    public void stop(){
        Log.e("TCP", "S: Closing");
        isRunning = false;
        sendTcpMessage(ClientMessages.CLOSE);

        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        try {
            isRunning = true;

            Log.e("TCP Client", "C: Connecting...");
            final int connectionTimeout = 10 * 1000;

            socket = new Socket();
            InetSocketAddress serverAddress = new InetSocketAddress(SettingsManager.getInstance().getServerIp(), SettingsManager.getInstance().getTcpPort());
            socket.connect(serverAddress, connectionTimeout);

            try {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Log.e("TCP Client", "C: Initialized.");

                sendTcpMessage(ClientMessages.CONNECTION_REQUEST + ";" + SettingsManager.getInstance().getDeviceName());

                String serverMessage;
                while (isRunning) {
                    serverMessage = in.readLine();
                    if (serverMessage != null && messageListener != null) {
                        Log.e("TCP Client", "C: Received: " + serverMessage);
                        messageListener.messageReceived(serverMessage);
                    }
                }
            } finally {
                if(messageListener != null)
                    messageListener.stop();
                Log.e("TCP", "S: Closed");
            }
        } catch (Exception e) {
            Log.e("TCP E: ", e.getMessage());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Log.e("TCP Client", "C: Ping timed out: ");
                    ConnectionManager.getInstance().notifyConnectionFailed();
                }
            });
        }
    }

    public interface OnMessageReceived {
        public void messageReceived(String message);
        public void stop();
    }
}
