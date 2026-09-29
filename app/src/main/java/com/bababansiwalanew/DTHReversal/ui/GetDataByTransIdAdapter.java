package com.bababansiwalanew.DTHReversal.ui;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bababansiwalanew.DTHReversal.dto.DataByTransactionId;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class GetDataByTransIdAdapter extends RecyclerView.Adapter<GetDataByTransIdAdapter.MyViewHolder> {

    private ArrayList<DataByTransactionId> transactionsList;
    private Context mContext;
    private String ticketId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView status, balance, mode, createdDate, tid, dthNumber, operatorName;
        RelativeLayout mainContainer;
        public AppCompatEditText rightId;
        public EditText remark;
        Button submit, approve, reject;
        ProgressDialog mProgressDialog = null;

        public MyViewHolder(View view) {
            super(view);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            mode = (AppCompatTextView) view.findViewById(R.id.mode);
            rightId = (AppCompatEditText) view.findViewById(R.id.rightId);
            createdDate = (AppCompatTextView) view.findViewById(R.id.createdDate);
            tid = (AppCompatTextView) view.findViewById(R.id.tid);
            dthNumber = (AppCompatTextView) view.findViewById(R.id.dthNumber);
            operatorName = (AppCompatTextView) view.findViewById(R.id.operatorName);

            mainContainer = (RelativeLayout) view.findViewById(R.id.mainContainer);
            mProgressDialog = new ProgressDialog(mContext);

            submit = (Button) view.findViewById(R.id.submit);
            approve = (Button) view.findViewById(R.id.approve);
            reject = (Button) view.findViewById(R.id.reject);
            remark = (EditText) view.findViewById(R.id.remark);
        }
    }

    public GetDataByTransIdAdapter(ArrayList<DataByTransactionId> transactionsList, Context mContext, String ticketId) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.ticketId = ticketId;
    }

    @Override
    public GetDataByTransIdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dth_get_data_byid_adapter, parent, false);

        return new GetDataByTransIdAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GetDataByTransIdAdapter.MyViewHolder holder, int position) {
        final DataByTransactionId operator = transactionsList.get(position);

        if (operator.getType() != null && operator.getType().toString().trim().length() > 0)
            holder.status.setText("" + operator.getType());
        else
            holder.status.setText("");

        if (operator.getAmount() != null && operator.getAmount().toString().trim().length() > 0)
            holder.balance.setText("" + operator.getAmount());
        else
            holder.balance.setText("");

        if (operator.getRechargeMode() != null && operator.getRechargeMode().toString().trim().length() > 0)
            holder.mode.setText("" + operator.getRechargeMode());
        else
            holder.mode.setText("");

        if (operator.getTransaction_ID() != null && operator.getTransaction_ID().toString().trim().length() > 0)
            holder.tid.setText("" + operator.getTransaction_ID());
        else
            holder.tid.setText("");

        if (operator.getAmounttransfer_to() != null && operator.getAmounttransfer_to().toString().trim().length() > 0)
            holder.dthNumber.setText("" + operator.getAmounttransfer_to());
        else
            holder.dthNumber.setText("");

        if (operator.getOpraterName() != null && operator.getOpraterName().toString().trim().length() > 0)
            holder.operatorName.setText("" + operator.getOpraterName());
        else
            holder.operatorName.setText("");

        //////////////////////////////////////////////////////////////////////////////////////////
        if (operator.getRightRechargeId() != null && operator.getRightRechargeId().toString().trim().length() > 0) {
            holder.rightId.setText("" + operator.getRightRechargeId());
            holder.rightId.setFocusable(false);

            holder.remark.setVisibility(View.GONE);
            holder.remark.setVisibility(View.GONE);
        } else {
            holder.rightId.setText("");
            holder.rightId.setFocusable(true);

            holder.remark.setVisibility(View.VISIBLE);
            holder.remark.setVisibility(View.VISIBLE);
        }


        if (UtilMethods.INSTANCE.getRoleId(mContext).equalsIgnoreCase("0")) {
            holder.approve.setVisibility(View.VISIBLE);
            holder.reject.setVisibility(View.VISIBLE);
        } else {
            holder.approve.setVisibility(View.GONE);
            holder.reject.setVisibility(View.GONE);
        }
        //////////////////////////////////////////////////////////////////////////////////////////
        holder.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                    holder.mProgressDialog.setIndeterminate(true);
                    holder.mProgressDialog.setMessage("Loading...");
                    holder.mProgressDialog.show();

                    UtilMethods.INSTANCE.DTH_Ticket(mContext, operator.getID(), ticketId, holder.mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                            mContext.getResources().getString(R.string.network_error_message), 2);
                }
            }
        });

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.rightId.getText() != null && holder.rightId.getText().toString().trim().length() > 0) {
                    if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                        holder.mProgressDialog.setIndeterminate(true);
                        holder.mProgressDialog.setMessage("Loading...");
                        holder.mProgressDialog.show();

                        UtilMethods.INSTANCE.DTH_Insert_Ticket(mContext, operator.getID(), holder.rightId.getText().toString().trim(), "", holder.mProgressDialog);

                    } else {
                        UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                                mContext.getResources().getString(R.string.network_error_message), 2);
                    }
                } else {
                    holder.rightId.setError("Please enter a valid Right Id");
                }
            }
        });

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                    holder.mProgressDialog.setIndeterminate(true);
                    holder.mProgressDialog.setMessage("Loading...");
                    holder.mProgressDialog.show();

                    UtilMethods.INSTANCE.DTH_ApproveTicket(mContext, operator.getTransaction_ID(), ticketId, holder.mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                            mContext.getResources().getString(R.string.network_error_message), 2);
                }
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                    holder.mProgressDialog.setIndeterminate(true);
                    holder.mProgressDialog.setMessage("Loading...");
                    holder.mProgressDialog.show();

                    UtilMethods.INSTANCE.DTH_RejectTicket(mContext, operator.getTransaction_ID(), ticketId, holder.mProgressDialog);

                } else {
                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                            mContext.getResources().getString(R.string.network_error_message), 2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }
}