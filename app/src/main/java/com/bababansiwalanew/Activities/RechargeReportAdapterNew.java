package com.bababansiwalanew.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;

import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bababansiwalanew.R;
import com.bababansiwalanew.RechargeReport.dto.RechargeStatus;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RechargeReportAdapterNew extends RecyclerView.Adapter<RechargeReportAdapterNew.MyViewHolder>{


    private ArrayList<RechargeStatus> transactionsList;
    private Context mContext;
    int resourceId = 0;
    ProgressDialog loader;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView balance;
        public AppCompatTextView txn;
        public AppCompatTextView amount;
        public AppCompatTextView date;
        public AppCompatImageView operatorImage;
        public AppCompatTextView operatorId;
        public AppCompatTextView message;
        public AppCompatTextView status;
        public AppCompatTextView disputeText;
        public AppCompatButton actionButton, disputeButton;
        AppCompatImageView   printButton;
        public ImageView statusImg;
        public RelativeLayout disputeTextContainer;

        public MyViewHolder(View view) {
            super(view);
            txn = (AppCompatTextView) view.findViewById(R.id.txn);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            amount = (AppCompatTextView) view.findViewById(R.id.amount);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            operatorId = (AppCompatTextView) view.findViewById(R.id.operatorId);
            message = (AppCompatTextView) view.findViewById(R.id.message);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            disputeText = (AppCompatTextView) view.findViewById(R.id.disputeText);
            printButton = (AppCompatImageView) view.findViewById(R.id.printButton);
            statusImg = (ImageView) view.findViewById(R.id.statusImg);
            operatorImage = (AppCompatImageView) view.findViewById(R.id.operatorImage);
            actionButton = (AppCompatButton) view.findViewById(R.id.actionButton);
            disputeButton = (AppCompatButton) view.findViewById(R.id.disputeButton);
            disputeTextContainer = (RelativeLayout) view.findViewById(R.id.disputeTextContainer);
        }
    }


    public RechargeReportAdapterNew(ArrayList<RechargeStatus> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public RechargeReportAdapterNew.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recharge_report_adapter_new, parent, false);

        return new RechargeReportAdapterNew.MyViewHolder(itemView);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(final RechargeReportAdapterNew.MyViewHolder holder, int position) {
        final RechargeStatus operator = transactionsList.get(position);
        holder.txn.setText("" + operator.getTID());
        holder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmt());
        holder.amount.setText(" " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
        holder.date.setText(operator.getRDate());
        if (operator.getOpID()!=null  && !operator.getOpID().isEmpty()){
            holder.operatorId.setText("" + operator.getOpID());
        }
        else {
            holder.operatorId.setText("");

        }
        holder.message.setText("Recharge of " + operator.getOpName() + " ,No. " + operator.getRechargeNo());

        String status = "";
        if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
            status = "Success";
            holder.printButton.setVisibility(View.VISIBLE);
            holder.statusImg.setBackgroundResource(R.drawable.ic_status_success);
        } else if (operator.getStatus().equalsIgnoreCase("FAILED")) {
            status = "Failed";
            holder.printButton.setVisibility(View.GONE);
            holder.statusImg.setBackgroundResource(R.drawable.ic_status_failed);
        } else if (operator.getStatus().equalsIgnoreCase("REFUND")) {
            status = "Refund";
            holder.printButton.setVisibility(View.GONE);
            holder.statusImg.setBackgroundResource(R.drawable.ic_status_refund);
        } else if (operator.getStatus().equalsIgnoreCase("PENDING") || operator.getStatus().equalsIgnoreCase("REQUEST SEND")
                || operator.getStatus().equalsIgnoreCase("REQUEST SENT")) {
            status = "Pending";
            holder.printButton.setVisibility(View.GONE);
            holder.statusImg.setBackgroundResource(R.drawable.ic_status_pending);
        } else {
            status = "other";
            holder.statusImg.setBackground(null);
        }
        holder.status.setText("" + status);
        //////////////////////////////////////////////////////////////////////////////
        if (operator.getIsDisputable().equalsIgnoreCase("1")) {
            if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
                holder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                        (mContext.getResources().getColor(R.color.buttoncolor)));
                holder.disputeTextContainer.setVisibility(View.GONE);
                holder.disputeButton.setVisibility(View.VISIBLE);
                holder.disputeButton.setClickable(true);
            } else if (operator.getStatus().equalsIgnoreCase("Failed")) {
                holder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                        (mContext.getResources().getColor(R.color.grey_600)));
                holder.disputeTextContainer.setVisibility(View.GONE);
                holder.disputeButton.setVisibility(View.GONE);
            }
        } else if (operator.getIsDisputable().equalsIgnoreCase("0")) {
            if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
                holder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                        (mContext.getResources().getColor(R.color.grey_600)));
                holder.disputeText.setText("" + operator.getIsDisputable());
                holder.disputeTextContainer.setVisibility(View.VISIBLE);
                holder.disputeButton.setVisibility(View.GONE);
            } else {
                holder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                        (mContext.getResources().getColor(R.color.grey_600)));
                holder.disputeTextContainer.setVisibility(View.GONE);
                holder.disputeButton.setVisibility(View.GONE);
            }
        }
        //////////////////////////////////////////////////////////////////////////////
        /*if (operator.getStatus().equalsIgnoreCase("2") || operator.getStatus().equalsIgnoreCase("4")) {
            holder.actionButton.setSupportBackgroundTintList(ColorStateList.valueOf
                    (mContext.getResources().getColor(R.color.buttoncolor)));
            holder.actionButton.setText("Repeat");
            holder.actionButton.setVisibility(View.VISIBLE);
            holder.actionButton.setClickable(true);
        } else if (operator.getStatus().equalsIgnoreCase("0")) {
            holder.actionButton.setSupportBackgroundTintList(ColorStateList.valueOf
                    (mContext.getResources().getColor(R.color.buttoncolor)));
            holder.actionButton.setText("Retry");
            holder.actionButton.setVisibility(View.VISIBLE);
            holder.actionButton.setClickable(false);
        } else {
            holder.actionButton.setVisibility(View.GONE);
        }*/
        holder.actionButton.setVisibility(View.GONE);

        holder.actionButton.setVisibility(View.GONE);

        if (operator.getColumn1() != null && operator.getColumn1().toString().length() > 0) {


            Picasso.with(mContext).load(ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1()) .into(
                    (holder.operatorImage) );
        } else {
            holder.operatorImage.setImageResource(R.drawable.ic_operator_default_icon);
        }

        holder.printButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String shareBody = "Mobile Number : " + operator.getRechargeNo() + "\n" +
                        "Operator Name :" + operator.getOpName() + "\n" +
                        "Amount :" + operator.getAmount() + "\n" +
                        "Date :" + operator.getRDate() + "\n" +
                        "Tx.ID :" + operator.getTID() + "\n" +
                        "Live.ID :" + operator.getMemberId() + "\n" +
                        "Recharge Status :" + operator.getStatus();

                saveandprint(shareBody);
            }
        });

        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
