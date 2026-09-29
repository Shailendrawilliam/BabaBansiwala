package com.bababansiwalanew.BrowsePlan.ui;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.Records;
import com.bababansiwalanew.Util.ResponseMobilePlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lalit on 24-02-2017.
 */


    public class BrowsePlanScreen extends AppCompatActivity {

        private TabLayout tabLayout;
        private ViewPager viewPager;
        private Toolbar toolbar;

        public BrowsePlanScreen() {

        }

        ResponseMobilePlan responsePlan = new ResponseMobilePlan();
        ArrayList<String> rechargeType = new ArrayList<>();
        Records operatorDetails = new Records();
        String response = "";
        String operatorName;
        String from;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.browse_plan_layout);

            response = getIntent().getExtras().getString("response");
            operatorName = getIntent().getExtras().getString("operatorName");
            from = getIntent().getExtras().getString("from");

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (from.equals("mob")){
                getSupportActionBar().setTitle("Browse Plan");
            } else  if (from.equals("dth")){
                getSupportActionBar().setTitle("DTH Plan");
            }

            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            viewPager = (ViewPager) findViewById(R.id.viewpager);

            Gson gson = new Gson();
            responsePlan = gson.fromJson(response, ResponseMobilePlan.class);
            operatorDetails = responsePlan.getData().getRecords();

//        for (OperatorDetail operatorDetail : operatorDetails) {
//            String type = operatorDetail.getRechargeType();
//            if (!rechargeType.contains(type))
            if (!from.equals("dth")){
                if (operatorDetails.getCOMBO()!=null && operatorDetails.getCOMBO().size()>0) {
                    rechargeType.add("Combo");
                }   if (operatorDetails.getTOPUP()!=null &&  operatorDetails.getTOPUP().size()>0){
                    rechargeType.add("TOPUP");
                }
                if (operatorDetails.getRoaming()!=null &&  operatorDetails.getRoaming().size()>0){
                    rechargeType.add("Roaming");
                }
                if (operatorDetails.getSMS()!=null &&  operatorDetails.getSMS().size()>0) {
                    rechargeType.add("SMS");
                }  if (operatorDetails.getTwoG()!=null &&  operatorDetails.getTwoG().size()>0){
                    rechargeType.add("2G");
                }if (operatorDetails.getRateCutter()!=null &&  operatorDetails.getRateCutter().size()>0){
                    rechargeType.add("RateCutter");
                }
                if (operatorDetails.getThreeGFourG()!=null &&  operatorDetails.getThreeGFourG().size()>0){
                    rechargeType.add("3G4G");
                }
                if (operatorDetails.getFullTT()!=null &&  operatorDetails.getFullTT().size()>0){
                    rechargeType.add("FullTT");
                }

                setupViewPager(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);



            }
            else {
                if (operatorDetails.getPlan()!=null &&  operatorDetails.getPlan().size()>0){
                    rechargeType.add("Plan");
                }
                if (operatorDetails.getAddOnPack()!=null &&  operatorDetails.getAddOnPack().size()>0){
                    rechargeType.add("AddOnPack");
                }

                setupViewPager1(viewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
            }

            //  }

            // Log.e("here", "size : " + rechargeType.size());


        }

        private void setupViewPager1(ViewPager viewPager) {
            BrowsePlanScreen.ViewPagerAdapter adapter = new BrowsePlanScreen.ViewPagerAdapter(getSupportFragmentManager());

            for (int i = 0; i < rechargeType.size(); i++) {
                RechargeTypeFragmentNew rechargeTypeFragment = new RechargeTypeFragmentNew();
                Bundle arg = new Bundle();
                arg.putString("type", rechargeType.get(i).toString());
                arg.putString("response", response);
                rechargeTypeFragment.setArguments(arg);

                adapter.addFragment(rechargeTypeFragment, "" + rechargeType.get(i).toString());
            }
            viewPager.setAdapter(adapter);
        }

        private void setupViewPager(ViewPager viewPager) {
            BrowsePlanScreen.ViewPagerAdapter adapter = new BrowsePlanScreen.ViewPagerAdapter(getSupportFragmentManager());

            for (int i = 0; i < rechargeType.size(); i++) {
                RechargeTypeFragment rechargeTypeFragment = new RechargeTypeFragment();
                Bundle arg = new Bundle();
                arg.putString("type", rechargeType.get(i).toString());
                arg.putString("response", response);
                rechargeTypeFragment.setArguments(arg);

                adapter.addFragment(rechargeTypeFragment, "" + rechargeType.get(i).toString());
            }
            viewPager.setAdapter(adapter);
        }

        class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.browseplan_menu, menu);
