package com.bababansiwalanew;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import com.bababansiwalanew.Dashboard.ui.Dashboard3;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {
    private Bitmap bitmap;
    private String image;
    public static String URGENT_CHANNEL = "com.sigma.yourwallet.urgent";
    private static final String TAG = "FCM Service";
    private static int count = 0;
    Boolean isInBackground;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super .onMessageReceived(remoteMessage);
        // createChannel();
        sendNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getSound());

    }
    private RemoteViews getCustomDesign(String title,
                                        String message
    ) {
        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews = new RemoteViews(
                getApplicationContext().getPackageName(),
                R.layout.notification);
        remoteViews.setTextViewText(R.id.title, title);
        remoteViews.setTextViewText(R.id.message, message);
        remoteViews.setImageViewResource(R.id.icon,
                R.drawable.logo);
        return remoteViews;
    }
    void createChannel() {

      //  Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.video_call);
        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel("videocall", "VIDEO CALL", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setLightColor(Color.GRAY);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            //mChannel.getLockscreenVisibility(NotificationVisibility);
            mChannel.setDescription("VIDEO CALL");
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
           // mChannel.setSound(sound, audioAttributes);

            NotificationManager notificationManager =
                    (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);

        }

    }



    private void sendNotification(String messageTitle, String messageBody,String sound) {
        Log.i(TAG, "sendNotification: ");
        Intent intent = new Intent(this, Dashboard3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("page","order");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0  /*Request code*/ , intent,
                PendingIntent.FLAG_ONE_SHOT);
        Log.e(TAG, "sendNotification: "+ messageBody);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)

                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setSound(Uri.parse(sound))
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }else {

        }
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder = notificationBuilder.setContent(
                    getCustomDesign(messageTitle, messageBody));
        }
        else {
            notificationBuilder = notificationBuilder.setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setSmallIcon(R.drawable.logo);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }


}

