package com.bababansiwalanew.LedgerReport.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.LedgerReport.dto.LedgerObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 10-04-2017.
 */

public class LedgerReportAdapter extends RecyclerView.Adapter<LedgerReportAdapter.MyViewHolder> {

    private ArrayList<LedgerObject> transactionsList;
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

    public LedgerReportAdapter(ArrayList<LedgerObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public LedgerReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ledger_report_adapter, parent, false);

        return new LedgerReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LedgerReportAdapter.MyViewHolder holder, int position) {
        final LedgerObject operator = transactionsList.get(position);

        holder.txnLabel.setText(operator.getStatus() + " : ");
        if(operator.getStatus().equalsIgnoreCase("Credit")){
            holder.txn.setText("+ " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
            holder.txn.setTextColor(mContext.getResources().getColor(R.color.green));}
        else {
            holder.txn.setText("- " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
            holder.txn.setTextColor(mContext.getResources().getColor(R.color.red));  }

        holder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmount());
        holder.date.setText("Date : "+operator.getCreatedDate());

        holder.statusLabel.setText("Description : ");
        holder.status.setText(operator.getRemark());

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
