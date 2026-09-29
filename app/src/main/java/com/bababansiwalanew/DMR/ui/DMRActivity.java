package com.bababansiwalanew.DMR.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
 import com.bababansiwalanew.DMR.dto.LoginSenderResponse;
import com.bababansiwalanew.DMR.dto.TABLE;
import com.bababansiwalanew.DMRReport.ui.DMRReportScreen;
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

public class DMRActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout formContainer, loginContainer, currentLogin;
    RelativeLayout optionMenu;

    LinearLayout llSubmit;
    ProgressBar progressBar;
    TextView capturePercentage, statusMessage;
    ImageView fingerprintImage;
    TextView loginLabel, loginButton;
    public static EditText senderLoginNumber, senderName,senderLame, senderCreateNumber, address;
    TextView dob;
    TextView beneficiaryDetail;
    FloatingActionButton faddBeneficiary;
    RelativeLayout currentLogoutContainer, createContainer;
    TextView currentMobile, currentName;
    RadioButton rblogin, rbcreate;
    RelativeLayout dmrLayout;
    EditText otp;
    CardView card_view9,
            card_view10, card_view11, card_view12;
    AlertDialog.Builder alertBuilder;
    RadioButton
            radio9, radio10, radio11, radio12;
    TABLE senderTableInfo;
    TextView kycText;
    TextView name;
    TextView currency;
    TextView addbene;
    TextView tvdmrreport;
    TextView limitUsed;
    TextView remaining;
    TextView tvLogout;
    RadioGroup rglogin;
    String otpReff="";
    public static String remiterid = "";
    private ProgressDialog mProgressDialog = null;
    EditText createpin;


    final Calendar myCalendar = Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener dobDateDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            dobDateDialog();
        }

    };

    private final ActivityResultLauncher<Intent> AuthenticateSenderLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() ==100 /*Activity.RESULT_OK*/ && result.getData() != null) {
                            // Extract the PID data
                            otpReff = result.getData().getStringExtra("reffId");
                            createContainer.setVisibility(View.VISIBLE);
                            rbcreate.setChecked(true);
                            rblogin.setChecked(false);

                        } else {

                            Log.e("error", "AuthenticateSenderLauncher failed or cancelled.");
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmractivity);
        mProgressDialog = new ProgressDialog(this);
        // imageView = (ImageView) findViewById(R.id.imgView);

        formContainer = findViewById(R.id.formContainer);
        optionMenu = findViewById(R.id.optionMenu);

        //card_view_dmr = (CardView) findViewById(R.id.card_view_dmr);
        loginContainer = findViewById(R.id.loginContainer);
        currentLogin = findViewById(R.id.currentLogin);

        // addBeneficiary = (View) findViewById(R.id.addBeneficiary);
        faddBeneficiary = findViewById(R.id.faddBeneficiary);
        rglogin = findViewById(R.id.rglogin);
        addbene = findViewById(R.id.addbene);
        createpin = findViewById(R.id.createpin);
        address = findViewById(R.id.address);
        dob = findViewById(R.id.dob);
        otp = findViewById(R.id.otp);
        llSubmit = findViewById(R.id.llSubmit);
        tvdmrreport = findViewById(R.id.tvdmrreport);

        createContainer = findViewById(R.id.createContainer);
        rblogin = findViewById(R.id.rblogin);
        rbcreate = findViewById(R.id.rbcreate);

        loginButton = findViewById(R.id.loginButton);
        senderLoginNumber = findViewById(R.id.senderLoginNumber);
        senderName = findViewById(R.id.sendername);
        senderLame = findViewById(R.id.senderLame);

        kycText = findViewById(R.id.kycText);
        name = findViewById(R.id.name);
        currency = findViewById(R.id.currency);
        limitUsed = findViewById(R.id.limitUsed);
        remaining = findViewById(R.id.remaining);
        beneficiaryDetail = findViewById(R.id.beneficiaryDetail);

        currentLogoutContainer = findViewById(R.id.currentLogoutContainer);
        currentMobile = findViewById(R.id.currentMobile);
        currentName = findViewById(R.id.currentName);
        tvLogout = findViewById(R.id.tvLogout);

        currentLogoutContainer.setOnClickListener(this);
        //senderLogout.setOnClickListener(this);
        beneficiaryDetail.setOnClickListener(this);
