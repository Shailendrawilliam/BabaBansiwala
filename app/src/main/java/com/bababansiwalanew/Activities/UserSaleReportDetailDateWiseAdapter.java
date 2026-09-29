package com.bababansiwalanew.Activities;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.Login.dto.SaleReportObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

class UserSaleReportDetailDateWiseAdapter  extends RecyclerView.Adapter<UserSaleReportDetailDateWiseAdapter.MyViewHolder> {

private ArrayList<SaleReportObject> transactionsList;
private Context mContext;
        int resourceId = 0;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public AppCompatTextView txn, txnLabel, balance, date, statusLabel, status;


    public MyViewHolder(View view) {
        super(view);
        txn = (AppCompatTextView) view.findViewById(R.id.txn);
        txnLabel = (AppCompatTextView) view.findViewById(R.id.txnLabel);
        balance = (AppCompatTextView) view.findViewById(R.id.balance);
        date = (AppCompatTextView) view.findViewById(R.id.date);
        statusLabel = (AppCompatTextView) view.findViewById(R.id.statusLabel);
        status = (AppCompatTextView) view.findViewById(R.id.status);
    }
}

    public UserSaleReportDetailDateWiseAdapter(ArrayList<SaleReportObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public UserSaleReportDetailDateWiseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usersalereportdetaildatewiseadapter, parent, false);

        return new UserSaleReportDetailDateWiseAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserSaleReportDetailDateWiseAdapter.MyViewHolder holder, int position) {
        final SaleReportObject operator = transactionsList.get(position);

        holder.txnLabel.setText("Name : "+operator.getName() );
        holder.txn.setText("Mobile : " +   operator.getMobileNo());
        holder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmont());
        holder.date.setText("Sale Date : "+operator.getSaleDate());
        holder.statusLabel.setVisibility(View.GONE);
        holder.status.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }}
