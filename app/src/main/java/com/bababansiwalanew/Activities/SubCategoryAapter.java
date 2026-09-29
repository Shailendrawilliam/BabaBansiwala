package com.bababansiwalanew.Activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SubCategoryAapter extends RecyclerView.Adapter<SubCategoryAapter.MyViewHolder> {
    Context context;
     String catID="";
    UtilMethods.ApiCallBack apiCallBack;
    List<ListProductCategory> listProductCategory = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle; ImageView ivimg;
        public MyViewHolder(View view) {
            super(view);
            tvtitle = (TextView) view.findViewById(R.id.tvtitle);
            ivimg =  view.findViewById(R.id.ivimg);
        }
    }
    public void updateList(ArrayList<ListProductCategory> list){
        listProductCategory = list;
        notifyDataSetChanged();
    }
    public SubCategoryAapter(Context context,String catID, List<ListProductCategory> listProductCategory,
                          UtilMethods.ApiCallBack apiCallBack) {
        this.listProductCategory= listProductCategory;
        this.context= context;
        this.catID= catID;
        this.apiCallBack= apiCallBack;
    }
    @Override
    public SubCategoryAapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_category_adapter, parent, false);
        return new SubCategoryAapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SubCategoryAapter.MyViewHolder holder, int position) {
        final ListProductCategory operator = listProductCategory.get(position);
        holder.tvtitle.setText(operator.getSubCategoryName());
        Log.v("data","binding"+ position);
        Picasso.with(context)
                .load(ApplicationConstant.INSTANCE.baseIconUrl + operator.getProductImage())
                .into(holder.ivimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(
                        new Intent(context, ProductList.class)
                                .putExtra("SubCatId",""+operator.getSubCategoryId())
                                .putExtra("catID",""+catID)
                                .putExtra("SubCatName",""+operator.getSubCategoryName())
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductCategory.size();
    }

}
