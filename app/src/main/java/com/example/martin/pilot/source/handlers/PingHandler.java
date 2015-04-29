package com.example.martin.pilot.source.handlers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.messages.ClientMessages;

import java.util.Timer;
import java.util.TimerTask;


public class PingHandler extends TaskHandler {
    private boolean isPonged = true;
    private Timer timer;

    public void handle() {
        isPonged = true;
    }

    public void initializeTimer() {
        final int interval =  10 * 1000;
        final int timeoutDelay = 3 * 1000;
        final int initializationDelay = 2 * 1000;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tcpClient.sendTcpMessage(ClientMessages.PING);
                try {
                    Thread.sleep(timeoutDelay);
                    if (!isPonged) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TCP Client", "C: Ping timed out: ");
                                ConnectionManager.getInstance().closeClient();
                                ConnectionManager.getInstance().notifyConnectionLost("Brak odpowiedzi serwera.");
                            }
                        });
                        this.cancel();
                    }
                    isPonged = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, initializationDelay, interval);
    }

    public void stopHandler() {
        if(timer != null)
            timer.cancel();
    }
}
