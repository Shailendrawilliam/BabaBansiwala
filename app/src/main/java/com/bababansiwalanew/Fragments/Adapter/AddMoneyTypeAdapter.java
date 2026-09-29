package com.bababansiwalanew.Fragments.Adapter;



import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

 import com.bababansiwalanew.AddMoney.AddMoneyActivity;


import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.Operator;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Locale;


public class AddMoneyTypeAdapter extends RecyclerView.Adapter<AddMoneyTypeAdapter.MyViewHolder> {


    private ArrayList<Operator> operatorList;
    private ArrayList<Operator> NoperatorList;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, comm;
        public ImageView opImage;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            comm = (TextView) view.findViewById(R.id.comm);
            opImage = (ImageView) view.findViewById(R.id.opImage);


        }
    }

    public AddMoneyTypeAdapter(ArrayList<Operator> operatorList, Context mContext) {
        this.operatorList = operatorList;
        this.mContext = mContext;
    }

    @Override
    public AddMoneyTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_money_type_adapter, parent, false);

        return new AddMoneyTypeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AddMoneyTypeAdapter.MyViewHolder holder, int position) {
        final Operator operator = operatorList.get(position);
        holder.title.setText(operator.getOPNAME());
        holder.comm.setText("Charges : " + UtilMethods.INSTANCE.formatedAmount(operator.getCharge() + "") + (operator.isChargeAmtType() ? " \u20B9" : " %"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof AddMoneyActivity) {
                    ((AddMoneyActivity) mContext).paymentTypeClick(operator);
                }
            }
        });

        Picasso.with(mContext)
                .load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getOPIMG())
                .into(holder.opImage);

    }

    @Override
    public int getItemCount() {
        if(operatorList.size()>0)
        {NoperatorList=operatorList;}
        return NoperatorList.size();
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        // operatorList.clear();
        if (charText.length() == 0) {
            operatorList.addAll(operatorList);
        } else {
            for (Operator op : operatorList) {
                if (op.getOPNAME().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    operatorList.add(op);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filter(ArrayList<Operator> newList) {
        operatorList = new ArrayList<>();
        operatorList.addAll(newList);
        notifyDataSetChanged();
    }

}



   /* int resourceId = 0;
    private ArrayList<Operator> operatorList;
    private Context mContext;
    RequestOptions requestOptions;
    public AddMoneyTypeAdapter(ArrayList<Operator> operatorList, Context mContext) {
        this.operatorList = operatorList;
        this.mContext = mContext;
        requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.error(R.mipmap.ic_launcher);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_money_type_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Operator operator = operatorList.get(position);
        holder.title.setText(operator.getOPNAME());
      //  holder.comm.setText("Charges : " + UtilMethods.INSTANCE.formatedAmount(operator.getCharge() + "") + (operator.isChargeAmtType() ? " \u20B9" : " %"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof AddMoneyActivity) {
                    ((AddMoneyActivity) mContext).paymentTypeClick(operator);
                }
            }
        });


        Glide.with(mContext)
                .load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getOPIMG())
                .apply(requestOptions)
                .into(holder.opImage);

    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        // operatorList.clear();
        if (charText.length() == 0) {
            operatorList.addAll(operatorList);
        } else {
            for (Operator op : operatorList) {
                if (op.getOPNAME().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    operatorList.add(op);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filter(ArrayList<Operator> newList) {
        operatorList = new ArrayList<>();
        operatorList.addAll(newList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, comm;
        public ImageView opImage;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            comm = (TextView) view.findViewById(R.id.comm);
            opImage = (ImageView) view.findViewById(R.id.opImage);


        }
    }

}*/
