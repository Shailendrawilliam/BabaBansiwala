package com.bababansiwalanew.Util.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.dto.BankListObject;
import com.bababansiwalanew.Util.dto.BankListResponse;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Lalit on 14-04-2017.
 */

public class BankListScreen extends AppCompatActivity {

    EditText rtSearch;

    RecyclerView recycler_view;
    TextView noData;
    BankListScreenAdapter mAdapter;
    ArrayList<BankListObject> operator = new ArrayList<>();
    BankListResponse operatorList = new BankListResponse();
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bank List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        noData = (TextView) findViewById(R.id.noData);

        getOperatorList();
        rtSearch = findViewById(R.id.rtSearch);
        rtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {


                if (rtSearch.getText().toString().length() >0 && operator!=null && operator.size()>0) {

                    String text = rtSearch.getText().toString().toLowerCase(Locale.getDefault());
                    filter(text);
                }


            }
        });
        if (operator != null && operator.size() > 0) {
            noData.setVisibility(View.GONE);

            mAdapter = new BankListScreenAdapter(operator, BankListScreen.this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

        } else {
            noData.setVisibility(View.VISIBLE);
        }
    }

    void filter(String text){
        ArrayList<BankListObject> temp = new ArrayList();

        for(int i=0; i<operator.size(); i++){
            if( text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(operator.get(i).getBankName().toLowerCase(Locale.getDefault()))
                    || operator.get(i).getBankName().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
            ){

                temp.add(operator.get(i));
            }
        }

        try{
            mAdapter.updateList(temp);
        }catch ( Exception e){}
    }

    public void getOperatorList() {
        SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        String response = prefs.getString(ApplicationConstant.INSTANCE.bankListPref, null);
        Gson gson = new Gson();
        operatorList = gson.fromJson(response, BankListResponse.class);
        operator = operatorList.getBanks();

    }

    public void ItemClick(String id, String name, String accVerification, String shortCode, String ifsc) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("bankName", name);
        clickIntent.putExtra("bankId", id);
        clickIntent.putExtra("accVerification", accVerification);
        clickIntent.putExtra("shortCode", shortCode);
        clickIntent.putExtra("ifsc", ifsc);
        setResult(4, clickIntent);
        finish();
    }
}
