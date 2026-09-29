package com.bababansiwalanew.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
 

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.bababansiwalanew.Login.dto.GetOperatorResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.RechargeReport.dto.RechargeReportResponse;
import com.bababansiwalanew.RechargeReport.dto.RechargeStatus;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.Operator;
import com.bababansiwalanew.Util.dto.OperatorList;
import com.bababansiwalanew.Util.ui.PinPasswordMatcher;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {
    String from,StdCode1;

    TextView tvName;
    TextView tvOperator,recentrecharges,dialog_number,dialog_money;
    TextView tvCircle,circle;
    TextView tv_browse_plan,tv_roffer,heavy_ref ;
    public static String OpName = "";

    ImageView opImage_logo;
    boolean flagElectricity = false;
    LinearLayoutManager mLayoutManager;

    RecyclerView recycler_view;
    RechargeReportAdapterNew mAdapter;
    private ProgressDialog mProgressDialog=null;
    boolean flaglandline = false;
    String paramValue1 = "";
    String paramValue2 = "";
    String paramValue3 = "";
    String paramValue4 = "";
     int resourceId = 0;
    RelativeLayout rl_img;
    private EditText etNumber;
    private EditText etNumber1;
    private EditText stdCode;
    private EditText etAmount;
    private EditText etAmount1;
    private EditText option1;
    private EditText option2;
    private EditText option3;
    private EditText option4;
    private ImageView refresh;
    private TextView more;
  ArrayList<RechargeStatus> transactionsObjects = new ArrayList<>();
    RechargeReportResponse transactions = new RechargeReportResponse();

    boolean flagOperatorFetch = true;
    LinearLayout recyclerlinearlayout,li;
    //Declare String Value...
    String OpCircleName = "";
    String OpRefOp = "";
    String OpRefCircle = "";
    int OpId = 0;
    String Number = "";
    String Name = "";
    String operatorSelected = "";
    String Roffer = "";
    String RofferCode = "";
    String operatorSelectedIcon = "",displayvalue1,displayvalue2,displayvalue3,displayvalue4,Roffercode;

    public String mobileType = "prepaid",hlr;
    public static String OprName = "";
    public String Page = "1",circle_selected;
    GetOperatorResponse circleob;
     static final int PIN_MATCHER = 111;
    public static int operatorSelectedId;
TextView tv_info;
    //Declare Button...
    private TextView btRecharge,dth_warning;

    // Declare CustomLoader....
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        mobileType =   getIntent().getExtras().getString("type");
        hlr =   getIntent().getExtras().getString("hlr");
        from =   getIntent().getExtras().getString("from");
        operatorSelected =   getIntent().getExtras().getString("selected");
        Roffer =   getIntent().getExtras().getString("Roffer");
        operatorSelectedIcon =   getIntent().getExtras().getString("selectedicon");
        operatorSelectedId =  getIntent().getExtras().getInt("selectedId");
        RofferCode =  getIntent().getExtras().getString("selectedOpcode");
        displayvalue1 =  getIntent().getExtras().getString("displayvalue1");
        displayvalue2 =  getIntent().getExtras().getString("displayvalue2");
        displayvalue3 =  getIntent().getExtras().getString("displayvalue3");
        displayvalue4 =  getIntent().getExtras().getString("displayvalue4");
        Roffercode =  getIntent().getExtras().getString("Roffercode");

        toolBar =  findViewById(R.id.toolbar);
        toolBar.setTitle("Recharge ");
        toolBar.setTitleTextColor(Color.WHITE);
//         setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getId();
        Picasso.with(RechargeActivity.this)
                .load(ApplicationConstant.INSTANCE.baseUrl+operatorSelectedIcon)
                .into(opImage_logo);

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (1):
                if (resultCode == 1) {
                    operatorSelected = data.getExtras().getString("selected");
                    operatorSelectedId = data.getExtras().getInt("selectedId");

                    FragmentActivityMessage activityActivityMessage =
                            new FragmentActivityMessage("" + "ok", "setValue");
                    GlobalBus.getBus().post(activityActivityMessage);

                    if (from.equalsIgnoreCase("prepaid")) {
                        if (OpRefOp.isEmpty()) {
                            tvCircle.setVisibility(View.GONE);
                            //     View1.setVisibility(View.GONE);
                        } else {
                            tvCircle.setVisibility(View.GONE);
                            //     View1.setVisibility(View.VISIBLE);
                        }
                    } else {
                        tvCircle.setVisibility(View.GONE);
                        //   View1.setVisibility(View.GONE);
                    }
                    if (from.equalsIgnoreCase("postpaid")) {
                        if (OpRefOp.isEmpty()) {
                            tvCircle.setVisibility(View.GONE);
                            //     View1.setVisibility(View.GONE);
                        } else {
                            tvCircle.setVisibility(View.GONE);
                            //     View1.setVisibility(View.VISIBLE);
                        }
                    }
                    //      tvOperator.setText("" + OpName);
                    Log.e("opIDss", operatorSelected + " , " + operatorSelectedId + " , " + OpName);

                }
                else if(resultCode == 3){
                    String  CircleName = data.getExtras().getString("Circle");
                    String  CircleCode = data.getExtras().getString("IReffCircle");
                    Log.e("val1", CircleName + " , " + CircleCode);
                    tvCircle.setText(CircleName);
                    circle.setText(CircleName);
                    OpRefCircle = CircleCode;}
                else if(resultCode == 2){
                    String  CircleName = data.getExtras().getString("Circle");
                    String  CircleCode = data.getExtras().getString("IReffCircle");
                    Log.e("val1", CircleName + " , " + CircleCode);
                    tvCircle.setText(CircleName);
                    circle.setText(CircleName);
                    OpRefCircle = CircleCode;}
                break;

             
            case (PIN_MATCHER):
                try
                {
                if (data!=null && data.getExtras()!=null)
                if (data.getExtras().getBoolean("flag")){
                if (UtilMethods.INSTANCE.isNetworkAvialable(RechargeActivity.this)) {

                    btRecharge.setEnabled(false);
                    btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_600));
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();
                    mProgressDialog.setCancelable(false);

                    if (from.equalsIgnoreCase("broadband")) {

                        SharedPreferences myPref = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                        StdCode1 = myPref.getString(ApplicationConstant.INSTANCE.reffCode, null);
                        Log.e("reffCode",StdCode1);
                        UtilMethods.INSTANCE.afterLogintoPreviousWindow(RechargeActivity.this, etNumber.getText().toString().trim(),
                                etAmount.getText().toString().trim(), "" + operatorSelectedId,StdCode1+","+stdCode.getText().toString().trim(),
                                flagElectricity, paramValue1, paramValue2, paramValue3, paramValue4, mProgressDialog,btRecharge);
                        Log.e("reffCode ",stdCode.getText().toString().trim()+","+StdCode1);
                    }else {
                        UtilMethods.INSTANCE.afterLogintoPreviousWindow(RechargeActivity.this, etNumber.getText().toString().trim(),
                                etAmount.getText().toString().trim(), "" + operatorSelectedId, stdCode.getText().toString().trim(),
                                flagElectricity, paramValue1, paramValue2, paramValue3, paramValue4, mProgressDialog,btRecharge);

                    }
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            btRecharge.setEnabled(true);
                            btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                    },7000);// set time as per your requirement


                } else {
                    /*UtilMethods.INSTANCE.dialogOk(RechargeActivity.this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);*/
                }
                }
                else if (!data.getExtras().getBoolean("flag") ){
                    new AlertDialog.Builder(RechargeActivity.this)
                            .setTitle("Error!")
                            .setMessage("Pin password not Matched!!\n Please Try with correct pin")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // "OK" button was clicked

                                }
                            })
                            .show();
                }
                else if (data.getExtras().getString("cancelled")!=null &&
                        data.getExtras().getString("cancelled").equalsIgnoreCase("cancelled")){

                }}catch (Exception e){

                }
                break;
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
    public void SetNumber(final String Number) {
        try {
            String Number1 = Number.replace("+91", "");
            String Number2 = Number1.replace(" ", "");
            etNumber.append(Number2);
            String snum = etNumber.getText().toString();
            if (snum.length() > 0) { //just checks that there is something. You may want to check that length is greater than or equal to 3
                String bar = snum.substring(0, 4);
                Log.e("bar", bar);
                //  SelectOperator(bar);
            }
        } catch (Exception e) {
            etNumber.setText("");
            e.printStackTrace();
        }
        /*if (etNumber.getText().toString().length() == 10) {
            tvROffers.setVisibility(View.VISIBLE);

            Log.e("r_offer  ", "" + OpRefOp);


            UtilMethods.INSTANCE.GetROffer(this, etNumber.getText().toString(), OpRefOp, null, tvROffers);
        } else {
            tvROffers.setVisibility(View.GONE);
        }*/
    }
    public void getId(   ) {
        SharedPreferences myPrefs =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref,  MODE_PRIVATE);
        from = myPrefs.getString(ApplicationConstant.INSTANCE.fromPref, null);
        Log.e("From"," from  "+ from);
       try{ if (from.equalsIgnoreCase("landline")){
            flaglandline=true;
        }else if (from.equalsIgnoreCase("electricity")){
            flaglandline=false;

        }}
       catch (Exception e){
          e.printStackTrace();
       }


        tvName = findViewById(R.id.tv_name);
      //  tv_clear= (TextView)  findViewById(R.id.tv_clear);
        circle= findViewById(R.id.circle);
        mProgressDialog=new ProgressDialog(this);
        tvOperator = findViewById(R.id.tv_op);
        tvOperator.setText(operatorSelected);
        tvCircle = findViewById(R.id.tv_circle);
        tv_browse_plan = findViewById(R.id.tv_browse_plan);
        heavy_ref = findViewById(R.id.heavy_ref);
        rl_img = findViewById(R.id.rl_img);
        tv_roffer = findViewById(R.id.tv_roffer);
        tv_info = findViewById(R.id.tv_info);


        opImage_logo = findViewById(R.id.rechar_logo);
        Picasso.with(this)
                .load(ApplicationConstant.INSTANCE.baseUrl+operatorSelectedIcon)
                .into(opImage_logo);
        Log.e("icon",ApplicationConstant.INSTANCE.baseUrl+operatorSelectedIcon);
        btRecharge = findViewById(R.id.bt_recharge);
        dth_warning = findViewById(R.id.dth_warning);
        recentrecharges = findViewById(R.id.recentrecharges);
        recyclerlinearlayout   = findViewById(R.id.recyclerlinearlayout);
        li   = findViewById(R.id.li1);
        recycler_view = findViewById(R.id.recycler_view);

        refresh =    findViewById(R.id.refresh);
        more =    findViewById(R.id.more);
        etNumber = findViewById(R.id.et_number);
        etNumber1 = findViewById(R.id.et_number1);
        stdCode = findViewById(R.id.stdCode);
        etAmount = findViewById(R.id.et_amount);
        etAmount1 = findViewById(R.id.et_amount1);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        if(displayvalue1!=null && displayvalue1.equalsIgnoreCase("AccountNo"))
        {
            etNumber.setVisibility(View.GONE);
            rl_img.setVisibility(View.GONE);
        }
        option1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!option1.getText().toString().isEmpty()){
                    etNumber.setText(option1.getText().toString());
                }

            }
        });


        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (from.equals("prepaid")){

                    if (s.length() == 4) {
                        String snum = etNumber.getText().toString();
                        SelectOperator(snum);

                    } }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobile()) {
                return;
            }if (hlr!=null && hlr.equalsIgnoreCase("yes")){
                if (mobileType.equals("prepaid")){
                     if(etNumber.getText().toString().length() == 10  ) {

                            mProgressDialog.setTitle("Loading..");
                            mProgressDialog.show();
                         UtilMethods.INSTANCE.LookUpApi(RechargeActivity.this, etNumber.getText().toString(), mProgressDialog, new UtilMethods.ApiCallBack() {
                             @Override
                             public void onSucess(Object object) {


                                 GetOperatorResponse getOperatorResponse=(GetOperatorResponse) object;
                                 if(getOperatorResponse!=null){
                                     operatorSelected =   getOperatorResponse.getOperator();
                                     tvOperator.setText(operatorSelected);
                                     operatorSelectedId =   getOperatorResponse.getOPID();
                                       OpCircleName = getOperatorResponse.getCircle();



                                 }
                                 ArrayList<Operator> operatorArray = new ArrayList<>();
                                 OperatorList operatorList = new OperatorList();
                                 SharedPreferences prefs =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref,  MODE_PRIVATE);
                                 String response = prefs.getString(ApplicationConstant.INSTANCE.operatorListPref, null);
                                 Gson gson = new Gson();
                                 operatorList = gson.fromJson(response, OperatorList.class);
                                 operatorArray = operatorList.getPrepaidOperator();

                                 for (Operator op : operatorArray) {
                                     if (operatorSelected.equalsIgnoreCase(op.getOPNAME())) {
                                         operatorSelected = op.getOPNAME();
                                         operatorSelectedId = op.getOPID();
                                         RofferCode= op.getRoffercode();
                                      //   RofferCode= op.get();
                                         operatorSelectedIcon= op.getColumn1();
                                     }
                                 }
                                 ////////////////////////////////////////////////////////
//
//                                   if (operatorSelectedIcon!=null&& operatorSelectedIcon.length() > 0 ){
//                                     String imgName = operatorSelectedIcon;
//                                     String imgTemp = imgName.toLowerCase().replace(" ", "");
//                                     String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//                                     String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//                                     String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//                                     String imgTemp4 = imgTemp3;
//                                     // Log.e("imagepathis",ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1().toString());
//
//
//                                     Picasso.with(RechargeActivity.this).load(ApplicationConstant.INSTANCE.baseUrl+
//                                             operatorSelectedIcon)./*centerCrop().error(R.drawable.ic_operator_default_new).*/into(
//                                                     (opImage_logo));
//                                 }
//                                 else   if (operatorSelected != null && operatorSelected.length() > 0) {
//
//                                     String imgName = operatorSelected;
//                                     String imgTemp = imgName.toLowerCase().replace(" ", "");
//                                     String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//                                     String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//                                     String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//                                     String imgTemp4 = imgTemp3;
//
//                                     //for eg operator name Airtel Digital tv
//                                     //above code convert it to airteldigitaltv
//                                     resourceId = getResources().getIdentifier(
//                                             imgTemp4, "drawable", getPackageName());
//
//                                     // Log.e("here", "name : " + imgTemp4 + " , and : " + resourceId);
//
//                                     Picasso.with(RechargeActivity.this).load(resourceId)./*centerCrop().error(R.drawable.ic_operator_default_new).*/
//                                             into((opImage_logo));
//                                 } else {
//                                     opImage_logo.setImageResource(R.drawable.ic_operator_default_new);
//                                 }

                                /* intent.putExtra("selected", operator.getOPNAME());
                                 intent.putExtra("Roffer", operator.getPLANCODE());
                                 intent.putExtra("selectedicon", operator.getOPIMG());
                                 intent.putExtra("selectedId", operator.getOPID());
                                 intent.putExtra("selectedOpcode", operator.getRoffercode());
                                 intent.putExtra("Roffercode", operator.getRoffercode());*/

                             }
                         });
                        }

                    circle.setVisibility(View.GONE);
                    circle.setHint("Select Circle");

                }}
            }
        });
        etNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateMobile()) {
                    return;
                }
                if (s.length()>0) {
                    String snum = etNumber.getText().toString();
                    String snum1 = etNumber1.getText().toString();
                    if (snum.equalsIgnoreCase(snum1)){

                    }else {
                        etNumber1.setError("Confirm Number not matched..");
                    }

                }
            }
        });
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!validateAmount()) {
                    return;
                }
            }
        });
        etAmount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!validateAmount()) {
                    return;
                }
                if (s.length()>0) {
                    String snum = etAmount.getText().toString();
                    String snum1 = etAmount1.getText().toString();
                    if (snum.equalsIgnoreCase(snum1)){

                    }else {
                        etAmount1.setError("Confirm Amount not matched..");
                    }

                }
            }
        });



        setListners();


        /*if (operatorSelected !=null&& operatorSelected.length() > 0 ){
            String imgName = operatorSelected;
            String imgTemp = imgName.toLowerCase().replace(" ", "");
            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
            // Log.e("imagepathis",ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1().toString());


            Glide.with(RechargeActivity.this).load(ApplicationConstant.INSTANCE.baseUrl+operatorSelected ).asBitmap().centerCrop().error(R.drawable.ic_operator_default_icon).into(new BitmapImageViewTarget(opImage_logo) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(RechargeActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    opImage_logo.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
//        else */ // if (operatorSelected != null && operatorSelected.length() > 0) {
//
//            String imgName = operatorSelected;
//            String imgTemp = imgName.toLowerCase().replace(" ", "");
//            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//            String imgTemp4 = imgTemp3;
//
//            //for eg operator name Airtel Digital tv
//            //above code convert it to airteldigitaltv
//            resourceId = RechargeActivity.this.getResources().getIdentifier(
//                    imgTemp4, "drawable", RechargeActivity.this.getPackageName());
//
//            // Log.e("here", "name : " + imgTemp4 + " , and : " + resourceId);
//
//            Picasso.with(RechargeActivity.this).load(resourceId)./*centerCrop().error(R.drawable.ic_operator_default_new).*/into(
//                    (opImage_logo));
//        } else {
//            opImage_logo.setImageResource(R.drawable.ic_operator_default_new);
//        }
////
    }


  /*  private boolean validateMobile() {
        if (etNumber.getText().toString().trim().isEmpty()) {
            btRecharge.setEnabled(false);
            etNumber.setError("Please Enter valid number");
            btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));
            return false;
        }
        else if (!(etNumber.getText().toString().trim().length()>12)){
            if(!etAmount.getText().toString().isEmpty())
            {
                btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btRecharge.setEnabled(true);
            }
            else
            {
                btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));
                btRecharge.setEnabled(false);
            }

            return false;
        } else {
            etNumber1.requestFocus();
            btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));
            btRecharge.setEnabled(false);
        }

        return true;







    }
*/
  private boolean validateMobile() {
      if (!etNumber.getText().toString().trim().isEmpty()) {
          btRecharge.setEnabled(true);
        //  etNumber.setError("Please Enter valid number");
          btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//          if ((etNumber.getText().toString().trim().length()==12)) {
//            //  etAmount.requestFocus();
////              if (!etAmount.getText().toString().isEmpty()) {
////                  btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
////                  btRecharge.setEnabled(true);
////              }
//              btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//              btRecharge.setEnabled(true);
//          }

          return true;
      }
     /* else if (!(etNumber.getText().toString().trim().length()>12)){
          if(!etAmount.getText().toString().isEmpty())
          {
              btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
              btRecharge.setEnabled(true);
          }*/
          else
          {    etNumber.setError("Please Enter valid number");
              btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_200));
              btRecharge.setEnabled(false);
              return false;
               }
