package com.bababansiwalanew.DMR.ui;

import static com.bababansiwalanew.Util.XMLParser.parseErrorResponse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class BioMetricActivity extends AppCompatActivity {
    boolean isActivityPause;
    EditText aadhar;
    ProgressBar progressBar;
    TextView capturePercentage;
    TextView statusMessage;
    ImageView fingerprintImage;
    String pidData = "";
    String senderNumber = "";
    CustomLoader loader;

    TextInputLayout otpTextLayout;
    TextInputLayout aadharTextLayout;
    EditText otp;
    AppCompatButton captureButton;// = findViewById(R.id.okButton);
    AppCompatButton okButton;// = findViewById(R.id.okButton);
    AppCompatButton close;// = findViewById(R.id.okButton);
    AppCompatButton submitDataButton;// = findViewById(R.id.submitDataButton);
    AppCompatButton cancelButton;// = findViewById(R.id.cancelButton);
    RelativeLayout basicView;//= findViewById(R.id.basicView);
    RelativeLayout bioMetricView;// = findViewById(R.id.bioMetricView);
    RelativeLayout otpRelativeLayout;

    private boolean isDialogShowBackground;
    private String dialogMsg;
    private boolean isSucessDialog;
    private final ActivityResultLauncher<Intent> fingerprintLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // Extract the PID data
                            pidData = result.getData().getStringExtra("PID_DATA");
                            Log.d("FingerprintData", pidData);
                            if (pidData != null && !pidData.isEmpty()) {

                                try {
                                    // Call the method and get the result
                                    Map<String, String> result1 = parseErrorResponse((pidData));

                                    // Print the results
                                    String respCode= result1.get("errCode");
                                    String respMessage= result1.get("errInfo");

                                    assert respCode != null;
                                    if (respCode.equals("0")){
                                        simulateFingerprintProgress();
                                        Toast.makeText(getApplicationContext(), ""+respMessage, Toast.LENGTH_LONG).show();

                                    }else{
                                        Toast.makeText(getApplicationContext(), ""+respMessage, Toast.LENGTH_LONG).show();

                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Invalid fingerprint scan data.", Toast.LENGTH_LONG).show();

                                    e.printStackTrace();
                                }


                            } else {
                                Log.e("FingerprintValidation", "No PID data received.");
                                Toast.makeText(getApplicationContext(), "No fingerprint scan data.", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Fingerprint capture failed or cancelled.", Toast.LENGTH_LONG).show();
                            Log.e("FingerprintError", "Fingerprint capture failed or cancelled.");
                        }
                    });


    public boolean isValidFingerprintXml(String pidData) {
        try {
            // Parse the PID data as XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(pidData.getBytes());
            Document document = builder.parse(inputStream);

            // Check if the root element is "PidData"
            Element rootElement = document.getDocumentElement();
            if (!"PidData".equals(rootElement.getTagName())) {
                return false;
            }

            // Additional validation: Check for specific required fields or tags
            NodeList childNodes = rootElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = node.getNodeName();
                    // Example: Check if a specific tag exists
                    if ("DeviceInfo".equals(nodeName) || "Resp".equals(nodeName)) {
                        return true; // Found mandatory tags
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Invalid XML or parsing error
        }
        return false; // No mandatory tags found
    }


    private void simulateFingerprintProgress() {
        new Thread(() -> {
            int progress = 0;

            while (progress <= 100) {
                int finalProgress = progress;

                runOnUiThread(() -> {
                    // Update ProgressBar and Percentage
                    progressBar.setProgress(finalProgress);
                    capturePercentage.setText(finalProgress + "%");

                    // Change fingerprint image color based on progress
                    if (finalProgress <= 30) {
                        fingerprintImage.setColorFilter(Color.RED); // Low progress - red
                    } else if (finalProgress <= 70) {
                        fingerprintImage.setColorFilter(Color.YELLOW); // Medium progress - yellow
                    } else {
                        fingerprintImage.setColorFilter(Color.BLUE); // High progress - blue
                    }

                    // Update status message
                    if (finalProgress == 100) {
                        submitDataButton.setVisibility(View.VISIBLE);
                        captureButton.setVisibility(View.VISIBLE);
                        statusMessage.setText("Fingerprint capture complete!");
                    } else {
                        statusMessage.setText("Capturing fingerprint...");
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
        setContentView(R.layout.activity_bio_metric);
        isActivityPause = false;
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
      try {
          senderNumber = getIntent().getStringExtra("sender").toString();
      } catch (Exception e){}
        otpTextLayout = findViewById(R.id.otpTextLayout);
        aadharTextLayout = findViewById(R.id.aadharTextLayout);
        otp = findViewById(R.id.otp);
        aadhar = findViewById(R.id.aadhar);
        okButton = findViewById(R.id.okButton);
        submitDataButton = findViewById(R.id.submitDataButton);
        cancelButton = findViewById(R.id.cancelButton);
        basicView = findViewById(R.id.basicView);
        bioMetricView = findViewById(R.id.bioMetricView);
        otpRelativeLayout = findViewById(R.id.otpRelativeLayout);
        CheckBox consent = findViewById(R.id.consent);
        captureButton = findViewById(R.id.captureButton);
        close = findViewById(R.id.close);
        progressBar = findViewById(R.id.progressBar);
        capturePercentage = findViewById(R.id.capturePercentage);
        statusMessage = findViewById(R.id.statusMessage);
        fingerprintImage = findViewById(R.id.fingerprintImage);
        aadhar = findViewById(R.id.aadhar);
        consent = findViewById(R.id.consent);


        cancelButton.setOnClickListener(v -> finish());
        close.setOnClickListener(v -> finish());
        captureButton.setOnClickListener(v -> {
            captureFingerprintData();


        });
        submitDataButton.setOnClickListener(v -> {
            if (!pidData.isEmpty()) {
                ValidateBiometric("BioMetric Validation");

            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();

            }
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

    public void captureFingerprintData() {
        Intent intent = new Intent("in.gov.uidai.rdservice.fp.CAPTURE");
        intent.setPackage("com.mantra.rdservice"); // Replace with the correct RD Service package name
        intent.putExtra("PID_OPTIONS", "<?xml version=\"1.0\"?> <PidOptions ver=\"1.0\"> <Opts fCount=\"1\" fType=\"2\"" +
                " iCount=\"0\" pCount=\"0\" pgCount=\"2\" format=\"0\"   pidVer=\"2.0\" timeout=\"10000\" pTimeout=\"20000\" " +
                "wadh=\"18f4CEiXeXcfGXvgWA/blxD+w2pw7hfQPY45JMytkPw=\" posh=\"UNKNOWN\" env=\"P\" /> <CustOpts><Param name=\"mantrakey\"" +
                " value=\"\" /></CustOpts> </PidOptions>");
        fingerprintLauncher.launch(intent);
//        Intent intent = new Intent("in.gov.uidai.rdservice.fp.CAPTURE");
//        intent.setPackage("com.mantra.rdservice"); // Replace with the correct RD Service package name
//        intent.putExtra("PID_OPTIONS", "<PidOptions ver=\"1.0\"><Opts fCount=\"1\" fType=\"0\" iCount=\"0\" iType=\"0\" pCount=\"0\" pType=\"0\" format=\"0\" pidVer=\"2.0\" timeout=\"20000\" otp=\"\" env=\"P\" wadh=\"\"/></PidOptions>");
//        fingerprintLauncher.launch(intent);

    }

    public void ValidateBiometric(String title) {
        try {
            if (!isActivityPause) {
                loader.show();
            }
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
            String userID = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);
            String deviceId = UtilMethods.INSTANCE.getDeviceId(BioMetricActivity.this);
            String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            retrofit2.Call<ValidateBioMetricResponse> call = git.ValidateBioMetric(new ValidateBioMetricRequest(Integer.parseInt(userID),
                    appInfo, senderNumber, aadhar.getText().toString(),
                    pidData, "25.2555", "51.1455","2"
            ));

            call.enqueue(new retrofit2.Callback<ValidateBioMetricResponse>() {
                @Override
                public void onResponse(retrofit2.Call<ValidateBioMetricResponse> call, final retrofit2.Response<ValidateBioMetricResponse> response) {


                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }


                        ////
                        if (response.body() != null) {
                            /*if (response.body().getIsBiomatricRequired() ) {
                                Intent clickIntent = new Intent();
                                clickIntent.putExtra("reffId", response.body().getReffId().toString()!=null?response.body().getReffId().toString():"");
                                clickIntent.putExtra("flag", "BiomatricRequired");
                                setResult(1, clickIntent);
                                finish();
                            }else*/
                            if (response.body().getIsSenderRegOTPRequired()) {
                                Intent clickIntent = new Intent();
                                clickIntent.putExtra("reffId", response.body().getReffId().toString());
                                clickIntent.putExtra("flag", "SenderRegOTPRequired");
                                setResult(100, clickIntent);
                                finish();


                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, title, response.body().getMessage() + "", 100);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = response.body().getMessage() + "";
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
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
                            UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
                                    (R.string.attention_error_title), e.getMessage() + "", 2);
                        } else {
                            isDialogShowBackground = true;
                            dialogMsg = e.getMessage() + "";
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
                                    UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
                                            (R.string.attention_error_title), getString(R.string.err_msg_network), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = getString(R.string.err_msg_network);
                                    isSucessDialog = false;
                                }
                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
                                            (R.string.attention_error_title), t.getMessage(), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = t.getMessage();
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
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
                            UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
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
                UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
                        (R.string.attention_error_title), e.getMessage() + "", 2);
            } else {
                isDialogShowBackground = true;
                dialogMsg = e.getMessage() + "";
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
                UtilMethods.INSTANCE.Successful(BioMetricActivity.this, dialogMsg);
            } else {
                UtilMethods.INSTANCE.dialogOk(BioMetricActivity.this, getResources().getString
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