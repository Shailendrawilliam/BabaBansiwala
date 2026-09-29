package com.bababansiwalanew.Util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bababansiwalanew.KhataBook.dto.KhataCustomer;

public class KhataShareUtil {

    public static void shareCustomerReminder(Context context, KhataCustomer customer, String uName, String uMobile) {
        if (context == null || customer == null) return;

        double balance = 0;
        try {
            String balClean = customer.getBalance() != null ? customer.getBalance().replaceAll("[^0-9.-]", "") : "";
            if (!TextUtils.isEmpty(balClean)) {
                balance = Double.parseDouble(balClean);
            }
        } catch (Exception ignored) {
        }

        String custName = !TextUtils.isEmpty(customer.getName()) ? customer.getName() : "Customer";
        String formattedAmount = String.format("₹%.2f", Math.abs(balance));

        StringBuilder message = new StringBuilder();
        String shopName = !TextUtils.isEmpty(uName) ? uName : "बाबा बंशी वाला";
        String shopMobile = !TextUtils.isEmpty(uMobile) ? uMobile : "";

        if (!TextUtils.isEmpty(shopMobile)) {
            message.append(shopName).append(" (").append(shopMobile).append(")\n\n");
        } else {
            message.append(shopName).append("\n\n");
        }

        if (balance > 0) {
            // Customer owes money to store
            message.append("Payment Due Reminder!\n");
            message.append(formattedAmount).append("\n\n");
            message.append("Dear ").append(custName).append(", your balance of ").append(formattedAmount)
                    .append(" is Due. Please pay at the earliest.\n\n");
        } else if (balance < 0) {
            // Store owes money to customer
            message.append("Account Balance Summary\n");
            message.append(formattedAmount).append("\n\n");
            message.append("Dear ").append(custName).append(", your current balance is ").append(formattedAmount)
                    .append(" (You'll Give).\n\n");
        } else {
            // Settled
            message.append("Account Balance Settled\n\n");
            message.append("Dear ").append(custName).append(", your account balance is completely settled (₹0.00).\n\n");
        }

        message.append("https://play.google.com/store/apps/details?id=").append(context.getPackageName());
        if (!TextUtils.isEmpty(shopMobile)) {
            message.append(" (").append(shopMobile).append(")");
        }

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Khata Payment Reminder");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message.toString());
        context.startActivity(Intent.createChooser(shareIntent, "Share Khata Statement via"));
    }
}
