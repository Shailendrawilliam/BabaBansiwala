package com.bababansiwalanew.Util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.bababansiwalanew.DMR.ui.AddBeneficiaryScreen;
import com.google.android.material.textfield.TextInputLayout;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.telephony.TelephonyManager;
 import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.bababansiwalanew.Activities.OfferSheetActivity;
import com.bababansiwalanew.Activities.UserSaleReportDetailDateWise;
import com.bababansiwalanew.AddMoney.AddMoneyActivity;
import com.bababansiwalanew.Api.Response.BasicResponse;
import com.bababansiwalanew.BalanceCheck.dto.BalanceCheckResponse;
import com.bababansiwalanew.BrowsePlan.dto.ResponsePlan;
import com.bababansiwalanew.BrowsePlan.ui.BrowsePlanScreen;
import com.bababansiwalanew.CommissionSlab.dto.CommissionSlabResponse;
import com.bababansiwalanew.CommissionSlab.ui.CommissionScreen;
import com.bababansiwalanew.DMR.dto.BeneficiaryResponse;
import com.bababansiwalanew.DMR.dto.LoginSenderResponse;
import com.bababansiwalanew.DMRReport.dto.DMRReportResponse;
import com.bababansiwalanew.DTHReversal.dto.ChatResponse;
import com.bababansiwalanew.DTHReversal.dto.DTHTicketResponse;
import com.bababansiwalanew.DTHReversal.dto.ReversalOpenListResponse;
import com.bababansiwalanew.DTHReversal.ui.DTHReversalOpenTickets;
import com.bababansiwalanew.DTHReversal.ui.GenerateTicket;
import com.bababansiwalanew.DTHReversal.ui.TicketDetail;
import com.bababansiwalanew.Dashboard.dto.RechargeInfoResponse;
import com.bababansiwalanew.Dashboard.ui.Dashboard3;
import com.bababansiwalanew.Dashboard.ui.DownlineUserResponse;
import com.bababansiwalanew.Dashboard.ui.UserListReportResponse;
import com.bababansiwalanew.DisputeReport.dto.DisputeResponse;
import com.bababansiwalanew.DisputeReport.ui.DisputeReport;
import com.bababansiwalanew.DthBook.dto.GetDthBookingResponse;
import com.bababansiwalanew.FundRecReport.dto.FundRecResponse;
import com.bababansiwalanew.FundRecReport.ui.FundRecReport;
import com.bababansiwalanew.FundTransferStat.FundTransferStatement;
import com.bababansiwalanew.GiftCardServices.dto.GiftCardServicesResponse;
import com.bababansiwalanew.LedgerReport.dto.LedgerReportResponse;
import com.bababansiwalanew.LedgerReport.ui.LedgerReport;
import com.bababansiwalanew.Login.dto.GetOperatorResponse;
import com.bababansiwalanew.Login.dto.LoginResponse;
import com.bababansiwalanew.Login.ui.LoginScreen;
import com.bababansiwalanew.Login.ui.SignupScreen;
import com.bababansiwalanew.R;
import com.bababansiwalanew.ROffer.dto.ROfferResponse;
import com.bababansiwalanew.ROffer.ui.ROffer;
import com.bababansiwalanew.RechargeReport.dto.RechargeReportResponse;
import com.bababansiwalanew.RechargeReport.ui.RechargeReport;
import com.bababansiwalanew.Register.dto.RegisterResponse;
import com.bababansiwalanew.Register.ui.RegisterScreen;
import com.bababansiwalanew.SplashScreen.ui.SplashScreen;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookResponse;
import com.bababansiwalanew.UserDayBook.ui.UserDayBookScreen;
import com.bababansiwalanew.UserListActivity;
import com.bababansiwalanew.Util.dto.BankDetailResponse;
import com.bababansiwalanew.Util.dto.BankListResponse;

