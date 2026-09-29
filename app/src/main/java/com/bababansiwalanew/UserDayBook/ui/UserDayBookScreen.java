package com.bababansiwalanew.UserDayBook.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookLedger;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookResponse;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookSummary;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class UserDayBookScreen extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view, recycler_view_ledger;
    UserDayBookAdapter mAdapter;
    UserDayBookLedgerAdapter mAdapterLedger;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<UserDayBookSummary> transactionsObjects = new ArrayList<>();
    ArrayList<UserDayBookLedger> ledgerObjects = new ArrayList<>();
    UserDayBookResponse transactions = new UserDayBookResponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdaybook_report);
        response = getIntent().getExtras().getString("response");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view_ledger = (RecyclerView) findViewById(R.id.recycler_view_ledger);
        mProgressDialog = new ProgressDialog(UserDayBookScreen.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("User Daybook Report");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataParse(response);
        dataParseLedger(response);
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, UserDayBookResponse.class);
        transactionsObjects = transactions.getSummary();
        if (transactionsObjects != null && transactionsObjects.size() > 0) {
            mAdapter = new UserDayBookAdapter(transactionsObjects, UserDayBookScreen.this);
            mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);
        }
    }

    public void dataParseLedger(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, UserDayBookResponse.class);
        ledgerObjects = transactions.getLedger();
        if (ledgerObjects != null && ledgerObjects.size() > 0) {
            mAdapterLedger = new UserDayBookLedgerAdapter(ledgerObjects, UserDayBookScreen.this);
            mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
            recycler_view_ledger.setLayoutManager(mLayoutManager);
            recycler_view_ledger.setItemAnimator(new DefaultItemAnimator());
            recycler_view_ledger.setAdapter(mAdapterLedger);
        }
    }

    @Override
    public void onClick(View v) {

    }
}