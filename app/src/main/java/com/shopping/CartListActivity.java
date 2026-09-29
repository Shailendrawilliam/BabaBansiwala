package com.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bababansiwalanew.Activities.ProductDetails;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {
    ArrayList<ListProductCategory> cart_list = new ArrayList<>();
    ShimmerRecyclerView shimmerRecycler;
    CartAdapter adapter;
    TextView tvTitle, tvCartCount, tvCount;
    TextView tvCountPrice, tvSavedPrice;
    LinearLayout llNodata;
    RelativeLayout llbottom;
    Double amount = 0.0;
    Double Saving_amount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvTitle = findViewById(R.id.tvTitle);
        llbottom = findViewById(R.id.llbottom);
        llNodata = findViewById(R.id.llNodata);
        tvCartCount = findViewById(R.id.tvCartCount);
        tvCount = findViewById(R.id.tvCount);
        tvCountPrice = findViewById(R.id.tvCountPrice);
        tvSavedPrice = findViewById(R.id.tvSavedPrice);
        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.rvCList);
        shimmerRecycler.showShimmerAdapter();

        HitCart();

        findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cart_list.size() > 0) {
                    ShowCheckoutAlert();

                } else {
                    Toast.makeText(CartListActivity.this, "Please add at least 1 product in your cart", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    private void ShowCheckoutAlert() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_checkout_alert, null);

        final TextView tvTotalProduct = (TextView) view.findViewById(R.id.tvTotalProduct);
        final TextView tvTotalAmount = (TextView) view.findViewById(R.id.tvTotalAmount);

        final TextView tvSaving = (TextView) view.findViewById(R.id.tvSaving);
        final TextView tvCancel = (TextView) view.findViewById(R.id.tvCancel);
        final TextView tvOk = (TextView) view.findViewById(R.id.tvOk);
        tvTotalProduct.setText("" + cart_list.size());
        tvTotalAmount.setText(" \u20b9" + CalculateAmount());
        tvSaving.setText("You will save total \u20b9 " + CalculateDiscountAmount() + " on this order.");
        final Dialog dialog = new Dialog(CartListActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HitCheckout();

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void ThankyouDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_checkout_thankyou, null);

        final TextView tvOk = (TextView) view.findViewById(R.id.tvOk);

        final Dialog dialog = new Dialog(CartListActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(CartListActivity.this, OrderHistory.class));
                finish();
            }
        });

        dialog.show();

    }

    private void HitCart() {

        UtilMethods.INSTANCE.GetCartList(CartListActivity.this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListEcommerceProduct().size());
                        tvCount.setText("" + response.getListEcommerceProduct().size() + " Products");

                        cart_list.clear();
                        if (response.getListEcommerceProduct().size() > 0) {
                            Log.v("data", " size received " + response.getListEcommerceProduct().size());
                            cart_list.addAll(response.getListEcommerceProduct());
                            adapter = new CartAdapter(CartListActivity.this, response.getListEcommerceProduct(), new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {

                                }
                            }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CartListActivity.this, LinearLayoutManager.VERTICAL, false);
                            shimmerRecycler.setLayoutManager(mLayoutManager);
                            shimmerRecycler.setItemAnimator(new DefaultItemAnimator());
                            shimmerRecycler.setAdapter(adapter);
                            llNodata.setVisibility(View.GONE);
                            shimmerRecycler.setVisibility(View.VISIBLE);
                            llbottom.setVisibility(View.VISIBLE);
                            tvCountPrice.setText("Total amount \u20b9" + CalculateAmount());
                            tvSavedPrice.setText("you saved total \u20b9 " + CalculateDiscountAmount());
                        } else {
                            llbottom.setVisibility(View.GONE);
                            llNodata.setVisibility(View.VISIBLE);
                            shimmerRecycler.setVisibility(View.GONE);
                            tvCountPrice.setText("Total amount \u20b9" + "0.0");
                            tvSavedPrice.setText("you saved total \u20b9 " + "0.0");
                            tvCount.setText("0 Products");
                            cart_list.clear();
                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        cart_list.clear();
                        llNodata.setVisibility(View.VISIBLE);
                        shimmerRecycler.setVisibility(View.GONE);
                        tvCountPrice.setText("Total amount \u20b9" + "0.0");
                        tvCount.setText("0 Products");
                        tvSavedPrice.setText("you saved total \u20b9 " + "0.0");
                        Log.v("data", " size received " + "on error" + errorMsg);
                    }
                });
    }

    private Double CalculateAmount() {
        Double amount = 0.0;
        for (int i = 0; i < cart_list.size(); i++) {
            amount = amount + (Double.parseDouble(cart_list.get(i).getPrice()) * Double.parseDouble(cart_list.get(i).getProductCount()));
        }
        return amount;
    }


    private Double CalculateDiscountAmount() {
        Double amt = 0.0;
        for (int i = 0; i < cart_list.size(); i++) {

            amt = amt + (Double.parseDouble(cart_list.get(i).getDiscount()) * Double.parseDouble(cart_list.get(i).getProductCount()));

        }
        return amt;
    }


    private void HitCheckout() {
        UtilMethods.INSTANCE.BuyProduct(CartListActivity.this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        ThankyouDialog();

                    }

                    @Override
                    public void onError(String errorMsg) {
                        Toast.makeText(CartListActivity.this, "" + errorMsg, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
        Context context;
        UtilMethods.ApiCallBack apiCallBack;
        List<ListProductCategory> listProductCategory = new ArrayList<>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvtitle, SalePrice, Mrp, tvDiscount;
            public TextView quantity;
            public ImageView plus, minus;
            ImageView ivImg, delete;
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
                plus = view.findViewById(R.id.plus);
                minus = view.findViewById(R.id.minus);
                ivImg = view.findViewById(R.id.ivImg);
                ll_discount = view.findViewById(R.id.ll_discount);
                llUpdate = view.findViewById(R.id.llUpdate);
                llBuy = view.findViewById(R.id.llBuy);
                delete = view.findViewById(R.id.delete);
            }
        }

        public void updateList(ArrayList<ListProductCategory> list) {
            listProductCategory = list;
            notifyDataSetChanged();
        }

        public CartAdapter(Context context, List<ListProductCategory> listProductCategory,
                           UtilMethods.ApiCallBack apiCallBack) {
            this.listProductCategory = listProductCategory;
            this.context = context;
            this.apiCallBack = apiCallBack;
        }

        @Override
        public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cart_adapter, parent, false);
            return new CartAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final CartAdapter.MyViewHolder holder, int position) {
            final ListProductCategory operator = listProductCategory.get(position);
            holder.tvtitle.setText(operator.getProductName());
            holder.SalePrice.setText("\u20b9 " + operator.getPrice());
            holder.Mrp.setText("\u20b9 " + operator.getFinalPrice());
            holder.tvDiscount.setText("\u20b9" + operator.getDiscount().replace(".00", "") + "\n off");
            if (operator.getDiscount() != null && !operator.getDiscount().equalsIgnoreCase("0.00")) {
                holder.ll_discount.setVisibility(View.VISIBLE);
            } else {
                holder.ll_discount.setVisibility(View.GONE);
            }
            holder.Mrp.setPaintFlags(holder.Mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Picasso.with(context)
                    .load(ApplicationConstant.INSTANCE.baseUrl + operator.getProductImage())
                    .into(holder.ivImg);
            Log.v("data", "binding" + position);

            for (int i = 0; i < cart_list.size(); i++) {

                if (operator.getProductId().equals(cart_list.get(i).getProductId())) {
                    holder.llUpdate.setVisibility(View.VISIBLE);
                    holder.llBuy.setVisibility(View.GONE);
                    holder.quantity.setText("" + cart_list.get(i).getProductCount());
                    break;
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(
                            new Intent(context, ProductDetails.class)
                                    .putExtra("Id", "" + operator.getProductId()));
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilMethods.INSTANCE.AddToCart(CartListActivity.this,
                            "" + operator.getProductId(), "" + 0,
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\n off", "")),
                            holder.Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    //  Toast.makeText(getApplicationContext(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    holder.llUpdate.setVisibility(View.GONE);
                                    holder.llBuy.setVisibility(View.GONE);
                                    HitCart();
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    //  Toast.makeText(ProductDetails.this, "Unable to update quantity", Toast.LENGTH_LONG).show();
                                    Log.v("data", " size received " + "on error" + errorMsg);
                                }
                            });
                }
            });
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qnty = Integer.parseInt(holder.quantity.getText().toString());
                    UtilMethods.INSTANCE.AddToCart(CartListActivity.this,
                            "" + operator.getProductId(), "" + (qnty + 1),
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

                        UtilMethods.INSTANCE.AddToCart(CartListActivity.this,
                                "" + operator.getProductId(), "" + (qnty - 1),
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
                        UtilMethods.INSTANCE.AddToCart(CartListActivity.this,
                                "" + operator.getProductId(), "" + 0,
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
                    UtilMethods.INSTANCE.AddToCart(CartListActivity.this,
                            "" + operator.getProductId(),
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
}