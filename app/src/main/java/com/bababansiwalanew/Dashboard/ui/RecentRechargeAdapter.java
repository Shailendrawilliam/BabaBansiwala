package com.bababansiwalanew.Dashboard.ui;

import android.app.Dialog;
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
import com.bababansiwalanew.Util.UtilMethods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecentRechargeAdapter extends RecyclerView.Adapter<RecentRechargeAdapter.MyViewHolder> {

    private ArrayList<RechargeStatus> transactionsList;
    private Context mContext;
    int resourceId = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView balance;
        public AppCompatTextView txn;
        public AppCompatTextView recharge;
        public AppCompatTextView amount;
        public AppCompatTextView date;
        public AppCompatImageView operatorImage;
        public AppCompatTextView operatorId;
        public AppCompatTextView message;
        public AppCompatTextView status;
        public AppCompatTextView disputeText;
        public AppCompatButton actionButton, disputeButton;
        public ImageView statusImg;
        public RelativeLayout disputeTextContainer;

        public MyViewHolder(View view) {
            super(view);
            txn = (AppCompatTextView) view.findViewById(R.id.txn);
            recharge = (AppCompatTextView) view.findViewById(R.id.recharge);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            amount = (AppCompatTextView) view.findViewById(R.id.amount);
            date = (AppCompatTextView) view.findViewById(R.id.date);
            message = (AppCompatTextView) view.findViewById(R.id.message);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            disputeText = (AppCompatTextView) view.findViewById(R.id.disputeText);
            statusImg = (ImageView) view.findViewById(R.id.statusImg);
            operatorImage = (AppCompatImageView) view.findViewById(R.id.operatorImage);
            operatorId = (AppCompatTextView) view.findViewById(R.id.operatorId);
            actionButton = (AppCompatButton) view.findViewById(R.id.actionButton);
            disputeButton = (AppCompatButton) view.findViewById(R.id.disputeButton);

            disputeTextContainer = (RelativeLayout) view.findViewById(R.id.disputeTextContainer);
        }
    }

    public RecentRechargeAdapter(ArrayList<RechargeStatus> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_recharge_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final  MyViewHolder myViewHolder, int i) {
        final RechargeStatus operator = transactionsList.get(i);
        myViewHolder.txn.setText("" + operator.getTID());
        myViewHolder.recharge.setText("" + operator.getRechargeNo());
        myViewHolder.balance.setText("" + mContext.getResources().getString(R.string.rupiya) + " " + operator.getBalanceAmt());
        myViewHolder.amount.setText(" " + mContext.getResources().getString(R.string.rupiya) + " " + operator.getAmount());
        myViewHolder.date.setText(operator.getRDate());
        myViewHolder.message.setText("Recharge of " + operator.getOpName() + " ,No. " + operator.getRechargeNo());

        String status = "";
        if(operator.getOpName().equals("APEPDCL") || operator.getOpName().equals("APSPDCL") || operator.getOpName().equals("TSSPDCL"))
        {
            status = "Success";
            myViewHolder.statusImg.setBackgroundResource(R.drawable.ic_status_success);
            if (operator.getIsDisputable().equalsIgnoreCase("1")) {
                myViewHolder.disputeButton.setVisibility(View.GONE);
                myViewHolder.disputeTextContainer.setVisibility(View.VISIBLE);
            }
            else
            {
                myViewHolder.disputeButton.setVisibility(View.GONE);
                myViewHolder.disputeTextContainer.setVisibility(View.GONE);
            }

        }
        else
        {
            if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
                status = "Success";
                myViewHolder.statusImg.setBackgroundResource(R.drawable.ic_status_success);
            } else if (operator.getStatus().equalsIgnoreCase("FAILED")) {
                status = "Failed";
                myViewHolder.statusImg.setBackgroundResource(R.drawable.ic_status_failed);
            } else if (operator.getStatus().equalsIgnoreCase("REFUND")) {
                status = "Refund";
                myViewHolder.statusImg.setBackgroundResource(R.drawable.ic_status_refund);
            } else if (operator.getStatus().equalsIgnoreCase("PENDING") || operator.getStatus().equalsIgnoreCase("REQUEST SEND")
                    || operator.getStatus().equalsIgnoreCase("REQUEST SENT")) {
                status = "Pending";
                myViewHolder.statusImg.setBackgroundResource(R.drawable.ic_status_pending);
            } else {
                status = "other";
                myViewHolder.statusImg.setBackground(null);
            }

            if (operator.getIsDisputable().equalsIgnoreCase("1")) {
                if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
                    myViewHolder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                            (mContext.getResources().getColor(R.color.buttoncolor)));
                    myViewHolder.disputeTextContainer.setVisibility(View.GONE);
                    myViewHolder.disputeButton.setVisibility(View.VISIBLE);
                    myViewHolder.disputeButton.setClickable(true);
                } else if (operator.getStatus().equalsIgnoreCase("Failed")) {
                    myViewHolder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                            (mContext.getResources().getColor(R.color.grey_600)));
                    myViewHolder.disputeTextContainer.setVisibility(View.GONE);
                    myViewHolder.disputeButton.setVisibility(View.GONE);
                }
            } else if (operator.getIsDisputable().equalsIgnoreCase("0")) {
                if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
                    myViewHolder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                            (mContext.getResources().getColor(R.color.grey_600)));
                    myViewHolder.disputeText.setText("" + operator.getIsDisputable());
                    myViewHolder.disputeTextContainer.setVisibility(View.VISIBLE);
                    myViewHolder.disputeButton.setVisibility(View.GONE);
                } else {
                    myViewHolder.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
                            (mContext.getResources().getColor(R.color.grey_600)));
                    myViewHolder.disputeTextContainer.setVisibility(View.GONE);
                    myViewHolder.disputeButton.setVisibility(View.GONE);
                }
            }
        }
        myViewHolder.status.setText("" + status);

        myViewHolder.operatorId.setText(operator.getOpID());

        //////////////////////////////////////////////////////////////////////////////


        myViewHolder.actionButton.setVisibility(View.GONE);

        if (operator.getOpName() != null && operator.getOpName().toString().length() > 0) {

            String imgName = operator.getOpName();
            String imgTemp = imgName.toLowerCase().replace(" ", "");
            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());


            resourceId = mContext.getResources().getIdentifier(
                    imgTemp4, "drawable", mContext.getPackageName());

            Picasso.with(mContext).load(resourceId)./*centerCrop().error(R.drawable.ic_operator_default_icon).*/into(
                    (myViewHolder.operatorImage));
        } else {
            myViewHolder.operatorImage.setImageResource(R.drawable.ic_operator_default_icon);
        }

        myViewHolder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
                    UtilMethods.INSTANCE.rechargeParams(mContext, operator.getRechargeNo(), operator.getAmount(), operator.getOpID(),
                            "", null,myViewHolder.actionButton,"");
                } else {
                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                            mContext.getResources().getString(R.string.network_error_message), 2);
                }

            }
        });

        myViewHolder.disputeButton.setOnClickListener(new View.OnClickListener() {
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
                            UtilMethods.INSTANCE.Dispute(mContext, operator.getID() ,remarkText);
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

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }


}
