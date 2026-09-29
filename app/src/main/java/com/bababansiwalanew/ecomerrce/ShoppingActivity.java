package com.bababansiwalanew.ecomerrce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

public class ShoppingActivity extends AppCompatActivity {
    ShimmerRecyclerView shimmerRecycler;
    CategoryAapter adapter;
     LinearLayout llNodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        findViewById(R.id.ivCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.rvCategory);
        llNodata =  findViewById(R.id.llNodata);
        shimmerRecycler.showShimmerAdapter();
        UtilMethods.INSTANCE.GetProductCategory(this,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListProductCategory().size());
                        if (response.getListProductCategory().size() > 0) {
                            Log.v("data", " size recived " + response.getListProductCategory().size());
                            adapter = new CategoryAapter(ShoppingActivity.this, response.getListProductCategory(),"", new UtilMethods.ApiCallBack() {
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
                            llNodata.setVisibility(View.GONE);
                            shimmerRecycler.setVisibility(View.VISIBLE);

                        }else{
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
}