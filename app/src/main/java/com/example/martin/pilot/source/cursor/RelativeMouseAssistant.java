package com.example.martin.pilot.source.cursor;

import android.os.AsyncTask;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import com.example.martin.pilot.source.connection.UdpClient;
import com.example.martin.pilot.source.connection.UdpConnectionTask;
import com.example.martin.pilot.source.messages.ClientMessages;


public class RelativeMouseAssistant extends MouseAssistant {
    private UdpClient udpClient;
    private int startingX;
    private int startingY;

    public void processMotionEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                startingX = (int) event.getX();
                startingY = (int) event.getY();
                new UdpConnectionTask(udpClient).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ClientMessages.UDP_CURSOR_DOWN);
                break;
            case (MotionEvent.ACTION_MOVE) :
                int x = startingX - (int) event.getX();
                int y = startingY - (int)event.getY();
                new UdpConnectionTask(udpClient).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ClientMessages.UDP_MOUSE_MOTION + ";" + String.valueOf(x) + ";" + String.valueOf(y) + ";");
                break;
            default :
                new UdpConnectionTask(udpClient).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ClientMessages.UDP_CURSOR_DOWN);
        }
    }

    public RelativeMouseAssistant(UdpClient udpClient) {
        this.udpClient = udpClient;
    }
}
