package com.bababansiwalanew.KhataBook.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bababansiwalanew.KhataBook.dto.KhataBookCommonResponse;
import com.bababansiwalanew.KhataBook.dto.KhataBookCustomerResponse;
import com.bababansiwalanew.KhataBook.dto.KhataCustomer;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApiClient;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.EndPointInterface;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhataBookActivity extends AppCompatActivity {

    private ImageView btnBack, btnRefresh, btnClearSearch;
    private TextView tvTotalCustomers, tvSummaryTotalBalance;
    private EditText etSearchCustomer;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvCustomers;
    private LinearLayout layoutEmptyState;
    private View fabAddCustomer;

    private KhataCustomerAdapter adapter;
    private List<KhataCustomer> customerList = new ArrayList<>();
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatabook);

        initViews();
        setupRecyclerView();
        setupListeners();

        fetchCustomers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh customer list on return from detail screen in case balances changed
        fetchCustomers();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnClearSearch = findViewById(R.id.btnClearSearch);
        tvTotalCustomers = findViewById(R.id.tvTotalCustomers);
        tvSummaryTotalBalance = findViewById(R.id.tvSummaryTotalBalance);
        etSearchCustomer = findViewById(R.id.etSearchCustomer);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        rvCustomers = findViewById(R.id.rvCustomers);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
        fabAddCustomer = findViewById(R.id.fabAddCustomer);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Khata customers...");
        progressDialog.setCancelable(false);
    }

    private void setupRecyclerView() {
        adapter = new KhataCustomerAdapter(
                this,
                customerList,
                customer -> {
                    Intent intent = new Intent(KhataBookActivity.this, KhataBookCustomerDetailActivity.class);
                    intent.putExtra("customer", customer);
                    startActivity(intent);
                },
                (customer, position) -> showDeleteConfirmDialog(customer, position)
        );

        rvCustomers.setLayoutManager(new LinearLayoutManager(this));
        rvCustomers.setItemAnimator(new DefaultItemAnimator());
        rvCustomers.setAdapter(adapter);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        btnRefresh.setOnClickListener(v -> fetchCustomers());

        swipeRefreshLayout.setOnRefreshListener(this::fetchCustomers);

        fabAddCustomer.setOnClickListener(v -> showAddCustomerDialog());

        etSearchCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
                btnClearSearch.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnClearSearch.setOnClickListener(v -> {
            etSearchCustomer.setText("");
            btnClearSearch.setVisibility(View.GONE);
        });
    }

    private void fetchCustomers() {
        if (!UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!swipeRefreshLayout.isRefreshing() && !progressDialog.isShowing() && customerList.isEmpty()) {
            progressDialog.show();
        }
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String uMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookActivity.this);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + uMobile + (char) 160 + SessionID;

        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
        Call<KhataBookCustomerResponse> call = git.GetKhataBookCustomer(appInfo, password);
        call.enqueue(new Callback<KhataBookCustomerResponse>() {
            @Override
            public void onResponse(Call<KhataBookCustomerResponse> call, Response<KhataBookCustomerResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                if (response.isSuccessful() && response.body() != null) {
                    KhataBookCustomerResponse body = response.body();
                    customerList = body.getCustomerList();

                    adapter.updateList(customerList);
                    updateSummary(customerList);

                    if (customerList.isEmpty()) {
                        layoutEmptyState.setVisibility(View.VISIBLE);
                        rvCustomers.setVisibility(View.GONE);
                    } else {
                        layoutEmptyState.setVisibility(View.GONE);
                        rvCustomers.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(KhataBookActivity.this, "Failed to load customers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhataBookCustomerResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(KhataBookActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSummary(List<KhataCustomer> list) {
        int count = list != null ? list.size() : 0;
        tvTotalCustomers.setText(count + (count == 1 ? " Customer" : " Customers"));

        double totalNet = 0;
        if (list != null) {
            for (KhataCustomer c : list) {
                try {
                    String balStr = c.getBalance().replaceAll("[^0-9.-]", "");
                    if (!TextUtils.isEmpty(balStr)) {
                        totalNet += Double.parseDouble(balStr);
                    }
                } catch (Exception ignored) {
                }
            }
        }

        tvSummaryTotalBalance.setText(String.format("₹ %.2f", Math.abs(totalNet)));
        if (totalNet >= 0) {
            tvSummaryTotalBalance.setTextColor(Color.parseColor("#2E7D32"));
        } else {
            tvSummaryTotalBalance.setTextColor(Color.parseColor("#C62828"));
        }
    }

    private void showAddCustomerDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_khata_customer);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        EditText etName = dialog.findViewById(R.id.etNewCustomerName);
        EditText etMobile = dialog.findViewById(R.id.etNewCustomerMobile);
        EditText etAddress = dialog.findViewById(R.id.etNewCustomerAddress);
        AppCompatButton btnCancel = dialog.findViewById(R.id.btnCancelAddCustomer);
        AppCompatButton btnSave = dialog.findViewById(R.id.btnSaveNewCustomer);

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etName.setError("Customer name is required");
                etName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mobile) || mobile.length() < 10) {
                etMobile.setError("Valid 10-digit mobile number is required");
                etMobile.requestFocus();
                return;
            }

            if (!UtilMethods.INSTANCE.isNetworkAvialable(this)) {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                return;
            }

            ProgressDialog addDialog = new ProgressDialog(this);
            addDialog.setMessage("Adding customer...");
            addDialog.setCancelable(false);
            addDialog.show();
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
            String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
            String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookActivity.this);
            String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + mobileLogin + (char) 160 + SessionID;

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<KhataBookCommonResponse> call = git.CreateKhataBookCustomer(appInfo, password, name, mobile, address);
            call.enqueue(new Callback<KhataBookCommonResponse>() {
                @Override
                public void onResponse(Call<KhataBookCommonResponse> call, Response<KhataBookCommonResponse> response) {
                    if (addDialog.isShowing()) {
                        addDialog.dismiss();
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        KhataBookCommonResponse body = response.body();
                        if (body.isSuccess()) {
                            Toast.makeText(KhataBookActivity.this, "Customer added successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            fetchCustomers();
                        } else {
                            String msg = !TextUtils.isEmpty(body.getMessage()) ? body.getMessage() : "Failed to add customer";
                            Toast.makeText(KhataBookActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(KhataBookActivity.this, "Failed to create customer", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KhataBookCommonResponse> call, Throwable t) {
                    if (addDialog.isShowing()) {
                        addDialog.dismiss();
                    }
                    Toast.makeText(KhataBookActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        dialog.show();
    }

    // ── Delete customer from list ──────────────────────────────────────────────

    private void showDeleteConfirmDialog(KhataCustomer customer, int position) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Delete Customer")
                .setMessage("Delete \"" + customer.getName() + "\"?\nThis action cannot be undone.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete", (d, which) -> deleteCustomerFromList(customer, position))
                .setNegativeButton("Cancel", (d, which) -> d.dismiss())
                .create();
        dialog.show();
        // Force visible button colours regardless of app theme
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(android.graphics.Color.parseColor("#D32F2F")); // red
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(android.graphics.Color.parseColor("#212121")); // black
    }

    private void deleteCustomerFromList(KhataCustomer customer, int position) {
        if (!UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog deleteDialog = new ProgressDialog(this);
        deleteDialog.setMessage("Deleting customer...");
        deleteDialog.setCancelable(false);
        deleteDialog.show();

        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String uMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookActivity.this);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID)
                + (char) 160 + deviceId
                + (char) 160 + uMobile
                + (char) 160 + SessionID;

        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
        Call<KhataBookCommonResponse> call = git.DeleteKhataBookCustomer(appInfo, password, customer.getCustomerId());
        call.enqueue(new Callback<KhataBookCommonResponse>() {
            @Override
            public void onResponse(Call<KhataBookCommonResponse> call, Response<KhataBookCommonResponse> response) {
                if (deleteDialog.isShowing()) deleteDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    KhataBookCommonResponse body = response.body();
                    if (body.isSuccess()) {
                        Toast.makeText(KhataBookActivity.this,
                                customer.getName() + " deleted!", Toast.LENGTH_SHORT).show();
                        // Refresh the full list from server
                        fetchCustomers();
                    } else {
                        String msg = !TextUtils.isEmpty(body.getMessage())
                                ? body.getMessage() : "Failed to delete customer";
                        Toast.makeText(KhataBookActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(KhataBookActivity.this,
                            "Failed to delete. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhataBookCommonResponse> call, Throwable t) {
                if (deleteDialog.isShowing()) deleteDialog.dismiss();
                Toast.makeText(KhataBookActivity.this,
                        "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
