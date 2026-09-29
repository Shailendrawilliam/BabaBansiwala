package com.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.UtilMethods;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddressBottomSheet extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.address_bottom_sheet, container, false);

        EditText etHouse;
        EditText etArea;
        EditText etLandmark;
        EditText etCity;
        EditText etState;
        EditText etPincode;
        TextView saveAddress;
        saveAddress =  v.findViewById(R.id.saveAddress);
        etPincode =  v.findViewById(R.id.etPincode);
        etState =  v.findViewById(R.id.etState);
        etCity =  v.findViewById(R.id.etCity);
        etLandmark =  v.findViewById(R.id.etLandmark);
        etArea =  v.findViewById(R.id.etArea);
        etHouse =  v.findViewById(R.id.etHouse);
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                 if (etHouse.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter house/street no", Toast.LENGTH_LONG).show();
                 }
                  else if (etArea.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter area ", Toast.LENGTH_LONG).show();
                 } else if (etLandmark.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter landmark", Toast.LENGTH_LONG).show();
                 }else if (etCity.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter City", Toast.LENGTH_LONG).show();
                 }else if (etState.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter state", Toast.LENGTH_LONG).show();
                 }else if (etPincode.getText().toString().isEmpty()){
                     Toast.makeText(requireActivity(),"Please enter Pincode", Toast.LENGTH_LONG).show();
                 }
                   else{
                       String Address  = etHouse.getText().toString()+","+etArea.getText().toString()
                               +","+etLandmark.getText().toString()+","+etCity.getText().toString()
                               +","+etState.getText().toString()+","+etPincode.getText().toString();


                     UtilMethods.INSTANCE.ShippingAddress(requireActivity(),Address,
                             new UtilMethods.ApiCallBackTwoMethod() {
                                 @Override
                                 public void onSucess(Object object) {


                                 }

                                 @Override
                                 public void onError(String errorMsg) {
                                   //  Toast.makeText( requireActivity(),""+errorMsg,Toast.LENGTH_LONG).show();
                                     }
                             });  }
                dismiss();
            }
        });


        return v;
    }


    }
