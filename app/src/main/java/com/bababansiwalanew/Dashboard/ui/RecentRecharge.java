package com.bababansiwalanew.Dashboard.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.RechargeReport.dto.RechargeReportResponse;
import com.bababansiwalanew.RechargeReport.dto.RechargeStatus;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Lalit on 24-05-2017.
 */

public class RecentRecharge extends Fragment implements View.OnClickListener {

    ProgressDialog mProgressDialog = null;
    RecyclerView recycler_view;
    RecentRechargeAdapter mAdapter;
    TextView noData;
    String response = "";
    LinearLayoutManager mLayoutManager;
    ArrayList<RechargeStatus> transactionsObjects = new ArrayList<>();
    RechargeReportResponse transactions = new RechargeReportResponse();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.recent_recharge_report, container, false);

        mProgressDialog = new ProgressDialog(getActivity());

        recycler_view = (RecyclerView) v.findViewById(R.id.recycler_view);
        noData = (TextView) v.findViewById(R.id.noData);
        //refresh = (TextView) v.findViewById(R.id.refresh);
        mProgressDialog = new ProgressDialog(getActivity());

        recycler_view.setVisibility(View.VISIBLE);

        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            GetUpdate();
        }
    }

    public void dataParse(String response) {
        Gson gson = new Gson();
        transactions = gson.fromJson(response, RechargeReportResponse.class);
        transactionsObjects = transactions.getRechargeStatus();

        if (transactionsObjects.size() > 0) {
            mAdapter = new RecentRechargeAdapter(transactionsObjects, getActivity());
            mLayoutManager = new LinearLayoutManager(getActivity());
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);

            noData.setVisibility(View.GONE);
            recycler_view.setVisibility(View.VISIBLE);
        } else {
            noData.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        }
    }

    @Subscribe
    public void onFragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("recentReport")) {
            dataParse(activityFragmentMessage.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }

    public void GetUpdate() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();

            UtilMethods.INSTANCE.RecentRecharges(getActivity(), "1","", "", mProgressDialog, "all");

        } else {
            UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }

    @Override
    public void onClick(View v) {
      /*  if (v == refresh) {
            if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                ProgressDialog newP = new ProgressDialog(getActivity());
                newP.setTitle("Loading");
                newP.show();
                UtilMethods.INSTANCE.RecentRecharges(getActivity(), "", "", newP, "all");

            } else {
                UtilMethods.INSTANCE.dialogOk(getActivity(), this.getResources().getString(R.string.network_error_title),
                        this.getResources().getString(R.string.network_error_message), 2);
            }

        }*/
    }
}