package com.example.martin.pilot.source.handlers;

import android.view.MotionEvent;

import com.example.martin.pilot.source.cursor.AbsoluteMouseAssistant;
import com.example.martin.pilot.source.cursor.MouseAssistant;
import com.example.martin.pilot.source.cursor.RelativeMouseAssistant;
import com.example.martin.pilot.source.messages.ClientMessages;


public class CursorHandler extends TaskHandler {
    private MouseAssistant mouseAssistant;

    public enum MouseButton {
        leftMouseButton,
        rightMouseButton
    }

    public CursorHandler(boolean isUdpModeOn) {
        updateCursorMode(isUdpModeOn);
    }

    public void sendViewSize(int paneHeight, int paneWidth) {
        String message = ClientMessages.TOUCH_AREA_SIZE + ";" + String.valueOf(paneHeight) + ";" + String.valueOf(paneWidth);
        tcpClient.sendTcpMessage(message);
    }

    public void updateCursorMode(boolean isUdpModeOn) {
        mouseAssistant = isUdpModeOn ? new RelativeMouseAssistant(udpClient) : new AbsoluteMouseAssistant(tcpClient);
    }

    public void handleMouseClick(MouseButton button) {
        String message = button == MouseButton.leftMouseButton ?
                ClientMessages.LEFT_CLICK :  ClientMessages.RIGHT_CLICK;

        tcpClient.sendTcpMessage(message);
    }

    public void handleCursorMovement(MotionEvent event) {
        mouseAssistant.processMotionEvent(event);
    }
}
