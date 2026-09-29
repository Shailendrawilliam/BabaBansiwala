package com.bababansiwalanew.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import com.bababansiwalanew.GiftCardServices.ui.GiftCardForm;
import com.bababansiwalanew.Login.dto.LoginData;
import com.bababansiwalanew.Login.dto.LoginResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class OfferSheetActivity extends AppCompatActivity   {
    ProgressDialog mProgressDialog = null;
    RecyclerView recycler_view;
    OfferSheetAdapter mAdapter;
    TextView noData;
    Toolbar toolbar;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<LoginData> transactionsObjects = new ArrayList<LoginData>();
    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    LoginResponse transactions = new LoginResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_sheet);
        // setContentView(new Zoom(this));
        response=getIntent().getExtras().getString("response");

        mProgressDialog = new ProgressDialog(OfferSheetActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OfferSheet ");
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
        mProgressDialog = new ProgressDialog(OfferSheetActivity.this);

        recycler_view.setVisibility(View.VISIBLE);
       // GetUpdate();
        if (response!=null){
            dataParse(response);
        }


    }
    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("OfferSheet")) {
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
        if (UtilMethods.INSTANCE.isNetworkAvialable(OfferSheetActivity.this)) {

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

           // UtilMethods.INSTANCE.OfferSheet(OfferSheetActivity.this,  mProgressDialog);

        } else {
            UtilMethods.INSTANCE.dialogOk(OfferSheetActivity.this, getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }
    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, LoginResponse.class);
        transactionsObjects = transactions.getTable();

        if (transactionsObjects.size() > 0) {
            mAdapter = new OfferSheetAdapter(transactionsObjects, OfferSheetActivity.this);
            mLayoutManager = new LinearLayoutManager(OfferSheetActivity.this);
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




}
