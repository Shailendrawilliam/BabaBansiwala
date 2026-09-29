package com.bababansiwalanew.DthBook.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.DthBook.dto.GetDthBookingResponse;
import com.bababansiwalanew.DthBook.dto.ObjectLanguage;
import com.bababansiwalanew.DthBook.dto.ObjectPackage;
import com.bababansiwalanew.DthBook.dto.Objectpack;
import com.bababansiwalanew.DthBook.dto.OperatorObjectdth;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class DthBookingActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner DTHConnectionName,Pack,Language,Package;
    Button submit;
    TextView getamount,amount;

    String name_id = "";
    String pack_id = "";
    String language_id = "";
    String package_id = "";

    String DTHnameSelect = "";
    String PackNameSelect = "";
    String LanguagenameSelect = "";
    String packagenameSelect = "";

    String plan_amount = "";
    private ProgressDialog mProgressDialog = null;
    EditText Name,holdernumber,LandMark,Address,PinCode,Mobile1,Mobile2;


    GetDthBookingResponse GetDthBookingResponse;
    ArrayList<OperatorObjectdth> OperatorObjectdth = new ArrayList<>();
    ArrayList<Objectpack> OperatorPack = new ArrayList<>();
    ArrayList<ObjectLanguage> OperatorLanguage = new ArrayList<>();
    ArrayList<ObjectPackage> OperatorPackage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dth_booking_layout);
        GetId();
    }

    private void GetId() {
        Name =(EditText) findViewById(R.id.Name);
        holdernumber =(EditText) findViewById(R.id.holdernumber);
        LandMark =(EditText) findViewById(R.id.LandMark);
        Address =(EditText) findViewById(R.id.Address);
        PinCode =(EditText) findViewById(R.id.pincode);
        Mobile1 =(EditText) findViewById(R.id.Mobile1);
        Mobile2 =(EditText) findViewById(R.id.Mobile2);
        getamount =(TextView) findViewById(R.id.getamount);
         amount =(TextView) findViewById(R.id.amount);
        getamount.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dth Booking");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mProgressDialog = new ProgressDialog(this);
        DTHConnectionName = (Spinner) findViewById(R.id.DTHConnectionName);
        Pack = (Spinner) findViewById(R.id.Pack);
        Language = (Spinner) findViewById(R.id.Language);
        Package = (Spinner) findViewById(R.id.Package);


        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        Log.e("plan_amount ","OperatorLanguage "+ plan_amount);

      //  amount.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(this, R.drawable.ic_rupee_indian), null, null, null);


        PlaneList();
    }

    private void DthBookingNmae(String from) {

        ArrayList<String> dthname = new ArrayList<String>();
        ArrayList<String> Packlist = new ArrayList<String>();
        ArrayList<String> Languagelist = new ArrayList<String>();
        ArrayList<String> Packagelist = new ArrayList<String>();
     //   Log.e("plan_amount ","OperatorObjectdth "+ from);
        dthname.add("SELECT COMPANY");
        Packlist.add("Select pack");
        Languagelist.add("Box Type");
        Packagelist.add("Select Package list");
        Gson gson = new Gson();
        GetDthBookingResponse = gson.fromJson(from, GetDthBookingResponse.class);
        OperatorObjectdth = GetDthBookingResponse.getDTHConnectionName();
        OperatorLanguage = GetDthBookingResponse.getLanguage();
        OperatorPackage = GetDthBookingResponse.getPackage();
        OperatorPack = GetDthBookingResponse.getPack();

        Log.e("plan_amount ","OperatorObjectdth "+ from);

        if (OperatorObjectdth != null && OperatorObjectdth.size() > 0) {
            for (int i = 0; i < OperatorObjectdth.size(); i++) {
                dthname.add(OperatorObjectdth.get(i).getOpratedName());
            }
        }
        if (OperatorPack != null && OperatorPack.size() > 0) {
            for (int i = 0; i < OperatorPack.size(); i++) {
                Packlist.add(OperatorPack.get(i).getPackageName());
            }
        }

        if (OperatorLanguage != null && OperatorLanguage.size() > 0) {
            for (int i = 0; i < OperatorLanguage.size(); i++) {
                Languagelist.add(OperatorLanguage.get(i).getName());
            }
        }

        if (OperatorPackage != null && OperatorPackage.size() > 0) {
            for (int i = 0; i < OperatorPackage.size(); i++) {
                Packagelist.add(OperatorPackage.get(i).getLanguagePackage());
            }
        }

        DTHConnectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                Log.e("plan_amount ","plan_amount "+ plan_amount);

                amount.setText("");
                if (parent.getItemAtPosition(position).toString().equals("SELECT COMPANY")) {
                    plan_amount="";
                    amount.setText("");
                } else {
                    DTHnameSelect = OperatorObjectdth.get(position-1).getOpratedName();
                    name_id = OperatorObjectdth.get(position-1).getOPId();
                    amount.setText("");
                    Log.e("plan_amount ","plan_amount "+ plan_amount);

                    //amount.setText(plan_amount);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                amount.setText("");
                // TODO Auto-generated method stub
            }
        });

        Pack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                Log.e("plan_amount ","plan_amount "+ plan_amount);
                amount.setText("");

                if (parent.getItemAtPosition(position).toString().equals("Select pack")) {
                    plan_amount="";
                    amount.setText("");
                } else {
                    PackNameSelect = OperatorPack.get(position-1).getPackageName();
                    pack_id = OperatorPack.get(position-1).getId();

                    Log.e("plan_amount ","plan_amount "+ plan_amount);
                    amount.setText("");
                    //amount.setText(plan_amount);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                amount.setText("");
            }
        });

        Language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                Log.e("plan_amount ","plan_amount "+ plan_amount);
                amount.setText("");

                if (parent.getItemAtPosition(position).toString().equals("Box Type")) {
                    plan_amount="";
                    amount.setText("");
                } else {
                    LanguagenameSelect = OperatorLanguage.get(position-1).getName();
                    language_id = OperatorLanguage.get(position-1).getId();
                    amount.setText("");
                    Log.e("plan_amount ","OperatorLanguage "+ plan_amount);

                    //amount.setText(plan_amount);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

                amount.setText("");}
        });

        Package.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                Log.e("plan_amount ","plan_amount "+ plan_amount);

                amount.setText("");
                if (parent.getItemAtPosition(position).toString().equals("Select Package list")) {
                    plan_amount="";
                    amount.setText("");
                } else {
                    packagenameSelect = OperatorPackage.get(position-1).getLanguagePackage();
                    package_id = OperatorPackage.get(position-1).getId();
                    amount.setText("");
                    Log.e("plan_amount ","plan_amount "+ plan_amount);

                    //amount.setText(plan_amount);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                amount.setText("");
            }
        });


        ArrayAdapter<String> dthnameAdapter;
        ArrayAdapter<String> PackAdapter;
        ArrayAdapter<String> LanguagelistAdapter;
        ArrayAdapter<String> PackagelistAdapter;

        dthnameAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, dthname);
        PackAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Packlist);
        LanguagelistAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Languagelist);
        PackagelistAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, Packagelist);

        DTHConnectionName.setAdapter(dthnameAdapter);
        Pack.setAdapter(PackAdapter);
        Language.setAdapter(LanguagelistAdapter);
        Package.setAdapter(PackagelistAdapter);

    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("dthconnectionname")) {
            Log.e("ConnectionActiv",activityFragmentMessage.getMessage().trim());

            DthBookingNmae("" + activityFragmentMessage.getMessage().trim());



        }else if (activityFragmentMessage.getFrom().equalsIgnoreCase("dthconnectionamount")) {
            String amountval = activityFragmentMessage.getMessage().trim().replace("U+0022","");

            amount.setText(" " + amountval.replace(".00",""));


           // amount.setText("" + activityFragmentMessage.getMessage().trim().replace(".00",""));



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

    private void PlaneList() {

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {


            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

            UtilMethods.INSTANCE.GetConnectionOperators(this,mProgressDialog);



        } else {
            UtilMethods.INSTANCE.dialogOk(this , getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == submit) {


            SharedPreferences myPrefs =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,  MODE_PRIVATE);

            String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);




             if (!UMobile.equalsIgnoreCase("")&& !name_id.equalsIgnoreCase("")&& !LanguagenameSelect.equalsIgnoreCase("")&&
                     !PackNameSelect.equalsIgnoreCase("") &&LandMark.getText()!=null&&amount.getText()!=null ) {

                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {


                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();


                    UtilMethods.INSTANCE.BookDTHConnection(this, UMobile, name_id, LanguagenameSelect, PackNameSelect, packagenameSelect, amount.getText().toString().trim().toString(), Name.getText().toString().trim().toString(), holdernumber.getText().toString().trim().toString(),
                            LandMark.getText().toString().trim().toString(), Address.getText().toString().trim().toString(), Mobile1.getText().toString().trim().toString(), Mobile2.getText().toString().trim().toString(), PinCode.getText().toString().trim().toString(), "", mProgressDialog);


                } else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                            getResources().getString(R.string.network_error_message), 2);
                }
            }  else {
                    UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.attention_error_title),
                           "Plz Enter The Value......", 2);
                }




        }if (v == getamount) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {


                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.GetDTHConnectionAmount(this,name_id,language_id,pack_id,package_id,mProgressDialog);



            } else {
                UtilMethods.INSTANCE.dialogOk(this , getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }


           /* if (!plan_amount.equalsIgnoreCase("")) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {



                } else {

                }
            }*/

        }
    }
}
