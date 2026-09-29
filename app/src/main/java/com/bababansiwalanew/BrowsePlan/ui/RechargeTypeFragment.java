package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.Records;
import com.bababansiwalanew.Util.ResponseMobilePlan;
import com.bababansiwalanew.Util.ThreeGFourG;

import java.util.ArrayList;

/**
 * Created by Lalit on 24-02-2017.
 */

public class RechargeTypeFragment extends Fragment {

        RecyclerView recycler_view;
        TextView noData;
        ArrayList<String> rechargeType = new ArrayList<>();
        ResponseMobilePlan responsePlan = new ResponseMobilePlan();
        Records operatorDetails = new Records();
        ArrayList<ThreeGFourG>  operatorDetailshow = new ArrayList<  ThreeGFourG> ();
        String response = "";
        RechargeTypeAdapter mAdapter;

        public void RechargeTypeFragment(String type) {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.recharge_plan_fragment, container, false);

            String re_type = getArguments().getString("type");
            response = getArguments().getString("response");

            recycler_view = (RecyclerView) v.findViewById(R.id.recycler_view);
            noData = (TextView) v.findViewById(R.id.noData);

            Gson gson = new Gson();
            responsePlan = gson.fromJson(response, ResponseMobilePlan.class);
            operatorDetails = responsePlan.getData().getRecords();

            Log.e("re_type",""+ re_type);

            if (re_type.equals("Combo"))
            {
                operatorDetailshow.addAll(operatorDetails.getCOMBO());

            }
            else if (re_type.equals("TOPUP")){ operatorDetailshow.addAll(operatorDetails.getTOPUP()); }
            else if (re_type.equals("Roaming")){ operatorDetailshow.addAll(operatorDetails.getRoaming()); }
            else if (re_type.equals("SMS")){ operatorDetailshow.addAll(operatorDetails.getSMS()); }
            else if (re_type.equals("2G")){ operatorDetailshow.addAll(operatorDetails.getTwoG()); }
            else if (re_type.equals("RateCutter")){ operatorDetailshow.addAll(operatorDetails.getRateCutter()); }
            else if (re_type.equals("3G4G")){ operatorDetailshow.addAll(operatorDetails.getThreeGFourG()); }
            else if (re_type.equals("FullTT")){ operatorDetailshow.addAll(operatorDetails.getFullTT()); }

            if (operatorDetailshow != null && operatorDetailshow.size() > 0) {
                noData.setVisibility(View.GONE);

                mAdapter = new RechargeTypeAdapter(operatorDetailshow, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recycler_view.setLayoutManager(mLayoutManager);
                recycler_view.setItemAnimator(new DefaultItemAnimator());
                recycler_view.setAdapter(mAdapter);

            } else {
                noData.setVisibility(View.VISIBLE);
            }





            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
        }


    }