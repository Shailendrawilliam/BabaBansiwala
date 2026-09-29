package com.bababansiwalanew.Register.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

/**
 * Created by Lalit on 09-03-2017.
 */

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextInputLayout mobileNumberLayout, nameLayout, emailLayout, pincodeLayout;
    EditText mobileNumber, name, email, pincode;
    AppCompatButton signupButton;

    String userName, userMobile, userEmail, userPincode;
    private ProgressDialog mProgressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        mProgressDialog = new ProgressDialog(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
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
        nameLayout = (TextInputLayout) findViewById(R.id.nameLayout);
        pincodeLayout = (TextInputLayout) findViewById(R.id.pincodeLayout);
        emailLayout = (TextInputLayout) findViewById(R.id.emailLayout);

        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        name = (EditText) findViewById(R.id.name);
        pincode = (EditText) findViewById(R.id.pincode);
        email = (EditText) findViewById(R.id.email);

        signupButton = (AppCompatButton) findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signupButton) {
            if (validateForm() == 0) {

                if (UtilMethods.INSTANCE.isNetworkAvialable(RegisterScreen.this)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.userCreation(this, name.getText().toString().trim(), email.getText().toString().trim(),
                            mobileNumber.getText().toString().trim(), pincode.getText().toString().trim(), mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(RegisterScreen.this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }

            }
        }
    }

    public int validateForm() {

        int flag = 0;

        if (pincode.getText() != null && pincode.getText().toString().trim().length() > 0) {
            pincodeLayout.setErrorEnabled(false);
            userPincode = pincode.getText().toString().trim();
        } else {
            pincode.setError(getResources().getString(R.string.pincode_error));
            pincode.requestFocus();
            flag++;
        }

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
                        mobileNumber.getText().toString().trim().charAt(0) == '9')
                ) {
            mobileNumberLayout.setErrorEnabled(false);
            userMobile = mobileNumber.getText().toString().trim();
        } else {
            mobileNumber.setError(getResources().getString(R.string.mobilenumber_error));
            mobileNumber.requestFocus();
            flag++;
        }

        if (email.getText() != null && email.getText().toString().trim().length() > 0) {
            userEmail = email.getText().toString().trim();
        }

        return flag;
    }

    public void finishMethod() {
        finish();
    }
}
