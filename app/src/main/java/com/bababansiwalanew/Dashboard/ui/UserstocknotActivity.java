package com.bababansiwalanew.Dashboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

public class UserstocknotActivity extends AppCompatActivity {



    RecyclerView rv_role;
    UserStockNotTakenAdapter mAdapter;
    String  from;
    String   userId;
    Toolbar toolbar; ProgressDialog mProgressDialog;
    RecyclerView.LayoutManager mLayoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userstocknot);

        from = getIntent().getExtras().getString("from");
        userId = getIntent().getExtras().getString("userId");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (from.equals("notdone")){
        getSupportActionBar().setTitle("DownlineUserRechargeNotDone");
        }else {
            getSupportActionBar().setTitle("DownlineUserStockNotTaken");
        }
        Log.e("userId"," userId "+ userId);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mProgressDialog= new  ProgressDialog(this);
        rv_role= findViewById(R.id.rv_role);
        if (from.equals("notdone")){
            DownlineUserStockNotTaken();
        }else {
            DownlineUserRechargeNotDone();}
    }


    public void DownlineUserStockNotTaken() {
        mProgressDialog.show();
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            try {
                UtilMethods.INSTANCE.DownlineUserStockNotTaken(this,mProgressDialog,   userId,new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        DownlineUserResponse userrole= (DownlineUserResponse) object;
                        if (userrole != null) {

                            if (userrole.getStockList() != null && userrole.getStockList().size() > 0) {

                                mAdapter = new UserStockNotTakenAdapter(userrole.getStockList(),  UserstocknotActivity.this,"DownlineUser" );
                                mLayoutManager = new LinearLayoutManager( UserstocknotActivity.this);
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
                Log.e("errr"," DownlineUserStockNotTaken "+e.getMessage());
                e.printStackTrace();
            }

        } else {
            UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.attention_error_title), this.getResources().getString(R.string.err_msg_network), 0);

        }
    }
    public void DownlineUserRechargeNotDone() {
        mProgressDialog.show();
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            try {
                UtilMethods.INSTANCE.DownlineUserRechargeNotDone(this,mProgressDialog, userId,new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        DownlineUserResponse userrole= (DownlineUserResponse) object;
                        if (userrole != null) {

                            if (userrole.getRechargeList() != null && userrole.getRechargeList().size() > 0) {

                                mAdapter = new UserStockNotTakenAdapter(userrole.getRechargeList(),  UserstocknotActivity.this,"DownlineUser" );
                                mLayoutManager = new LinearLayoutManager( UserstocknotActivity.this);
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
                Log.e("err"," DownlineUserStockNotTaken "+e.getMessage());
                e.printStackTrace();
            }

        } else {
            UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.attention_error_title), this.getResources().getString(R.string.err_msg_network), 0);

        }
    }

}