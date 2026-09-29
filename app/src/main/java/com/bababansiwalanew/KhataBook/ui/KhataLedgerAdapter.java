package com.bababansiwalanew.KhataBook.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.KhataBook.dto.KhataLedgerItem;
import com.bababansiwalanew.R;

import java.util.ArrayList;
import java.util.List;

public class KhataLedgerAdapter extends RecyclerView.Adapter<KhataLedgerAdapter.LedgerViewHolder> {

    private final Context context;
    private List<KhataLedgerItem> ledgerList;

    public KhataLedgerAdapter(Context context, List<KhataLedgerItem> ledgerList) {
        this.context = context;
        this.ledgerList = ledgerList != null ? ledgerList : new ArrayList<>();
    }

    public void updateList(List<KhataLedgerItem> newList) {
        this.ledgerList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LedgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khatabook_ledger, parent, false);
        return new LedgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerViewHolder holder, int position) {
        KhataLedgerItem item = ledgerList.get(position);

        // Date
        String date = item.getCreatedDate();
        holder.tvEntryDate.setText(!TextUtils.isEmpty(date) ? date : "N/A");

        // Fund type
        String fundType = item.getFundType();
        boolean isCredit = item.isCredit();

        if (isCredit) {
            holder.tvEntryFundType.setText("Credit (Got)");
            holder.tvEntryFundType.setBackgroundResource(R.drawable.bg_khata_chip_credit);
            holder.tvEntryFundType.setTextColor(Color.parseColor("#2E7D32"));

            holder.tvEntryAmount.setTextColor(Color.parseColor("#2E7D32"));
            holder.tvEntryAmount.setText("+ ₹ " + item.getAmount());
        } else {
            holder.tvEntryFundType.setText("Debit (Gave)");
            holder.tvEntryFundType.setBackgroundResource(R.drawable.bg_khata_chip_debit);
            holder.tvEntryFundType.setTextColor(Color.parseColor("#C62828"));

            holder.tvEntryAmount.setTextColor(Color.parseColor("#C62828"));
            holder.tvEntryAmount.setText("- ₹ " + item.getAmount());
        }

        // Remark
        String remark = item.getRemark();
        holder.tvEntryRemark.setText(!TextUtils.isEmpty(remark) ? remark : (isCredit ? "Payment Received" : "Payment Given"));

        // Running balance
        String balance = item.getBalanceAmount();
        if (!TextUtils.isEmpty(balance)) {
            holder.tvRunningBalance.setText("Balance: ₹ " + balance);
            holder.tvRunningBalance.setVisibility(View.VISIBLE);
        } else {
            holder.tvRunningBalance.setVisibility(View.GONE);
        }

        // Image attachment
        String imgStr = item.getImage();
        if (!TextUtils.isEmpty(imgStr)) {
            holder.layoutEntryImage.setVisibility(View.VISIBLE);
            loadTransactionImage(imgStr, holder.ivEntryThumbnail);

            holder.layoutEntryImage.setOnClickListener(v -> showFullImageDialog(imgStr));
        } else {
            holder.layoutEntryImage.setVisibility(View.GONE);
        }
    }

    private void loadTransactionImage(String imgStr, android.widget.ImageView imageView) {
        if (TextUtils.isEmpty(imgStr)) return;

        try {
            if (imgStr.startsWith("http://") || imgStr.startsWith("https://")) {
                com.squareup.picasso.Picasso.with(context).load(imgStr).placeholder(R.drawable.bg_grey_stroke).into(imageView);
            } else if (imgStr.startsWith("/") || imgStr.contains("Uploads") || imgStr.contains("Image")) {
                String fullUrl = com.bababansiwalanew.Util.ApplicationConstant.INSTANCE.baseUrl + (imgStr.startsWith("/") ? imgStr.substring(1) : imgStr);
                com.squareup.picasso.Picasso.with(context).load(fullUrl).placeholder(R.drawable.bg_grey_stroke).into(imageView);
            } else {
                // Assume Base64 string
                byte[] decodedBytes = android.util.Base64.decode(imgStr, android.util.Base64.DEFAULT);
                android.graphics.Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    imageView.setImageResource(R.drawable.bg_grey_stroke);
                }
            }
        } catch (Exception e) {
            imageView.setImageResource(R.drawable.bg_grey_stroke);
        }
    }

    private void showFullImageDialog(String imgStr) {
        if (context == null || TextUtils.isEmpty(imgStr)) return;

        android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_full_image_preview);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        ImageView ivFullImage = dialog.findViewById(R.id.ivFullImagePreview);
        ImageView btnClose = dialog.findViewById(R.id.btnCloseFullImage);

        loadTransactionImage(imgStr, ivFullImage);

        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dialog.dismiss());
        }

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return ledgerList != null ? ledgerList.size() : 0;
    }

    public static class LedgerViewHolder extends RecyclerView.ViewHolder {
        TextView tvEntryDate, tvEntryFundType, tvEntryRemark, tvRunningBalance, tvEntryAmount, tvViewReceipt;
        ImageView ivEntryThumbnail;
        View layoutEntryImage;

        public LedgerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEntryDate = itemView.findViewById(R.id.tvEntryDate);
            tvEntryFundType = itemView.findViewById(R.id.tvEntryFundType);
            tvEntryRemark = itemView.findViewById(R.id.tvEntryRemark);
            tvRunningBalance = itemView.findViewById(R.id.tvRunningBalance);
            tvEntryAmount = itemView.findViewById(R.id.tvEntryAmount);
            tvViewReceipt = itemView.findViewById(R.id.tvViewReceipt);
            ivEntryThumbnail = itemView.findViewById(R.id.ivEntryThumbnail);
            layoutEntryImage = itemView.findViewById(R.id.layoutEntryImage);
        }
    }
}
