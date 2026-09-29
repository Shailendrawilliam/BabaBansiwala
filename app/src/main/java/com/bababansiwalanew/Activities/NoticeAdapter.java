package com.bababansiwalanew.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bababansiwalanew.BalanceCheck.dto.ChildBalance;
import com.bababansiwalanew.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter
extends RecyclerView.Adapter< NoticeAdapter.MyViewHolder> {

private ArrayList<ChildBalance> noticeList;
private Context mContext;
private String   from;
private String   role;
private RecyclerView   subrecyclerview;
        int resourceId = 0;
        String fund ="1";

        ProgressDialog mProgressDialog = null;
public class MyViewHolder extends RecyclerView.ViewHolder {
    public  TextView date;
    public TextView mg;



    public MyViewHolder(View view) {
        super(view);
        mProgressDialog= new ProgressDialog(mContext);

        date =  view.findViewById(R.id.date);
        mg =   view.findViewById(R.id.text);
    }
}

    public  NoticeAdapter(ArrayList<ChildBalance> noticeList, Context mContext  ) {
        this.noticeList = noticeList;
        this.mContext = mContext;

    }

    @Override
    public   MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notice_adapter, parent, false);

        return new   MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ChildBalance operator = noticeList.get(position);


            holder.date.setText("" + operator.getCreateDate());
            holder.mg.setText("" + operator.getMsg());
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent transactionIntent = new Intent(holder.itemView.getContext(), ViewNoticeActivity.class);
                    transactionIntent.putExtra("date", operator.getCreateDate());
                    transactionIntent.putExtra("mg", operator.getMsg());
                    holder.itemView.getContext().startActivity(transactionIntent);
                    //  ViewDownline( operator.getLoginId());

                }
            });
    }




    @Override
    public int getItemCount() {
        return noticeList.size();
    }

   }