package com.bababansiwalanew.Dashboard.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class UserStockNotTakenAdapter extends RecyclerView.Adapter< UserStockNotTakenAdapter.MyViewHolder> {

    private ArrayList<UserRole> UsersList;
    private Context mContext;
    private String   from;
    private String   role;
    private RecyclerView   subrecyclerview;
    int resourceId = 0;
    String fund ="1";

    ProgressDialog mProgressDialog = null;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView RetailerName;
        public AppCompatTextView MobileNumber;
        public AppCompatTextView name;   LinearLayout ll_remark  ,ll_name ,ll_ba;
        public AppCompatTextView CurrentBalance;
        public AppCompatTextView ba;

        public AppCompatButton fndtransButton,viewdetails,downline;
        public AppCompatButton notdone,stocknot ;


        public MyViewHolder(View view) {
            super(view);
            mProgressDialog= new ProgressDialog(mContext);
            RetailerName = (AppCompatTextView) view.findViewById(R.id.retailer_name);
            MobileNumber = (AppCompatTextView) view.findViewById(R.id.mobile_number);
            CurrentBalance = (AppCompatTextView) view.findViewById(R.id.current_balance);
            name = (AppCompatTextView) view.findViewById(R.id.name);
            ba = (AppCompatTextView) view.findViewById(R.id.ba);
            //  current_utility = (AppCompatTextView) view.findViewById(R.id.current_utility);
            // fnddeductButton = (AppCompatButton) view.findViewById(R.id.actionButton);
            fndtransButton = (AppCompatButton) view.findViewById(R.id.fundTrasfer);
            viewdetails = (AppCompatButton) view.findViewById(R.id.viewdetails);
            downline = (AppCompatButton) view.findViewById(R.id.downline);
            stocknot = (AppCompatButton) view.findViewById(R.id.stocknot);
            notdone = (AppCompatButton) view.findViewById(R.id.notdone);
            subrecyclerview = (RecyclerView) view.findViewById(R.id.subrecyclerview);
            ll_remark    =  view.findViewById(R.id.ll_remark);
             ll_name =  view.findViewById(R.id.ll_name);
            ll_ba =   view.findViewById(R.id.ll_ba);
        }
    }

    public  UserStockNotTakenAdapter(ArrayList<UserRole> UsersList, Context mContext,String from ) {
        this.UsersList = UsersList;
        this.mContext = mContext;
        this.from = from;
        this.role = role;
    }

    @Override
    public  UserStockNotTakenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_stock_adapter, parent, false);

        return new  UserStockNotTakenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final  UserStockNotTakenAdapter.MyViewHolder holder, int position) {
        final UserRole operator = UsersList.get(position);

            holder.RetailerName.setText("" + operator.getRole_Name());
            holder.name.setText("" + operator.getName());
            holder.ba.setText("" + operator.getBalance());
            holder.ll_name.setVisibility(View.VISIBLE);
            holder.ll_ba.setVisibility(View.VISIBLE);
            holder.ba.setVisibility(View.VISIBLE);

           holder.MobileNumber.setText(" " + operator.getMobileNo().toString());





    }



    private void userSaleReport(String User) {
        if (from.equalsIgnoreCase("UserSaleReport")){
            if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.UserSaleReportDetail(mContext,   User,mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                        mContext.getResources().getString(R.string.network_error_message), 2);
            }
        }
        else if (from.equalsIgnoreCase("UserSaleReportDetailDateWise")){
            if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.UserSaleReportDetailDateWise(mContext,   User,mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                        mContext.getResources().getString(R.string.network_error_message), 2);
            }
        }
        else if (from.equalsIgnoreCase("UserSaleReportDetailAllDateWise")){
            if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.UserSaleReportDetailAllDateWise(mContext,   User,mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                        mContext.getResources().getString(R.string.network_error_message), 2);
            }
        }
        else if (from.equalsIgnoreCase("FundReceiveStatementDateWise")) {
            if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.FundReceiveStatementDateWise(mContext, User, mProgressDialog, "");

            } else {
                UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                        mContext.getResources().getString(R.string.network_error_message), 2);
            }

        }
    }
    private void ViewDownline(String User) {
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
            try {
                /* UtilMethods.INSTANCE.Balancecheck(getActivity(), loader);*/
                UtilMethods.INSTANCE.SubUserList(mContext,   mProgressDialog,User,"adapter", new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        subrecyclerview.setVisibility(View.GONE);
//                            SharedPreferences myPrefs =  mContext.getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref,  mContext.MODE_PRIVATE);
//                            String userlst = myPrefs.getString(ApplicationConstant.INSTANCE.userlist, null);
//                            setSubData(userlst);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }



        } else {
            UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                    mContext.getResources().getString(R.string.network_error_message), 2);
        }


    }

    private void setSubData() {

    }

    @Override
    public int getItemCount() {
        return UsersList.size();
    }

    public void updateList(ArrayList<UserRole> list){
        UsersList = list;
        notifyDataSetChanged();
    }
}