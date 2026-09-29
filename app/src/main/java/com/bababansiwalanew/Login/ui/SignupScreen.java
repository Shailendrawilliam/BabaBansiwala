package com.bababansiwalanew.Login.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;

public class SignupScreen extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;
    TextInputLayout mobileNumberLayout, nameLayout, pincodeLayout;
    TextInputLayout ShopNameLayout, _WhatsMobileLayout, _AddressLayout, _DistrictLayout;
    TextInputLayout _StateLayout, _AadharNoLayout, _PANNoLayout, role_layout, emailLayout;

    EditText mobileNumber, pincode;
    TextInputEditText name;
    EditText whatsapp, address, district, state;
    EditText aadhar, pan, shopName, email;
    AppCompatButton signupButton;
    String RoleId = "";

    String userName, userMobile, userPincode;
    String Selectedrole, SelectedroleID = "1";
    Spinner role;
    String userwhatsapp, useraddress, userdistrict;
    String userstate, useraadhar, userpan, usershopName, useremail, from;
    String[] Rrole = {"--Select Role--", "Retailer"};
    String[] Drole = {"--Select Role--", "Distributor", "Retailer"};
    String[] Mrole = {"--Select Role--", "MasterDistributor", "Distributor", "Retailer"};
    ArrayAdapter aa;
    private ProgressDialog mProgressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        from = getIntent().getExtras().getString("from");
        mProgressDialog = new ProgressDialog(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sign Up");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mobileNumberLayout = (TextInputLayout) findViewById(R.id.mobileNumberLayout);
        role_layout = (TextInputLayout) findViewById(R.id.rolelayout);
        role = (Spinner) findViewById(R.id.Spinner);
        nameLayout = (TextInputLayout) findViewById(R.id.nameLayout);
        pincodeLayout = (TextInputLayout) findViewById(R.id.pincodeLayout);
        ShopNameLayout = (TextInputLayout) findViewById(R.id.shopNameLayout);
        _WhatsMobileLayout = (TextInputLayout) findViewById(R.id.whatsappLayout);
        _AddressLayout = (TextInputLayout) findViewById(R.id.addressLayout);
        _DistrictLayout = (TextInputLayout) findViewById(R.id.districtlayout);
        _StateLayout = (TextInputLayout) findViewById(R.id.statelayout);
        _AadharNoLayout = (TextInputLayout) findViewById(R.id.aadharLayout);
        _PANNoLayout = (TextInputLayout) findViewById(R.id.panlayout);
        emailLayout = (TextInputLayout) findViewById(R.id.emaillayout);
        _PANNoLayout.setVisibility(View.GONE);

        email = (EditText) findViewById(R.id.email);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        name = findViewById(R.id.name);
        pincode = (EditText) findViewById(R.id.pincode);
        whatsapp = (EditText) findViewById(R.id.whatsapp);
        address = (EditText) findViewById(R.id.address);
        district = (EditText) findViewById(R.id.district);
        state = (EditText) findViewById(R.id.state);
        aadhar = (EditText) findViewById(R.id.aadhar);
        pan = (EditText) findViewById(R.id.pan);
        shopName = (EditText) findViewById(R.id.shopName);
        signupButton = (AppCompatButton) findViewById(R.id.signupButton);
        signupButton.setOnClickListener((View.OnClickListener) this);
        try {
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, "");

            if (RoleId != null && RoleId.equalsIgnoreCase("2")) {
                aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Rrole);
            } else if (RoleId != null && RoleId.equalsIgnoreCase("8")) {
                aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Drole);
            } else if (RoleId != null && RoleId.equalsIgnoreCase("6")) {
                aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Mrole);
            } else if (RoleId != null && !RoleId.equalsIgnoreCase("8")
                    && !RoleId.equalsIgnoreCase("3") &&
                    !RoleId.equalsIgnoreCase("2")) {
                aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Mrole);
            }
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
            role.setAdapter(aa);
            role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int position, long id) {
                    Selectedrole = arg0.getItemAtPosition(position).toString();

                    if (Selectedrole.equalsIgnoreCase("--Select Role--")) {
                        try {
                            ((TextView) role.getSelectedView()).setError("Please Select Role");
                        } catch (Exception e) {
                        }
                    } else if (Selectedrole.equalsIgnoreCase("Distributor")) {
                        SelectedroleID = "2";
                    } else if (Selectedrole.equalsIgnoreCase("Retailer")) {
                        SelectedroleID = "3";
                    } else if (Selectedrole.equalsIgnoreCase("MasterDistributor")) {
                        SelectedroleID = "8";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    ((TextView) role.getSelectedView()).setError("Please Select Role");
                }
            });
        } catch (Exception e) {
        }

    }


    @Override
    public void onClick(View v) {
        if (v == signupButton) {
            if (validateForm() == 0) {

                if (UtilMethods.INSTANCE.isNetworkAvialable(SignupScreen.this)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();
                    if (!from.equalsIgnoreCase("profile")) {
                        UtilMethods.INSTANCE.Signup(this, shopName.getText().toString().trim(), name.getText().toString().trim(),
                                mobileNumber.getText().toString().trim(), whatsapp.getText().toString().trim(),
                                address.getText().toString().trim(), district.getText().toString().trim(),
                                state.getText().toString().trim(),
                                pincode.getText().toString().trim(),
                                aadhar.getText().toString().trim(),
                                pan.getText().toString().trim(), email.getText().toString().trim(), mProgressDialog, SelectedroleID, new UtilMethods.ApiCallBack() {
                                    @Override
                                    public void onSucess(Object object) {
                                        mobileNumber.setText("");
                                        name.setText("");
                                        pincode.setText("");
                                        whatsapp.setText("");
                                        address.setText("");
                                        district.setText("");
                                        state.setText("");
                                        aadhar.setText("");
                                        pan.setText("");
                                        shopName.setText("");
                                        SelectedroleID = "";
                                        email.setText("");

                                    }
                                });

                    } else {
                        UtilMethods.INSTANCE.UserCreation(this,
                                shopName.getText().toString().trim(),
                                name.getText().toString().trim(),
                                mobileNumber.getText().toString().trim(),
                                whatsapp.getText().toString().trim(),
                                address.getText().toString().trim(),
                                district.getText().toString().trim(),
                                state.getText().toString().trim(),
                                pincode.getText().toString().trim(),
                                aadhar.getText().toString().trim(),
                                pan.getText().toString().trim(), email.getText().toString().trim(), mProgressDialog, SelectedroleID, new UtilMethods.ApiCallBack() {
                                    @Override
                                    public void onSucess(Object object) {
                                        mobileNumber.setText("");
                                        name.setText("");
                                        pincode.setText("");
                                        whatsapp.setText("");
                                        address.setText("");
                                        district.setText("");
                                        state.setText("");
                                        aadhar.setText("");
                                        pan.setText("");
                                        shopName.setText("");
                                        SelectedroleID = "";
                                        email.setText("");

                                    }
                                });
                    }

                } else {
                    UtilMethods.INSTANCE.dialogOk(SignupScreen.this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }

            }
        }
    }

    public int validateForm() {

        int flag = 0;

//        if (pincode.getText() != null && pincode.getText().toString().trim().length() > 0) {
//        pincodeLayout.setErrorEnabled(false);
//        userPincode = pincode.getText().toString().trim();
//        } else {
//        pincode.setError(getResources().getString(R.string.pincode_error));
//        pincode.requestFocus();
//        flag++;
//        }

        if (name.getText() != null && name.getText().toString().trim().length() > 0) {
            nameLayout.setErrorEnabled(false);
            userName = name.getText().toString().trim();
        } else {
            name.setError(getResources().getString(R.string.name_error));
            name.requestFocus();
            flag++;
        }

        if (mobileNumber.getText() != null && mobileNumber.getText().toString().trim().length() > 0 &&
                !(mobileNumber.getText().toString().trim().length() < 10) &&
                (mobileNumber.getText().toString().trim().charAt(0) == '7' ||
                        mobileNumber.getText().toString().trim().charAt(0) == '8' ||
                        mobileNumber.getText().toString().trim().charAt(0) == '6' ||
                        mobileNumber.getText().toString().trim().charAt(0) == '9')
        ) {
            mobileNumberLayout.setErrorEnabled(false);
            userMobile = mobileNumber.getText().toString().trim();
        } else {
            mobileNumber.setError(getResources().getString(R.string.mobilenumber_error));
            mobileNumber.requestFocus();
            flag++;
        }

//        if (shopName.getText() != null && shopName.getText().toString().trim().length() > 0) {
//                ShopNameLayout.setErrorEnabled(false);
//                usershopName = shopName.getText().toString().trim();
//        } else {
//                shopName.setError( "Enter Valid shopName");
//                shopName.requestFocus();
//                flag++;
//        }
//        if (whatsapp.getText() != null && whatsapp.getText().toString().trim().length() > 0) {
//                _WhatsMobileLayout.setErrorEnabled(false);
//                userwhatsapp = whatsapp.getText().toString().trim();
//        } else {
//                whatsapp.setError( "Enter Valid Whatsapp No");
//                whatsapp.requestFocus();
//                flag++;
//        }
       /* if (address.getText() != null && address.getText().toString().trim().length() > 0) {
                _AddressLayout.setErrorEnabled(false);
                useraddress = address.getText().toString().trim();
        } else {
                address.setError( "Enter Valid Address");
                address.requestFocus();
                flag++;
        }*//*if (district.getText() != null && district.getText().toString().trim().length() > 0) {
                _DistrictLayout.setErrorEnabled(false);
                userdistrict = district.getText().toString().trim();
        } else {
                district.setError( "Enter Valid District");
                district.requestFocus();
                flag++;
        }if (state.getText() != null && state.getText().toString().trim().length() > 0) {
                _StateLayout.setErrorEnabled(false);
                userstate = state.getText().toString().trim();
        } else {
                state.setError( "Enter Valid State");
                state.requestFocus();
                flag++;
        }if (aadhar.getText() != null && aadhar.getText().toString().trim().length() > 0) {
                _AadharNoLayout.setErrorEnabled(false);
                useraadhar = aadhar.getText().toString().trim();
        } else {
                aadhar.setError( "Enter Valid Password");
                aadhar.requestFocus();
                flag++;
        }*/ /*if (pan.getText() != null && pan.getText().toString().trim().length() > 0) {
                _PANNoLayout.setErrorEnabled(false);
                userpan = pan.getText().toString().trim();
        } else {
                pan.setError( "Enter Valid pan  ");
                pan.requestFocus();
                flag++;
        }*/
        if (email.getText() != null && email.getText().toString().trim().length() > 0) {
            emailLayout.setErrorEnabled(false);
            useremail = email.getText().toString().trim();
        } else {
            email.setError("Enter Valid Email  ");
            email.requestFocus();
            flag++;
        }

        return flag;
    }

    public void finishMethod() {
        finish();
    }
}
