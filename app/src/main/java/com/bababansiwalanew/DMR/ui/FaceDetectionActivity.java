package com.bababansiwalanew.DMR.ui;

import static com.bababansiwalanew.Util.XMLParser.parseErrorResponse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputLayout;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApiClient;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CustomLoader;
import com.bababansiwalanew.Util.EndPointInterface;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.Map;
import java.util.Random;

import okhttp3.MediaType;

public class FaceDetectionActivity extends AppCompatActivity {
    boolean isActivityPause;
    EditText aadhar;
    ProgressBar progressBar;
    TextView capturePercentage;
    TextView statusMessage;
    ImageView faceImage;
    String pidData = "";
    String senderNumber = "";
    CustomLoader loader;
    private static final MediaType XML = MediaType.get("application/xml; charset=utf-8");

    private static final String VERSION = "2.5";
    private static final String RESIDENT_AUTHENTICATION_TYPE = "P";
    private static final String RESIDENT_CONSENT = "Y";
    private static final String LOCAL_LANGUAGE_IR = "N";
    private static final String DECRYPTION = "N";
    private static final String PRINT_FORMAT = "N";

    private static final String CAPTURE_INTENT = "in.gov.uidai.rdservice.face.CAPTURE";
     TextInputLayout aadharTextLayout;
     AppCompatButton captureButton;
    AppCompatButton okButton;
    AppCompatButton close;
    AppCompatButton submitDataButton;
    AppCompatButton cancelButton;
    RelativeLayout basicView;RelativeLayout bioMetricView;

