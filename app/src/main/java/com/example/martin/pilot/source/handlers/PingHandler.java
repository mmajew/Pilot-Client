package com.example.martin.pilot.source.handlers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.martin.pilot.source.connection.ConnectionManager;

import java.util.Timer;
import java.util.TimerTask;


public class PingHandler extends TaskHandler {
    private boolean isPonged = false;
    private Timer timer;

    public void handle() {
        isPonged = true;
    }

    public void initializeTimer() {
        final int interval =  4 * 1000;
        final int timeoutDelay = 2 * 1000;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                client.sendMessage("C:PING");
                try {
                    Thread.sleep(timeoutDelay);
                    if (!isPonged) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
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
