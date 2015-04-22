package com.example.martin.pilot.source.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.martin.pilot.source.connection.ConnectionManager;
import com.example.martin.pilot.source.cursor.CursorActivity;
import com.example.martin.pilot.source.settings.SettingsActivity;
import com.example.martin.pilot.source.tools.DialogFactory;


public class MainOnClickListener implements AdapterView.OnItemClickListener {
    private Context context;

    public MainOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent;
        boolean isConnected = ConnectionManager.getInstance().isConnected();
        switch (position) {
            case 0:
                intent = new Intent(context, SettingsActivity.class);
                context.startActivity(intent);
                break;
            case 1:
                if(!isConnected)
                    DialogFactory.getNoConnectionDialog(context).show();
                else {
                    intent = new Intent(context, CursorActivity.class);
                    context.startActivity(intent);
                }
                break;
            case 2:
                if(!isConnected)
                    DialogFactory.getNoConnectionDialog(context).show();
                //intent = new Intent(context, ScheduleActivity.class);
                //context.startActivity(intent);
                break;
        }
    }
}
