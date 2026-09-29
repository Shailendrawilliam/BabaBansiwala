package com.bababansiwalanew.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.WindowManager;

import com.bababansiwalanew.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static cn.pedant.SweetAlert.SweetAlertDialog.ERROR_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.NORMAL_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;
import static cn.pedant.SweetAlert.SweetAlertDialog.WARNING_TYPE;

//import com.skrechargenew.Fragments.Adapter.DashboardOptionListAdapter;

public class CustomAlertDialog {

    private Context context;
    private SweetAlertDialog alertDialog;
    boolean isScreenOpen;
    AlertDialog alertDialogLogout;
    private AlertDialog alertDialogServiceList;

    public CustomAlertDialog(Context context, boolean isScreenOpen) {
        try {
            this.context = context;
            this.isScreenOpen = isScreenOpen;
            alertDialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                }
            });
       /* TextView text = (TextView) alertDialog.findViewById(R.id.content_text);
        text.setGravity(Gravity.CENTER);
        //text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        text.setSingleLine(false);
        text.setMaxLines(10);
        text.setLines(6);*/
        } catch (IllegalStateException ise) {

        } catch (Exception e) {

        }

    }

    public SweetAlertDialog returnDialog() {
        return alertDialog;
    }

    public void Failed(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(ERROR_TYPE);
                if (message != null && !message.isEmpty() && message.length() > 1) {
                    alertDialog.setContentText(message);
                } else {
                    alertDialog.setContentText("Failed");
                }

                // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void Successful(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                if (message != null && !message.isEmpty() && message.length() > 1) {
                    alertDialog.setContentText(message);
                } else {
                    alertDialog.setContentText("Success");
                }
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void SuccessfulWithTitle(final String title, final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                alertDialog.setTitle(title);
                alertDialog.setContentText(message);

                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void ErrorWithTitle(final String title, final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(ERROR_TYPE);
                alertDialog.setTitle(title);
                alertDialog.setContentText(message);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }




    public void SuccessfulWithFinsh(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity) context).finish();
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void SuccessfulWithCallBack(final String message, final Activity activity) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        activity.finish();
                    }
                });
                alertDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();

                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void WarningWithCallBack(final String message, String posBtn, boolean isCancelable, final DialogCallBack dialogCallBack) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(WARNING_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setCancelable(isCancelable);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton(posBtn, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        if (dialogCallBack != null) {
                            dialogCallBack.onPositiveClick();
                        }
                    }
                });
                alertDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        if (dialogCallBack != null) {
                            dialogCallBack.onNegativeClick();
                        }
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void WarningWithCallBack(final String message,final String title, String posBtn, boolean isCancelable, final DialogCallBack dialogCallBack) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(WARNING_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setCancelable(isCancelable);
                alertDialog.setTitle(title);
                alertDialog.setConfirmButton(posBtn, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        alertDialog.dismiss();
                        if (dialogCallBack != null) {
                            dialogCallBack.onPositiveClick();
                        }
                    }
                });
                alertDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        alertDialog.dismiss();
                        if (dialogCallBack != null) {
                            dialogCallBack.onNegativeClick();
                        }
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }


    public void WarningWithSingleBtnCallBack(final String message, String posBtn, boolean isCancelable, final DialogCallBack dialogCallBack) {
        if (isScreenOpen) {
            try {

                if (alertDialog != null && alertDialog.isShowing()) {
                    return;
                }

                alertDialog.changeAlertType(WARNING_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setCancelable(isCancelable);
                // alertDialogSingleBtn.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton(posBtn, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        if (dialogCallBack != null) {
                            dialogCallBack.onPositiveClick();
                        }
                    }
                });

                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }


    //////////////



/*
    public void serviceListDialog(final int parentId, String title, final ArrayList<AssignedOpType> opTypes, final DialogServiceListCallBack mDialogServiceListCallBack) {
        try {
            if (alertDialogServiceList != null && alertDialogServiceList.isShowing()) {
                return;
            }


            AlertDialog.Builder dialogBuilder;
            dialogBuilder = new AlertDialog.Builder(context);
            alertDialogServiceList = dialogBuilder.create();
            alertDialogServiceList.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
           // LayoutInflater inflater = context.getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_service_list, null);
            alertDialogServiceList.setView(dialogView);

            ImageView iconTv = dialogView.findViewById(R.id.icon);
            ImageView bgView = dialogView.findViewById(R.id.bgView);
            RelativeLayout imageContainer = dialogView.findViewById(R.id.imageContainer);
            RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(context, opTypes.size() > 2 ? 3 : 2));
            AppCompatImageView closeIv = dialogView.findViewById(R.id.closeIv);
            TextView titleTv = dialogView.findViewById(R.id.titleTv);

            titleTv.setText(title);
            //   iconTv.setImageResource(ServiceIcon.INSTANCE.parentIcon(parentId));

            DashboardOptionListAdapter mDashboardOptionListAdapter = new DashboardOptionListAdapter(opTypes, context, new DashboardOptionListAdapter.ClickView() {
                @Override
                public void onClick(int serviceId, int parentId, String serviceParent, ArrayList<AssignedOpType> subOpTypeList) {
                    alertDialogServiceList.dismiss();
                    if (mDialogServiceListCallBack != null) {
                        mDialogServiceListCallBack.onIconClick(serviceId);
                    }
                }

           */
