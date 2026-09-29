package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.AddOnPack;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class RechargeTypeAdapterforDTH

        extends RecyclerView.Adapter<RechargeTypeAdapterforDTH.MyViewHolder> {

    private ArrayList<AddOnPack> operatorList;
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

    public RechargeTypeAdapterforDTH(ArrayList<AddOnPack> operatorList, Context mContext,String from) {
        this.operatorList = operatorList;
        this.mContext = mContext;
        this.from = from;
    }

    @Override
    public RechargeTypeAdapterforDTH.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dth_plan_adapter, parent, false);

        return new RechargeTypeAdapterforDTH.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RechargeTypeAdapterforDTH.MyViewHolder holder, int position) {
        final AddOnPack operator = operatorList.get(position);



        if (operator.getPlanName() != null && operator.getPlanName().length() > 0)
            holder.validity.setText(operator.getPlanName());
        else
            holder.validity.setText("N/A");

        holder.amount.setText(mContext.getResources().getString(R.string.rupiya) + " " + operator.getRS().getOneMonth());
        holder.amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BrowsePlanScreen) mContext).ItemClick("" + operator.getRS().getOneMonth());
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
