package com.bababansiwalanew.Dashboard.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.GiftCardServices.dto.GiftCardServicesResponse;
import com.bababansiwalanew.GiftCardServices.dto.GiftCardServicesResponsedata;
import com.bababansiwalanew.GiftCardServices.ui.GiftCardForm;
import com.bababansiwalanew.GiftCardServices.ui.GiftCardServicesAdapter;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class GiftCardServicesActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog = null;
    RecyclerView recycler_view;
    GiftCardServicesAdapter mAdapter;
    TextView noData;
     Toolbar toolbar;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<GiftCardServicesResponsedata> transactionsObjects = new ArrayList<GiftCardServicesResponsedata>();
    GiftCardServicesResponse transactions = new GiftCardServicesResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_card_services);

        mProgressDialog = new ProgressDialog(GiftCardServicesActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("GiftCard List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recycler_view = (RecyclerView)  findViewById(R.id.recycler_view);
        noData = (TextView)  findViewById(R.id.noData);
        mProgressDialog = new ProgressDialog(GiftCardServicesActivity.this);

        recycler_view.setVisibility(View.VISIBLE);
        GetUpdate();
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("giftcarddetail")) {
          dataParse(activityFragmentMessage.getMessage());

        }
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("giftcardselectedkey")) {
            Intent intent = new Intent(getApplicationContext(), GiftCardForm.class);
            intent.putExtra("VoucherCode",activityFragmentMessage.getMessage());
            startActivity(intent);

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void GetUpdate() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(GiftCardServicesActivity.this)) {

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

            UtilMethods.INSTANCE.GetGiftVoucher(GiftCardServicesActivity.this,  mProgressDialog);

        } else {
            UtilMethods.INSTANCE.dialogOk(GiftCardServicesActivity.this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }
    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, GiftCardServicesResponse.class);
        transactionsObjects = transactions.getData();

        if (transactionsObjects.size() > 0) {
            mAdapter = new GiftCardServicesAdapter(transactionsObjects, GiftCardServicesActivity.this);
            mLayoutManager = new LinearLayoutManager(GiftCardServicesActivity.this);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

            noData.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
        } else {
            noData.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        }
    }

    public void ItemClick(String image,  String pro_key, String provider, String pro_type, String pro_desc) {
        Intent clickIntent = new Intent(GiftCardServicesActivity.this, GiftCardForm.class);
        clickIntent.putExtra("image", image);
        clickIntent.putExtra("pro_key", pro_key);
        clickIntent.putExtra("provider", provider);
        clickIntent.putExtra("pro_type", pro_type);
        clickIntent.putExtra("pro_desc", pro_desc);
        setResult(1, clickIntent);
       // finish();
    }
}