/*
          return false;
      } else {
          etNumber1.requestFocus();
          btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));
          btRecharge.setEnabled(false);
      }*/









  }

    private boolean validateAmount() {
        if (!etAmount.getText().toString().trim().isEmpty()) {
             /*btRecharge.setEnabled(false);
             etAmount.setErroetAmountr("Please Enter valid Amount");
            btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));*/
            btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btRecharge.setEnabled(true);
            if ((etAmount.getText().toString().trim().length()==5)) {
                //etAmount1.requestFocus();
//              if (!.getText().toString().isEmpty()) {
//                  btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                  btRecharge.setEnabled(true);
//              }
              //  etNumber.requestFocus();
                btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btRecharge.setEnabled(true);
            }
            return true;
        }/* else if (!(etAmount.getText().toString().trim().length() > 0)) {
            if (!etNumber.getText().toString().isEmpty()) {
                btRecharge.setEnabled(true);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else {
                    btRecharge.setEnabled(false);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.back_bg));
                etNumber.requestFocus();
            }
            return false;
        }*/ else {
            btRecharge.setEnabled(false);
            etAmount.setError("Please Enter valid Amount");
            btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_200));
            /*btRecharge.setEnabled(true);
            btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));*/

            return false;

        }












    }

    private void setListners() {
        //   tvOperator.setOnClickListener(this);
        tvCircle.setOnClickListener(this);
        tv_browse_plan.setOnClickListener(this);
        heavy_ref.setOnClickListener(this);
        tv_roffer.setOnClickListener(this);
         circle.setOnClickListener(this);
        btRecharge.setOnClickListener(this);
        tv_info.setOnClickListener(this);
        refresh.setOnClickListener(this);
        more.setOnClickListener(this);
       // tv_clear.setOnClickListener(this);

        if (displayvalue1 != null && displayvalue1.length() > 0) {

            option1.setHint(displayvalue1);
            option1.setVisibility(View.VISIBLE);
        } else {
            option1.setVisibility(View.GONE);
        }

        if (displayvalue2 != null && displayvalue2.length() > 0) {
            option2.setHint(displayvalue2);
            option2.setVisibility(View.VISIBLE);
        } else {
            option2.setVisibility(View.GONE);
        }

        if (displayvalue3 != null && displayvalue3.length() > 0) {
            option3.setHint(displayvalue3);
            option3.setVisibility(View.VISIBLE);
        } else {
            option3.setVisibility(View.GONE);
        }

        if (displayvalue4  != null && displayvalue4.length() > 0) {
            option4.setHint(displayvalue4);
            option4.setVisibility(View.VISIBLE);
        } else {
            option4.setVisibility(View.GONE);
        }
        setView();


    }

    private void setView() {
        if (!UtilMethods.INSTANCE.getRoleId(getApplicationContext()).equalsIgnoreCase("3")) {
            recyclerviewgone();
        }



        if (from!=null)
        if (from.equals("prepaid")){
            etAmount.setHint("Amount");

            etNumber.setHint("Prepaid Number");
            etNumber1.setHint("Confirm Prepaid Number");
            tv_roffer.setText("Roffer");
            tv_browse_plan.setText("Browse Plan");
//            etNumber.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
//            etNumber1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );

            tvCircle.setVisibility(View.GONE);
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            tvCircle.setEnabled(true);
            tv_browse_plan.setVisibility(View.VISIBLE);
        }

        if (from.equals("postpaid")){
            etAmount.setHint("Amount");
            etNumber.setHint("Postpaid Number");
            etNumber1.setHint("Confirm Postpaid Number");
//            etNumber.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
//            etNumber1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
            btRecharge.setText("Pay Bill");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            tvCircle.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            tv_roffer.setVisibility(View.GONE);
        }
        if (from.equals("dth")){
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            etAmount.setHint("Amount");
            etNumber.setHint("Customer ID");
            etNumber1.setHint("Confirm Customer ID");
            
            tvCircle.setVisibility(View.GONE);
//            etNumber.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
//            etNumber1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
            circle.setVisibility(View.GONE);
            tv_browse_plan.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            tv_info.setVisibility(View.VISIBLE);
            dth_warning.setVisibility(View.VISIBLE);
            heavy_ref.setVisibility(View.VISIBLE);
            heavy_ref.setText("Simple Plan");
            tv_browse_plan.setText("DTH Customer Info");
            tv_roffer.setText("Heavy Refresh");
            tv_roffer.setVisibility(View.VISIBLE);
        }
        if (from.equals("dthmob")){
            etAmount.setHint("Amount");
            etNumber.setHint("Enter Mobile No");
            etNumber1.setHint("Confirm Mobile No");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            
            tvCircle.setVisibility(View.GONE);
//            etNumber.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
//            etNumber1.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL );
            circle.setVisibility(View.GONE);
            tv_browse_plan.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            tv_info.setVisibility(View.VISIBLE);
            dth_warning.setVisibility(View.VISIBLE);
            heavy_ref.setVisibility(View.VISIBLE);
            tv_browse_plan.setText("DTH Customer Info");
            tv_roffer.setText("Heavy Refresh");
            tv_roffer.setVisibility(View.VISIBLE);
        }
        if (from.equals("broadband")){
            etAmount.setHint("Amount");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            etNumber.setHint("Customer ID");
            etNumber1.setHint("Confirm Customer ID");
            
            tvCircle.setVisibility(View.GONE);
            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );
            circle.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            btRecharge.setText("Pay Bill");
            tv_info.setVisibility(View.VISIBLE);
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            tv_browse_plan.setText("DTH Customer Info");
            tv_roffer.setVisibility(View.GONE);
        }
        if (from.equals("landline")){
            etAmount.setHint("Amount");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            etNumber.setHint("landline Number");
            etNumber1.setHint("Confirm landline Number");
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            
            tvCircle.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            stdCode.setVisibility(View.VISIBLE);
            btRecharge.setText("Pay Bill");
            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );
            tv_roffer.setVisibility(View.INVISIBLE);
        }
        if (from.equals("electricity")) {
            etAmount.setHint("Amount");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            etNumber.setHint("Consumer ID");
            etNumber1.setHint("Confirm Consumer ID");
            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );
            
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            tvCircle.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            btRecharge.setText("Pay Bill");
              flagElectricity = true;
            tv_roffer.setVisibility(View.VISIBLE);
            tv_roffer.setText("View Bill");
            Log.e("from",from );
        }
        if (from.equals("water")) {
            etAmount.setHint("Amount");
            etNumber.setHint("Consumer ID");
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            etNumber1.setHint("Confirm Consumer ID");
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            tvCircle.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            btRecharge.setText("Pay Bill");
            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );

            
            tv_roffer.setVisibility(View.INVISIBLE); Log.e("from",from );
        }
        if ( from.equals("gas")) {
            etAmount.setHint("Amount");
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            tvCircle.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            tv_info.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);

            etNumber.setHint("Consumer ID");
            etNumber1.setHint("Confirm Consumer ID");
            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );

            
            btRecharge.setText("Pay Bill");
            tv_roffer.setVisibility(View.INVISIBLE);
            Log.e("from",from );
        }
        if (from.equals("insurance")) {
            etAmount.setHint("Amount");
            tv_browse_plan.setVisibility(View.GONE);
            heavy_ref.setVisibility(View.GONE);
            tvCircle.setVisibility(View.GONE);
            etAmount1.setVisibility(View.GONE);
            etNumber1.setVisibility(View.GONE);
            stdCode.setVisibility(View.GONE);
            circle.setVisibility(View.GONE);
            dth_warning.setVisibility(View.GONE);

            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            etNumber1.setInputType(InputType.TYPE_CLASS_TEXT );
            btRecharge.setText("Pay Bill");
            etNumber.setHint("Policy Number");
            etNumber1.setHint("Confirm Policy Number");
            
            tv_info.setVisibility(View.GONE);
            tv_roffer.setVisibility(View.INVISIBLE);
        }
    }
    private int ValidCircle(String s) {
        int flag = 0;

        if ( OpRefOp.equalsIgnoreCase("")) {
         //   new CircleFragment().show(getSupportFragmentManager(), operatorSelected);
            flag++;
        }else{

        }
        return flag;
    }
    @Override
    public void onClick(View v) {

         if (v == refresh) {
             if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                 ProgressDialog newP = new ProgressDialog(this);
                 newP.setTitle("Loading");
                 newP.show();
                 UtilMethods.INSTANCE.RecentRecharges(this, "1", "", "", newP, "all");

             } else {
                 UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.network_error_title),
                         this.getResources().getString(R.string.network_error_message), 2);
             }

        }
        if (v == more) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.LastRechargeStatus(  this  , "","", "","", "", mProgressDialog, "Recent");

            } else {
//                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                        getResources().getString(R.string.network_error_message), 2);
            }

        }
        
        if (v == tv_info) {
           /* if (from.equalsIgnoreCase("electricity")){

                if (operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.GetElectricityInfo(this,etNumber.getText().toString().trim(),
                                "" + operatorSelectedId, mProgressDialog );

                    } else {
                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                                getResources().getString(R.string.network_error_message), 2);
                    }
                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

            }
            else */if (from.equalsIgnoreCase("broadband")){

                if (operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.GetViewBill(this,etNumber.getText().toString().trim(),
                                "" + operatorSelectedId, mProgressDialog );

                    } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                    }
                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

            }
            else if (from.equalsIgnoreCase("dth")){

                if (operatorSelectedId!=0){

                        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.show();





                            UtilMethods.INSTANCE.GetDTHInfo(this,etNumber.getText().toString().trim()
                                    , String.valueOf(operatorSelectedId),mProgressDialog );

                        } else {
//                            UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                    getResources().getString(R.string.network_error_message), 2);
                        }

                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }
            }
         }
        if (v == tv_browse_plan) {
            if (from.equalsIgnoreCase("electricity")){

                if (! etNumber.getText().toString().equalsIgnoreCase("")&& operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.GetElectricityInfo(this,etAmount,etNumber.getText().toString().trim(),
                                "" + operatorSelectedId, mProgressDialog );

                    } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                    }
                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

            }
            else if (from.equalsIgnoreCase("broadband")){

                if (operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.GetViewBill(this,etNumber.getText().toString().trim(),
                                "" + operatorSelectedId, mProgressDialog );

                    } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                    }
                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

            }
            else if (from.equalsIgnoreCase("dth")){
                if (operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();
                  UtilMethods.INSTANCE.CheckDTHPlan(this,
                                String.valueOf(operatorSelectedId),"","", mProgressDialog );

                    } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                    }



                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

            }
             else if (from.equalsIgnoreCase("prepaid")) {
                if (operatorSelectedId!=0){
                    if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();
                        UtilMethods.INSTANCE.CheckMobSimplePlan(this,
                                String.valueOf(operatorSelectedId),"","", mProgressDialog );

                    } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                    }



                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }

                /*if ((circle != null && circle.length() > 0)   *//*(part3 != null && part3.length() > 0)*//*) {
                    rechargePlan(RofferCode,  circle.getText().toString(),  operatorSelected);
                }
                else if (operatorSelected != null && operatorSelected.length() > 0) {

                    Intent planOptionIntent = new Intent(this, SelectPlanOption.class);
                    planOptionIntent.putExtra("from", "zoneOp");
                    planOptionIntent.putExtra("opList", 1);
                    planOptionIntent.putExtra("opListName", operatorSelected);
                    planOptionIntent.putExtra("operatorId", RofferCode);
                    startActivityForResult(planOptionIntent, 4);

                } else {
                    Intent planOptionIntent = new Intent(this, SelectPlanOption.class);
                    planOptionIntent.putExtra("from", "operator");
                    planOptionIntent.putExtra("opList", 0);
                    planOptionIntent.putExtra("operatorId", "");
                    startActivityForResult(planOptionIntent, 4);
                }*/
            }


        }
  if (v == heavy_ref) {
             if (from.equalsIgnoreCase("dth")){
                 if (operatorSelectedId!=0){
                     if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                         mProgressDialog.setIndeterminate(true);
                         mProgressDialog.setMessage("Loading...");
                         mProgressDialog.show();
                         UtilMethods.INSTANCE.CheckDTHPlan(this,
                                 String.valueOf(operatorSelectedId),"","", mProgressDialog );

                     } else {
//                         UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                 getResources().getString(R.string.network_error_message), 2);
                     }



                 }
                 else {
                     UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                             "Please Select operator first", 2);
                 }
             /*   if (operatorSelectedId!=0){

                    String  SCode="";

                    if(operatorSelected.trim().equalsIgnoreCase("Dish Tv"))
                    {
                        SCode="DD";
                        Roffer="DD";
                    }
                    else  if(operatorSelected.trim().equalsIgnoreCase("Videocon D2H"))
                    {
                        SCode="DV";
                        Roffer="DV";
                    }
                    else  if(operatorSelected.trim().equalsIgnoreCase("Tata Sky"))
                    {
                        Roffer="DT";
                        SCode="DT";
                    }
                     else if (operatorSelected.equalsIgnoreCase("AIRTEL DIGITAL TV")){
                        Roffer = "AD";
                        SCode = "AD";
                    }
                    else if (operatorSelected.equalsIgnoreCase("Airtel DTH")){
                        Roffer = "AD";
                        SCode = "AD";
                    }else  if (operatorSelected.equalsIgnoreCase("DISH TV")){
                        Roffer = "DT";
                        SCode = "DT";
                    }else  if (operatorSelected.equalsIgnoreCase("Reliance Big Tv")){
                        Roffer = "DB";
                        SCode = "DB";
                    }
                    else  if (operatorSelected.equalsIgnoreCase("BIGTV")){
                        Roffer = "DB";
                        SCode = "DB";
                    }else  if (operatorSelected.replaceAll("\r\n","").equalsIgnoreCase("SUN DIRECT")){
                        Roffer = "SD";
                        SCode = "SD";
                    }else  if (operatorSelected.equalsIgnoreCase("TATA SKY B 2 B")){
                        Roffer = "TS";
                        SCode = "TS";
                    }
                    else  if (operatorSelected.equalsIgnoreCase("TATASKY")){
                        Roffer = "TS";
                        SCode = "TS";
                    }else  if (operatorSelected.equalsIgnoreCase("TATA SKY B 2 C")){
                        Roffer = "TS";
                        SCode = "TS";
                    }else  if (operatorSelected.equalsIgnoreCase("VIDEOCON D2H")){
                        Roffer = "VD";
                        SCode = "VD";
                    }

                    if(SCode.equals(""))
                    {
                         Toast.makeText(getApplicationContext()," Select operator",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (etNumber.getText() != null && etNumber.getText().toString().trim().length() > 0) {

                            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                                mProgressDialog.setIndeterminate(true);
                                mProgressDialog.setMessage("Loading...");
                                mProgressDialog.show();

                                Log.e("SCodee",SCode);

                                UtilMethods.INSTANCE.DTHHeavyRefresh(this,etNumber.getText().toString().trim(),SCode,mProgressDialog);

                            } else {
                                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                                        getResources().getString(R.string.network_error_message), 2);
                            }
                        }

                        else
                        {
                            etNumber.setError(getResources().getString(R.string.number_error));
                            etNumber.requestFocus();
                        }
                    }

                }else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                            "Please Select operator first", 2);
                }*/
            }


        }

        if (v == tv_roffer) {


//            if(etNumber.getText().toString().length() < 10)
//            {
//
//            }
            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
                  final ProgressDialog loader = new ProgressDialog(this );
                loader.setMessage("Loading...");
                loader.show();
                if (from.equals("prepaid")) {
                    if(etNumber.getText().toString().length() == 10 )
                    {

                        UtilMethods.INSTANCE.GetROffer(this,etNumber.getText().toString(),"" + operatorSelectedId,loader,tv_roffer);
                     }
                    else if(etNumber.getText().toString().length() < 10)
                    {
                        Toast.makeText(this, "Please Enter the Valid Number", Toast.LENGTH_SHORT).show();
                     }

                }
                else if (from.equals("dth")) {
                    if (operatorSelectedId!=0){


                            if (etNumber.getText() != null && etNumber.getText().toString().trim().length() > 0) {

                                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                                    mProgressDialog.setIndeterminate(true);
                                    mProgressDialog.setMessage("Loading...");
                                    mProgressDialog.show();

                                  //  Log.e("SCodee",SCode);

                                    UtilMethods.INSTANCE.DTHHeavyRefresh(this,etNumber.getText().toString().trim(),"" + operatorSelectedId,mProgressDialog);

                                } else {
                                  /*  UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                                            getResources().getString(R.string.network_error_message), 2);*/
                                }
                            }

                            else
                            {
                                etNumber.setError(getResources().getString(R.string.number_error));
                                etNumber.requestFocus();
                            }


                    }else {
                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                                "Please Select operator first", 2);
                    }
                }
                else  if (from.equalsIgnoreCase("electricity")){

                    if (! etNumber.getText().toString().equalsIgnoreCase("")&& operatorSelectedId!=0){
                        if (UtilMethods.INSTANCE.isNetworkAvialable(RechargeActivity.this)) {

//                            mProgressDialog.setIndeterminate(true);
//                            mProgressDialog.setMessage("Loading...");
//                            mProgressDialog.show();

                            UtilMethods.INSTANCE.GetElectricityInfo(RechargeActivity.this,etAmount,etNumber.getText().toString().trim(),
                                    "" + operatorSelectedId, loader );

                        } else {
//                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
//                                getResources().getString(R.string.network_error_message), 2);
                        }
                    }else {
                        UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                                "Please Select operator first", 2);
                    }

                }
            } else {
               /* UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message),2);*/
            }
            }

