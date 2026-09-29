package com.bababansiwalanew.Notification.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.bababansiwalanew.Dashboard.ui.Dashboard3;
import com.bababansiwalanew.Notification.NotificationActivity;
import com.bababansiwalanew.Notification.app.Config;
import com.bababansiwalanew.Notification.util.NotificationUtils;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends FirebaseMessagingService  {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;
    private Bitmap bitmap;
    private String image;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage == null)
            return;
        else {
            Log.e("coming", "here data : " + remoteMessage.getData().get("message"));

            if (remoteMessage.getData().get("key") != null && remoteMessage.getData().get("key").length() > 0) {
                // UtilMethods.INSTANCE.setRegKey(getApplicationContext(), remoteMessage.getData().get("key"));
                UtilMethods.INSTANCE.setRegKey(getApplicationContext(), "7d7d8f5f-7412-49dc-bc55-aeb56ee7713c");
            }




        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }



            final String message = remoteMessage.getData().get("body");
            image = remoteMessage.getData().get("icon");
            if (image != null && !image.isEmpty()) {
                image = ApplicationConstant.INSTANCE.baseUrl + "/Image/Notification/" + image;
            }
            final String url = remoteMessage.getData().get("Url");
            final String title = remoteMessage.getData().get("title");
            final String key = remoteMessage.getData().get("Key");
            final String postDate = remoteMessage.getData().get("PostDate");
            final String type = remoteMessage.getData().get("Type");
        /*final String sessionId = remoteMessage.getData().get("SessionId");
        final String isValidate = remoteMessage.getData().get("IsValidate");
        final String userId = remoteMessage.getData().get("UserId");*/

            int notification_id = 1;
            try {
                notification_id = Integer.parseInt(key);
            } catch (NumberFormatException nfe) {
                notification_id = 1;
            }


            // if (type != null && !type.isEmpty() && type.equalsIgnoreCase("Browsable_Notification")) {
            sendNewNotificationBrodcast();
            bitmap = getBitmapfromUrl(image);

            if (bitmap != null) {
                // showNotification(message, bitmap, url, title, key, postDate, type, notification_id);
                showNotification(message, image, type, postDate, bitmap, url, title, notification_id);
            } else {

                final int finalNotification_id = notification_id;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        new generatePictureStyleNotification(message, image, url, title, key, postDate, type, finalNotification_id).execute();

                    }
                });
            }

    }


        //message will contain the Push Message

    }


    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
    private void sendNewNotificationBrodcast() {
        Intent intent = new Intent("New_Notification_Detect");
        // You can also include some extra data.
        intent.putExtra("message", "New Notification");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
       // UtilMethods.INSTANCE.setFCMRegKey(this, s);

       // if (UtilMethods.INSTANCE.getIsLogin(this) == 1) {
       //     UtilMethods.INSTANCE.updateFcm(this);
       // }
        UtilMethods.INSTANCE.setRegKey(getApplicationContext(), "7d7d8f5f-7412-49dc-bc55-aeb56ee7713c");

    }
    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);


           if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), Dashboard3.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
    private void showNotification(String messageBody, String imageUrl, String type, String postDate, Bitmap image, String url, String contentTitle, int notification_id) {
        String CHANNEL_ID = getPackageName();

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Title", contentTitle);
        intent.putExtra("Message", messageBody);
        intent.putExtra("Image", imageUrl);
        intent.putExtra("Url", url);
        intent.putExtra("Time", postDate + "");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, notification_id + 2 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(contentTitle)
                .setAutoCancel(true)
                .setContentText(messageBody)
                .setTicker(messageBody)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setDefaults(Notification.DEFAULT_SOUND)
                .setGroup(this.getPackageName() + "." + type)
                .setChannelId(CHANNEL_ID)
                .setGroupSummary(true)
                .setContentIntent(pendingIntent);
        if (image != null) {
            notification.setLargeIcon(image);
            notification.setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(image)
                    .setSummaryText(messageBody)
                    .setBigContentTitle(contentTitle));
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_MIN;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, getPackageName() + " Service", importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(notification_id + 2, notification.build());
    }
    public class generatePictureStyleNotification extends AsyncTask<String, Void, Bitmap> {


        private String url, message, image, title, key, postDate, type;
        private int notification_id = 1;

        public generatePictureStyleNotification(String message, String image, String url, String title, String key, String postDate, String type, int notification_id) {
            super();
            this.url = url;
            this.notification_id = notification_id;
            this.message = message;
            this.image = image;
            this.title = title;
            this.key = key;
            this.postDate = postDate;
            this.type = type;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bitmap = getBitmapfromUrl(this.image);
            if (bitmap != null) {
                return bitmap;
            } else {
                return null;
            }

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            //showNotification(message, result, url, title, key, postDate, type, notification_id);
            showNotification(message, image, type, postDate, result, url, title, notification_id);
        }
    }
}
