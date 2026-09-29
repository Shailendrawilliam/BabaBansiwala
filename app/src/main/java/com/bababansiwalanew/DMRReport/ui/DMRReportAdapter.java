package com.bababansiwalanew.DMRReport.ui;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.DMRReport.dto.DMRTransactions;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

/**
 * Created by Lalit on 18-04-2017.
 */

public class DMRReportAdapter extends RecyclerView.Adapter<DMRReportAdapter.MyViewHolder> {

    private ArrayList<DMRTransactions> transactionsList;
    private Context mContext;
    ProgressDialog mProgressDialog = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, status, senderMobile, outletName, accountNumber, operatorName, operatorId, createdDate, responseDate, action;

        public MyViewHolder(View view) {
            super(view);
            amount = (TextView) view.findViewById(R.id.amount);
            status = (TextView) view.findViewById(R.id.status);
            senderMobile = (TextView) view.findViewById(R.id.senderMobile);
            outletName = (TextView) view.findViewById(R.id.outletName);
            accountNumber = (TextView) view.findViewById(R.id.accountNumber);
            operatorName = (TextView) view.findViewById(R.id.operatorName);
            operatorId = (TextView) view.findViewById(R.id.operatorId);
            createdDate = (TextView) view.findViewById(R.id.createdDate);
            responseDate = (TextView) view.findViewById(R.id.responseDate);
            action = (TextView) view.findViewById(R.id.action);
        }
    }

    public DMRReportAdapter(ArrayList<DMRTransactions> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public DMRReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dmr_report_adapter, parent, false);

        mProgressDialog = new ProgressDialog(mContext);

        return new DMRReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DMRReportAdapter.MyViewHolder holder, int position) {
        final DMRTransactions operator = transactionsList.get(position);

        holder.amount.setText("" + operator.getAmount());
        holder.status.setText("" + operator.getType());
        holder.senderMobile.setText("" + operator.getSenderMobileNo());
        holder.outletName.setText("" + operator.getOutletname());
        holder.accountNumber.setText("" + operator.getAmounttransfer_to());
        holder.operatorName.setText("" + operator.getOpraterName());
        holder.operatorId.setText("" + operator.getOperatorid());
        holder.createdDate.setText("" + operator.getCreatd_Date());
        holder.responseDate.setText("" + operator.getResponse_Time());
        holder.action.setText("Dispute");

        if (operator.getIsDisputable().equalsIgnoreCase("1")) {
            holder.action.setVisibility(View.VISIBLE);
        } else {
            holder.action.setVisibility(View.GONE);
        }
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.show();

                    UtilMethods.INSTANCE.Refund(mContext, operator.getSenderMobileNo(), operator.getVenderID(), operator.getTransaction_ID(), mProgressDialog);
                } else {
                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getString(R.string.network_error_title),
                            mContext.getString(R.string.network_error_message), 2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

}
