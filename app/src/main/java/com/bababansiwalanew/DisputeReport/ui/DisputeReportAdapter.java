package com.bababansiwalanew.DisputeReport.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.DisputeReport.dto.Dispute;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class DisputeReportAdapter extends RecyclerView.Adapter<DisputeReportAdapter.MyViewHolder> {

    private ArrayList<Dispute> transactionsList;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView txn, balance, number, date, status, refundRequest, remark;

        public MyViewHolder(View view) {
            super(view);
            txn = (AppCompatTextView) view.findViewById(R.id.txn);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            number = (AppCompatTextView) view.findViewById(R.id.number);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            refundRequest = (AppCompatTextView) view.findViewById(R.id.refundRequest);
            remark = (AppCompatTextView) view.findViewById(R.id.remark);

        }
    }

    public DisputeReportAdapter(ArrayList<Dispute> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public DisputeReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dispute_report_adapter, parent, false);

        return new DisputeReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DisputeReportAdapter.MyViewHolder holder, int position) {
        final Dispute operator = transactionsList.get(position);
        holder.txn.setText("" + operator.getTID());
        holder.balance.setText("" + operator.getAmount());

        holder.number.setText("" + operator.getRechargeNo());
        holder.date.setText("" + operator.getRDate());
        holder.status.setText("" + operator.getStatus());
        holder.refundRequest.setText("" + operator.getRefundRequest());
        holder.remark.setText("" + operator.getRemark());

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}