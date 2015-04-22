package com.example.martin.pilot.source.handlers;

import android.util.Log;
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

    public void updateCursorMode(boolean isUdpModeOn) {
        if (isUdpModeOn)
            Log.e("TCP Client", "in CURSOR WAS CHECKED YO");

        mouseAssistant = isUdpModeOn ? new RelativeMouseAssistant() : new AbsoluteMouseAssistant();
    }

    public void handleMouseClick(MouseButton button) {
        String message = button == MouseButton.leftMouseButton ?
                ClientMessages.LEFT_CLICK :  ClientMessages.RIGHT_CLICK;

        client.sendTcpMessage(message);
    }

    public void handleCursorMovement(MotionEvent event) {
        String message = mouseAssistant.processMotionEvent(event);
        client.sendTcpMessage(message);
    }
}
