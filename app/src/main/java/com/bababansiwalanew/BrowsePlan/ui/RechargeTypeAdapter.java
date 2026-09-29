package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ThreeGFourG;

import java.util.ArrayList;

/**
 * Created by Lalit on 24-02-2017.
 */

public class RechargeTypeAdapter extends RecyclerView.Adapter<RechargeTypeAdapter.MyViewHolder> {

    private ArrayList<ThreeGFourG> operatorList;
    private Context mContext;
    private String from;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView talktime;
        public TextView validity;
        public AppCompatButton amount;
        public TextView description;

        public MyViewHolder(View view) {
            super(view);
            talktime = (TextView) view.findViewById(R.id.talktime);
            validity = (TextView) view.findViewById(R.id.validity);
            amount = (AppCompatButton) view.findViewById(R.id.amount);
            description = (TextView) view.findViewById(R.id.description);

        }
    }

    public RechargeTypeAdapter(ArrayList<ThreeGFourG> operatorList, Context mContext ) {
        this.operatorList = operatorList;
        this.mContext = mContext;

    }

    @Override
    public RechargeTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recharge_plan_adapter, parent, false);

        return new RechargeTypeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RechargeTypeAdapter.MyViewHolder holder, int position) {
        final ThreeGFourG operator = operatorList.get(position);



        if (operator.getValidity() != null && operator.getValidity().length() > 0)
            holder.validity.setText(operator.getValidity());
        else
            holder.validity.setText("N/A");

        holder.amount.setText(mContext.getResources().getString(R.string.rupiya) + " " + operator.getRS());
        holder.amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrowsePlanScreen) mContext).ItemClick("" + operator.getRS());
            }
        });
        if (operator.getDesc() != null && operator.getDesc().length() > 0)
            holder.description.setText(operator.getDesc());
        else
            holder.description.setText("N/A");

    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

}