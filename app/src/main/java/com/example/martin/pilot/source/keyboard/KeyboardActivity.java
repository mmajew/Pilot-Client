package com.example.martin.pilot.source.keyboard;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.martin.pilot.R;
import com.example.martin.pilot.source.handlers.CursorHandler;
import com.example.martin.pilot.source.handlers.KeyboardHandler;


public class KeyboardActivity extends ActionBarActivity {
    private KeyboardHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        handler = new KeyboardHandler();
        new KeyboardInitializer(handler, this).initializeKeys();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_keyboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getViewGroup() {
        final View keyboard = findViewById(R.id.keyboard);
        ViewGroup viewgroup=(ViewGroup)keyboard.getParent();
        int childCount = viewgroup.getChildCount();
        Log.e("Keyboard activity", "ChildCount: " + String.valueOf(childCount));
    }

    private void initializeListeners() {
        final ImageButton lShiftButton = (ImageButton) findViewById(R.id.lShiftButton);
        lShiftButton.setTag("lShift");
        lShiftButton.setOnTouchListener(new OnKeyTouchListener(handler));

        final Button eButton = (Button) findViewById(R.id.eButton);
        eButton.setTag("e");
        eButton.setOnTouchListener(new OnKeyTouchListener(handler));

    }

    private void initializeButtons() {

    }
}
