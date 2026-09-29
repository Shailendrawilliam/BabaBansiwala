package com.bababansiwalanew.FundRecReport.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.FundRecReport.dto.FundRecObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 15-04-2017.
 */

public class FundRecAdapter extends RecyclerView.Adapter<FundRecAdapter.MyViewHolder> {

    private ArrayList<FundRecObject> transactionsList;
    private Context mContext;
    private String from;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView from, fromLabel, balance, date, tidLabel, tid,balanceav;


        public MyViewHolder(View view) {
            super(view);
            from = (AppCompatTextView) view.findViewById(R.id.from);
            fromLabel = (AppCompatTextView) view.findViewById(R.id.fromLabel);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            tidLabel = (AppCompatTextView) view.findViewById(R.id.tidLabel);
            tid = (AppCompatTextView) view.findViewById(R.id.tid);
            balanceav = (AppCompatTextView) view.findViewById(R.id.balanceav);
        }
    }

    public FundRecAdapter(ArrayList<FundRecObject> transactionsList, Context mContext,String from) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.from = from;
    }

    @Override
    public FundRecAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_rec_adapter_e, parent, false);

        return new FundRecAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FundRecAdapter.MyViewHolder holder, int position) {
        final FundRecObject operator = transactionsList.get(position);
if (from.equalsIgnoreCase("FundReceiveStatementDateWise")){
    holder.fromLabel.setText("");
    holder.from.setText("Name : " + operator.getName());
    holder.balance.setText("+ " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmont());
    holder.date.setText("Found Date : "+operator.getFundDate());
    holder.tidLabel.setText("Mobile No : ");
    holder.tid.setText(operator.getMobileNo());
    holder.balanceav.setText(operator.getBalanceAmount());


}else {
    holder.fromLabel.setText("From : ");
    holder.from.setText("" + operator.getFrom());
    holder.balance.setText("+ " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
    holder.date.setText("Date : "+operator.getDateTime());
    holder.balanceav.setText( "Balance : "+mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmount()!=null? operator.getBalanceAmount():"0.0");
    holder.tidLabel.setText("TID : ");
    holder.tid.setText(operator.getTID());
}


    }
    public void updateList(ArrayList<FundRecObject> list){
        transactionsList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
