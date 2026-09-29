package com.bababansiwalanew.Activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    UtilMethods.ApiCallBack apiCallBack;
    List<ListProductCategory> listProductCategory = new ArrayList<>();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle,SalePrice,Mrp,tvDiscount;
        public TextView quantity,plus,minus;
        ImageView ivImg;
         LinearLayout ll_discount;
        public MyViewHolder(View view) {
            super(view);
            tvtitle = (TextView) view.findViewById(R.id.tvtitle);
            SalePrice = (TextView) view.findViewById(R.id.SalePrice);
            Mrp = (TextView) view.findViewById(R.id.Mrp);
            tvDiscount = (TextView) view.findViewById(R.id.tvDiscount);
            quantity = (TextView) view.findViewById(R.id.quantity);
            plus = (TextView) view.findViewById(R.id.plus);
            minus = (TextView) view.findViewById(R.id.minus);
            ivImg =  view.findViewById(R.id.ivImg);
            ll_discount =  view.findViewById(R.id.ll_discount);
        }
    }
    public void updateList(ArrayList<ListProductCategory> list){
        listProductCategory = list;
        notifyDataSetChanged();
    }
    public ProductAdapter(Context context, List<ListProductCategory> listProductCategory,
                             UtilMethods.ApiCallBack apiCallBack) {
        this.listProductCategory= listProductCategory;
        this.context= context;
        this.apiCallBack= apiCallBack;
    }
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_adapter, parent, false);
        return new ProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.MyViewHolder holder, int position) {
        final ListProductCategory operator = listProductCategory.get(position);
        holder.tvtitle.setText(operator.getProductName());
        holder.SalePrice.setText("\u20b9 "+operator.getPrice());
        holder.Mrp.setText("\u20b9 "+operator.getFinalPrice());
        holder.tvDiscount.setText("\u20b9"+operator.getDiscount()+"\n off");
         if (operator.getDiscount()!=null && !operator.getDiscount().equalsIgnoreCase("0.00")){
             holder.ll_discount.setVisibility(View.VISIBLE);
         }else{
             holder.ll_discount.setVisibility(View.GONE);
         }
        Picasso.with(context)
                .load(ApplicationConstant.INSTANCE.baseUrl+operator.getProductImage())
                .into(holder.ivImg);
        Log.v("data","binding"+ position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(
//                        new Intent(context, ProductDetails.class)
//                                .putExtra("Id",""+operator.getProductId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductCategory.size();
    }

}
