package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.dto.NumberList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lalit on 25-02-2017.
 */

public class SelectPlanOptionAdapter extends RecyclerView.Adapter<SelectPlanOptionAdapter.MyViewHolder> {

    private ArrayList<NumberList> operatorList;
    String from;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView opImage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            opImage = (ImageView) view.findViewById(R.id.opImage);
        }
    }

    public SelectPlanOptionAdapter(ArrayList<NumberList> operatorList, Context mContext, String from) {
        this.operatorList = operatorList;
        this.from = from;
        this.mContext = mContext;

    }

    @Override
    public SelectPlanOptionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_option_adapter, parent, false);

        return new SelectPlanOptionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SelectPlanOptionAdapter.MyViewHolder holder, int position) {
        final NumberList operator = operatorList.get(position);

        if (from.equalsIgnoreCase("operator")) {
            holder.title.setPadding(0, 0, 0, 0);
            holder.title.setText(operator.getOperator());
            holder.opImage.setVisibility(View.VISIBLE);
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SelectPlanOption) mContext).ItemClick(operator.getOperator(), operator.getIReffOp(), from);
                }
            });

            if (operator.getOperator() != null && operator.getOperator().toString().length() > 0) {

                String imgName = operatorList.get(position).getOperator();
                String imgTemp = imgName.toLowerCase().replace(" ", "");
                String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
                String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
                String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
                String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());


                resourceId = mContext.getResources().getIdentifier(
                        imgTemp4, "drawable", mContext.getPackageName());

                Picasso.with(mContext).load(resourceId)/*.centerCrop().error(R.drawable.ic_operator_default_icon)*/.into((holder.opImage)
                );
            } else {
                holder.opImage.setImageResource(R.drawable.ic_operator_default_icon);
            }
        } else {
            holder.title.setPadding(20, 0, 0, 0);
            holder.title.setText(operator.getCircle());
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SelectPlanOption) mContext).ItemClick(operator.getCircle(), operator.getIReffCircle(), from);
                }
            });
            holder.opImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return operatorList.size();
    }

}