    private boolean isDialogShowBackground;
    private String dialogMsg;
    private boolean isSucessDialog;
    private final ActivityResultLauncher<Intent> faceLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            Bundle bundle = result.getData().getExtras();
                            pidData  =   bundle.getString("response");
                           // pidData = result.getData().getStringExtra("PID_DATA");
                            Log.d("FingerprintData", pidData);
                            Log.e("FingerprintData", pidData);
                            if (pidData != null && !pidData.isEmpty()) {
                                try {
                                    Map<String, String> result1 = parseErrorResponse((pidData));
                                    String respCode= result1.get("errCode");
                                    String respMessage= result1.get("errInfo");
                                    assert respCode != null;
                                    if (respCode.equals("0")){
                                        simulateFaceProgress();
                                        Toast.makeText(getApplicationContext(), ""+respMessage, Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), ""+respMessage, Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Unable to capture your face.", Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            } else {
                                 Toast.makeText(getApplicationContext(), "Unable to capture your face", Toast.LENGTH_LONG).show();
                            }
                             }
                    });

    private void simulateFaceProgress() {
        new Thread(() -> {
            int progress = 0;

            while (progress <= 100) {
                int finalProgress = progress;

                runOnUiThread(() -> {
                    // Update ProgressBar and Percentage
                    progressBar.setProgress(finalProgress);
                    capturePercentage.setText(finalProgress + "%");
                    // Update status message
                    if (finalProgress == 100) {
                        submitDataButton.setVisibility(View.VISIBLE);
                        captureButton.setVisibility(View.VISIBLE);
                        statusMessage.setText("face capture complete!");
                    } else {
                        statusMessage.setText("Capturing face...");
                    }
                });

                try {
                    Thread.sleep(100); // Simulate time delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                progress += 10;
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_face_detection);
        isActivityPause = false;
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        try {
            senderNumber = getIntent().getStringExtra("sender");
        } catch (Exception e){}
         aadharTextLayout = findViewById(R.id.aadharTextLayout);
         aadhar = findViewById(R.id.aadhar);
        okButton = findViewById(R.id.okButton);
        submitDataButton = findViewById(R.id.submitDataButton);
        cancelButton = findViewById(R.id.cancelButton);
        basicView = findViewById(R.id.basicView);
        bioMetricView = findViewById(R.id.bioMetricView);
         CheckBox consent = findViewById(R.id.consent);
        captureButton = findViewById(R.id.captureButton);
        close = findViewById(R.id.close);
        progressBar = findViewById(R.id.progressBar);
        capturePercentage = findViewById(R.id.capturePercentage);
        statusMessage = findViewById(R.id.statusMessage);
        faceImage = findViewById(R.id.faceImage);
        aadhar = findViewById(R.id.aadhar);
        consent = findViewById(R.id.consent);


        cancelButton.setOnClickListener(v -> finish());
        close.setOnClickListener(v -> finish());
        captureButton.setOnClickListener(v -> {
            capturefaceData();


        });
        submitDataButton.setOnClickListener(v -> {
                ValidateBiometric(pidData ,"BioMetric Validation");
        });

        EditText finalAadhar = aadhar;
        CheckBox finalConsent = consent;
        okButton.setOnClickListener(v -> {
            if (finalAadhar.getText() != null && finalAadhar.getText().length() == 12) {
                aadharTextLayout.setErrorEnabled(false);
                if (finalConsent.isChecked()) {
                    basicView.setVisibility(View.GONE);
                    bioMetricView.setVisibility(View.VISIBLE);


                } else {
                    finalConsent.setError("Please agree to our terms and policies");
                    Toast.makeText(getApplicationContext(), "Please check the consent check box !", Toast.LENGTH_LONG).show();


                }
            } else {
                finalAadhar.setError("Please enter a valid Aadhar Number !!");
                finalAadhar.requestFocus();
            }
        });

    }

    public void capturefaceData() {

        Intent intent = new Intent(CAPTURE_INTENT);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("request",createPidOptions(getRandomNumber()));
        faceLauncher.launch(intent);
    }

    private String getRandomNumber() {
        int start = 10000000;
        int end = 99999999;
        Random random = new Random(System.nanoTime());
        int number = random.nextInt(end - start + 1) + start;
        return String.valueOf(number);
    }

    private String createPidOptions(String txnId) {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<PidOptions ver=\"1.0\" env=\"" + "P" + "\">\n" +
                "    <Opts fCount=\"0\" fType=\"1\" iCount=\"1\" iType=\"1\" pCount=\"0\" pType=\"\" format=\"\" pidVer=\"2.0\" " +
                "timeout=\"\" otp=\"\" wadh=\"" + "mtDVz0PM/HvMAWSkCkjcxW+KhNWk2nfbUhfZwLl2faw=" + "\"" +
                " posh=\"\" />\n" +
                "    <CustOpts>\n" +
                "        <Param name=\"txnId\" value=\"" + txnId + "\"/>\n" +
                "        <Param name=\"purpose\" value=\"" + "auth" + "\"/>\n" +
                "        <Param name=\"language\" value=\"" + "en" + "\"/>\n" +
                "    </CustOpts>\n" +
                "</PidOptions>";
    }

    public void ValidateBiometric(String pidData,String title) {


        try {
            if (!isActivityPause) {
                loader.show();
            }
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
            String userID = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);
            String deviceId = UtilMethods.INSTANCE.getDeviceId(FaceDetectionActivity.this);
            String appInfo = UtilMethods.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            retrofit2.Call<ValidateBioMetricResponse> call = git.ValidateBioMetric(new ValidateBioMetricRequest(Integer.parseInt(userID),
                    appInfo, senderNumber, aadhar.getText().toString(),
                    pidData, "25.2555", "51.1455","4"
            ));

            call.enqueue(new retrofit2.Callback<ValidateBioMetricResponse>() {
                @Override
                public void onResponse(retrofit2.Call<ValidateBioMetricResponse> call, final retrofit2.Response<ValidateBioMetricResponse> response) {


                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }

                        if (response.body() != null) {

                            if (response.body().getIsSenderRegOTPRequired()) {
                                Intent clickIntent = new Intent();
                                clickIntent.putExtra("reffId", response.body().getReffId().toString());
                                clickIntent.putExtra("flag", "SenderRegOTPRequired");
                                setResult(100, clickIntent);
                                finish();


                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, title, response.body().getMessage() + "", 1000);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = response.body().getMessage() + "";
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                        (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                            } else {
                                isDialogShowBackground = true;
                                dialogMsg = getString(R.string.some_thing_error);
                                isSucessDialog = false;
                            }
                        }

                    } catch (Exception e) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }

                        if (!isActivityPause) {
                            UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                    (R.string.attention_error_title), e.getMessage(), 2);
                        } else {
                            isDialogShowBackground = true;
                            dialogMsg = e.getMessage();
                            isSucessDialog = false;
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ValidateBioMetricResponse> call, Throwable t) {
                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                            (R.string.attention_error_title), getString(R.string.err_msg_network), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = getString(R.string.err_msg_network);
                                    isSucessDialog = false;
                                }
                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                            (R.string.attention_error_title), t.getMessage(), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = t.getMessage();
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                        (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                            } else {
                                isDialogShowBackground = true;
                                dialogMsg = getString(R.string.some_thing_error);
                                isSucessDialog = false;
                            }
                        }
                    } catch (IllegalStateException ise) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }

                        if (!isActivityPause) {
                            UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                                    (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                        } else {
                            isDialogShowBackground = true;
                            dialogMsg = getString(R.string.some_thing_error);
                            isSucessDialog = false;
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader.isShowing()) {
                loader.dismiss();
            }

            if (!isActivityPause) {
                UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                        (R.string.attention_error_title), e.getMessage(), 2);
            } else {
                isDialogShowBackground = true;
                dialogMsg = e.getMessage();
                isSucessDialog = false;
            }
        }
    }
    @Override
    protected void onResume() {
        isActivityPause = false;
        super.onResume();

        if (isDialogShowBackground) {
            isDialogShowBackground = false;

            if (isSucessDialog) {
                UtilMethods.INSTANCE.Successful(FaceDetectionActivity.this, dialogMsg);
            } else {
                UtilMethods.INSTANCE.dialogOk(FaceDetectionActivity.this, getResources().getString
                        (R.string.attention_error_title), dialogMsg, 2);
            }
        }


    }


    @Override
    protected void onPause() {
        isActivityPause = true;
        super.onPause();
    }

    @Override
    protected void onStart() {
        isActivityPause = false;
        super.onStart();
    }

    @Override
    protected void onStop() {
        isActivityPause = true;
        super.onStop();
    }

    public void finishMethod() {
        finish();
    }

}