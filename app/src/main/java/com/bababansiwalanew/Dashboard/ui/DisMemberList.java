package com.bababansiwalanew.Dashboard.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

public  class DisMemberList extends AppCompatActivity  implements View.OnClickListener{
    private TextView name, mobile, email;
    Button changePassword, createUser;
    ImageView toggle, logout, logo;
    CardView pinPasswordLayoutContainer, pinPasswordChangeLayout;
    boolean flagPasscode = false;
    ProgressDialog loader ;
    RecyclerView recycler_view;
    UserListReportAdapter mAdapter;
    TextView noData;
    String response = "";
    EditText userName;
    RelativeLayout searchContainer;
    ImageView search;
    Toolbar toolbar;
    String from;
    Button Distributor,Retailer,APiUser,MD,SA;
    LinearLayoutManager mLayoutManager;
    ArrayList<UserListStatus> userListObjects = new ArrayList<>();
    ArrayList<UserListStatus> retailerListObjects = new ArrayList<>();
    ArrayList<UserListStatus> distributorListObjects = new ArrayList<>();
    ArrayList<UserListStatus> mDListObjects = new ArrayList<>();
    ArrayList<UserListStatus> subAdminListObjects = new ArrayList<>();
    ArrayList<UserListStatus>apiuserListObjects = new ArrayList<>();
    UserListReportResponse userlists = new UserListReportResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_1);
        recycler_view = (RecyclerView)  findViewById(R.id.recycler_view);
  from = getIntent().getStringExtra("from");
         toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        if (from!=null ){
            if (!from.equalsIgnoreCase(""))
            toolbar.setTitle(""+from);
        }


        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loader= new ProgressDialog(this);
        userName = (EditText) findViewById(R.id.userName);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = userName.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = userName.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }
        });
        search = (ImageView) findViewById(R.id.search);
        noData = (TextView)  findViewById(R.id.noData);
        Distributor =findViewById(R.id.Distributor);
        Retailer =findViewById(R.id.Retailer);
        APiUser =findViewById(R.id.ApiUser);
        MD =findViewById(R.id.subAdmin);
        SA =findViewById(R.id.MDistributor);
        Distributor.setOnClickListener(this);
        Retailer.setOnClickListener(this);
        APiUser.setOnClickListener(this);
        SA.setOnClickListener(this);
        MD.setOnClickListener(this);
        //    loader = new CustomLoader(DisMemberList.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        searchContainer = (RelativeLayout)  findViewById(R.id.searchContainer);
        recycler_view.setVisibility(View.VISIBLE);
        final SharedPreferences myPrefs =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,  MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        UtilMethods.INSTANCE.UserList(this, loader, mobileLogin, "Search", new UtilMethods.ApiCallBack() {
            @Override
            public void onSucess(Object object) {
                UserListReportResponse UserListReportResponse =(UserListReportResponse) object;
               // String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
                dataParse(UserListReportResponse);

            }
        });
        SetListener();


    }

    void filter(String text){
        ArrayList<UserListStatus> temp = new ArrayList();

        for(int i=0; i<userListObjects.size(); i++){
            if( text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(userListObjects.get(i).getOutletname().toLowerCase(Locale.getDefault()))
                    || userListObjects.get(i).getOutletname().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
                    ||text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(userListObjects.get(i).getLoginId().toLowerCase(Locale.getDefault()))
                    || userListObjects.get(i).getLoginId().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
            ){

                temp.add(userListObjects.get(i));
            }
        }

try{
    mAdapter.updateList(temp);
}catch ( Exception e){}
    }

    public void dataParse(UserListReportResponse response) {

       try{
            userListObjects = response.getUser();
               filterUser(userListObjects);
              }catch (Exception e){
            Log.e("errur",""+e.getMessage());
            e.printStackTrace();}


    }

    private void filterUser(ArrayList<UserListStatus>  userListObjects) {
       if (userListObjects.size()>0)
        for(int i=0; i<userListObjects.size(); i++){
            if( userListObjects.get(i).getRoleId().equalsIgnoreCase( "3")){
                retailerListObjects.add(userListObjects.get(i));

            }
            else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "2")){
                distributorListObjects.add(userListObjects.get(i));
                if (distributorListObjects.size() > 0) {
                    mAdapter = new UserListReportAdapter(distributorListObjects, DisMemberList.this,from,"Distributor");
                    mLayoutManager = new LinearLayoutManager(DisMemberList.this);
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
            else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "4")){
                apiuserListObjects.add(userListObjects.get(i));

            }
            else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "8")){
               mDListObjects.add(userListObjects.get(i));

            }
            else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "6")){
                subAdminListObjects.add(userListObjects.get(i));

            }
        }
