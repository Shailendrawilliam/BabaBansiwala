package com.bababansiwalanew.KhataBook.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bababansiwalanew.KhataBook.dto.KhataCustomer;
import com.bababansiwalanew.R;

import java.util.ArrayList;
import java.util.List;

public class KhataCustomerAdapter extends RecyclerView.Adapter<KhataCustomerAdapter.CustomerViewHolder> implements Filterable {

    private final Context context;
    private List<KhataCustomer> originalList;
    private List<KhataCustomer> filteredList;
    private final OnCustomerClickListener listener;
    private final OnDeleteClickListener deleteListener;

    public interface OnCustomerClickListener {
        void onCustomerClick(KhataCustomer customer);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(KhataCustomer customer, int position);
    }

    public KhataCustomerAdapter(Context context, List<KhataCustomer> customerList,
                                 OnCustomerClickListener listener,
                                 OnDeleteClickListener deleteListener) {
        this.context = context;
        this.originalList = customerList != null ? customerList : new ArrayList<>();
        this.filteredList = new ArrayList<>(this.originalList);
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    public void updateList(List<KhataCustomer> newList) {
        this.originalList = newList != null ? newList : new ArrayList<>();
        this.filteredList = new ArrayList<>(this.originalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khatabook_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        KhataCustomer customer = filteredList.get(position);

        String name = customer.getName();
        if (TextUtils.isEmpty(name)) {
            name = "Customer " + customer.getCustomerId();
        }
        holder.tvCustomerName.setText(name);

        // Initials avatar
        holder.tvCustomerInitials.setText(getInitials(name));

        // Mobile
        String mobile = customer.getMobile();
        if (!TextUtils.isEmpty(mobile)) {
            holder.tvCustomerMobile.setText("+91 " + mobile);
            holder.btnCallCustomer.setVisibility(View.VISIBLE);
        } else {
            holder.tvCustomerMobile.setText("No mobile number");
            holder.btnCallCustomer.setVisibility(View.GONE);
        }

        // Address
        String address = customer.getAddress();
        if (!TextUtils.isEmpty(address)) {
            holder.tvCustomerAddress.setText(address);
            holder.tvCustomerAddress.setVisibility(View.VISIBLE);
        } else {
            holder.tvCustomerAddress.setVisibility(View.GONE);
        }

        // Balance & Status
        double balance = 0;
        try {
            String balStr = customer.getBalance().replaceAll("[^0-9.-]", "");
            if (!TextUtils.isEmpty(balStr)) {
                balance = Double.parseDouble(balStr);
            }
        } catch (Exception ignored) {
        }

        holder.tvCustomerBalance.setText(String.format("₹ %.2f", Math.abs(balance)));

        if (balance > 0) {
            holder.tvCustomerBalance.setTextColor(Color.parseColor("#2E7D32"));
            holder.tvBalanceStatus.setText("You'll Get");
            holder.tvBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_credit);
            holder.tvBalanceStatus.setTextColor(Color.parseColor("#2E7D32"));
        } else if (balance < 0) {
            holder.tvCustomerBalance.setTextColor(Color.parseColor("#C62828"));
            holder.tvBalanceStatus.setText("You'll Give");
            holder.tvBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_debit);
            holder.tvBalanceStatus.setTextColor(Color.parseColor("#C62828"));
        } else {
            holder.tvCustomerBalance.setTextColor(Color.parseColor("#757575"));
            holder.tvBalanceStatus.setText("Settled");
            holder.tvBalanceStatus.setBackgroundResource(R.drawable.bg_khata_chip_credit);
            holder.tvBalanceStatus.setTextColor(Color.parseColor("#757575"));
        }

        // Item click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCustomerClick(customer);
            }
        });

        // Call button
        holder.btnCallCustomer.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(customer.getMobile())) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + customer.getMobile()));
                    context.startActivity(callIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Cannot open dialer", Toast.LENGTH_SHORT).show();
                }
            }});
        // Share button
        holder.btnShareCustomer.setOnClickListener(v -> {
            android.content.SharedPreferences myPrefs = context.getSharedPreferences(
                    com.bababansiwalanew.Util.ApplicationConstant.INSTANCE.prefNameLoginPref, Context.MODE_PRIVATE);
            String uName = myPrefs.getString(com.bababansiwalanew.Util.ApplicationConstant.INSTANCE.UName, "बाबा बंशी वाला");
            String uMobile = myPrefs.getString(com.bababansiwalanew.Util.ApplicationConstant.INSTANCE.UMobile, "");
            com.bababansiwalanew.Util.KhataShareUtil.shareCustomerReminder(context, customer, uName, uMobile);
        });

        // Delete button
        holder.btnDeleteCustomer.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(customer, holder.getAdapterPosition());
            }
        });
    }

    private String getInitials(String name) {
        if (TextUtils.isEmpty(name)) return "C";
        String[] parts = name.trim().split("\\s+");
        if (parts.length >= 2 && parts[0].length() > 0 && parts[1].length() > 0) {
            return (parts[0].substring(0, 1) + parts[1].substring(0, 1)).toUpperCase();
        } else if (parts.length >= 1 && parts[0].length() > 0) {
            return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        }
        return "C";
    }

    @Override
    public int getItemCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint != null ? constraint.toString().toLowerCase().trim() : "";
                List<KhataCustomer> resultsList = new ArrayList<>();

                if (query.isEmpty()) {
                    resultsList.addAll(originalList);
                } else {
                    for (KhataCustomer item : originalList) {
                        if (item.getName().toLowerCase().contains(query) ||
                                item.getMobile().toLowerCase().contains(query) ||
                                item.getAddress().toLowerCase().contains(query)) {
                            resultsList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = resultsList;
                results.count = resultsList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<KhataCustomer>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView tvCustomerInitials, tvCustomerName, tvCustomerMobile, tvCustomerAddress, tvCustomerBalance, tvBalanceStatus;
        ImageView btnCallCustomer, btnShareCustomer, btnDeleteCustomer;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerInitials = itemView.findViewById(R.id.tvCustomerInitials);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvCustomerMobile = itemView.findViewById(R.id.tvCustomerMobile);
            tvCustomerAddress = itemView.findViewById(R.id.tvCustomerAddress);
            tvCustomerBalance = itemView.findViewById(R.id.tvCustomerBalance);
            tvBalanceStatus = itemView.findViewById(R.id.tvBalanceStatus);
            btnCallCustomer = itemView.findViewById(R.id.btnCallCustomer);
            btnShareCustomer = itemView.findViewById(R.id.btnShareCustomer);
            btnDeleteCustomer = itemView.findViewById(R.id.btnDeleteCustomer);
        }
    }
}
