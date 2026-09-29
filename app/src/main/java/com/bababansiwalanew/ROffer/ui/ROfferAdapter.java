package com.bababansiwalanew.ROffer.ui;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.ROffer.dto.ROfferObject;

import java.util.ArrayList;


/**
 * Created by Lalit on 15-04-2017.
 */

public class ROfferAdapter extends RecyclerView.Adapter<ROfferAdapter.MyViewHolder> {

    private ArrayList<ROfferObject> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Description, Amount;


        public MyViewHolder(View view) {
            super(view);
            Description = (TextView) view.findViewById(R.id.tv_description);
            Amount = (TextView) view.findViewById(R.id.tv_amount);
        }
    }

    public ROfferAdapter(ArrayList<ROfferObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public ROfferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_adapter, parent, false);

        return new ROfferAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ROfferAdapter.MyViewHolder holder, int position) {
        final ROfferObject operator = transactionsList.get(position);

        holder.Description.setText(" " + operator.getDesc());
        holder.Amount.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getRs());

        holder.Amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ROffer) mContext).ItemClick("" + operator.getRs());
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
