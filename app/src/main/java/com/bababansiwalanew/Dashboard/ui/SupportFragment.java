package com.bababansiwalanew.Dashboard.ui;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.squareup.picasso.Picasso;

/**
 * Created by Lalit on 23-05-2017.
 */

public class SupportFragment extends Fragment {

    ImageView logo;
    TextView email, phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.support_fragment, container, false);

        logo =   v.findViewById(R.id.logo);
        email = (TextView) v.findViewById(R.id.email);
        phone = (TextView) v.findViewById(R.id.phone);

        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String emailPref = myPrefs.getString(ApplicationConstant.INSTANCE.supportEmail, null);
        String numberPref = myPrefs.getString(ApplicationConstant.INSTANCE.supportNumber, null);
        String icon = myPrefs.getString(ApplicationConstant.INSTANCE.icon, null);

    /*    Glide.with(getActivity()).load(ApplicationConstant.INSTANCE.baseUrl + icon).error(R.drawable.defaultlogo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(logo);
*/

        Picasso.with(getActivity())
                .load(ApplicationConstant.INSTANCE.baseUrl + icon)
                .into(logo);


        email.setText("  " + emailPref);
        numberPref = numberPref.replace(",", "\n");
        phone.setText("  " + numberPref);

        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Support");
        //LinearLayout mLinearLayout=(LinearLayout)  ((AppCompatActivity) getActivity()).findViewById(R.id.layout_name1);
       // mLinearLayout.setVisibility(View.GONE);
        super.onActivityCreated(savedInstanceState);
    }
}