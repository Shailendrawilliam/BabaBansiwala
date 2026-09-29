package com.bababansiwalanew.CommissionSlab.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.bababansiwalanew.CommissionSlab.dto.CommissionSlabObject;
import com.bababansiwalanew.CommissionSlab.dto.CommissionSlabResponse;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class CommissionScreen extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    CommissionAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<CommissionSlabObject> transactionsObjects = new ArrayList<>();
    CommissionSlabResponse transactions = new CommissionSlabResponse();

    RelativeLayout searchLayout;
    EditText number;
    CardView cardContainer;
    EditText childNumber;

    String rechargeMobileNumber = "";
    String childMobileNumber = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharge_report);
        response = getIntent().getExtras().getString("response");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(CommissionScreen.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Commission Slab");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        number = (EditText) findViewById(R.id.number);
        cardContainer = (CardView) findViewById(R.id.cardContainer);
        searchLayout = (RelativeLayout) findViewById(R.id.searchLayout);
        cardContainer.setOnClickListener(this);

        childNumber = (EditText) findViewById(R.id.number);

        if (response.equalsIgnoreCase("specific")) {
            searchLayout.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);

        } else {
            searchLayout.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
            dataParse(response);
        }
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, CommissionSlabResponse.class);
        transactionsObjects = transactions.getCommission();

        mAdapter = new CommissionAdapter(transactionsObjects, CommissionScreen.this);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == cardContainer) {

            if (validateForm() == 0) {

            }
        }
    }

    public int validateForm() {

        int flag = 0;

        if (number.getText() != null && number.getText().toString().trim().length() > 1 &&
                !(number.getText().toString().trim().length() < 10) &&
                (number.getText().toString().trim().charAt(0) == '7' ||
                        number.getText().toString().trim().charAt(0) == '8' ||
                        number.getText().toString().trim().charAt(0) == '9')
                ) {
            rechargeMobileNumber = number.getText().toString().trim();
        } else {
            number.setError(getResources().getString(R.string.mobilenumber_error));
            number.requestFocus();
            flag++;
        }

        if (childNumber.getText() != null && childNumber.getText().toString().trim().length() > 1 &&
                !(childNumber.getText().toString().trim().length() < 10) &&
                (childNumber.getText().toString().trim().charAt(0) == '7' ||
                        childNumber.getText().toString().trim().charAt(0) == '8' ||
                        childNumber.getText().toString().trim().charAt(0) == '9')
                ) {
            childMobileNumber = childNumber.getText().toString().trim();
        } else {
            childNumber.setError(getResources().getString(R.string.mobilenumber_error));
            childNumber.requestFocus();
            flag++;
        }

        return flag;
    }

    public void specificReportList(String response) {
        searchLayout.setVisibility(View.VISIBLE);
        recycler_view.setVisibility(View.VISIBLE);
        dataParse(response);
    }
}
