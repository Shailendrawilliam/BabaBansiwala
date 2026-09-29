package com.bababansiwalanew.DTHReversal.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.bababansiwalanew.DTHReversal.dto.DataByTransactionId;
import com.bababansiwalanew.DTHReversal.dto.ReversalOpenListResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class GenerateTicket extends AppCompatActivity implements View.OnClickListener {

    EditText transId;
    Button search;
    private ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RelativeLayout firstContainer;

    RecyclerView recycler_view;
    GetDataByTransIdAdapter mAdapter;
    String response = "";
    String from = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<DataByTransactionId> transactionsObjects = new ArrayList<>();
    ReversalOpenListResponse transactions = new ReversalOpenListResponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_ticket);

        response = getIntent().getExtras().getString("response");
        from = getIntent().getExtras().getString("from");

        mProgressDialog = new ProgressDialog(this);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        firstContainer = (RelativeLayout) findViewById(R.id.firstContainer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Generate Ticket");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        transId = (EditText) findViewById(R.id.transId);
        search = (Button) findViewById(R.id.search);

        transId.setText("");
        search.setOnClickListener(this);

        if (from.equalsIgnoreCase("gettingData")) {
            firstContainer.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
        } else {
            firstContainer.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        }
        if (from.equalsIgnoreCase("gettingData"))
            dataParse(response);
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, ReversalOpenListResponse.class);
        transactionsObjects = transactions.getTransaction();

        mAdapter = new GetDataByTransIdAdapter(transactionsObjects, GenerateTicket.this, "");
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == search) {
            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                if (transId.getText() != null && transId.getText().toString().trim().length() > 0)
                    UtilMethods.INSTANCE.DTH_GetDataByTransactionId(this, transId.getText().toString().trim(), mProgressDialog);
                else {
                    transId.setError("Please enter valid transaction Id");
                }

            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
    }
}
