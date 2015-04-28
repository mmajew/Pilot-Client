package com.example.martin.pilot.source.tools;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


public class DialogFactory {
    public static AlertDialog getNoConnectionDialog(Context context) {
        return createAlertDialog(context, "Brak połączenia", "Najpierw nawiąż połączenie z serwerem");
    }

    public static AlertDialog getConnectionlostDialog(Context context, String message) {
        return createAlertDialog(context, "Utracono połączenie", message);
    }

    public static AlertDialog getUdpFailedDialog(Context context) {
        return createAlertDialog(context, "Nie udało się nawiązać połączenia UDP", "Aplikacja będzie kontyunować w trybie TCP only");
    }

    public static ProgressDialog getAwaitingConnectionDialog(Context context, DialogInterface.OnClickListener listener) {
        ProgressDialog dialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Łączenie");
        dialog.setMessage("Oczekiwanie na odpowiedź serwera");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Anuluj", listener);

        return dialog;
    }

    private static AlertDialog createAlertDialog(Context context, String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return dialog;
    }
}
