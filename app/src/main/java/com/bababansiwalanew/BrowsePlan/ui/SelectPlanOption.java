package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Intent;
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
import com.bababansiwalanew.Util.dto.NumberList;
import com.bababansiwalanew.Util.dto.NumberListResponse;

import java.util.ArrayList;

/**
 * Created by Lalit on 25-02-2017.
 */

public class SelectPlanOption extends AppCompatActivity {

    RecyclerView recycler_view;
    TextView noData;
    SelectPlanOptionAdapter mAdapter;
    ArrayList<NumberList> operator = new ArrayList<>();
    ArrayList<NumberList> operatorSelection = new ArrayList<>();
    ArrayList<String> rechargeType = new ArrayList<>();
    Toolbar toolbar;
    String from = "";
    String operatorId = "";
    String opListName = "";
    String opListNameCode = "";
    int opList = 0;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_option_layout);

        from = getIntent().getExtras().getString("from");
        operatorId = getIntent().getExtras().getString("operatorId");
        opList = getIntent().getExtras().getInt("opList");
        if (opList == 1)
            opListName = getIntent().getExtras().getString("opListName");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (from.equalsIgnoreCase("operator"))
            getSupportActionBar().setTitle("Operator's");
        else
            getSupportActionBar().setTitle("Zone");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        noData = (TextView) findViewById(R.id.noData);

        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.numberListPref, null);
        NumberListResponse numberListResponse = new Gson().fromJson(response, NumberListResponse.class);
        operator = numberListResponse.getNumberList();
try{
        if (from.equalsIgnoreCase("operator")) {
            for (NumberList operatorDetail : operator) {
                String type = operatorDetail.getOperator();
                if (!rechargeType.contains(type)) {
                    operatorSelection.add(operatorDetail);
                    rechargeType.add(type);
                }
            }
        }
        else {
            if (opList == 0) {
                for (NumberList operatorDetail : operator) {
                    String type = operatorDetail.getCircle();
                    if (operatorDetail.getIReffOp().equalsIgnoreCase(operatorId)) {
                        if (!rechargeType.contains(type)) {
                            operatorSelection.add(operatorDetail);
                            rechargeType.add(type);
                        }

                    }
                }
            }
            else {
                String opListNameTemp = opListName;
                for (NumberList operatorDetail : operator) {
                    String type = operatorDetail.getCircle();
                    if (opListName.contains("-"))
                        opListNameTemp = opListName.substring(0, opListName.indexOf('-'));
                    if (operatorDetail.getOperator().equalsIgnoreCase(opListNameTemp)) {
                        if (!rechargeType.contains(type)) {
                            operatorSelection.add(operatorDetail);
                            rechargeType.add(type);
                            opListNameCode = operatorDetail.getIReffOp();
                        }

                    }
                }
            }
        }
            if (operator != null && operator.size() > 0) {
            noData.setVisibility(View.GONE);

            mAdapter = new SelectPlanOptionAdapter(operatorSelection, SelectPlanOption.this, from);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

        }
        else {
            noData.setVisibility(View.VISIBLE);
        }}
catch (Exception e){

}
    }

    public void ItemClick(String name, String id, String from) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("selected", name);
        clickIntent.putExtra("selectedId", id);
        if (from.equalsIgnoreCase("operator"))
            setResult(1, clickIntent);
        else if (from.equalsIgnoreCase("zoneOp")) {
            clickIntent.putExtra("opListName", opListName);
            clickIntent.putExtra("opListNameCode", opListNameCode);
            setResult(3, clickIntent);
        } else
            setResult(2, clickIntent);
        finish();
    }

}
