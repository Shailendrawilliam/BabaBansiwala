package com.bababansiwalanew.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.shopping.CartListActivity;

public class SubCategory extends AppCompatActivity {
    ShimmerRecyclerView shimmerRecycler;
    SubCategoryAapter adapter;
    String CatName = "";
    TextView tvTitle,tvCartCount;
    LinearLayout llNOdata;
    @Override
    public void onResume() {
        super.onResume();
        HitCart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        CatName = getIntent().getStringExtra("CatName");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("" + CatName);
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        startActivity(new Intent(SubCategory.this, CartListActivity.class));

            }
        });
        HitCart();
        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.rvSubCategory);
        llNOdata = findViewById(R.id.llNodata);
        tvCartCount = findViewById(R.id.tvCartCount);
        shimmerRecycler.showShimmerAdapter();
        UtilMethods.INSTANCE.GetSubProductCategory(this, getIntent().getStringExtra("CatId"),
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListProductSubCategory().size());
                        if (response.getListProductSubCategory().size() > 0) {
                            Log.v("data", " size received " + response.getListProductSubCategory().size());
                            adapter = new SubCategoryAapter(SubCategory.this, getIntent().getStringExtra("CatId"), response.getListProductSubCategory(), new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {

                                }
                            }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3
                            );
                            shimmerRecycler.setLayoutManager(mLayoutManager);
                            shimmerRecycler.setItemAnimator(new DefaultItemAnimator());
                            shimmerRecycler.setAdapter(adapter);
                            llNOdata.setVisibility(View.GONE);
                            shimmerRecycler.setVisibility(View.VISIBLE);

                        } else {
                            llNOdata.setVisibility(View.VISIBLE);
                            shimmerRecycler.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onError(String errorMsg) {
                        llNOdata.setVisibility(View.VISIBLE);
                        shimmerRecycler.setVisibility(View.GONE);
                        Log.v("data", " size received " + "on error" + errorMsg);
                    }
                });
    }
    private void HitCart() {
        UtilMethods.INSTANCE.GetCartList(SubCategory.this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("GetCartList", " on response " + response.getListEcommerceProduct().size());
                        tvCartCount.setText("" + response.getListEcommerceProduct().size() + "");

                    }

                    @Override
                    public void onError(String errorMsg) {
                    }
                });
    }
}