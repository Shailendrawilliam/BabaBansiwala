package com.bababansiwalanew.Dashboard.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

public  class UserListFragment  extends Fragment implements View.OnClickListener{
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
    LinearLayoutManager mLayoutManager;
    ArrayList<UserListStatus> userListObjects = new ArrayList<>();
    UserListReportResponse userlists = new UserListReportResponse();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_user_list, container, false);
        recycler_view = (RecyclerView) v. findViewById(R.id.recycler_view);
        userName = (EditText) v.findViewById(R.id.userName);
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
        search = (ImageView) v.findViewById(R.id.search);
        noData = (TextView)  v.findViewById(R.id.noData);
    //    loader = new CustomLoader(getActivity(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        searchContainer = (RelativeLayout) v. findViewById(R.id.searchContainer);
        recycler_view.setVisibility(View.VISIBLE);
        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        UtilMethods.INSTANCE.UserList(getActivity(),loader, mobileLogin, "Search",null);

        String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
        dataParse(userlst);
        SetListener();
        return v;
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

        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
        try{   Log.e("ressponse",userlst);
            Gson gson = new Gson();
            userlists = gson.fromJson(response, UserListReportResponse.class);
            userListObjects = userlists.getUser();

            if (userListObjects.size() > 0) {
                mAdapter = new UserListReportAdapter(userListObjects, getActivity(),"userlist","");
                mLayoutManager = new LinearLayoutManager(getActivity());
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);

                noData.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);
            } else {
                noData.setVisibility(View.VISIBLE);
                recycler_view.setVisibility(View.GONE);
            }}catch (Exception e){e.printStackTrace();}


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
            if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                loader.show();
                loader.setCancelable(false);
                loader.setCanceledOnTouchOutside(false);

                UtilMethods.INSTANCE.UserList(getActivity(),loader, userName.getText().toString().trim(), "Search",null);


                SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
                String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
                Log.e("ressponse",userlst);

            } else {
//                UtilMethods.INSTANCE.NetworkError(this, getResources().getString(R.string.network_error_title),
//                        getResources().getString(R.string.network_error_message));
            }
        }
    }
//
//    public void initialValues() {
//
//        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
//        String IsExist = myPrefs.getString(ApplicationConstant.INSTANCE.IsExist, null);
//        String OTP = myPrefs.getString(ApplicationConstant.INSTANCE.OTP, null);
//        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
//        String UMail = myPrefs.getString(ApplicationConstant.INSTANCE.UMail, null);
//        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
//        String UName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);
//
//        if (UMobile != null && UMobile.length() > 0) {
//            mobile.setText(UMobile);
//        }
//        if (UMail != null && UMail.length() > 0) {
//            email.setText(UMail);
//        }
//        if (UName != null && UName.length() > 0) {
//            name.setText(UName);
//        }
//        //////////////////////////////////////////////////////////////////////////
//        SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
//        flagPasscode = myPreferences.getBoolean(ApplicationConstant.INSTANCE.PinPasscodeFlag, false);
//
//        // Log.e("here", "flag : " + flagPasscode);
//
//        if (flagPasscode) {
//            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_on));
//        } else {
//            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_off));
//        }
//
//        if (!(UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("4") ||
//                UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("8")))
//            createUser.setVisibility(View.VISIBLE);
//        else
//            createUser.setVisibility(View.GONE);
//
//    }
}