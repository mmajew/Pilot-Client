package com.example.martin.pilot.source.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.martin.pilot.source.settings.SettingsActivity;


public class MainOnClickListener implements AdapterView.OnItemClickListener {
    private Context m_Context;

    public MainOnClickListener(Context context) {
        m_Context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(m_Context, SettingsActivity.class);
                m_Context.startActivity(intent);
                break;
            case 1:
                showToast("Dwa");
                //intent = new Intent(context, PlacesActivity.class);
                //context.startActivity(intent);
                break;
            case 2:
                showToast("Trzy");
                //intent = new Intent(context, ScheduleActivity.class);
                //context.startActivity(intent);
                break;
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(m_Context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
