package com.bababansiwalanew.Dashboard.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.Activities.NoticeBoardActivity;
import com.bababansiwalanew.Activities.PaymentRequest;
import com.bababansiwalanew.BalanceCheck.dto.BalanceCheckResponse;
import com.bababansiwalanew.BalanceCheck.dto.ChildBalance;
import com.bababansiwalanew.GooglePlayStoreAppVersionNameLoader;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;

import static android.content.pm.PackageManager.GET_META_DATA;

public class Dashboard3 extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog = null;
    TextView balanceUtility, balancePrepaid;
    TextView iv_11, iv_12;
    TextView iv_13;
    LinearLayout balancePrepaidLayout, balanceUtilityLayout;
    TextView news;
    FragmentManager fm;
    String time;
    Spinner lang_Spinner;


    private String version = "";
    String versionName = "";
    int versionCode = -1;
    private static long back_pressed;
    public static int countBackstack = 0;
    public static FragmentManagerHelper fragmentManagerHelper;
    public static FragmentManagerHelper getFragmentManagerHelper;

    @Override
    public void onClick(View v) {
        if ((v == iv_11) || v == iv_12 || v == iv_13) {
            showCC();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard);
       mProgressDialog = new ProgressDialog(this);
        version = GooglePlayStoreAppVersionNameLoader.newVersion;
        checkNumberList();
        getVersionInfo();
        ShowPopup();
        ShowPopupNow();
      fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_container, new HomeFragment(), "Home");
        transaction.addToBackStack(null);
        transaction.commit();

        resetTitles();
        if (UtilMethods.INSTANCE.getKeyId(Dashboard3.this) != null &&
                UtilMethods.INSTANCE.getKeyId(Dashboard3.this).length() > 0) {
            if (UtilMethods.INSTANCE.getRegKeyStatus(Dashboard3.this) != null &&
                    UtilMethods.INSTANCE.getRegKeyStatus(Dashboard3.this).length() > 0) {

            } else {
                UtilMethods.INSTANCE.KeyUpdate(Dashboard3.this, UtilMethods.INSTANCE.getKeyId(Dashboard3.this));
            }
        } else {

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String IsExist = myPrefs.getString(ApplicationConstant.INSTANCE.IsExist, null);
        String OTP = myPrefs.getString(ApplicationConstant.INSTANCE.OTP, null);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String UMail = myPrefs.getString(ApplicationConstant.INSTANCE.UMail, null);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String UName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);
        String role = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, null);

        balanceUtility = (TextView) findViewById(R.id.balanceUtility);
        balancePrepaid = (TextView) findViewById(R.id.balancePrepaid);
        iv_11 = (TextView) findViewById(R.id.iv_11);

        iv_12 = (TextView) findViewById(R.id.iv_12);
        iv_13 = (TextView) findViewById(R.id.iv_13);
        iv_11.setText("" + UName);
         if (role.equalsIgnoreCase("3")){
             iv_12.setText("" + "Role : Retailer");
         }
             else if (role.equalsIgnoreCase("2")){
             iv_12.setText("" + "Role : Distributor");
         }
             else if (role.equalsIgnoreCase("3")){
             iv_12.setText("" + "Role : Retailer");
         }
             else if (role.equalsIgnoreCase("4")){
             iv_12.setText("" + "Role : ApiUser");
         }
             else if (role.equalsIgnoreCase("6")){
             iv_12.setText("" + "Role : Subadmin");
         }
             else if (role.equalsIgnoreCase("8")){
             iv_12.setText("" + "Role : MasterDistributor");
         }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        balanceUtility = (TextView) findViewById(R.id.balanceUtility);
        balancePrepaid = (TextView) findViewById(R.id.balancePrepaid);
        iv_11 = (TextView) findViewById(R.id.iv_11);
        iv_12 = (TextView) findViewById(R.id.iv_12);
        iv_13 = (TextView) findViewById(R.id.iv_13);
        //tv_cc = (TextView) findViewById(R.id.tv_cc);
        balancePrepaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCC();
            }


        });
        iv_11.setOnClickListener(this);
        iv_12.setOnClickListener(this);
        iv_13.setOnClickListener(this);

        balancePrepaidLayout = (LinearLayout) findViewById(R.id.balancePrepaidLayout);
        balanceUtilityLayout = (LinearLayout) findViewById(R.id.balanceUtilityLayout);

        news =  findViewById(R.id.tvNews);
        UtilMethods.INSTANCE.GetNews(this, news);
        lang_Spinner = (Spinner) findViewById(R.id.lang_Spinner);
       news.setSelected(true);

        BalanceRefresh();

    }

    private void showCC() {


    }

    private void checkNumberList() {
        if (getNumberList() != null && !getNumberList().isEmpty()) {

        } else {
            UtilMethods.INSTANCE.GetNumberList(Dashboard3.this, null);
        }
    }

    public String getNumberList() {
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);

        return prefs.getString(ApplicationConstant.INSTANCE.numberListPref, null);
    }

    protected void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
  private void getVersionInfo() {

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;

            Log.e("vers", versionName);
            //   UtilMethods.INSTANCE.UserSaleReportNew(Dashboard3.this,  mProgressDialog);
            UtilMethods.INSTANCE.BankDetail(Dashboard3.this, null);
            UtilMethods.INSTANCE.BalanceCheck(this, null);
            //uncomment here
            //UtilMethods.INSTANCE.VideoGallery(this, null);
            UtilMethods.INSTANCE.Popup(this, null);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count != 1) {
                fm = getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction transaction = fm.beginTransaction();
                int count1 = fm.getBackStackEntryCount();
                transaction.replace(R.id.fragment_container, new HomeFragment(), "Home");
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
               /* new SweetAlertDialog(Dashboard3.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Do you want Exit?")
                        .setCancelText("No,cancel!")
                        .setConfirmText("Yes,Exit!")

                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finish();
//
                            }
                        })
                        .show();*/

                AlertDialog.Builder customBuilder = new AlertDialog.Builder(Dashboard3.this)
                        .setTitle("Do You want Exit?")
                        //.setMessage(message)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // "OK" button was clicked
                                dialogInterface.dismiss();
                                finish();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // "OK" button was clicked

                                dialogInterface.dismiss();

                            }
                        });
                AlertDialog dialog = customBuilder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                    }
                });
                dialog.show();
            }
