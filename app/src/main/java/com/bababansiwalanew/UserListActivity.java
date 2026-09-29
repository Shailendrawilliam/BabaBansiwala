package com.bababansiwalanew;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

import com.google.gson.Gson;

import com.bababansiwalanew.Dashboard.ui.UserListReportAdapter;
import com.bababansiwalanew.Dashboard.ui.UserListReportResponse;
import com.bababansiwalanew.Dashboard.ui.UserListStatus;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

public class UserListActivity extends AppCompatActivity  implements View.OnClickListener{
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
        String from,mobileLogin;
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
        setContentView(R.layout.activity_user_list2);
        recycler_view = (RecyclerView)  findViewById(R.id.recycler_view);
        from = getIntent().getStringExtra("from");
    mobileLogin = getIntent().getStringExtra("mobile");
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        if (from!=null ){
        if (!from.equalsIgnoreCase(""))
        toolbar.setTitle("UserList of "+from);
        }


        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        onBackPressed();
        }
        });



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
        //    loader = new CustomLoader(UserListActivity.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        searchContainer = (RelativeLayout)  findViewById(R.id.searchContainer);
        recycler_view.setVisibility(View.VISIBLE);
        SharedPreferences myPrefs =  getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,  MODE_PRIVATE);
        String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userUlist, null);

        if (userlst!=null){
                dataParse(userlst);
        }else {
                UtilMethods.INSTANCE.SubUserList(this,loader, mobileLogin, "Search",null);

                dataParse(userlst);
        }



        SetListener();

        }

        void filter(String text){
        ArrayList<UserListStatus> temp = new ArrayList();
//        Log.e("transactionsObjects",userListObjects.size()+"");
//        Log.e("Names :",userListObjects.get(0).getName());
//        Log.e("Names :",userListObjects.get(1).getName());
//        for(UserListRetailer d: transactionsObjects){
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if(d.getName().toLowerCase(Locale.getDefault()).contains(text) || d.getOutletName().toLowerCase(Locale.getDefault()).contains(text)){
//                temp.add(d);
//            }
//        }
        for(int i=0; i<userListObjects.size(); i++){
        if( text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(userListObjects.get(i).getOutletname().toLowerCase(Locale.getDefault()))
        || userListObjects.get(i).getOutletname().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
        ||text.toLowerCase(Locale.getDefault()).equalsIgnoreCase(userListObjects.get(i).getMobileno().toLowerCase(Locale.getDefault()))
        || userListObjects.get(i).getMobileno().toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))
        ){

        temp.add(userListObjects.get(i));
        }
        }
        //update recyclerview
        mAdapter.updateList(temp);
        }

public void dataParse(String response) {

        SharedPreferences myPrefs = UserListActivity.this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, UserListActivity.this.MODE_PRIVATE);
        String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userUlist, null);
        try{
        if (userlst!=null)
        Log.e("ressponse",userlst);


        Gson gson = new Gson();
        userlists = gson.fromJson(response, UserListReportResponse.class);
        userListObjects = userlists.getUser();
        filterUser(userListObjects);
        }catch (Exception e){e.printStackTrace();}


        }

private void filterUser(ArrayList<UserListStatus>  userListObjects) {
        for(int i=0; i<userListObjects.size(); i++){
        if( userListObjects.get(i).getRoleId().equalsIgnoreCase( "3")){
        retailerListObjects.add(userListObjects.get(i));

        }
        else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "2")){
        distributorListObjects.add(userListObjects.get(i));
        if (distributorListObjects.size() > 0) {
        mAdapter = new UserListReportAdapter(distributorListObjects, UserListActivity.this,from,"Distributor");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "6")){
        mDListObjects.add(userListObjects.get(i));

        }
        else if (userListObjects.get(i).getRoleId().equalsIgnoreCase( "7")){
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

public void UserList(String response) {
        recycler_view.setVisibility(View.VISIBLE);
        dataParse(response);
        }
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
        } private void SetListener() {
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
        if (UtilMethods.INSTANCE.isNetworkAvialable(UserListActivity.this)) {

        loader.show();
        loader.setCancelable(false);
        loader.setCanceledOnTouchOutside(false);

        UtilMethods.INSTANCE.UserList(UserListActivity.this,loader, userName.getText().toString().trim(), "Search",null);


        SharedPreferences myPrefs = UserListActivity.this.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, UserListActivity.this.MODE_PRIVATE);
        String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userUlist, null);
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
        mAdapter = new UserListReportAdapter(distributorListObjects, UserListActivity.this,from,"Distributor");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        mAdapter = new UserListReportAdapter(retailerListObjects, UserListActivity.this,from,"Retailer");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        mAdapter = new UserListReportAdapter(apiuserListObjects, UserListActivity.this,from,"APiUser");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        mAdapter = new UserListReportAdapter(subAdminListObjects, UserListActivity.this,from,"SA");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        mAdapter = new UserListReportAdapter(mDListObjects, UserListActivity.this,from,"MD");
        mLayoutManager = new LinearLayoutManager(UserListActivity.this);
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
        Toast.makeText(getApplicationContext(),"No MD Found !!", Toast.LENGTH_LONG).show();
        }

        }
        }}
