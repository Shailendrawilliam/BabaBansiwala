package com.bababansiwalanew.AddMoney;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.StrictMode;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
/*import com.razorpay.Checkout;
import com.razorpay.Payment;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;*/
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;
import com.bababansiwalanew.Api.Object.BalanceType;
import com.bababansiwalanew.Api.Object.PG;
import com.bababansiwalanew.Api.Object.PGModelForApp;
import com.bababansiwalanew.Api.Object.RequestPTM;
import com.bababansiwalanew.Api.Request.ChoosePaymentGatewayRequest;
import com.bababansiwalanew.Api.Request.GatewayTransactionRequest;
import com.bababansiwalanew.Api.Request.PayTMTransactionUpdateRequest;
import com.bababansiwalanew.Api.Response.BalanceResponse;
import com.bababansiwalanew.Api.Response.BasicResponse;
import com.bababansiwalanew.Api.Response.PaymentChooseResponse;
import com.bababansiwalanew.Api.Response.PaymentTransactionResponsedto.PaymentChooseResponse.PaymentTransactionResponse;
 import com.bababansiwalanew.Fragments.Adapter.AddMoneyTypeAdapter;
import com.bababansiwalanew.Fragments.Adapter.GatewayTypeAdapter;
import com.bababansiwalanew.Login.dto.LoginResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApiClient;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CustomLoader;
import com.bababansiwalanew.Util.EndPointInterface;
import com.bababansiwalanew.Util.ListPopupWindowAdapter;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.Operator;
import com.bababansiwalanew.Util.dto.OperatorList;
import com.bababansiwalanew.Util.dto.WalletResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static android.view.View.VISIBLE;

import retrofit2.Call;

import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.razorpay.Checkout;
import com.razorpay.Payment;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import retrofit2.Callback;

