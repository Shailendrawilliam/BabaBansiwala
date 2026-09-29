package com.bababansiwalanew.DTHReversal.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.bababansiwalanew.DTHReversal.dto.OpenDetailObject;
import com.bababansiwalanew.DTHReversal.dto.ReversalOpenListResponse;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class DTHReversalOpenTickets extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    DTHReversalTicketAdapter mAdapter;
    String response = "";
    String from = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<OpenDetailObject> transactionsObjects = new ArrayList<>();
    ReversalOpenListResponse transactions = new ReversalOpenListResponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledger_report);
        response = getIntent().getExtras().getString("response");
        from = getIntent().getExtras().getString("from");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(DTHReversalOpenTickets.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        if (from.equalsIgnoreCase("open"))
            toolbar.setTitle("Open Tickets");
        else if (from.equalsIgnoreCase("getdata"))
            toolbar.setTitle("DTH Ticket");
        else
            toolbar.setTitle("Closed Tickets");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataParse(response);

    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, ReversalOpenListResponse.class);
        transactionsObjects = transactions.getDetail();

        mAdapter = new DTHReversalTicketAdapter(transactionsObjects, DTHReversalOpenTickets.this);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}