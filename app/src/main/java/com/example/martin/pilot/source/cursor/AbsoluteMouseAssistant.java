package com.example.martin.pilot.source.cursor;

import android.view.MotionEvent;

import com.example.martin.pilot.source.messages.ClientMessages;

/**
 * Created by marmajew on 4/22/2015.
 */
public class AbsoluteMouseAssistant extends MouseAssistant {
    public String processMotionEvent(MotionEvent event) {
        String rawX = String.valueOf(event.getRawX()) + ";";
        String rawY = String.valueOf(event.getRawY()) + ";";
        String x = String.valueOf(event.getX()) + ";";
        String y = String.valueOf(event.getY()) + ";";

        return ClientMessages.TCP_MOUSE_MOVE + ";" + rawX + rawY + x + y;
    }
}
