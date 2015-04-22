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
        final int interval =  8 * 1000;
        final int timeoutDelay = 4 * 1000;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                client.sendTcpMessage(ClientMessages.PING);
                try {
                    Thread.sleep(timeoutDelay);
                    if (!isPonged) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TCP Client", "C: Ping timed out: ");
                                ConnectionManager.getInstance().confirmConnectionLost();
                            }
                        });
                        this.cancel();
                    }
                    isPonged = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, interval, interval);
    }

    public void stopHandler() {
        if(timer != null)
            timer.cancel();
    }
}
