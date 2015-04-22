package com.example.martin.pilot.source.cursor;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by marmajew on 4/22/2015.
 */
public class RelativeMouseAssistant extends MouseAssistant {
    public String processMotionEvent(MotionEvent event) {
        Log.e("TCP Client", "C: LPM");
        return "";
    }
}
