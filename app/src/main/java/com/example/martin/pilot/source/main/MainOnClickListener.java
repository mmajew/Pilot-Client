package com.example.martin.pilot.source.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.martin.pilot.R;
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
                showNoConnectionDialog();
                //intent = new Intent(context, PlacesActivity.class);
                //context.startActivity(intent);
                break;
            case 2:
                showNoConnectionDialog();
                //intent = new Intent(context, ScheduleActivity.class);
                //context.startActivity(intent);
                break;
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(m_Context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showNoConnectionDialog(){
        AlertDialog dialog = new AlertDialog.Builder(m_Context, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
        dialog.setTitle("Brak połączenia");
        dialog.setMessage("Najpierw nawiąż połączenie z serwerem");
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
    }
}
