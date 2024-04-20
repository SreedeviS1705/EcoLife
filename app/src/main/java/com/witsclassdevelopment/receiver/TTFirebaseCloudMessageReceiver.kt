package com.witsclassdevelopment.receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.witsclassdevelopment.R
import com.witsclassdevelopment.ui.SplashScreenActivity

class TTFirebaseCloudMessageReceiver : FirebaseMessagingService() {
    companion object {
        const val TAG  = "CloudMessageReceiver"
    }
    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "onMessageReceived:1 ")
        Log.d(TAG, "onMessageReceived:2 "+message.notification?.title)
        Log.d(TAG, "onMessageReceived:3 "+message.notification?.body)
        val title = message.notification?.title
        val body = message.notification?.body
        if(message.data.isNotEmpty()) {
            Log.d(TAG, "onMessageReceived: "+message.data)

            if(message.notification != null) {
                Log.d(TAG, "onMessageReceived: "+message?.notification?.body)
            }

        }
        val intent:Intent = Intent(applicationContext, SplashScreenActivity::class.java)
        val contentIntent: PendingIntent = PendingIntent.getActivity(
            this,
            1001,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val CHANNEL_ID = "HEADS_UP_NOTIFICATION"



        val notification: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.text_tutor_top_logo_ic)
                .setAutoCancel(true)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        notification.setContentIntent(contentIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        assert(notificationManager != null)
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1000, notification.build());

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: ")
    }
}