//uncomment here
            // UtilMethods.INSTANCE.VideoGallery(this, mProgressDialog);
            UtilMethods.INSTANCE.GetNews(this, news);
            back_pressed = System.currentTimeMillis();

        } catch (Exception e) {
        }

    }

    public static void ChangeFragment(FragmentManager fragmentManager, Fragment fragment) {

//
//        String backStateName =  fragment.getClass().getName();
//        String fragmentTag = backStateName;
//
//        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
//
//        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.replace(R.id.content_frame, fragment, fragmentTag);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.addToBackStack(backStateName);
//            ft.commit();
//        }
//
//


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerpager, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return;
    }

    private void ShowPopup() {
        try {
            SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String popupresponse = myPreferences.getString(ApplicationConstant.INSTANCE.popupPref, null);
            //Log.e("response",popupresponse);

            if (popupresponse != null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.layoutpoop, null);

                final ImageView dialog_logo = (ImageView) view.findViewById(R.id.dialog_logo);
                final AppCompatTextView title = (AppCompatTextView) view.findViewById(R.id.dialog_title);
                final ImageView okButton = (ImageView) view.findViewById(R.id.okButton);
                final AppCompatTextView content = (AppCompatTextView) view.findViewById(R.id.dialog_content);

                final Dialog dialog = new Dialog(this);

                dialog.setContentView(view);

                BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(popupresponse, BalanceCheckResponse.class);
                Log.e("response", popupresponse.toString());


                ChildBalance childBalance = balanceCheckResponse.getDATA().get(0);
                String imageUrl = childBalance.getImageURl().toString();
                String title1 = childBalance.getHeadings().toString();
                String content1 = childBalance.getContent().toString();
                time = childBalance.getSetTime().toString();

//        Glide.with(getApplicationContext())
//                .load(ApplicationConstant.INSTANCE.baseUrl+imageUrl.get(position).getImageUrl().replace("~",""))
//                .into(imageView);

                Picasso.with(getApplicationContext()).load((ApplicationConstant.INSTANCE.baseUrl + imageUrl)).into(dialog_logo);
                title.setText("" + title1);
                content.setText("" + content1);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            } else {
                // Toast.makeText(this, "show popup", Toast.LENGTH_SHORT).show();


            }
        } catch (Exception e) {


        }


