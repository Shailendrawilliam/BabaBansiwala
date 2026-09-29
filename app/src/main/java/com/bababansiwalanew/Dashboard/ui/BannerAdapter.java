package com.bababansiwalanew.Dashboard.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bababansiwalanew.Login.dto.LoginData;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


    public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

        private List<LoginData> data;
        private Context context;

        /**
         * setup Constructure
         */
        public BannerAdapter(List<LoginData> data, Context context) {
            this.data = data;
            this.context = context;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.pager_item_banner, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final LoginData bannerListResponse=data.get(position);

            Picasso.with(context)
                    .load(ApplicationConstant.INSTANCE.baseUrl+data.get(position).getdESCRIPTION().replace("~",""))
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.get(position).getSTATUS()!=null && !data.get(position).getSTATUS().equalsIgnoreCase("")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).getSTATUS()));
                        context.startActivity(intent);}

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        /**
         * bind ViewHolder
         */
        public class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);

                  imageView = (ImageView) itemView.findViewById(R.id.imageView);

            }
        }


}
