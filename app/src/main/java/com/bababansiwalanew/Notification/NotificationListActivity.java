package com.bababansiwalanew.Notification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.Api.Response.AppUserListResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationListActivity extends AppCompatActivity {

    AppUserListResponse mNotificationResponse;
    RecyclerView recycler_view;
    TextView noData;
    NotificationListAdapter mNotificationListAdapter;
    private int readCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("Notification & Event");
        toolBar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        noData = findViewById(R.id.noData);
        mNotificationResponse = new Gson().fromJson(UtilMethods.INSTANCE.getNotificationList(this), AppUserListResponse.class);

        if (mNotificationResponse != null && mNotificationResponse.getNotifications() != null && mNotificationResponse.getNotifications().size() > 0) {
            mNotificationListAdapter = new NotificationListAdapter(this, mNotificationResponse.getNotifications());
            recycler_view.setAdapter(mNotificationListAdapter);
        } else {
            // noData.setVisibility(View.VISIBLE);
        }


    }

    public void setReadNotification(int position) {
        mNotificationResponse.getNotifications().get(position).setSeen(true);
        mNotificationListAdapter.notifyDataSetChanged();
        UtilMethods.INSTANCE.setNotificationList(this, new Gson().toJson(mNotificationResponse));
        readCount++;

        setResult(RESULT_OK, new Intent().putExtra("Notification_Count", readCount));
    }

}
