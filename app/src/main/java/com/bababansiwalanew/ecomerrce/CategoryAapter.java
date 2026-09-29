package com.bababansiwalanew.ecomerrce;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.Activities.SubCategory;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

 
public class CategoryAapter extends RecyclerView.Adapter<CategoryAapter.MyViewHolder> {
    Context context;
     String from ="";
    UtilMethods.ApiCallBack apiCallBack;
    List<ListProductCategory> listProductCategory = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle;
         ImageView ivimg;
        public MyViewHolder(View view) {
            super(view);
            tvtitle = (TextView) view.findViewById(R.id.tvtitle);
            ivimg =view.findViewById(R.id.ivimg);
        }
    }
    public void updateList(ArrayList<ListProductCategory> list){
        listProductCategory = list;
        notifyDataSetChanged();
    }
    public CategoryAapter(Context context, List<ListProductCategory> listProductCategory,String from,
                          UtilMethods.ApiCallBack apiCallBack) {
        this.listProductCategory= listProductCategory;
        this.context= context;
        this.from= from;
        this.apiCallBack= apiCallBack;
    }
    @Override
    public CategoryAapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_adapter, parent, false);
        return new CategoryAapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoryAapter.MyViewHolder holder, int position) {
        final ListProductCategory operator = listProductCategory.get(position);
        holder.tvtitle.setText(operator.getCategoryName());

        Picasso.with(context)
                .load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getProductImage())
                .into(holder.ivimg);
        Log.v("data","binding"+ position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(
                        new Intent(context, SubCategory.class)
                                .putExtra("CatId",""+operator.getCategoryId())
                                .putExtra("CatName",""+operator.getCategoryName())
                );


            }
        });
    }

    @Override
    public int getItemCount() {
         if ( from.equalsIgnoreCase("fragment")){
             return Math.min(listProductCategory.size(), 6);
         }
          else {
             return listProductCategory.size();
         }


    }

}
