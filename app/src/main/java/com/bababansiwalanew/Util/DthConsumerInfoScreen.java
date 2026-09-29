package com.bababansiwalanew.Util;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.ROffer.dto.ROfferObject;
import com.bababansiwalanew.ROffer.dto.ROfferResponse;

import java.util.ArrayList;
import java.util.List;

public class DthConsumerInfoScreen extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    // Declare CustomLoader.....

    ROfferResponse responsePlan = new ROfferResponse();
    ArrayList<String> rechargeType = new ArrayList<>();
    List<ROfferObject> operatorDetails = new ArrayList<>();
    String response = "";
    String operatorName;
    String OpRefOp = "", CoustomerNumber = "";
    String OpRefCircle = "";
    TextView tel, operator, customerName, planname, NextRechargeDate, Balance;
    TextView MonthlyRecharge;
    LinearLayout ll_RechargeDate, ll_bal_amount, ll_customer_layout, ll_plan_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_info_layout);
         response = getIntent().getExtras().getString("response");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DTH Consumer Info");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tel = (TextView) findViewById(R.id.tel);
        operator = (TextView) findViewById(R.id.operator);
        customerName = (TextView) findViewById(R.id.customerName);
        Balance = (TextView) findViewById(R.id.Balance);
        planname = (TextView) findViewById(R.id.planname);
        NextRechargeDate = (TextView) findViewById(R.id.NextRechargeDate);
        MonthlyRecharge = (TextView) findViewById(R.id.RechargeAmount);
        ll_RechargeDate = (LinearLayout) findViewById(R.id.ll_RechargeDate);
        ll_bal_amount = (LinearLayout) findViewById(R.id.ll_bal_amount);
        ll_customer_layout = (LinearLayout) findViewById(R.id.ll_customer_layout);
        ll_plan_name = (LinearLayout) findViewById(R.id.ll_plan_name);


        // dthPlan(OpRefOp, CoustomerNumber);
        parsedata(response);
    }

    private void parsedata(String response) {
        Gson gson = new Gson();
        responsePlan = gson.fromJson(response, ROfferResponse.class);
        operatorDetails = responsePlan.getRecords();
        tel.setText("Customer Number :" + responsePlan.getTel());
        operator.setText(responsePlan.getOperator());
        try {
            if (!operatorDetails.get(0).getCustomerName().equalsIgnoreCase(""))
                customerName.setText(operatorDetails.get(0).getCustomerName());
            else
                ll_customer_layout.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
            ll_customer_layout.setVisibility(View.GONE);
        }
        try {
            MonthlyRecharge.setText(getResources().getString(R.string.rupiya) + " " +operatorDetails.get(0).getMonthlyRecharge() );
        } catch (Exception e) {
            e.printStackTrace();
            ll_bal_amount.setVisibility(View.GONE);
        }
        try {
            planname.setText(operatorDetails.get(0).getPlanname());
        } catch (Exception e) {
            e.printStackTrace();
            ll_plan_name.setVisibility(View.GONE);
        }
        try {
            NextRechargeDate.setText(operatorDetails.get(0).getNextRechargeDate());
        } catch (Exception e) {
            e.printStackTrace();
            ll_RechargeDate.setVisibility(View.GONE);
        }
        try {
            Balance.setText(getResources().getString(R.string.rupiya)+"   "+ operatorDetails.get(0).getBalance() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        MonthlyRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentActivityMessage activityActivityMessage =
                        new FragmentActivityMessage(" " + operatorDetails.get(0).getMonthlyRecharge(), "dthCustomerPlan");
                GlobalBus.getBus().post(activityActivityMessage);



                DthConsumerInfoScreen.this.finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browseplan_menu, menu);

        if (operatorName != null && operatorName.toString().length() > 0) {

            String imgName = operatorName;
            String imgTemp = imgName.toLowerCase().replace(" ", "");
            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());

            int resourceId = getResources().getIdentifier(
                    imgTemp4, "drawable", getPackageName());

            if (resourceId != 0)
                menu.getItem(0).setIcon(getResources().getDrawable(resourceId));
            else
                menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
        }


        this.invalidateOptionsMenu();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (!EventBus.getDefault().isRegistered(this)) {
//            GlobalBus.getBus().register(this);
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);
    }


}
