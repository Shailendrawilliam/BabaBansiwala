package com.bababansiwalanew.DMRReport.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bababansiwalanew.DMRReport.dto.DMRReportResponse;
import com.bababansiwalanew.DMRReport.dto.DMRTransactions;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Lalit on 18-04-2017.
 */

public class DMRReportScreen extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    DMRReportAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<DMRTransactions> transactionsObjects = new ArrayList<>();
    DMRReportResponse transactions = new DMRReportResponse();

    LinearLayout searchContainer;
    TextView fromDate, toDate, searchDone;
    EditText accountNumber;
    boolean flagFilter = false;

    String fromDateVar="", toDateVar="", accountNumberVar="";

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dmr_report_screen);
     //   response = getIntent().getExtras().getString("response");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(DMRReportScreen.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("DMR Ledger Report");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        searchContainer = (LinearLayout) findViewById(R.id.filterSearchContainer);
        fromDate = (TextView) findViewById(R.id.filterFromDate);
        toDate = (TextView) findViewById(R.id.filterToDate);
        searchDone = (TextView) findViewById(R.id.searchDone);
        accountNumber = (EditText) findViewById(R.id.filterAccountNumber);

        searchDone.setOnClickListener(this);
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);


        UtilMethods.INSTANCE.DMRTransaction(this, fromDateVar, toDateVar, accountNumberVar, mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
            @Override
            public void onSucess(Object object) {

                DMRReportResponse response = (DMRReportResponse)object;
                transactionsObjects = response.getDMRTransactions();

                mAdapter = new DMRReportAdapter(transactionsObjects, DMRReportScreen.this);
                mLayoutManager = new LinearLayoutManager(DMRReportScreen.this, LinearLayoutManager.VERTICAL,false);
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);


            }

            @Override
            public void onError(String errorMsg) {
                UtilMethods.INSTANCE.dialogOk(DMRReportScreen.this,
                        getResources().getString(R.string.failed),
                        errorMsg, 2);
            }
        });


    }


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dmr_report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_dmr_report_search) {
            filterShow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void filterShow() {
        if (flagFilter) {
            flagFilter = false;
            searchContainer.setVisibility(View.GONE);
        } else {
            flagFilter = true;
            searchContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == fromDate) {
            DatePickerDialog pd =    new DatePickerDialog(this, fromDateDialog, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)) ;

            pd.show();
            pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
            pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
        }

        if (v == toDate) {
            DatePickerDialog pd =    new DatePickerDialog(this, toDateDialog, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)) ;

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

                UtilMethods.INSTANCE.DMRTransaction(this, fromDateVar, toDateVar, accountNumberVar, mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {

                        DMRReportResponse response = (DMRReportResponse)object;
                        transactionsObjects = response.getDMRTransactions();

                        mAdapter = new DMRReportAdapter(transactionsObjects, DMRReportScreen.this);
                        mLayoutManager = new LinearLayoutManager(DMRReportScreen.this, LinearLayoutManager.VERTICAL,false);
                        recycler_view.setLayoutManager(mLayoutManager);
                        recycler_view.setItemAnimator(new DefaultItemAnimator());
                        recycler_view.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        UtilMethods.INSTANCE.dialogOk(DMRReportScreen.this,
                                getResources().getString(R.string.failed),
                                errorMsg, 2);
                    }
                });

            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
    }


}
