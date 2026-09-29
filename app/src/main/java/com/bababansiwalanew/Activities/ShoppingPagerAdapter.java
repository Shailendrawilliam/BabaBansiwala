package com.bababansiwalanew.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingPagerAdapter extends PagerAdapter {

    private ArrayList<EcommerceBannerModel> ImageList;


    Context mContext;
    LayoutInflater mLayoutInflater;

    public ShoppingPagerAdapter(ArrayList<EcommerceBannerModel> ImageList, Context context) {
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
                .load(ApplicationConstant.INSTANCE.baseUrl+(ImageList.get(position).getBnnerImage(
                ).replace("~","")).replace(" ","%20"))
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ImageList.get(position).getBannerName()!=null && !ImageList.get(position).getBannerName().equalsIgnoreCase("")){
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ImageList.get(position).getBannerName()));
//                    mContext.startActivity(intent);}

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