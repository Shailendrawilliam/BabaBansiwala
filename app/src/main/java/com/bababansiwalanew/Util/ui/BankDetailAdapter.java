package com.bababansiwalanew.Util.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.dto.BankDetail;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class BankDetailAdapter extends RecyclerView.Adapter<BankDetailAdapter.MyViewHolder> {

    private ArrayList<BankDetail> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView title;


        public MyViewHolder(View view) {
            super(view);
            title = (AppCompatTextView) view.findViewById(R.id.title);
        }
    }

    public BankDetailAdapter(ArrayList<BankDetail> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public BankDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_detail_adapter, parent, false);

        return new BankDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BankDetailAdapter.MyViewHolder holder, int position) {
        final BankDetail operator = transactionsList.get(position);

        holder.title.setText("" + operator.getBankName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BankDetailList) mContext).AdapterClicked("" + operator.getBankName() + "," + operator.getAccountNo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
