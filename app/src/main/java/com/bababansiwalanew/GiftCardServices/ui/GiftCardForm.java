package com.bababansiwalanew.GiftCardServices.ui;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.FragmentActivityMessage;
import com.bababansiwalanew.Util.GlobalBus;
import com.bababansiwalanew.Util.UtilMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GiftCardForm extends AppCompatActivity implements View.OnClickListener {
  Toolbar    toolbar;
String code;
    ProgressDialog mProgressDialog = null;
  EditText SenderName,SenderEmail,Amount,SenderMobile,SenderAddress,SenderpinCode,SenderCity,SenderState,gv_gift_msg;
  EditText ReceiverName,ReceiverEmail,ReceiverMobile,ReceiverAddress,ReceiverpinCode,ReceiverCity,ReceiverState;
  Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_card_form);
                         mProgressDialog= new ProgressDialog(GiftCardForm.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("GiftCard Recharge");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SenderName = (EditText)  findViewById(R.id.SenderName);
        SenderEmail = (EditText)  findViewById(R.id.Sender_Email);
      // SenderState = (EditText)  findViewById(R.id.senderName);
        SenderMobile = (EditText)  findViewById(R.id.Mob_no);
        SenderAddress = (EditText)  findViewById(R.id.Address);
        SenderpinCode = (EditText)  findViewById(R.id.Pincode);
        Amount = (EditText)  findViewById(R.id.amount);
        SenderCity = (EditText)  findViewById(R.id.city);
        SenderState = (EditText)  findViewById(R.id.state);
        ReceiverName = (EditText)  findViewById(R.id.ReceiverName);
        ReceiverEmail = (EditText)  findViewById(R.id.Receiver_Email);
        ReceiverMobile = (EditText)  findViewById(R.id.ReceiverMob_no);
        ReceiverAddress = (EditText)  findViewById(R.id.ReceiverAddress);
        ReceiverpinCode = (EditText)  findViewById(R.id.ReceiverPincode);
        ReceiverCity = (EditText)  findViewById(R.id.Receivercity);
        ReceiverState = (EditText)  findViewById(R.id.Receiverstate);
        gv_gift_msg = (EditText)  findViewById(R.id.gv_gift_msg);
        submit = (Button)  findViewById(R.id.Submit);
        submit.setOnClickListener( this);

        //Intent intent= new Intent(GiftCardForm.this)
        code = getIntent().getExtras().getString("VoucherCode");
        Log.e("getIntentCode",code);// getGiftCode();
    }
    @Subscribe
    public void FragmentActivityMessage(FragmentActivityMessage activityFragmentMessage) {
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("giftcardselectedkey")) {
            final String code1 = activityFragmentMessage.getMessage();
            //code=activityFragmentMessage.getMessage();
//           Toast.makeText(getApplicationContext(),"code1"+code1,Toast.LENGTH_SHORT).show();
//            Log.e("KeySelectedglobalbus",code1);

        }
        if (activityFragmentMessage.getFrom().equalsIgnoreCase("GetGiftVoucherRecharge")) {
            final String code1 = activityFragmentMessage.getMessage();
            //code=activityFragmentMessage.getMessage();
//           Toast.makeText(getApplicationContext(),"code1"+code1,Toast.LENGTH_SHORT).show();
//            Log.e("KeySelectedglobalbus",code1);
            UtilMethods.INSTANCE.dialogOk(getApplicationContext(), getApplicationContext().getResources().getString(R.string.successful_title),
                    activityFragmentMessage.getMessage().toString(), 11);
            finish();
        }
    }







    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
    @Override
    public void onClick(View v) {
        if (v==submit){

           if (validateForm() == 0){
               mProgressDialog.setTitle("Loading...");
                    mProgressDialog.show();
                    UtilMethods.INSTANCE.GetGiftVoucherRecharge(GiftCardForm.this,code,

                            Amount.getText().toString(),
                            SenderName.getText().toString(),
                            SenderEmail.getText().toString(),
                            ReceiverName.getText().toString(),
                            ReceiverEmail.getText().toString(),
                            SenderAddress.getText().toString(),
                            SenderpinCode.getText().toString(),
                            SenderCity.getText().toString(),
                            SenderState.getText().toString(),
                            SenderMobile.getText().toString(),
                            ReceiverAddress.getText().toString(),
                            ReceiverpinCode.getText().toString(),
                            ReceiverCity.getText().toString(),
                            ReceiverState.getText().toString(),
                            ReceiverMobile.getText().toString(),
                            gv_gift_msg.getText().toString(),mProgressDialog) ;
           }else {
           }
        }
        }
    public int validateForm() {
        int flag = 0;

        if (Amount.getText() != null && Amount.getText().toString().trim().length() > 0) {
        } else {
            Amount.setError(getResources().getString(R.string.amount_error));
            Amount.requestFocus();
            flag++;
        }

        if (code != null &&code.toString().trim().length() > 0) {
        } else {
//            code.setError(getResources().getString(R.string.operator_error));
//            code.requestFocus();
           // flag++;
        }
        if (SenderName.getText() != null && SenderName.getText().toString().trim().length() > 0
        ) {
        } else {
            SenderName.setError(getResources().getString(R.string.name_error));
            SenderName.requestFocus();
            flag++;
        }
        if (SenderMobile.getText() != null && SenderMobile.getText().toString().trim().length() > 0 && SenderMobile.getText().toString().trim().length() < 11) {
        } else {
            SenderMobile.setError(getResources().getString(R.string.mobilenumber_error));
            SenderMobile.requestFocus();
            flag++;
        }
        if (SenderEmail.getText() != null && SenderEmail.getText().toString().trim().length() > 0
        ) {
        } else {
            SenderEmail.setError(getResources().getString(R.string.email_error));
            SenderEmail.requestFocus();
            flag++;
        }
        if (SenderAddress.getText() != null && SenderAddress.getText().toString().trim().length() > 0) {
        } else {
            SenderAddress.setError(getResources().getString(R.string.address_error));
            SenderAddress.requestFocus();
            flag++;
        } if (SenderpinCode.getText() != null && SenderpinCode.getText().toString().trim().length() > 0 && SenderpinCode.getText().toString().trim().length() < 7
        ) {
        } else {
            SenderpinCode.setError(getResources().getString(R.string.pincode_error));
            SenderpinCode.requestFocus();
            flag++;
        }
        if (SenderCity.getText() != null && SenderCity.getText().toString().trim().length() > 0) {
        } else {
            SenderCity.setError(getResources().getString(R.string.city_error));
            SenderCity.requestFocus();
            flag++;
        } if (SenderState.getText() != null && SenderState.getText().toString().trim().length() > 0
        ) {
        } else {
            SenderState.setError(getResources().getString(R.string.state_error));
            SenderState.requestFocus();
            flag++;
        }
        if (ReceiverName.getText() != null && ReceiverName.getText().toString().trim().length() > 0) {
        } else {
            ReceiverName.setError(getResources().getString(R.string.name_error));
            ReceiverName.requestFocus();
            flag++;
        } if (ReceiverEmail.getText() != null && ReceiverEmail.getText().toString().trim().length() > 0)
        {
        } else {
            ReceiverEmail.setError(getResources().getString(R.string.email_error));
            ReceiverEmail.requestFocus();
            flag++;
        }
        if (ReceiverMobile.getText() != null && ReceiverMobile.getText().toString().trim().length() > 0 && ReceiverMobile.getText().toString().trim().length() < 11) {
        } else {
            ReceiverMobile.setError(getResources().getString(R.string.mobilenumber_error));
            ReceiverMobile.requestFocus();
            flag++;
        } if (ReceiverAddress.getText() != null && ReceiverAddress.getText().toString().trim().length() > 0
        ) {
        } else {
            ReceiverAddress.setError(getResources().getString(R.string.address_error));
            ReceiverAddress.requestFocus();
            flag++;
        }
        if (ReceiverpinCode.getText() != null && ReceiverpinCode.getText().toString().trim().length() > 0 && ReceiverpinCode.getText().toString().trim().length() < 7) {
        } else {
            ReceiverpinCode.setError(getResources().getString(R.string.pincode_error));
            ReceiverpinCode.requestFocus();
            flag++;
        } if (ReceiverCity.getText() != null && ReceiverCity.getText().toString().trim().length() > 0
        ) {
        } else {
            ReceiverCity.setError(getResources().getString(R.string.city_error));
            ReceiverCity.requestFocus();
            flag++;
        }
        if (ReceiverState.getText() != null && ReceiverState.getText().toString().trim().length() > 0) {
        } else {
            ReceiverState.setError(getResources().getString(R.string.state_error));
            ReceiverState.requestFocus();
            flag++;
        } if (gv_gift_msg.getText() != null && gv_gift_msg.getText().toString().trim().length() > 0
        ) {
        } else {
            gv_gift_msg.setError(getResources().getString(R.string.empty_gift_message_error));
            gv_gift_msg.requestFocus();
            flag++;
        }

        return flag;
    }

}



// submit.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"code1"+code1,Toast.LENGTH_SHORT).show();
//                    mProgressDialog.setTitle("Loading...");
//                    mProgressDialog.show();
//                    UtilMethods.INSTANCE.GetGiftVoucherRecharge(GiftCardForm.this,code1,
//
//                            Amount.getText().toString(),
//                            SenderName.getText().toString(),
//                            SenderEmail.getText().toString(),
//                            ReceiverName.getText().toString(),
//                            ReceiverEmail.getText().toString(),
//                            SenderAddress.getText().toString(),
//                            SenderpinCode.getText().toString(),
//                            SenderCity.getText().toString(),
//                            SenderState.getText().toString(),
//                            SenderMobile.getText().toString(),
//                            ReceiverAddress.getText().toString(),
//                            ReceiverpinCode.getText().toString(),
//                            ReceiverCity.getText().toString(),
//                            ReceiverState.getText().toString(),
//                            ReceiverMobile.getText().toString(),
//                            gv_gift_msg.getText().toString(),mProgressDialog) ;
//                }
//            });