//        BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(popupresponse, BalanceCheckResponse.class);
//        String prepaidWallet = balanceCheckResponse.getPrepaidWallet();
//        String utilityWallet = balanceCheckResponse.getUtilityWallet();
//
//        if (prepaidWallet != null && prepaidWallet.length() > 0) {
//            balancePrepaidLayout.setVisibility(View.VISIBLE);
//        } else {
//            balancePrepaidLayout.setVisibility(View.GONE);
//        }
//
//        if (utilityWallet != null && utilityWallet.length() > 0) {
//            balanceUtilityLayout.setVisibility(View.VISIBLE);
//        } else {
//            balanceUtilityLayout.setVisibility(View.GONE);
//        }
//
//        balancePrepaid.setText("" + prepaidWallet);
//        balanceUtility.setText("" + utilityWallet);
//
//        if (balanceCheckResponse.getIsLogin().equalsIgnoreCase("0")) {
//            UtilMethods.INSTANCE.logout(Dashboard3.this);
//        }


    }

    public void ShowPopupNow() {

        Thread timerThread = new Thread() {
            public void run() {
                try {

                    sleep(600000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ShowPopup();
                }
            }
        };
        timerThread.start();
    }

    public void BalanceRefresh() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            /*mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();*/
            //uncomment here
            //   UtilMethods.INSTANCE.VideoGallery(Dashboard3.this, null);
            UtilMethods.INSTANCE.BalanceCheck(Dashboard3.this, null);

            //UtilMethods.INSTANCE.UserSaleReportNew(Dashboard3.this, mProgressDialog);

        } else {
            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!UtilMethods.INSTANCE.getRoleId(this).equalsIgnoreCase("1")) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        } else {
            getMenuInflater().inflate(R.menu.main_menu_retailer, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            ProgressDialog pd = new ProgressDialog(this);
            pd.show();
            UtilMethods.INSTANCE.Logout(getApplicationContext(), pd);
            return true;
        }
        else if (id == R.id.action_refresh) {
            showDialogLoader();
            BalanceRefresh();
            return true;
        }else if (id == R.id.action_update_operator) {
            UpdateOperator();
            return true;
        } else if (id == R.id.action_downline_balance) {
            downlineBalance(Dashboard3.this);
            return true;
        } else if (id == R.id.action_support) {
            fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.fragment_container, new SupportFragment(), "support");
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        } else if (id == R.id.action_showpopup) {

            ShowPopup();
            return true;
        } else if (id == R.id.action_payment_req) {

            Intent createIntent = new Intent(getApplicationContext(), PaymentRequest.class);
            createIntent.putExtra("Type", "abc");
            startActivity(createIntent);
            return true;
        } else if (id == R.id.action_privacy_policy) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationConstant.INSTANCE.baseUrl + "/Policy.html"));
            startActivity(intent);

            return true;
        } else if (id == R.id.action_refund_policy) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationConstant.INSTANCE.baseUrl + "/Refund.html"));
            startActivity(intent);

            return true;
        } else if (id == R.id.action_notice) {
            Intent transactionIntent = new Intent(this, NoticeBoardActivity.class);
            startActivity(transactionIntent);
            //ShowNotice();
            return true;
        }

        return true;


    }


    private void showDialogLoader() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    pd.dismiss();
                }
            }
        };
        timerThread.start();

    }

    public void UpdateOperator() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

            UtilMethods.INSTANCE.startingOperatorService(Dashboard3.this, mProgressDialog);

        } else {
            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }

    public void SetBalance() {
        try {
            SharedPreferences myPreferences = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, null);

            BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(balanceResponse, BalanceCheckResponse.class);


            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String IsExist = myPrefs.getString(ApplicationConstant.INSTANCE.IsExist, null);
            String OTP = myPrefs.getString(ApplicationConstant.INSTANCE.OTP, null);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
            String UMail = myPrefs.getString(ApplicationConstant.INSTANCE.UMail, null);
            String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
            String UName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

            balancePrepaid.setText("Welcome, " + UName + " \n" + UMobile + " \n" + UMail);
            if (balanceCheckResponse != null) {
                String prepaidWallet = balanceCheckResponse.getPrepaidWallet();
                String utilityWallet = balanceCheckResponse.getUtilityWallet();

                if (prepaidWallet != null && prepaidWallet.length() > 0) {
                    balancePrepaidLayout.setVisibility(View.VISIBLE);
                } else {
                    balancePrepaidLayout.setVisibility(View.GONE);
                }

                if (utilityWallet != null && utilityWallet.length() > 0) {
                    balanceUtilityLayout.setVisibility(View.GONE);
                } else {
                    balanceUtilityLayout.setVisibility(View.GONE);
                }

                // balancePrepaid.setText("Your Wallet Amount is : \u20B9 " + prepaidWallet);
                balanceUtility.setText("" + utilityWallet);

                if (balanceCheckResponse.getIsLogin().equalsIgnoreCase("0")) {
                    UtilMethods.INSTANCE.Logout(Dashboard3.this, null);
                }
            }
        } catch (Exception e) {

        }

    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("balanceUpdate")) {
            SetBalance();
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("news")) {
            Log.e("FragmentActivityMessage", activityFragmentMessage.getMessage().trim());
            news.setText("" + activityFragmentMessage.getMessage().trim());
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
////        if (!EventBus.getDefault().isRegistered(this)) {
////            GlobalBus.getBus().register(this);
////        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }

    public void downlineBalance(Context context) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.downline_balance_check, null);

        final EditText downlineMobileNumber = (EditText) view.findViewById(R.id.downlineMobileNumber);
        final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
        final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

        final Dialog dialog = new Dialog(this);

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
                if (downlineMobileNumber.getText() != null && downlineMobileNumber.getText().length() > 0) {

                    if (UtilMethods.INSTANCE.isNetworkAvialable(Dashboard3.this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.CheckBalanceDownline(Dashboard3.this, downlineMobileNumber.getText().toString().trim(), dialog, "", mProgressDialog);

                    } else {
                        UtilMethods.INSTANCE.dialogOk(Dashboard3.this, getResources().getString(R.string.network_error_title),
                                getResources().getString(R.string.network_error_message), 2);
                    }
                } else {
                    downlineMobileNumber.setError("Please enter a valid mobile number !!");
                    downlineMobileNumber.requestFocus();
                }
            }
        });
        dialog.show();
    }

    public void CheckBalanceDownline(String response, Context context) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.downline_balance_check, null);

        final EditText downlineMobileNumber = (EditText) view.findViewById(R.id.downlineMobileNumber);
        final AppCompatTextView message = (AppCompatTextView) view.findViewById(R.id.message);
        final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
        final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

        final Dialog dialog = new Dialog(this);

        dialog.setCancelable(false);
        dialog.setContentView(view);

        BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(response, BalanceCheckResponse.class);
        ChildBalance childBalance = balanceCheckResponse.getChildBalance().get(0);
        String msg = "Name : " + childBalance.getName() + "\n" + "Prepaid Balance : " + childBalance.getPrepaidWallet() + "\n" +
                "Utility Balance : " + childBalance.getUtilityWallet();
        cancelButton.setVisibility(View.GONE);
        downlineMobileNumber.setVisibility(View.GONE);
        message.setText("" + msg);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}