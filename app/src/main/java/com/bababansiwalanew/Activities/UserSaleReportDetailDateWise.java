package com.bababansiwalanew.Activities;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.bababansiwalanew.Login.dto.GetOperatorResponse;
import com.bababansiwalanew.Login.dto.SaleReportObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

public class UserSaleReportDetailDateWise extends AppCompatActivity  implements View.OnClickListener{
    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    UserSaleReportDetailDateWiseAdapter mAdapter;
    String response = "";
    String from = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<SaleReportObject> transactionsObjects = new ArrayList<>();
    GetOperatorResponse transactions = new GetOperatorResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sale_report_detail_date_wise);
        response = getIntent().getExtras().getString("response");
        from = getIntent().getExtras().getString("from");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(UserSaleReportDetailDateWise.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        if (from.equalsIgnoreCase("UserSaleReportDetailAllDateWise")){
            toolbar.setTitle("UserSaleReportDetailAllDateWise ");
        }else {
            toolbar.setTitle("UserSaleReportDetailDateWise ");
        }

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
        transactions = gson.fromJson(response, GetOperatorResponse.class);
        transactionsObjects = transactions.getSaleReport();

        mAdapter = new UserSaleReportDetailDateWiseAdapter(transactionsObjects, UserSaleReportDetailDateWise.this);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {

    }
}
