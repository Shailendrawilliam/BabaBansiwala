package com.bababansiwalanew.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.shopping.CartListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {
    ArrayList<ListProductCategory> cart_list = new ArrayList<>();
    ShimmerRecyclerView shimmerRecycler;
    ProductAdapter adapter;
TextView tvTitle,tvCartCount;
 LinearLayout llNodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        tvTitle = findViewById(R.id.tvTitle);
        llNodata = findViewById(R.id.llNodata);
        tvCartCount = findViewById(R.id.tvCartCount);
        tvTitle.setText(""+getIntent().getStringExtra("SubCatName"));
        findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
            }
        }); findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProductList.this, CartListActivity.class));

            }
        });
        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.rvPList);
        shimmerRecycler.showShimmerAdapter();

        HitCart();
        UtilMethods.INSTANCE.GetProductList(this,getIntent().getStringExtra("catID"),
                getIntent().getStringExtra("SubCatId"),
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListEcommerceProduct().size());
                        if (response.getListEcommerceProduct().size() > 0) {
                            Log.v("data", " size recived " + response.getListEcommerceProduct().size());
                            adapter = new ProductAdapter(ProductList.this, response.getListEcommerceProduct(), new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {

                                }
                            }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2
                            );
                            shimmerRecycler.setLayoutManager(mLayoutManager);
                            shimmerRecycler.setItemAnimator(new DefaultItemAnimator());
                            shimmerRecycler.setAdapter(adapter);

                            llNodata.setVisibility(View.GONE);
                            shimmerRecycler.setVisibility(View.VISIBLE);

                        }else {
                            llNodata.setVisibility(View.VISIBLE);
                            shimmerRecycler.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        llNodata.setVisibility(View.VISIBLE);
                        shimmerRecycler.setVisibility(View.GONE);
                        Log.v("data", " size recived " + "on error" + errorMsg);
                    }
                });

    }

        private void HitCart() {
            UtilMethods.INSTANCE.GetCartList(ProductList.this,
                    new UtilMethods.ApiCallBackTwoMethod() {
                        @Override
                        public void onSucess(Object object) {
                            CatgoryResponse response = (CatgoryResponse) object;
                            Log.v("GetCartList", " on response " + response.getListEcommerceProduct().size());
                            tvCartCount.setText("" + response.getListEcommerceProduct().size() + "");
                            cart_list.clear();
                            cart_list.addAll(response.getListEcommerceProduct());
                        }

                        @Override
                        public void onError(String errorMsg) {
                        }
                    });
        }



    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
        Context context;
        UtilMethods.ApiCallBack apiCallBack;
        List<ListProductCategory> listProductCategory = new ArrayList<>();
        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvtitle,SalePrice,Mrp,tvDiscount;
            public TextView quantity ;
            public ImageView  plus,minus;
            ImageView ivImg;
            LinearLayout ll_discount;
            LinearLayout llUpdate;
            LinearLayout llBuy;
            public MyViewHolder(View view) {
                super(view);
                tvtitle = (TextView) view.findViewById(R.id.tvtitle);
                SalePrice = (TextView) view.findViewById(R.id.SalePrice);
                Mrp = (TextView) view.findViewById(R.id.Mrp);
                tvDiscount = (TextView) view.findViewById(R.id.tvDiscount);
                quantity = (TextView) view.findViewById(R.id.quantity);
                plus =  view.findViewById(R.id.plus);
                minus =  view.findViewById(R.id.minus);
                ivImg =  view.findViewById(R.id.ivImg);
                ll_discount =  view.findViewById(R.id.ll_discount);
                llUpdate =  view.findViewById(R.id.llUpdate);
                llBuy =  view.findViewById(R.id.llBuy);
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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_adapter, parent, false);
            return new  MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            final ListProductCategory operator = listProductCategory.get(position);
            holder.tvtitle.setText(operator.getProductName());
            holder.SalePrice.setText("\u20b9 "+operator.getPrice());
            holder.Mrp.setText("\u20b9 "+operator.getFinalPrice());
            holder.tvDiscount.setText("\u20b9"+operator.getDiscount().replace(".00","")+"\n off");
            if (operator.getDiscount()!=null && !operator.getDiscount().equalsIgnoreCase("0.00")){
                holder.ll_discount.setVisibility(View.VISIBLE);
            }else{
                holder.ll_discount.setVisibility(View.GONE);
            }
            holder.Mrp.setPaintFlags( holder.Mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Picasso.with(context)
                    .load(ApplicationConstant.INSTANCE.baseUrl+operator.getProductImage())
                    .into(holder.ivImg);
            Log.v("data","binding"+ position);

            for (int i = 0; i < cart_list.size(); i++) {

                if ( operator.getProductId().equals(cart_list.get(i).getProductId())){
                    holder.llUpdate.setVisibility(View.VISIBLE);
                    holder.llBuy.setVisibility(View.GONE);
                    holder.quantity.setText(""+cart_list.get(i).getProductCount());
                    break;
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                context.startActivity(
                        new Intent(context, ProductDetails.class)
                                .putExtra("Id",""+operator.getProductId()));
                }
            });
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qnty = Integer.parseInt(holder.quantity.getText().toString());
                    UtilMethods.INSTANCE.AddToCart(ProductList.this,
                            ""+operator.getProductId(), "" + (qnty + 1),
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "")
                                    .replace("\n off", "")),
                            holder.Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    //Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    holder.llUpdate.setVisibility(View.VISIBLE);
                                    holder.llBuy.setVisibility(View.GONE);
                                    holder.quantity.setText("" + (qnty + 1));
                                    HitCart();
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    //Toast.makeText(ProductDetails.this, "Unable to update quantity", Toast.LENGTH_LONG).show();
                                    Log.v("data", " size received " + "on error" + errorMsg);
                                }
                            });
                }
            });
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qnty = Integer.parseInt(holder.quantity.getText().toString());
                    if (qnty > 1) {

                        UtilMethods.INSTANCE.AddToCart(ProductList.this,
                                ""+operator.getProductId(), "" + (qnty - 1),
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "")
                                        .replace("\n off", "")),
                                holder.Mrp.getText().toString().replace("₹", "")
                                , new UtilMethods.ApiCallBackTwoMethod() {
                                    @Override
                                    public void onSucess(Object object) {
                                        // Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                        holder.llUpdate.setVisibility(View.VISIBLE);
                                        holder.llBuy.setVisibility(View.GONE);
                                        holder.quantity.setText("" + (qnty - 1));
                                        HitCart();
                                    }

                                    @Override
                                    public void onError(String errorMsg) {
                                        ///  Toast.makeText(ProductDetails.this, "Unable to update quantity", Toast.LENGTH_LONG).show();
                                        Log.v("data", " size received " + "on error" + errorMsg);
                                    }
                                });
                    } else {
                        UtilMethods.INSTANCE.AddToCart(ProductList.this,
                                ""+operator.getProductId(), "" + 0,
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\n off", "")),
                                holder.Mrp.getText().toString().replace("₹", "")
                                , new UtilMethods.ApiCallBackTwoMethod() {
                                    @Override
                                    public void onSucess(Object object) {
                                        //  Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                        holder.llUpdate.setVisibility(View.GONE);
                                        holder.llBuy.setVisibility(View.VISIBLE);
                                        HitCart();
                                    }

                                    @Override
                                    public void onError(String errorMsg) {
                                        //  Toast.makeText(ProductDetails.this, "Unable to update quantity", Toast.LENGTH_LONG).show();
                                        Log.v("data", " size received " + "on error" + errorMsg);
                                    }
                                });
                    }
                }
            });
            holder.llBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilMethods.INSTANCE.AddToCart(ProductList.this,
                            ""+operator.getProductId(),
                            "1",
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\n off", "")),
                            holder.Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    holder.llUpdate.setVisibility(View.VISIBLE);
                                    holder.llBuy.setVisibility(View.GONE);
                                    HitCart();
                                }

                                @Override
                                public void onError(String errorMsg) {
//                                llUpdate.setVisibility(View.VISIBLE);
//                                llBuy.setVisibility(View.GONE);
                                    Log.v("data", " size received " + "on error" + errorMsg);
                                }
                            });
                }
            });
        }

        @Override
        public int getItemCount() {
            return listProductCategory.size();
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        HitCart();
    }
}