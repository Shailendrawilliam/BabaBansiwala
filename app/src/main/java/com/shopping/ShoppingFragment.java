package com.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bababansiwalanew.Activities.EcommerceBannerModel;
import com.bababansiwalanew.Activities.ProductDetails;
import com.bababansiwalanew.Activities.ShoppingPagerAdapter;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.EcommerceBannerResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.ecomerrce.CategoryAapter;
import com.bababansiwalanew.ecomerrce.ShoppingActivity;
import com.bababansiwalanew.ecomerrce.modal.ListProductCategory;
 import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment implements View.OnClickListener {
    ArrayList<EcommerceBannerModel> imageList = new ArrayList<>();
    ShoppingPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    ShimmerRecyclerView shimmerRecycler;
    TextView tvCartCount;
    Handler handler;
    CategoryAapter adapter;
    ShimmerRecyclerView rvProduct;
    ProductAdapter productAdapter;
    ArrayList<ListProductCategory> cart_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.shopping_fragment, container, false);
        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String icon = myPrefs.getString(ApplicationConstant.INSTANCE.icon, null);
        String role = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, "");
        mViewPager = v.findViewById(R.id.pager);
        tvCartCount = v.findViewById(R.id.tvCartCount);
        v.findViewById(R.id.tvViewAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ShoppingActivity.class));
            }
        });
        v.findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), CartListActivity.class));
            }
        });


        shimmerRecycler = (ShimmerRecyclerView) v.findViewById(R.id.rvCategory);
        shimmerRecycler.showShimmerAdapter();
        handler = new Handler();
        ShoppingBannerAPI();
        HitCart();
        setHomeProduct(v);
        UtilMethods.INSTANCE.GetProductCategory(getActivity(),
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListProductCategory().size());
                        if (response.getListProductCategory().size() > 0) {
                            Log.v("data", " size recived " + response.getListProductCategory().size());
                            adapter = new CategoryAapter(getActivity(), response.getListProductCategory(), "fragment", new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {


                                }
                            }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
                            shimmerRecycler.setLayoutManager(mLayoutManager);
                            shimmerRecycler.setItemAnimator(new DefaultItemAnimator());
                            shimmerRecycler.setAdapter(adapter);


                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.v("data", " size received " + "on error" + errorMsg);
                    }
                });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        HitCart();
    }

    private void setHomeProduct(View root) {
        rvProduct = (ShimmerRecyclerView) root.findViewById(R.id.rvProduct);
        rvProduct.showShimmerAdapter();
        UtilMethods.INSTANCE.GetHomeProductList(getActivity(),
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListEcommerceProduct().size());
                        if (response.getListEcommerceProduct().size() > 0) {
                            Log.v("data", " size recived " + response.getListEcommerceProduct().size());
                            productAdapter = new ProductAdapter(getActivity(), response.getListEcommerceProduct(),
                                    new UtilMethods.ApiCallBack() {
                                        @Override
                                        public void onSucess(Object object) {


                                        }
                                    }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                                    getActivity(), LinearLayoutManager.HORIZONTAL, false
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
                        Log.v("data", " size recived " + "on error" + errorMsg);
                    }
                });
    }

    private void HitCart() {
        UtilMethods.INSTANCE.GetCartList(requireActivity(),
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
            public TextView tvtitle, SalePrice, Mrp, tvDiscount;
            public TextView quantity;
            public ImageView plus, minus;
            ImageView ivImg;
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
                llUpdate = view.findViewById(R.id.llUpdate);
                llBuy = view.findViewById(R.id.llBuy);
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
                }
            });
            for (int i = 0; i < cart_list.size(); i++) {

                if (operator.getProductId().equals(cart_list.get(i).getProductId())) {
                    holder.llUpdate.setVisibility(View.VISIBLE);
                    holder.llBuy.setVisibility(View.GONE);
                    holder.quantity.setText("" + cart_list.get(i).getProductCount());

                }
            }
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int qnty = Integer.parseInt(holder.quantity.getText().toString());
                    UtilMethods.INSTANCE.AddToCart(getActivity(),
                            "" + operator.getProductId(), "" + (qnty + 1),
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "")
                                    .replace("\noff", "")),
                            holder.Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    //Toast.makeText(getActivity(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                    holder.llUpdate.setVisibility(View.VISIBLE);
                                    holder.llBuy.setVisibility(View.GONE);
                                    holder.quantity.setText("" + (qnty + 1));
                                    HitCart();
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    //Toast.makeText(getActivity(), "Unable to update quantity", Toast.LENGTH_LONG).show();
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

                        UtilMethods.INSTANCE.AddToCart(getActivity(),
                                "" + operator.getProductId(), "" + (qnty - 1),
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "")
                                        .replace("\noff", "")),
                                holder.Mrp.getText().toString().replace("₹", "")
                                , new UtilMethods.ApiCallBackTwoMethod() {
                                    @Override
                                    public void onSucess(Object object) {
                                        // Toast.makeText(getActivity(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                        holder.llUpdate.setVisibility(View.VISIBLE);
                                        holder.llBuy.setVisibility(View.GONE);
                                        holder.quantity.setText("" + (qnty - 1));
                                        HitCart();
                                    }

                                    @Override
                                    public void onError(String errorMsg) {
                                        ///  Toast.makeText(getActivity(), "Unable to update quantity", Toast.LENGTH_LONG).show();
                                        Log.v("data", " size received " + "on error" + errorMsg);
                                    }
                                });
                    } else {
                        UtilMethods.INSTANCE.AddToCart(getActivity(),
                                "" + operator.getProductId(), "" + 0,
                                holder.SalePrice.getText().toString().replace("₹", "")
                                , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                                holder.Mrp.getText().toString().replace("₹", "")
                                , new UtilMethods.ApiCallBackTwoMethod() {
                                    @Override
                                    public void onSucess(Object object) {
                                        //  Toast.makeText(getActivity(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                                        holder.llUpdate.setVisibility(View.GONE);
                                        holder.llBuy.setVisibility(View.VISIBLE);
                                        HitCart();
                                    }

                                    @Override
                                    public void onError(String errorMsg) {
                                        //  Toast.makeText(getActivity(), "Unable to update quantity", Toast.LENGTH_LONG).show();
                                        Log.v("data", " size received " + "on error" + errorMsg);
                                    }
                                });
                    }
                }
            });
            holder.llBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilMethods.INSTANCE.AddToCart(getActivity(),
                            "" + operator.getProductId(),
                            "1",
                            holder.SalePrice.getText().toString().replace("₹", "")
                            , (holder.tvDiscount.getText().toString().replace("₹", "").replace("\noff", "")),
                            holder.Mrp.getText().toString().replace("₹", "")
                            , new UtilMethods.ApiCallBackTwoMethod() {
                                @Override
                                public void onSucess(Object object) {
                                    Toast.makeText(getActivity(), "Product Added Successfully", Toast.LENGTH_LONG).show();
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


    private void postDelayedScrollNext() {
        handler.postDelayed(new Runnable() {
            public void run() {
                if (mViewPager.getAdapter() != null) {
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                        mViewPager.setCurrentItem(0);
                        postDelayedScrollNext();
                        return;
                    }
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    postDelayedScrollNext();
                }
            }
        }, 8000);

    }

    public void ShoppingBannerAPI() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {
            try {
                UtilMethods.INSTANCE.ShoppingBanner(getActivity(), new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        EcommerceBannerResponse bannerResponse = (EcommerceBannerResponse) object;
                        if (bannerResponse != null) {
                            imageList.addAll(bannerResponse.getListEcommerceBanner());
                            if (imageList != null && imageList.size() > 0) {
                                mCustomPagerAdapter = new ShoppingPagerAdapter(imageList, getActivity());
                                mViewPager.setAdapter(mCustomPagerAdapter);
                                mViewPager.setOffscreenPageLimit(mCustomPagerAdapter.getCount());
//                                mDotsCount = mViewPager.getAdapter().getCount();
//                                mDotsText = new TextView[mDotsCount];
                                postDelayedScrollNext();
                            }

                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onClick(View v) {
      /*
        if (v == createUser) {
            Intent createIntent = new Intent(getActivity(), SignupScreen.class);
            createIntent.putExtra("from","profile");
            startActivity(createIntent);
        }*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Shopping");
        super.onActivityCreated(savedInstanceState);
    }
}