package com.bababansiwalanew.Popup.ui;

public class Popup {

//
//    String response = "";
//    ArrayList<ROfferObject> transactionsObjects = new ArrayList<>();
//    ROfferResponse transactions = new ROfferResponse();
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.recharge_report);
//
//        response=getIntent().getExtras().getString("response");
//
//        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
//        toolbar.setTitle("R Offers");
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        parseData(response);
//    }
//
//    public void parseData(String response) {
//        Gson gson = new Gson();
//        transactions = gson.fromJson(response, ROfferResponse.class);
//        transactionsObjects = transactions.getRecords();
//
//        mAdapter = new ROfferAdapter(transactionsObjects, Popup.this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    public void ItemClick(String amount) {
//
//        Log.e("here", amount);
//
//        FragmentActivityMessage activityActivityMessage =
//                new FragmentActivityMessage("" + amount, "rOffer_Amount");
//        GlobalBus.getBus().post(activityActivityMessage);
//
//        finish();
//    }
}


