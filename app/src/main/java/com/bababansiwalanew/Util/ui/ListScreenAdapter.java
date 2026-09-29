package com.bababansiwalanew.Util.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bababansiwalanew.Activities.RechargeActivity;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.bababansiwalanew.Util.dto.Operator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ListScreenAdapter extends RecyclerView.Adapter<ListScreenAdapter.MyViewHolder> {

    private ArrayList<Operator> operatorList;
    private Context mContext;
    int resourceId = 0;
String from;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView opImage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            opImage = (ImageView) view.findViewById(R.id.opImage);

        }
    }

    public ListScreenAdapter(ArrayList<Operator> operatorList, Context mContext,String from) {
        this.operatorList = operatorList;
        this.mContext = mContext;
        this.from = from;
    }
    public void updateList(ArrayList<Operator> list){
        operatorList = list;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.operator_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Operator operator = operatorList.get(position);
if (from.equalsIgnoreCase("from")){

}else {

}
        holder.title.setText(operator.getOPNAME());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, RechargeActivity.class);
                intent.putExtra("selected", operator.getOPNAME());
                intent.putExtra("Roffer", operator.getPLANCODE());
                intent.putExtra("selectedicon", operator.getIcon());
                intent.putExtra("selectedId", operator.getOPID());
                intent.putExtra("selectedOpcode", operator.getRoffercode());
                intent.putExtra("displayvalue1", operator.getDisplayalue1());
                intent.putExtra("displayvalue2", operator.getDisplayalue2());
                intent.putExtra("displayvalue3", operator.getDisplayalue3());
                intent.putExtra("displayvalue4", operator.getDisplayalue4());
                intent.putExtra("Roffercode", operator.getRoffercode());
                callapi();

                // intent.putExtra("flaglandline", operator.getDisplayvalue4());
                intent.putExtra("from", "from");
                mContext.startActivity(intent);

                ((ListScreen)mContext).finish();

//
//                ((ListScreen) mContext).ItemClick(operator.getOPNAME(),
//                        operator.getOPID(),
//                        operator.getColumn1(),
//                        operator.getPLANCODE(),
//                        operator.getDisplayalue1(),
//                        operator.getDisplayalue2(),
//                        operator.getDisplayalue3(),
//                        operator.getDisplayalue4(),
//                        operator.getRoffercode());
            }




        });
        //try {
            holder.title.setText(operator.getOPNAME());
            String img = "";
            img = operator.getIcon();
            if (!img.equalsIgnoreCase("")) {
                // Log.e("imagepathis",ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1().toString());


                Picasso.with(mContext).load(ApplicationConstant.INSTANCE.baseUrl + operator.getIcon().toString())./*centerCrop().error(R.drawable.ic_operator_default_new).*/
                        into((holder.opImage)  );
            }
            else if (operator.getOPNAME() != null && operator.getOPNAME().toString().length() > 0) {

                String imgName = operatorList.get(position).getOPNAME();
                String imgTemp = imgName.toLowerCase().replace(" ", "");
                String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
                String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
                String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
                String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
                resourceId = mContext.getResources().getIdentifier(
                        imgTemp4, "drawable", mContext.getPackageName());
                Picasso.with(mContext).load(resourceId)./*centerCrop().error(R.drawable.ic_operator_default_new).*/into((holder.opImage));
            } else {
                holder.opImage.setImageResource(R.drawable.ic_operator_default_new);
            }
      //  }catch ( Exception e){}
//                if (operator.getColumn1()!=null&& operator.getColumn1().toString().length() > 0 ){
//                    String imgName = operatorList.get(position).getColumn1();
//                    String imgTemp = imgName.toLowerCase().replace(" ", "");
//                    String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//                    String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//                    String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//                    String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
//                   // Log.e("imagepathis",ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1().toString());
//
//
//                    Glide.with(mContext).asBitmap().load(ApplicationConstant.INSTANCE.baseUrl+operator.getColumn1().toString())./*centerCrop().error(R.drawable.ic_operator_default_new).*/into(new BitmapImageViewTarget(holder.opImage) {
//                        @Override
//                        protected void setResource(Bitmap resource) {
//                            RoundedBitmapDrawable circularBitmapDrawable =
//                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                            circularBitmapDrawable.setCircular(true);
//                            holder.opImage.setImageDrawable(circularBitmapDrawable);
//                        }
//                    });
//                       }
//               else   if (operator.getOPNAME() != null && operator.getOPNAME().toString().length() > 0) {
//
//            String imgName = operatorList.get(position).getOPNAME();
//            String imgTemp = imgName.toLowerCase().replace(" ", "");
//            String imgTemp1 = imgTemp.toLowerCase().replace("-", "");
//            String imgTemp2 = imgTemp1.toLowerCase().replace("&", "");
//            String imgTemp3 = imgTemp2.toLowerCase().replace("_", "");
//            String imgTemp4 = imgTemp3.substring(0, imgTemp3.length());
//
//   //for eg operator name Airtel Digital tv
//            //above code convert it to airteldigitaltv
//            resourceId = mContext.getResources().getIdentifier(
//                    imgTemp4, "drawable", mContext.getPackageName());
//
//            // Log.e("here", "name : " + imgTemp4 + " , and : " + resourceId);
//
//            Glide.with(mContext).asBitmap().load(resourceId)./*centerCrop().error(R.drawable.ic_operator_default_new).*/into(new BitmapImageViewTarget(holder.opImage) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable =
//                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    holder.opImage.setImageDrawable(circularBitmapDrawable);
//                }
//            });
//        } else {
//            holder.opImage.setImageResource(R.drawable.ic_operator_default_new);
//        }
    }
    private void callapi() {
        if (UtilMethods.INSTANCE.isNetworkAvialable(mContext)) {

            ProgressDialog newP = new ProgressDialog(mContext);
            newP.setTitle("Loading");
            newP.show();
            UtilMethods.INSTANCE.RecentRecharges(mContext, "1", "", "", newP, "all");

        } else {
            UtilMethods.INSTANCE.dialogOk(mContext, mContext.getResources().getString(R.string.network_error_title),
                    mContext.getResources().getString(R.string.network_error_message), 2);
        }

    }
    @Override
    public int getItemCount() {
        return operatorList.size();
    }

}
