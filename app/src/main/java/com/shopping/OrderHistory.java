package com.shopping;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.CatgoryResponse;
import com.bababansiwalanew.Util.UtilMethods;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderHistory extends AppCompatActivity {
    ArrayList<OrderItem> order_list = new ArrayList<>();
    ShimmerRecyclerView shimmerRecycler;
    OrderAdapter adapter;
    TextView tvTitle, fromDate, toDate;
    LinearLayout llNodata;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvTitle = findViewById(R.id.tvTitle);
        llNodata = findViewById(R.id.llNodata);

        shimmerRecycler = (ShimmerRecyclerView) findViewById(R.id.rvOList);
        shimmerRecycler.showShimmerAdapter();


        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);
        Calendar myCalendar = Calendar.getInstance();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            myCalendar = Calendar.getInstance();
        }

        Calendar finalMyCalendar = myCalendar;
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                finalMyCalendar.set(Calendar.YEAR, year);
                finalMyCalendar.set(Calendar.MONTH, monthOfYear);
                finalMyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                fromDate.setText(sdf.format(finalMyCalendar.getTime()));
            }

        };
        Calendar finalMyCalendar1 = myCalendar;
        fromDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog pd = new DatePickerDialog(OrderHistory.this, date, finalMyCalendar1
                        .get(Calendar.YEAR), finalMyCalendar1.get(Calendar.MONTH),
                        finalMyCalendar1.get(Calendar.DAY_OF_MONTH));

                pd.show();
                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });


        Calendar finalMyCalendar3 = myCalendar;
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                finalMyCalendar3.set(Calendar.YEAR, year);
                finalMyCalendar3.set(Calendar.MONTH, monthOfYear);
                finalMyCalendar3.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                toDate.setText(sdf.format(finalMyCalendar3.getTime()));
            }

        };
        Calendar finalMyCalendar2 = myCalendar;
        toDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog pd = new DatePickerDialog(OrderHistory.this, date1, finalMyCalendar2
                        .get(Calendar.YEAR), finalMyCalendar2.get(Calendar.MONTH),
                        finalMyCalendar2.get(Calendar.DAY_OF_MONTH));

                pd.show();
                pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });


        findViewById(R.id.searchLayout11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromDate.getText().toString().isEmpty()){
                    Toast.makeText(OrderHistory.this,"Please select fromDate",Toast.LENGTH_LONG).show();
                }
                 else if ( toDate.getText().toString().isEmpty()){
                         Toast.makeText(OrderHistory.this,"Please select toDate",Toast.LENGTH_LONG).show();
                      }
                  else {
                    hitApi(""+fromDate.getText().toString(), ""+toDate.getText().toString());
                }

            }
        });

        findViewById(R.id.search1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromDate.getText().toString().isEmpty()){
                    Toast.makeText(OrderHistory.this,"Please select fromDate",Toast.LENGTH_LONG).show();
                }
                else if ( toDate.getText().toString().isEmpty()){
                    Toast.makeText(OrderHistory.this,"Please select toDate",Toast.LENGTH_LONG).show();
                }
                else {
                    hitApi(""+fromDate.getText().toString(), ""+toDate.getText().toString());
                }

            }
        });


        hitApi("","");

    }

    private void hitApi(String fromDate, String toDate) {
        ProgressDialog pd  = new ProgressDialog(OrderHistory.this);
                              pd.show();
        UtilMethods.INSTANCE.BuyProductDetail(OrderHistory.this, fromDate, toDate,
                new UtilMethods.ApiCallBackTwoMethod() {
                    @Override
                    public void onSucess(Object object) {
                        CatgoryResponse response = (CatgoryResponse) object;
                        Log.v("data", " on response " + response.getListBuyProduct().size());
                        pd.dismiss();
                        if (response.getListBuyProduct().size() > 0) {
                            Log.v("data", " size received " + response.getListBuyProduct().size());
                            adapter = new OrderAdapter(OrderHistory.this, response.getListBuyProduct(), new UtilMethods.ApiCallBack() {
                                @Override
                                public void onSucess(Object object) {

                                }
                            }
                            );
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(OrderHistory.this, LinearLayoutManager.VERTICAL, false);
                            shimmerRecycler.setLayoutManager(mLayoutManager);
                            shimmerRecycler.setItemAnimator(new DefaultItemAnimator());
                            shimmerRecycler.setAdapter(adapter);
                            llNodata.setVisibility(View.GONE);
                            shimmerRecycler.setVisibility(View.VISIBLE);

                        }
                        else {
                            llNodata.setVisibility(View.VISIBLE);
                            shimmerRecycler.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onError(String errorMsg) {
                        pd.dismiss();
                        llNodata.setVisibility(View.VISIBLE);
                        shimmerRecycler.setVisibility(View.GONE);
                        Log.v("data", " size received " + "on error" + errorMsg);
                    }
                });


    }


    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
        Context context;
        UtilMethods.ApiCallBack apiCallBack;
        List<OrderItem> listProductCategory = new ArrayList<>();

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tvtitle, tvTxnId, tvStatus, tvTotalAmount, tvOrderDate, SalePrice;
            ImageView ivImg;

            public MyViewHolder(View view) {
                super(view);
                tvtitle = (TextView) view.findViewById(R.id.tvtitle);
                SalePrice = (TextView) view.findViewById(R.id.SalePrice);
                tvTotalAmount = (TextView) view.findViewById(R.id.tvTotalAmount);
                tvOrderDate = (TextView) view.findViewById(R.id.tvOrderDate);
                tvStatus = (TextView) view.findViewById(R.id.tvStatus);
                tvTxnId = (TextView) view.findViewById(R.id.tvTxnId);
                ivImg = view.findViewById(R.id.ivImg);
            }
        }

        public void updateList(ArrayList<OrderItem> list) {
            listProductCategory = list;
            notifyDataSetChanged();
        }

        public OrderAdapter(Context context, List<OrderItem> listProductCategory,
                            UtilMethods.ApiCallBack apiCallBack) {
            this.listProductCategory = listProductCategory;
            this.context = context;
            this.apiCallBack = apiCallBack;
        }

        @Override
        public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_adapter, parent, false);
            return new OrderAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final OrderAdapter.MyViewHolder holder, int position) {
            final OrderItem operator = listProductCategory.get(position);
            holder.tvtitle.setText(operator.getProductName());
            holder.tvTxnId.setText("TXN ID : " + operator.getTransactionID());
            holder.tvOrderDate.setText(operator.getCreatedDate());
            holder.SalePrice.setText("\u20b9 " + operator.getPrice() + " X " + operator.getProductQuantity());
            holder.tvTotalAmount.setText("Total Amount : \u20b9 " + (Double.parseDouble(operator.getPrice())
                    * Double.parseDouble("" + operator.getProductQuantity())));

            if (operator.getStatus().equalsIgnoreCase("Processing")) {
                holder.tvStatus.setText("IN PROCESS");
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.orange));
            } else if (operator.getStatus().equalsIgnoreCase("Packing")) {
                holder.tvStatus.setText("PACKED" );
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.lightDarkGreen));
            } else if (operator.getStatus().equalsIgnoreCase("Delivered")) {
                holder.tvStatus.setText("DELIVERED"  );
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
            } else if (operator.getStatus().equalsIgnoreCase("Reject")) {
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red));
                holder.tvStatus.setText("REJECTED");
            } else {
                holder.tvStatus.setText("" + operator.getStatus());
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.orange));
            }


            Picasso.with(context)
                    .load(ApplicationConstant.INSTANCE.baseUrl + operator.getProductImage())
                    .into(holder.ivImg);
            Log.v("data", "binding" + position);


        }

        @Override
        public int getItemCount() {
            return listProductCategory.size();
        }

    }
}