/*     @Override
                public void onClick(int serviceId, int parentId, String serviceParent, ArrayList<AssignedOpType> subOpTypeList) {
                    alertDialogServiceList.dismiss();
                    if (mDialogServiceListCallBack != null) {
                        mDialogServiceListCallBack.onIconClick(serviceId);
                    }

                }*//*

            }, R.layout.adapter_dashboard_option_grid);
            recyclerView.setAdapter(mDashboardOptionListAdapter);

            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogServiceList.dismiss();
                }
            });


            alertDialogServiceList.show();

        } catch (IllegalStateException ise) {

        } catch (IllegalArgumentException iae) {

        } catch (Exception e) {

        }
    }
*/

    ////////////////

//    public void Successfullogout(final String message, final Activity activity) {
//        if (isScreenOpen) {
//            try {
//                if (alertDialogLogout != null && alertDialogLogout.isShowing()) {
//                    return;
//                }
//                alertDialogLogout = new AlertDialog.Builder(activity).create();
//
//                alertDialogLogout.setTitle("Logout!");
//
//                alertDialogLogout.setMessage(message);
//
//                alertDialogLogout.setButton(AlertDialog.BUTTON_POSITIVE, "Logout From All Device", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        UtilMethods.INSTANCE.Logout(activity, "3");
//
//                    }
//                });
//
//                alertDialogLogout.setButton(AlertDialog.BUTTON_NEGATIVE, "Logout ", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        UtilMethods.INSTANCE.Logout(activity, "1");
//
//
//                    }
//                });
//
//                alertDialogLogout.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        dissmiss();
//
//                    }
//                });
//                alertDialogLogout.show();
//
//              /*  alertDialog.changeAlertType(SUCCESS_TYPE);
//                alertDialog.setContentText(message);
//                // alertDialog.setCustomImage(R.drawable.ic_success);
//                alertDialog.setConfirmButton("Logout From All Device", new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        UtilMethods.INSTANCE.logout(activity);
//                        activity.finish();
//                    }
//                });
//                alertDialog.setNeutralButton("Logout", new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        UtilMethods.INSTANCE.logout(activity);
//                        activity.finish();
//                    }
//                });
//                alertDialog.show();
//                alertDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        sweetAlertDialog.dismiss();
//
//                    }
//                });
//                alertDialog.show();*/
//            } catch (WindowManager.BadTokenException bte) {
//
//            } catch (IllegalStateException ise) {
//
//            } catch (Exception e) {
//
//            }
//        }
//    }

    public void Successfulok(final String message, final Activity activity) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        activity.finish();
                    }
                });
                alertDialog.show();

            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void Errorok(final String message, final Activity activity) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(ERROR_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        activity.finish();
                    }
                });
                alertDialog.show();

            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void Warning(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(WARNING_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }
    public void Warning(final String title, final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(WARNING_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setTitle(title);
                // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void Error(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(ERROR_TYPE);
                if (message != null && !message.isEmpty() && message.length() > 1) {
                    alertDialog.setContentText(message);

                } else {
                    alertDialog.setContentText("Error");
                }
                // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }


    public void ErrorWithFinsh(final String message) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(ERROR_TYPE);
                alertDialog.setContentText(message);
                // alertDialog.setCustomImage(R.drawable.ic_error_red_24dp);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity) context).finish();
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void SuccessfulWithFinsh(final String message, boolean isCancelable) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(SUCCESS_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setCancelable(isCancelable);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity) context).finish();
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void AlertLowBalance(final String message, boolean isCancelable) {
        if (isScreenOpen) {
            try {
                alertDialog.changeAlertType(NORMAL_TYPE);
                alertDialog.setContentText(message);
                alertDialog.setCancelable(isCancelable);
                // alertDialog.setCustomImage(R.drawable.ic_success);
                alertDialog.setConfirmButton("Ok", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                alertDialog.show();
            } catch (WindowManager.BadTokenException bte) {

            } catch (IllegalStateException ise) {

            } catch (Exception e) {

            }
        }
    }

    public void dissmiss() {
        try {
            if (alertDialog != null && alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        } catch (WindowManager.BadTokenException bte) {

        } catch (IllegalStateException ise) {

        } catch (Exception e) {

        }
    }

    public interface DialogCallBack {
        void onPositiveClick();

        void onNegativeClick();
    }


    public interface DialogServiceListCallBack {
        void onIconClick(int serviceId);


    }


}
