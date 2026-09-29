package com.bababansiwalanew.Dashboard.ui;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

 import com.bababansiwalanew.Login.dto.LoginData;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
 public class CustomPagerAdapter extends PagerAdapter {

    private ArrayList<LoginData> ImageList;


    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(ArrayList<LoginData> ImageList, Context context) {
        this.ImageList = ImageList;
        this.mContext = context;
       this. mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

//        Log.e("Image"," "+ApplicationConstant.INSTANCE.baseUrl+"/"+ImageList.get(position).getPath());
        Picasso.with(mContext)
                .load(ApplicationConstant.INSTANCE.baseUrl+ImageList.get(position).getdESCRIPTION().replace("~",""))
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImageList.get(position).getSTATUS()!=null && !ImageList.get(position).getSTATUS().equalsIgnoreCase("")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ImageList.get(position).getSTATUS()));
                mContext.startActivity(intent);}

            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}