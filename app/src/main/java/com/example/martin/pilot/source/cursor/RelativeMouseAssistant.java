package com.example.martin.pilot.source.cursor;

import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;

import com.example.martin.pilot.source.connection.UdpClient;
import com.example.martin.pilot.source.connection.UdpConnectionTask;
import com.example.martin.pilot.source.messages.ClientMessages;

public class RelativeMouseAssistant extends MouseAssistant {
    private UdpClient udpClient;

    public void processMotionEvent(MotionEvent event) {
        new UdpConnectionTask(udpClient).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ClientMessages.UDP_MOUSE_MOTION);
    }

    public RelativeMouseAssistant(UdpClient udpClient) {
        this.udpClient = udpClient;
    }
}
