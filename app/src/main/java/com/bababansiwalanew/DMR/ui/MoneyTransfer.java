package com.bababansiwalanew.DMR.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bababansiwalanew.Util.SendMoneyResponse;
import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.BankListObject;
import com.bababansiwalanew.Util.dto.BankListResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;




public class MoneyTransfer extends AppCompatActivity implements View.OnClickListener {

    EditText transferAmount,pin,etOtp;
    TextView submitButton, closeButton, SendOTPButton;
    Toolbar toolbar;
    String name, otp, otpReff,bank, BankId,bankAccount, recipientId,_BeneficiaryMobile, channel, amount, senderNumber,ifsc,   pinPass="" ;

    RelativeLayout neftContainer, impsContainer;
    RadioButton neftRadio, impsRadio;

    private ProgressDialog mProgressDialog;
    ArrayList<BankListObject> operator = new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    boolean NEFTFlag = false;
    boolean IMPSFlag = false;
    int selectedFlag = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_transfer);

        name = getIntent().getExtras().getString("name");
        bank = getIntent().getExtras().getString("bank");
        bankAccount = getIntent().getExtras().getString("bankAccount");
        recipientId = getIntent().getExtras().getString("recipientId");
        _BeneficiaryMobile = getIntent().getExtras().getString("_BeneficiaryMobile");
        ifsc = getIntent().getExtras().getString("ifsc");
        BankId = getIntent().getExtras().getString("_BankId")==null?"":getIntent().getExtras().getString("_BankId");

        mProgressDialog = new ProgressDialog(MoneyTransfer.this);
        transferType();
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        senderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Money Transfer");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        ///////////////////////////////////////////////////////////////
        neftContainer = findViewById(R.id.neftContainer);
        impsContainer = findViewById(R.id.impsContainer);
        neftRadio = findViewById(R.id.neftRadio);
        impsRadio = findViewById(R.id.impsRadio);
        etOtp = findViewById(R.id.otp);

        transferAmount = findViewById(R.id.transferAmount);
        pin = findViewById(R.id.pin);

        submitButton = findViewById(R.id.submitButton);
        SendOTPButton = findViewById(R.id.SendOTPButton);
        closeButton = findViewById(R.id.closeButton);
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        pinPass = myPreferences.getString(ApplicationConstant.INSTANCE.PinPasscode, null);
        // Log.v("pinPass", pinPass);
        neftRadio.setOnClickListener(this);
        impsRadio.setOnClickListener(this);
        neftContainer.setOnClickListener(this);
        impsContainer.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        SendOTPButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == neftContainer || v == neftRadio) {
            neftRadio.setChecked(true);
            impsRadio.setChecked(false);

            selectedFlag = 1;
            channel = "1";
        }

        if (v == impsContainer || v == impsRadio) {
            neftRadio.setChecked(false);
            impsRadio.setChecked(true);

            selectedFlag = 2;
            channel = "2";
        }

        if (v == closeButton) {
            finish();
        }

        if (v == submitButton) {
            if (validationForm() == 0) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.SendMoney(this,_BeneficiaryMobile, senderNumber, bankAccount,
                            amount, recipientId, channel,
                            name, bank,"test",amount,"0",
                            pin.getText().toString() ,ifsc,BankId,otpReff,etOtp.getText().toString(),mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }
            }
        }
        if (v == SendOTPButton) {
            if (validationForm() == 0) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.SendMoneyOTP(this,_BeneficiaryMobile, senderNumber, bankAccount,
                            amount, recipientId, channel,
                            name, bank,"test",amount,"0",
                            pin.getText().toString() ,ifsc,BankId,mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    SendMoneyResponse response = (SendMoneyResponse) object;
                                    otpReff  = response.getOtpReff();
                                    SendOTPButton.setVisibility(View.GONE);
                                    // pin.setVisibility(View.GONE);
                                    etOtp.setVisibility(View.VISIBLE);
                                    submitButton.setVisibility(View.VISIBLE);
                                    UtilMethods.INSTANCE.dialogOk(MoneyTransfer.this, getResources().getString(R.string.successful_title),
                                            response.getMessage().toString(), 0);


                                }

                                @Override
                                public void onError(String errorMsg) {
                                    SendOTPButton.setVisibility(View.VISIBLE);
                                    etOtp.setVisibility(View.GONE);
                                    pin.setVisibility(View.VISIBLE);
                                    submitButton.setVisibility(View.GONE);
                                    UtilMethods.INSTANCE.dialogOk(MoneyTransfer.this,
                                            getResources().getString(R.string.attention_error_title),
                                            errorMsg, 4);
                                }
                            });

                } else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }
            }
        }

    }

    public int validationForm() {
        int flag = 0;

        if (selectedFlag != 0 && channel != null && channel.length() > 0) {

        } else {
            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title), "Please specify transfer type", 2);
            flag++;
        }

        if (transferAmount.getText() != null && transferAmount.getText().toString().trim().length() > 0) {
            amount = transferAmount.getText().toString().trim();
        } else {
            transferAmount.setError(getResources().getString(R.string.amount_transfer_error));
            transferAmount.requestFocus();
            flag++;
        }
        if (pin.getText() != null && pin.getText().toString().trim().length() > 0) {
            pinPass = pin.getText().toString().trim();
        } else {
            pin.setError( ("Please Enter pin "));
            pin.requestFocus();
            flag++;
        }

        return flag;
    }

    public void transferType() {
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);

        Gson gson = new Gson();
        operatorList = gson.fromJson(response, BankListResponse.class);
        operator = operatorList.getBanks();

        for (BankListObject object : operator) {
            if (object.getBankName().equalsIgnoreCase(bank)) {
                if (object.getNEFT().equalsIgnoreCase("Yes")) {
                    NEFTFlag = true;
                }

                if (object.getIMPS().equalsIgnoreCase("Yes")) {
                    IMPSFlag = true;
                }
            } else {
                NEFTFlag = false;
                IMPSFlag = false;
            }
        }
    }

    public void finishMethod() {
        finish();
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("transferDone")) {

            ActivityActivityMessage activityActivityMessage =
                    new ActivityActivityMessage("transferDoneDialog");
            GlobalBus.getBus().post(activityActivityMessage);

            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
}

