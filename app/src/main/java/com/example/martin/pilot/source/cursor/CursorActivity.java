package com.example.martin.pilot.source.cursor;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.handlers.CursorHandler;
import com.example.martin.pilot.source.main.BaseActivity;
import com.example.martin.pilot.source.settings.SettingsManager;


public class CursorActivity extends BaseActivity {
    private CursorHandler cursorHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);

        boolean isUdpEnabled = SettingsManager.getInstance().getIsUdpEnabled();
        cursorHandler = new CursorHandler(isUdpEnabled);

        final View cursorPane = findViewById(R.id.cursorView);
        cursorPane.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                cursorHandler.sendViewSize(v.getWidth(), v.getHeight());
            }
        });

        assignListeners();
    }

    @Override
    public void onResume() {
        super.onResume();

        cursorHandler.updateCursorMode(SettingsManager.getInstance().getIsUdpEnabled());
    }

    public void assignListeners() {
        final Button leftMouseButton = (Button) findViewById(R.id.lmButton);
        leftMouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursorHandler.handleMouseClick(CursorHandler.MouseButton.leftMouseButton);
            }
        });

        final Button rightMouseButton = (Button) findViewById(R.id.rmButton);
        rightMouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursorHandler.handleMouseClick(CursorHandler.MouseButton.rightMouseButton);
            }
        });

        final View cursorPane = findViewById(R.id.cursorView);
        cursorPane.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cursorHandler.handleCursorMovement(event);
                return true;
            }
        });
    }
}
