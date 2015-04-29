package com.example.martin.pilot.source.handlers;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.martin.pilot.source.messages.ClientMessages;


public class KeyboardHandler extends TaskHandler {
    public void handleKeyTouch(View view, MotionEvent event) {
        String key = view.getTag().toString();

        int action = MotionEventCompat.getActionMasked(event);
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                sendKeyDown(key);
                break;
            case (MotionEvent.ACTION_UP) :
            case (MotionEvent.ACTION_CANCEL) :
                sendKeyUp(key);
                break;
        }
    }

    private void sendKeyDown(String key){
        tcpClient.sendTcpMessage(ClientMessages.TCP_KEY_DOWN + ";" + key);
    }

    private void sendKeyUp(String key){
        tcpClient.sendTcpMessage(ClientMessages.TCP_KEY_UP + ";" + key);
    }
}
