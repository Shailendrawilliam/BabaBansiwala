package com.bababansiwalanew.CommissionSlab.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bababansiwalanew.CommissionSlab.dto.CommissionSlabObject;
import com.bababansiwalanew.R;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class CommissionAdapter extends RecyclerView.Adapter<CommissionAdapter.MyViewHolder> {

    private ArrayList<CommissionSlabObject> transactionsList;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView operator, recCommission;

        public MyViewHolder(View view) {
            super(view);
            operator = (AppCompatTextView) view.findViewById(R.id.operator);
            recCommission = (AppCompatTextView) view.findViewById(R.id.recCommission);
        }
    }

    public CommissionAdapter(ArrayList<CommissionSlabObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public CommissionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commision_adapter, parent, false);

        return new CommissionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CommissionAdapter.MyViewHolder holder, int position) {
        final CommissionSlabObject operator = transactionsList.get(position);
        holder.operator.setText("" + operator.getOpratedName()+ " ");
        holder.recCommission.setText(" " + operator.getCommission()+ " %");
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}