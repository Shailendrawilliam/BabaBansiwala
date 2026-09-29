package com.bababansiwalanew.UserDayBook.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookLedger;

import java.util.ArrayList;

/**
 * Created by Lalit on 16-04-2017.
 */

public class UserDayBookLedgerAdapter extends RecyclerView.Adapter<UserDayBookLedgerAdapter.MyViewHolder> {

    private ArrayList<UserDayBookLedger> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView operator, operatorLabel, commision, commisionLabel;
        public TextView totalHit, totalAmount, successHit, successAmount, failedHit, failedAmount, pendingHit, pendingAmount;
        public TextView totalHitLabel, totalAmountLabel, successHitLabel, successAmountLabel, failedHitLabel, failedAmountLabel, pendingHitLabel, pendingAmountLabel;


        public MyViewHolder(View view) {
            super(view);
            operator = (AppCompatTextView) view.findViewById(R.id.operator);
            commision = (AppCompatTextView) view.findViewById(R.id.commision);
            operatorLabel = (AppCompatTextView) view.findViewById(R.id.operatorLabel);
            commisionLabel = (AppCompatTextView) view.findViewById(R.id.commisionLabel);

            totalHit = (TextView) view.findViewById(R.id.totalHit);
            totalAmount = (TextView) view.findViewById(R.id.totalAmount);
            successHit = (TextView) view.findViewById(R.id.successHit);
            successAmount = (TextView) view.findViewById(R.id.successAmount);
            failedHit = (TextView) view.findViewById(R.id.failedHit);
            failedAmount = (TextView) view.findViewById(R.id.failedAmount);
            pendingHit = (TextView) view.findViewById(R.id.pendingHit);
            pendingAmount = (TextView) view.findViewById(R.id.pendingAmount);

            totalHitLabel = (TextView) view.findViewById(R.id.totalHitLabel);
            totalAmountLabel = (TextView) view.findViewById(R.id.totalAmountLabel);
            successHitLabel = (TextView) view.findViewById(R.id.successHitLabel);
            successAmountLabel = (TextView) view.findViewById(R.id.successAmountLabel);
            failedHitLabel = (TextView) view.findViewById(R.id.failedHitLabel);
            failedAmountLabel = (TextView) view.findViewById(R.id.failedAmountLabel);
            pendingHitLabel = (TextView) view.findViewById(R.id.pendingHitLabel);
            pendingAmountLabel = (TextView) view.findViewById(R.id.pendingAmountLabel);
        }
    }

    public UserDayBookLedgerAdapter(ArrayList<UserDayBookLedger> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public UserDayBookLedgerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_day_book_summary, parent, false);

        return new UserDayBookLedgerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserDayBookLedgerAdapter.MyViewHolder holder, int position) {
        final UserDayBookLedger operator = transactionsList.get(position);


        holder.totalHitLabel.setText("CurrectAmount");
        holder.totalAmountLabel.setText("OpeningBal");
        holder.successHitLabel.setText("Receive");
        holder.successAmountLabel.setText("Transfer");
        holder.failedHitLabel.setText("RequestedAmount");
        holder.failedAmountLabel.setText("Amount");
        holder.pendingHitLabel.setText("Commission");
        holder.pendingAmountLabel.setText("HCommission");


        holder.operator.setVisibility(View.GONE);
        holder.commision.setVisibility(View.GONE);
        holder.operatorLabel.setVisibility(View.GONE);
        holder.commisionLabel.setVisibility(View.GONE);

        if (operator.getCurrectAmount() != null)
            holder.totalHit.setText("" + operator.getCurrectAmount());
        if (operator.getOpeningBal() != null)
            holder.totalAmount.setText("" + operator.getOpeningBal());
        if (operator.getReceive() != null)
            holder.successHit.setText("" + operator.getReceive());
        if (operator.getTransfer() != null)
            holder.successAmount.setText("" + operator.getTransfer());
        if (operator.getRequestedAmount() != null)
            holder.failedHit.setText("" + operator.getRequestedAmount());
        if (operator.getAmount() != null)
            holder.failedAmount.setText("" + operator.getAmount());
        if (operator.getCommission() != null)
            holder.pendingHit.setText("" + operator.getCommission());
        if (operator.getHCommission() != null)
            holder.pendingAmount.setText("" + operator.getHCommission());


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