//
//        if (operatorName != null && operatorName.toString().length() > 0) {
//
//            String imgName = operatorName;
//            String imgTemp = imgName.toLowerCase().replace(" ", "");
//            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
//
//            int resourceId = getResources().getIdentifier(
//                    imgTemp4, "drawable", getPackageName());
//
//          //  if (resourceId != 0)
//                //menu.getItem(0).setIcon(getResources().getDrawable(resourceId));
//           // else
//               // menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
//        } else {
//          ///  menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
//        }
//
//
//        this.invalidateOptionsMenu();
//        return super.onCreateOptionsMenu(menu);
//    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            return super.onOptionsItemSelected(item);
        }

        public void ItemClick(String amount) {
            Intent clickIntent = new Intent();
            clickIntent.putExtra("amount", amount);
            setResult(5, clickIntent);

            //Log.e("here", "coming");
            FragmentActivityMessage activityActivityMessage =
                    new FragmentActivityMessage("" + amount, "planSelected");
            GlobalBus.getBus().post(activityActivityMessage);
            finish();
        }

    }

/*
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    public BrowsePlanScreen() {

    }

    ResponsePlan responsePlan = new ResponsePlan();
    ArrayList<String> rechargeType = new ArrayList<>();
    ArrayList<OperatorDetail> operatorDetails = new ArrayList<>();
    String response = "";
    String operatorName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_plan_layout);

        response = getIntent().getExtras().getString("response");
        operatorName = getIntent().getExtras().getString("operatorName");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Browse Plan");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Gson gson = new Gson();
        responsePlan = gson.fromJson(response, ResponsePlan.class);
        operatorDetails = responsePlan.getOperatorDetail();

        for (OperatorDetail operatorDetail : operatorDetails) {
            String type = operatorDetail.getRechargeType();
            if (!rechargeType.contains(type))
                rechargeType.add(type);
        }

       // Log.e("here", "size : " + rechargeType.size());

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        BrowsePlanScreen.ViewPagerAdapter adapter = new BrowsePlanScreen.ViewPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < rechargeType.size(); i++) {
            RechargeTypeFragment rechargeTypeFragment = new RechargeTypeFragment();
            Bundle arg = new Bundle();
            arg.putString("type", rechargeType.get(i).toString());
            arg.putString("response", response);
            rechargeTypeFragment.setArguments(arg);

            adapter.addFragment(rechargeTypeFragment, "" + rechargeType.get(i).toString());
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.browseplan_menu, menu);
//
//        if (operatorName != null && operatorName.toString().length() > 0) {
//
//            String imgName = operatorName;
//            String imgTemp = imgName.toLowerCase().replace(" ", "");
//            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
//
//            int resourceId = getResources().getIdentifier(
//                    imgTemp4, "drawable", getPackageName());
//
//          //  if (resourceId != 0)
//                //menu.getItem(0).setIcon(getResources().getDrawable(resourceId));
//           // else
//               // menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
//        } else {
//          ///  menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_operator_default_icon));
//        }
//
//
//        this.invalidateOptionsMenu();
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void ItemClick(String amount) {
        Intent clickIntent = new Intent();
        clickIntent.putExtra("amount", amount);
        setResult(5, clickIntent);

        //Log.e("here", "coming");
        FragmentActivityMessage activityActivityMessage =
                new FragmentActivityMessage("" + amount, "planSelected");
        GlobalBus.getBus().post(activityActivityMessage);
        finish();
    }

}
*/
