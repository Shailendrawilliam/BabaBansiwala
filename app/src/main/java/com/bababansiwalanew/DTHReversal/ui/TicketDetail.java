package com.bababansiwalanew.DTHReversal.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.DTHReversal.dto.ChatResponse;
import com.bababansiwalanew.DTHReversal.dto.ChatResponseObject;
import com.bababansiwalanew.DTHReversal.dto.DTHTicketResponse;
import com.bababansiwalanew.DTHReversal.dto.DataByTransactionId;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class TicketDetail extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog = null;
    private Toolbar toolbar;

    RecyclerView recycler_view;
    GetDataByTransIdAdapter mAdapter;
    String response = "";
    String ticketDataResponse;
    String from = "";
    String ticketId = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<DataByTransactionId> transactionsObjects = new ArrayList<>();
    DTHTicketResponse transactions = new DTHTicketResponse();

    ChatResponse chatResponse = new ChatResponse();
    ArrayList<ChatResponseObject> chatResponseObjects = new ArrayList<>();

    LinearLayout chatContainer;

    EditText replyText;
    Button replyButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_detail);

        response = getIntent().getExtras().getString("response");
        ticketDataResponse = getIntent().getExtras().getString("ticketData");
        from = getIntent().getExtras().getString("from");
        ticketId = getIntent().getExtras().getString("ticketId");

        Log.e("cominf", "here is : " + ticketDataResponse);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Ticket Dashboard");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        replyText = (EditText) findViewById(R.id.replyText);
        replyButton = (Button) findViewById(R.id.replyButton);
        replyButton.setOnClickListener(this);

        chatContainer = (LinearLayout) findViewById(R.id.chatContainer);

        dataParse(ticketDataResponse);
        dataParseChat(response);

    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, DTHTicketResponse.class);
        transactionsObjects = transactions.getDetail();

        mAdapter = new GetDataByTransIdAdapter(transactionsObjects, TicketDetail.this, ticketId);
        mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    public void dataParseChat(String response) {
        Gson gson = new Gson();
        chatResponse = gson.fromJson(response, ChatResponse.class);
        chatResponseObjects = chatResponse.getResponse();

        /////////////////////////////////////////////////////////////////////////////
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.chatContainer);
        TextView[] lLayout = new TextView[chatResponseObjects.size()];
        LinearLayout[] lLinearLayout = new LinearLayout[chatResponseObjects.size()];
        for (int i = 0; i < lLayout.length; i++) {

            lLinearLayout[i] = new LinearLayout(this);
            lLinearLayout[i].setBackgroundColor(getResources().getColor(R.color.grey_600));
            lLinearLayout[i].setPadding(0, 5, 0, 0);

            lLayout[i] = new TextView(this);
            lLayout[i].setId(i);
            lLayout[i].setText(chatResponseObjects.get(i).getRemarks());
            lLayout[i].setPadding(20, 20, 20, 20);
            lLayout[i].setBackgroundColor(Color.parseColor(chatResponseObjects.get(i).getBackground()));

            LinearLayout.LayoutParams myLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lLayout[i].setLayoutParams(myLayoutParams);
            lLinearLayout[i].setLayoutParams(myLayoutParams);

            lLinearLayout[i].addView(lLayout[i]);
            parentLayout.addView(lLinearLayout[i]);
        }
        /////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void onClick(View v) {
        if (v == replyButton) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.DTH_Insert_Response(this, chatResponseObjects.get(0).getTicketId(), replyText.getText().toString().trim(), mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(this, getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
    }
}