//            // rechargePlan(OpRefOp, OpRefCircle, "");
//            Log.e("BrowsePlan", OpRefOp + ",+" + OpRefCircle);
//
//
//            if (etNumber.getText().toString().length() == 10) {
//            //    Roffer
//
//                if (OpRefOp.equals("")) {
//                    UtilMethods.INSTANCE.Error(getApplicationContext(), "Please Select the Operator" );
//                } else if (OpRefCircle.equals("")) {
//                    UtilMethods.INSTANCE.Error(getApplicationContext(), "Please Select the Circle" );
//                } else {
//                    Intent browseIntent = new Intent(getApplicationContext(), BrowsePlanScreen.class);
//                    browseIntent.putExtra("OpRefOp", "" + OpRefOp.trim());
//                    browseIntent.putExtra("OpRefCircle", "" + OpRefCircle.trim());
//                    browseIntent.putExtra("Type", "MobPlan");
//                    startActivity(browseIntent);
//                }
//
//
//
//            //    loader.show();
//                // tvROffers.setVisibility(View.VISIBLE);
//                Log.e("tv_roffer_plan  ", "" + OpRefOp);
//              // UtilMethods.INSTANCE.GetROffer(getApplicationContext(), etNumber.getText().toString(), OpRefOp, mProgressDialog);
//            } else {
//              UtilMethods.INSTANCE.Failed(getApplicationContext(), "Not a valid mobile number");
//            }

        if (v == tvCircle) {

            if (!operatorSelected.equalsIgnoreCase("")) {

                if (ValidCircle("") == 0) {
                    // ViewPlane();
                }


            } else {
                Toast.makeText(this, "Please Select Operator", Toast.LENGTH_LONG).show();
            }
        }
        if (v == btRecharge) {
            if (!validateMobile()) {
                return;
            }
          /*  if (!validateMobile1()) {
                return;
            }/*/
            if (!validateAmount()) {
                return;
            }
            /*if (!validateAmount1()) {
                return;
            }*/
           RechargeDialog();



            }





        }

    private boolean validateAmount1() {
        if ( !etAmount1.getText().toString().trim().isEmpty()) {
            if (etAmount.getText().toString().trim().equalsIgnoreCase(etAmount1.getText().toString().trim()) ||
                    etAmount1.getText().toString().trim().isEmpty()){
                btRecharge.setEnabled(true);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if ((etAmount1.getText().toString().trim().length()==5)) {
                    btRecharge.requestFocus();
//              if (!etAmount.getText().toString().isEmpty()) {
//                  btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                  btRecharge.setEnabled(true);
//              }
                    //  etNumber.requestFocus();
                    btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btRecharge.setEnabled(true);
                }
                return true;
            }
            else   {
                etAmount1.setError(" Confirm Amount Not Matched..");
                btRecharge.setEnabled(false);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_200));

                return false;
            }
        }


