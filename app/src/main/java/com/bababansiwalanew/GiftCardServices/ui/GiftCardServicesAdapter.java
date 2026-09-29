package com.bababansiwalanew.GiftCardServices.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bababansiwalanew.Dashboard.ui.GiftCardServicesActivity;
import com.bababansiwalanew.GiftCardServices.dto.GiftCardServicesResponsedata;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class GiftCardServicesAdapter extends RecyclerView.Adapter<GiftCardServicesAdapter.MyViewHolder> {


        private ArrayList<GiftCardServicesResponsedata> dataList;
        private Context mContext;
        int resourceId = 0;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView title;
            public AppCompatButton   buyButton;
            public ImageView img;
         //   public RelativeLayout disputeTextContainer;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                img = (ImageView) view.findViewById(R.id.dialog_logo);
                buyButton = (AppCompatButton) view.findViewById(R.id.okButton);


            }
        }

    public GiftCardServicesAdapter(ArrayList<GiftCardServicesResponsedata> dataList, Context mContext) {
            this.dataList = dataList;
            this.mContext = mContext;
        }

        @Override
        public GiftCardServicesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.giftcard_adapter, parent, false);

            return new GiftCardServicesAdapter.MyViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final GiftCardServicesResponsedata datalist = dataList.get(i);


        myViewHolder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GiftCardServicesActivity) mContext).ItemClick(
                        datalist.getImage(),
                       datalist.getProvider_key(),
                       datalist.getService_provider(),
                        datalist.getService_type(),
                        datalist.getService_desc());
                Log.e("GiftAdopt",""+  datalist.getProvider_key());
                FragmentActivityMessage activityActivityMessage =
                        new FragmentActivityMessage( ""+  datalist.getProvider_key(),"giftcardselectedkey");
                GlobalBus.getBus().post(activityActivityMessage);


//                  Intent intent = new Intent(((GiftCardServicesActivity) mContext), GiftCardServicesActivity.class);
//                mContext.startActivity(intent);


                // ((ListScreen) mContext).ItemClick(operator.getRoffercode());
            }
        });
                          myViewHolder.title.setText(datalist.getService_provider().toString());
        if (datalist.getImage()!= null && datalist.getImage().toString().length() > 0) {

            String imgName = dataList.get(i).getImage();
            String imgTemp = imgName.toLowerCase().replace(" ", "");
            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());

            //for eg operator name Airtel Digital tv
            //above code convert it to airteldigitaltv
            resourceId = mContext.getResources().getIdentifier(
                    imgTemp4, "drawable", mContext.getPackageName());

            // Log.e("here", "name : " + imgTemp4 + " , and : " + resourceId);

            Picasso.with(mContext).load(ApplicationConstant.INSTANCE.baseUrl+imgTemp4)./*error(R.drawable.default_image).*/into((myViewHolder.img) );
        } else {
            myViewHolder.img.setImageResource(R.drawable.default_image);
        }
    }

