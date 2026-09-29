package com.bababansiwalanew.FundRecReport.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.FundRecReport.dto.FundRecObject;
import com.bababansiwalanew.FundRecReport.dto.FundRecResponse;
import com.bababansiwalanew.R;

import java.util.ArrayList;
import java.util.Locale;

public class FundRecReport extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    FundRecAdapter mAdapter;
    LinearLayout ll_d,llSearchContainer; TextView fromdate,toDate;
    RelativeLayout filter,rll;
    ImageView filterimg;
  //  String response = "",from;
    LinearLayoutManager mLayoutManager;
    ArrayList<FundRecObject> transactionsObjects = new ArrayList<>();

EditText fromSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledger_report);
       // response = getIntent().getExtras().getString("response");
       // from = getIntent().getExtras().getString("from");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(FundRecReport.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_d =   findViewById(R.id.ll_d);
        fromSearch =   findViewById(R.id.fromSearch);
        ll_d.setVisibility(View.VISIBLE);
        llSearchContainer=(LinearLayout)findViewById(R.id.searchContainer);
        llSearchContainer .setVisibility(View.VISIBLE);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Fund Receive Report");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fromdate= findViewById(R.id.fromDate);
        rll= findViewById(R.id.rll);
        ll_d= findViewById(R.id.ll_d);
        ll_d.setVisibility(View.VISIBLE);
        filter= findViewById(R.id.searchLayout11);
        filter.setVisibility(View.VISIBLE);
        filterimg= findViewById(R.id.search1);
        filterimg.setVisibility(View.VISIBLE);
        fromSearch.setVisibility(View.VISIBLE);
        rll.setVisibility(View.GONE);
        toDate= findViewById(R.id.toDate);
        filterimg.setOnClickListener(this); filter.setOnClickListener(this);
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //  myCalendar.setTimeZone(UTF:+5.3);
                Locale locale = getResources().getConfiguration().locale;
                Locale.setDefault(Locale.ENGLISH);


                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                fromdate.setText(sdf.format(myCalendar.getTime()));
            }

        };
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pd =    new DatePickerDialog(FundRecReport.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_POSITIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEUTRAL)
//                        .setTextColor(Color.GRAY) ;
                pd.show();
                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });
        // final Calendar myCalendar = Calendar.getInstance();
        fromSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = fromSearch.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = fromSearch.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }
        });
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //  myCalendar.setTimeZone(UTF:+5.3);
                Locale locale = getResources().getConfiguration().locale;
                Locale.setDefault(Locale.ENGLISH);


                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                toDate.setText(sdf.format(myCalendar.getTime()));
            }

        };
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pd =  new DatePickerDialog(FundRecReport.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_POSITIVE)
//                        .setTextColor(Color.GRAY) ;
//                pd.getButton(DatePickerDialog.BUTTON_NEUTRAL)
//                        .setTextColor(Color.GRAY) ;
                pd.show();
                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });

        UtilMethods.INSTANCE.FundReceiveStatus(FundRecReport.this,
                fromdate.getText().toString(),
                toDate.getText().toString(),mProgressDialog, new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        FundRecResponse recResponse = (FundRecResponse)object;
                       // new Gson().toJson(response.body()).toString();
                        dataParse(recResponse);
                    }
                });
    }
//{"Message":"Dear Customer , HR request is captured , Please Ensure your STB is Switched on","status":1,"Operator":null,"circle":null,"OPID":null}
    public void dataParse(FundRecResponse recResponse) {
       // Gson gson = new Gson();
        //transactions = gson.fromJson(response, FundRecResponse.class);
        transactionsObjects = recResponse.getFundReceive();
        mAdapter = new FundRecAdapter(transactionsObjects, FundRecReport.this,"");
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v==filter || v==filterimg )
        {
            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.FundReceiveStatus(FundRecReport.this,
                        fromdate.getText().toString(),
                        toDate.getText().toString(),mProgressDialog, new UtilMethods.ApiCallBack() {
                            @Override
                            public void onSucess(Object object) {
                                FundRecResponse recResponse = (FundRecResponse)object;

                                dataParse(recResponse);
                            }
                        });
            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
    }
    void filter(String text){
        ArrayList<FundRecObject> temp = new ArrayList();

        for(int i=0; i<transactionsObjects.size(); i++){
            if( text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(transactionsObjects.get(i).getFrom().toLowerCase(Locale.getDefault()))
                    || transactionsObjects.get(i).getFrom().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
                     ){

                temp.add(transactionsObjects.get(i));
            }
        }

        try{
            mAdapter.updateList(temp);
        }catch ( Exception e){}
    }

}