return true;

    }

    private boolean validateMobile1() {
        if ( !etNumber1.getText().toString().trim().isEmpty()) {
            if (etNumber.getText().toString().trim().equalsIgnoreCase(etNumber1.getText().toString().trim()) ||  etNumber1.getText().toString().trim().isEmpty()){

                btRecharge.setEnabled(true);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                if ((etNumber1.getText().toString().trim().length()==12)) {
                    etAmount.requestFocus();

//              if (!etAmount.getText().toString().isEmpty()) {
//                  btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                  btRecharge.setEnabled(true);
//              }
                    btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btRecharge.setEnabled(true);
                }

            return true;
            }
            else   {
                etNumber1.setError(" Confirm Number Not Matched..");
                btRecharge.setEnabled(false);
                btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_200));

                return false;
            }
        }
        return true;
    }

    private void RechargeDialog() {
        if (UtilMethods.INSTANCE.getRegKey(RechargeActivity.this) != null &&
                UtilMethods.INSTANCE.getRegKey(RechargeActivity.this).length() > 0) {

                 LayoutInflater inflater = (LayoutInflater) RechargeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.layout_confirmation, null);

                TextView number1 = view.findViewById(R.id.number);
                TextView operator1 = view.findViewById(R.id.operator);
                TextView amount1 = view.findViewById(R.id.amount);
                TextView okButton =  view.findViewById(R.id.ok);
                TextView cancelButton =   view.findViewById(R.id.cancel);
                number1.setText("Recharge No : " + etNumber.getText().toString());
                operator1.setText("Operator : "+operatorSelected);
                amount1.setText(" Amount : "+etAmount.getText().toString());
                final Dialog dialog = new Dialog(RechargeActivity.this);
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


                        SharedPreferences myPreferences =getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
                        boolean pinPass = myPreferences.getBoolean(ApplicationConstant.INSTANCE.PinPasscodeFlag, false);
                        if (pinPass) {

                            Intent passIntent = new Intent(RechargeActivity.this, PinPasswordMatcher.class);
                            startActivityForResult(passIntent, PIN_MATCHER);
                        }
                        else
                            {
                            if (UtilMethods.INSTANCE.isNetworkAvialable(RechargeActivity.this)) {

                                btRecharge.setEnabled(false);
                                btRecharge.setBackgroundColor(getResources().getColor(R.color.grey_600));
                                mProgressDialog.setIndeterminate(true);
                                mProgressDialog.setMessage("Loading...");
                                mProgressDialog.show();
                                mProgressDialog.setCancelable(false);

                                if (from.equalsIgnoreCase("broadband")) {

                                    SharedPreferences myPref = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
                                    StdCode1 = myPref.getString(ApplicationConstant.INSTANCE.reffCode, null);
                                    Log.e("reffCode",StdCode1);
                                    UtilMethods.INSTANCE.afterLogintoPreviousWindow(RechargeActivity.this, etNumber.getText().toString().trim(),
                                            etAmount.getText().toString().trim(), "" + operatorSelectedId,StdCode1+","+stdCode.getText().toString().trim(),
                                            flagElectricity, paramValue1, paramValue2, paramValue3, paramValue4, mProgressDialog,btRecharge);
                                    Log.e("reffCode ",stdCode.getText().toString().trim()+","+StdCode1);
                                }else {
                                    UtilMethods.INSTANCE.afterLogintoPreviousWindow(RechargeActivity.this, etNumber.getText().toString().trim(),
                                            etAmount.getText().toString().trim(), "" + operatorSelectedId, stdCode.getText().toString().trim(),
                                            flagElectricity, paramValue1, paramValue2, paramValue3, paramValue4, mProgressDialog,btRecharge);

                                }
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        // This method will be executed once the timer is over
                                        btRecharge.setEnabled(true);
                                        btRecharge.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                    }
                                },7000);// set time as per your requirement


                            } else {
                               /* UtilMethods.INSTANCE.dialogOk(RechargeActivity.this, getResources().getString(R.string.network_error_title),
                                        getResources().getString(R.string.network_error_message), 2);*/
                            }
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();





                //}
          //  }
         } else {
            Toast.makeText(RechargeActivity.this, "Please try after some time", Toast.LENGTH_SHORT).show();
            UtilMethods.INSTANCE.KeyUpdate(RechargeActivity.this, UtilMethods.INSTANCE.getKeyId(RechargeActivity.this));
        }
    }





    public void ItemClick(String name, int id, String param1, String param2, String param3, String param4, String Shortcode, String roffer) {
        //    tvOperator.setText(name);
        OpName = name;
        operatorSelectedId = id;

        Log.e("val", name + " , " + id);

        //   OpRefOp = UtilMethods.INSTANCE.fetchShortCode(this , name);

        FragmentActivityMessage activityActivityMessage =
                new FragmentActivityMessage("" + "ok", "setValue");
        GlobalBus.getBus().post(activityActivityMessage);

        if (mobileType.equalsIgnoreCase("prepaid")) {
            if (OpRefOp.isEmpty()) {
                tvCircle.setVisibility(View.GONE);
                //   View1.setVisibility(View.GONE);
            } else {
                tvCircle.setVisibility(View.GONE);
                //   View1.setVisibility(View.VISIBLE);
            }
        } else {
            tvCircle.setVisibility(View.GONE);
            //  View1.setVisibility(View.GONE);
        }

        //   tvOperator.setText("" + OpName);


        Log.e("opIDss", operatorSelected + " , " + operatorSelectedId + " , " + OpName);
    }


    public void CircleClick(String CircleName, String CircleCode,String circleReff) {
        Log.e("val1", CircleName + " , " + CircleCode);
        tvCircle.setText(CircleName);
        circle.setText(CircleName);
        OpRefCircle = CircleCode;
        OpRefOp = CircleCode;

        FragmentActivityMessage activityActivityMessage =
                new FragmentActivityMessage("" + "ok", "setCircle");
        GlobalBus.getBus().post(activityActivityMessage);
    }
    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("planSelected")) {

            etAmount.setText(""+activityFragmentMessage.getMessage() );
        }
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("recentReport")) {
            dataParse(activityFragmentMessage.getMessage());
            Log.e("logRecent", activityFragmentMessage.getMessage());
        }
        else  if (activityFragmentMessage.getFrom().equalsIgnoreCase("recentReportnull")) {
            recyclerviewgone();
        }

        else if (activityFragmentMessage.getFrom().equalsIgnoreCase("dth_info")) {


            etAmount.setText(""+activityFragmentMessage.getMessage());

        }
        else if (activityFragmentMessage.getFrom().equalsIgnoreCase("refreshvalue")) {
            // SetBalance();
            etNumber.setText("");
            etNumber1.setText("");
            etAmount.setText("");
            etAmount1.setText("");
            // tvCircle.setText("");
            circle.setText("");
            // tvName.setVisibility(View.GONE);
        }
        else if (activityFragmentMessage.getFrom().equalsIgnoreCase("rofferAmount")) {
            // SetBalance();
            etAmount.setText(""+activityFragmentMessage.getMessage());

        }
    }
    private void recyclerviewgone() {

        recentrecharges.setVisibility(View.GONE);
        recyclerlinearlayout.setVisibility(View.GONE);
        li.setVisibility(View.GONE);
        // noData.setVisibility(View.VISIBLE);
        recycler_view.setVisibility(View.GONE);
    }

    @Subscribe
    public void FragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
          if (activityFragmentMessage.getFrom().equalsIgnoreCase("rOffer_Amount")) {
            etAmount.setText(""+activityFragmentMessage.getMessage());

        }else if (activityFragmentMessage.getFrom().equalsIgnoreCase("LookUpApi")) {
              setoperator(activityFragmentMessage.getMessage());
              //  lookUpApi1=activityFragmentMessage.getMessage();
              Log.e("lookupAPI", activityFragmentMessage.getMessage());
          }
    }
            @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("refreshvalue")) {
            // SetBalance();
            etNumber.setText("");
            etNumber1.setText("");
            etAmount.setText("");
            etAmount1.setText("");
            tvCircle.setText("");
            circle.setText("");
            tvName.setVisibility(View.GONE);
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("prepaid")) {


            etAmount.setText("");
            etAmount1.setText("");
            //  etAmount.append(activityFragmentMessage.getFrom());
            Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("postpaid")) {
            etAmount.setHint("Amount");
            etAmount1.setHint("Amount");
            etNumber.setHint("Postpaid Number");
            etNumber1.setHint("Postpaid Number");
            
            tv_browse_plan.setVisibility(View.GONE);
            //  etAmount.append(activityFragmentMessage.getFrom());
            Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("dth")) {
            etAmount.setHint("Amount");
            etAmount1.setHint("Amount");
            etNumber.setHint("Customer ID");
            etNumber1.setHint("Customer ID");
            
            tv_browse_plan.setVisibility(View.GONE);
            Log.e("from",activityFragmentMessage.getMessage() );
        }  else if (activityFragmentMessage.getMessage().equalsIgnoreCase("dthmob")) {
            etAmount.setHint("Amount");
            etAmount1.setHint("Amount");
            etNumber.setHint("Enter Mobile Number");
            etNumber1.setHint("Confirm Mobile Number");
            
            tv_browse_plan.setVisibility(View.GONE);
            Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("landline")) {
            etAmount.setHint("Amount");
            etAmount1.setHint("Amount");
            etNumber.setHint("landline Number");
            etNumber1.setHint("landline Number");
            
            tv_browse_plan.setVisibility(View.GONE);
            Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("electricity")) {
            etAmount.setHint("Amount");
            etAmount1.setHint("Amount");
            etNumber.setHint("Consumer ID");
            etNumber1.setHint("Consumer ID");

            etNumber.setInputType(InputType.TYPE_CLASS_TEXT );
            
            tv_browse_plan.setVisibility(View.GONE); Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("water")) {
            etAmount.setHint("Amount");
            etNumber.setHint("Consumer ID");
            
            tv_browse_plan.setVisibility(View.GONE); Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("gas")) {
            etAmount.setHint("Amount");
            etNumber.setHint("Consumer ID");
            
            tv_browse_plan.setVisibility(View.GONE);Log.e("from",activityFragmentMessage.getMessage() );
        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("insurance")) {
            etAmount.setHint("Amount");
            etNumber.setHint("Policy Number");
            
            tv_browse_plan.setVisibility(View.GONE); Log.e("from",activityFragmentMessage.getMessage() );
        }
