package com.bababansiwalanew.Dashboard.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.bababansiwalanew.BalanceCheck.dto.BalanceCheckResponse;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

class DisListReportAdapter extends/* UserListReportAdapter {
    public DisListReportAdapter(ArrayList<UserListStatus> distributorListObjects, DisMemberList disMemberList, String from) {
        super();
    }
}

public class UserListReportAdapter extends */RecyclerView.Adapter<DisListReportAdapter.MyViewHolder> {

    private ArrayList<UserListStatus> UsersList;
    private Context mContext;
    private String   from;
    int resourceId = 0;
    String fund ="1";
    ProgressDialog mProgressDialog = null;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView RetailerName;
        public AppCompatTextView MobileNumber;
        public AppCompatTextView CurrentBalance;
        public AppCompatTextView current_utility;

        public AppCompatButton fndtransButton,viewdetails;


        public MyViewHolder(View view) {
            super(view);
            mProgressDialog= new ProgressDialog(mContext);
            RetailerName = (AppCompatTextView) view.findViewById(R.id.retailer_name);
            MobileNumber = (AppCompatTextView) view.findViewById(R.id.mobile_number);
            CurrentBalance = (AppCompatTextView) view.findViewById(R.id.current_balance);
            //  current_utility = (AppCompatTextView) view.findViewById(R.id.current_utility);
            // fnddeductButton = (AppCompatButton) view.findViewById(R.id.actionButton);
            fndtransButton = (AppCompatButton) view.findViewById(R.id.fundTrasfer);
            viewdetails = (AppCompatButton) view.findViewById(R.id.viewdetails);
        }
    }

    public DisListReportAdapter(ArrayList<UserListStatus> UsersList, Context mContext,String from) {
        this.UsersList = UsersList;
        this.mContext = mContext;
        this.from = from;
    }

    @Override
    public DisListReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dislist_report_adapter, parent, false);

        return new DisListReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DisListReportAdapter.MyViewHolder holder, int position) {
        final UserListStatus operator = UsersList.get(position);
        holder.RetailerName.setText("" + operator.getOutletname());
        holder.MobileNumber.setText(" "+operator.getLoginId().toString());
        holder.CurrentBalance.setText("" + operator.getBalance_Amount());
        if (from.equalsIgnoreCase("UserSaleReport")){
            holder.viewdetails.setVisibility(View.VISIBLE);
            holder.fndtransButton.setVisibility(View.GONE);
        }
        else if (from.equalsIgnoreCase("UserSaleReportDetailDateWise")){
            holder.viewdetails.setVisibility(View.VISIBLE);
            holder.viewdetails.setText("ViewSaleReportDetail");
            holder.fndtransButton.setVisibility(View.GONE);
        }
        else if (from.equalsIgnoreCase("UserSaleReportDetailAllDateWise")){
            holder.viewdetails.setVisibility(View.VISIBLE);
            holder.viewdetails.setText("ViewSaleReportAllDetail");
            holder.fndtransButton.setVisibility(View.GONE);
        }
        else if (from.equalsIgnoreCase("FundReceiveStatementDateWise")){
            holder.viewdetails.setVisibility(View.VISIBLE);
            holder.viewdetails.setText("FundReceiveStatementDateWise");
            holder.fndtransButton.setVisibility(View.GONE);
        }
        else {
            holder.viewdetails.setVisibility(View.GONE);

        }

        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSaleReport(operator.getLoginId());

            }
        });
        holder.fndtransButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LayoutInflater[] inflater = {(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)};
                View view = inflater[0].inflate(R.layout.fundtrans_layout, null);

                final EditText amount = (EditText) view.findViewById(R.id.amount);
                final RadioButton prepaid = (RadioButton) view.findViewById(R.id.prepaid);
                final RadioButton utility = (RadioButton) view.findViewById(R.id.utility);
                final AppCompatTextView ret_name = (AppCompatTextView) view.findViewById(R.id.ret_name);
                final AppCompatTextView message = (AppCompatTextView) view.findViewById(R.id.message);
                final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioview);
                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);
                final Dialog dialog = new Dialog(mContext);

                dialog.setCancelable(false);
                dialog.setContentView(view);
                SharedPreferences myPreferences = mContext.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, mContext.MODE_PRIVATE);
                String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, null);

                BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(balanceResponse, BalanceCheckResponse.class);
                String utilityWallet = balanceCheckResponse.getUtilityWallet();
                if (utilityWallet != null && !utilityWallet.equalsIgnoreCase("")) {
                    utility.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);


                } else {
                    utility.setVisibility(View.GONE);
                    radioGroup.setVisibility(View.GONE);
                    message.setVisibility(View.GONE);
                }
                prepaid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prepaid.setChecked(true);
                        utility.setChecked(false);
                        fund = "1";
                    }
                });

                utility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prepaid.setChecked(false);
                        utility.setChecked(true);
                        fund = "2";
                    }
                });

                ret_name.setText(" Retailer Name: " + operator.getOutletname() + " and Mobile No: " + operator.getLoginId());

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (amount.getText() != null && amount.getText().length() > 0) {

                            if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
                                // CustomLoader loader = new CustomLoader(mContext,android.R.style.Theme_Translucent_NoTitleBar);
//                                loader.show();
//                                loader.setCancelable(false);
//                                loader.setCanceledOnTouchOutside(false);

                                mProgressDialog.setMessage("Loading...");
                                mProgressDialog.show();

                                UtilMethods.INSTANCE.FundTransfer(mContext, operator.getLoginId(), amount.getText().toString().trim(), fund, mProgressDialog, okButton);
                                dialog.dismiss();
                            } else {
                                UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                                        mContext.getResources().getString(R.string.network_error_message), 0);
                            }
                        } else {
                            amount.setError("Please enter a valid amount number !!");
                            amount.requestFocus();
                        }
                    }
                });
                dialog.show();

            }
        });

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

    @Override
    public int getItemCount() {
        return UsersList.size();
    }

    public void updateList(ArrayList<UserListStatus> list){
        UsersList = list;
        notifyDataSetChanged();
    }
}
