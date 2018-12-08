package com.example.tecsup.notificacionesv3;

import android.app.Service;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class servicioNotificaciones extends  FirebaseMessagingService {
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            Log.e("FIREBASE", remoteMessage.getNotification().getBody());
        }
}
