package com.bababansiwalanew.Login.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

 import com.bababansiwalanew.Dashboard.ui.Dashboard3;
import com.bababansiwalanew.Notification.service.MyFirebaseInstanceIDService;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;


/**
 * Created by Lalit on 08-03-2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView logo;
   // TextInputLayout mobileNumberLayout, passwordLayout;
    EditText password;
    EditText mobileNumber;

   // TextView clearLabel;
    TextView signup;

    //RelativeLayout rememberLayout;
    TextView loginButton,/* registerUser,*/ forgotPassword;

    private static final int REQUEST_PERMISSIONS = 20;
    private AlertDialog alertDialog, confirmDialog;
    private static String IMEI;
    private SparseIntArray mErrorString;
    private ProgressDialog mProgressDialog = null;
    MyFirebaseInstanceIDService myFirebaseInstanceIDService;
   // CheckBox rememberCheck;
    String[] recent;
    String[] recentNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.login_activity);
        setContentView(R.layout.login_new);
          myFirebaseInstanceIDService= new MyFirebaseInstanceIDService();
       // myFirebaseInstanceIDService.onTokenRefresh();
       //
        mProgressDialog = new ProgressDialog(this);


        mobileNumber =   findViewById(R.id.etMobile);
        password =   findViewById(R.id.etPassword);
        signup =   findViewById(R.id.tvSignup);

        loginButton =   findViewById(R.id.tvBtnLogin);
        //registerUser =   findViewById(R.id.tvSignup);
        forgotPassword =   findViewById(R.id.tvForget);
        loginButton.setOnClickListener(this);
       // registerUser.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
      //  rememberLayout.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == loginButton) {


            if (UtilMethods.INSTANCE.isNetworkAvialable(LoginScreen.this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();
               // Toast.makeText(getApplicationContext(),""+ FirebaseInstanceId.getInstance().getToken(),Toast.LENGTH_LONG).show();
             Log.e(" token","");
                UtilMethods.INSTANCE.secureLogin(LoginScreen.this, mobileNumber.getText().toString().trim(),
                        password.getText().toString().trim(), "","", mProgressDialog);




                //UtilMethods.INSTANCE.isRemember(LoginScreen.this, rememberFlag);
            }
            else {
                UtilMethods.INSTANCE.dialogOk(LoginScreen.this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
 if (v == signup) {
            Intent registerIntent = new Intent(LoginScreen.this, SignupScreen.class);
            registerIntent.putExtra("from","Login");
            startActivity(registerIntent);
        }

        if (v == forgotPassword) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.forgot_password, null);

            final TextInputLayout forgotTextLayout = (TextInputLayout) view.findViewById(R.id.forgotTextLayout);
            final EditText number = (EditText) view.findViewById(R.id.number);
            final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(this);

            dialog.setTitle("Forgot Password");
            dialog.setCancelable(false);
            dialog.setContentView(view);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number.getText() != null && number.getText().toString().trim().length() > 0) {
                        forgotTextLayout.setErrorEnabled(false);
                        UtilMethods.INSTANCE.ForgetPassword(LoginScreen.this, number.getText().toString().trim());
                        dialog.dismiss();
                    } else {
                        number.setError(getResources().getString(R.string.mobilenumber_error));
                        number.requestFocus();
                    }
                }
            });
            dialog.show();

        }


    }

//    public int validateForm() {
//
//        int flag = 0;
//
//        if (password.getText() != null && password.getText().toString().trim().length() >= 4) {
//            passwordLayout.setErrorEnabled(false);
//        } else {
//            password.setError(getResources().getString(R.string.password_error));
//            password.requestFocus();
//            flag++;
//        }
//
//        if (mobileNumber.getText() != null && mobileNumber.getText().toString().trim().length() > 0) {
//            mobileNumberLayout.setErrorEnabled(false);
//        } else {
//            mobileNumber.setError(getResources().getString(R.string.mobilenumber_error));
//            mobileNumber.requestFocus();
//            flag++;
//        }
//
//        return flag;
//    }

    public void startDashboard() {
        Intent intent = new Intent(LoginScreen.this, Dashboard3.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /****************************************************************************************/
                        /*Permissions Related code start*/

    /****************************************************************************************/


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
            Snackbar.make(findViewById(android.R.id.content), mErrorString.get(requestCode),
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        }
                    }).show();
        }
    }

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar.make(findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(LoginScreen.this, requestedPermissions, requestCode);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    public void onPermissionsGranted(int requestCode) {
        //Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
        IMEI = UtilMethods.INSTANCE.getDeviceId(LoginScreen.this);
    }
    /****************************************************************************************/
                        /*Permissions Related code end*/

    /****************************************************************************************/
}