try{
    if (retailerListObjects.size()>0){
    Retailer.setVisibility(View.VISIBLE);
}else {
    Retailer.setVisibility(View.GONE);
}
    if (distributorListObjects.size()>0){
        Distributor.setVisibility(View.VISIBLE);
    }else {
        Distributor.setVisibility(View.GONE);
    }
    if (apiuserListObjects.size()>0){
        APiUser.setVisibility(View.VISIBLE);
    }else {
        APiUser.setVisibility(View.GONE);
    }
    if (mDListObjects.size()>0){ MD.setVisibility(View.VISIBLE);
    }else {
        MD.setVisibility(View.GONE);
    }
    if (subAdminListObjects.size()>0){ SA.setVisibility(View.VISIBLE);
    }else {
        SA.setVisibility(View.GONE);
    }}catch (Exception e){}


    }

//    public void UserList(String response) {
//        recycler_view.setVisibility(View.VISIBLE);
//        dataParse(response);
//    }
    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
//        if (activityFragmentMessage.getFrom().equalsIgnoreCase("userList")) {
//            String response =activityFragmentMessage.getFrom();
//            Log.e("resonse",response);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
    private void SetListener() {
        search.setOnClickListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }
    @Override
    public void onClick(View v) {
        if (v==search){
            if (UtilMethods.INSTANCE.isNetworkAvialable(DisMemberList.this)) {

                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);

                UtilMethods.INSTANCE.UserList(DisMemberList.this,loader, userName.getText().toString().trim(), "Search",null);


                SharedPreferences myPrefs = DisMemberList.this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, DisMemberList.this.MODE_PRIVATE);
                String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
                Log.e("ressponse",userlst);

            } else {
//                UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
//                        getResources().getString(R.string.network_error_message));
            }
        }
          if (v==Distributor){
              Retailer.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
              Distributor.setBackground(getResources().getDrawable(R.drawable.buttonback_new));
              APiUser.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
              SA.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
              MD.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
              try
              { if (distributorListObjects.size() > 0) {
                  mAdapter = new UserListReportAdapter(distributorListObjects, DisMemberList.this,from,"Distributor");
                  mLayoutManager = new LinearLayoutManager(DisMemberList.this);
                  recycler_view.setLayoutManager(mLayoutManager);
                  recycler_view.setItemAnimator(new DefaultItemAnimator());
                  recycler_view.setAdapter(mAdapter);

                  noData.setVisibility(View.GONE);
                  recycler_view.setVisibility(View.VISIBLE);
              } else {
                  noData.setVisibility(View.VISIBLE);
                  recycler_view.setVisibility(View.GONE);
              }}catch (Exception e){
                  Toast.makeText(getApplicationContext(),"No Distributor Found !!",Toast.LENGTH_LONG).show();

              }

          }
        if (v==Retailer){
             Retailer.setBackground(getResources().getDrawable(R.drawable.buttonback_new));
            Distributor.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            APiUser.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            SA.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            MD.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            try{ if (retailerListObjects.size() > 0) {
                mAdapter = new UserListReportAdapter(retailerListObjects, DisMemberList.this,from,"Retailer");
                mLayoutManager = new LinearLayoutManager(DisMemberList.this);
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);

                noData.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);
            } else {
                noData.setVisibility(View.VISIBLE);
                recycler_view.setVisibility(View.GONE);
            }}catch (Exception e){
                Toast.makeText(getApplicationContext(),"No Retailer Found !!",Toast.LENGTH_LONG).show();

            }

        }
        if (v==APiUser){
            Retailer.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            Distributor.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            APiUser.setBackground(getResources().getDrawable(R.drawable.buttonback_new));
            SA.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            MD.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            try{
                if (apiuserListObjects.size() > 0) {
                    mAdapter = new UserListReportAdapter(apiuserListObjects, DisMemberList.this,from,"APiUser");
                    mLayoutManager = new LinearLayoutManager(DisMemberList.this);
                    recycler_view.setLayoutManager(mLayoutManager);
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(mAdapter);

                    noData.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"No APiUser Found !!",Toast.LENGTH_LONG).show();

            }

        }
        if (v==SA){
            Retailer.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            Distributor.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            APiUser.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            SA.setBackground(getResources().getDrawable(R.drawable.buttonback_new));
            MD.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            try{
                if (subAdminListObjects.size() > 0) {
                    mAdapter = new UserListReportAdapter(subAdminListObjects, DisMemberList.this,from,"SA");
                    mLayoutManager = new LinearLayoutManager(DisMemberList.this);
                    recycler_view.setLayoutManager(mLayoutManager);
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(mAdapter);

                    noData.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"No SubAdmin Found !!",Toast.LENGTH_LONG).show();
            }

        }
        if (v==MD){
            Retailer.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            Distributor.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            APiUser.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            MD.setBackground(getResources().getDrawable(R.drawable.buttonback_new));
            SA.setBackground(getResources().getDrawable(R.drawable.bg_btnok));
            try{
                if (mDListObjects.size() > 0) {
                    mAdapter = new UserListReportAdapter(mDListObjects, DisMemberList.this,from,"MD");
                    mLayoutManager = new LinearLayoutManager(DisMemberList.this);
                    recycler_view.setLayoutManager(mLayoutManager);
                    recycler_view.setItemAnimator(new DefaultItemAnimator());
                    recycler_view.setAdapter(mAdapter);

                    noData.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"No MD Found !!",Toast.LENGTH_LONG).show();
            }

        }
    }}
