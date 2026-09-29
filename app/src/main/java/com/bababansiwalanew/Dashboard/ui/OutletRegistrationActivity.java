package com.bababansiwalanew.Dashboard.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OutletRegistrationActivity extends AppCompatActivity    {
    Button submit;
    EditText otp;
    String latlong ;
    TextView FName, LName, DOB, sendermobile, Pincode, Address, Area, Pan, Aadhar, Panlink, Aadharlink, OTP, email;
    Button Submit, getotp;
    private Toolbar toolbar;
    ProgressDialog mProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_registration);
        // loader = new CustomLoader(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        mProgressDialog = new ProgressDialog(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Outlet Registration");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        FName=(TextView)findViewById(R.id.FName);
        LName=(TextView)findViewById(R.id.LName);
        email=(EditText)findViewById(R.id.email);
        DOB=(TextView)findViewById(R.id.DOB);
        sendermobile=(TextView)findViewById(R.id.sendermobile);
        Pincode=(TextView)findViewById(R.id.Pincode);
        Address=(TextView)findViewById(R.id.Address);
        Area=(TextView)findViewById(R.id.Area);
        Pan=(TextView)findViewById(R.id.Pan);
        Aadhar=(TextView)findViewById(R.id.Aadhar);
        Panlink=(TextView)findViewById(R.id.Panlink);
        Aadharlink=(TextView)findViewById(R.id.Aadharlink);
        OTP=(TextView)findViewById(R.id.OTP);
        Submit=(Button)findViewById(R.id.Submit);
        getotp=(Button)findViewById(R.id.getotp);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UtilMethods.INSTANCE.isNetworkAvialable(getApplicationContext())) {
                    mProgressDialog.setTitle("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.OutletRegistration(OutletRegistrationActivity.this,
                            LName.getText().toString(),
                            email.getText().toString(),
                            sendermobile.getText().toString(),
                            Pincode.getText().toString(),
                            Address.getText().toString(),
                            Pan.getText().toString(),
                            OTP.getText().toString(),
                            latlong,
                            mProgressDialog);
                    // Area.getText().toString(),
                } else {
//                UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.err_msg_network_title),
//                        getResources().getString(R.string.err_msg_network));
                }
            }
        });
        //getotp.setOnClickListener(this);
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        sendermobile.setText(UMobile);
        sendermobile.setEnabled(false);
        Getotp();

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                DOB.setText(sdf.format(myCalendar.getTime()));
            }

        };


        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pd=  new DatePickerDialog(OutletRegistrationActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_POSITIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEUTRAL)
//                        .setTextColor(Color.GRAY) ;
                pd.show();
                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });
    }

    private void Getotp() {

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

           mProgressDialog.setTitle("Loading...");
           mProgressDialog.show();
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

           UtilMethods.INSTANCE.OutletRegistartionreOTP(OutletRegistrationActivity.this,mobileLogin,mProgressDialog);



        } else {
            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.err_msg_network),
                    getResources().getString(R.string.err_msg_network), 2);
        }

    }




    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("outletRegistered")) {
           String outletregisteredresponse=activityFragmentMessage.getMessage();
           if (outletregisteredresponse!=null){
               LayoutInflater inflater = (LayoutInflater) OutletRegistrationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               View view = inflater.inflate(R.layout.layoutpoop, null);
               ImageView imageView =(ImageView) view.findViewById(R.id.dialog_logo);
               TextView tc=(TextView)view.findViewById(R.id.dialog_content);
               final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
               TextView tt=(TextView)view.findViewById(R.id.dialog_title);
               final Dialog dialog = new Dialog(OutletRegistrationActivity.this);

               dialog.setCancelable(false);
               dialog.setContentView(view);
               imageView.setVisibility(View.GONE);
               tt.setVisibility(View.GONE);
               tc.setText(""+outletregisteredresponse);
               okButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                     finish();
                   }
               });
               dialog.show();
           }
 else {}       }
    }
        @Override
        public void onStart() {
            super.onStart();
            if (!EventBus.getDefault().isRegistered(this)) {
                GlobalBus.getBus().register(this);
            }
        }
    }
