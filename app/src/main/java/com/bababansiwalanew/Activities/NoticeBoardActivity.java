package com.bababansiwalanew.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bababansiwalanew.BalanceCheck.dto.BalanceCheckResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

public class NoticeBoardActivity extends AppCompatActivity {
    RecyclerView rv_role;
    NoticeAdapter mAdapter;
    Integer role; private ProgressDialog mProgressDialog = null;
    Integer userId;
    Toolbar toolbar;
    RecyclerView.LayoutManager mLayoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NoticeBoard ");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mProgressDialog = new ProgressDialog(this);
        rv_role= findViewById(R.id.rv_notice);
        hitApi();
    }



    private void hitApi() {

        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            try {
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();
                UtilMethods.INSTANCE.BellNotification(NoticeBoardActivity.this, mProgressDialog,new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        BalanceCheckResponse balanceCheckResponse=(BalanceCheckResponse)object;

                        if (balanceCheckResponse != null) {

                            if ( balanceCheckResponse.getNotification()!= null &&  balanceCheckResponse.getNotification().size() > 0) {

                                mAdapter = new NoticeAdapter( balanceCheckResponse.getNotification(),
                                        NoticeBoardActivity.this );
                                mLayoutManager = new LinearLayoutManager( NoticeBoardActivity.this);
                                rv_role.setLayoutManager(mLayoutManager);
                                rv_role.setItemAnimator(new DefaultItemAnimator());
                                rv_role.setAdapter(mAdapter);

                                rv_role.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(getApplicationContext(),"Not Found !!",Toast.LENGTH_LONG).show();
                            }
                        }




                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.attention_error_title), this.getResources().getString(R.string.err_msg_network), 0);

        }
    }
}