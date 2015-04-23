package com.example.martin.pilot.source.cursor;

import android.view.MotionEvent;

import com.example.martin.pilot.source.connection.TcpClient;
import com.example.martin.pilot.source.connection.UdpClient;
import com.example.martin.pilot.source.messages.ClientMessages;


public class AbsoluteMouseAssistant extends MouseAssistant {
    private TcpClient tcpClient;

    public void processMotionEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        tcpClient.sendTcpMessage(ClientMessages.TCP_MOUSE_MOVE + ";" + String.valueOf(x) + ";" + String.valueOf(y));
    }

    public AbsoluteMouseAssistant(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }
}
