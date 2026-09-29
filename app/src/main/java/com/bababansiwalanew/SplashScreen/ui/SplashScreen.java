package com.bababansiwalanew.SplashScreen.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.bababansiwalanew.Dashboard.ui.Dashboard3;
import com.bababansiwalanew.GooglePlayStoreAppVersionNameLoader;
import com.bababansiwalanew.Login.ui.LoginScreen;
import com.bababansiwalanew.Notification.app.Config;
import com.bababansiwalanew.Notification.util.NotificationUtils;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;

public class SplashScreen extends AppCompatActivity {

    private ProgressDialog mProgressDialog = null;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final int REQUEST_PERMISSIONS = 1;
    private static String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,    Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA
             };
    private static final int READ_EXTRENAL_MEDIA_PERMISSIONS_REQUEST = 1;

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

         LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
  LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
   NotificationUtils.clearNotifications(getApplicationContext());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new GooglePlayStoreAppVersionNameLoader().execute();
        UtilMethods.INSTANCE.setRegKey(getApplicationContext(), "7d7d8f5f-7412-49dc-bc55-aeb56ee7713c");

         mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
 if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                      FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                }
            }
        };

        mProgressDialog = new ProgressDialog(this);
        ReadPhoneStatePermission();
    }

    public void HitApi()
    {
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String apicheck = myPrefs.getString(ApplicationConstant.INSTANCE.prefapi, null);

        if (UMobile != null && UMobile.length() > 0)
        {
            Log.e("check","1");
            UtilMethods.INSTANCE.getDeviceId(this);
            dashboardpage();
        }

        else if (apicheck != null && apicheck.equals("1")&& UMobile.equals("") )
        {
            Log.e("check","2");
            loginpage();
        }
        else
        {
            if (UtilMethods.INSTANCE.isNetworkAvialable(this))
            {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.startingOperatorService(SplashScreen.this, mProgressDialog);
            }
            else
            {
                UtilMethods.INSTANCE.dialogOk(SplashScreen.this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 4);
            }
        }
    }

    public void startDashboard() {
        Intent intent = new Intent(SplashScreen.this, Dashboard3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void startLogin() {
        Intent intent = new Intent(SplashScreen.this, LoginScreen.class);
        startActivity(intent);
        finish();
    }

    public void dashboardpage() {

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    startDashboard();
                }
            }
        };
        timerThread.start();
    }

    public void loginpage() {

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    startLogin();
                }
            }
        };
        timerThread.start();
    }
    public void ReadPhoneStatePermission() {
         if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            ActivityCompat.requestPermissions(SplashScreen.this, PERMISSIONS, REQUEST_PERMISSIONS);
        } else {
             ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length == 4 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                HitApi();

            }  } else if (requestCode == READ_EXTRENAL_MEDIA_PERMISSIONS_REQUEST) {
                if (grantResults.length == 4 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SplashScreen.this, " permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SplashScreen.this, " permission denied", Toast.LENGTH_SHORT).show();
                }
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }