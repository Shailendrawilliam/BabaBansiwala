package com.bababansiwalanew.Dashboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

public class UserRoleActivity extends AppCompatActivity {
RecyclerView rv_role;
    UserRoleAdapter mAdapter;
    Toolbar toolbar;
    RecyclerView.LayoutManager mLayoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_role);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User Roles ");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rv_role= findViewById(R.id.rv_role);
        hitApi();
    }

    
    public void hitApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            try {
                UtilMethods.INSTANCE.UserRole(this,null, new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        UserListReportResponse userrole= (UserListReportResponse) object;
                        if (userrole != null) {

                            if (userrole.getRoleLIst() != null && userrole.getRoleLIst().size() > 0) {

                                mAdapter = new UserRoleAdapter(userrole.getRoleLIst(),  UserRoleActivity.this,"" );
                                mLayoutManager = new LinearLayoutManager( UserRoleActivity.this);
                                rv_role.setLayoutManager(mLayoutManager);
                                rv_role.setItemAnimator(new DefaultItemAnimator());
                                rv_role.setAdapter(mAdapter);

                                 rv_role.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(getApplicationContext(),"No Role Found !!",Toast.LENGTH_LONG).show();
                            }
                        }

                      
                       

                        }
                });
            } catch (Exception e) {
                e.printStackTrace();
                UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.attention_error_title), this.getResources().getString(R.string.err_msg_network), 0);
            }

        } else {
            UtilMethods.INSTANCE.dialogOk(this, this.getResources().getString(R.string.attention_error_title), this.getResources().getString(R.string.err_msg_network), 0);

        }
    }
    

}