import com.bababansiwalanew.Util.dto.HelloWorldResponse;
import com.bababansiwalanew.Util.dto.KeyUpdateResponse;
import com.bababansiwalanew.Util.dto.NumberList;
import com.bababansiwalanew.Util.dto.NumberListResponse;
import com.bababansiwalanew.Util.dto.RechargeResponse;
import com.bababansiwalanew.Util.dto.ServicesResponse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public enum UtilMethods {

    INSTANCE;

    public static final String md5Convertor(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDeviceId(Context context) {


        return getIMEI(context);
    }

    public String getIMEI(Context context) {

        String deviceId = "";
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                String uniquePseudoID = "35" +
                        Build.BOARD.length() % 10 +
                        Build.BRAND.length() % 10 +
                        Build.DEVICE.length() % 10 +
                        Build.DISPLAY.length() % 10 +
                        Build.HOST.length() % 10 +
                        Build.ID.length() % 10 +
                        Build.MANUFACTURER.length() % 10 +
                        Build.MODEL.length() % 10 +
                        Build.PRODUCT.length() % 10 +
                        Build.TAGS.length() % 10 +
                        Build.TYPE.length() % 10 +
                        Build.USER.length() % 10;
                String serial = Build.getRadioVersion();
                String uniqueId = new UUID(uniquePseudoID.hashCode(), serial.hashCode()).toString();
                deviceId = uniqueId.substring(uniqueId.length() - 15);
            } else {
               /* if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                }*/
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    deviceId = telephonyManager.getImei();
                }

                if (deviceId == null || deviceId.isEmpty()) {
                    deviceId = telephonyManager.getDeviceId();
                }


            }

        } catch (SecurityException e) {
        } catch (NullPointerException npe) {
        } catch (Exception ex) {
        }

        return deviceId;
    }



    public boolean isNetworkAvialable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean isValidMobile(String mobile) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String mobilePattern = "^(?:0091|\\\\+91|0)[7-9][0-9]{9}$";
        String mobileSecPattern = "[7-9][0-9]{9}$";

        if (mobile.matches(mobilePattern) || mobile.matches(mobileSecPattern)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Matcher(Context context, String pinpasscode) {
        final Boolean[] tatu = {false};
        SharedPreferences myPreferences = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String pinPass = myPreferences.getString(ApplicationConstant.INSTANCE.PinPasscode, null);

//        if (pinpasscode.equalsIgnoreCase(pinPass)) {
//            return true;
//        } else {
//            return false;
//        }
        return tatu[0];
    }

    public void pinpasscode(Context context, String pinpasscode, boolean flagPinPasscodeFlag) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if (pinpasscode.equalsIgnoreCase("")) {
            editor.putBoolean(ApplicationConstant.INSTANCE.PinPasscodeFlag, flagPinPasscodeFlag);
        } else {
            editor.putString(ApplicationConstant.INSTANCE.PinPasscode, pinpasscode);
        }

        editor.commit();
    }

    public String fetchOperator(Context context, String param) {
        String paramReturn = "";
        String paramOpId = "";
        String paramZoneId = "";
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.numberListPref, null);

        NumberListResponse numberListResponse = new Gson().fromJson(response, NumberListResponse.class);
        try {
            for (NumberList numberList : numberListResponse.getNumberList()) {
                if (numberList.getNumber().equalsIgnoreCase(param)) {
                    paramReturn = numberList.getOperator();
                    paramOpId = numberList.getIReffOp();
                    paramZoneId = numberList.getIReffCircle();
                    break;
                }
            }
        } catch (Exception e) {
        }
        return paramReturn + "," + paramOpId + "," + paramZoneId;
    }

    public void ForgetPassword(final Context context, String mobile) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId + ((char) 160) + mobile;

        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.forgotPassword(appInfo);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                    // Log.e("response", "is : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("0")) {
                                dialogOk(context, context.getResources().getString(R.string.login_error_title), response.body().getMessage(), 2);
                            } else {
                                new AlertDialog.Builder(context)
                                        .setTitle(context.getResources().getString(R.string.successful_title))
                                        .setMessage(response.body().getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                // "OK" button was clicked

                                            }
                                        })
                                        .show();
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SubUserList(final Context context, final ProgressDialog loader, final String User, final String from, final ApiCallBack mApiCallBack) {
        final String User1 = "";
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("UserList ", appInfo + " ,pass : " + password + " ,search : " + "");
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<UserListReportResponse> call = git.UserListStatus(appInfo, password, User);
            call.enqueue(new Callback<UserListReportResponse>() {

                @Override
                public void onResponse(Call<UserListReportResponse> call, retrofit2.Response<UserListReportResponse> response) {
                    Log.e("UserList_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                UserListReportResponse userlists = new Gson().fromJson(new Gson().toJson(response.body()).toString(), UserListReportResponse.class);

                                if (userlists.getUser() != null && userlists.getUser().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                    if (from.equalsIgnoreCase("adapter")) {
                                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString(ApplicationConstant.INSTANCE.userUlist, new Gson().toJson(response.body()).toString());
                                        editor.commit();

                                        Intent transactionIntent = new Intent(context, UserListActivity.class);
                                        transactionIntent.putExtra("from", User);
                                        transactionIntent.putExtra("mobile", User);
                                        context.startActivity(transactionIntent);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);

                                    } else {
                                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString(ApplicationConstant.INSTANCE.userlist, new Gson().toJson(response.body()).toString());
                                        editor.commit();
                                    }


//
//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Oops...!  an error occurred", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                Error(context, response.body().getMessage());
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserListReportResponse> call, Throwable t) {
                    if (loader != null)
                        // Log.e("response", "error ");
                        if (loader.isShowing())
                            loader.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DownlineUser(final Context context, final ProgressDialog loader, String role, String userId, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password + " ,roleid : " + role);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DownlineUserResponse> call = git.DownlineUser(appInfo, password,/*role,*//*RoleId*/userId);
            call.enqueue(new Callback<DownlineUserResponse>() {

                @Override
                public void onResponse(Call<DownlineUserResponse> call, retrofit2.Response<DownlineUserResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DownlineUserResponse userlists = new Gson().fromJson(new
                                        Gson().toJson(response.body()).toString(), DownlineUserResponse.class);

                                if (userlists.getUser() != null && userlists.getUser().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Data not found !!", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                // Error(context,response.body().getMessage());
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DownlineUserResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());

            e.printStackTrace();
        }
    }

    public void DownlineUserStockNotTaken(final Context context, final ProgressDialog loader, String userId, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DownlineUserResponse> call = git.DownlineUserStockNotTaken(appInfo, password,/*role,*//*RoleId*/userId);
            call.enqueue(new Callback<DownlineUserResponse>() {

                @Override
                public void onResponse(Call<DownlineUserResponse> call, retrofit2.Response<DownlineUserResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DownlineUserResponse userlists = new Gson().fromJson(new
                                        Gson().toJson(response.body()).toString(), DownlineUserResponse.class);

                                if (userlists.getStockList() != null && userlists.getStockList().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Data not found !!", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                // Error(context,response.body().getMessage());
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DownlineUserResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());

            e.printStackTrace();
        }
    }

    public void DirectRetailerAndTeamRetailerBusiness(final Context context, final ProgressDialog loader, String userId, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DownlineUserResponse> call = git.BusinessList(appInfo, password,/*role,*//*RoleId*/userId);
            call.enqueue(new Callback<DownlineUserResponse>() {

                @Override
                public void onResponse(Call<DownlineUserResponse> call, retrofit2.Response<DownlineUserResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DownlineUserResponse userlists = new Gson().fromJson(new
                                        Gson().toJson(response.body()).toString(), DownlineUserResponse.class);

                                if (userlists.getBusinessList() != null && userlists.getBusinessList().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Data not found !!", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                // Error(context,response.body().getMessage());
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DownlineUserResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());

            e.printStackTrace();
        }
    }

    public void DownlineUserRechargeNotDone(final Context context, final ProgressDialog loader, String userId, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DownlineUserResponse> call = git.DownlineUserRechargeNotDone(appInfo, password,/*role,*//*RoleId*/userId);
            call.enqueue(new Callback<DownlineUserResponse>() {

                @Override
                public void onResponse(Call<DownlineUserResponse> call, retrofit2.Response<DownlineUserResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DownlineUserResponse userlists = new Gson().fromJson(new
                                        Gson().toJson(response.body()).toString(), DownlineUserResponse.class);

                                if (userlists.getRechargeList() != null && userlists.getRechargeList().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Data not found !!", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                // Error(context,response.body().getMessage());
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DownlineUserResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());

            e.printStackTrace();
        }
    }

    public void DonUser(final Context context, final ProgressDialog loader, String role, String userId, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String UserID = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password + " ,roleid : " + role);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DownlineUserResponse> call = git.GetDownlineUsersByRole(appInfo, password, role,/*RoleId*/UserID);
            call.enqueue(new Callback<DownlineUserResponse>() {

                @Override
                public void onResponse(Call<DownlineUserResponse> call, retrofit2.Response<DownlineUserResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DownlineUserResponse userlists = new Gson().fromJson(new
                                        Gson().toJson(response.body()).toString(), DownlineUserResponse.class);

                                if (userlists.getUser() != null && userlists.getUser().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Data not found !!", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);

                                //Error(context,response.body().getMessage());
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<DownlineUserResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());

            e.printStackTrace();
        }
    }

    public void UserRole(final Context context, final ProgressDialog loader, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String UId = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("Userrole ", appInfo + " ,pass : " + password + " ,UserID : " + " UserID");
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<UserListReportResponse> call = git.UserRole(appInfo, password, UId /*"2"*/);
            call.enqueue(new Callback<UserListReportResponse>() {

                @Override
                public void onResponse(Call<UserListReportResponse> call, retrofit2.Response<UserListReportResponse> response) {
                    Log.e("Userrole_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                UserListReportResponse userlists = new Gson().fromJson(new Gson().toJson(response.body()).toString(), UserListReportResponse.class);

                                if (userlists.getRoleLIst() != null && userlists.getRoleLIst().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }


//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Oops...!  an error occurred", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                dialogOk(context, context.getResources().getString(R.string.attention_error_title), response.body().getMessage(), 2);

                                // Error(context,response.body().getMessage());
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserListReportResponse> call, Throwable t) {
                    if (loader != null)
                        Log.e("response", "error " + t.getMessage());
                    if (loader.isShowing())
                        loader.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("response", "error e " + e.getMessage());
        }
    }

    public void UserList(final Context context, final ProgressDialog loader, final String User, final String from, final ApiCallBack mApiCallBack) {
        final String User1 = "";
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("UserList ", appInfo + " ,pass : " + password + " ,search : " + "");
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<UserListReportResponse> call = git.UserListStatus(appInfo, password, User1);
            call.enqueue(new Callback<UserListReportResponse>() {

                @Override
                public void onResponse(Call<UserListReportResponse> call, retrofit2.Response<UserListReportResponse> response) {
                    Log.e("UserList_response", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null)
                        if (loader.isShowing()) {
                            loader.dismiss();
                        }
                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                UserListReportResponse userlists = new Gson().fromJson(new Gson().toJson(response.body()).toString(), UserListReportResponse.class);

                                if (userlists.getUser() != null && userlists.getUser().size() > 0) {
//                                    ActivityActivityMessage activityActivityMessage =
//                                            new ActivityActivityMessage("userList", "" + userlists);
//                                    GlobalBus.getBus().post(activityActivityMessage);

                                    if (mApiCallBack != null) {
                                        mApiCallBack.onSucess(response.body());
                                    }
                                    if (from.equalsIgnoreCase("adapter")) {
                                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString(ApplicationConstant.INSTANCE.userUlist, new Gson().toJson(response.body()).toString());
                                        editor.commit();

                                        Intent transactionIntent = new Intent(context, UserListActivity.class);
                                        transactionIntent.putExtra("from", User);
                                        transactionIntent.putExtra("mobile", User);
                                        context.startActivity(transactionIntent);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);

                                    } else {
                                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString(ApplicationConstant.INSTANCE.userlist, new Gson().toJson(response.body()).toString());
                                        editor.commit();
                                    }

//                                if (from.equalsIgnoreCase("Search")) {
//                                    ((UserListActivity) context).UserList(new Gson().toJson(response.body()).toString());
//                                } else {
//                                    Intent transactionIntent = new Intent(context, UserListActivity.class);
//                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
//                                    context.startActivity(transactionIntent);
//                                }
                                } else {
                                    Toast.makeText(context, "Oops...!  an error occurred", Toast.LENGTH_SHORT).show();
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    // UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.transaction_empty_message));
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                Error(context, response.body().getMessage());
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } else {
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserListReportResponse> call, Throwable t) {
                    if (loader != null)
                        // Log.e("response", "error ");
                        if (loader.isShowing())
                            loader.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RechargePlans(final Activity context, String opId, String opZone, final String part1, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);

        // Log.e("op", "opId : " + opId.trim() + " ,opZone : " + opZone.trim());
        try {
            EndPointInterface git = ApiClientPlans.getClient().create(EndPointInterface.class);
            Call<ResponsePlan> call = git.RechargePlans(opId.trim(), opZone.trim(), "");
            call.enqueue(new Callback<ResponsePlan>() {
                @Override
                public void onResponse(Call<ResponsePlan> call, retrofit2.Response<ResponsePlan> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());

                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getOperatorDetail() != null && response.body().getOperatorDetail().size() > 0
                                    && response.body().getOperatorDetail().get(0).getOperatorName() != null
                                    && response.body().getOperatorDetail().get(0).getOperatorName().length() > 0) {
                                Intent browseIntent = new Intent(context, BrowsePlanScreen.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                browseIntent.putExtra("operatorName", "" + part1);
                                context.startActivity(browseIntent);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "No data found!", 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsePlan> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterLogintoPreviousWindowFund(Context context, String from, File selectedImageFile, String MobileNo, String Amount, String accountNumber, String transactionId, String mobileType, String remark, String bankname, String amountInwords, String file, final ProgressDialog mProgressDialog, final Button payButton) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,
                context.MODE_PRIVATE);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String fundType = "1";

        if (mobileType.equalsIgnoreCase("utility")) {
            fundType = "2";
        } else {
            fundType = "1";
        }

        if (UMobile != null && UMobile.length() > 0) {
            if (from.equalsIgnoreCase("fundTransfer")) {
                UtilMethods.INSTANCE.FundTransfer(context, MobileNo, Amount, fundType, mProgressDialog, payButton);
            } else {
                //  UtilMethods.INSTANCE.PaymentRequest(context, Amount, accountNumber, transactionId, fundType,remark,bankname,amountInwords,file, mProgressDialog,payButton);
                //   UtilMethods.INSTANCE.PaymentRequestNew(context, selectedImageFile,Amount, accountNumber, transactionId, fundType,remark,bankname,amountInwords,file, mProgressDialog,payButton);

            }
        } else {
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            Intent loginIntent = new Intent(context, LoginScreen.class);
            context.startActivity(loginIntent);
        }
    }

    public void afterLogintoPreviousWindow(Context context, String MobileNo, String Amount, String OpID, String STDCode, boolean flagElectricity, final String paramValue1, final String paramValue2, final String paramValue3, final String paramValue4, final ProgressDialog mProgressDialog, final TextView payButton) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,
                context.MODE_PRIVATE);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        if (UMobile != null && UMobile.length() > 0) {
            if (flagElectricity) {
                if ((paramValue1 != null && paramValue1.length() > 0) || (paramValue2 != null && paramValue2.length() > 0)
                        || (paramValue3 != null && paramValue3.length() > 0) || (paramValue4 != null && paramValue4.length() > 0)) {

                    Log.e("playstore", "1");
                    UtilMethods.INSTANCE.ElectricityExtraParam(context, paramValue1, paramValue2, paramValue3, paramValue4, MobileNo, Amount, OpID, mProgressDialog, payButton);

                } else {
                    Log.e("playstore", "2");
                    UtilMethods.INSTANCE.rechargeParams(context, MobileNo, Amount, OpID, STDCode, mProgressDialog, payButton, "electric");
                }
            } else {
                Log.e("playstore", "3");
                UtilMethods.INSTANCE.rechargeParams(context, MobileNo, Amount, OpID, STDCode, mProgressDialog, payButton, "");
            }

        } else {
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            Intent loginIntent = new Intent(context, LoginScreen.class);
            context.startActivity(loginIntent);
        }
    }

    public String ElectricityExtraParam(final Context context, final String paramValue1, final String paramValue2, final String paramValue3, final String paramValue4, final String MobileNo, final String Amount, final String OpID, final ProgressDialog mProgressDialog, final TextView payButton) {

        ////////////////////////////////////////////////////////////////////////////////
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.extra_param_electricity, null);

        final TextInputLayout param1Layout, param2Layout, param3Layout, param4Layout;
        final EditText param1, param2, param3, param4;

        param1Layout = (TextInputLayout) view.findViewById(R.id.param1Layout);
        param2Layout = (TextInputLayout) view.findViewById(R.id.param2Layout);
        param3Layout = (TextInputLayout) view.findViewById(R.id.param3Layout);
        param4Layout = (TextInputLayout) view.findViewById(R.id.param4Layout);

        param1 = (EditText) view.findViewById(R.id.param1);
        param2 = (EditText) view.findViewById(R.id.param2);
        param3 = (EditText) view.findViewById(R.id.param3);
        param4 = (EditText) view.findViewById(R.id.param4);

        final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
        final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

        if (paramValue1 != null && paramValue1.length() > 0) {
            param1Layout.setVisibility(View.VISIBLE);
            param1.setHint(paramValue1);
        } else {
            param1Layout.setVisibility(View.GONE);
        }
        if (paramValue2 != null && paramValue2.length() > 0) {
            param2Layout.setVisibility(View.VISIBLE);
            param2.setHint(paramValue2);
        } else {
            param2Layout.setVisibility(View.GONE);
        }
        if (paramValue3 != null && paramValue3.length() > 0) {
            param3Layout.setVisibility(View.VISIBLE);
            param3.setHint(paramValue3);
        } else {
            param3Layout.setVisibility(View.GONE);
        }
        if (paramValue4 != null && paramValue4.length() > 0) {
            param4Layout.setVisibility(View.VISIBLE);
            param4.setHint(paramValue4);
        } else {
            param4Layout.setVisibility(View.GONE);
        }

        final Dialog dialog = new Dialog(context);

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

                int flag = 0;
                if (paramValue4 != null && paramValue4.length() > 0) {
                    if (param4.getText() != null && param4.getText().toString().trim().length() > 0) {
                        param4Layout.setErrorEnabled(false);
                    } else {
                        param4.setError(paramValue4 + " cannot be empty !");
                        param4.requestFocus();
                        flag++;
                    }
                }

                if (paramValue3 != null && paramValue3.length() > 0) {
                    if (param3.getText() != null && param3.getText().toString().trim().length() > 0) {
                        param3Layout.setErrorEnabled(false);
                    } else {
                        param3.setError(paramValue3 + " cannot be empty !");
                        param3.requestFocus();
                        flag++;
                    }
                }
                if (paramValue2 != null && paramValue2.length() > 0) {
                    if (param2.getText() != null && param2.getText().toString().trim().length() > 0) {
                        param2Layout.setErrorEnabled(false);
                    } else {
                        param2.setError(paramValue2 + " cannot be empty !");
                        param2.requestFocus();
                        flag++;
                    }
                }

                // ..
                if (paramValue1 != null && paramValue1.length() > 0) {
                    if (param1.getText() != null && param1.getText().toString().trim().length() > 0) {
                        param1Layout.setErrorEnabled(false);
                    } else {
                        param1.setError(paramValue1 + " cannot be empty !");
                        param1.requestFocus();
                        flag++;
                    }
                }

                if (flag == 0) {

                    String stdCode = "" + param1.getText().toString().trim() + "," + "" + param2.getText().toString().trim() + "," +
                            "" + param3.getText().toString().trim() + "," + "" + param4.getText().toString().trim();

                    UtilMethods.INSTANCE.rechargeParams(context, MobileNo, Amount, OpID, stdCode, mProgressDialog, payButton, "electric");
                }
            }
        });
        dialog.show();
        ////////////////////////////////////////////////////////////////////////////////

        return "";
    }

    public void GetNumberList(final Context context, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;

        Log.e("appInfo", "appInfo : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<NumberListResponse> call = git.GetNumberList(appInfo);
            call.enqueue(new Callback<NumberListResponse>() {
                @Override
                public void onResponse(Call<NumberListResponse> call, retrofit2.Response<NumberListResponse> response) {
                    Log.e("response", "isGetNumberList :  " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.setNumberList(context, new Gson().toJson(response.body()).toString());


                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                //  UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                //          response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {
                            Log.e("response", "e " + e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<NumberListResponse> call, Throwable t) {
                    Log.e("response", "t " + t.getMessage());
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            Log.e("response", "end  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void BankDetail(final Context context, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("appInfo", "appInfo : " + appInfo);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BankDetailResponse> call = git.BankDetail(appInfo, password);
            call.enqueue(new Callback<BankDetailResponse>() {
                @Override
                public void onResponse(Call<BankDetailResponse> call, retrofit2.Response<BankDetailResponse> response) {
                    // Log.e("response", "is :  " + new Gson().toJson(response.body()).toString());

                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.setBankDetailList(context, new Gson().toJson(response.body()).toString());

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                // UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                //         response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BankDetailResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetBeneficiary(final Context context, String senderNumber, final ProgressDialog mProgressDialog , final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BeneficiaryResponse> call = git.GetBeneficiary(appInfo, password, senderNumber);
            call.enqueue(new Callback<BeneficiaryResponse>() {
                @Override
                public void onResponse(Call<BeneficiaryResponse> call, retrofit2.Response<BeneficiaryResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            ActivityActivityMessage activityActivityMessage =
                                    new ActivityActivityMessage("beneUpdate");
                            GlobalBus.getBus().post(activityActivityMessage);

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus()==1) {
                                if (apiCallBackTwoMethod!=null){
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }

                            }  else {

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BeneficiaryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void VerifyBeneficiary(final Context context, final String remiterid, final String senderName,final String ifsc,final String senderNumber, final String bankAccount, final String bankCode,
                                  final ProgressDialog mProgressDialog, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password + " , bankAccount : " + bankAccount
                + " , bankCode : " + bankCode);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.VerifyBeneficiary(appInfo, password, senderNumber, bankAccount, bankCode,
                    remiterid, "", "", "", senderName, ifsc, "", "");
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body().getMessage()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus()==1) {
                                apiCallBackTwoMethod.onSucess(response.body());


                            } else {

                                apiCallBackTwoMethod.onError(""+response.body().getMessage()+"");


                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError("OOPs!!   Failed to verify !!!");
                        }

                    }
                    else {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        apiCallBackTwoMethod.onError("OOPs!!   Failed to verify !!!1");


                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    apiCallBackTwoMethod.onError("OOPs!!   Failed to verify !!!"+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }

            apiCallBackTwoMethod.onError("OOPs!!   Failed to verify !!!"+e.getMessage());
        }
    }

    public void AddBeneficiary(final Context context, final String remiterid, final String senderNumber, final String beneName, final String beneMobileNumber
            , final String accountNumber, final String bankIFSC, final String validationStatus,
                               final String bankId,final String address,final String pincode,
                               final ProgressDialog mProgressDialog, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,senderNumber : " + senderNumber + " , pass : " + password + " , beneName : " + beneName
                + " , beneMobileNumber : " + beneMobileNumber + " , accountNumber : " + accountNumber +
                " , bankIFSC : " + bankIFSC + " , validationStatus : " + validationStatus + " , bankId : " + bankId);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.AddBeneficiary(appInfo, password, senderNumber, beneName, beneMobileNumber,
                    accountNumber, bankIFSC, "1", bankId, address,pincode,bankIFSC,remiterid);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus() == 1) {

                                UtilMethods.INSTANCE.setBeneficiaryList(context, "");
                                UtilMethods.INSTANCE.GetBeneficiary(context, senderNumber, mProgressDialog,null);


                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + senderNumber, "beneAdded");
                                GlobalBus.getBus().post(activityActivityMessage);
                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage().toString(), 2);
                            }
                        } catch (Exception e) {
                            dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    "Please try again later!"+e.getMessage(), 2);
                        }
                    }
                    else{
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                "Please try again later.", 2);
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Please try again later.."+t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
            dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                    "Please try again later."+e.getMessage(), 2);
            e.printStackTrace();
        }
    }

    public void CreateSender(final Context context, final String senderNumber, final String senderName, final String pincode,
                             final String address, final String dob, final String otp, final String otpReff, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.CreateSender(appInfo, password, senderNumber, senderName, "",
                    pincode, dob, address, otp,otpReff);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus() == 1) {
                                apiCallBackTwoMethod.onSucess(response.body());
//                                FragmentActivityMessage activityActivityMessage =
//                                        new FragmentActivityMessage("" + senderNumber + ","
//                                                + senderName, "createSender");
//                                GlobalBus.getBus().post(activityActivityMessage);

                            } else if (response.body().getStatus() != null &&
                                    response.body().getStatus() == 0) {
                                apiCallBackTwoMethod.onError(response.body().getMessage().toString());


                            } else {
                                apiCallBackTwoMethod.onError(response.body().getMessage());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError("Something went wrong, Please try after some time");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            apiCallBackTwoMethod.onError("Something went wrong, Please try after some time");
        }
    }


    /* public void CreateSender(final Context context, final String senderNumber, final String senderName,final String pincode,
                             final String address, final String dob, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.CreateSender(appInfo, password, senderNumber, senderName,"",pincode,dob,address,"");
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus()==1) {
                                apiCallBackTwoMethod.onSucess(response.body());
//                                FragmentActivityMessage activityActivityMessage =
//                                        new FragmentActivityMessage("" + senderNumber + ","
//                                                + senderName, "createSender");
//                                GlobalBus.getBus().post(activityActivityMessage);

                            }  else if (response.body().getStatus() != null &&
                                    response.body().getStatus()==0) {
                                apiCallBackTwoMethod.onError(response.body().getOtpReff().toString());


                            }else {
                                apiCallBackTwoMethod.onError("");

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError("");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            apiCallBackTwoMethod.onError("");
        }
    }

   */ public void VerifySender(final Context context, final String senderNumber, final String senderName, final String otp,
                             final String otpref , final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        // Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.VerifySender(appInfo, password, senderNumber, otp,otpref,senderName,"","");
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus()==1) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            } else {

                                if (response.body().getStatus() != null &&
                                        response.body().getStatus()==0) {
                                    apiCallBackTwoMethod.onError(response.body().getMessage().toString());
                                }
                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onSucess("Failed to Verify Sender");
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onSucess("Failed to Verify Sender");
                }
            });

        } catch (Exception e) {
            apiCallBackTwoMethod.onSucess("Failed to Verify Sender");
            e.printStackTrace();
        }
    }
    public void dialogResponse(final Context context, String title, final String status, final String message, final int flag) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewTemp = inflater.inflate(R.layout.dialog_show_response, null);

        final ImageView cancelButton =  viewTemp.findViewById(R.id.cancelButton);
        final ImageView ivImage = viewTemp.findViewById(R.id.ivImage);
        final  TextView tvtitle = viewTemp.findViewById(R.id.title);
        final  TextView tvmessage = viewTemp.findViewById(R.id.message);
        final  TextView ok =   viewTemp.findViewById(R.id.ok);
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(viewTemp);
        tvtitle.setText("" + title);
        tvmessage.setText("" + message);
        if (status.equalsIgnoreCase("1")){
            ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.success));
        }else {
            ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_baseline_close_red_24));
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();

    }


    public void BankList(final Context context, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BankListResponse> call = git.BankList(appInfo, password);
            call.enqueue(new Callback<BankListResponse>() {
                @Override
                public void onResponse(Call<BankListResponse> call, retrofit2.Response<BankListResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus().equalsIgnoreCase("1")) {

                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }
                                UtilMethods.INSTANCE.setBankList(context, new Gson().toJson(response.body()).toString());

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BankListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void GetSender(final Context context, final String senderNumber, final ProgressDialog mProgressDialog, final ApiCallBackDMRMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.GetSender(appInfo, password, senderNumber);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getIsBiomatricRequired() ||
                                    response.body().isSenderRegOTPRequired()
                                    || response.body().isOTPGenerated()
                                    || response.body().isSenderRegRequired()
                            ) {

                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onAuthRequired(response.body());
                                }
                            } else if (response.body().getStatus() != null &&
                                    response.body().getStatus() == (1) && response.body().getData()!=null) {

                                UtilMethods.INSTANCE.BankList(context, null);
                                UtilMethods.INSTANCE.setSenderNumber(context, senderNumber);
                                UtilMethods.INSTANCE.setSenderInfo(context,
                                        new Gson().toJson(response.body().getData().getName()).toString(), senderNumber, true, mProgressDialog);

                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }
                            }
                        } catch (Exception e) {
                            if (apiCallBackTwoMethod != null) {
                                apiCallBackTwoMethod.onError(""+e.getMessage()+"  "+response.body().getMessage().toString());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    if (apiCallBackTwoMethod != null) {
                        apiCallBackTwoMethod.onError("Failed to Login..");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteBeneficiary(final Context context, final String remiterid, final String senderNumber, final String recipientId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.DeleteBeneficiary(appInfo, password, senderNumber, recipientId, remiterid, "", "", "");
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getDMR().getTABLE().getRESPONSESTATUS() != null &&
                                    response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.setSenderNumber(context, senderNumber);
                                UtilMethods.INSTANCE.setSenderInfo(context, new Gson().toJson(response.body().getDMR().getTABLE()).toString(), senderNumber, true, mProgressDialog);

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + senderNumber, "senderLogin");
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else {

                                if (response.body().getDMR().getTABLE().getRESPONSESTATUS() != null &&
                                        response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("0") &&
                                        response.body().getDMR().getTABLE().getMESSAGE() != null &&
                                        response.body().getDMR().getTABLE().getMESSAGE().equalsIgnoreCase("Sender not found....Please Create Sender or verify sender !!")) {

                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SendMoney(final Context context, final String _BeneficiaryMobile, final String senderNumber, final String account, final String amount,
                          final String recipientId, final String channel, String benename, String bank,
                          String name, String _ChargeAmount,
                          String comm, String pinPass, String ifsc, String _BankId,String otpReff, String otp,
                          final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , account : " + account + " , amount : " + amount
                + " , recipientId : " + recipientId + " , channel : " + channel);

        Log.v("_BankId", "" + _BankId.toString());
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<SendMoneyResponse> call = git.SendMoney(appInfo, password, senderNumber, account, amount,
                    recipientId, channel,
                    benename, name, pinPass, ifsc,bank,
                    (_BankId.equalsIgnoreCase("null")) ? "" : _BankId,otpReff,otp);

            call.enqueue(new Callback<SendMoneyResponse>() {
                @Override
                public void onResponse(Call<SendMoneyResponse> call, retrofit2.Response<SendMoneyResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus() == 1) {

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getMessage(), 4);
                            } else {


                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 4);

                            }
                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 4);
                        }
                    }
                }

                @Override
                public void onFailure(Call<SendMoneyResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Failed to Money Transfer !! ", 4);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                    "Failed to Money Transfer !! ", 4);
        }
    }


    public void SendMoneyOTP(final Context context, final String _BeneficiaryMobile, final String senderNumber, final String account, final String amount,
                             final String recipientId, final String channel, String benename, String bank,
                             String name, String _ChargeAmount,
                             String comm, String pinPass, String ifsc, String _BankId,
                             final ProgressDialog mProgressDialog , final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , account : " + account + " , amount : " + amount
                + " , recipientId : " + recipientId + " , channel : " + channel+" , _BankId : " + _BankId);

        Log.v("_BankId", "" + _BankId.toString());//,
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<SendMoneyResponse> call = git.SendMoneyOTP(appInfo, password, senderNumber, account, amount,
                    recipientId,  channel,bank,
                    benename, name,  pinPass, (_BankId.equalsIgnoreCase("null")) ? "" : _BankId, ifsc,_BeneficiaryMobile);

            call.enqueue(new Callback<SendMoneyResponse>() {
                @Override
                public void onResponse(Call<SendMoneyResponse> call, retrofit2.Response<SendMoneyResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus() == 1) {
                                apiCallBackTwoMethod.onSucess(response.body());
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
//                                        response.body().getMessage(), 4);
                            } else {
                                apiCallBackTwoMethod.onError(response.body().getMessage());



                            }
                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 4);
                        }
                    }
                }

                @Override
                public void onFailure(Call<SendMoneyResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "OTP verification failed !! ", 4);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                    "OTP Verification failed !! ", 4);
        }
    }

    /*
    public void GetSender(final Context context, final String senderNumber, final ProgressDialog mProgressDialog, final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.GetSender(appInfo, password, senderNumber);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus() == (1)) {

                                UtilMethods.INSTANCE.BankList(context, null);
                                UtilMethods.INSTANCE.setSenderNumber(context, senderNumber);
                                UtilMethods.INSTANCE.setSenderInfo(context, new Gson().toJson(response.body().getData().getName()).toString(), senderNumber, true, mProgressDialog);

                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }
                            } else {
                                if (apiCallBackTwoMethod != null) {
                                    apiCallBackTwoMethod.onError(response.body().getMessage().toString());
                                }

                            }
                        } catch (Exception e) {
                            if (apiCallBackTwoMethod != null) {
                                apiCallBackTwoMethod.onError(response.body().getMessage().toString());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    if (apiCallBackTwoMethod != null) {
                        apiCallBackTwoMethod.onError("Failed to Login..");
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteBeneficiary(final Context context, final String remiterid, final String senderNumber, final String recipientId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.DeleteBeneficiary(appInfo, password, senderNumber, recipientId,remiterid,"","","");
              call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getDMR().getTABLE().getRESPONSESTATUS() != null &&
                                    response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.setSenderNumber(context, senderNumber);
                                UtilMethods.INSTANCE.setSenderInfo(context, new Gson().toJson(response.body().getDMR().getTABLE()).toString(), senderNumber, true, mProgressDialog);

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + senderNumber, "senderLogin");
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else {

                                if (response.body().getDMR().getTABLE().getRESPONSESTATUS() != null &&
                                        response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("0") &&
                                        response.body().getDMR().getTABLE().getMESSAGE() != null &&
                                        response.body().getDMR().getTABLE().getMESSAGE().equalsIgnoreCase("Sender not found....Please Create Sender or verify sender !!")) {

                                }
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SendMoney(final Context context, final String senderNumber, final String account, final String amount,
                          final String recipientId, final String channel, String benename, String bank,
                          String name, String _ChargeAmount,
                          String comm, String pinPass, String ifsc,
                          final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        *//*Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , account : " + account + " , amount : " + amount
                + " , recipientId : " + recipientId + " , channel : " + channel);*//*

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<SendMoneyResponse> call = git.SendMoney(appInfo, password, senderNumber, account, amount,
                    recipientId, channel,
                    benename,bank,name,_ChargeAmount,comm,pinPass,ifsc);

            call.enqueue(new Callback<SendMoneyResponse>() {
                @Override
                public void onResponse(Call<SendMoneyResponse> call, retrofit2.Response<SendMoneyResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus() != null &&
                                    response.body().getStatus()==1) {

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getMessage(), 4);
                            } else {


                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 4);

                            }
                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 4);
                        }
                    }
                }

                @Override
                public void onFailure(Call<SendMoneyResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Failed to Money Transfer !! ", 4);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                    "Failed to Money Transfer !! ", 4);
        }
    }*/
    public void DMRTransaction(final Context context, String fromDate, String toDate, String account, final ProgressDialog mProgressDialog,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DMRReportResponse> call = git.DMRTransaction(appInfo, password, fromDate, toDate, account);
            call.enqueue(new Callback<DMRReportResponse>() {
                @Override
                public void onResponse(Call<DMRReportResponse> call, retrofit2.Response<DMRReportResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getStatus().equalsIgnoreCase("1")) {

                                if (apiCallBackTwoMethod!=null) {
                                    apiCallBackTwoMethod.onSucess(response.body());
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }   else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                            if (apiCallBackTwoMethod!=null) {
                                apiCallBackTwoMethod.onError("Something went wrong !!"+e.getMessage());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<DMRReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (apiCallBackTwoMethod!=null) {
                        apiCallBackTwoMethod.onError("Something went wrong !!"+t.getMessage());

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    Shopping
public void GetProductCategory(final Context context,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.GetProductCategory(appInfo, password );
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }

public void GetSubProductCategory(final Context context, String category,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.GetSubProductCategory(appInfo, password,category );
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
public void GetProductList(final Context context, String category, String Subcategory,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.GetEcommerceProduct(appInfo, password,category ,Subcategory);
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
public void AddToCart(final Context context, String productID, String Count,String amount, String discountamt,
                      String finalamt,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.AddToCart(appInfo, password,productID ,Count.trim(),amount.trim(), discountamt.trim() ,
                    finalamt.trim());
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
public void BuyProductDetail(final Context context,  String fromDate, String toDate, final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.BuyProductDetail(appInfo, password ,fromDate,toDate);
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());

        }
    }
    public void GetCartList(final Context context,   final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.AddToCartDetail(appInfo, password );
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
    public void BuyProduct(final Context context,   final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.BuyProduct(appInfo, password );
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body().getMessage());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
    public void ShippingAddress(final Context context, String Address,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.ShippingAddress(appInfo, password,Address );
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
public void GetHomeProductList(final Context context,    final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.GetEcommerceDisplayHomeProduct(appInfo, password);
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }
public void GetEcommerceProductDetail(final Context context, String id,  final ApiCallBackTwoMethod apiCallBackTwoMethod) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("here", "appInfo : " + appInfo + " ,numbr : " + senderNumber + " , pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CatgoryResponse> call = git.GetEcommerceProductDetail(appInfo, password,id);
            call.enqueue(new Callback<CatgoryResponse>() {
                @Override
                public void onResponse(Call<CatgoryResponse> call, retrofit2.Response<CatgoryResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getResponsestatus() != null &&
                                    response.body().getResponsestatus() .equalsIgnoreCase("1")) {
                                apiCallBackTwoMethod.onSucess(response.body());
                            }   else {
                                apiCallBackTwoMethod.onError(""+response.body());

                            }
                        } catch (Exception e) {
                            apiCallBackTwoMethod.onError(""+response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CatgoryResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    apiCallBackTwoMethod.onError(""+t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("Exception","Exception  "+e.getMessage());
            apiCallBackTwoMethod.onError(""+e.getMessage());
        }
    }


    public void GetViewBill(final Context context, final String mobileNo, final String opId, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        String outletId = myPrefs.getString(ApplicationConstant.INSTANCE.outletid, null);
        Log.e("GetViewBill", " appInfo : " + appInfo + "   " + password + " password : " + mobileNo + " No : " + opId + " opId : " + outletId + " outletId : ");
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeInfoResponse> call = git.GetViewBill(appInfo, password, mobileNo, opId, outletId);
            call.enqueue(new Callback<RechargeInfoResponse>() {
                @Override
                public void onResponse(Call<RechargeInfoResponse> call, final retrofit2.Response<RechargeInfoResponse> response) {
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }
                            Log.e("GetViewBill ", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (response.body().getRESPONSESTATUS() != null &&
                                    response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString(ApplicationConstant.INSTANCE.reffCode, response.body().getReffid().trim());
                                editor.commit();
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.broadband_customer_info, null);

                                final TextView customername = (TextView) view.findViewById(R.id.customername);
                                final TextView Due_Amount = (TextView) view.findViewById(R.id.Due_Amount);
                                final TextView Due_Date = (TextView) view.findViewById(R.id.Due_Date);
                                final TextView Bill_Number = (TextView) view.findViewById(R.id.Bill_Number);
                                final TextView bill_date = (TextView) view.findViewById(R.id.bill_date);
//                                final TextView newamount = (TextView) view.findViewById(R.id.newamount);
//                                final TextView billdate = (TextView) view.findViewById(R.id.billdate);
//                                final TextView billamount = (TextView) view.findViewById(R.id.billamount);
                                final Button done = (Button) view.findViewById(R.id.done);

                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);

                                customername.setText("" + response.body().getCustomername().toString());
                                Due_Amount.setText("" + response.body().getDueamount().toString());
                                Due_Date.setText("" + response.body().getDuedate().toString());
                                Bill_Number.setText("" + response.body().getBillnumber().toString());
                                bill_date.setText("" + response.body().getBilldate().toString());
//                                newamount.setText(""+response.body().getNewamount());
//                                billdate.setText(""+response.body().getBilldate());
//                                billamount.setText(""+response.body().getBillamount());

                                done.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FragmentActivityMessage fragmentActivityMessage =
                                                new FragmentActivityMessage("fetchBillReffId", "" + response.body().getReffid().trim() + "," + response.body().getDueamount().toString());
                                        GlobalBus.getBus().post(fragmentActivityMessage);
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }/*else {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title) ,
//                                        "Wrong Customer id Or Operator", 2);
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title)
                                        ,response.body().getMessage().toString(), 2);
                            }*/
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<RechargeInfoResponse> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("GetViewBill", "error " + t.getMessage());
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Wrong Customer id Or Operator", 2);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetElectricityInfo(final Context context, final TextView amount, final String mobileNo, final String opId, final ProgressDialog mProgressDialog) {

        Log.e("GetElectricityInfo", "mobileNo : " + mobileNo + ", opId: " + opId);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            // Call<RechargeInfoResponse> call = git.GetElectricityInfo(mobileNo, opId);
            Call<ElectricInfoResponse> call = git.ViewElectricityBill(mobileNo, opId);
            call.enqueue(new Callback<ElectricInfoResponse>() {
                @Override
                public void onResponse(Call<ElectricInfoResponse> call, final retrofit2.Response<ElectricInfoResponse> response) {

                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    if (response.body() != null) {
                        try {

                            Log.e("GetElectricityInfo ", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (response.body().getStatuscode() != null &&
                                    response.body().getStatuscode() == 1) {

                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.customer_info, null);

                                final TextView customername = (TextView) view.findViewById(R.id.customername);
                                final TextView customernumber = (TextView) view.findViewById(R.id.customernumber);
                                final TextView customeraddress = (TextView) view.findViewById(R.id.customeraddress);
                                final TextView ero = (TextView) view.findViewById(R.id.ero);
                                final TextView oldamount = (TextView) view.findViewById(R.id.oldamount);
                                final TextView newamount = (TextView) view.findViewById(R.id.newamount);
                                final TextView billdate = (TextView) view.findViewById(R.id.billdate);
                                final TextView billamount = (TextView) view.findViewById(R.id.billamount);
                                final Button done = (Button) view.findViewById(R.id.done);

                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);
                                customername.setText("" + response.body().getbBPSResponse().getCustomerName());
                                if (response.body().getbBPSResponse().getBillNumber() != null && !response.body().getbBPSResponse().getBillNumber().toString().equals("null")) {
                                    customernumber.setText("" + response.body().getbBPSResponse().getBillNumber().toString());

                                }
                                if (response.body().getbBPSResponse().getBillDate() != null && !response.body().getbBPSResponse().getBillDate().toString().equals("null")) {
                                    billdate.setText("" + response.body().getbBPSResponse().getBillDate());

                                }
                                if (response.body().getbBPSResponse().getBillPeriod() != null && !response.body().getbBPSResponse().getBillPeriod().toString().equals("null")) {
                                    customeraddress.setText("" + response.body().getbBPSResponse().getBillPeriod().toString());

                                }
                                if (response.body().getbBPSResponse().getBillamount() != null && !response.body().getbBPSResponse().getBillamount().toString().equals("null")) {
                                    billamount.setText("" + response.body().getbBPSResponse().getBillamount() + "");

                                }
                                if (response.body().getbBPSResponse().getDueDate() != null && !response.body().getbBPSResponse().getDueDate().toString().equals("null")) {
                                    newamount.setText("" + response.body().getbBPSResponse().getDueDate() + "");

                                }
                                if (response.body().getbBPSResponse().getRefferenceID() != null &&
                                        !response.body().getbBPSResponse().getRefferenceID().toString().equals("null")) {
                                    ero.setText("" + response.body().getbBPSResponse().getRefferenceID());

                                }
                                if (response.body().getbBPSResponse().getAmount() != null &&
                                        !response.body().getbBPSResponse().getAmount().toString().equals("null")) {
                                    oldamount.setText("" + response.body().getbBPSResponse().getAmount());

                                }


                                done.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        amount.setText("" + String.valueOf(response.body().getbBPSResponse().getBillamount()) + "");
                                        /*FragmentActivityMessage fragmentActivityMessage =
                                                new FragmentActivityMessage("BillAmount",""+response.body().getBillamount().replace("Rs.","").replace("\u00C2","").replace(" ","").replace(".0","").trim());
                                        GlobalBus.getBus().post(fragmentActivityMessage);*/
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();

                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getbBPSResponse().getMsg().toString(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<ElectricInfoResponse> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("GetElectricityInfo", "error " + t.getMessage());
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Wrong Customer id Or Operator", 2);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        }
    }

    public void GetDTHInfo(final Context context, final String mobileNo, final String opId, final ProgressDialog mProgressDialog) {

        Log.e("GetElectricityInfo", "mobileNo : " + mobileNo + ", opId: " + opId);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeInfoResponse> call = git.GetDTHInfo(mobileNo, opId);
            call.enqueue(new Callback<RechargeInfoResponse>() {
                @Override
                public void onResponse(Call<RechargeInfoResponse> call, final retrofit2.Response<RechargeInfoResponse> response) {
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }
                            Log.e("GetElectricityInfo ", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus().equalsIgnoreCase("success")) {

                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.dth_info, null);

                                final TextView customername = (TextView) view.findViewById(R.id.customername);
                                final TextView customernumber = (TextView) view.findViewById(R.id.customernumber);
                                final TextView customeraddress = (TextView) view.findViewById(R.id.customeraddress);
                                final TextView customerid = (TextView) view.findViewById(R.id.customerid);
                                final TextView customerbalance = (TextView) view.findViewById(R.id.customerbalance);
                                final TextView customerpackage = (TextView) view.findViewById(R.id.customerpackage);
                                final TextView customerexpiry = (TextView) view.findViewById(R.id.customerexpiry);
                                final TextView customeraddons = (TextView) view.findViewById(R.id.customeraddons);
                                final Button done = (Button) view.findViewById(R.id.done);

                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);

                                customername.setText("" + response.body().getCustomername().replace("<B>", "").replace("</B>", ""));
                                customernumber.setText("" + response.body().getCustomermobile().replace("<B>", "").replace("</B>", ""));
                                customeraddress.setText("" + response.body().getCustomeraddress());
                                customerid.setText("" + response.body().getCustomerid());
                                customerbalance.setText("" + response.body().getCustomerbalance());
                                customerpackage.setText("" + response.body().getCustomerpackage());
                                customerexpiry.setText("" + response.body().getCustomerexpiry());
                                customeraddons.setText("" + response.body().getCustomeraddons());

                                done.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        FragmentActivityMessage activityActivityMessage =
                                                new FragmentActivityMessage("amount", "" + response.body().getCustomerbalance());
                                        GlobalBus.getBus().post(activityActivityMessage);
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();

                            } else
                                //  if (response.body().getRecords()!=null && response.body().getRecords().size()>0){

                             /*   LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.dth_info, null);

                                final TextView customername = (TextView) view.findViewById(R.id.customername);
                                final TextView customernumber = (TextView) view.findViewById(R.id.customernumber);
                                final TextView customeraddress = (TextView) view.findViewById(R.id.customeraddress);
                                final TextView customerid = (TextView) view.findViewById(R.id.customerid);
                                final TextView customerbalance = (TextView) view.findViewById(R.id.customerbalance);
                                final TextView customerpackage = (TextView) view.findViewById(R.id.customerpackage);
                                final TextView customerexpiry = (TextView) view.findViewById(R.id.customerexpiry);
                                final TextView customeraddons = (TextView) view.findViewById(R.id.customeraddons);
                                final TextView lastrechargedate = (TextView) view.findViewById(R.id.lastrechargedate);
                                final TextView lastrechargeamount = (TextView) view.findViewById(R.id.lastrechargeamount);
                                final TextView status = (TextView) view.findViewById(R.id.status);
                                final TextView rmn = (TextView) view.findViewById(R.id.rmn);
                                final Button done = (Button) view.findViewById(R.id.done);

                                final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(view);


//
//      "status": "Active",

//      "lastrechargedate": "12 17:57:55.0 Jan 2020",
//      "lastrechargeamount": "274",
//      "planname":

                                 try{
                                    customername.setText(""+response.body().getRecords().get(0).getCustomername().replace("<B>","").replace("</B>",""));

                                   // customernumber.setText(""+response.body().getCustomermobile().replace("<B>","").replace("</B>",""));
                                 *//*   customeraddress.setText(""+response.body().getCustomeraddress());
                                    customerid.setText(""+response.body().getCustomerid());*//*
                                    customerbalance.setText(""+response.body().getRecords().get(0).getBalance());
                                    customerpackage.setText(""+response.body().getRecords().get(0).getMonthlyRecharge());
                                    customerexpiry.setText(""+response.body().getRecords().get(0).getNextRechargeDate());
                                  //  customeraddons.setText(""+response.body().getCustomeraddons());
                                    lastrechargedate.setText(""+response.body().getRecords().get(0).getLastrechargedate());
                                    lastrechargeamount.setText(""+response.body().getRecords().get(0).getLastrechargeamount());
                                    status.setText(""+response.body().getRecords().get(0).getStatus());
                                    rmn.setText(""+response.body().getRecords().get(0).getRmn());

                                    done.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            FragmentActivityMessage activityActivityMessage =
                                                    new FragmentActivityMessage("amount", ""+response.body().getCustomerbalance());
                                            GlobalBus.getBus().post(activityActivityMessage);
                                            dialog.dismiss();
                                        }
                                    });

                               }catch (Exception e){
                                     UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title) ,
                                             "Some thing went Wrong!!"+ e.getMessage(), 2);
                                 }

                                dialog.show();
                            }else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title) ,
                                        "Some thing went Wrong!!", 2);
                            }*/



                                if (response.body().getRecords() != null && response.body().getRecords().size() > 0) {
                                    if (response.body().getRecords().get(0).getStatus() != null) {
                                        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View view = inflater.inflate(R.layout.get_dth_info, null);


                                        final TextView Balance = (TextView) view.findViewById(R.id.Balance);
                                        final TextView monthlyRecharge = (TextView) view.findViewById(R.id.monthlyRecharge);
                                        final TextView NextRechargeDate = (TextView) view.findViewById(R.id.NextRechargeDate);
                                        final TextView Rmn = (TextView) view.findViewById(R.id.Rmn);
                                        final TextView lastrechargeamount = (TextView) view.findViewById(R.id.lastrechargeamount);
                                        final TextView lastrechargedate = (TextView) view.findViewById(R.id.lastrechargedate);
                                        final TextView STATUS = (TextView) view.findViewById(R.id.STATUS);
                                        final TextView CustomerName = (TextView) view.findViewById(R.id.CustomerName);
                                        final TextView planname = (TextView) view.findViewById(R.id.planname);

                                        final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                                        final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

                                        Balance.setText(" " + context.getResources().getString(R.string.rupiya) + " " + response.body().getRecords().get(0).getBalance());
                                        STATUS.setText(" " + response.body().getRecords().get(0).getStatus());
                                        monthlyRecharge.setText(" " + context.getResources().getString(R.string.rupiya) + " " + response.body().getRecords().get(0).getMonthlyRecharge() + " ");
                                        NextRechargeDate.setText(" " + response.body().getRecords().get(0).getNextRechargeDate());
                                        Rmn.setText(" " + response.body().getRecords().get(0).getRmn());
                                        if (response.body().getRecords().get(0).getLastrechargeamount() != null && !response.body().getRecords().get(0).getLastrechargeamount().equalsIgnoreCase("")) {
                                            lastrechargeamount.setText(" " + response.body().getRecords().get(0).getLastrechargeamount());
                                        } else {
                                            lastrechargeamount.setText("N/A ");

                                        }

                                        lastrechargedate.setText(" " + response.body().getRecords().get(0).getLastrechargedate());
                                        //   MSG.setText(" " + response.body().getMSG());
                                        CustomerName.setText(" " + response.body().getRecords().get(0).getCustomerName());
                                        planname.setText(" " + response.body().getRecords().get(0).getPlanname());


                                        final Dialog dialog = new Dialog(context);

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

                                                FragmentActivityMessage activityActivityMessage =
                                                        new FragmentActivityMessage("" + response.body().getRecords().get(0).getMonthlyRecharge(), "dth_info");
                                                GlobalBus.getBus().post(activityActivityMessage);


                                                //    Log.e("get_billclick " ,"  "  + response.body().getBillerResponse().getBillAmount());


                                                dialog.dismiss();


                                            }
                                        });
                                        dialog.show();

                                    } else {

                                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                                "No Plan found!", 2);
                                    }

                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            "No Plan found!", 2);
                                }
                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    "Some thing went Wrong!!" + e.getMessage(), 2);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RechargeInfoResponse> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("GetElectricityInfo", "error " + t.getMessage());
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Some thing went Wrong!!" + t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DTHHeavyRefresh(final Context context, final String mobileNo, final String opId, final ProgressDialog mProgressDialog) {

        Log.e("GetElectricityInfo", "mobileNo : " + mobileNo + ", opId: " + opId);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeInfoResponse> call = git.DTHHeavyRefresh(mobileNo, opId);
            call.enqueue(new Callback<RechargeInfoResponse>() {
                @Override
                public void onResponse(Call<RechargeInfoResponse> call, final retrofit2.Response<RechargeInfoResponse> response) {
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }
                            Log.e("GetElectricityInfo ", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (response.body().getStatus() != null &&
                                    response.body().getStatus().equalsIgnoreCase("success")) {
                                UtilMethods.INSTANCE.dialogOk(context,
                                        context.getResources().getString(R.string.successful_title),
                                        "" + response.body().getMessage(), 2);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "" + response.body().getMessage(), 2);
                            }


                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    "Some thing went Wrong!!" + e.getMessage(), 2);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RechargeInfoResponse> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("GetElectricityInfo", "error " + t.getMessage());
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "Some thing went Wrong!!" + t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HTMLResponse(final Context context, final String MobNo, final String Scode, final ProgressDialog mProgressDialog) {
        Log.e("GetDATA", "mobileNo : " + MobNo + ", Scode: " + Scode);
        try {
            EndPointInterface git = ApiClientDthPlans.getClient().create(EndPointInterface.class);
            Call<ResponseBody> call = git.htmlDate(MobNo, Scode, "a3VtYXI4ODk3MTE1MjY5QGdtYWlsLmNvbQ");
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                    Log.e("Response ", " aa " + response.body().toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }
                            try {
                                String html = response.body().string();
                                Log.e("Responses", html);

                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.dth_info_data, null);

                                final WebView mWebView = (WebView) view.findViewById(R.id.myWebView);

                                mWebView.loadData(html, "text/html", null);

                                final Button done = (Button) view.findViewById(R.id.done);

                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);


                                done.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                     /*   FragmentActivityMessage activityActivityMessage =
                                                new FragmentActivityMessage("amount", ""+ finalBalance);
                                        GlobalBus.getBus().post(activityActivityMessage);*/
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();

                            } catch (IOException e) {
                                Log.e("Response", " myHTMLResponseCallback : " + e.getMessage());
                                e.printStackTrace();
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "Wrong Customer id Or Operator", 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("Response", " Error " + t.getMessage());
                }
            });
        } catch (Exception e) {
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                    "Some thing went Wrong!!" + e.getMessage(), 2);
            e.printStackTrace();
        }
    }

    public void setNotificationList(Activity context, String toString) {

        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.notificationListPref, toString);
        editor.commit();
    }

    public String getNotificationList(Activity context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return prefs.getString(ApplicationConstant.INSTANCE.notificationListPref, "");

    }
    public void Dispute(final Context context, String id, String remarkText) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("dispute", "id : " + id + " ,numbr : " + remarkText + " ,appInfo : " + appInfo);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DisputeResponse> call = git.MarkDispute(appInfo, password, id, remarkText);
            call.enqueue(new Callback<DisputeResponse>() {
                @Override
                public void onResponse(Call<DisputeResponse> call, retrofit2.Response<DisputeResponse> response) {
                    Log.e("dispute", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getMessage(), 0);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<DisputeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void VerifyRefund(final Context context, String senderMobile, String venderId, String otp, String transactionId) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.VerifyRefund(appInfo, password, senderMobile, venderId, otp, transactionId);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getDMR().getTABLE().getMESSAGE(), 6);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getDMR().getTABLE().getMESSAGE(), 2);

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void Refund(final Context context, final String senderMobile, final String venderId, final String transactionId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("coming", "appInfo : " + appInfo + " ,pass : " + password + " ,sender : " + senderMobile + " ,vender : " + venderId);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginSenderResponse> call = git.Refund(appInfo, password, senderMobile, venderId);
            call.enqueue(new Callback<LoginSenderResponse>() {
                @Override
                public void onResponse(Call<LoginSenderResponse> call, retrofit2.Response<LoginSenderResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getDMR().getTABLE().getRESPONSESTATUS().equalsIgnoreCase("1")) {


                                ////////////////////////////////////////////////////////////////////////////////
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.otp_layout, null);

                                final TextInputLayout otpTextLayout = (TextInputLayout) view.findViewById(R.id.otpTextLayout);
                                final EditText otp = (EditText) view.findViewById(R.id.otp);
                                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

                                AppCompatTextView title = (AppCompatTextView) view.findViewById(R.id.title);
                                AppCompatTextView message = (AppCompatTextView) view.findViewById(R.id.message);

                                message.setText("Please enter valid OTP code sent on sender mobile.");

                                final Dialog dialog = new Dialog(context);

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

                                            UtilMethods.INSTANCE.VerifyRefund(context, senderMobile, venderId, otp.getText().toString().trim(), transactionId);
                                        } else {
                                            otp.setError("Please enter a valid OTP !!");
                                            otp.requestFocus();
                                        }
                                    }
                                });
                                dialog.show();
                                ////////////////////////////////////////////////////////////////////////////////

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getDMR().getTABLE().getMESSAGE(), 2);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getDMR().getMESSAGE(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginSenderResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RecentRecharges(final Context context, final String wallettype,  final String rechargeMobile, final String childMobileNumber, final ProgressDialog mProgressDialog, final String from) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("appinfo ", appInfo + " ,pass : " + password + " ,rechargeMobile : " + rechargeMobile + " ,childMobileNumber : " + childMobileNumber);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.LastRechargeStatus(appInfo, password, rechargeMobile, childMobileNumber, " ",
                    wallettype,"");
            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                RechargeReportResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), RechargeReportResponse.class);

                                if (transactions.getRechargeStatus() != null && transactions.getRechargeStatus().size() > 0) {
                                    if (transactions.getRechargeStatus().get(0).getTID() != null && transactions.getRechargeStatus().get(0).getTID().length() > 0) {

                                        UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);

                                        FragmentActivityMessage activityActivityMessage =
                                                new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "recentReport");
                                        GlobalBus.getBus().post(activityActivityMessage);

                                    } else {
                                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                                context.getResources().getString(R.string.transaction_empty_message), 2);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.transaction_empty_message), 2);
                                }

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                dialogOk(context, context.getResources().getString(R.string.login_error_title), response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }


                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    public void rechargeParams(final Context context, String mobile, String amount, String opId, String stdCode, final ProgressDialog mProgressDialog, final TextView payButton, final String flag) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String key = UtilMethods.INSTANCE.getRegKey(context);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        String IMEI = UtilMethods.INSTANCE.getIMEI(context);

        Log.e("recharge", "appInfo : " + appInfo + " ,password : " + password + " ,mobile : " + mobile + " ,stdCode : " + stdCode + "_" + deviceId + " ,key : " + key + " ,opId : " + opId + " ,amount : " + amount.trim() + " ,flag : " + flag);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeResponse> call = git.Recharge(appInfo, password, mobile, amount.trim(), opId, stdCode + "_" + deviceId, key);
            call.enqueue(new Callback<RechargeResponse>() {
                @Override
                public void onResponse(Call<RechargeResponse> call, retrofit2.Response<RechargeResponse> response) {
                    if (response.body() != null) {
                        try {
                            Log.e("recharge", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (flag.equals("electric")) {
                                if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                    Log.e("check", "hiiiiiiiiii ele");
                                    UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);
                                    UtilMethods.INSTANCE.dialogResponse(context,
                                            context.getResources().getString(R.string.successful_title),"1",
                                            response.body().getMessage(), 123654);



                                    payButton.setEnabled(true);

                                    FragmentActivityMessage activityActivityMessage =
                                            new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "all");
                                    GlobalBus.getBus().post(activityActivityMessage);

                                } else {
                                    UtilMethods.INSTANCE.dialogResponse(context,
                                            context.getResources().getString(R.string.failed),"0",
                                            response.body().getMessage(), 123654);


                                    payButton.setEnabled(true);
                                }
                            }
                            else {
                                Log.e("check", "hii ele");
                                if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                    //   UtilMethods.INSTANCE.LastRechargeStatus(context, "", "","", "", mProgressDialog, "all");

                                    UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);
                                    FragmentActivityMessage activityActivityMessage =
                                            new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "refreshvalue");
                                    GlobalBus.getBus().post(activityActivityMessage);

                                    UtilMethods.INSTANCE.dialogResponse(context,
                                            context.getResources().getString(R.string.successful_title),"1",
                                            response.body().getMessage(), 123654);


                                    payButton.setEnabled(true);

                                } else {
                                    UtilMethods.INSTANCE.dialogResponse(context,
                                            context.getResources().getString(R.string.failed),"0",
                                            response.body().getMessage(), 123654);


                                    payButton.setEnabled(true);
                                }
                            }
                        } catch (Exception e) {
                            UtilMethods.INSTANCE.dialogResponse(context,
                                    context.getResources().getString(R.string.failed),"0",
                                    response.body().getMessage(), 123654);

                        }
                    }
                }

                @Override
                public void onFailure(Call<RechargeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    UtilMethods.INSTANCE.dialogResponse(context,
                            context.getResources().getString(R.string.failed),"0",
                            "Something went wrong !!", 123654);
                }
            });

        } catch (Exception e) {
            UtilMethods.INSTANCE.dialogResponse(context,
                    context.getResources().getString(R.string.failed),"0",
                    "Something went wrong !!", 123654);

            e.printStackTrace();
        }

    }

    public void VideoGallery(final Context context, final ApiCallBack mApiCallBack) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            // Call<LoginResponse> call = git.banner(appInfo);
          //  Call<LoginResponse> call = git.VideoImageGallery(appInfo, RoleId);
            Call<LoginResponse> call = git.BannerSetting(appInfo, RoleId);
            // Call<LoginResponse> call = git.banner(appInfo,RoleId);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    Log.e("videoResp", "response : " + new Gson().toJson(response.body()));

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null && response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());

                            }
                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                            //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //Failed(context,  response.body().getMessage());
                        } else {
                            // UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            //       response.body().getMessage(), 2);
                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    displayingOnFailuireMessage(context, t.getMessage());

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShoppingBanner(final Context context, final ApiCallBack mApiCallBack) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<EcommerceBannerResponse> call = git.EcommerceBanner(appInfo);
            call.enqueue(new Callback<EcommerceBannerResponse>() {

                @Override
                public void onResponse(Call<EcommerceBannerResponse> call, retrofit2.Response<EcommerceBannerResponse> response) {
                    Log.e("videoResp", "response : " + new Gson().toJson(response.body()));

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null && response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            if (mApiCallBack != null) {
                                mApiCallBack.onSucess(response.body());

                            }
                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));

                        }

                    }
                }

                @Override
                public void onFailure(Call<EcommerceBannerResponse> call, Throwable t) {
                    displayingOnFailuireMessage(context, t.getMessage());

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VideoGallery(final Context context, final ProgressDialog loader) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId;
        String roleid = UtilMethods.INSTANCE.getRoleId(context);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.VideoImageGallery(appInfo, roleid);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    Log.e("videoResp", "VideoGallery response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "videogallery");
                            GlobalBus.getBus().post(activityActivityMessage);
                        } else {
//                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.err_attention_error_title),
//                                    response.body().getMessage(), 2);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayingOnFailuireMessage(Context context, String failuireMssg) {
        if (failuireMssg != null && failuireMssg.contains("No address associated with hostname")) {
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.err_msg_network_title), context.getResources().getString(R.string.err_msg_network), 1);
        } else {
            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.err_msg_network_title), failuireMssg + "", 1);
        }
    }

    public void FundTransfer(final Context context, String mobile, String amount, String fundType, final ProgressDialog mProgressDialog, final Button payButton) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeResponse> call = git.FundTransfer(appInfo, password, mobile, amount, fundType);
            call.enqueue(new Callback<RechargeResponse>() {

                @Override
                public void onResponse(Call<RechargeResponse> call, retrofit2.Response<RechargeResponse> response) {
                    // Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        payButton.setEnabled(true);
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);
                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "refreshvalue");
                                GlobalBus.getBus().post(activityActivityMessage);

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getMessage(), 2);
                                SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
                                String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
                                UtilMethods.INSTANCE.UserList(context, mProgressDialog, mobileLogin, "Search", null);
                                UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);


                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
                                        response.body().getMessage(), 2);
                                UtilMethods.INSTANCE.BalanceCheck(context, mProgressDialog);

                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<RechargeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void PaymentRequest(final Context context, String amount, String accountNumber, String transactionId,
                               String fundType, String Remark, String Bankname, String _AmountINWord,
                             final ProgressDialog loader) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("payment_req", "appinfo : " + appInfo + " ,pass : " + password + " ,transactionId : " + transactionId + " ,fundType : " + fundType + " ,accountNumber : " + accountNumber);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);

            Call<RechargeResponse> call = git.PaymentRequest(appInfo, password, accountNumber, transactionId, amount, fundType,/*Remark,Bankname,_AmountINWord,imgString.trim()*/ "1");
            call.enqueue(new Callback<RechargeResponse>() {

                @Override
                public void onResponse(Call<RechargeResponse> call, retrofit2.Response<RechargeResponse> response) {
                    Log.e("payment_req", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    try {
                        if (!response.body().getRESPONSESTATUS().equals("null")) {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("payment_request");
                                GlobalBus.getBus().post(activityActivityMessage);

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title), response.body().getMessage(), 0);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title), response.body().getMessage(), 0);
                            }
                        }
                    } catch (Exception ex) {
                        if (loader != null) {
                            if (loader.isShowing())
                                loader.dismiss();
                        }
                    }

                }

                @Override
                public void onFailure(Call<RechargeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
//                    UtilMethods.INSTANCE.dialogOk(context,context.getResources().getString(R.string.successful_title) ,"Payment Request Sucessfully.",0);
//                    ActivityActivityMessage activityActivityMessage =
//                            new ActivityActivityMessage("payment_request"  );
//                    GlobalBus.getBus().post(activityActivityMessage);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }
        }

    }

    public void Ledger(final Context context, String fromDate, String toDate, String content, String _WalletType, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LedgerReportResponse> call = git.Ledger(appInfo, password, fromDate, toDate,_WalletType, content);
            call.enqueue(new Callback<LedgerReportResponse>() {
                @Override
                public void onResponse(Call<LedgerReportResponse> call, retrofit2.Response<LedgerReportResponse> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                LedgerReportResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), LedgerReportResponse.class);

                                if (transactions.getLedger() != null && transactions.getLedger().size() > 0) {
                                    if (transactions.getLedger().get(0).getCreatedDate() != null && transactions.getLedger().get(0).getCreatedDate().length() > 0) {

                                        Intent transactionIntent = new Intent(context, LedgerReport.class);
                                        transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                        context.startActivity(transactionIntent);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.transaction_empty_message), 2);
                                }
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<LedgerReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CheckMobSimplePlan(final Activity context, final String opId, final String opZone, final String part1, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        Log.e("op", "opId : " + opId.trim() + " ,opZone : " + opZone.trim());

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ResponseMobilePlan> call = git.CheckMobSimplePlan(opId.trim(), opZone.trim());
            call.enqueue(new Callback<ResponseMobilePlan>() {
                @Override
                public void onResponse(Call<ResponseMobilePlan> call, retrofit2.Response<ResponseMobilePlan> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());

                    if (response.body() != null) {
                        try {

                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getData() != null && response.body().getData().getRecords() != null
                            ) {
                                Intent browseIntent = new Intent(context, BrowsePlanScreen.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                browseIntent.putExtra("operatorName", "" + part1);
                                browseIntent.putExtra("from", "mob");
                                context.startActivity(browseIntent);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "No data found!", 2);
                            }
                        } catch (Exception e) {
                            Log.e("mProgressDialog1", "mProgressDialog");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseMobilePlan> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mProgressDialog2", "mProgressDialog");
        }
    }

//    public void PaymentRequest(final Context context, String amount, String accountNumber, String transactionId, String fundType,String Remark, String Bankname,String _AmountINWord,String _ImgSlip1, final ProgressDialog mProgressDialog,final Button payButton) {
//
//        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
//        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
//        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
//
//        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
//
//        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
//        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
////@Field("_UMobile") String appInfo,
////                                          @Field("_UPassword") String password,
////                                          @Field("_AccountNo") String accountNumber,
////                                          @Field("_TransactionID") String transactionId,
////                                          @Field("_Amount") String amount,
////                                          @Field("_FundType") String fundType,
////                                          @Field("_Remark") String _Remark,
////                                          @Field("_BankName") String _BankName,
////                                          @Field("_AmountINWord") String _AmountINWord,
////                                          @Field("_ImgSlip1") String _ImgSlip1
//       Log.e("here", "PaymentRequest appInfo : " + appInfo + " , acc : " + accountNumber + "fundType : " + fundType+ "   Remark : " + Remark+ "   Bankname : " + Bankname+"   _AmountINWord " +_AmountINWord + "   _ImgSlip1 : " +_ImgSlip1);
//        try {
//            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
//            Call<RechargeResponse> call = git.PaymentRequest(appInfo, password, accountNumber, transactionId, amount, fundType,Remark,Bankname,_AmountINWord,_ImgSlip1.trim()   );
//            call.enqueue(new Callback<RechargeResponse>() {
//
//                @Override
//                public void onResponse(Call<RechargeResponse> call, retrofit2.Response<RechargeResponse> response) {
//                   Log.e("response hello", "PaymentRequest response : " + new Gson().toJson(response.body()).toString());
//
//                    if (mProgressDialog!=null)
//                        if (mProgressDialog.isShowing())
//                            mProgressDialog.dismiss();
//                        if(response.body()!=null)
//                    {
//                        payButton.setEnabled(true);
//                        try {
////                            if (mProgressDialog!=null)
//                                if (mProgressDialog.isShowing())
//                                mProgressDialog.dismiss();
//
//                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
//                                        response.body().getMessage(), 2);
//                            } else {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                                        response.body().getMessage(), 2);
//                            }
//                        }
//                        catch (Exception e)
//                        {
//                            if (response.body().getMessage() !=null){
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                                        response.body().getMessage()+ e.getMessage(), 2);
//                            }else {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                                        "Failed to Request !!!"+ e.getMessage(), 2);
//                            }
//
//                        }
//                    }
//                        else {
//                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                                    "Failed  to Payment Request!!!", 2);
//                        }
//                }
//
//                @Override
//                public void onFailure(Call<RechargeResponse> call, Throwable t) {
//                    Log.e("response", "error ");
//                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                             "Failed  !!!"+ t.getMessage(), 2);
//                     if (mProgressDialog!=null)
//                    if (mProgressDialog.isShowing())
//                        mProgressDialog.dismiss();
//                }
//            });
//
//        } catch (Exception e) {
//            if (mProgressDialog!=null)
//                if (mProgressDialog.isShowing())
//                mProgressDialog.dismiss();
//            e.printStackTrace();
//            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.failed),
//                    "Failed to to Request !!!"+ e.getMessage(), 2);
//        }
//
//    }

    public void CheckDTHPlan(final Activity context, final String opId, final String opZone, final String part1, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        Log.e("op", "opId : " + opId.trim() + " ,opZone : " + opZone.trim());

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ResponseMobilePlan> call = git.CheckDTHPlan("", opId.trim());
            call.enqueue(new Callback<ResponseMobilePlan>() {
                @Override
                public void onResponse(Call<ResponseMobilePlan> call, retrofit2.Response<ResponseMobilePlan> response) {
                    Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());

                    if (response.body() != null) {
                        try {

                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getData() != null && response.body().getData().getRecords() != null
                            ) {
                                Intent browseIntent = new Intent(context, BrowsePlanScreen.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                browseIntent.putExtra("operatorName", "" + part1);
                                browseIntent.putExtra("from", "dth");
                                context.startActivity(browseIntent);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "No data found!", 2);
                            }
                        } catch (Exception e) {
                            Log.e("mProgressDialog1", "mProgressDialog");
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseMobilePlan> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("mProgressDialog2", "mProgressDialog");
        }
    }

    public void DisputeReport(final Context context, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DisputeResponse> call = git.DisputeReport(appInfo, password);
            call.enqueue(new Callback<DisputeResponse>() {
                @Override
                public void onResponse(Call<DisputeResponse> call, retrofit2.Response<DisputeResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                DisputeResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), DisputeResponse.class);

                                if (transactions.getDispute() != null && transactions.getDispute().size() > 0) {
                                    if (transactions.getDispute().get(0).getRDate() != null && transactions.getDispute().get(0).getTID().length() > 0) {

                                        Intent transactionIntent = new Intent(context, DisputeReport.class);
                                        transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                        context.startActivity(transactionIntent);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.empty_list_message), 2);
                                }
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<DisputeResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetUserDayBook(final Context context, String childNumber, String fromDate, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        // Log.e("here data", "is : " + appInfo + " ,child : " + childNumber + " ,fromDate : " + fromDate);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<UserDayBookResponse> call = git.GetUserDayBook(appInfo, password, childNumber, fromDate);
            call.enqueue(new Callback<UserDayBookResponse>() {


                @Override
                public void onResponse(Call<UserDayBookResponse> call, retrofit2.Response<UserDayBookResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    try {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();

                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            UserDayBookResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), UserDayBookResponse.class);
                            //if (transactions.getSummary().get(0).getCreatedDate() != null && transactions.getLedger().get(0).getCreatedDate().length() > 0) {
                            if (transactions.getLedger() != null && transactions.getLedger().size() > 0) {
                                Intent transactionIntent = new Intent(context, UserDayBookScreen.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                context.startActivity(transactionIntent);
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.empty_list_message), 2);
                            }
                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                            //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //Failed(context,  response.body().getMessage());
                        } /*else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }*/ else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.transaction_empty_message), 2);
                        }
                    } catch (Exception ex) {

                    }
                }

                @Override
                public void onFailure(Call<UserDayBookResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void FundReceiveStatementDateWise(final Context context, String mobile, final ProgressDialog mProgressDialog, final String type) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("appinfo", appInfo + " , " + password + " , " + mobile);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<FundRecResponse> call = git.FundReceiveStatementDateWise(appInfo, password, mobile);
            call.enqueue(new Callback<FundRecResponse>() {

                @Override
                public void onResponse(Call<FundRecResponse> call, retrofit2.Response<FundRecResponse> response) {
                    Log.e("Fund_receive_statement", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                FundRecResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), FundRecResponse.class);

                                Intent transactionIntent = new Intent(context, FundRecReport.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                transactionIntent.putExtra("from", "FundReceiveStatementDateWise");

                                context.startActivity(transactionIntent);

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }


                        } catch (Exception ex) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<FundRecResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void FundReceiveStatus(final Context context, String fromdate, String todate, final ProgressDialog mProgressDialog, final ApiCallBack mApiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<FundRecResponse> call = git.FundReceiveStatus(appInfo, password, fromdate, todate);
            call.enqueue(new Callback<FundRecResponse>() {
                @Override
                public void onResponse(Call<FundRecResponse> call, retrofit2.Response<FundRecResponse> response) {
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body() != null) {
                        try {
                            Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                FundRecResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), FundRecResponse.class);

                                if (transactions.getFundReceive() != null &&
                                        transactions.getFundReceive().size() > 0) {
                                    if (transactions.getFundReceive().get(0).getTID() != null &&
                                            transactions.getFundReceive().get(0).getDateTime().length() > 0) {

                                        mApiCallBack.onSucess(response.body());
                                        if (mProgressDialog != null && mProgressDialog.isShowing())
                                            mProgressDialog.dismiss();
                                    } else {
                                        Toast.makeText(context, "No transaction found !!!", Toast.LENGTH_LONG).show();
                                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                                context.getResources().getString(R.string.transaction_empty_message), 2);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.transaction_empty_message), 2);
                                }
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }
                        } catch (Exception e) {
                            Log.e("exxxxxxxxxxxxxxx", "" + e.getMessage());
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.transaction_empty_message), 2);
                        }
                    }
                }

                @Override
                public void onFailure(Call<FundRecResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        }

    }

    public void FundReceiveStatement(final Context context, String fromdate, String todate, String mobile, final ProgressDialog mProgressDialog, final String type) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("appinfo", appInfo + " , " + password + " , " + mobile);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<FundRecResponse> call = git.FundTransferStatement(appInfo, password, fromdate, todate, mobile);
            call.enqueue(new Callback<FundRecResponse>() {

                @Override
                public void onResponse(Call<FundRecResponse> call, retrofit2.Response<FundRecResponse> response) {
                    Log.e("Fund_receive_statement", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {


                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Log.e("tRESPONSESTATUS()", "    response.body().getRESPONSESTATUS()   " + response.body().getRESPONSESTATUS());
                        if (response.body().getRESPONSESTATUS().toString().equals("1")) {
                            //  FundRecResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), FundRecResponse.class);

                            if (response.body().getFundReceive() != null && response.body().getFundReceive().size() > 0) {
//                                    if (response.body().getFundReceive().get(0).getTransactionID() != null
//                                            && response.body().getFundReceive().get(0).getDateTime().length() > 0) {

                                if (type.equalsIgnoreCase("all")) {
                                    Intent transactionIntent = new Intent(context, FundTransferStatement.class);
                                    transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                    context.startActivity(transactionIntent);
                                } else {
                                    FragmentActivityMessage fragmentActivityMessage = new FragmentActivityMessage("fund_statement", "" + new Gson().toJson(response.body()).toString());
                                    GlobalBus.getBus().post(fragmentActivityMessage);
                                }
                                // }
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }
                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                            //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //Failed(context,  response.body().getMessage());
                        } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.transaction_empty_message), 2);
                        }
                        //   }
//                        catch (Exception ex)
//                        {
//                            Log.e("erpr"," ex "+ ex.getMessage());
//                            if ( mProgressDialog!=null &&mProgressDialog.isShowing())
//                                mProgressDialog.dismiss();
//                        }
                    }
                }

                @Override
                public void onFailure(Call<FundRecResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            e.printStackTrace();
            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        }

    }


    public void CommisionSlab(final Context context, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<CommissionSlabResponse> call = git.CommissionSlab(appInfo, password);
            call.enqueue(new Callback<CommissionSlabResponse>() {

                @Override
                public void onResponse(Call<CommissionSlabResponse> call, retrofit2.Response<CommissionSlabResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                CommissionSlabResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), CommissionSlabResponse.class);

                                if (transactions.getCommission() != null && transactions.getCommission().size() > 0) {
                                    if (transactions.getCommission().get(0).getID() != null && transactions.getCommission().get(0).getOpratedName().length() > 0) {

                                        Intent transactionIntent = new Intent(context, CommissionScreen.class);
                                        transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                        context.startActivity(transactionIntent);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.empty_list_message), 2);
                                }
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<CommissionSlabResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_Ticket_Open(final Context context, final String transId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ReversalOpenListResponse> call = git.DTH_Ticket_Open(appInfo, password, transId);
            call.enqueue(new Callback<ReversalOpenListResponse>() {

                @Override
                public void onResponse(Call<ReversalOpenListResponse> call, retrofit2.Response<ReversalOpenListResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                        ReversalOpenListResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), ReversalOpenListResponse.class);

                        if (transactions.getDetail() != null && transactions.getDetail().size() > 0) {
                            if (transactions.getDetail().get(0).getID() != null && transactions.getDetail().get(0).getAmount().length() > 0) {

                                Intent transactionIntent = new Intent(context, DTHReversalOpenTickets.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                transactionIntent.putExtra("from", "open");
                                context.startActivity(transactionIntent);
                            }
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.empty_list_message), 2);
                        }
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<ReversalOpenListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DTH_Ticket_Closed(final Context context, final String transId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ReversalOpenListResponse> call = git.DTH_Ticket_Closed(appInfo, password, transId);
            call.enqueue(new Callback<ReversalOpenListResponse>() {

                @Override
                public void onResponse(Call<ReversalOpenListResponse> call, retrofit2.Response<ReversalOpenListResponse> response) {
                    //       Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                        ReversalOpenListResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), ReversalOpenListResponse.class);

                        if (transactions.getDetail() != null && transactions.getDetail().size() > 0) {
                            if (transactions.getDetail().get(0).getID() != null && transactions.getDetail().get(0).getAmount().length() > 0) {

                                Intent transactionIntent = new Intent(context, DTHReversalOpenTickets.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                transactionIntent.putExtra("from", "close");
                                context.startActivity(transactionIntent);
                            }
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.empty_list_message), 2);
                        }
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<ReversalOpenListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_Ticket(final Context context, final String transId, final String ticketId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<DTHTicketResponse> call = git.DTH_Ticket(appInfo, password, transId);
            call.enqueue(new Callback<DTHTicketResponse>() {

                @Override
                public void onResponse(Call<DTHTicketResponse> call, retrofit2.Response<DTHTicketResponse> response) {
                    //        Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                        DTHTicketResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), DTHTicketResponse.class);

                        if (transactions.getDetail() != null && transactions.getDetail().size() > 0) {
                            if (transactions.getDetail().get(0).getID() != null && transactions.getDetail().get(0).getAmount().length() > 0) {

                                String ticketData = new Gson().toJson(response.body()).toString();
                               /* Intent transactionIntent = new Intent(context, TicketDetail.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                transactionIntent.putExtra("from", "gettingData");
                                context.startActivity(transactionIntent);*/

                                UtilMethods.INSTANCE.DTH_Response(context, transId, mProgressDialog, ticketData, ticketId);
                            }
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.empty_list_message), 2);
                        }
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<DTHTicketResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_Insert_Ticket(final Context context, final String transId, final String rightId, final String remark, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.DTH_Insert_Ticket(appInfo, password, transId, rightId, remark);
            call.enqueue(new Callback<HelloWorldResponse>() {

                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //      Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                response.body().getMessage(), 2);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_ApproveTicket(final Context context, final String transId, final String ticketId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.DTH_ApproveTicket(appInfo, password, ticketId, transId);
            call.enqueue(new Callback<HelloWorldResponse>() {

                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //         Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                response.body().getMessage(), 2);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_RejectTicket(final Context context, final String transId, final String ticketId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.DTH_RejectTicket(appInfo, password, ticketId, transId);
            call.enqueue(new Callback<HelloWorldResponse>() {

                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //         Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DTH_Response(final Context context, final String transId, final ProgressDialog mProgressDialog, final String ticketData, final String ticketId) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ChatResponse> call = git.DTH_Response(appInfo, password, transId);
            call.enqueue(new Callback<ChatResponse>() {

                @Override
                public void onResponse(Call<ChatResponse> call, retrofit2.Response<ChatResponse> response) {
                    //          Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                ChatResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), ChatResponse.class);

                                if (transactions.getResponse() != null && transactions.getResponse().size() > 0) {
                                    if (transactions.getResponse().get(0).getId() != null && transactions.getResponse().get(0).getTicketId().length() > 0) {

                                        Intent transactionIntent = new Intent(context, TicketDetail.class);
                                        transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                        transactionIntent.putExtra("ticketData", ticketData);
                                        transactionIntent.putExtra("from", "gettingData");
                                        transactionIntent.putExtra("ticketId", ticketId);
                                        context.startActivity(transactionIntent);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.empty_list_message), 2);
                                }
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<ChatResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_Insert_Response(final Context context, final String ticketId, final String reply, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.DTH_Insert_Response(appInfo, password, ticketId, reply);
            call.enqueue(new Callback<HelloWorldResponse>() {
                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //        Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DTH_GetDataByTransactionId(final Context context, final String transId, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        // Log.e("here", "transId : " + transId);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ReversalOpenListResponse> call = git.DTH_GetDataByTransactionId(appInfo, password, transId);
            call.enqueue(new Callback<ReversalOpenListResponse>() {

                @Override
                public void onResponse(Call<ReversalOpenListResponse> call, retrofit2.Response<ReversalOpenListResponse> response) {
                    //          Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                        ReversalOpenListResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), ReversalOpenListResponse.class);

                        if (transactions.getTransaction() != null && transactions.getTransaction().size() > 0) {
                            if (transactions.getTransaction().get(0).getID() != null && transactions.getTransaction().get(0).getAmount().length() > 0) {

                                Intent transactionIntent = new Intent(context, GenerateTicket.class);
                                transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                transactionIntent.putExtra("from", "gettingData");
                                context.startActivity(transactionIntent);
                            }
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.empty_list_message), 2);
                        }
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<ReversalOpenListResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void BalanceCheck(final Context context, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BalanceCheckResponse> call = git.BalanceCheck(appInfo, password);
            call.enqueue(new Callback<BalanceCheckResponse>() {

                @Override
                public void onResponse(Call<BalanceCheckResponse> call, retrofit2.Response<BalanceCheckResponse> response) {
                    if (response.body() != null) {
                        try {
                            Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.GetNews(context, null);
                                setBalanceCheck(context, new Gson().toJson(response.body()).toString());
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "(redirectToLogin)");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
//                                        response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BalanceCheckResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void BellNotification(final Context context, final ProgressDialog mProgressDialog, final ApiCallBack apiCallBack) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BalanceCheckResponse> call = git.BellNotification(appInfo, password);
            call.enqueue(new Callback<BalanceCheckResponse>() {

                @Override
                public void onResponse(Call<BalanceCheckResponse> call, retrofit2.Response<BalanceCheckResponse> response) {
                    //  Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        try {


                            //  Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                            //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                apiCallBack.onSucess(response.body());
                                //  UtilMethods.INSTANCE.GetNews(context);
                                //setPopup(context, new Gson().toJson(response.body()).toString());
                            } else {
                                //  UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                //          response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        //   Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                        //  Log.e("response", " response is null  ");


                    }
                }

                @Override
                public void onFailure(Call<BalanceCheckResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    Toast.makeText(context, "2" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            Toast.makeText(context, "3" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void Popup(final Context context, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("here", "appInfo : " + appInfo + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BalanceCheckResponse> call = git.Popupdata(appInfo, password);
            call.enqueue(new Callback<BalanceCheckResponse>() {

                @Override
                public void onResponse(Call<BalanceCheckResponse> call, retrofit2.Response<BalanceCheckResponse> response) {
                    //  Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                    if (response.body() != null) {
                        try {


                            //  Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                            //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                            if (mProgressDialog != null && mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                UtilMethods.INSTANCE.GetNews(context, null);
                                setPopup(context, new Gson().toJson(response.body()).toString());
                            } else {
                                //UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                //   response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    } else {
                        //   Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();

                        //  Log.e("response", " response is null  ");


                    }
                }

                @Override
                public void onFailure(Call<BalanceCheckResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CheckBalanceDownline(final Context context, final String childNumber, final Dialog dialog, final String from, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BalanceCheckResponse> call = git.CheckBalanceDownline(appInfo, password, childNumber);
            call.enqueue(new Callback<BalanceCheckResponse>() {

                @Override
                public void onResponse(Call<BalanceCheckResponse> call, retrofit2.Response<BalanceCheckResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        try {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            if (from.equalsIgnoreCase("info")) {

                                if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                    UtilMethods.INSTANCE.dialogOk(context, "Information !!",
                                            "Name : " + response.body().getChildBalance().get(0).getName() + "\n" +
                                                    "Mobile : " + childNumber + "\n", 2);

                                } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                    Error(context, "redirectToLogin");
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    //Failed(context,  response.body().getMessage());
                                } /*else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            response.body().getMessage(), 2);
                                }*/ else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.transaction_empty_message), 2);
                                }

                            } else {
                                if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                    ((Dashboard3) context).CheckBalanceDownline(new Gson().toJson(response.body()).toString(), context);

                                } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                    Error(context, "redirectToLogin");
                                    Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                    //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    //Failed(context,  response.body().getMessage());
                                } /*else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            response.body().getMessage(), 2);
                                }*/ else {
                                    dialog.dismiss();
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            response.body().getMessage(), 2);
                                }
                            }
                        } catch (Exception ex) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BalanceCheckResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void LastRechargeStatus(final Context context, final String wallettype, final String rechargeMobile, final String childMobileNumber, String fromDate, String toDate, final ProgressDialog mProgressDialog, final String from) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("LastRechargeSta", appInfo + " ,pass : " + password + " rechargeMobile   " + rechargeMobile + " childMobileNumber   " + childMobileNumber + "  fromDate  " + fromDate + "  toDate  " + toDate);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RechargeReportResponse> call = git.LastRechargeStatus(appInfo, password, rechargeMobile, childMobileNumber, fromDate,wallettype, toDate);
            call.enqueue(new Callback<RechargeReportResponse>() {

                @Override
                public void onResponse(Call<RechargeReportResponse> call, retrofit2.Response<RechargeReportResponse> response) {
                    Log.e("LastRechargeStatus", "LastRechargeStatus response : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body() != null && response.body().getRESPONSESTATUS() != null)
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            RechargeReportResponse transactions = new Gson().fromJson(new Gson().toJson(response.body()).toString(), RechargeReportResponse.class);

                            if (transactions.getRechargeStatus() != null && transactions.getRechargeStatus().size() > 0) {
                                if (transactions.getRechargeStatus().get(0).getTID() != null && transactions.getRechargeStatus().get(0).getTID().length() > 0) {

                                    if (from.equalsIgnoreCase("specific")) {
                                        ((RechargeReport) context).specificReportList(new Gson().toJson(response.body()).toString(), "specific");
                                    } else if (from.equalsIgnoreCase("recentnew")) {

                                        FragmentActivityMessage activityActivityMessage =
                                                new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "recent2");
                                        GlobalBus.getBus().post(activityActivityMessage);

                                    } else {
                                        Intent transactionIntent = new Intent(context, RechargeReport.class);
                                        transactionIntent.putExtra("response", new Gson().toJson(response.body()).toString());
                                        context.startActivity(transactionIntent);
                                    }
                                } else {
                                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                            context.getResources().getString(R.string.transaction_empty_message), 2);
                                }
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        context.getResources().getString(R.string.transaction_empty_message), 2);
                            }

                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                            //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //Failed(context,  response.body().getMessage());
                        } /*else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }*/ else {
                            dialogOk(context, context.getResources().getString(R.string.login_error_title), response.body().getMessage(), 2);
                        }
                }


                @Override
                public void onFailure(Call<RechargeReportResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null && mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exceptionresult", "" + e.getMessage());
        }
    }


    public void dialogOk(final Context context, String title, final String message, final int flag) {
        try {
            AlertDialog.Builder customBuilder =  new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // "OK" button was clicked
                            if (flag == 123654) {
                                UtilMethods.INSTANCE.LastRechargeStatus(context, "", "", "", "", "", null, "Recent");

                            }
                            if (flag == 4) {
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("transferDone");
                                GlobalBus.getBus().post(activityActivityMessage);
                            } else if (flag == 3) {
                                ((RegisterScreen) context).finishMethod();
                            } else if (flag == 33) {
                                Intent intent = new Intent(context, LoginScreen.class);
                                context.startActivity(intent);
                                ((Dashboard3) context).finish();

                            }  else if (flag == 13) {
                                ((AddMoneyActivity) context).finishMethod();
                            } else if (flag == 1333) {
                                ((SignupScreen) context).finishMethod();
                            } else if (flag == 12) {
                                ((AddMoneyActivity) context).finishMethod();
                            } else if (flag == 22) {
                                ((AddBeneficiaryScreen) context).finishMethod();
                            } else if (flag == 6) {
                                //Dispute on DMR
                                ActivityActivityMessage activityActivityMessage =
                                        new ActivityActivityMessage("disputeDMR");
                                GlobalBus.getBus().post(activityActivityMessage);

                            }
                        }
                    });


            AlertDialog dialog = customBuilder.create();
            dialog.show();
            Button b = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button b1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button b2 = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);

            if(b != null) {
                b.setTextColor(context.getResources().getColor(R.color.colorAccent));

            }
            if(b1 != null) {
                b1.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
            if(b2 != null) {
                b2.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        } catch (Exception e) {

        }
    }
 public void dialogSuccessOk(final Context context, String title, final String message, final int flag) {
        try {

            AlertDialog.Builder customBuilder =  new AlertDialog.Builder(context);
            AlertDialog dialog = customBuilder.create();
            customBuilder  .setTitle(title).setIcon(context.getResources().getDrawable(R.drawable.ic_status_success))
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // "OK" button was clicked
                            dialog.dismiss();
                            if (flag == 123654) {
                                UtilMethods.INSTANCE.LastRechargeStatus(context, "","", "", "", "", null, "Recent");

                            }

                        }
                    });



            dialog.show();
            Button b = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button b1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button b2 = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);

            if(b != null) {
                b.setTextColor(context.getResources().getColor(R.color.colorAccent));

            }
            if(b1 != null) {
                b1.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
            if(b2 != null) {
                b2.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        } catch (Exception e) {

        }
    }
    public void dialogFaileOk(final Context context, String title, final String message, final int flag) {
        try {

            AlertDialog.Builder customBuilder =  new AlertDialog.Builder(context);
            AlertDialog dialog = customBuilder.create();
            customBuilder.setTitle(title).setIcon(context.getResources().getDrawable(R.drawable.ic_status_failed))
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // "OK" button was clicked

                            dialog.dismiss();
                        }
                    });



            dialog.show();
            Button b = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button b1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button b2 = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);

            if(b != null) {
                b.setTextColor(context.getResources().getColor(R.color.colorAccent));

            }
            if(b1 != null) {
                b1.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
            if(b2 != null) {
                b2.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        } catch (Exception e) {

        }
    }

    public void startingService(final Context context) {
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.helloWorld();
            call.enqueue(new Callback<HelloWorldResponse>() {
                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //  Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void KeyUpdate(final Context context, String id) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        // Log.e("here", "appinfo : " + appInfo + " ,id : " + id + " ,pass : " + password);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<KeyUpdateResponse> call = git.KeyUpdate(appInfo, password, id);
            call.enqueue(new Callback<KeyUpdateResponse>() {
                @Override
                public void onResponse(Call<KeyUpdateResponse> call, retrofit2.Response<KeyUpdateResponse> response) {
                    //Log.e("response hello", "hello response : " + new Gson().toJson(response.body()).toString());

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS() != null && response.body().getRESPONSESTATUS().equalsIgnoreCase("1"))
                            UtilMethods.INSTANCE.setRegKeyStatus(context, "1");
                        else

                            UtilMethods.INSTANCE.setRegKeyStatus(context, "1");
                    }
                }

                @Override
                public void onFailure(Call<KeyUpdateResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetROffer(final Context context, String Number, String SCode, final ProgressDialog mProgressDialog, final TextView tvROffers) {

        Log.e("r_offer", " Number " + Number + ", SCode " + SCode);

     /*   String UMobile="9044004486";
        String Token="8adda5c1681b9cdaaabd10d3bd90dad2";*/

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ROfferResponse> call = git.RofferPlans(SCode, Number);
            call.enqueue(new Callback<ROfferResponse>() {

                @Override
                public void onResponse(Call<ROfferResponse> call, retrofit2.Response<ROfferResponse> response) {
                    Log.e("r_offer", "hello response : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (response.body() != null) {
                            if (mProgressDialog != null) {
                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                            }

                            if (response.body().getStatus().equalsIgnoreCase("1") && response.body().getRecords().size() > 0) {

                                Intent browseIntent = new Intent(context, ROffer.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                context.startActivity(browseIntent);


                            } else {
                                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                alertDialog.setTitle("Alert!!!");
                                alertDialog.setMessage("No Offer Available on this Number.");
                                alertDialog.show();

                            }
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<ROfferResponse> call, Throwable t) {
                    Log.e("r_offer", t.getMessage());
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Alert!!!");
                    alertDialog.setMessage("No Offer Available on this Number.");
                    alertDialog.show();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void App_Services_Provided(final Context context) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        final String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        final String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + UMobile + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<ServicesResponse> call = git.App_Services_Provided(appInfo);
            call.enqueue(new Callback<ServicesResponse>() {
                @Override
                public void onResponse(Call<ServicesResponse> call, retrofit2.Response<ServicesResponse> response) {

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        UtilMethods.INSTANCE.setServicesPref(context, response.body().getAPPSERVICES().get(0).getPostpaid().toString(),
                                response.body().getAPPSERVICES().get(0).getPrepaid().toString(),
                                response.body().getAPPSERVICES().get(0).getLandline().toString(),
                                response.body().getAPPSERVICES().get(0).getDTH_Datacard().toString(),
                                response.body().getAPPSERVICES().get(0).getElectricity().toString(),
                                response.body().getAPPSERVICES().get(0).getDMR().toString(),
                                response.body().getAPPSERVICES().get(0).getHotel().toString(),
                                response.body().getAPPSERVICES().get(0).getFlight().toString(),
                                response.body().getAPPSERVICES().get(0).getInsurancePremium().toString(),
                                response.body().getAPPSERVICES().get(0).getGas().toString(),
                                response.body().getAPPSERVICES().get(0).getWaterBills().toString(),
                                response.body().getAPPSERVICES().get(0).getDTHConnections().toString());

                    } else
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);

                }

                @Override
                public void onFailure(Call<ServicesResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startingOperatorService(final Context context, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("here come", "operators : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.GetOperators(appInfo);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("here come", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null && mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                //  UtilMethods.INSTANCE.GetNumberList(context);
                                UtilMethods.INSTANCE.setOperatorList(context, new Gson().toJson(response.body()).toString(), mProgressDialog);
                                /*UtilMethods.INSTANCE.GetNumberList(context);*/
                                SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                                String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
                                String UPassword = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

                                if (UMobile != null && UMobile.length() > 0) {
                                    // ((SplashScreen) context).startDashboard();
                                } else {
                                    ((SplashScreen) context).startLogin();
                                }
                            } else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {
                            Log.e("response", "error " + e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error " + t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OfferSheet(final Context context, final ProgressDialog loader) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.OfferSheet(appInfo);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    Log.e("videoResp", "OfferSheet response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "OfferSheet");
                            GlobalBus.getBus().post(activityActivityMessage);
                            Intent browseIntent = new Intent(context, OfferSheetActivity.class);
                            browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                            context.startActivity(browseIntent);

                        } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                            Error(context, "redirectToLogin");
                            Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                            //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            //Failed(context,  response.body().getMessage());
                        }/* else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }*/ else {
//                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.err_attention_error_title),
//                                    response.body().getMessage(), 2);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Logout(final Context context, final ProgressDialog loader) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String key = UtilMethods.INSTANCE.getRegKey(context);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.Logout(appInfo);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                    Log.e("Logout", "Logout response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }

                    if (response.body() != null) {
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {


                            UtilMethods.INSTANCE.setLoginPref(context, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            UtilMethods.INSTANCE.setuserlist(context, "");
                            Intent startIntent = new Intent(context, LoginScreen.class);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(startIntent);
                        }  else {

                            Toast.makeText(context,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    Toast.makeText(context,"Failed to logout",Toast.LENGTH_LONG).show();



                }
            });

        } catch (Exception e) {
            if (loader != null) {
                if (loader.isShowing())
                    loader.dismiss();
            }

            Toast.makeText(context,"Failed to logout !",Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }

    }

    public void GetSaleCoupan(final Context context, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("GetNotice", "operators : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.GetSaleCoupan(appInfo);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("GetNoticeres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                String msg = response.body().getMessage().toString().replace("U+002F", ",");

                                UtilMethods.INSTANCE.dialogOk(context, "Sales Coupons",
                                        msg, 2);

                               /* int i;
                                String v0="";
                                String v1="";
                                String v2="";
                                String v3="";

                                String[] splitedValue = msg.split(",");

                                for(i=0;i<splitedValue.length;i++) {
                                    String b = String.valueOf(i);
                                    String a = "amo" + String.valueOf(i);
                                    if (a.equals("amo0"))
                                        v0 = splitedValue[i];
                                    else if (a.equals("amo1"))
                                        v1 = splitedValue[i];
                                    else if (a.equals("amo2"))
                                        v2 = splitedValue[i];
                                    else if (a.equals("amo3"))
                                        v3 = splitedValue[i];


                                    Log.e("GetNoticemsg", " msg " + msg);


                                }*/
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }/* else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetmyCoupan(final Context context, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);

        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        Log.e("GetNotice", "operators : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.GetmyCoupan(appInfo, password);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("GetNoticeres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                String msg = response.body().getMessage().toString().replace("U+002F", ",");

                                UtilMethods.INSTANCE.dialogOk(context, "My Coupon",
                                        msg, 2);

                               /* int i;
                                String v0="";
                                String v1="";
                                String v2="";
                                String v3="";

                                String[] splitedValue = msg.split(",");

                                for(i=0;i<splitedValue.length;i++) {
                                    String b = String.valueOf(i);
                                    String a = "amo" + String.valueOf(i);
                                    if (a.equals("amo0"))
                                        v0 = splitedValue[i];
                                    else if (a.equals("amo1"))
                                        v1 = splitedValue[i];
                                    else if (a.equals("amo2"))
                                        v2 = splitedValue[i];
                                    else if (a.equals("amo3"))
                                        v3 = splitedValue[i];


                                    Log.e("GetNoticemsg", " msg " + msg);


                                }*/
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }/* else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetNotice(final Context context, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("GetNotice", "operators : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.GetNotice(appInfo);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("GetNoticeres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                String msg = response.body().getMessage().toString().replace("U+002F", ",");

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.dthreport),
                                        msg, 2);

                               /* int i;
                                String v0="";
                                String v1="";
                                String v2="";
                                String v3="";

                                String[] splitedValue = msg.split(",");

                                for(i=0;i<splitedValue.length;i++) {
                                    String b = String.valueOf(i);
                                    String a = "amo" + String.valueOf(i);
                                    if (a.equals("amo0"))
                                        v0 = splitedValue[i];
                                    else if (a.equals("amo1"))
                                        v1 = splitedValue[i];
                                    else if (a.equals("amo2"))
                                        v2 = splitedValue[i];
                                    else if (a.equals("amo3"))
                                        v3 = splitedValue[i];


                                    Log.e("GetNoticemsg", " msg " + msg);


                                }*/
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            }/* else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetConnectionOperators(final Context context, final ProgressDialog mProgressDialog) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("Connection", "operators : " + appInfo);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetDthBookingResponse> call = git.GetConnectionOperators(appInfo);
            call.enqueue(new Callback<GetDthBookingResponse>() {
                @Override
                public void onResponse(Call<GetDthBookingResponse> call, retrofit2.Response<GetDthBookingResponse> response) {
                    Log.e("Connectionhere", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "dthconnectionname");
                                GlobalBus.getBus().post(activityActivityMessage);


                            } else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetDthBookingResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetDTHConnectionAmount(final Context context, final String dthid, final String languageid,
                                       final String packid, final String packageid, final ProgressDialog mProgressDialog) {

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("Connectionamount", "appinfo : " + appInfo + "    dthid : " + dthid + "    languageid : " + languageid
                + "   packid : " + packid + "      packageid : " + packageid);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetDthBookingResponse> call = git.GetDTHConnectionAmount(appInfo, dthid, languageid, packid, packageid);
            call.enqueue(new Callback<GetDthBookingResponse>() {
                @Override
                public void onResponse(Call<GetDthBookingResponse> call, retrofit2.Response<GetDthBookingResponse> response) {
                    Log.e("Connectionamountres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + response.body().getMessage().toString(), "dthconnectionamount");
                                GlobalBus.getBus().post(activityActivityMessage);


                            } else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetDthBookingResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BookDTHConnection(final Context context, final String UMobile, final String dthid, final String languageid,
                                  final String packid, final String packageid, final String Amount, final String customerName,
                                  final String HNONumber, final String landmark, final String address, final String mobileno1,
                                  final String mobileno2, final String pincode, final String packlanguagename, final ProgressDialog mProgressDialog) {

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);

        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId;
        Log.e("Connectionamount", "appinfo : " + appInfo + "    dthid : " + dthid + "    languageid : " + languageid
                + "   packid : " + packid + "      packageid : " + packageid + "Amount : " + Amount + "    customerName : " + customerName + "   " +
                " HNONumber : " + HNONumber + "   landmark : " + landmark + "      address : " + address + "   mobileno1 : " + mobileno1 + "      mobileno2 : " + mobileno2 + "  " +
                "    pincode : " + pincode + "   packlanguagename : " + packid + "   UMobile : " + UMobile);
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetDthBookingResponse> call = git.BookDTHConnection(appInfo, UMobile, dthid, languageid, packid, packageid,
                    Amount, customerName, HNONumber, landmark, address, mobileno1, mobileno2, pincode, packid);
            call.enqueue(new Callback<GetDthBookingResponse>() {
                @Override
                public void onResponse(Call<GetDthBookingResponse> call, retrofit2.Response<GetDthBookingResponse> response) {
                    Log.e("Connectionamountres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {


                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                        response.body().getMessage(), 2);


                            } else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetDthBookingResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetNews(final Context context, TextView news) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String key = UtilMethods.INSTANCE.getRegKey(context);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //String IMEI = UtilMethods.INSTANCE.getIMEI(context);
        String roleid = UtilMethods.INSTANCE.getRoleId(context);


        //  Log.e("GetNews", " appInfo : " + appInfo +"   password : " + password +"   roleid : " + roleid );
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<BankDetailResponse> call = git.GetNews(appInfo, roleid);
            call.enqueue(new Callback<BankDetailResponse>() {
                @Override
                public void onResponse(Call<BankDetailResponse> call, retrofit2.Response<BankDetailResponse> response) {
                    // Log.e("getGetNewsaaaaaa", "is :  " + new Gson().toJson(response.body().getMessage()).toString());
                    if (response.body() != null) {
                        try {

                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                Log.e("GetNews ", "aaaaaaaa " + new Gson().toJson(response.body().getMessage()).toString());
//
                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + response.body().getMessage().toString(), "news");
                                GlobalBus.getBus().post(activityActivityMessage);

                                  if(news!=null) {
                                      news.setText("" + response.body().getMessage());
                                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        ///  news.setText(Html.fromHtml("<marquee>Description here</marquee>", Html.FROM_HTML_MODE_COMPACT));
                                      } else {
                                        //  news.setText(Html.fromHtml("<marquee>Description here</marquee>"));
                                      }
                                    news  .setSelected(true);
                                  }
                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else {
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
//                                        response.body().getMessage(), 2);
                            }
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<BankDetailResponse> call, Throwable t) {
                    Log.e("response", "error " + t.getMessage());

                }
            });

        } catch (Exception e) {
            Log.e("response", "error " + e.getMessage());

            e.printStackTrace();
        }
    }

    public void UserSaleReportDetailAllDateWise(final Context context, String User, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("userre", " appinfo " + appInfo + "  password" + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.UserSaleReportDetailAllDateWise(appInfo, password, User);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("userreres", "UserSaleReportDetail : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                Intent browseIntent = new Intent(context, UserSaleReportDetailDateWise.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                browseIntent.putExtra("from", "" + "UserSaleReportDetailAllDateWise");
                                context.startActivity(browseIntent);

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }
    }

    public void UserSaleReportDetailDateWise(final Context context, String User, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("userre", " UserSaleReportDetailappinfo " + appInfo + "  password" + password + "  User " + User);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.UserSaleReportDetailDateWise(appInfo, password, User);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("userreres", "UserSaleReportDetail : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                                Intent browseIntent = new Intent(context, UserSaleReportDetailDateWise.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                browseIntent.putExtra("from", "" + "UserSaleReportDetailDateWise");
                                context.startActivity(browseIntent);
                               /* final LayoutInflater[] inflater = {(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)};
                                View view = inflater[0].inflate(R.layout.user_sale_report, null);

                                final TextView name = (TextView) view.findViewById(R.id.name);
                                final TextView number = (TextView) view.findViewById(R.id.number);
                                final TextView today = (TextView) view.findViewById(R.id.today);
                                final TextView month = (TextView) view.findViewById(R.id.month);
                                final TextView lmonth = (TextView) view.findViewById(R.id.lmonth);
                                final TextView salestarget = (TextView) view.findViewById(R.id.salestarget);
                                final TextView salestargetremaining = (TextView) view.findViewById(R.id.salestargetremaining);
                                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);
                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);
                                if ( response.body().getSaleReport()!=null   )
                                {
                                    name.setText( response.body().getSaleReport().get(0).getUserName());
                                    number.setText( response.body().getSaleReport().get(0).getMobile());
                                    today.setText( response.body().getSaleReport().get(0).getTodays());
                                    month.setText( response.body().getSaleReport().get(0).getMonths());
                                    lmonth.setText( response.body().getSaleReport().get(0).getLastMonths());
                                    salestarget.setText( response.body().getSaleReport().get(0).getSalesTarget());
                                    salestargetremaining.setText( response.body().getSaleReport().get(0).getRemaingSalesTarget());

                                }




                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });


                                dialog.show();*/

                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/ else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }
    }

    public void UserSaleReportDetail(final Context context, String User, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("userre", " appinfo " + appInfo + "  password" + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.UserSaleReportDetail(appInfo, password, User);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("userreres", "UserSaleReportDetail : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                final LayoutInflater[] inflater = {(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)};
                                View view = inflater[0].inflate(R.layout.user_sale_report, null);

                                final TextView name = (TextView) view.findViewById(R.id.name);
                                final TextView number = (TextView) view.findViewById(R.id.number);
                                final TextView today = (TextView) view.findViewById(R.id.today);
                                final TextView month = (TextView) view.findViewById(R.id.month);
                                final TextView lmonth = (TextView) view.findViewById(R.id.lmonth);
                                final TextView salestarget = (TextView) view.findViewById(R.id.salestarget);
                                final TextView salestargetremaining = (TextView) view.findViewById(R.id.salestargetremaining);
                                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);
                                final Dialog dialog = new Dialog(context);

                                dialog.setCancelable(false);
                                dialog.setContentView(view);
                                if (response.body().getSaleReport() != null) {
                                    name.setText(response.body().getSaleReport().get(0).getUserName());
                                    number.setText(response.body().getSaleReport().get(0).getMobile());
                                    today.setText(response.body().getSaleReport().get(0).getTodays());
                                    month.setText(response.body().getSaleReport().get(0).getMonths());
                                    lmonth.setText(response.body().getSaleReport().get(0).getLastMonths());
                                    salestarget.setText(response.body().getSaleReport().get(0).getSalesTarget());
                                    salestargetremaining.setText(response.body().getSaleReport().get(0).getRemaingSalesTarget());

                                }


                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });


                                dialog.show();

                            } else
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            t.getMessage(), 2);
                }
            });

        } catch (Exception e) {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
            e.printStackTrace();
            Log.e("error", e.getMessage());
        }
    }

    public void UserSaleReport(final Context context, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("userre", " appinfo " + appInfo + "  password" + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.UserSaleReport(appInfo, password);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("userreres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                String msg = "  Todays  " + response.body().getSaleReport().get(0).getTodays() + "  ,  " + " Months  " +
                                        response.body().getSaleReport().get(0).getMonths() + "  ,  " +
                                        "  LastMonths  " + response.body().getSaleReport().get(0).getLastMonths();

                                String msg1 = "" + response.body().getSaleReport().get(0).getTodays() + "," + "" +
                                        response.body().getSaleReport().get(0).getMonths() + "," +
                                        "" + response.body().getSaleReport().get(0).getLastMonths() + "," +
                                        "" + response.body().getSaleReport().get(0).getRemaingSalesTarget() + "," +
                                        "" + response.body().getSaleReport().get(0).getSalesTarget();
                                //  UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.userSaleew),
                                //     msg, 2);
                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + msg1, "usersalereport");
                                GlobalBus.getBus().post(activityActivityMessage);


                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                        //    }*/
                            //else
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
//                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UserSaleReportNew(final Context context, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        Log.e("userre", " appinfo " + appInfo + "  password" + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.UserSaleReportDetail(appInfo, password, mobileLogin);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("userreres", "operators : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null) {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        try {
                            if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                                String msg = "  Todays  " + response.body().getSaleReport().get(0).getTodays() + "  ,  " + " Months  " +
                                        response.body().getSaleReport().get(0).getMonths() + "  ,  " +
                                        "  LastMonths  " + response.body().getSaleReport().get(0).getLastMonths();

                                String msg1 = "" + response.body().getSaleReport().get(0).getTodays() + "," + "" +
                                        response.body().getSaleReport().get(0).getMonths() + "," +
                                        "" + response.body().getSaleReport().get(0).getLastMonths() + "," +
                                        "" + response.body().getSaleReport().get(0).getRemaingSalesTarget() + "," +
                                        "" + response.body().getSaleReport().get(0).getSalesTarget();
                                //  UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.userSaleew),
                                //     msg, 2);
                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" + msg1, "usersalereport");
                                GlobalBus.getBus().post(activityActivityMessage);


                            } else if (response.body().getMessage() != null && response.body().getMessage().contains("Invalid Session!")) {
                                Error(context, "redirectToLogin");
                                Log.e("error", context.getResources().getString(R.string.transaction_empty_message));
                                //Toast.makeText(context, "Failed...!" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                //Failed(context,  response.body().getMessage());
                            } /*else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }*/
//                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
//                                        response.body().getMessage(), 2);
                        } catch (Exception e) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("errorr", e.getMessage());
        }
    }

    public void DTHCustomerInfo(final Activity context, final String VcNumber, final String Roffer, final ProgressDialog loader) {

        Log.e("dthop", "VcNumber : " + VcNumber + " ,Roffer : " + Roffer);
        try {
            EndPointInterface git = ApiClientPlans.getClient().create(EndPointInterface.class);
            Call<ROfferResponse> call = git.GetCustomerInfo("9044004486", "8adda5c1681b9cdaaabd10d3bd90dad2", VcNumber, Roffer);
            call.enqueue(new Callback<ROfferResponse>() {
                @Override
                public void onResponse(Call<ROfferResponse> call, retrofit2.Response<ROfferResponse> response) {
                    Log.e("dthopres", "hello response : " + new Gson().toJson(response.body()).toString());
                    if (loader != null) {
                        if (loader.isShowing())
                            loader.dismiss();
                    }
                    try {
                        if (response.body() != null) {

                            if (response.body().getRecords().size() > 0) {

                                Intent browseIntent = new Intent(context, DthConsumerInfoScreen.class);
                                browseIntent.putExtra("response", "" + new Gson().toJson(response.body()).toString());
                                // browseIntent.putExtra("OpRefCircle", "" + OpRefCircle);
                                context.startActivity(browseIntent);

                            } else {

                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        "No offer Available !!", 2);

                                // UtilMethods.INSTANCE.dialogOk(context, "No offer Available !!",2);
                            }

                        } else {
                            Toast.makeText(context, "Service is not available right now!!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Log.e("exception", ex.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ROfferResponse> call, Throwable t) {
                    if (loader != null) {
                        if (loader.isShowing())
                            try {
                                loader.dismiss();
                            } catch (Exception e) {

                            }
                    }

                    UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                            "No offer Available !!", 2);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void secureLogin(final Context context, String mobile, final String password,
                            final String pushId, final String _FCMI, final ProgressDialog mProgressDialog) {

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String userName = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + ((char) 160) + deviceId + ((char) 160) + mobile;
        final String passCode = password;

        Log.e("response", "is : " + "mobile : " + userName + " , password : " + passCode + " ,devId : " + UtilMethods.INSTANCE.getKeyId(context));

        String keyId = "";
        if (UtilMethods.INSTANCE.getKeyId(context) != null && UtilMethods.INSTANCE.getKeyId(context).length() > 0) {
            keyId = UtilMethods.INSTANCE.getKeyId(context);
        } else {
            keyId = "";
        }

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<LoginResponse> call = git.secureLogin(userName, passCode, keyId, _FCMI);
            //  Call<LoginResponse> call = git.Logins(userName, passCode, keyId,_FCMI,"","");
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final retrofit2.Response<LoginResponse> response) {
                    if (response.body() != null)
                        Log.e("response", "is : " + new Gson().toJson(response.body()).toString());
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    if (response.body() != null)
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            if (response.body().getData().get(0).getOTP() != null &&
                                    response.body().getData().get(0).getOTP().toString().length() > 0) {

                                ////////////////////////////////////////////////////////////////////////////////
                                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View view = inflater.inflate(R.layout.otp_layout, null);

                                final TextInputLayout otpTextLayout = (TextInputLayout) view.findViewById(R.id.otpTextLayout);
                                final EditText otp = (EditText) view.findViewById(R.id.otp);
                                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

                                final Dialog dialog = new Dialog(context);

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
                                        if (otp.getText() != null && otp.getText().length() == 6) {
                                            otpTextLayout.setErrorEnabled(false);

                                            if (response.body().getData().get(0).getOTP().equalsIgnoreCase(
                                                    UtilMethods.INSTANCE.md5Convertor(otp.getText().toString().trim()))) {

                                                UtilMethods.INSTANCE.setLoginPref(context, passCode,
                                                        response.body().getData().get(0).getIsExist(),
                                                        response.body().getData().get(0).getOTP(),
                                                        response.body().getData().get(0).getSessionID(),
                                                        response.body().getData().get(0).getUMail(),
                                                        response.body().getData().get(0).getUMobile(),
                                                        response.body().getData().get(0).getUName(),
                                                        response.body().getData().get(0).getPinPassword(),
                                                        response.body().getData().get(0).getPrepaidWallet(),
                                                        response.body().getData().get(0).getUtilityWallet(),
                                                        response.body().getData().get(0).getKey(),
                                                        response.body().getData().get(0).getRoleId(),
                                                        response.body().getData().get(0).getSupportEmail(),
                                                        response.body().getData().get(0).getIcon(),
                                                        response.body().getData().get(0).getOutletId(),
                                                        response.body().getData().get(0).getSupportNumber(),
                                                        "1"
                                                        , response.body().getData().get(0).getUserID());

                                                if (UtilMethods.INSTANCE.getRegKey(context) != null &&
                                                        UtilMethods.INSTANCE.getRegKey(context).length() > 0) {
                                                    UtilMethods.INSTANCE.KeyUpdate(context, UtilMethods.INSTANCE.getKeyId(context));
                                                }
                                                UtilMethods.INSTANCE.App_Services_Provided(context);
                                                UtilMethods.INSTANCE.pinpasscode(context, response.body().getData().get(0).getPinPassword(), false);

                                                if (mProgressDialog != null)
                                                    ((LoginScreen) context).startDashboard();
                                                else
                                                    ((SplashScreen) context).startDashboard();

                                                dialog.dismiss();
                                            } else {
                                                otp.setError("Please enter a valid OTP !!");
                                                otp.requestFocus();
                                            }
                                        } else {
                                            otp.setError("Please enter a valid OTP !!");
                                            otp.requestFocus();
                                        }
                                    }
                                });
                                dialog.show();
                                ////////////////////////////////////////////////////////////////////////////////

                            } else {
                                UtilMethods.INSTANCE.setLoginPref(context, passCode,
                                        response.body().getData().get(0).getIsExist(),
                                        response.body().getData().get(0).getOTP(),
                                        response.body().getData().get(0).getSessionID(),
                                        response.body().getData().get(0).getUMail(),
                                        response.body().getData().get(0).getUMobile(),
                                        response.body().getData().get(0).getUName(),
                                        response.body().getData().get(0).getPinPassword(),
                                        response.body().getData().get(0).getPrepaidWallet(),
                                        response.body().getData().get(0).getUtilityWallet(),
                                        response.body().getData().get(0).getKey(),
                                        response.body().getData().get(0).getRoleId(),
                                        response.body().getData().get(0).getSupportEmail(),
                                        response.body().getData().get(0).getIcon(),
                                        response.body().getData().get(0).getOutletId(),
                                        response.body().getData().get(0).getSupportNumber(),
                                        "1",
                                        response.body().getData().get(0).getUserID());

                                if (UtilMethods.INSTANCE.getRegKey(context) != null &&
                                        UtilMethods.INSTANCE.getRegKey(context).length() > 0) {
                                    UtilMethods.INSTANCE.KeyUpdate(context, UtilMethods.INSTANCE.getKeyId(context));
                                }
                                UtilMethods.INSTANCE.App_Services_Provided(context);
                                UtilMethods.INSTANCE.pinpasscode(context, response.body().getData().get(0).getPinPassword(), false);

                                if (mProgressDialog != null)
                                    ((LoginScreen) context).startDashboard();
                                else
                                    ((SplashScreen) context).startDashboard();
                            }

                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassword(final Context context, String oldPassword, final String newPassword) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        final String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        final String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);

        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + UMobile + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.changePassword(appInfo, oldPassword, newPassword);
            call.enqueue(new Callback<HelloWorldResponse>() {
                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    // Log.e("response hello", "changepass response : " + new Gson().toJson(response.body()).toString());
                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(ApplicationConstant.INSTANCE.UPassword, newPassword);
                        editor.commit();

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                response.body().getMessage(), 33);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePinPassword(final Context context, final String newPassword, final String newPin) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        final String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        final String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);

        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + UMobile + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.changePinPassword(appInfo, newPassword, newPin);
            call.enqueue(new Callback<HelloWorldResponse>() {
                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    //Log.e("response hello", "changepass response : " + new Gson().toJson(response.body()).toString());
                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(ApplicationConstant.INSTANCE.PinPasscode, response.body().getPin());
                        editor.commit();

                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                response.body().getMessage() + " \n Your new Pin is " + response.body().getPin().toString(), 2);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PinSecureAuth(final Context context, final String newPin, final ApiCallBackTwoMethod apiCallBack) {
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        final String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        final String UPassword = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        final String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);

        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + UMobile + (char) 160 + SessionID;

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<HelloWorldResponse> call = git.PinSecureAuth(appInfo, UPassword, newPin);
            call.enqueue(new Callback<HelloWorldResponse>() {
                @Override
                public void onResponse(Call<HelloWorldResponse> call, retrofit2.Response<HelloWorldResponse> response) {
                    Log.e("response hello", "PinSecureAuth response : " + new Gson().toJson(response.body()).toString());
                    if (response.body() != null)
                        if (response.body().getRESPONSESTATUS() != null &&
                                response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {

                            apiCallBack.onSucess(response.body());

                        } else if (response.body().getMessage() != null) {
                            apiCallBack.onError(response.body().getMessage());

                        } else {
                            //apiCallBack.onError(response.body().getMessage());
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    context.getResources().getString(R.string.attention_error_title), 2);
                        }
                }

                @Override
                public void onFailure(Call<HelloWorldResponse> call, Throwable t) {
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Signup(final Context context, String _ShopName, String Name,
                       String Mobile, String _WhatsMobile, String _Address,
                       String _District, String _State, String _PIN,
                       String _AadharNo, String _PANNo, String email,
                       final ProgressDialog mProgressDialog, String role, final ApiCallBack apiCallBack) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);

        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + Mobile /*+ (char) 160 + SessionID*/;
        Log.e("req ", " appInfo " + appInfo + " _ShopName " + _ShopName + " Name " + Name + " Mobile " + Mobile + " _WhatsMobile " +
                _WhatsMobile + " _Address " +
                _Address + " _District" + _District + "_State_ " + _State + " _PIN  " + _PIN + " _AadharNo " + _AadharNo + "  panno " + _PANNo);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            //     Call<RegisterResponse> call = git.UserCreation(appInfo, password, Name, Email, Mobile, PIN);
//            Call<RegisterResponse> call = git.Signup(appInfo, _ShopName, Name,   Mobile,_WhatsMobile,
//                    _Address, _District, _State, _PIN, _AadharNo, _PANNo ,role,email);

            Call<RegisterResponse> call = git.Signup(Name, _ShopName, "",
                    _Address, "", "", Mobile, "", _PANNo
                    , "", _AadharNo, "", "", "", "", email);
            // //FName:
            //    //ApplicantName:
            //    //fatherName:
            //    //Address:
            //    //OfficeAddress:
            //    //ResidenceAddress:
            //    //Mobile:
            //    //landline:
            //    //PANNo:
            //    //GSTNo:
            //    //AAdharNo:
            //    //BankName:
            //    //BranchAddress:
            //    //BankAccount:
            //    //IFSCCode:
            //    //EmailId:
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, final retrofit2.Response<RegisterResponse> response) {

                    //Log.e("response", "is : " + new Gson().toJson(response.body()).toString());

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body() != null)
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            apiCallBack.onSucess(response.body());
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 13);
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UserCreation(final Context context, String _ShopName, String Name,
                             String Mobile, String _WhatsMobile, String _Address,
                             String _District, String _State, String _PIN,
                             String _AadharNo, String _PANNo, String email, final ProgressDialog mProgressDialog, String role, final ApiCallBack apiCallBack) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);

        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;


        Log.e("req ", " appInfo " + appInfo + " _ShopName " + _ShopName + " Name " + Name + " Mobile " + Mobile + " _WhatsMobile " +
                _WhatsMobile + " _Address " +
                _Address + " _District" + _District + "_State_ " + _State + " _PIN  " + _PIN + " _AadharNo " + _AadharNo + "  panno " + _PANNo);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            //     Call<RegisterResponse> call = git.UserCreation(appInfo, password, Name, Email, Mobile, PIN);
            Call<RegisterResponse> call = git.UserCreation(appInfo, password, _ShopName, Name, Mobile, _WhatsMobile,
                    _Address, _District, _State, _PIN, _AadharNo, _PANNo, role, email);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, final retrofit2.Response<RegisterResponse> response) {

                    //Log.e("response", "is : " + new Gson().toJson(response.body()).toString());

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body() != null)
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            apiCallBack.onSucess(response.body());
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 13);
                        } else {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void userCreation(final Context context, String Name, String Email,
                             String Mobile, String PIN, final ProgressDialog mProgressDialog) {

        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

        //Log.e("response", "is : " + "mobile : " + appInfo + " , password : " + password);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RegisterResponse> call = git.UserCreation(appInfo, password, Name, Email, Mobile, PIN);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, final retrofit2.Response<RegisterResponse> response) {

                    //Log.e("response", "is : " + new Gson().toJson(response.body()).toString());

                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                response.body().getMessage(), 3);
                    } else {
                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                response.body().getMessage(), 2);
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Log.e("response", "error ");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setOperatorList(Context context, String operatorList, final ProgressDialog mProgressDialog) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.operatorListPref, operatorList);
        editor.commit();


    }

    public void setSenderInfo(Context context, String senderInfo, String senderNumber, boolean flag, final ProgressDialog mProgressDialog) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.senderInfoPref, senderInfo);
        editor.commit();

        if (flag) {
            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
            UtilMethods.INSTANCE.GetBeneficiary(context, senderNumber, mProgressDialog,null);
        }
    }

    public void setSenderNumber(Context context, String senderNumber) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.senderNumberPref, senderNumber);
        editor.commit();
    }

    public void setNumberList(Context context, String numberList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.numberListPref, numberList);
        editor.commit();

    }

    public void setBankDetailList(Context context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankDetailListPref, bankList);
        editor.commit();

    }

    public void setBankList(Context context, String bankList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.bankListPref, bankList);
        editor.commit();
    }

    public void setRecentLogin(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regRecentLoginPref, key);
        editor.commit();
    }

    public String getRecentLogin(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regRecentLoginPref, null);
        return key;
    }

    public void setRegKey(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regKeyPref, key);
        editor.commit();
    }

    public String getRegKey(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regKeyPref, null);
        return key;
    }

    public void setKeyId(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regKeyIdPref, key);
        editor.commit();
    }

    public String getKeyId(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regKeyIdPref, null);
        return key;
    }

    public void setRegKeyStatus(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.regKeyStatusPref, key);
        editor.commit();
    }

    public String getRegKeyStatus(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        String key = myPrefs.getString(ApplicationConstant.INSTANCE.regKeyStatusPref, null);
        return key;
    }

    public void setBalanceCheck(Context context, String balance) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.balancePref, balance);
        editor.commit();

        ActivityActivityMessage activityActivityMessage =
                new ActivityActivityMessage("balanceUpdate");
        GlobalBus.getBus().post(activityActivityMessage);
    }

    public void setPopup(Context context, String popupdata) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        Log.e("response", popupdata);

        editor.putString(ApplicationConstant.INSTANCE.popupPref, popupdata);
        editor.commit();

        ActivityActivityMessage activityActivityMessage =
                new ActivityActivityMessage("popupdataUpdate");
        GlobalBus.getBus().post(activityActivityMessage);
    }

    public void setBeneficiaryList(Context context, String beneficiaryList) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.beneficiaryListPref, beneficiaryList);
        editor.commit();
    }

    public void setuserlist(Context context, String userlist) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(ApplicationConstant.INSTANCE.userlist, userlist);
        editor.commit();

    }

    public void setLoginPref(Context context, String password, String IsExist, String OTP, String SessionID, String UMail, String UMobile,
                             String UName, String PinPassword, String PrepaidWallet, String UtilityWallet, String Key, String RoleId, String supportEmail, String icon, String outletid, String supportNumber, String prefApi, String UserID) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(ApplicationConstant.INSTANCE.IsExist, IsExist);
        editor.putString(ApplicationConstant.INSTANCE.OTP, OTP);
        editor.putString(ApplicationConstant.INSTANCE.SessionID, SessionID);
        editor.putString(ApplicationConstant.INSTANCE.UMail, UMail);
        editor.putString(ApplicationConstant.INSTANCE.UMobile, UMobile);
        editor.putString(ApplicationConstant.INSTANCE.UPassword, password);
        editor.putString(ApplicationConstant.INSTANCE.UName, UName);
        editor.putString(ApplicationConstant.INSTANCE.PinPasscode, PinPassword);
        editor.putString(ApplicationConstant.INSTANCE.PrepaidWallet, PrepaidWallet);
        editor.putString(ApplicationConstant.INSTANCE.UtilityWallet, UtilityWallet);
        editor.putString(ApplicationConstant.INSTANCE.Key, Key);
        editor.putString(ApplicationConstant.INSTANCE.RoleId, RoleId);
        editor.putString(ApplicationConstant.INSTANCE.supportEmail, supportEmail);
        editor.putString(ApplicationConstant.INSTANCE.icon, icon);
        editor.putString(ApplicationConstant.INSTANCE.outletid, outletid);
        editor.putString(ApplicationConstant.INSTANCE.supportNumber, supportNumber);
        editor.putString(ApplicationConstant.INSTANCE.prefapi, prefApi);
        editor.putString(ApplicationConstant.INSTANCE.UserID, UserID);
        editor.commit();

    }

    public void setServicesPref(Context context, String Postpaid, String Prepaid, String Landline, String DTH_Datacard, String Electricity,
                                String DMR, String Hotel, String Flight, String InsurancePremium, String Gas, String WaterBills,
                                String DTHConnections) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.servicesPref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(ApplicationConstant.INSTANCE.Services_Postpaid, Postpaid);
        editor.putString(ApplicationConstant.INSTANCE.Services_Prepaid, Prepaid);
        editor.putString(ApplicationConstant.INSTANCE.Services_Landline, Landline);
        editor.putString(ApplicationConstant.INSTANCE.Services_DTH_Datacard, DTH_Datacard);
        editor.putString(ApplicationConstant.INSTANCE.Services_Electricity, Electricity);
        editor.putString(ApplicationConstant.INSTANCE.Services_DMR, DMR);
        editor.putString(ApplicationConstant.INSTANCE.Services_Hotel, Hotel);
        editor.putString(ApplicationConstant.INSTANCE.Services_Flight, Flight);
        editor.putString(ApplicationConstant.INSTANCE.Services_InsurancePremium, InsurancePremium);
        editor.putString(ApplicationConstant.INSTANCE.Services_Gas, Gas);
        editor.putString(ApplicationConstant.INSTANCE.Services_WaterBills, WaterBills);
        editor.putString(ApplicationConstant.INSTANCE.Services_DTHConnections, DTHConnections);
        editor.commit();

    }

    public String getDMRService(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.servicesPref, context.MODE_PRIVATE);
        String DMRService = myPrefs.getString(ApplicationConstant.INSTANCE.Services_DMR, null);

        return DMRService;
    }

    public String getRoleId(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, context.MODE_PRIVATE);
        String RoleId = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);

        return RoleId;
    }

    //
