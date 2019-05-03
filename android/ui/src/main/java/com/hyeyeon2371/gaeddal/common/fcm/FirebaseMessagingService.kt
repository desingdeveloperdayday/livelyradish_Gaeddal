package com.hyeyeon2371.gaeddal.common.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.hyeyeon2371.gaeddal.main.MainActivity
import com.hyeyeon2371.gaeddal.R
import java.text.SimpleDateFormat
import java.util.*

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage!!.notification != null) {
            sendNotification(remoteMessage.notification!!)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage.Notification) {
        val sp = getSharedPreferences("notification", Context.MODE_PRIVATE)
        val isPushAllowed = sp.getBoolean("push", true)

        if (isPushAllowed) {
            val CHANNEL_ID = "NOTIFICATION"
            val CHANNEL_NAME = "GAEDDAL_NOTIFICATION"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val isVibrationAllowed = sp.getBoolean("vibration", true)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                channel.setShowBadge(false)
                notificationManager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(remoteMessage.title)
                .setContentText(remoteMessage.body)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(PendingIntent.getActivity(this, 0,
                    Intent(this, MainActivity::class.java), PendingIntent.FLAG_ONE_SHOT))

            if (isVibrationAllowed) {
                notification.setDefaults(Notification.DEFAULT_VIBRATE)
            } else {
                notification.setVibrate(LongArray(1) { 0L })
            }

            notificationManager.notify(createNotificationId(), notification.build())
        }

    }

    private fun createNotificationId(): Int {
        val now = Date()
        return SimpleDateFormat("SSS", Locale.KOREA).format(now).toInt()
    }

}