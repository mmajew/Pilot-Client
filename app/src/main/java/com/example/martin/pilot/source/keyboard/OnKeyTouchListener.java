package com.example.martin.pilot.source.keyboard;

import android.view.MotionEvent;
import android.view.View;

import com.example.martin.pilot.source.handlers.KeyboardHandler;


public class OnKeyTouchListener implements View.OnTouchListener{
    private KeyboardHandler handler;

    public OnKeyTouchListener(KeyboardHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(view.getTag() != null) {
            handler.handleKeyTouch(view, event);
            return true;
        }
        return false;
    }
}
