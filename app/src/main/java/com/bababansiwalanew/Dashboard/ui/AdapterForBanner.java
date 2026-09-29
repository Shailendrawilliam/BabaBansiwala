package com.bababansiwalanew.Dashboard.ui;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
 import com.bababansiwalanew.Login.dto.LoginData;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.HashMap;

public class AdapterForBanner extends PagerAdapter {

    private ArrayList<HashMap<String, String>> images;
    private LayoutInflater inflater;
    private Context context;
    int[] bannerData;
    private ArrayList<LoginData> ImageList;

/*
    public AdapterForBanner(Context context, ArrayList<HashMap<String, String>> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }*/

    public AdapterForBanner(Context activity, ArrayList<LoginData> ImageList) {
        this.context = activity;
        this.ImageList = ImageList;
        try{
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e){

        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout=inflater.inflate(R.layout.banner_layout, view, false);
        /*View myImageLayout = inflater.from(view.getContext()).inflate(R.layout.banner_layout, view, false);*/
        final ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.img_banner);

        Picasso.with(context)
                .load(ApplicationConstant.INSTANCE.baseUrl+ImageList.get(position).getdESCRIPTION().replace("~","")).into(myImage);

        view.addView(myImageLayout);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view.equals(o);
    }


}

