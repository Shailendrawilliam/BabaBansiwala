package com.bababansiwalanew.DMR.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bababansiwalanew.DMR.dto.LoginSenderResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.BankDetail;
import com.bababansiwalanew.Util.ui.BankListScreen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.bababansiwalanew.DMR.ui.DMRActivity.remiterid;


    public class AddBeneficiaryScreen extends AppCompatActivity {
        ProgressDialog mProgressDialog;
        EditText beneficiaryName, beneficiaryNumber, bank, accountNumber, ifscCode, ifsc,address,pincode;
        TextView create, accVerify, senderNumber;
        String bankId;
        String bankName;
        String accVerification = "";
        String shortCode;
        String Fullifsc = "";
        String verified="yes";
        ArrayList<BankDetail> bankDetails = new ArrayList<>();

        public void finishMethod() {
            finish();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_beneficiary_screen);
            mProgressDialog = new ProgressDialog(AddBeneficiaryScreen.this);
            getID();
            findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validationAddBeneficiary("") == 0) {
                        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                        String currentSenderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

                        if (UtilMethods.INSTANCE.isNetworkAvialable(AddBeneficiaryScreen.this)) {

                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.show();

                            UtilMethods.INSTANCE.AddBeneficiary(AddBeneficiaryScreen.this, remiterid,currentSenderNumber,
                                    beneficiaryName.getText().toString().trim(),
                                    beneficiaryNumber.getText().toString().trim(), accountNumber.getText().toString().trim(),
                                    ifsc.getText().toString().trim(), verified, bankId,address.getText().toString(),pincode.getText().toString(),
                                    mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
                                        @Override
                                        public void onSucess(Object object) {
                                            UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this,
                                                    getResources().getString(R.string.successful_title),
                                                    "Beneficiary Added !!", 22);

                                        }

                                        @Override
                                        public void onError(String errorMsg) {

                                        }
                                    });

                        } else {
                            UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this, getResources().getString(R.string.network_error_title),
                                    getResources().getString(R.string.network_error_message), 2);
                        }
                    }
                }
            });
            findViewById(R.id.accVerify).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (validationAddBeneficiary("accVerif") == 0) {
                        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                        String currentSenderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

                        if (UtilMethods.INSTANCE.isNetworkAvialable(AddBeneficiaryScreen.this)) {

                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.show();

                            UtilMethods.INSTANCE.VerifyBeneficiary(AddBeneficiaryScreen.this, remiterid,beneficiaryName.getText().toString(), ifscCode.getText().toString().trim() + ifsc.getText().toString().trim(), currentSenderNumber,
                                    accountNumber.getText().toString().trim(), bankId, mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
                                        @Override
                                        public void onSucess(Object object) {
                                            LoginSenderResponse response = (LoginSenderResponse) object;
                                            verified = "Yes";
                                            beneficiaryName.setText(response.getMessage());
                                            UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this, getResources().getString(R.string.successful_title),
                                                    response.getMessage(), 2);

                                        }

                                        @Override
                                        public void onError(String errorMsg) {
                                            UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this, getResources().getString(R.string.attention_error_title),
                                                    errorMsg, 2);
                                            verified = "No";
                                        }
                                    });

                        } else {
                            UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this, getResources().getString(R.string.network_error_title),
                                    getResources().getString(R.string.network_error_message), 2);
                        }
                    }

                }
            });

            findViewById(R.id.bank).setOnClickListener(v -> {
                Intent bankIntent = new Intent(AddBeneficiaryScreen.this, BankListScreen.class);
                startActivityForResult(bankIntent, 4);
            });
        }

        private void getID() {
            beneficiaryName = findViewById(R.id.beneficiaryName);
            beneficiaryNumber = findViewById(R.id.beneficiaryNumber);
            bank = findViewById(R.id.bank);
            accountNumber = findViewById(R.id.accountNumber);
            ifscCode = findViewById(R.id.ifscCode);
            address = findViewById(R.id.address);
            pincode = findViewById(R.id.pincode);
            ifsc = findViewById(R.id.ifsc);

            senderNumber = findViewById(R.id.senderNumber);
            accVerify = findViewById(R.id.accVerify);
            create = findViewById(R.id.create);
            SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String currentSenderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);
            senderNumber.setText(currentSenderNumber);
        }

        public int validationAddBeneficiary(String from) {
            int flag = 0;

            if (!from.equalsIgnoreCase("accVerif")) {

                if (accVerification.equalsIgnoreCase("Yes")||accVerification.equalsIgnoreCase("true")||
                        accVerification.equalsIgnoreCase("1") ) {
                    if (verified != null && (verified.equalsIgnoreCase("1") ||
                            verified.equalsIgnoreCase("Yes")||
                            verified.equalsIgnoreCase("0")||
                            verified.equalsIgnoreCase("No"))) {

                    } else {
                        UtilMethods.INSTANCE.dialogOk(AddBeneficiaryScreen.this, getResources().getString(R.string.attention_error_title),
                                "Please verify account first !!", 2);
                        flag++;
                    }
                }
            }

            if (ifsc.getText() != null && ifsc.getText().toString().trim().length() > 0
                    && !(ifsc.getText().toString().trim().length() < 7)) {
            } else {
                ifsc.setError(getResources().getString(R.string.bene_ifsc_error));
                ifsc.requestFocus();
                flag++;
            }

            if (accountNumber.getText() != null && accountNumber.getText().toString().trim().length() > 0
            ) {
            } else {
                accountNumber.setError(getResources().getString(R.string.bene_acc_error));
                accountNumber.requestFocus();
                flag++;
            }

            if (bank.getText() != null && bank.getText().toString().trim().length() > 0) {
            } else {
                bank.setError(getResources().getString(R.string.bene_bank_error));
                bank.requestFocus();
                flag++;
            }

            if (beneficiaryName.getText() != null && beneficiaryName.getText().toString().trim().length() > 0) {
            } else {
                beneficiaryName.setError(getResources().getString(R.string.bene_name_error));
                beneficiaryName.requestFocus();
                flag++;
            }

            if (beneficiaryNumber.getText() != null && beneficiaryNumber.getText().toString().trim().length() > 0
            ) {
            } else {
                beneficiaryNumber.setError(getResources().getString(R.string.mobilenumber_error));
                beneficiaryNumber.requestFocus();
                flag++;
            }
            if (address.getText() != null && address.getText().toString().trim().length() > 0
            ) {
            } else {
                address.setError(getResources().getString(R.string.address_error));
                address.requestFocus();
                flag++;
            }
            if (pincode.getText() != null && pincode.getText().toString().trim().length() == 6
            ) {
            } else {
                pincode.setError(getResources().getString(R.string.pincode_error));
                pincode.requestFocus();
                flag++;
            }

            return flag;
        }

        @Override
        public void onStart() {
            super.onStart();
            if (!EventBus.getDefault().isRegistered(this)) {
                GlobalBus.getBus().register(this);
            }
        }

        @Subscribe
        public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
            if (activityFragmentMessage.getFrom().equalsIgnoreCase("verifyBene")) {

                if (activityFragmentMessage.getMessage() != null && activityFragmentMessage.getMessage().length() > 0) {
                    beneficiaryName.setText(activityFragmentMessage.getMessage());
                    verified = "1";
                } else {
                    beneficiaryName.setText(activityFragmentMessage.getMessage());
                    verified = "0";
                }

            } else if (activityFragmentMessage.getFrom().equalsIgnoreCase("refreshvalue")) {

            }

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == 4) {
                if (requestCode == 4) {
                    try {
                        bankId = data.getExtras().getString("bankId");
                        bankName = data.getExtras().getString("bankName");
                        accVerification = data.getExtras().getString("accVerification");
                        shortCode = data.getExtras().getString("shortCode");
                        Fullifsc = data.getExtras().getString("ifsc");
                        ifsc.setText(Fullifsc);
                        bank.setText(bankName);
                        ifscCode.setVisibility(View.GONE);
                        if (accVerification.equalsIgnoreCase("1") || accVerification.equalsIgnoreCase("yes")|| accVerification.equalsIgnoreCase("true"))
                            accVerify.setVisibility(View.VISIBLE);
                        else
                            accVerify.setVisibility(View.GONE);
                    }catch (Exception e){}
                }
            }

        }



    }