//        else if (activityFragmentMessage.getFrom().equalsIgnoreCase("planSelected"))
//        {
//             etAmount.setText(""+activityFragmentMessage.getMessage() );
//
//              etAmount.append(activityFragmentMessage.getFrom());
//        }

        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("rOffer_Amount")) {
            etAmount.setText(""+activityFragmentMessage.getMessage());

        }
        else if (activityFragmentMessage.getMessage().equalsIgnoreCase("rofferAmount")) {
         //   etAmount.setText(""+activityFragmentMessage.getFrom());

        }

        if (activityFragmentMessage.getMessage().equalsIgnoreCase("DTHCustomerinfo")) {
           // String response = activityFragmentMessage.getFrom();
           // if (response!=null){
                //   Intent browseIntent = new Intent( this, DthConsumerInfoScreen.class);
                // browseIntent.putExtra("response", response);
                // startActivity(browseIntent);
          //  }

        }
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("dthCustomerPlan")) {
         //   String response = activityFragmentMessage.getFrom();
          //  etAmount.setText(""+response);

        }

    }

    public void rechargePlan(String opId, String zoneId, String operator) {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();
            UtilMethods.INSTANCE.CheckMobSimplePlan(this, String.valueOf(opId), zoneId, operator, mProgressDialog);

            //UtilMethods.INSTANCE.RechargePlans(this, opId, zoneId, operator, mProgressDialog);
        } else {
            /*UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);*/
        }
    }
    private void SelectOperator(String s) {
        if (flagOperatorFetch) {
            try {
                String param = UtilMethods.INSTANCE.fetchOperator(this, s);

                String[] parts = param.split(",");
                if (parts.length == 3) {
                    OpName = parts[0];
                    OpRefCircle = parts[1];
                    OpRefOp = parts[2];
                    ArrayList<Operator> operatorArray = new ArrayList<>();
                    OperatorList operatorList = new OperatorList();
                    SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, Context.MODE_PRIVATE);
                    String response = prefs.getString(ApplicationConstant.INSTANCE.operatorListPref, null);
                    Gson gson = new Gson();
                    operatorList = gson.fromJson(response, OperatorList.class);
                    operatorArray = operatorList.getPrepaidOperator();

                    for (Operator op : operatorArray) {
                        if (op.getOPNAME().equalsIgnoreCase(OpName)) {

                        }
                    }
                    circle.setText(OpRefOp);


                } else if (s.length() < 4) {
//                tvOperator.setHint("Select Operator");
//                tvCircle.setHint("Select Circle");
                    // opeImgMethod("");
                    OpName = "";
                    OpCircleName = "";
                    OpRefOp = "";
                    OpRefCircle = "";
                    // OpId = 0;

                    operatorSelected = "";
                    operatorSelectedId = 0;
                }
            }catch (Exception e){}}

    }
    public void dataParse(String response) {

        Log.e("enteredindataparse", response);

        if (!response.equals("")){
            Gson gson = new Gson();
            transactions = gson.fromJson(response, RechargeReportResponse.class);
            transactionsObjects = transactions.getRechargeStatus();

            if (transactionsObjects!=null && transactionsObjects.size() > 0) {
                mAdapter = new RechargeReportAdapterNew(transactionsObjects, this);
                mLayoutManager = new LinearLayoutManager(this);
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setLayoutManager(new LinearLayoutManager(this
                        , LinearLayoutManager.HORIZONTAL, false));
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);
                // noData.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);}
        } else {
            recentrecharges.setVisibility(View.GONE);
            recyclerlinearlayout.setVisibility(View.GONE);
            li.setVisibility(View.GONE);
            // noData.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        }
    }



    private void setoperator(String message) {
        Log.e("lookupAPI", message);

        Gson gson = new Gson();
        circleob = gson.fromJson(message, GetOperatorResponse.class);
        circle_selected = circleob.getCircle();
        operatorSelected = circleob.getOperator();

        tvOperator.setText(operatorSelected);
        opeImgMethod(circleob.getOperator());
        // opeIdMethod(circleob.getOperator());
        operatorSelectedId=circleob.getOPID();
    }
    public void opeImgMethod(final String imgName) {
//
//        String imgTemp = imgName.toLowerCase().replace(" ", "");
//        String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//        String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//        String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//        String imgTemp4 = imgTemp3;
//        Log.e("img is ","imgeselected "+imgTemp4);
//        if (imgTemp4!="jiospecial"){
//            int resourceId = getResources().getIdentifier(
//                    imgTemp4 + "rec", "drawable",  getPackageName());
//            if (resourceId != 0) {
//                opImage_logo.setVisibility(View.VISIBLE);
//               // opImgLeft.setVisibility(View.GONE);
//
//                //un comment
//         /*   Glide.with(this).load(resourceId)
//                    .bitmapTransform(new RoundedCornersTransformation(getActivity(), 0, 0))
//                    .into(opImg);*/
//
//            } else {
//                opImage_logo.setVisibility(View.GONE);
//              //  opImgLeft.setVisibility(View.VISIBLE);
//            }
//        }else {
//            int resourceId = getResources().getIdentifier(
//                    "reliancejio" + "rec", "drawable",  getPackageName());
//            Log.e("resourceid  is ", String.valueOf(resourceId));
//            if (resourceId != 0) {
//                opImage_logo.setVisibility(View.VISIBLE);
//               // opImgLeft.setVisibility(View.GONE);
//
//                // Glide.with(this).load(R.drawable.reliancejio).into(opImg);
//              //  opImage_logo.setImageAlpha(R.drawable.reliancejio);
//
//            } else {
//                opImage_logo.setVisibility(View.GONE);
//               // opImgLeft.setVisibility(View.VISIBLE);
//            }
//        }

    }

}