//GetGiftVoucher
    public void LookUpApi(final Context context, final String mob, final ProgressDialog mProgressDialog, final ApiCallBack apiCallBack) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        String outletName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

        Log.e("LookUpApi ", " appInfo   " + appInfo + "   password " + password + "   mob  " + mob);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GetOperatorResponse> call = git.LookUpApi(appInfo, password, mob);
            call.enqueue(new Callback<GetOperatorResponse>() {
                @Override
                public void onResponse(Call<GetOperatorResponse> call, retrofit2.Response<GetOperatorResponse> response) {
                    Log.e("LookUpApi", "hello response : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        if (response.body() != null)
                            if (response.body().getStatus().equalsIgnoreCase("1")) {
//                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
//                                    response.body().getStatuscode(), 3);


                                apiCallBack.onSucess(response.body());
                                FragmentActivityMessage activityActivityMessage =
                                        new FragmentActivityMessage("" +
                                                new Gson().toJson(response.body()).toString(), "LookUpApi");
                                GlobalBus.getBus().post(activityActivityMessage);
                                //
                            } else {
                                UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                        response.body().getMessage(), 2);
                            }
//                        else if (response.body().getStatus().equalsIgnoreCase("3")) {
//                            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
//                        }
                        else {

//                        UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
//                                response.body().getStatus(), 2);
                        }
                    } catch (Exception e) {
                        Log.e("type", "type" + e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<GetOperatorResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void GetGiftVoucher(final Context context, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        String outletName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

//        Log.e("OutletRegistration ", "  " + appInfo + "   password " + password + "   outletName  " + outletName + "   Name " + Name +
//                "   Email " + Email + "   sendermobile " + sendermobile + "   pincode " + Pincode + "   adress " + Address
//                +  "   pan " + Pan + "   OTP " + OTP+ "   Latlong " + Latlong);
//        ;
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GiftCardServicesResponse> call = git.GetGiftVoucher();
            call.enqueue(new Callback<GiftCardServicesResponse>() {
                @Override
                public void onResponse(Call<GiftCardServicesResponse> call, retrofit2.Response<GiftCardServicesResponse> response) {
                    Log.e("giftcard", "hello response : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        if (response.body().getStatus().equalsIgnoreCase("1")) {
//                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
//                                    response.body().getStatuscode(), 3);
                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "giftcarddetail");
                            GlobalBus.getBus().post(activityActivityMessage);
                            //
                        }
//                        else if (response.body().getStatus().equalsIgnoreCase("3")) {
//                            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
//                        }
                        else {

                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getStatuscode(), 2);
                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<GiftCardServicesResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetGiftVoucherRecharge(final Context context, String Code, String gv_amt, String gv_sndr_name, String gv_sndr_email, String gv_recv_name,
                                       String gv_recv_email, String gv_sndr_address, String gv_sndr_pincode, String gv_sndr_city, String gv_sndr_state,
                                       String gv_sndr_mobile, String gv_recv_address, String gv_recv_pincode, String gv_recv_city,
                                       String gv_recv_state, String gv_recv_mobile, String gv_gift_msg, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String _UPassword = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String _UMobile = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        String outletName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

        Log.e("GetGiftVoucherRecharge ", "appInfo   " + _UMobile + "   password " + _UPassword + "   Code " + Code + "   gv_amt " + gv_amt + "   gv_sndr_name " + gv_sndr_name + "   gv_sndr_email " + gv_sndr_email + "   gv_recv_name " +
                gv_recv_name + "   gv_recv_email " + gv_recv_email + "   gv_sndr_address " + gv_sndr_address + "   gv_sndr_pincode " +
                gv_sndr_pincode + "   gv_sndr_city " + gv_sndr_city + "   gv_sndr_state " + gv_sndr_state + "   gv_sndr_mobile " +
                gv_sndr_mobile + "   gv_recv_address " + gv_recv_address + "   gv_recv_pincode " + gv_recv_pincode + "   gv_recv_city " +
                gv_recv_city + "   gv_recv_state " + gv_recv_state + "   gv_recv_mobile " + gv_recv_mobile + "   gv_gift_msg " +
                gv_gift_msg);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<GiftCardServicesResponse> call = git.GetGiftVoucherRecharge(_UMobile, _UPassword, Code, gv_amt, gv_sndr_name,
                    gv_sndr_email, gv_recv_name, gv_recv_email, gv_sndr_address, gv_sndr_pincode, gv_sndr_city, gv_sndr_state,
                    gv_sndr_mobile, gv_recv_address, gv_recv_pincode, gv_recv_city, gv_recv_state, gv_recv_mobile,
                    gv_gift_msg);
            call.enqueue(new Callback<GiftCardServicesResponse>() {
                @Override
                public void onResponse(Call<GiftCardServicesResponse> call, retrofit2.Response<GiftCardServicesResponse> response) {
                    Log.e("GetGiftVoucherRecharge", "hello response : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 11);
                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "GetGiftVoucherRecharge");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else {

                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<GiftCardServicesResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OutletRegistration(final Context context, final String Name, final String Email, final String sendermobile, final String Pincode, final String Address, final String Pan, final String OTP, final String Latlong, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
        String outletName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

        Log.e("OutletRegistration ", "  " + appInfo + "   password " + password + "   outletName  " + outletName + "   Name " + Name +
                "   Email " + Email + "   sendermobile " + sendermobile + "   pincode " + Pincode + "   adress " + Address
                + "   pan " + Pan + "   OTP " + OTP + "   Latlong " + Latlong);
        ;
        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RegisterResponse> call = git.OutletRegistration(appInfo, password, sendermobile, outletName, Name, Email,
                    Pan, Pincode, Address, OTP, Latlong);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, retrofit2.Response<RegisterResponse> response) {
                    Log.e("OutletRegistra", "hello response : " + new Gson().toJson(response.body()).toString());

                    try {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }

                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 3);
                            FragmentActivityMessage activityActivityMessage =
                                    new FragmentActivityMessage("" + new Gson().toJson(response.body()).toString(), "outletRegistered");
                            GlobalBus.getBus().post(activityActivityMessage);

                        } else if (response.body().getRESPONSESTATUS().equalsIgnoreCase("3")) {
                            UtilMethods.INSTANCE.Logout(context, mProgressDialog);
                            UtilMethods.INSTANCE.setSenderNumber(context, "");
                            UtilMethods.INSTANCE.setSenderInfo(context, "", "", false, null);
                            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
                        } else {

                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.attention_error_title),
                                    response.body().getMessage(), 2);
                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    Log.e("response", "error ");
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

    //
//
//    public void GetOutlet(final Context context, final String SenderMobile, final ProgressDialog mProgressDialog) {
//        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
//        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
//        final String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
//        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
//        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
//        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;
//
//
//        Log.e("OutletRegOTP", " appinfo " + appInfo + "   password " + password + "  Sendernumber  " + SenderMobile);
//
//        try {
//            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
//            Call<OTPResponse> call = git.GetOutlet(appInfo, password, SenderMobile);
//            call.enqueue(new Callback<OTPResponse>() {
//                @Override
//                public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
//                    Log.e("OutletRegOTPres", "hello response : " + new Gson().toJson(response.body()).toString());
//
//                    try {
//                        if (mProgressDialog != null) {
//                            if (mProgressDialog.isShowing())
//                                mProgressDialog.dismiss();
//                        }
//
//                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
//
//                            UtilMethods.INSTANCE.OutletRegistartionres(context, new Gson().toJson(response.body()).toString());
//
//                            Intent i = new Intent(context, DMRLogin.class);
//                            context.startActivity(i);
//
//
//                        } else if (response.body().getRESPONSESTATUS().equalsIgnoreCase("3")) {
//                            UtilMethods.INSTANCE.logout(context);
//                            UtilMethods.INSTANCE.setSenderNumber(context, "");
//                            UtilMethods.INSTANCE.setSenderInfo(context, "", "", false, null);
//                            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
//                        } else {
//
//                            UtilMethods.INSTANCE.OutletRegistartionres(context, new Gson().toJson(response.body()).toString());
//
//
//                           /* Intent i=new Intent(this,OutletRegistration.class);
//                            startActivity(i);*/
//
//
//                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setContentText("Outlet Not  Exist Plz Registration")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//
//                                            Intent i = new Intent(context, OutletRegistration.class);
//                                            context.startActivity(i);
//
//
//                                            sweetAlertDialog.dismiss();
//                                        }
//                                    })
//                                    .show();
//
//
//                        }
//                    } catch (Exception e) {
//
//                    }
//
//
//                }
//
//                @Override
//                public void onFailure(Call<OTPResponse> call, Throwable t) {
//                    if (loader != null) {
//                        if (loader.isShowing())
//                            loader.dismiss();
//                    }
//                }
//
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    public void OutletRegistartionres(Context context, String OutletRegistartionres) {
        Log.e("OutletRegistartionres ", " OutletRegistartionres " + OutletRegistartionres);
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ApplicationConstant.INSTANCE.OutletRegistartionres, OutletRegistartionres);
        editor.commit();

    }

    public void OutletRegistartionreOTP(final Context context, final String SenderMobile, final ProgressDialog mProgressDialog) {
        SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(context);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;


        Log.e("OutletRegOTP", " appinfo " + appInfo + "   password " + password + "  Sendernumber  " + SenderMobile);

        try {
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<RegisterResponse> call = git.OutletRegistartionOTP(appInfo, password, SenderMobile);
            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, retrofit2.Response<RegisterResponse> response) {
                    Log.e("OutletRegOTPres", "hello response : " + new Gson().toJson(response.body()).toString());

                    /*  Intent i=new Intent(Home.this,DMRLogin.class);
            startActivity(i);*/
                    try {
                        if (mProgressDialog != null) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                        }
                        if (response.body().getRESPONSESTATUS().equalsIgnoreCase("1")) {
                            UtilMethods.INSTANCE.OutletRegistartionres(context, new Gson().toJson(response.body()).toString());
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 1);

//                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setContentText(response.body().getMessage())
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//
//                                           /* Intent i=new Intent( context,DMRLogin.class);
//                                            context.startActivity(i);*/
//
//
//                                            sweetAlertDialog.dismiss();
//                                        }
//                                    })
//                                    .show();
                            //UtilMethods.INSTANCE.Successful(context, response.body().getMessage());
                        } else if (response.body().getRESPONSESTATUS().equalsIgnoreCase("3")) {
                            UtilMethods.INSTANCE.Logout(context, mProgressDialog);
                            UtilMethods.INSTANCE.setSenderNumber(context, "");
                            UtilMethods.INSTANCE.setSenderInfo(context, "", "", false, null);
                            UtilMethods.INSTANCE.setBeneficiaryList(context, "");
                        } else {
                            UtilMethods.INSTANCE.OutletRegistartionres(context, new Gson().toJson(response.body()).toString());
                            UtilMethods.INSTANCE.dialogOk(context, context.getResources().getString(R.string.successful_title),
                                    response.body().getMessage(), 1);


                            /* Intent i=new Intent(this,OutletRegistration.class);
                            startActivity(i);*/
//                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setContentText(response.body().getMessage())
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                            /*Intent i=new Intent( context,OutletRegistration.class);
//                                            context.startActivity(i);*/
//                                            sweetAlertDialog.dismiss();
//                                        }
//                                    })
//                                    .show();
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    if (mProgressDialog != null) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatedAmount(String value) {
        if (value != null && !value.isEmpty()) {
            if (value.contains(".")) {
                String postfixValue = value.substring(value.indexOf("."));
                if (postfixValue.equalsIgnoreCase(".0")) {
                    return value.replace(".0", "");
                } else if (postfixValue.equalsIgnoreCase(".00")) {
                    return value.replace(".00", "");
                } else if (postfixValue.equalsIgnoreCase(".000")) {
                    return value.replace(".000", "");
                } else if (postfixValue.equalsIgnoreCase(".0000")) {
                    return value.replace(".0000", "");
                } else {
                    return value;
                }
            } else {
                return value;
            }

        } else {
            return "0";
        }
    }

    public void Processing(final Context context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Warning(message);
    }

    public void Error(final Context context, final String message) {
        if ((message != null && !message.equalsIgnoreCase(""))) {
            if (message.contains("(redirectToLogin)")) {
                Logout(context, null);
            } else {
                CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
                customAlertDialog.Error(message);
            }
        }
    }

    public void Successful(final Context context, final String message) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(context, true);
        customAlertDialog.Successful(message);
    }

    @SuppressLint("MissingPermission")
    public String getSerialNo(Context context) {
        String serialNo = "";
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            // only for gingerbread and newer versions

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            serialNo = android.os.Build.getSerial() + "";
        } else if (android.os.Build.VERSION.SDK_INT >= 25) {
            serialNo = android.os.Build.SERIAL + "";
        }
        Log.e("seriolNo", serialNo);
        return serialNo;
    }

    public void RazorpayUpdate(final Context context, String PaymentStatus, String PaymentOrderId,
                               String PaymentId,
                               String Entity,
                               String CreatedAt,
                               String Amount,
                               String Method,
                               String Amount_Refund,
                               String Refund_Status,
                               String CardId,
                               String Bank,
                               String Wallet,
                               String ContactNo /*RazorpayresponseUpdate razorpayresponseUpdate*/, final CustomLoader loader) {
        try {
            loader.show();
            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            SharedPreferences myPrefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);


            Call<BasicResponse> call = git.RazorpayUpdate(new RazorpayresponseUpdate(PaymentStatus, PaymentOrderId,
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
                            ContactNo
                    /*razorpayresponseUpdate/*,
                    ApplicationConstant.INSTANCE.APP_ID,
                    UtilMethods.INSTANCE.getIMEI(context),
                    SessionID*/
                    )
            );


            call.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, final retrofit2.Response<BasicResponse> response) {

                    try {
                        if (loader.isShowing())
                            loader.dismiss();
                        if ((response.body() != null && response.body().getStatuscode() != null &&
                                response.body().getStatuscode().equals("1"))
                                || (response.body() != null && response.body().getMsg() != null)
                                /*
                                ||(response.body() != null && response.body().getStatuscode()!=null &&
                                response.body().getStatuscode().equals("1"))*/) {


                            // UtilMethods.INSTANCE.Successful(context, "   "+response.body().getMsg());

                            UtilMethods.INSTANCE.dialogOk(context, context.getString(R.string.successful_title),
                                    "   " + response.body().getMsg(), 12);
                        } else {
                            if (response.body() != null && response.body().getMsg() != null)
                                // UtilMethods.INSTANCE.Error(context, "  "+response.body().getMsg());
                                UtilMethods.INSTANCE.dialogOk(context, context.getString(R.string.failed),
                                        "  " + response.body().getMsg(), 12);
                        }

                    } catch (Exception e) {
                        if (loader.isShowing())
                            loader.dismiss();

                        UtilMethods.INSTANCE.dialogOk(context, context.getString(R.string.failed),
                                e.getMessage() + "", 2);
                        //UtilMethods.INSTANCE.Error(context, e.getMessage() + "");
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
                                //  Toast.makeText(context, "hehehehehe", Toast.LENGTH_SHORT).show();
                                UtilMethods.INSTANCE.Error(context, context.getString(R.string.err_msg_network));


                            } else {
                                // Toast.makeText(context, "ohoohoh", Toast.LENGTH_SHORT).show();
                                UtilMethods.INSTANCE.Error(context, t.getMessage());


                            }

                        } else {

                            UtilMethods.INSTANCE.Error(context, context.getString(R.string.some_thing_error));
                        }
                    } catch (IllegalStateException ise) {
                        loader.dismiss();

                        UtilMethods.INSTANCE.Error(context, context.getString(R.string.some_thing_error));
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (loader.isShowing())
                loader.dismiss();
            // Toast.makeText(context, "uiofdshuhfjsdds", Toast.LENGTH_SHORT).show();
            UtilMethods.INSTANCE.Error(context, e.getMessage() + "");
        }
    }

   /* public String getLoginPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,context.MODE_PRIVATE);
        return prefs.getString(ApplicationConstant.INSTANCE.LoginPref,"");

    }*/ //Invalid Session!  redirectToLogin


    public interface ApiCallBack {
        void onSucess(Object object);
    }

    public interface ApiCallBackTwoMethod {
        void onSucess(Object object);

        void onError(String errorMsg);
    }

    public interface ApiCallBackDMRMethod {
        void onSucess(Object object);
        void onAuthRequired(Object object);

        void onError(String errorMsg);
    }
}