//                    UtilMethods.INSTANCE.rechargeParams(mContext, operator.getRechargeNo(), operator.getAmount(), operator.getOpID(),
//                            "", null);
//                } else {
//                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
//                            mContext.getResources().getString(R.string.network_error_message), 2);
//                }

            }
        });

        holder.disputeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dispute_popup, null);

                final TextInputLayout remarkLayout = (TextInputLayout) view.findViewById(R.id.remarkLayout);
                final EditText remark = (EditText) view.findViewById(R.id.remark);

                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

                final Dialog dialog = new Dialog(mContext);

                dialog.setTitle("Dispute Remark");
                dialog.setCancelable(false);
                dialog.setContentView(view);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String remarkText;
                        if (remark.getText() != null && remark.getText().toString().trim().length() > 0) {
                            remarkText = remark.getText().toString().trim();
                        } else {
                            remarkText = "";
                        }

                        if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
                            dialog.dismiss();
                            UtilMethods.INSTANCE.Dispute(mContext, operator.getID(), remarkText);
                        } else {
                            UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                                    mContext.getResources().getString(R.string.network_error_message), 2);
                        }

                    }
                });
                dialog.show();

            }
        });
    }


    private void saveandprint(String string) {
        MyPrintDocumentAdapter pd = new MyPrintDocumentAdapter(mContext, string);
        pd.printDocument(string);
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
