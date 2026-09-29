package com.bababansiwalanew.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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


public class ProductDetails extends AppCompatActivity {

    public TextView tvtitle,tvCountPrice, ivCart, SalePrice, Mrp, tvDiscount;
    public ImageView plus, minus,ivBack, ivImg;
    public TextView quantity;
    ShimmerRecyclerView rvProduct;
    ProductAdapter productAdapter;
    LinearLayout llBuy;
    LinearLayout llUpdate;
    TextView tvCartCount;
    TextView tvCount;
    ArrayList<ListProductCategory> cart_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        tvtitle = findViewById(R.id.tvtitle);
        tvCountPrice = findViewById(R.id.tvCountPrice);
        ivBack = findViewById(R.id.ivBack);
        SalePrice = findViewById(R.id.SalePrice);
        Mrp = findViewById(R.id.Mrp);
        tvDiscount = findViewById(R.id.tvDiscount);
        quantity = findViewById(R.id.quantity);
        llBuy = findViewById(R.id.llBuy);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        llUpdate = findViewById(R.id.llUpdate);
        ivImg = findViewById(R.id.ivImg);
        tvCartCount = findViewById(R.id.tvCartCount);
        tvCount = findViewById(R.id.tvCount);

        findViewById(R.id.gotoCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity( new Intent( ProductDetails.this, CartListActivity.class));
            }
        });
        findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProductDetails.this, CartListActivity.class));

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnty = Integer.parseInt(quantity.getText().toString());
                UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                        getIntent().getStringExtra("Id"), "" + (qnty + 1), SalePrice.getText().toString().replace("₹", "")
                        , (tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                        Mrp.getText().toString().replace("₹", "")
                        , new UtilMethods.ApiCallBackTwoMethod() {
                            @Override
                            public void onSucess(Object object) {
                                //Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                llUpdate.setVisibility(View.VISIBLE);
                                llBuy.setVisibility(View.GONE);
                                quantity.setText("" + (qnty + 1));
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
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnty = Integer.parseInt(quantity.getText().toString());
                if (qnty > 1) {

                    UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                            getIntent().getStringExtra("Id"), "" + (qnty - 1), SalePrice.getText().toString().replace("₹", "")
                            , (tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                            Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    // Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    llUpdate.setVisibility(View.VISIBLE);
                                    llBuy.setVisibility(View.GONE);
                                    quantity.setText("" + (qnty - 1));
                                    HitCart();
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    ///  Toast.makeText(ProductDetails.this, "Unable to update quantity", Toast.LENGTH_LONG).show();
                                    Log.v("data", " size received " + "on error" + errorMsg);
                                }
                            });
                } else {
                    UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                            getIntent().getStringExtra("Id"), "" + 0, SalePrice.getText().toString().replace("₹", "")
                            , (tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                            Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    //  Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    llUpdate.setVisibility(View.GONE);
                                    llBuy.setVisibility(View.VISIBLE);
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
        llBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                        getIntent().getStringExtra("Id"),
                        "1",
                        SalePrice.getText().toString().replace("₹", "")
                        , (tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                        Mrp.getText().toString().replace("₹", "")
                        , new UtilMethods.ApiCallBackTwoMethod() {
                            @Override
                            public void onSucess(Object object) {
                                Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                llUpdate.setVisibility(View.VISIBLE);
                                llBuy.setVisibility(View.GONE);
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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        UtilMethods.INSTANCE.GetEcommerceProductDetail(this,
                getIntent().getStringExtra("Id"),
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListEcommerceProduct().size());
                        if (response.getListEcommerceProduct().size() > 0) {
                            Log.v("data", " size received " + response.getListEcommerceProduct().size());
                            Picasso.with(ProductDetails.this)
                                    .load(ApplicationConstant.INSTANCE.baseUrl + response.getListEcommerceProduct().get(0).getProductImage())
                                    .into(ivImg);
                            tvtitle.setText(response.getListEcommerceProduct().get(0).getProductName());
                            SalePrice.setText("\u20b9 " + response.getListEcommerceProduct().get(0).getPrice());
                            Mrp.setText("\u20b9 " + response.getListEcommerceProduct().get(0).getFinalPrice());
                            tvDiscount.setText("\u20b9" + response.getListEcommerceProduct().get(0).getDiscount().replace(".00", "") + "\noff");
                            Mrp.setPaintFlags(Mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.v("data", " size recived " + "on error" + errorMsg);
                    }
                });
        setHomeProduct();
        HitCart();

    }

    private void HitCart() {
        UtilMethods.INSTANCE.GetCartList(this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("GetCartList", " on response " + response.getListEcommerceProduct().size());
                        if (response.getListEcommerceProduct().size() > 0)
                            tvCartCount.setText("" + response.getListEcommerceProduct().size());
                        tvCount.setText("" + response.getListEcommerceProduct().size()+" Products");
                        cart_list.clear();
                        ;
                        cart_list.addAll(response.getListEcommerceProduct());
                        for (int i = 0; i < response.getListEcommerceProduct().size(); i++) {
                            if (getIntent().getStringExtra("Id").
                                    equalsIgnoreCase("" + response.getListEcommerceProduct().get(i).getProductId())) {
                                llUpdate.setVisibility(View.VISIBLE);
                                quantity.setText("" + response.getListEcommerceProduct().get(i).getProductCount());
                                llBuy.setVisibility(View.GONE);
                                Log.v(" in loop", "" + getIntent().getStringExtra("Id")
                                        + " " + response.getListEcommerceProduct().get(i).getProductId());
                                break;
                            }

                        }
                        tvCountPrice.setText("\u20b9 "+CalculateAmount());

                    }

                    @Override
                    public void onError(String errorMsg) {
                    }
                });
    }

    private Double CalculateAmount() {
         Double amount=0.0;
        for (int i = 0; i < cart_list.size(); i++) {
            amount = amount +( Double.parseDouble(cart_list.get(i).getPrice() )*Double.parseDouble(cart_list.get(i).getProductCount()));

        }
        return amount;
    }


    private void setHomeProduct() {
        rvProduct = (ShimmerRecyclerView) findViewById(R.id.rvProduct);
        rvProduct.showShimmerAdapter();
        UtilMethods.INSTANCE.GetHomeProductList(ProductDetails.this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListEcommerceProduct().size());
                        if (response.getListEcommerceProduct().size() > 0) {
                            Log.v("data", " size received " + response.getListEcommerceProduct().size());
                            productAdapter = new ProductAdapter(ProductDetails.this, response.getListEcommerceProduct(),
                                    new UtilMethods.ApiCallBack() {
                                        @Override
                                        public void onSucess(Object object) {


                                        }
                                    }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                                    ProductDetails.this, LinearLayoutManager.HORIZONTAL, false
                            );
                            rvProduct.setLayoutManager(mLayoutManager);
                            rvProduct.setItemAnimator(new DefaultItemAnimator());
                            rvProduct.setAdapter(productAdapter);

                            rvProduct.setVisibility(View.VISIBLE);
                        } else {
                            rvProduct.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        rvProduct.setVisibility(View.GONE);
                        Log.v("data", " size received " + "on error" + errorMsg);
                    }
                });
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
        Context context;
        UtilMethods.ApiCallBack apiCallBack;
        List<ListProductCategory> listProductCategory = new ArrayList<>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvtitle, SalePrice, Mrp, tvDiscount;
            public TextView quantity;
            public ImageView plus, minus;
            ImageView ivImg;
            LinearLayout llBuy;
            LinearLayout llUpdate;

            public MyViewHolder(View view) {
                super(view);
                tvtitle = (TextView) view.findViewById(R.id.tvtitle);
                SalePrice = (TextView) view.findViewById(R.id.SalePrice);
                Mrp = (TextView) view.findViewById(R.id.Mrp);
                tvDiscount = (TextView) view.findViewById(R.id.tvDiscount);
                quantity = (TextView) view.findViewById(R.id.quantity);
                plus = view.findViewById(R.id.plus);
                minus = view.findViewById(R.id.minus);
                ivImg = view.findViewById(R.id.ivImg);
                llBuy = view.findViewById(R.id.llBuy);
                llUpdate = view.findViewById(R.id.llUpdate);
            }
        }

        public void updateList(ArrayList<ListProductCategory> list) {
            listProductCategory = list;
            notifyDataSetChanged();
        }

        public ProductAdapter(Context context, List<ListProductCategory> listProductCategory,
                              UtilMethods.ApiCallBack apiCallBack) {
            this.listProductCategory = listProductCategory;
            this.context = context;
            this.apiCallBack = apiCallBack;
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
            holder.SalePrice.setText("\u20b9" + operator.getPrice());
            holder.Mrp.setText("\u20b9" + operator.getFinalPrice());
            holder.Mrp.setPaintFlags(holder.Mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            holder.tvDiscount.setText("\u20b9" + operator.getDiscount() + "\noff");
            Picasso.with(context)
                    .load(ApplicationConstant.INSTANCE.baseUrl + operator.getProductImage())
                    .into(holder.ivImg);
            Log.v("data", "binding" + position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(
                            new Intent(context, ProductDetails.class)
                                    .putExtra("Id", "" + operator.getProductId()));
                    ((ProductDetails)context).finish();

                }
            });

            for (int i = 0; i < cart_list.size(); i++) {

                 if ( operator.getProductId().equals(cart_list.get(i).getProductId())){
                     holder.llUpdate.setVisibility(View.VISIBLE);
                     holder.llBuy.setVisibility(View.GONE);
                      holder.quantity.setText(""+cart_list.get(i).getProductCount());
 break;
                }
            }
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qnty = Integer.parseInt(holder.quantity.getText().toString());
                    UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                            "" + operator.getProductId(), "" + (qnty + 1),
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "")
                                    .replace("\noff", "")),
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

                        UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                                "" + operator.getProductId(), "" + (qnty - 1),
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "")
                                        .replace("\noff", "")),
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
                        UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                                "" + operator.getProductId(), "" + 0,
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
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
                    UtilMethods.INSTANCE.AddToCart(ProductDetails.this,
                            "" + operator.getProductId(),
                            "1",
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
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