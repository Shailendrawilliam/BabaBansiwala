package com.bababansiwalanew.ROffer.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.ROffer.dto.ROfferObject;
import com.bababansiwalanew.ROffer.dto.ROfferResponse;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class ROffer extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    RecyclerView recycler_view;
    ROfferAdapter mAdapter;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<ROfferObject> transactionsObjects = new ArrayList<>();
    ROfferResponse transactions = new ROfferResponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roffer);

        response=getIntent().getExtras().getString("response");

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("R Offers");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        parseData(response);
    }

    public void parseData(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, ROfferResponse.class);
        transactionsObjects = transactions.getRecords();

        mAdapter = new ROfferAdapter(transactionsObjects, ROffer.this);
        mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    public void ItemClick(String amount) {

        Log.e("here", amount);

        FragmentActivityMessage activityActivityMessage =
                new FragmentActivityMessage("" + amount, "rOffer_Amount");
        GlobalBus.getBus().post(activityActivityMessage);

        finish();
    }
}
