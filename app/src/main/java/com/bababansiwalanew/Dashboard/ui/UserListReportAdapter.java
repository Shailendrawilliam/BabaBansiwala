package com.bababansiwalanew.Dashboard.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
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

import static android.content.Context.MODE_PRIVATE;

public class UserListReportAdapter extends RecyclerView.Adapter<UserListReportAdapter.MyViewHolder> {

    private ArrayList<UserListStatus> UsersList;
    private final Context mContext;
    private final String   from;
    private final String   role;
    private RecyclerView   subrecyclerview;
    int resourceId = 0;
    String fund ="1";
    ProgressDialog mProgressDialog = null;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView RetailerName;
        public AppCompatTextView MobileNumber;
        public AppCompatTextView CurrentBalance;
        public AppCompatTextView current_utility;

        public AppCompatButton    fndtransButton,viewdetails,downline;


        public MyViewHolder(View view) {
            super(view);
            mProgressDialog= new ProgressDialog(mContext);
            RetailerName = view.findViewById(R.id.retailer_name);
            MobileNumber = view.findViewById(R.id.mobile_number);
            CurrentBalance = view.findViewById(R.id.current_balance);
          //  current_utility = (AppCompatTextView) view.findViewById(R.id.current_utility);
           // fnddeductButton = (AppCompatButton) view.findViewById(R.id.actionButton);
            fndtransButton = view.findViewById(R.id.fundTrasfer);
            viewdetails = view.findViewById(R.id.viewdetails);
            downline = view.findViewById(R.id.downline);
            subrecyclerview = view.findViewById(R.id.subrecyclerview);
        }
    }

    public UserListReportAdapter(ArrayList<UserListStatus> UsersList, Context mContext,String from,String role) {
        this.UsersList = UsersList;
        this.mContext = mContext;
        this.from = from;
        this.role = role;
    }

    @Override
    public UserListReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_report_adapter, parent, false);

        return new UserListReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserListReportAdapter.MyViewHolder holder, int position) {
        final UserListStatus operator = UsersList.get(position);
        holder.RetailerName.setText(operator.getOutletname());
        holder.MobileNumber.setText(" "+ operator.getLoginId());
        holder.CurrentBalance.setText(operator.getBalance_Amount());

        if (role.equalsIgnoreCase("Retailer")){
            holder.downline.setVisibility(View.GONE);
        }else {
            holder.downline.setVisibility(View.GONE);
        }

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
        holder.downline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDownline( operator.getLoginId());

            }
        });
            holder.fndtransButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final LayoutInflater[] inflater = {(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)};
                    View view = inflater[0].inflate(R.layout.fundtrans_layout, null);

                    final EditText amount = view.findViewById(R.id.amount);
                    final RadioButton prepaid = view.findViewById(R.id.prepaid);
                    final RadioButton utility = view.findViewById(R.id.utility);
                    final AppCompatTextView ret_name = view.findViewById(R.id.ret_name);
                    final AppCompatTextView message = view.findViewById(R.id.message);
                    final RadioGroup radioGroup = view.findViewById(R.id.radioview);
                    final AppCompatButton okButton = view.findViewById(R.id.okButton);
                    final AppCompatButton cancelButton = view.findViewById(R.id.cancelButton);
                    final Dialog dialog = new Dialog(mContext);

                    dialog.setCancelable(false);
                    dialog.setContentView(view);
                    SharedPreferences myPreferences = mContext.getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
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
                                okButton.setEnabled(false);
                                okButton.setBackgroundColor(mContext.getResources().getColor(R.color.grey_600));
                                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
                                    // CustomLoader loader = new CustomLoader(mContext,android.R.style.Theme_Translucent_NoTitleBar);
//                                loader.show();
//                                loader.setCancelable(false);
//                                loader.setCanceledOnTouchOutside(false);

                                    mProgressDialog.setMessage("Loading...");
                                    mProgressDialog.show();
                                    new Handler().postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            // This method will be executed once the timer is over
                                            okButton.setEnabled(true);
                                            okButton.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                                        }
                                    },7000);// set time as per your requirement

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

    public void updateList(ArrayList<UserListStatus> list){
        UsersList = list;
        notifyDataSetChanged();
    }
}