public class AddMoneyActivity extends AppCompatActivity implements PaymentResultWithDataListener {
    private static final String TAG = AddMoneyActivity.class.getSimpleName();
    View walletView;
    TextView walletTv, walletAmountTv;
    ImageView arrowIv;
    EditText amountEt;
    RecyclerView recyclerView;
    CustomLoader loader;
    BalanceResponse balanceCheckResponse;
    RazorpayClient razorpay;
    HashMap<String, Integer> walletIdMap = new HashMap<>();
    ArrayList<BalanceType> mBalanceTypes = new ArrayList<>();
    ArrayList<ArrayList<Operator>> operatorArray = new ArrayList<ArrayList<Operator>>();
    ArrayList<PG> pgList = new ArrayList<>();
    int selectedOPId = 0;
    LoginResponse mLoginDataResponse;
    boolean isActivityPause;
    ArrayList<Operator> operator = new ArrayList<>();
    OperatorList operatorList = new OperatorList();
    // String from, type;
    String paymentid;
    String SessionID;
    String UMobile;
    String userId;
    String umail;
    Checkout checkout;
    String PaymentStatus;
    String PaymentOrderId;
    String PaymentId;
    String Entity;
    String CreatedAt;
    String Amount;
    String Method;
    String Amount_Refund;
    String Refund_Status;
    String CardId;
    String Bank;
    String Wallet;
    String ContactNo;
    String payment_method;
    String r_key;
    String r_secret_key;
    // private boolean isBankWalletActive;
    private WalletResponse mWalletTypeResponse;
    private int selectedWalletId = 1;
    private Dialog gatewayDialog;
    private String selectedMethod;
    private boolean isDialogShowBackground;
    private String dialogMsg, selectedWallet = "1";
    private boolean isSucessDialog;
    private PaymentCapture paymentCapture;
    LinearLayout wallettype;
    RadioButton rbPrepaid, rbUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        isActivityPause = false;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        wallettype = findViewById(R.id.wallettype);
        rbPrepaid = findViewById(R.id.rbPrepaid);
        rbUtility = findViewById(R.id.rbUtility);
          checkout = new Checkout();
        SharedPreferences myPrefs = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, this.MODE_PRIVATE);

        SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        userId = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);
        umail = myPrefs.getString(ApplicationConstant.INSTANCE.UMail, null);
         Checkout.preload(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Add Money");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loader = new CustomLoader(this, android.R.style.Theme_Translucent_NoTitleBar);
        walletView = findViewById(R.id.walletView);
        walletTv = findViewById(R.id.walletTv);
        walletAmountTv = findViewById(R.id.walletAmountTv);
        arrowIv = findViewById(R.id.arrowIv);
        amountEt = findViewById(R.id.amountEt);
        amountEt.setCompoundDrawablesWithIntrinsicBounds(
                AppCompatResources.getDrawable(this, R.drawable.ic_rupee_indian),
                null, null, null);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentCapture = new PaymentCapture();
        walletView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPoupWindow(v);
            }
        });
          if (rbPrepaid.isChecked()) {
            selectedWallet = "1";
        } else if (rbUtility.isChecked()) {
            selectedWallet = "2";
        }
        Button pay = findViewById(R.id.pay);
        pay.setVisibility(View.GONE);

        getOperatorList();
    }

    private void showWalletListPopupWindow() {

        if (balanceCheckResponse != null) {
            mBalanceTypes.clear();
            if (balanceCheckResponse.getBalanceData().getIsBalance() && balanceCheckResponse.getBalanceData().getIsBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Prepaid Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getBalance() + "")));
            }
            if (balanceCheckResponse.getBalanceData().getIsUBalance() && balanceCheckResponse.getBalanceData().getIsUBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Utility Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getuBalance() + "")));

            }
            if (balanceCheckResponse.getBalanceData().getIsBBalance() && balanceCheckResponse.getBalanceData().getIsBBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Bank Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getbBalance() + "")));
                //  isBankWalletActive = true;
            }
            if (balanceCheckResponse.getBalanceData().getIsCBalance() && balanceCheckResponse.getBalanceData().getIsCBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Card Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getcBalance() + "")));
            }
            if (balanceCheckResponse.getBalanceData().getIsIDBalance() && balanceCheckResponse.getBalanceData().getIsIDBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Registration Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getIdBalnace() + "")));
            }
            if (balanceCheckResponse.getBalanceData().getIsAEPSBalance() && balanceCheckResponse.getBalanceData().getIsAEPSBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Aeps Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getAepsBalnace() + "")));
            }
            if (balanceCheckResponse.getBalanceData().getIsPacakgeBalance() && balanceCheckResponse.getBalanceData().getIsPacakgeBalanceFund()) {
                mBalanceTypes.add(new BalanceType("Package Wallet", UtilMethods.INSTANCE.formatedAmount(balanceCheckResponse.getBalanceData().getPackageBalnace() + "")));
            }
            if (mBalanceTypes != null && mBalanceTypes.size() > 0) {
                if (mBalanceTypes.size() == 1) {
                    arrowIv.setVisibility(View.GONE);
                    walletView.setClickable(false);
                } else {
                    arrowIv.setVisibility(VISIBLE);
                    walletView.setClickable(true);
                }
             }
        } else {
            SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, "");
            balanceCheckResponse = new Gson().fromJson(balanceResponse, BalanceResponse.class);
            if (balanceCheckResponse != null) {
                showWalletListPopupWindow();
            }
            return;
        }

    }


    private void showPoupWindow(View anchor) {
        if (mBalanceTypes != null && mBalanceTypes.size() > 0) {
            final ListPopupWindow listPopupWindow =
                    createListPopupWindow(anchor, ViewGroup.LayoutParams.WRAP_CONTENT, mBalanceTypes);
            listPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect));
            listPopupWindow.show();
        } else {
            showWalletListPopupWindow();
        }
    }

    private ListPopupWindow createListPopupWindow(View anchor, int width, ArrayList<BalanceType> items) {
        final ListPopupWindow popup = new ListPopupWindow(this);

        ListAdapter adapter = new ListPopupWindowAdapter(items, this, false, R.layout.wallet_list_popup, new ListPopupWindowAdapter.ClickView() {
            @Override
            public void onClickView(String walletName, String amount) {
                walletTv.setText(walletName);
                walletAmountTv.setText(amount);
                selectedWalletId = walletIdMap.get(walletName);
                popup.dismiss();
            }
        });
        // popup.setWidth((int) getResources().getDimension(R.dimen._200sdp));
        popup.setAnchorView(anchor);
        popup.setAdapter(adapter);
        return popup;
    }

    public void getOperatorList() {


        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.operatorListPref, null);

        Gson gson = new Gson();
        operatorList = gson.fromJson(response, OperatorList.class);

        if (operatorList != null) {
            if (operatorList.getPaymentOperator() != null && operatorList.getPaymentOperator().size() > 0) {
                operator = operatorList.getPaymentOperator();
                operatorArray.add(operator);

                AddMoneyTypeAdapter addMoneyTypeAdapter = new AddMoneyTypeAdapter(operator, this);
                recyclerView.setAdapter(addMoneyTypeAdapter);

            }
        }


    }


    public void paymentTypeClick(Operator operator) {
        if (amountEt.getText().toString().isEmpty()) {
            amountEt.setError("Please Enter Amount");
            amountEt.requestFocus();
            return;
        }
        selectedMethod = operator.getOPNAME();
        selectedOPId = operator.getOPID();
        //here uncomment
        //selectedOPId =

        if (pgList != null && pgList.size() > 0) {
            if (pgList.size() == 1) {
                startGateway(pgList.get(0));
                //Toast.makeText(this, "GatewayTransaction", Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();
                showPopupGateWay();
            }
        } else {
            ChoosePaymentGateway();
            //Toast.makeText(this, "ChoosePaymentGateway", Toast.LENGTH_SHORT).show();
        }
    }


    public void ChoosePaymentGateway() {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<PaymentChooseResponse> call = git.ChoosePaymentGateway(new ChoosePaymentGatewayRequest(
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(AddMoneyActivity.this),
                    /*mLoginDataResponse.getData().get(0).getSessionID()*/SessionID,
                    /*mLoginDataResponse.getData().get(0).getUserID()*/userId,
                    /* mLoginDataResponse.getData().get(0).getUMobile()*/UMobile,
                    ""
            ));

            call.enqueue(new Callback<PaymentChooseResponse>() {
                @Override
                public void onResponse(Call<PaymentChooseResponse> call, final retrofit2.Response<PaymentChooseResponse> response) {

                    try {
                        if (loader.isShowing())
                            loader.dismiss();
                        if (response.body() != null && response.body().getStatuscode().equals("1")) {

                            if (response.body().getPGs() != null && response.body().getPGs().size() > 0) {
                                pgList = response.body().getPGs();
                                if (response.body().getPGs().size() == 1) {
                                    startGateway(pgList.get(0));
                                } else {
                                    showPopupGateWay();
                                }

                            } else {
                                UtilMethods.INSTANCE.Processing(AddMoneyActivity.this, "Service is currently down.");
                            }

                        }
                    } catch (Exception e) {
                        if (loader.isShowing())
                            loader.dismiss();

                        UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString(R.string.attention_error_title), e.getMessage(), 2);

                        //   UtilMethods.INSTANCE.Error(AddMoneyActivity.this, e.getMessage() + "");
                    }

                }

                @Override
                public void onFailure(Call<PaymentChooseResponse> call, Throwable t) {
                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                Toast.makeText(AddMoneyActivity.this, "hehehehehe", Toast.LENGTH_SHORT).show();
                                // UtilMethods.INSTANCE.Error(AddMoneyActivity.this, getString(R.string.err_msg_network));
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this,
                                        getResources().getString(R.string.attention_error_title), getString(R.string.err_msg_network), 2);


                            } else {
                                Toast.makeText(AddMoneyActivity.this, "ohoohoh", Toast.LENGTH_SHORT).show();

                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                        (R.string.attention_error_title), t.getMessage(), 2);


                            }

                        } else {


                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                    (R.string.attention_error_title), getResources().getString
                                    (R.string.failed), 2);

                        }
                    } catch (IllegalStateException ise) {
                        loader.dismiss();

                        UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                (R.string.attention_error_title), ise.getMessage(), 2);


                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader.isShowing())
                loader.dismiss();
            Toast.makeText(AddMoneyActivity.this, "uiofdshuhfjsdds", Toast.LENGTH_SHORT).show();


            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                    (R.string.attention_error_title), e.getMessage(), 2);
        }
    }


    private void showPopupGateWay() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); // or (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewMyLayout = inflater.inflate(R.layout.dialog_select_gateway, null);
        RecyclerView recyclerView = viewMyLayout.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        View closeBtn = viewMyLayout.findViewById(R.id.closeBtn);

        GatewayTypeAdapter gatewayTypeAdapter = new GatewayTypeAdapter(pgList, AddMoneyActivity.this);
        recyclerView.setAdapter(gatewayTypeAdapter);
        gatewayDialog = new Dialog(this);
        gatewayDialog.setCancelable(false);
        gatewayDialog.setContentView(viewMyLayout);
        /* dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));*/
        gatewayDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatewayDialog.dismiss();
            }
        });

        gatewayDialog.show();
        // Window window = dialog.getWindow();
        //window.setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);


    }

    public void startGateway(PG paymentGatewayType) {

        if (gatewayDialog != null && gatewayDialog.isShowing()) {
            gatewayDialog.dismiss();
        }
        GatewayTransaction(paymentGatewayType);

    }


    void initPaytmSdkNew(final RequestPTM requestPTM, String token) {


        PaytmOrder paytmOrder = new PaytmOrder(requestPTM.getORDERID() + "", requestPTM.getMID() + "",
                token, requestPTM.getTXNAMOUNT() + "",
                requestPTM.getCALLBACKURL() + "");

        TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback() {
            @Override
            public void onTransactionResponse(@Nullable Bundle bundle) {
                String status =""+ bundle.getString("STATUS");
                String msg=""+ bundle.getString("RESPMSG");
                paytmCallBackApi(bundle,status,msg);
            }

            @Override
            public void networkNotAvailable() {
                paytmCallBackApi(paytmFailedData(requestPTM, 0, "TXN_CANCEL", "Network not available"), "TXN_CANCEL", "Network not available");
            }

            @Override
            public void onErrorProceed(String inErrorMessage) {
                paytmCallBackApi(paytmFailedData(requestPTM, 0, "TXN_CANCEL", inErrorMessage), "TXN_CANCEL", inErrorMessage);
            }

            @Override
            public void clientAuthenticationFailed(String inErrorMessage) {
                paytmCallBackApi(paytmFailedData(requestPTM, 0, "TXN_CANCEL", inErrorMessage), "TXN_CANCEL", inErrorMessage);
            }

            @Override
            public void someUIErrorOccurred(String inErrorMessage) {
                paytmCallBackApi(paytmFailedData(requestPTM, 0, "TXN_CANCEL", inErrorMessage), "TXN_CANCEL", inErrorMessage);
            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {
                paytmCallBackApi(paytmFailedData(requestPTM, i, "TXN_CANCEL", s), "TXN_CANCEL", s);
            }

            @Override
            public void onBackPressedCancelTransaction() {
                paytmCallBackApi(paytmFailedData(requestPTM, 0, "TXN_CANCEL", "Transaction canceled by user"), "TXN_CANCEL", "Transaction canceled by user");
            }

            @Override
            public void onTransactionCancel(String s, Bundle bundle) {
                paytmCallBackApi(bundle, "TXN_CANCEL", "Transaction cancelled");
            }
        });
        transactionManager.setAppInvokeEnabled(false);
        transactionManager.startTransaction(this, 11);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && data != null) {
            Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();
        }
    }


    void paytmCallBackApi(Bundle inResponse, String title, String msg) {
        try {
            JsonObject json = new JsonObject();
            Set<String> keys = inResponse.keySet();
            for (String key : keys) {
                json.addProperty(key, (String) inResponse.get(key));
            }
            PayTMTransactionUpdate(json, title, msg);
        } catch (Exception e) {
        }
    }


    private Bundle paytmFailedData(RequestPTM requestPTM, int errorCode, String status, String errorMsg) {
        Bundle inResponse = new Bundle();
        inResponse.putString("STATUS", status);
        inResponse.putString("CHECKSUMHASH", requestPTM.getCHECKSUMHASH());
        inResponse.putString("BANKNAME", "");
        inResponse.putString("ORDERID", requestPTM.getORDERID());
        inResponse.putString("TXNAMOUNT", requestPTM.getTXNAMOUNT());
        inResponse.putString("MID", requestPTM.getMID());
        inResponse.putString("TXNID", "");
        inResponse.putString("RESPCODE", errorCode + "");
        inResponse.putString("PAYMENTMODE", "");
        inResponse.putString("BANKTXNID", "");
        inResponse.putString("CURRENCY", "INR");
        inResponse.putString("GATEWAYNAME", "");
        inResponse.putString("RESPMSG", errorMsg);

        return inResponse;
    }


    void initRazorPaySdk(PGModelForApp pGModelForApp) {
        r_key = pGModelForApp.getRPayRequest().getKey_id();
        r_secret_key = pGModelForApp.getRPayRequest().getKey_Secret();
        Checkout checkout = new Checkout();
        checkout.setKeyID(pGModelForApp.getRPayRequest().getKey_id());
        checkout.setImage(R.drawable.logo);

        try {
            JSONObject options = new JSONObject();
            options.put("name", pGModelForApp.getRPayRequest().getPrefill_name());
            options.put("theme.color", "#" + Integer.toHexString(ContextCompat.getColor(this, R.color.colorPrimary) & 0x00ffffff));
            options.put("prefill.contact", pGModelForApp.getRPayRequest().getPrefill_contact());
            options.put("prefill.email", pGModelForApp.getRPayRequest().getPrefill_email());

            if (selectedMethod != null) {
                if (selectedMethod.toLowerCase().contains("card")) {
                    options.put("prefill.method", "card");
                }
                if (selectedMethod.toLowerCase().contains("net banking") || selectedMethod.toLowerCase().contains("netbanking")) {
                    options.put("prefill.method", "netbanking");
                }
                if (selectedMethod.toLowerCase().contains("upi")) {
                    options.put("prefill.method", "upi");
                }
                if (selectedMethod.toLowerCase().contains("wallet")) {
                    options.put("prefill.method", "wallet");
                }
                if (selectedMethod.toLowerCase().contains("emi")) {
                    options.put("prefill.method", "emi");
                }
            }
            options.put("description", pGModelForApp.getRPayRequest().getDescription());
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", pGModelForApp.getRPayRequest().getOrder_id());
            options.put("currency", "INR");

            options.put("amount", (pGModelForApp.getRPayRequest().getAmount()) * 100);

            checkout.open(AddMoneyActivity.this, options);
        } catch (Exception e) {
            android.util.Log.e(TAG, "Error in starting Razorpay Checkout", e);
        } //key_id":"rzp_live_0ImY1iPcwt5Mgd","key_Secret":"
        // uzyvZPc4wdTU1Wv5RYzxdqHw","order_id":"order_GBpVuK9Q9Rl1A5"
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData data) {

        try {
            //    Toast.makeText(this, "Payment Successful: " + data.getPaymentId(), Toast.LENGTH_SHORT).show();
            final String paymentId = data.getPaymentId();
            String signature = data.getSignature();
            String orderId = data.getOrderId();
            String contact = data.getUserContact();
            String email = data.getUserEmail();

       try {

                //Get all details
                fetchPayment(paymentId, orderId);
                //Your code goes here
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            android.util.Log.e("com.merchant", e.getMessage(), e);
        }
    }


    public void finishMethod() {
        finish();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(getApplicationContext(), "" + s, Toast.LENGTH_LONG).show();
    }

    public void fetchPayment(String payment_id, String orderId) throws RazorpayException {
//fwaPUCwDKn8kqANP1Zf3sO4T
        RazorpayClient razorpay = new RazorpayClient(/*"rzp_live_0966TRE2PCqgbk"*/r_key, r_secret_key /*"fwaPUCwDKn8kqANP1Zf3sO4T"*//*"fwaPUCwDKn8kqANP1Zf3sO4T"*/);

        try {
            Payment payment = razorpay.Payments.fetch(payment_id);
            android.util.Log.e("payyyy", "" + payment);

            try {
                JSONObject object = new JSONObject("" + payment);
                PaymentStatus = object.getString("status");
                PaymentOrderId = object.getString("order_id");
                PaymentId = object.getString("id");
                Entity = object.getString("entity");
                CreatedAt = getDate(Long.parseLong(object.getString("created_at")));
                Amount = object.getString("amount");
                Method = object.getString("method");
                Amount_Refund = object.getString("amount_refunded");
                Refund_Status = object.getString("refund_status");
                CardId = object.getString("card_id");
                Bank = object.getString("bank");
                Wallet = object.getString("wallet");
                ContactNo = object.getString("contact");
                payment_method = object.getString("method");

                Handler h = new Handler(Looper.getMainLooper());
                h.post(new Runnable() {
                    public void run() {

                        if (UtilMethods.INSTANCE.isNetworkAvialable(AddMoneyActivity.this)) {


                            loader.show();
                            loader.setCancelable(false);
                            loader.setCanceledOnTouchOutside(true);
                           /* HitPayment payAPI = new HitPayment();
                            payAPI.execute();*/
                            UtilMethods.INSTANCE.RazorpayUpdate(AddMoneyActivity.this, /*new RazorpayresponseUpdate(*/PaymentStatus, PaymentOrderId,
                                    PaymentId,
                                    Entity,
                                    CreatedAt,
                                    Amount,
                                    Method,
                                    Amount_Refund,
                                    Refund_Status,
                                    CardId,
                                    Bank,
                                    Wallet,
                                    ContactNo/*)*/, loader);
                        } else {
                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getString(R.string.network_error_title),
                                    getString(R.string.network_error_message), 2);
                        }

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (RazorpayException e) {
            // Handle Exception
            System.out.println(e.getMessage());
        }
    }


    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss a", cal).toString();
        return date;
    }

    public void GatewayTransaction(final PG paymentGatewayType) {
        try {
            if (rbPrepaid.isChecked()) {
                selectedWallet = "1";
            } else if (rbUtility.isChecked()) {
                selectedWallet = "2";
            }
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<PaymentTransactionResponse> call = git.GatewayTransaction(new GatewayTransactionRequest(selectedWallet,
                            ApplicationConstant.INSTANCE.APP_ID,
                            UtilMethods.INSTANCE.getIMEI(AddMoneyActivity.this),
                            SessionID,
                            userId,
                            UMobile,
                            amountEt.getText().toString(),
                            paymentGatewayType.getID() + "",
                            selectedOPId,
                            "2"
                    )
            );
            call.enqueue(new Callback<PaymentTransactionResponse>() {
                @Override
                public void onResponse(Call<PaymentTransactionResponse> call, final retrofit2.Response<PaymentTransactionResponse> response) {
                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode().equals("1")) {
                                if (response.body().getPGModelForApp() != null) {
                                    if (response.body().getPGModelForApp().getStatuscode().equals("1")) {

                                        if (response.body().getPGModelForApp().getPGID().equals("1") || paymentGatewayType.getPGType() == 1) {
                                            if (response.body().getPGModelForApp().getRequestPTM() != null) {
                                                //initPaytmSdk(response.body().getPGModelForApp().getRequestPTM());
                                            } else {
                                                UtilMethods.INSTANCE.Processing(AddMoneyActivity.this, response.body().getPGModelForApp().getMsg() + "");
                                            }
                                        } else if (response.body().getPGModelForApp().getPGID().equals("11") || paymentGatewayType.getPGType() == 5) {
                                            if (response.body().getPGModelForApp().getRequestPTM() != null) {
                                                initPaytmSdkNew(response.body().getPGModelForApp().getRequestPTM(), response.body().getPGModelForApp().getToken());
                                            } else {
                                                UtilMethods.INSTANCE.Processing(AddMoneyActivity.this, response.body().getPGModelForApp().getMsg() + "");
                                            }
                                        } else if (response.body().getPGModelForApp().getPGID().equals("2") || paymentGatewayType.getPGType() == 2) {
                                            if (response.body().getPGModelForApp().getRPayRequest() != null) {
                                                initRazorPaySdk(response.body().getPGModelForApp());
                                            } else {
                                                UtilMethods.INSTANCE.Processing(AddMoneyActivity.this, response.body().getPGModelForApp().getMsg() + "");
                                            }
                                        }
                                    } else {
                                        UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                                        (R.string.attention_error_title),
                                                response.body().getPGModelForApp().getMsg() + "", 2);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                            (R.string.attention_error_title), response.body().getMsg() + " " +
                                            getString(R.string.some_thing_error), 2);
                                }
                            } else {
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                        (R.string.attention_error_title), response.body().getMsg() + "", 2);
                            }

                        } else {
                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                    (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                        }

                    } catch (Exception e) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                (R.string.attention_error_title), e.getMessage() + "", 2);
                    }
                }

                @Override
                public void onFailure(Call<PaymentTransactionResponse> call, Throwable t) {
                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                        (R.string.attention_error_title), getString(R.string.err_msg_network), 2);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                        (R.string.attention_error_title), t.getMessage(), 2);
                            }

                        } else {
                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                    (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                        }
                    } catch (IllegalStateException ise) {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                (R.string.attention_error_title), getString(R.string.some_thing_error), 2);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader.isShowing()) {
                loader.dismiss();
            }
            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                    (R.string.attention_error_title), e.getMessage() + "", 2);
        }
    }


    public void PayTMTransactionUpdate(JsonObject response, String title, String msg) {
        try {
            if (!isActivityPause) {
                loader.show();
            }
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BasicResponse> call = git.PayTMTransactionUpdate(new PayTMTransactionUpdateRequest(response,
                    /*mLoginDataResponse.getData().getUserID()*/userId,
                    /*mLoginDataResponse.getData().getLoginTypeID()*/"1",
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(AddMoneyActivity.this),
                    "", "", ""
                    /* UtilMethods.INSTANCE.getSerialNo(AddMoneyActivity.this)*/,
                    /*mLoginDataResponse.getData().getSessionID()*/SessionID,
                    /*  mLoginDataResponse.getData().getSession()*/SessionID,
                    UMobile
            ));

            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (response.body() != null) {
                            if (response.body().getStatuscode() == 1) {
                                amountEt.setText("");
                                UtilMethods.INSTANCE.BalanceCheck(getApplicationContext(), null);
                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, title, msg + "", 13);
                                } else {
                                    isDialogShowBackground = true;
                                    isSucessDialog = true;
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, title, msg + "", 12);

                                }
                            } else if (response.body().getStatuscode() == 2) {
                                amountEt.setText("");
                                UtilMethods.INSTANCE.BalanceCheck(getApplicationContext(), null);
                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, title, msg + "", 12);
                                } else {
                                    isDialogShowBackground = true;

                                    isSucessDialog = true;
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, title, msg + "", 12);

                                }
                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, title, msg + "", 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = response.body().getMsg() + "";
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
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
                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                    (R.string.attention_error_title), e.getMessage() + "", 2);
                        } else {
                            isDialogShowBackground = true;
                            dialogMsg = e.getMessage() + "";
                            isSucessDialog = false;
                        }
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    try {
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                        if (t.getMessage() != null && !t.getMessage().isEmpty()) {

                            if (t.getMessage().contains("No address associated with hostname")) {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                            (R.string.attention_error_title), getString(R.string.err_msg_network), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = getString(R.string.err_msg_network);
                                    isSucessDialog = false;
                                }
                            } else {

                                if (!isActivityPause) {
                                    UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
                                            (R.string.attention_error_title), t.getMessage(), 2);
                                } else {
                                    isDialogShowBackground = true;
                                    dialogMsg = t.getMessage();
                                    isSucessDialog = false;
                                }
                            }

                        } else {

                            if (!isActivityPause) {
                                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
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
                            UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
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
                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
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
                UtilMethods.INSTANCE.Successful(AddMoneyActivity.this, dialogMsg);
            } else {
                UtilMethods.INSTANCE.dialogOk(AddMoneyActivity.this, getResources().getString
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
}
