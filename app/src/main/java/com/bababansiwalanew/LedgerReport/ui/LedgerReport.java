package com.bababansiwalanew.LedgerReport.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.LedgerReport.dto.LedgerObject;
import com.bababansiwalanew.LedgerReport.dto.LedgerReportResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Lalit on 10-04-2017.
 */

public class LedgerReport extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    LedgerReportAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<LedgerObject> transactionsObjects = new ArrayList<>();
    LedgerReportResponse transactions = new LedgerReportResponse();


    String from = "";
LinearLayout wallettype;

    RelativeLayout searchLayout;
    EditText number;
    CardView cardContainer;
    EditText childNumber;
    String rechargeMobileNumber = "";
    String childMobileNumber = "";
    RadioButton rbPrepaid, rbUtility;


    LinearLayout searchContainer;
    TextView fromDate, toDate, searchDone;
    EditText accountNumber;
    boolean flagFilter = false;

    String fromDateVar, toDateVar, accountNumberVar;

    final Calendar myCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener fromDateDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            fromDateResult();
        }

    };

    final DatePickerDialog.OnDateSetListener toDateDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            toDateResult();
        }

    };
    public void fromDateResult() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void toDateResult() {
        String myFormat = "dd/MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toDate.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledger_report_ne);
        response = getIntent().getExtras().getString("response");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        wallettype =  findViewById(R.id.wallettype);
        mProgressDialog = new ProgressDialog(LedgerReport.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Ledger Report");
        setSupportActionBar(toolbar);
       /* number = (EditText) findViewById(R.id.number);
        cardContainer = (CardView) findViewById(R.id.cardContainer);
        searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
        cardContainer.setOnClickListener(this);

        childNumber = (EditText) findViewById(R.id.childNumber);*/
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rbPrepaid = findViewById(R.id.rbPrepaid);
        rbUtility = findViewById(R.id.rbUtility);
        wallettype.setVisibility(View.VISIBLE);
        dataParse(response);
        searchContainer = (LinearLayout) findViewById(R.id.filterSearchContainer);
        fromDate = (TextView) findViewById(R.id.filterFromDate);
        toDate = (TextView) findViewById(R.id.filterToDate);
        searchDone = (TextView) findViewById(R.id.searchDone);
        accountNumber = (EditText) findViewById(R.id.filterAccountNumber);
        accountNumber.setVisibility(View.INVISIBLE);

        searchDone.setOnClickListener(this);
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, LedgerReportResponse.class);
        transactionsObjects = transactions.getLedger();

        mAdapter = new LedgerReportAdapter(transactionsObjects, LedgerReport.this);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }



    @Override
    public void onClick(View v) {
        if (v == fromDate) {
            DatePickerDialog pd =new DatePickerDialog(this, fromDateDialog, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            pd.show();
            pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
            pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
        }

        if (v == toDate) {
            DatePickerDialog pd =  new DatePickerDialog(this, toDateDialog, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            pd.show();
            pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
            pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
        }

        if (v == searchDone) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                if (fromDate.getText() != null && fromDate.getText().toString().trim().length() > 0) {
                    fromDateVar = fromDate.getText().toString().trim();
                } else {
                    fromDateVar = "";
                }

                if (toDate.getText() != null && toDate.getText().toString().trim().length() > 0) {
                    toDateVar = toDate.getText().toString().trim();
                } else {
                    toDateVar = "";
                }

                if (accountNumber.getText() != null && accountNumber.getText().toString().trim().length() > 0) {
                    accountNumberVar = accountNumber.getText().toString().trim();
                } else {
                    accountNumberVar = "";
                }

                if (rbPrepaid.isChecked()){
                    UtilMethods.INSTANCE.Ledger(this, fromDateVar, toDateVar, accountNumberVar,"1", mProgressDialog);
                }else if (rbUtility.isChecked()){
                    UtilMethods.INSTANCE.Ledger(this, fromDateVar, toDateVar, accountNumberVar,"2", mProgressDialog);
                }


            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }





    }
}
