package com.bababansiwalanew.DMR.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bababansiwalanew.DMR.dto.BeneficiaryResponse;
import com.bababansiwalanew.DMR.dto.Recipient;
import com.bababansiwalanew.DMR.dto.TABLE;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

public class BeneficiaryListScreen extends AppCompatActivity {

    RecyclerView recycler_view;
    TextView noData;
    BeneficiaryAdapter mAdapter;
    TABLE beneResponse;
    Toolbar toolbar;
    EditText rtSearch;
    ArrayList<Recipient> operator = new ArrayList<>();
    ProgressDialog mProgressDialog;

    public void finishMethod() {
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beneficiary_list_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Beneficiary List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rtSearch = findViewById(R.id.rtSearch);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        noData = (TextView) findViewById(R.id.noData);
        SharedPreferences prefs = this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, this.MODE_PRIVATE);
        String senderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

        UtilMethods.INSTANCE.GetBeneficiary(BeneficiaryListScreen.this, senderNumber, mProgressDialog, new UtilMethods.ApiCallBackTwoMethod() {
            @Override
            public void onSucess(Object object) {
                BeneficiaryResponse beneficiaryResponse = (BeneficiaryResponse) object;
                operator.clear();
                if (beneficiaryResponse.getData().getRecipientList() != null && beneficiaryResponse.getData().getRecipientList().size() > 0) {
                    noData.setVisibility(View.GONE);
                    operator = beneficiaryResponse.getData().getRecipientList();
                    mAdapter = new BeneficiaryAdapter(beneficiaryResponse.getData().getRecipientList(), BeneficiaryListScreen.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycler_view.setLayoutManager(mLayoutManager);
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(mAdapter);

                } else {
                    noData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String errorMsg) {

            }
        });


        rtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {


                if (rtSearch.getText().toString().length() > 0 && operator != null && operator.size() > 0) {

                    String text = rtSearch.getText().toString().toLowerCase(Locale.getDefault());
                    filter(text);
                }


            }
        });
    }

    void filter(String text) {
        ArrayList<Recipient> temp = new ArrayList();

        for (int i = 0; i < operator.size(); i++) {
            if (text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(operator.get(i).getRecipientName().toLowerCase(Locale.getDefault()))

                    || operator.get(i).getRecipientName().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
            ) {

                temp.add(operator.get(i));
            }
        }

        try {
            mAdapter.updateList(temp);
        } catch (Exception e) {
        }
    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("transferDoneDialog")) {
            SharedPreferences prefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String senderNumber = prefs.getString(ApplicationConstant.INSTANCE.senderNumberPref, null);

            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                UtilMethods.INSTANCE.GetSender(this, senderNumber, null, null);

            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
            finish();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
}