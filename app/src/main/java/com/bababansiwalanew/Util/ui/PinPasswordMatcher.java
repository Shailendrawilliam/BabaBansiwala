package com.bababansiwalanew.Util.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;


/**
 * Created by Lalit on 15-02-2017.
 */

public class PinPasswordMatcher extends AppCompatActivity implements View.OnClickListener {
    static final int PIN_MATCHER = 111;
    TextInputLayout otpTextLayout;
    EditText otp;
    AppCompatButton okButton;
    AppCompatButton cancelButton;
boolean pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinpassword_layout);

        otpTextLayout = (TextInputLayout) findViewById(R.id.otpTextLayout);
        otp = (EditText) findViewById(R.id.otp);
        okButton = (AppCompatButton) findViewById(R.id.okButton);
        cancelButton = (AppCompatButton) findViewById(R.id.cancelButton);
      //  cancelButton.setVisibility(View.GONE);
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == okButton) {
            if (otp.getText() != null && otp.getText().toString().trim().length() > 0) {
                //UtilMethods.INSTANCE.Matcher(PinPasswordMatcher.this, otp.getText().toString().trim());
                //PinSecureAuth

                UtilMethods.INSTANCE.PinSecureAuth(  this,   otp.getText().toString(), new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        Intent intent = new Intent();
                        intent.putExtra("flag",  true);
                        setResult( PIN_MATCHER , intent);
                        finish();
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Intent intent = new Intent();
                        intent.putExtra("flag",  false);
                        setResult( PIN_MATCHER , intent);
                        finish();
                    }
                });

            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Error!")
                        .setMessage("Pin password cannot be empty!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // "OK" button was clicked

                            }
                        })
                        .show();
            }

        }
        if (v == cancelButton) {
            /*Intent intent = new Intent();
            intent.putExtra("flag",  pin);
            intent.putExtra("cancelled",  "cancelled");
            setResult( PIN_MATCHER , intent);*/
            finish();
        }
    }
}
