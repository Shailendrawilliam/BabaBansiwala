package com.bababansiwalanew.Util.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.dto.BankDetail;
import com.bababansiwalanew.Util.dto.BankDetailResponse;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class BankDetailList extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;
    RecyclerView recycler_view;
    BankDetailAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<BankDetail> transactionsObjects = new ArrayList<>();
    BankDetailResponse transactions = new BankDetailResponse();
    TextView noData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledger_report);

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(BankDetailList.this);
        noData = (TextView) findViewById(R.id.noData);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Bank Detail's");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = myPrefs.getString(ApplicationConstant.INSTANCE.bankDetailListPref, null);
        dataParse(response);

    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, BankDetailResponse.class);
      try{
          if (transactions!=null)
              transactionsObjects = transactions.getBanks();
          if (transactionsObjects.size() > 0) {
              noData.setVisibility(View.GONE);
              recycler_view.setVisibility(View.VISIBLE);

              mAdapter = new BankDetailAdapter(transactionsObjects, BankDetailList.this);
              mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
              recycler_view.setLayoutManager(mLayoutManager);
              recycler_view.setItemAnimator(new DefaultItemAnimator());
              recycler_view.setAdapter(mAdapter);

          } else {
              noData.setVisibility(View.VISIBLE);
              recycler_view.setVisibility(View.GONE);
          }
      }catch (Exception e){}
    }

    @Override
    public void onClick(View v) {

    }

    public void AdapterClicked(String param) {
        FragmentActivityMessage activityActivityMessage =
                new FragmentActivityMessage("" + param, "bankSelected");
        GlobalBus.getBus().post(activityActivityMessage);
        finish();
    }
}
