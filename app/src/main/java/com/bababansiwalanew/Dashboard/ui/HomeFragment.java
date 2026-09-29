package com.bababansiwalanew.Dashboard.ui;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bababansiwalanew.Activities.PaymentRequest;
import com.bababansiwalanew.AddMoney.AddMoneyActivity;
import com.bababansiwalanew.BalanceCheck.dto.BalanceCheckResponse;
 import com.bababansiwalanew.DMR.ui.DMRActivity;
import com.bababansiwalanew.FundRecReport.ui.FundRecReport;
import com.bababansiwalanew.Login.dto.LoginData;
import com.bababansiwalanew.Login.dto.LoginResponse;
import com.bababansiwalanew.Login.ui.SignupScreen;
import com.bababansiwalanew.KhataBook.ui.KhataBookActivity;
import com.bababansiwalanew.R;
import com.bababansiwalanew.RechargeReport.ui.RechargeReport;
import com.bababansiwalanew.Util.ActivityActivityMessage;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.BankDetail;
import com.bababansiwalanew.Util.ui.ListScreen;
import com.google.gson.Gson;
import com.shopping.OrderHistory;
import com.shopping.ShoppingFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public ArrayList<LoginData> imageList = new ArrayList<>();
    LinearLayout dotsCount,llMoneyTransfer,ll_postpaid1, ll_order_history1,recyclerlinearlayout, userSaleReportlayout;
    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    Handler handler;public static TextView mDotsText[];
    Integer mDotsCount;
     EditText stdCode, number, operator, amount, bankFund, transactionId;
    AppCompatButton payButton;

     ImageView  iv_createUser,specific_report, insurance, ledger_report, fund_recieve_report, user_day_book_report, commission_report,

    iv_fundtranfer, iv_addmoney, iv_recharge_report,  iv_dth, iv_postpaid, iv_prepaid;

    LinearLayout iv_addmoneyNew,balancePrepaidLayout,createUser, balanceUtilityLayout,
            llfundtransfer, ll_profile,ll_order_history ,ll_support_frag, llKhataBook;

    int operatorSelectedId;
    String operatorSelected;
     TextView tvPrepaid;
    TextView tvPostpaid;
    ImageView ivRefresh;
     String part2 = "";
    String part3 = "";
         TextView balancePrepaid, tvbalance;
    ArrayList<BankDetail> bankDetails = new ArrayList<>();
    boolean flagElectricity = false;
    String paramValue1 = "";
    String paramValue2 = "";
    String paramValue3 = "";
    String paramValue4 = "";
    String ROffer = "";
      TextView tvROffers,     today, month, last_month, target_done, target_remaining;
    SwipeRefreshLayout pullToRefresh;
    LinearLayout ll_shopping,ll_rtshopping,llelectricity, ll_recharge,ll_iv_recharge_report, ll_share, ll_support,ll_fundTrans,cs3,cs2,cs1,ll_landline,ll_insurance;
     private ProgressDialog mProgressDialog = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  View rootView = inflater.inflate(R.layout.activity_dashboard_new, container, false);
        View rootView = inflater.inflate(R.layout.new_dashboard, container, false);
        geTId(rootView);
        HitApi();
        VideoGalleryApi();

        return rootView;
    }

    public void VideoGalleryApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {
            try {
                UtilMethods.INSTANCE.VideoGallery(getActivity(), new UtilMethods.ApiCallBack() {
                    @Override
                    public void onSucess(Object object) {
                        LoginResponse bannerResponse = (LoginResponse) object;
                        if (bannerResponse != null) {
                            imageList = bannerResponse.getList();
                            if (imageList != null && imageList.size() > 0) {
                                mCustomPagerAdapter = new CustomPagerAdapter(imageList, getActivity());
                                mViewPager.setAdapter(mCustomPagerAdapter);
                                mViewPager.setOffscreenPageLimit(mCustomPagerAdapter.getCount());
                                mDotsCount = mViewPager.getAdapter().getCount();
                                mDotsText = new TextView[mDotsCount];
                                postDelayedScrollNext();
                            }

                        }

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void postDelayedScrollNext() {
        handler.postDelayed(new Runnable() {
            public void run() {


                if (mViewPager.getAdapter() != null) {
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                        mViewPager.setCurrentItem(0);
                        postDelayedScrollNext();
                        return;
                    }
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    // postDelayedScrollNext(position+1);
                    postDelayedScrollNext();
                }

                // onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
            }


        }, 8000);

    }

    private void geTId(View rootView) {
         mProgressDialog = new ProgressDialog(getActivity());
        handler = new Handler();
         tvPrepaid = rootView.findViewById(R.id.tvPrepaid);
        ll_order_history1 = rootView.findViewById(R.id.ll_order_history1);
        ll_shopping = rootView.findViewById(R.id.ll_shopping);
        ll_rtshopping = rootView.findViewById(R.id.ll_rtshopping);
        ll_order_history = rootView.findViewById(R.id.ll_order_history);
        createUser = rootView.findViewById(R.id.createUser);
        iv_createUser = rootView.findViewById(R.id.iv_createUser);
        ll_recharge = rootView.findViewById(R.id.ll_recharge);
        tvbalance = rootView.findViewById(R.id.tvbalance);
        insurance = rootView.findViewById(R.id.insurance);
        tvPostpaid = rootView.findViewById(R.id.tvPostpaid);
        ivRefresh = rootView.findViewById(R.id.ivRefresh);
        llelectricity = rootView.findViewById(R.id.llelectricity);
        ll_landline = rootView.findViewById(R.id.ll_landline);
        ll_insurance = rootView.findViewById(R.id.ll_insurance);
        cs1 = rootView.findViewById(R.id.cs1);
        cs2 = rootView.findViewById(R.id.cs2);
        cs3 = rootView.findViewById(R.id.cs3);
        dotsCount = (LinearLayout) rootView.findViewById(R.id.image_count);
        ll_iv_recharge_report = (LinearLayout) rootView.findViewById(R.id.ll_iv_recharge_report);
        recyclerlinearlayout = (LinearLayout) rootView.findViewById(R.id.recyclerlinearlayout);
     //   ll_logout11 = (LinearLayout) rootView.findViewById(R.id.ll_logout11);
        mViewPager = rootView.findViewById(R.id.pager);
        llMoneyTransfer = rootView.findViewById(R.id.llMoneyTransfer);
        ll_postpaid1 = rootView.findViewById(R.id.ll_postpaid1);
        specific_report = rootView.findViewById(R.id.specificReport);
        ledger_report = rootView.findViewById(R.id.ledgerReport);
        fund_recieve_report = rootView.findViewById(R.id.fundRecReport);
        userSaleReportlayout = rootView.findViewById(R.id.userSaleReportlayout);
        pullToRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.pullToRefresh);
        user_day_book_report = rootView.findViewById(R.id.userDayBook);
        commission_report = rootView.findViewById(R.id.commisionSlab);

        iv_addmoneyNew = rootView.findViewById(R.id.iv_addmoneyNew);

        llfundtransfer = rootView.findViewById(R.id.ll_exchange_money);
        ll_profile = rootView.findViewById(R.id.ll_profile);
        ll_share = rootView.findViewById(R.id.ll_share);
        ll_support = rootView.findViewById(R.id.ll_support);
        ll_fundTrans = rootView.findViewById(R.id.ll_fundTrans);
        iv_fundtranfer = rootView.findViewById(R.id.iv_fundtranfer);
        iv_addmoney = rootView.findViewById(R.id.iv_addmoney);
        iv_recharge_report = rootView.findViewById(R.id.iv_recharge_report);
        iv_dth = rootView.findViewById(R.id.iv_dth);
        iv_postpaid = rootView.findViewById(R.id.iv_postpaid);
        iv_prepaid = rootView.findViewById(R.id.iv_prepaid);
        month = rootView.findViewById(R.id.month);
        today = rootView.findViewById(R.id.today);
        target_done = rootView.findViewById(R.id.target);
        target_remaining = rootView.findViewById(R.id.remaining);
        last_month = rootView.findViewById(R.id.lastmonth);
        balancePrepaid = (TextView) rootView.findViewById(R.id.balancePrepaid);
        balancePrepaid = rootView.findViewById(R.id.balancePrepaid);
        ll_support_frag = rootView.findViewById(R.id.ll_support_frag);
       // ivSupport = rootView.findViewById(R.id.ivSupport);

        balancePrepaidLayout = (LinearLayout) rootView.findViewById(R.id.balancePrepaidLayout);
        balanceUtilityLayout = (LinearLayout) rootView.findViewById(R.id.balanceUtilityLayout);
        specific_report.setOnClickListener(this);
        ll_order_history1.setOnClickListener(this);
        ledger_report.setOnClickListener(this);
        fund_recieve_report.setOnClickListener(this);
        llMoneyTransfer.setOnClickListener(this);
        ll_postpaid1.setOnClickListener(this);
        user_day_book_report.setOnClickListener(this);
        commission_report.setOnClickListener(this);
       // ll_logout11.setOnClickListener(this);
        ivRefresh.setOnClickListener(this);
        llfundtransfer.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        iv_createUser.setOnClickListener(this);
       // ivSupport.setOnClickListener(this);
        ll_support.setOnClickListener(this);
        ll_fundTrans.setOnClickListener(this);
        iv_addmoneyNew.setOnClickListener(this);
        ll_shopping.setOnClickListener(this);
        ll_rtshopping.setOnClickListener(this);
        ll_order_history.setOnClickListener(this);

        llKhataBook = rootView.findViewById(R.id.llKhataBook);
        if (llKhataBook != null) {
            llKhataBook.setOnClickListener(this);
        }

        iv_fundtranfer.setOnClickListener((View.OnClickListener) this);
        iv_addmoney.setOnClickListener((View.OnClickListener) this);
        iv_recharge_report.setOnClickListener((View.OnClickListener) this);
        iv_dth.setOnClickListener((View.OnClickListener) this);
        iv_postpaid.setOnClickListener((View.OnClickListener) this);
        ll_landline.setOnClickListener((View.OnClickListener) this);
        ll_insurance.setOnClickListener((View.OnClickListener) this);
        iv_prepaid.setOnClickListener((View.OnClickListener) this);
        insurance.setOnClickListener((View.OnClickListener) this);
        ll_support_frag.setOnClickListener((View.OnClickListener) this);

        if (UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("3")) {
            ll_iv_recharge_report.setVisibility(View.GONE);
            llfundtransfer.setVisibility(View.GONE);
            userSaleReportlayout.setVisibility(View.VISIBLE);
            iv_recharge_report.setVisibility(View.GONE);
            createUser.setVisibility(View.GONE);
            ll_support_frag.setVisibility(View.GONE);
            ll_order_history.setVisibility(View.GONE);
            ll_support.setVisibility(View.GONE);
            ll_shopping.setVisibility(View.GONE);
            ll_fundTrans.setVisibility(View.GONE);
            ll_recharge.setVisibility(View.VISIBLE);
            llelectricity.setVisibility(View.VISIBLE);
            ll_postpaid1.setVisibility(View.VISIBLE);
            ll_landline.setVisibility(View.VISIBLE);
            ll_insurance.setVisibility(View.VISIBLE);
            cs1.setVisibility(View.VISIBLE);
            cs2.setVisibility(View.VISIBLE);
            cs3.setVisibility(View.VISIBLE);

        }
        else if (UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("2")) {
            llfundtransfer.setVisibility(View.VISIBLE);
            ll_iv_recharge_report.setVisibility(View.VISIBLE);
            iv_recharge_report.setVisibility(View.VISIBLE);
            createUser.setVisibility(View.VISIBLE);
            ll_support.setVisibility(View.VISIBLE);
            ll_fundTrans.setVisibility(View.VISIBLE);
            userSaleReportlayout.setVisibility(View.GONE);
            ll_landline.setVisibility(View.GONE);
            ll_insurance.setVisibility(View.GONE);
            llelectricity.setVisibility(View.GONE);
            ll_recharge.setVisibility(View.GONE);
            cs1.setVisibility(View.GONE);
            cs2.setVisibility(View.GONE);
            cs3.setVisibility(View.GONE);
            ll_postpaid1.setVisibility(View.GONE);
            ll_order_history.setVisibility(View.VISIBLE);
            ll_support_frag.setVisibility(View.VISIBLE);
            ll_shopping.setVisibility(View.VISIBLE);

        } else {
            iv_recharge_report.setVisibility(View.VISIBLE);
            ll_iv_recharge_report.setVisibility(View.VISIBLE);
            ll_support.setVisibility(View.VISIBLE);
            ll_fundTrans.setVisibility(View.VISIBLE);
            llfundtransfer.setVisibility(View.VISIBLE);
            userSaleReportlayout.setVisibility(View.VISIBLE);
            createUser.setVisibility(View.VISIBLE);
            ll_order_history.setVisibility(View.VISIBLE);
            ll_support_frag.setVisibility(View.VISIBLE);
            ll_shopping.setVisibility(View.VISIBLE);
            ll_recharge.setVisibility(View.GONE);
            ll_landline.setVisibility(View.GONE);
            ll_insurance.setVisibility(View.GONE);
            llelectricity.setVisibility(View.GONE);
            cs1.setVisibility(View.GONE);
            cs2.setVisibility(View.GONE);
            cs3.setVisibility(View.GONE);
            ll_postpaid1.setVisibility(View.GONE);

        }
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
            public void onRefresh() {
                //Here you can update your data from internet or from local SQLite data
                HitApi();

                pullToRefresh.setRefreshing(false);
            }
        });


    }

    private void HitApi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

            UtilMethods.INSTANCE.GetNews(getActivity(), null);
            UtilMethods.INSTANCE.BalanceCheck(getActivity(), null);
            UtilMethods.INSTANCE.UserSaleReportNew(getActivity(), null);

        } else {
            UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                    getResources().getString(R.string.network_error_message), 2);
        }
    }


    @Override
    public void onDestroy() {
        // HitApi();
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        HitApi();
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        //HitApi();
        super.onStop();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onClick(View v) {
        UtilMethods.INSTANCE.BalanceCheck(getActivity(), null);

        if (v == specific_report) {
            Intent transactionIntent = new Intent(getActivity(), RechargeReport.class);
            transactionIntent.putExtra("response", "specific");
            transactionIntent.putExtra("from", "");
            getActivity().startActivity(transactionIntent);

        } if (v == ll_order_history  ) {
            Intent transactionIntent = new Intent(getActivity(), OrderHistory.class);
            getActivity().startActivity(transactionIntent);

        }  if (v == ll_shopping) {
            Fragment newFragment = new ShoppingFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } if (v == ll_support_frag) {
            Fragment newFragment = new SupportFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }if (v == ll_rtshopping) {
            Fragment newFragment = new ShoppingFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        if (v == ledger_report) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.Ledger(getActivity(), "", "", "","1", mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }

        if (v == ivRefresh) {
            HitApi();
        }if (v == llMoneyTransfer) {
            Intent transactionIntent = new Intent(getActivity(), DMRActivity.class);
            transactionIntent.putExtra("response", "specific");
            transactionIntent.putExtra("from", "");
            getActivity().startActivity(transactionIntent);
        }if (v == ll_order_history1) {
            Intent transactionIntent = new Intent(getActivity(), OrderHistory.class);
            getActivity().startActivity(transactionIntent);
        }
        if (v == llKhataBook) {
            Intent khataIntent = new Intent(getActivity(), KhataBookActivity.class);
            startActivity(khataIntent);
        }
        if (v == createUser  ||  v == iv_createUser ) {
            Intent createIntent = new Intent(getActivity(), SignupScreen.class);
            createIntent.putExtra("from","profile");
            startActivity(createIntent);
        }
        if (v == fund_recieve_report) {

            Intent transactionIntent = new Intent(getActivity(), FundRecReport.class);
             startActivity(transactionIntent);
        }

        if (v == user_day_book_report) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewTemp = inflater.inflate(R.layout.userdaybook_dateselection, null);

            final TextView fromDate = (TextView) viewTemp.findViewById(R.id.fromDate);
            final EditText childNumber = (EditText) viewTemp.findViewById(R.id.childNumber);
            final AppCompatButton okButton = (AppCompatButton) viewTemp.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) viewTemp.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(getActivity());

            dialog.setCancelable(false);
            dialog.setContentView(viewTemp);

            if (UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("3")) {
                childNumber.setVisibility(View.GONE);
            } else {
                childNumber.setVisibility(View.VISIBLE);
            }

            final Calendar myCalendar = Calendar.getInstance();

            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    //  myCalendar.setTimeZone(UTF:+5.3);

                    String myFormat = "dd/MMM/yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    fromDate.setText(sdf.format(myCalendar.getTime()));
                }

            };
            fromDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog pd =  new DatePickerDialog(getActivity(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));
                    pd.show();
                    pd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);
                    pd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fromDate.getText() != null && fromDate.getText().toString().trim().length() > 0) {

                        if (UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("3")) {

                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Loading...");
                            mProgressDialog.show();

                            SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
                            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

                            UtilMethods.INSTANCE.GetUserDayBook(getActivity(), "" + mobileLogin,
                                    "" + fromDate.getText().toString().trim(), mProgressDialog);

                        } else {
                            if (childNumber.getText() != null && childNumber.getText().toString().trim().length() > 0) {
                                mProgressDialog.setIndeterminate(true);
                                mProgressDialog.setMessage("Loading...");
                                mProgressDialog.show();

                                SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
                                String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);

                                UtilMethods.INSTANCE.GetUserDayBook(getActivity(), "" + childNumber.getText().toString().trim(),
                                        "" + fromDate.getText().toString().trim(), mProgressDialog);
                            } else {
                                childNumber.setError("Please enter child mobile number !!");
                                childNumber.requestFocus();
                            }
                        }

                    } else {
                        fromDate.setError("Please select From date !!");
                        fromDate.requestFocus();
                    }
                }
            });
            dialog.show();
        }
        if (v == commission_report) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.CommisionSlab(getActivity(), mProgressDialog);

            } else {
                UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }
        if (v == iv_prepaid) {
            SharedPreferences prefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(ApplicationConstant.INSTANCE.fromPref, "prepaid");
            editor.commit();


            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "prepaid");
            transactionIntent.putExtra("from", "mobile");
            getActivity().startActivity(transactionIntent);

        }

        if (v == iv_postpaid || v==ll_postpaid1) {
            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "postpaid");
            transactionIntent.putExtra("from", "mobile");
            getActivity().startActivity(transactionIntent);
        }  if (v == ll_landline) {
            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "landline");
            transactionIntent.putExtra("from", "landline");
            getActivity().startActivity(transactionIntent);
        }  if (v == ll_insurance) {
            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "insurance");
            transactionIntent.putExtra("from", "insurance");
            getActivity().startActivity(transactionIntent);
        }

        if (v == iv_dth) {

            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "dth");
            transactionIntent.putExtra("from", "dth");
            getActivity().startActivity(transactionIntent);

        }
        if (v == insurance) {

            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "insurance");
            transactionIntent.putExtra("from", "insurance");
            getActivity().startActivity(transactionIntent);

        }

        if (v == iv_recharge_report) {

            if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                UtilMethods.INSTANCE.FundReceiveStatement(getActivity(), "", "", "", mProgressDialog, "all");

            } else {
                UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                        getResources().getString(R.string.network_error_message), 2);
            }
        }

        if (v == iv_addmoney) {
            Intent transactionIntent = new Intent(getActivity(), ListScreen.class);
            transactionIntent.putExtra("type", "electricity");
            transactionIntent.putExtra("from", "electricity");
            getActivity().startActivity(transactionIntent);
        }
 if (v == iv_addmoneyNew) {
            Intent intent = new Intent(getActivity(), AddMoneyActivity.class);
            startActivity(intent);
        }


        if (v == llfundtransfer) {


            Intent intent = new Intent(getActivity(), DisMemberList.class);
            intent.putExtra("from", "FundTransfer");

            startActivity(intent);
        }

        if (v == ll_profile) {
            Fragment newFragment = new ProfileFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();


        }
        if (v == ll_share) {
            SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
            String mobileLogin = myPrefs.getString(ApplicationConstant.INSTANCE.UserID, null);

            String link = " Hi, I am using " + getString(R.string.app_name) + " " +
                    "" + "https://play.google.com/store/apps/details?id=com.bababanshiwala";
//  String link =" Hi, I am using "+ getString(R.string.app_name)+  " " +
//                    ""+"http://sssrecharge.com/Auth/RefreralSignup/"+mobileLogin;

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, link);
            sendIntent.setType("text/plain");
            ///sendIntent.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(sendIntent, ""));
            startActivity(sendIntent);
        }
        if (v == ll_support) {
            Intent intent = new Intent(getActivity(), DisMemberList.class);
            intent.putExtra("from", "FundTransfer");
            startActivity(intent);
        }

        if (v == iv_fundtranfer) {
            Intent createIntent = new Intent(getActivity(), PaymentRequest.class);
            createIntent.putExtra("Type", "abc");
            startActivity(createIntent);


        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                operatorSelected = data.getExtras().getString("selected");
                operatorSelectedId = data.getExtras().getInt("selectedId");
                operator.setText(operatorSelected);
                paramValue1 = data.getExtras().getString("param1");
                paramValue2 = data.getExtras().getString("param2");
                paramValue3 = data.getExtras().getString("param3");
                paramValue4 = data.getExtras().getString("param4");
                ROffer = data.getExtras().getString("ROffer");

                part2 = "";
                part3 = "";
                //   opeImgMethod(operatorSelected);

            }
        }
        else if (requestCode == 3) {
            if (resultCode == 3) {
                boolean flag = data.getExtras().getBoolean("flag");
                if (flag) {

                    if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {

                        payButton.setEnabled(false);
                        payButton.setBackgroundColor(getResources().getColor(R.color.grey_600));

                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.show();

                        UtilMethods.INSTANCE.afterLogintoPreviousWindow(getActivity(), number.getText().toString().trim().toString(),
                                amount.getText().toString().trim(), "" + operatorSelectedId, stdCode.getText().toString().trim(),
                                flagElectricity, paramValue1, paramValue2, paramValue3, paramValue4, mProgressDialog, payButton);


                    } else {
                        UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                                getResources().getString(R.string.network_error_message), 2);
                    }

                } else {
                    UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.attention_error_title),
                            getResources().getString(R.string.pinpass_error), 2);
                }

            }
        }

        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                boolean status = data.getBooleanExtra("status", false);
                int response = data.getIntExtra("response", 0);
                String message = data.getStringExtra("message");

                String detailedResponse = "Status: " + status +
                        "\nResponse:  " + response +
                        "\nMessage: " + message;
                Toast.makeText(getActivity(), detailedResponse, Toast.LENGTH_LONG).show();
                //btnSubmit.snack(detailedResponse);

                Log.i("logTag", detailedResponse);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }




    public void SetBalance() {
        try {
            SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
            String balanceResponse = myPreferences.getString(ApplicationConstant.INSTANCE.balancePref, null);

            BalanceCheckResponse balanceCheckResponse = new Gson().fromJson(balanceResponse, BalanceCheckResponse.class);

            if (balanceCheckResponse != null) {
                String prepaidWallet = balanceCheckResponse.getPrepaidWallet();
                String utilityWallet = balanceCheckResponse.getUtilityWallet();
                String balance = "";
                if (prepaidWallet != null && prepaidWallet.length() > 0) {
                    balance = "Prepaid : " + "\u20b9" + prepaidWallet;
                    tvPrepaid.setText("\u20b9" + prepaidWallet);
                    balancePrepaidLayout.setVisibility(View.VISIBLE);
                } else {
                    balancePrepaidLayout.setVisibility(View.GONE);
                }
                if (utilityWallet != null && utilityWallet.length() > 0) {
                    balance = balance + "    Utility : " + "\u20b9" + utilityWallet;
                    tvPostpaid.setText("\u20b9" + utilityWallet);
                } else {
                    tvPostpaid.setText("\u20b9 0.0 ");
                }
                tvbalance.setText(balance);


                // balancePrepaid.setText("Balance\n" + prepaidWallet);


                if (balanceCheckResponse.getIsLogin().equalsIgnoreCase("0")) {
                    UtilMethods.INSTANCE.Logout(getActivity(), null);
                }
            }
        } catch (Exception e) {

        }

    }

    @Subscribe
    public void onActivityActivityMessage(ActivityActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getMessage().equalsIgnoreCase("balanceUpdate")) {
            SetBalance();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }



}