//        loginSenderRadio.setOnClickListener(this);
//        createSenderRadio.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        createContainer.setOnClickListener(this);
        llSubmit.setOnClickListener(this);
        dob.setOnClickListener(this);




        dmrLayout = findViewById(R.id.dmrLayout);
        card_view9 = findViewById(R.id.card_view9);
        card_view10 = findViewById(R.id.card_view10);
        card_view11 = findViewById(R.id.card_view11);
        card_view12 = findViewById(R.id.card_view12);


        radio9 = findViewById(R.id.radio9);
        radio10 = findViewById(R.id.radio10);
        radio11 = findViewById(R.id.radio11);
        radio12 = findViewById(R.id.radio12);


        tvLogout.setOnClickListener(this);
        card_view9.setOnClickListener(this);
        card_view10.setOnClickListener(this);
        card_view11.setOnClickListener(this);
        card_view11.setVisibility(View.GONE);
        card_view12.setOnClickListener(this);
        card_view12.setVisibility(View.GONE);

        radio9.setOnClickListener(this);
        radio10.setOnClickListener(this);
        radio11.setOnClickListener(this);
        radio12.setOnClickListener(this);
        addbene.setOnClickListener(this);
        tvdmrreport.setOnClickListener(this);
        alertBuilder = new AlertDialog.Builder(this);
        findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharedPreferences prefs = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String isLogin = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);
        IsSenderLogin(isLogin);
        rglogin.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rblogin) {
                createContainer.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbcreate) {
                createContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    public void dobDateDialog() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {


        if (v == loginButton || v.getId() == R.id.llSubmit) {
            if (rblogin.isChecked()) {
                if (validationForm("login") == 0) {
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.GetSender(this, senderLoginNumber.getText().toString().trim(), mProgressDialog, new UtilMethods.ApiCallBackDMRMethod() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onSucess(Object object) {
                                LoginSenderResponse response = (LoginSenderResponse) object;
                                UtilMethods.INSTANCE.setSenderNumber(DMRActivity.this, response.getData().getMobile());
                                currentMobile.setText("Sender Mobile : " + response.getData().getMobile());
                                currentName.setText("Sender Name : " + response.getData().getName());
                                limitUsed.setText(getResources().getString(R.string.rupiya) + response.getData().getUsedLimit());
                                remaining.setText(response.getData().getAvailableLimit());
                                if (response.getData().getRemiterid() != null && !response.getData().getRemiterid().isEmpty() && !response.getData().getRemiterid().equalsIgnoreCase("null")) {
                                    remiterid = response.getData().getRemiterid();
                                } else {
                                    remiterid = "";
                                }

                                loginContainer.setVisibility(View.GONE);
                                formContainer.setVisibility(View.VISIBLE);
                                faddBeneficiary.setVisibility(View.GONE);


                            }

                            @Override
                            public void onAuthRequired(Object object) {

                                LoginSenderResponse response = (LoginSenderResponse) object;
                                //Toast.makeText(getApplicationContext(), "ni", Toast.LENGTH_LONG).show();

                                if (response.getIsBiomatricRequired()) {
                                    showBottomSheetDialog();
                                } else if(response.isSenderRegOTPRequired()){

                                    // Toast.makeText(getApplicationContext(), "errorMsg", Toast.LENGTH_LONG).show();

                                    otpReff = response.getReffId()!=null?response.getReffId():"";
                                    createContainer.setVisibility(View.VISIBLE);
                                    rbcreate.setChecked(true);
                                    rblogin.setChecked(false);
                                }
                            }

                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onError(String errorMsg) {
                                Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                                faddBeneficiary.setVisibility(View.GONE);
                            }
                        });

                    } else {
                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                                getResources().getString(R.string.network_error_message), 2);
                    }
                }
            }
            else {
                if (validationForm("create") == 0) {
                    llSubmit.setEnabled(false);
                    UtilMethods.INSTANCE.CreateSender(this,
                            senderLoginNumber.getText().toString().trim(), senderName.getText().toString()+" "+senderLame.getText().toString(),
                            createpin.getText().toString(), address.getText().toString(), dob.getText().toString(),otp.getText().toString(),otpReff
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    // IsSenderLogin(senderLoginNumber.getText().toString().trim());
//                                    LoginSenderResponse response = (LoginSenderResponse) object;
//                                    otpVerification(senderLoginNumber.getText().toString().trim(),
//                                            senderName.getText().toString(), response.getMessage());
//                                    llSubmit.setEnabled(true);
                                    createContainer.setVisibility(View.GONE);
                                    createpin.setText("");
                                    senderName.setText("");
                                    senderLame.setText("");
                                    address.setText("");
                                    otp.setText("");
                                    dob.setText("");
                                    otpReff=("");
                                    rbcreate.setChecked(false);
                                    rblogin.setChecked(true);
                                    LoginSenderResponse response = (LoginSenderResponse) object;
                                    UtilMethods.INSTANCE.dialogOk(DMRActivity.this, getResources().getString(R.string.successful_title),
                                            response.getMessage(), 2);
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    llSubmit.setEnabled(true);
                                    UtilMethods.INSTANCE.dialogOk(DMRActivity.this, getResources().getString(R.string.failed),
                                            errorMsg, 2);
                                }
                            });
                }
            }
        }

        if (v == beneficiaryDetail) {
            Intent beneIntent = new Intent(this, BeneficiaryListScreen.class);
            startActivity(beneIntent);
        }
        if (v == addbene) {
            Intent beneIntent = new Intent(this, AddBeneficiaryScreen.class);
            startActivity(beneIntent);
        }
        if (v == tvdmrreport) {
            Intent beneIntent = new Intent(this, DMRReportScreen.class);
            startActivity(beneIntent);
        }
        if (v == dob) {
            DatePickerDialog pd = new DatePickerDialog(this, dobDateDialog, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            pd.getDatePicker().setMaxDate(System.currentTimeMillis());
            pd.show();
            pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
            pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);



        }

        if (v == tvLogout) {
            UtilMethods.INSTANCE.setSenderNumber(this, "");
            UtilMethods.INSTANCE.setSenderInfo(this, "", "", false, null);
            UtilMethods.INSTANCE.setBeneficiaryList(this, "");
            Toast.makeText(getApplicationContext(), "Sender Logout !!", Toast.LENGTH_LONG).show();

            loginContainer.setVisibility(View.VISIBLE);
            formContainer.setVisibility(View.GONE);
        }

        if (v == currentLogoutContainer) {
            UtilMethods.INSTANCE.setSenderNumber(this, "");
            UtilMethods.INSTANCE.setSenderInfo(this, "", "", false, null);
            UtilMethods.INSTANCE.setBeneficiaryList(this, "");
            Toast.makeText(getApplicationContext(), "Sender Logout !!", Toast.LENGTH_LONG).show();

            loginContainer.setVisibility(View.VISIBLE);
            formContainer.setVisibility(View.GONE);
        }


    }
