package com.example.martin.pilot.source.tools;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


public class DialogFactory {
    public static AlertDialog getNoConnectionDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
        dialog.setTitle("Brak połączenia");
        dialog.setMessage("Najpierw nawiąż połączenie z serwerem");
        dialog.setIcon(android.R.drawable.ic_dialog_alert);

        return dialog;
    }

    public static AlertDialog getConnectionlostDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
        dialog.setTitle("Utracono połączenie");
        dialog.setMessage("Brak odpowiedzi z serwera");
        dialog.setIcon(android.R.drawable.ic_dialog_alert);

        return dialog;
    }

    public static ProgressDialog getAwaitingConnectionDialog(Context context, DialogInterface.OnClickListener listener) {
        ProgressDialog dialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Łączenie");
        dialog.setMessage("Oczekiwanie na odpowiedź serwera");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Anuluj", listener);

        return dialog;
    }
}
