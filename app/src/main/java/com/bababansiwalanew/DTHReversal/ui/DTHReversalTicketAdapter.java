package com.bababansiwalanew.DTHReversal.ui;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bababansiwalanew.DTHReversal.dto.OpenDetailObject;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class DTHReversalTicketAdapter extends RecyclerView.Adapter<DTHReversalTicketAdapter.MyViewHolder> {

    private ArrayList<OpenDetailObject> transactionsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView status, balance, mode, rightId, createdDate, ticketDate, tid, dthNumber, operatorName;
        public RelativeLayout ticketContainer;
        ProgressDialog mProgressDialog = null;

        public MyViewHolder(View view) {
            super(view);
            status = (AppCompatTextView) view.findViewById(R.id.status);
            balance = (AppCompatTextView) view.findViewById(R.id.balance);
            mode = (AppCompatTextView) view.findViewById(R.id.mode);
            rightId = (AppCompatTextView) view.findViewById(R.id.rightId);
            createdDate = (AppCompatTextView) view.findViewById(R.id.createdDate);
            ticketDate = (AppCompatTextView) view.findViewById(R.id.ticketDate);
            tid = (AppCompatTextView) view.findViewById(R.id.tid);
            dthNumber = (AppCompatTextView) view.findViewById(R.id.dthNumber);
            operatorName = (AppCompatTextView) view.findViewById(R.id.operatorName);

            mProgressDialog = new ProgressDialog(mContext);
            ticketContainer = (RelativeLayout) view.findViewById(R.id.ticketContainer);
        }
    }

    public DTHReversalTicketAdapter(ArrayList<OpenDetailObject> transactionsList, Context mContext) {
        this.transactionsList = transactionsList;
        this.mContext = mContext;
    }

    @Override
    public DTHReversalTicketAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dth_reversal_adapter, parent, false);

        return new DTHReversalTicketAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DTHReversalTicketAdapter.MyViewHolder holder, int position) {
        final OpenDetailObject operator = transactionsList.get(position);

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

        if (operator.getRightRechargeId() != null && operator.getRightRechargeId().toString().trim().length() > 0)
            holder.rightId.setText("" + operator.getRightRechargeId());
        else
            holder.rightId.setText("");

        if (operator.getCreatd_Date() != null && operator.getCreatd_Date().toString().trim().length() > 0)
            holder.createdDate.setText("" + operator.getCreated_Date());
        else
            holder.createdDate.setText("");

        if (operator.getCreatd_Date() != null && operator.getCreatd_Date().toString().trim().length() > 0)
            holder.ticketDate.setText("" + operator.getCreatd_Date());
        else
            holder.ticketDate.setText("");

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

        holder.ticketContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

                    holder.mProgressDialog.setIndeterminate(true);
                    holder.mProgressDialog.setMessage("Loading...");
                    holder.mProgressDialog.show();

                    UtilMethods.INSTANCE.DTH_Ticket(mContext, operator.getID(), operator.getTicketId(), holder.mProgressDialog);

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