//}



    public int validationForm(String type) {
        int flag = 0;

        if (type.equalsIgnoreCase("create")) {
            if (senderName.getText() != null && senderName.getText().toString().trim().length() > 0) {
            } else {
                senderName.setError(getResources().getString(R.string.name_error));
                senderName.requestFocus();
                flag++;
            }
            if (createpin.getText() != null && createpin.getText().toString().trim().length() > 0) {
            } else {
                createpin.setError(getResources().getString(R.string.pincode_error));
                createpin.requestFocus();
                flag++;
            }
            if (address.getText() != null && address.getText().toString().trim().length() > 0) {
            } else {
                address.setError(getResources().getString(R.string.address_error));
                address.requestFocus();
                flag++;
            }
            if (dob.getText() != null && dob.getText().toString()!="Enter Date of birth" && dob.getText().toString().contains("/")) {
            } else {
                dob.setError("Please select date of birth");
                dob.requestFocus();
                flag++;
            }if (otp.getText().toString() != null && otp.getText().toString().trim().length() == 4) {
            } else {
                otp.setError(getResources().getString(R.string.otp_warn));
                otp.requestFocus();
                flag++;
            }
        }

        if (senderLoginNumber.getText() != null && senderLoginNumber.getText().toString().trim().length() > 0) {
        } else {
            senderLoginNumber.setError(getResources().getString(R.string.mobilenumber_error));
            senderLoginNumber.requestFocus();
            flag++;
        }

        return flag;
    }


    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("senderLogin")) {
            senderLogin(activityFragmentMessage.getMessage());
        } else if (activityFragmentMessage.getFrom().equalsIgnoreCase("createSender")) {
            String[] data = activityFragmentMessage.getMessage().split(",");
            otpVerification(data[0], data[1], "");
        } else if (activityFragmentMessage.getFrom().equalsIgnoreCase("verifySender")) {
            senderLogin(activityFragmentMessage.getMessage());
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void IsSenderLogin(String senderNumber) {


        if (senderNumber != null && senderNumber.length() > 0) {
            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.GetSender(this, senderNumber, mProgressDialog, new UtilMethods.ApiCallBackDMRMethod() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onSucess(Object object) {
                        LoginSenderResponse response = (LoginSenderResponse) object;
                        currentMobile.setText("Sender Mobile : " + response.getData().getMobile());
                        currentName.setText("Sender Name : " + response.getData().getName());
                        loginContainer.setVisibility(View.GONE);
                        formContainer.setVisibility(View.VISIBLE);
                        faddBeneficiary.setVisibility(View.GONE);
                        limitUsed.setText(getResources().getString(R.string.rupiya) + response.getData().getUsedLimit());
                        remaining.setText(response.getData().getAvailableLimit());
                    }


                    @Override
                    public void onAuthRequired(Object object) {

                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });

            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }

        } else {

        }
    }

    public void senderLogin(String senderMobile) {
        SharedPreferences prefs = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.senderInfoPref, null);

        Gson gson = new Gson();
        senderTableInfo = gson.fromJson(response, TABLE.class);

        kycText.setText(senderTableInfo.getKYC());
        name.setText(senderTableInfo.getNAME());
        currency.setText(senderTableInfo.getCURRENCY());

        radio11.setChecked(true);


    }


    public void otpVerification(final String senderNumber, final String ssenderName, final String otpref) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.otp_layout, null);

        final TextInputLayout otpTextLayout = view.findViewById(R.id.otpTextLayout);
        final EditText otp = view.findViewById(R.id.otp);
        final AppCompatButton okButton = view.findViewById(R.id.okButton);
        final AppCompatButton cancelButton = view.findViewById(R.id.cancelButton);

        final Dialog dialog = new Dialog(this);

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
                if (otp.getText() != null && otp.getText().length() > 0) {
                    otpTextLayout.setErrorEnabled(false);

                    UtilMethods.INSTANCE.VerifySender(DMRActivity.this, senderNumber, ssenderName,
                            otp.getText().toString().trim(), otpref, new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    dialog.dismiss();
                                    IsSenderLogin(senderNumber);
                                    senderLoginNumber.setText("");
                                    Toast.makeText(getApplicationContext(), "Sender Created Successfully !!", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    dialog.dismiss();
                                    UtilMethods.INSTANCE.dialogOk(DMRActivity.this, getResources().getString(R.string.attention_error_title),
                                            errorMsg, 2);
                                }
                            });
                } else {
                    otp.setError("Please enter a valid OTP !!");
                    otp.requestFocus();
                }
            }
        });
        dialog.show();
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bio_metric_option_bottom_sheet);

        // Get views inside BottomSheet
        TextView tvTitle = bottomSheetDialog.findViewById(R.id.tvTitle);
        Button btnClose = bottomSheetDialog.findViewById(R.id.btnClose);
        CardView fingerCard = bottomSheetDialog.findViewById(R.id.finger_card);
        CardView faceCard = bottomSheetDialog.findViewById(R.id.face_card);

        // Handle button click
        if (btnClose != null) {
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });
        }if (fingerCard != null) {
            fingerCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent transactionIntent = new Intent(DMRActivity.this, BioMetricActivity.class);
                    transactionIntent.putExtra("from", "biomertric");
                    transactionIntent.putExtra("sender", "" + senderLoginNumber.getText().toString());
                    AuthenticateSenderLauncher.launch(transactionIntent);
                    bottomSheetDialog.dismiss();
                }
            });
        }if (faceCard != null) {
            faceCard.setOnClickListener(v -> {

                Intent transactionIntent = new Intent(DMRActivity.this, FaceDetectionActivity.class);
                transactionIntent.putExtra("from", "faceAuth");
                transactionIntent.putExtra("sender", "" + senderLoginNumber.getText().toString());
                AuthenticateSenderLauncher.launch(transactionIntent);
                bottomSheetDialog.dismiss();
            });
        }

        bottomSheetDialog.show();
    }

}