package com.bababansiwalanew.FundTransferStat;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.FundRecReport.dto.FundRecObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class FundTransferAdapter extends RecyclerView.Adapter<FundTransferAdapter.MyViewHolder> {

    private ArrayList<FundRecObject> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView from, fromLabel, balance, date, tidLabel, tid;
        TextView tvTo,tvCurrent;
        TextView tvTransferAmt;

        public MyViewHolder(View view) {
            super(view);
            from = (AppCompatTextView) view.findViewById(R.id.from);
            fromLabel = (AppCompatTextView) view.findViewById(R.id.fromLabel);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            tidLabel = (AppCompatTextView) view.findViewById(R.id.tidLabel);
            tid = (AppCompatTextView) view.findViewById(R.id.tid);
            tvTo = (TextView) view.findViewById(R.id.tv_to);
            tvTransferAmt = (TextView) view.findViewById(R.id.tv_trans_amt);
            tvCurrent = (TextView) view.findViewById(R.id.tvCurrent);
        }
    }

    public FundTransferAdapter(ArrayList<FundRecObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public FundTransferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_rec_statement_adapter, parent, false);

        return new FundTransferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FundTransferAdapter.MyViewHolder holder, int position) {
        final FundRecObject operator = transactionsList.get(position);

        holder.fromLabel.setText("From : ");
        holder.from.setText(" " + operator.getFrom());
        holder.tvTo.setText(" " + operator.getTo());
        holder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmount());
        holder.tvTransferAmt.setText("- " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
        holder.tvCurrent.setText("Balance " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmount());
        holder.tvCurrent.setVisibility(View.VISIBLE);
        holder.date.setText(operator.getDateTime());

        holder.tidLabel.setText("TID : ");
        holder.tid.setText(operator.getTID());

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }
    public void updateList(ArrayList<FundRecObject> list){
        transactionsList = list;
        notifyDataSetChanged();
    }
}
