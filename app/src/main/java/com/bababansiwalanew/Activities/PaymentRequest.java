package com.bababansiwalanew.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.EnglishNumberToWords;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.ui.BankDetailList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Random;

public class PaymentRequest extends AppCompatActivity implements View.OnClickListener {

    String fundType = "1";

    public ImageView cancel;




    EditText txtTransactionID, bankFund, number, bankRole, AmountInWords;
    Spinner paymentmode;
    EditText txttranferAmount, remark;
    String walletType;
    Button btnPaymentSubmit;
    Toolbar toolbar;
    ArrayAdapter aa;
    String RequestedTo = "", SelectedpaymentMode;
    RadioButton prepaid, utility;
    ProgressDialog loader = null;

    String[] bankNames = {"--Select Payment Mode--", "Cash deposit", "Third Party Transfer", "NEFT", "IMPS", "RTGS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);
        GetId();
    }


    private void GetId() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Payment Request");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        // setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loader = new ProgressDialog(PaymentRequest.this);
        txtTransactionID = findViewById(R.id.txtTransactionID);
        txttranferAmount = findViewById(R.id.txttranferAmount);
        remark = findViewById(R.id.remark);
        number = findViewById(R.id.number);


        bankFund = findViewById(R.id.bankFund);
        bankRole = findViewById(R.id.bankRole);
        paymentmode = findViewById(R.id.paymentmode);
        AmountInWords = findViewById(R.id.AmountInWords);

        prepaid = findViewById(R.id.prepaid);
        utility = findViewById(R.id.utility);
        txttranferAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    try {
                        Long Amount = Long.parseLong(s.toString());

                        String return_val_in_english = EnglishNumberToWords.convert(Amount);
                        AmountInWords.setText(return_val_in_english);
                    } catch (Exception e) {

                    }

                }


            }
        });
        btnPaymentSubmit = findViewById(R.id.btnPaymentSubmit);
        prepaid.setChecked(true);
        walletType = "1";
        SetListener();
        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        paymentmode.setAdapter(aa);
        paymentmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                SelectedpaymentMode = arg0.getItemAtPosition(position).toString();

//                if (SelectedpaymentMode.equalsIgnoreCase("--Select Payment Mode--")){
//                    ((TextView)paymentmode.getSelectedView()).setError("Please Select Payment Mode");
//                }
                if (SelectedpaymentMode.equalsIgnoreCase("Cash deposit")) {

                    txtTransactionID.setText(getRandomString(20));
                } else {
                    txtTransactionID.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ((TextView) paymentmode.getSelectedView()).setError("Please Select Payment Mode");
            }
        });
    }

    private void SetListener() {
        bankRole.setOnClickListener(this);
          bankFund.setOnClickListener(this);
        prepaid.setOnClickListener(this);
        utility.setOnClickListener(this);
        btnPaymentSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == bankRole) {
            Intent bankRoleIntent = new Intent(PaymentRequest.this, BankDetailList.class);
            bankRoleIntent.putExtra("from", "role");
            startActivity(bankRoleIntent);
        }
        if (v == paymentmode) {
            Intent bankRoleIntent = new Intent(PaymentRequest.this, BankDetailList.class);
            bankRoleIntent.putExtra("from", "role");
            startActivity(bankRoleIntent);
        }

        if (v == bankFund) {
            Intent bankIntent = new Intent(PaymentRequest.this, BankDetailList.class);
            bankIntent.putExtra("from", "bank");
            startActivity(bankIntent);
        }
        if (v == prepaid) {
            prepaid.setChecked(true);
            walletType = "1";
            utility.setChecked(false);
        }
        if (v == utility) {
            prepaid.setChecked(false);
            walletType = "2";
            utility.setChecked(true);
        }
        if (v == btnPaymentSubmit) {
            if (validationForm("") == 0) {
            btnPaymentSubmit.setEnabled(false);
                btnPaymentSubmit.setBackgroundColor(getResources().getColor(R.color.grey_200));
                if (UtilMethods.INSTANCE.isNetworkAvialable(PaymentRequest.this)) {

             new Uploadtask().execute("Execute");
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            btnPaymentSubmit.setEnabled(true);
                            btnPaymentSubmit.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    }, 7000);
                } else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }


                // }
            }

        }
    }

    private class Uploadtask extends AsyncTask<String, Integer, String> {

        // Runs in UI before background thread is called
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.show();
            loader.setMessage("Loading.. ");
            loader.setIndeterminate(true);
            loader.setCanceledOnTouchOutside(false);

            // Do something like display a progress bar
        }

        // This is run in a background thread
        @Override
        protected String doInBackground(String... params) {

            UtilMethods.INSTANCE.PaymentRequest(PaymentRequest.this, txttranferAmount.getText().toString(),
                    number.getText().toString(), txtTransactionID.getText().toString(), fundType, remark.getText().toString(),
                    bankFund.getText().toString(), AmountInWords.getText().toString(), loader);

            return "this string is passed to onPostExecute";
        }

         @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
          }
   @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Do things like hide the progress bar or change a TextView
        }
    }


    private int validationForm(String s) {
        int flag = 0;
        if (number.getText() != null && number.getText().toString().trim().length() > 0) {
        } else {
            number.setError("Please enter valid number!!");
            number.requestFocus();
            flag++;
        }
        if (txttranferAmount.getText() != null && txttranferAmount.getText().toString().trim().length() > 0) {
        } else {
            txttranferAmount.setError("Please enter valid amount!!");
            txttranferAmount.requestFocus();
            flag++;
        }

        if (txtTransactionID.getText() != null && txtTransactionID.getText().toString().trim().length() > 0) {
        } else {
            txtTransactionID.setError("Please enter valid Txn Id!!");
            txtTransactionID.requestFocus();
            flag++;
        }
        return flag;
    }

    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }


    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("bankSelected")) {

            String[] detail = activityFragmentMessage.getMessage().split(",");
            bankFund.setText("" + detail[0]);
            number.setText("" + detail[1]);
        } else if (activityFragmentMessage.getFrom().equalsIgnoreCase("bankSelectedRole")) {

            String[] detail = activityFragmentMessage.getMessage().split(",");
            bankRole.setText("" + detail[0]);
            RequestedTo = detail[1];
        } else if (activityFragmentMessage.getFrom().equalsIgnoreCase("refreshvalue")) {
            bankRole.setText("");
            bankFund.setText("");
            number.setText("");
            txtTransactionID.setText("");
            txttranferAmount.setText("");
            remark.setText("");
            number.setText("");
            bankFund.setText("");
            paymentmode.setSelection(0);

        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
}
