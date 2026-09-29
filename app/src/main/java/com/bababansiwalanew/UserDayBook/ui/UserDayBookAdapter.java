package com.bababansiwalanew.UserDayBook.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.UserDayBook.dto.UserDayBookSummary;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class UserDayBookAdapter extends RecyclerView.Adapter<UserDayBookAdapter.MyViewHolder> {

    private ArrayList<UserDayBookSummary> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView operator, commision;
        public TextView totalHit, totalAmount, successHit, successAmount, failedHit, failedAmount, pendingHit, pendingAmount;


        public MyViewHolder(View view) {
            super(view);
            operator = (AppCompatTextView) view.findViewById(R.id.operator);
            commision = (AppCompatTextView) view.findViewById(R.id.commision);
            totalHit = (TextView) view.findViewById(R.id.totalHit);
            totalAmount = (TextView) view.findViewById(R.id.totalAmount);
            successHit = (TextView) view.findViewById(R.id.successHit);
            successAmount = (TextView) view.findViewById(R.id.successAmount);
            failedHit = (TextView) view.findViewById(R.id.failedHit);
            failedAmount = (TextView) view.findViewById(R.id.failedAmount);
            pendingHit = (TextView) view.findViewById(R.id.pendingHit);
            pendingAmount = (TextView) view.findViewById(R.id.pendingAmount);
        }
    }

    public UserDayBookAdapter(ArrayList<UserDayBookSummary> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public UserDayBookAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_day_book_summary, parent, false);

        return new UserDayBookAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserDayBookAdapter.MyViewHolder holder, int position) {
        final UserDayBookSummary operator = transactionsList.get(position);

        holder.operator.setText(operator.getOpraterName());
        holder.commision.setText(" " + operator.getCommission());

        holder.totalHit.setText("" + operator.getTotalHits());
        holder.totalAmount.setText("" + operator.getTotalAmount());

        holder.successHit.setText("" + operator.getSuccessHits());
        holder.successAmount.setText("" + operator.getSuccessAmount());

        holder.failedHit.setText("" + operator.getFailedHits());
        holder.failedAmount.setText("" + operator.getFailedAmount());

        holder.pendingHit.setText("" + operator.getPendingHits());
        holder.pendingAmount.setText("" + operator.getPendingAmount());


    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
