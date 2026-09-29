package com.bababansiwalanew.Notification.service;

import android.content.SharedPreferences;
import android.util.Log;

import com.bababansiwalanew.Notification.app.Config;
import com.bababansiwalanew.Util.UtilMethods;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService  extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
//
//    @Override
//    public void onNewToken() {
//        super.onNewToken();
//      //  String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//        // Saving reg id to shared preferences
//        //storeRegIdInPref(refreshedToken);
//
//        // sending reg id to your server
//       // sendRegistrationToServer(refreshedToken);
//
//        // Notify UI that registration has completed, so the progress indicator can be hidden.
//        //if (getCallingActivity().getPackageName().equals(BuildConfig.APPLICATION_ID)) {
////            Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
////            registrationComplete.putExtra("token", refreshedToken);
////            LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
//       // }
//    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
        UtilMethods.INSTANCE.setKeyId(getApplicationContext(), token);
        UtilMethods.INSTANCE.setRegKey(getApplicationContext(), "7d7d8f5f-7412-49dc-bc55-aeb56ee7713c");
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }
}

