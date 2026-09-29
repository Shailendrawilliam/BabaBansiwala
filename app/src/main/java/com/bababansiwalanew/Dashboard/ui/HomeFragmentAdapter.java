package com.bababansiwalanew.Dashboard.ui;

import android.content.Context;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bababansiwalanew.Dashboard.dto.HomeItem;
import com.bababansiwalanew.R;

import java.util.List;


/**
 * Created by Lalit on 23-01-2017.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeItem> albumList;
    private String type = "";
    private int selectedThumbnail = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public RelativeLayout mainContainer;
        public AppCompatRadioButton opCheck;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            mainContainer = (RelativeLayout) view.findViewById(R.id.mainContainer);
            opCheck = (AppCompatRadioButton) view.findViewById(R.id.opCheck);
        }
    }


    public HomeFragmentAdapter(Context mContext, List<HomeItem> albumList, String type) {
        this.mContext = mContext;
        this.albumList = albumList;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HomeItem album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.thumbnail.setImageResource(album.getThumbnail());
        if (album.getFlag() == 1) {
            holder.opCheck.setChecked(true);
        } else {
            holder.opCheck.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setOperator(int poition) {

        for (HomeItem homeItem : albumList) {
            homeItem.setFlag(0);
        }
        albumList.get(poition).setFlag(1);
        notifyDataSetChanged();
    }
}
