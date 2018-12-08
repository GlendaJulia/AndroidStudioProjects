package com.example.tecsup.glendajuliapractica03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SimpleSMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages = SmsMessage.createFromPdu((byte[])pdus[0]);


        Intent smsIntent= new Intent(context, sms.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());
        smsIntent.putExtra("Message", messages.getMessageBody());
        context.startActivity(smsIntent);
        Toast.makeText(context, "SMS Recibido de :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody(), Toast.LENGTH_LONG).show();
    }
}