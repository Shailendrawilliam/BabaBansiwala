package com.bababansiwalanew.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bababansiwalanew.R;

public class ViewNoticeActivity extends AppCompatActivity {

    String role;
    String Mg,Date; TextView text,date;
    ImageView closeBtn,back;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);
        Date = getIntent().getExtras().getString("date");
        Mg = getIntent().getExtras().getString("mg");

        text= findViewById(R.id.text);
        if (Mg!=null)
        text.setText(""+Mg);

        date= findViewById(R.id.date);
        if (Date!=null)
            date.setText(" Created on "+Date);
        closeBtn= findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}