package com.example.tecsup.notificacionesv3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadata;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.onesignal.OneSignal;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

    int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }
    public void agregarNotificacion(View v){
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



        NotificationCompat.Builder builder1 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.imgnotificacion)
                        .setContentTitle("Notificacion creada")
                        .setSound(alarm)
                        .setContentText("Abrir actividad")
                        .setOngoing(true)
                        .setAutoCancel(true)
                        .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("When the morning comes\n" +
                                "When we see what we've become\n" +
                                "In the cold light of day we're a flame in the wind\n" +
                                "Not the fire that we begun\n" +
                                "Every argument, every word we can't take back\n" +
                                "'Cause with all that has happened\n" +
                                "I think that we both know the way that the story ends"))
                        .setNumber(ID);


        Intent notificacionIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificacionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder1.setContentIntent(contentIntent);
        long[] vibraPattern = { 1000, 1000, 1000, 1000,1000,1000 };
        builder1.setVibrate(vibraPattern);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(ID++,builder1.build());


    }

    private void crearCanalNotificacion() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.CHANNEL_ID), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notificacionOreo(View v){
        crearCanalNotificacion();
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, getString(R.string.CHANNEL_ID))
                .setSmallIcon(R.drawable.imgnotificacion)
                .setContentTitle("Notificacion Oreo")
                .setContentText("Mi primera notificacion en Oreo")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(ID++, mBuilder.build());
    }





}