/*



public class MoneyTransfer extends AppCompatActivity implements View.OnClickListener {

    EditText transferAmount,pin;
    TextView submitButton, closeButton;
    Toolbar toolbar;
    String name, bank, bankAccount, recipientId, channel, amount, senderNumber,ifsc,   pinPass="" ;

    RelativeLayout neftContainer, impsContainer;
    RadioButton neftRadio, impsRadio;

    private ProgressDialog mProgressDialog;
    ArrayList<BankListObject> operator = new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    boolean NEFTFlag = false;
    boolean IMPSFlag = false;
    int selectedFlag = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_transfer);

        name = getIntent().getExtras().getString("name");
        bank = getIntent().getExtras().getString("bank");
        bankAccount = getIntent().getExtras().getString("bankAccount");
        recipientId = getIntent().getExtras().getString("recipientId");
        ifsc = getIntent().getExtras().getString("ifsc");

        mProgressDialog = new ProgressDialog(MoneyTransfer.this);
        transferType();
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        senderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Money Transfer");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        ///////////////////////////////////////////////////////////////
        neftContainer = (RelativeLayout) findViewById(R.id.neftContainer);
        impsContainer = (RelativeLayout) findViewById(R.id.impsContainer);
        neftRadio = (RadioButton) findViewById(R.id.neftRadio);
        impsRadio = (RadioButton) findViewById(R.id.impsRadio);

        transferAmount = (EditText) findViewById(R.id.transferAmount);
        pin = (EditText) findViewById(R.id.pin);

        submitButton = (TextView) findViewById(R.id.submitButton);
        closeButton = (TextView) findViewById(R.id.closeButton);
        SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
          pinPass = myPreferences.getString(ApplicationConstant.INSTANCE.PinPasscode, null);
        Log.v("pinPass",""+pinPass);
        neftRadio.setOnClickListener(this);
        impsRadio.setOnClickListener(this);
        neftContainer.setOnClickListener(this);
        impsContainer.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == neftContainer || v == neftRadio) {
            neftRadio.setChecked(true);
            impsRadio.setChecked(false);

            selectedFlag = 1;
            channel = "1";
        }

        if (v == impsContainer || v == impsRadio) {
            neftRadio.setChecked(false);
            impsRadio.setChecked(true);

            selectedFlag = 2;
            channel = "2";
        }

        if (v == closeButton) {
            finish();
        }

        if (v == submitButton) {
            if (validationForm() == 0) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.SendMoney(this, senderNumber, bankAccount,
                            amount, recipientId, channel,
                            name, bank,"test",amount,"0",
                            pin.getText().toString() ,ifsc,mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }
            }
        }

    }

    public int validationForm() {
        int flag = 0;

        if (selectedFlag != 0 && channel != null && channel.length() > 0) {

        } else {
            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title), "Please specify transfer type", 2);
            flag++;
        }

        if (transferAmount.getText() != null && transferAmount.getText().toString().trim().length() > 0) {
            amount = transferAmount.getText().toString().trim();
        } else {
            transferAmount.setError(getResources().getString(R.string.amount_transfer_error));
            transferAmount.requestFocus();
            flag++;
        }
        if (pin.getText() != null && pin.getText().toString().trim().length() > 0) {
            pinPass = pin.getText().toString().trim();
        } else {
            pin.setError( ("Please Enter pin "));
            pin.requestFocus();
            flag++;
        }

        return flag;
    }

    public void transferType() {
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);

        Gson gson = new Gson();
        operatorList = gson.fromJson(response, BankListResponse.class);
        operator = operatorList.getBanks();

        for (BankListObject object : operator) {
            if (object.getBankName().equalsIgnoreCase(bank)) {
                if (object.getNEFT().equalsIgnoreCase("Yes")) {
                    NEFTFlag = true;
                }

                if (object.getIMPS().equalsIgnoreCase("Yes")) {
                    IMPSFlag = true;
                }
            } else {
                NEFTFlag = false;
                IMPSFlag = false;
            }
        }
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("transferDone")) {

            ActivityActivityMessage activityActivityMessage =
                    new ActivityActivityMessage("transferDoneDialog");
            GlobalBus.getBus().post(activityActivityMessage);

            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
}
*/