//
//    @Override
//    public void onBindViewHolder(@NonNull GiftCardServicesAdapter giftCardServicesAdapter, int i) {
//        final GiftCardServicesResponsedata operator = dataList.get(i);
////        giftCardServicesAdapter.txn.setText("" + operator.getTID());
////        giftCardServicesAdapter.recharge.setText("" + operator.getRechargeNo());
////
//        String status = "";
////        if(operator.getOpName().equals("APEPDCL") || operator.getOpName().equals("APSPDCL") || operator.getOpName().equals("TSSPDCL"))
////        {
////            status = "Success";
////            giftCardServicesAdapter.statusImg.setBackgroundResource(R.drawable.ic_status_success);
////            if (operator.getIsDisputable().equalsIgnoreCase("1")) {
////                giftCardServicesAdapter.disputeButton.setVisibility(View.GONE);
////                giftCardServicesAdapter.disputeTextContainer.setVisibility(View.VISIBLE);
////            }
////            else
////            {
////                giftCardServicesAdapter.disputeButton.setVisibility(View.GONE);
////                giftCardServicesAdapter.disputeTextContainer.setVisibility(View.GONE);
////            }
////
////        }
//       // else{
////        {
////            if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
////                status = "Success";
////                giftCardServicesAdapter.statusImg.setBackgroundResource(R.drawable.ic_status_success);
////            } else if (operator.getStatus().equalsIgnoreCase("FAILED")) {
////                status = "Failed";
////                giftCardServicesAdapter.statusImg.setBackgroundResource(R.drawable.ic_status_failed);
////            } else if (operator.getStatus().equalsIgnoreCase("REFUND")) {
////                status = "Refund";
////                giftCardServicesAdapter.statusImg.setBackgroundResource(R.drawable.ic_status_refund);
////            } else if (operator.getStatus().equalsIgnoreCase("PENDING") || operator.getStatus().equalsIgnoreCase("REQUEST SEND")
////                    || operator.getStatus().equalsIgnoreCase("REQUEST SENT")) {
////                status = "Pending";
////                giftCardServicesAdapter.statusImg.setBackgroundResource(R.drawable.ic_status_pending);
////            } else {
////                status = "other";
////                giftCardServicesAdapter.statusImg.setBackground(null);
////            }
//
////            if (operator.getIsDisputable().equalsIgnoreCase("1")) {
////                if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
////                    giftCardServicesAdapter.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
////                            (mContext.getResources().getColor(R.color.buttoncolor)));
////                    giftCardServicesAdapter.disputeTextContainer.setVisibility(View.GONE);
////                    giftCardServicesAdapter.disputeButton.setVisibility(View.VISIBLE);
////                    giftCardServicesAdapter.disputeButton.setClickable(true);
////                } else if (operator.getStatus().equalsIgnoreCase("Failed")) {
////                    giftCardServicesAdapter.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
////                            (mContext.getResources().getColor(R.color.bottommenu)));
////                    giftCardServicesAdapter.disputeTextContainer.setVisibility(View.GONE);
////                    giftCardServicesAdapter.disputeButton.setVisibility(View.GONE);
////                }
////            } else if (operator.getIsDisputable().equalsIgnoreCase("0")) {
////                if (operator.getStatus().equalsIgnoreCase("SUCCESS")) {
////                    giftCardServicesAdapter.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
////                            (mContext.getResources().getColor(R.color.bottommenu)));
////                    giftCardServicesAdapter.disputeText.setText("" + operator.getIsDisputable());
////                    giftCardServicesAdapter.disputeTextContainer.setVisibility(View.VISIBLE);
////                    giftCardServicesAdapter.disputeButton.setVisibility(View.GONE);
////                } else {
////                    giftCardServicesAdapter.disputeButton.setSupportBackgroundTintList(ColorStateList.valueOf
////                            (mContext.getResources().getColor(R.color.bottommenu)));
////                    giftCardServicesAdapter.disputeTextContainer.setVisibility(View.GONE);
////                    giftCardServicesAdapter.disputeButton.setVisibility(View.GONE);
////                }
////            }
//      //  }
////        giftCardServicesAdapter.status.setText("" + status);
////
////        giftCardServicesAdapter.operatorId.setText(operator.getOpID());
////
////        //////////////////////////////////////////////////////////////////////////////
////
////
////        giftCardServicesAdapter.actionButton.setVisibility(View.GONE);
//
////        if (operator.getOpName() != null && operator.getOpName().toString().length() > 0) {
////
////            String imgName = operator.getOpName();
////            String imgTemp = imgName.toLowerCase().replace(" ", "");
////            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
////            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
////            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
////            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
////
////
////            resourceId = mContext.getResources().getIdentifier(
////                    imgTemp4, "drawable", mContext.getPackageName());
////
////            Glide.with(mContext).load(resourceId).asBitmap().centerCrop().error(R.drawable.ic_operator_default_icon).into(new BitmapImageViewTarget(giftCardServicesAdapter.operatorImage) {
////                @Override
////                protected void setResource(Bitmap resource) {
////                    RoundedBitmapDrawable circularBitmapDrawable =
////                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
////                    circularBitmapDrawable.setCircular(true);
////                    giftCardServicesAdapter.operatorImage.setImageDrawable(circularBitmapDrawable);
////                }
////            });
////        } else {
////            giftCardServicesAdapter.operatorImage.setImageResource(R.drawable.ic_operator_default_icon);
////        }
//
////        giftCardServicesAdapter.actionButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
////                    UtilMethods.INSTANCE.rechargeParams(mContext, operator.getRechargeNo(), operator.getAmount(), operator.getOpID(),
////                            "", null,giftCardServicesAdapter.actionButton,"");
////                } else {
////                    UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
////                            mContext.getResources().getString(R.string.network_error_message), 2);
////                }
////
////            }
////        });
//
////        giftCardServicesAdapter.disputeButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////                View view = inflater.inflate(R.layout.dispute_popup, null);
////
////                final TextInputLayout remarkLayout = (TextInputLayout) view.findViewById(R.id.remarkLayout);
////                final EditText remark = (EditText) view.findViewById(R.id.remark);
////
////                final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
////                final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);
////
////                final Dialog dialog = new Dialog(mContext);
////
////                dialog.setTitle("Dispute Remark");
////                dialog.setCancelable(false);
////                dialog.setContentView(view);
////
////                cancelButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog.dismiss();
////                    }
////                });
////
////                okButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                        String remarkText;
////                        if (remark.getText() != null && remark.getText().toString().trim().length() > 0) {
////                            remarkText = remark.getText().toString().trim();
////                        } else {
////                            remarkText = "";
////                        }
////
////                        if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {
////                            dialog.dismiss();
////                            UtilMethods.INSTANCE.Dispute(mContext, operator.getID() ,remarkText);
////                        } else {
////                            UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
////                                    mContext.getResources().getString(R.string.network_error_message), 2);
////                        }
////
////                    }
////                });
////                dialog.show();
////
////            }
////        });
//    }
//
//

        @Override
        public int getItemCount() {
            return dataList.size();
        }

    }
