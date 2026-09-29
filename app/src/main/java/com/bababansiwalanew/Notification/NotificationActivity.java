package com.bababansiwalanew.Notification;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
 import com.bababansiwalanew.R;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notification");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String title = getIntent().getStringExtra("Title");
        String msg = getIntent().getStringExtra("Message");
        String imageUrl = getIntent().getStringExtra("Image");
        final String url = getIntent().getStringExtra("Url");
        final String time = getIntent().getStringExtra("Time");
        TextView titleTv = findViewById(R.id.title);
        TextView msgTv = findViewById(R.id.message);
        final ImageView imageIv = findViewById(R.id.banner);
        TextView detailBtn = findViewById(R.id.detailBtn);
        TextView timeTv = findViewById(R.id.time);
        if (time != null && !time.isEmpty()) {
            timeTv.setText(time + "");
        } else {
            findViewById(R.id.timeView).setVisibility(View.GONE);
        }
        titleTv.setText(title);
        msgTv.setText(msg);
        if (imageUrl != null && !imageUrl.isEmpty() && URLUtil.isValidUrl(imageUrl)) {
            imageIv.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(imageUrl)
                     .into(imageIv);
        } else {
            imageIv.setVisibility(View.GONE);
        }
        if (url != null && !url.isEmpty() && URLUtil.isValidUrl(url)) {
            detailBtn.setVisibility(View.VISIBLE);
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBrowser(url);
                }
            });
        } else {
            detailBtn.setVisibility(View.GONE);
        }


    }

    void openBrowser(String url) {
        url = url.replaceAll(" ", "");
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException anfe) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (ActivityNotFoundException anfe2) {

            }
        }

    }
}
