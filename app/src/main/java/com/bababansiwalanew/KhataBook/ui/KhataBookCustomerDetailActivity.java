package com.bababansiwalanew.KhataBook.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.KhataBook.dto.KhataBookCommonResponse;
import com.bababansiwalanew.KhataBook.dto.KhataBookLedgerResponse;
import com.bababansiwalanew.KhataBook.dto.KhataCustomer;
import com.bababansiwalanew.KhataBook.dto.KhataLedgerItem;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApiClient;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.EndPointInterface;
import com.bababansiwalanew.Util.UtilMethods;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Base64;
import com.bababansiwalanew.Util.KhataShareUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhataBookCustomerDetailActivity extends AppCompatActivity {

    private ImageView btnBackDetail, btnCallCustomerDetail, btnDeleteCustomerDetail, btnShareCustomerDetail;
    private TextView tvCustomerDetailName, tvCustomerDetailMobile, tvCustomerDetailAddress;
    private TextView tvDetailNetBalance, tvDetailBalanceStatus;
    private LinearLayout btnFromDate, btnToDate;
    private TextView tvFromDate, tvToDate;
    private AppCompatButton btnApplyDateFilter, btnYouGave, btnYouGot;
    private RecyclerView rvLedgerEntries;
    private LinearLayout layoutEmptyLedger;

    private KhataCustomer customer;
    private KhataLedgerAdapter adapter;
    private List<KhataLedgerItem> ledgerList = new ArrayList<>();
    private ProgressDialog progressDialog;

    private String fromDateStr = "";
    private String toDateStr = "";

    private ImageView currentDialogImagePreview;
    private LinearLayout currentDialogPlaceholder;
    private ImageView currentDialogBtnRemove;
    private String currentSelectedImageBase64 = "";

    private static final int REQUEST_CAMERA = 1001;
    private static final int REQUEST_GALLERY = 1002;

    private final Calendar calendarFrom = Calendar.getInstance();
    private final Calendar calendarTo = Calendar.getInstance();
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatabook_customer_detail);

        // Retrieve customer data passed from KhataBookActivity
        if (getIntent().hasExtra("customer")) {
            customer = (KhataCustomer) getIntent().getSerializableExtra("customer");
        }

        if (customer == null) {
            Toast.makeText(this, "Customer details not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        initViews();
        populateCustomerData();
        setupRecyclerView();
        setupListeners();

        // Load ledger with default date range (e.g. past 30 days to today)
        calendarFrom.add(Calendar.DAY_OF_MONTH, -30);
        fromDateStr = sdf.format(calendarFrom.getTime());
        toDateStr = sdf.format(calendarTo.getTime());
        tvFromDate.setText(fromDateStr);
        tvToDate.setText(toDateStr);

        fetchLedger();
    }

    private void initViews() {
        btnBackDetail = findViewById(R.id.btnBackDetail);
        btnCallCustomerDetail = findViewById(R.id.btnCallCustomerDetail);
        btnShareCustomerDetail = findViewById(R.id.btnShareCustomerDetail);
        tvCustomerDetailName = findViewById(R.id.tvCustomerDetailName);
        tvCustomerDetailMobile = findViewById(R.id.tvCustomerDetailMobile);
        tvCustomerDetailAddress = findViewById(R.id.tvCustomerDetailAddress);
        tvDetailNetBalance = findViewById(R.id.tvDetailNetBalance);
        tvDetailBalanceStatus = findViewById(R.id.tvDetailBalanceStatus);
        btnFromDate = findViewById(R.id.btnFromDate);
        btnToDate = findViewById(R.id.btnToDate);
        tvFromDate = findViewById(R.id.tvFromDate);
        tvToDate = findViewById(R.id.tvToDate);
        btnApplyDateFilter = findViewById(R.id.btnApplyDateFilter);
        btnYouGave = findViewById(R.id.btnYouGave);
        btnYouGot = findViewById(R.id.btnYouGot);
        rvLedgerEntries = findViewById(R.id.rvLedgerEntries);
        layoutEmptyLedger = findViewById(R.id.layoutEmptyLedger);
        btnDeleteCustomerDetail = findViewById(R.id.btnDeleteCustomerDetail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading statement...");
        progressDialog.setCancelable(false);
    }

    private void populateCustomerData() {
        tvCustomerDetailName.setText(customer.getName());

        if (!TextUtils.isEmpty(customer.getMobile())) {
            tvCustomerDetailMobile.setText("+91 " + customer.getMobile());
            btnCallCustomerDetail.setVisibility(View.VISIBLE);
        } else {
            tvCustomerDetailMobile.setText("No mobile number");
            btnCallCustomerDetail.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(customer.getAddress())) {
            tvCustomerDetailAddress.setText("Address: " + customer.getAddress());
        } else {
            tvCustomerDetailAddress.setText("Address: Not specified");
        }

        updateBalanceDisplay(customer.getBalance());
    }

    private void updateBalanceDisplay(String balanceStr) {
        double balance = 0;
        try {
            String balClean = balanceStr.replaceAll("[^0-9.-]", "");
            if (!TextUtils.isEmpty(balClean)) {
                balance = Double.parseDouble(balClean);
            }
        } catch (Exception ignored) {
        }

        tvDetailNetBalance.setText(String.format("₹ %.2f", Math.abs(balance)));

        if (balance > 0) {
            tvDetailNetBalance.setTextColor(Color.parseColor("#2E7D32"));
            tvDetailBalanceStatus.setText("You'll Get");
            tvDetailBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_credit);
            tvDetailBalanceStatus.setTextColor(Color.parseColor("#2E7D32"));
        } else if (balance < 0) {
            tvDetailNetBalance.setTextColor(Color.parseColor("#C62828"));
            tvDetailBalanceStatus.setText("You'll Give");
            tvDetailBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_debit);
            tvDetailBalanceStatus.setTextColor(Color.parseColor("#C62828"));
        } else {
            tvDetailNetBalance.setTextColor(Color.parseColor("#757575"));
            tvDetailBalanceStatus.setText("Settled");
            tvDetailBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_credit);
            tvDetailBalanceStatus.setTextColor(Color.parseColor("#757575"));
        }
    }

    private void setupRecyclerView() {
        adapter = new KhataLedgerAdapter(this, ledgerList);
        rvLedgerEntries.setLayoutManager(new LinearLayoutManager(this));
        rvLedgerEntries.setItemAnimator(new DefaultItemAnimator());
        rvLedgerEntries.setAdapter(adapter);
    }

    private void setupListeners() {
        btnBackDetail.setOnClickListener(v -> onBackPressed());

        btnCallCustomerDetail.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(customer.getMobile())) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + customer.getMobile()));
                    startActivity(callIntent);
                } catch (Exception e) {
                    Toast.makeText(this, "Cannot open dialer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (btnShareCustomerDetail != null) {
            btnShareCustomerDetail.setOnClickListener(v -> shareCustomerStatement());
        }

        btnDeleteCustomerDetail.setOnClickListener(v -> showDeleteConfirmationDialog());

        btnFromDate.setOnClickListener(v -> showDatePicker(true));
        btnToDate.setOnClickListener(v -> showDatePicker(false));

        btnApplyDateFilter.setOnClickListener(v -> fetchLedger());

        btnYouGave.setOnClickListener(v -> showAddEntryDialog(false));
        btnYouGot.setOnClickListener(v -> showAddEntryDialog(true));
    }

    private void shareCustomerStatement() {
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String uName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, "बाबा बंशी वाला");
        String uMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, "");
        KhataShareUtil.shareCustomerReminder(this, customer, uName, uMobile);
    }

    private void showDeleteConfirmationDialog() {
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Customer")
                .setMessage("Are you sure you want to delete \""
                        + customer.getName() + "\"?\nThis action cannot be undone.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete", (d, which) -> deleteCustomer())
                .setNegativeButton("Cancel", (d, which) -> d.dismiss())
                .create();
        dialog.show();
        dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(android.graphics.Color.parseColor("#D32F2F"));
        dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(android.graphics.Color.parseColor("#212121"));
    }

    private void deleteCustomer() {
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
        String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookCustomerDetailActivity.this);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID)
                + (char) 160 + deviceId
                + (char) 160 + uMobile
                + (char) 160 + SessionID;

        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
        Call<KhataBookCommonResponse> call = git.DeleteKhataBookCustomer(appInfo, password, customer.getCustomerId());
        call.enqueue(new Callback<KhataBookCommonResponse>() {
            @Override
            public void onResponse(Call<KhataBookCommonResponse> call, Response<KhataBookCommonResponse> response) {
                if (deleteDialog.isShowing()) {
                    deleteDialog.dismiss();
                }
                if (response.isSuccessful() && response.body() != null) {
                    KhataBookCommonResponse body = response.body();
                    if (body.isSuccess()) {
                        Toast.makeText(KhataBookCustomerDetailActivity.this,
                                customer.getName() + " deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        String msg = !TextUtils.isEmpty(body.getMessage()) ? body.getMessage() : "Failed to delete customer";
                        Toast.makeText(KhataBookCustomerDetailActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(KhataBookCustomerDetailActivity.this,
                            "Failed to delete customer. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhataBookCommonResponse> call, Throwable t) {
                if (deleteDialog.isShowing()) {
                    deleteDialog.dismiss();
                }
                Toast.makeText(KhataBookCustomerDetailActivity.this,
                        "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePicker(boolean isFromDate) {
        Calendar cal = isFromDate ? calendarFrom : calendarTo;
        DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String formatted = sdf.format(cal.getTime());
            if (isFromDate) {
                fromDateStr = formatted;
                tvFromDate.setText(formatted);
            } else {
                toDateStr = formatted;
                tvToDate.setText(formatted);
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void fetchLedger() {
        if (!UtilMethods.INSTANCE.isNetworkAvialable(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String uMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
        String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookCustomerDetailActivity.this);
        String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + uMobile + (char) 160 + SessionID;

        progressDialog.show();

        EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
        Call<KhataBookLedgerResponse> call = git.KhataBookLedger(appInfo, password, customer.getCustomerId(), fromDateStr, toDateStr);
        call.enqueue(new Callback<KhataBookLedgerResponse>() {
            @Override
            public void onResponse(Call<KhataBookLedgerResponse> call, Response<KhataBookLedgerResponse> response) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                if (response.isSuccessful() && response.body() != null) {
                    KhataBookLedgerResponse body = response.body();
                    ledgerList = body.getLedgerList();
                    adapter.updateList(ledgerList);

                    if (ledgerList.isEmpty()) {
                        layoutEmptyLedger.setVisibility(View.VISIBLE);
                        rvLedgerEntries.setVisibility(View.GONE);
                    } else {
                        layoutEmptyLedger.setVisibility(View.GONE);
                        rvLedgerEntries.setVisibility(View.VISIBLE);

                        KhataLedgerItem lastItem = ledgerList.get(0);
                        if (!TextUtils.isEmpty(lastItem.getBalanceAmount())) {
                            updateBalanceDisplay(lastItem.getBalanceAmount());
                        }
                    }
                } else {
                    Toast.makeText(KhataBookCustomerDetailActivity.this, "Failed to load statement", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KhataBookLedgerResponse> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(KhataBookCustomerDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddEntryDialog(boolean isCredit) {
        currentSelectedImageBase64 = "";

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_khata_entry);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        LinearLayout layoutHeader = dialog.findViewById(R.id.layoutEntryHeader);
        TextView tvTitle = dialog.findViewById(R.id.tvEntryDialogTitle);
        TextView tvSubtitle = dialog.findViewById(R.id.tvEntryDialogSubtitle);
        RadioGroup rgFundType = dialog.findViewById(R.id.rgFundType);
        RadioButton rbDebit = dialog.findViewById(R.id.rbDebit);
        RadioButton rbCredit = dialog.findViewById(R.id.rbCredit);
        EditText etAmount = dialog.findViewById(R.id.etEntryAmount);
        EditText etRemark = dialog.findViewById(R.id.etEntryRemark);

        currentDialogImagePreview = dialog.findViewById(R.id.ivEntryImagePreview);
        currentDialogPlaceholder = dialog.findViewById(R.id.layoutImagePlaceholder);
        currentDialogBtnRemove = dialog.findViewById(R.id.btnRemoveImage);
        View cardImageContainer = dialog.findViewById(R.id.cardImageContainer);
        AppCompatButton btnTakePhoto = dialog.findViewById(R.id.btnTakePhoto);
        AppCompatButton btnSelectPhoto = dialog.findViewById(R.id.btnSelectPhoto);

        AppCompatButton btnCancel = dialog.findViewById(R.id.btnCancelEntry);
        AppCompatButton btnSave = dialog.findViewById(R.id.btnSaveEntry);

        tvSubtitle.setText("Customer: " + customer.getName());

        if (isCredit) {
            rbCredit.setChecked(true);
            layoutHeader.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvTitle.setText("Record Payment Received");
        } else {
            rbDebit.setChecked(true);
            layoutHeader.setBackgroundColor(Color.parseColor("#D83430"));
            tvTitle.setText("Record Payment Given");
        }

        rgFundType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCredit) {
                layoutHeader.setBackgroundColor(Color.parseColor("#2E7D32"));
                tvTitle.setText("Record Payment Received");
            } else {
                layoutHeader.setBackgroundColor(Color.parseColor("#D83430"));
                tvTitle.setText("Record Payment Given");
            }
        });

        if (cardImageContainer != null) {
            cardImageContainer.setOnClickListener(v -> openCamera());
        }
        if (btnTakePhoto != null) {
            btnTakePhoto.setOnClickListener(v -> openCamera());
        }
        if (btnSelectPhoto != null) {
            btnSelectPhoto.setOnClickListener(v -> openGallery());
        }
        if (currentDialogBtnRemove != null) {
            currentDialogBtnRemove.setOnClickListener(v -> clearSelectedImage());
        }

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            String amount = etAmount.getText().toString().trim();
            String remark = etRemark.getText().toString().trim();
            String fundType = rbCredit.isChecked() ? "Credit" : "Debit";

            if (TextUtils.isEmpty(amount)) {
                etAmount.setError("Amount is required");
                etAmount.requestFocus();
                return;
            }

            try {
                double val = Double.parseDouble(amount);
                if (val <= 0) {
                    etAmount.setError("Enter a valid amount greater than 0");
                    etAmount.requestFocus();
                    return;
                }
            } catch (Exception e) {
                etAmount.setError("Invalid amount format");
                etAmount.requestFocus();
                return;
            }

            String imageBase64ToSend = currentSelectedImageBase64 != null ? currentSelectedImageBase64 : "";

            if (!UtilMethods.INSTANCE.isNetworkAvialable(this)) {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                return;
            }

            ProgressDialog entryDialog = new ProgressDialog(this);
            entryDialog.setMessage("Recording entry...");
            entryDialog.setCancelable(false);
            entryDialog.show();
            SharedPreferences myPrefs = getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, MODE_PRIVATE);
            String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
            String uMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
            String password = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);
            String deviceId = UtilMethods.INSTANCE.getDeviceId(KhataBookCustomerDetailActivity.this);
            String appInfo = UtilMethods.INSTANCE.md5Convertor(ApplicationConstant.INSTANCE.APP_ID) + (char) 160 + deviceId + (char) 160 + uMobile + (char) 160 + SessionID;

            EndPointInterface git = ApiClient.getClient().create(EndPointInterface.class);
            Call<KhataBookCommonResponse> call = git.AddItemKhataBookCustomer(appInfo, password, customer.getCustomerId(), amount, fundType, remark, imageBase64ToSend);
            call.enqueue(new Callback<KhataBookCommonResponse>() {
                @Override
                public void onResponse(Call<KhataBookCommonResponse> call, Response<KhataBookCommonResponse> response) {
                    if (entryDialog.isShowing()) {
                        entryDialog.dismiss();
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        KhataBookCommonResponse body = response.body();
                        if (body.isSuccess()) {
                            Toast.makeText(KhataBookCustomerDetailActivity.this, "Entry saved successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            fetchLedger();
                        } else {
                            String msg = !TextUtils.isEmpty(body.getMessage()) ? body.getMessage() : "Failed to record entry";
                            Toast.makeText(KhataBookCustomerDetailActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(KhataBookCustomerDetailActivity.this, "Failed to save entry", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KhataBookCommonResponse> call, Throwable t) {
                    if (entryDialog.isShowing()) {
                        entryDialog.dismiss();
                    }
                    Toast.makeText(KhataBookCustomerDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        dialog.show();
    }

    private void openCamera() {
        try {
            if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                androidx.core.app.ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 100);
                return;
            }
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open camera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {
        try {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, REQUEST_GALLERY);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open gallery: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void clearSelectedImage() {
        currentSelectedImageBase64 = "";
        if (currentDialogImagePreview != null) {
            currentDialogImagePreview.setImageDrawable(null);
            currentDialogImagePreview.setVisibility(View.GONE);
        }
        if (currentDialogPlaceholder != null) {
            currentDialogPlaceholder.setVisibility(View.VISIBLE);
        }
        if (currentDialogBtnRemove != null) {
            currentDialogBtnRemove.setVisibility(View.GONE);
        }
    }

    private Bitmap scaleBitmapIfNeeded(Bitmap bitmap, int maxDimension) {
        if (bitmap == null) return null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= maxDimension && height <= maxDimension) return bitmap;

        float ratio = Math.min((float) maxDimension / width, (float) maxDimension / height);
        int newWidth = Math.round(ratio * width);
        int newHeight = Math.round(ratio * height);
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = null;
                if (requestCode == REQUEST_CAMERA && data != null) {
                    if (data.getExtras() != null && data.getExtras().get("data") != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                    } else if (data.getData() != null) {
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    }
                } else if (requestCode == REQUEST_GALLERY && data != null && data.getData() != null) {
                    Uri selectedImageUri = data.getData();
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                }

                if (bitmap != null) {
                    Bitmap scaledBitmap = scaleBitmapIfNeeded(bitmap, 800);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
                    byte[] byteArray = outputStream.toByteArray();
                    currentSelectedImageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);

                    if (currentDialogImagePreview != null) {
                        currentDialogImagePreview.setImageBitmap(scaledBitmap);
                        currentDialogImagePreview.setVisibility(View.VISIBLE);
                    }
                    if (currentDialogPlaceholder != null) {
                        currentDialogPlaceholder.setVisibility(View.GONE);
                    }
                    if (currentDialogBtnRemove != null) {
                        currentDialogBtnRemove.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Failed to process selected image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
