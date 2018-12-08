package com.example.tecsup.mensajes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {





    @Override
    public void onReceive(Context context, Intent intent) {



        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            /* Get Messages */
            Object[] sms = (Object[]) intentExtras.get("pdus");

            for (int i = 0; i < sms.length; ++i) {
                /* Parse Each Message */
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String phone = smsMessage.getOriginatingAddress();
                String message = smsMessage.getMessageBody().toString();

                Toast.makeText(context, phone + ": " + message, Toast.LENGTH_SHORT).show();
            }
        }

        Cursor cur = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);

        if (cur.moveToFirst()) { /* false = no sms */
            do {
                String msgInfo = "";

                for (int i = 0; i < cur.getColumnCount(); i++) {
                    msgInfo += " " + cur.getColumnName(i) + ":" + cursor.getString(i);
                }

                Toast.makeText(this, msgInfo, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
    }

}
