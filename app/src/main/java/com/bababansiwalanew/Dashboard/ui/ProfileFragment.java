package com.bababansiwalanew.Dashboard.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
 import com.bababansiwalanew.Login.ui.SignupScreen;
import com.bababansiwalanew.R;
import com.bababansiwalanew.Util.ApplicationConstant;
import com.bababansiwalanew.Util.UtilMethods;
import com.squareup.picasso.Picasso;

/**
 * Created by Lalit on 08-04-2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView name, mobile, email;
    Button changePassword, createUser,changePin;
    ImageView toggle, logout, logo;
    CardView pinPasswordLayoutContainer, pinPasswordChangeLayout;
    boolean flagPasscode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        name = (TextView) v.findViewById(R.id.name);
        mobile = (TextView) v.findViewById(R.id.mobile);
        email = (TextView) v.findViewById(R.id.email);
        logo = (ImageView) v.findViewById(R.id.logo);
        changePassword = (Button) v.findViewById(R.id.changePassword);
        changePin = (Button) v.findViewById(R.id.changePin1);
        createUser = (Button) v.findViewById(R.id.createUser);
        pinPasswordLayoutContainer = (CardView) v.findViewById(R.id.pinPasswordLayoutContainer);
        pinPasswordChangeLayout = (CardView) v.findViewById(R.id.pinPasswordChangeLayout);

        toggle = (ImageView) v.findViewById(R.id.toggle);
        logout = (ImageView) v.findViewById(R.id.logout);

        toggle.setOnClickListener(this);
        logout.setOnClickListener(this);
        pinPasswordLayoutContainer.setOnClickListener(this);
        pinPasswordChangeLayout.setOnClickListener(this);

        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String icon = myPrefs.getString(ApplicationConstant.INSTANCE.icon, null);
        String role = myPrefs.getString(ApplicationConstant.INSTANCE.RoleId, "");
       /* Glide.with(getActivity()).load(ApplicationConstant.INSTANCE.baseUrl + icon).error(R.drawable.defaultlogo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(logo);*/

        if(  role.equalsIgnoreCase("3")   ) {
                          createUser.setVisibility(View.GONE);
                      }else {
                          createUser.setVisibility(View.VISIBLE);
                      }
        Picasso.with(getActivity())
                .load(ApplicationConstant.INSTANCE.baseUrl + icon)
                .into(logo);

        initialValues();

        changePassword.setOnClickListener(this);
        changePin.setOnClickListener(this);
        createUser.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == changePassword) {

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.change_password, null);

            final TextInputLayout currentPasswordLayout = (TextInputLayout) view.findViewById(R.id.currentPasswordLayout);
            final EditText currentPassword = (EditText) view.findViewById(R.id.currentPassword);
            final TextInputLayout newPasswordLayout = (TextInputLayout) view.findViewById(R.id.newPasswordLayout);
            final EditText newPassword = (EditText) view.findViewById(R.id.newPassword);
            final TextInputLayout confirmPasswordLayout = (TextInputLayout) view.findViewById(R.id.confirmPasswordLayout);
            final EditText confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);

            final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(getActivity());

            dialog.setTitle("Forgot Password");
            dialog.setCancelable(false);
            dialog.setContentView(view);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int flag = 0;

                    if (currentPassword.getText() != null && currentPassword.getText().toString().trim().length() > 0) {
                        currentPasswordLayout.setErrorEnabled(false);
                    } else {
                        flag++;
                        currentPassword.setError(getResources().getString(R.string.previouspass_error));
                        currentPassword.requestFocus();
                    }

                    if (confirmPassword.getText() != null && confirmPassword.getText().toString().trim().length() > 0 && (newPassword.getText() != null &&
                            newPassword.getText().toString().trim().equalsIgnoreCase(confirmPassword.getText().toString().trim()))) {
                        confirmPasswordLayout.setErrorEnabled(false);
                    } else {
                        flag++;
                        confirmPassword.setError(getResources().getString(R.string.newpass_error));
                        confirmPassword.requestFocus();
                    }

                    if (newPassword.getText() != null && newPassword.getText().toString().trim().length() > 0) {
                        newPasswordLayout.setErrorEnabled(false);
                    } else {
                        flag++;
                        newPassword.setError(getResources().getString(R.string.password_error));
                        newPassword.requestFocus();
                    }

                    if (flag == 0) {
                        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {
                            dialog.dismiss();
                            UtilMethods.INSTANCE.changePassword(getActivity(), currentPassword.getText().toString().trim()
                                    , newPassword.getText().toString().trim());
                        } else {
                            UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                                    getResources().getString(R.string.network_error_message), 2);
                        }
                    }

                }
            });
            dialog.show();

        }

        if (v == pinPasswordChangeLayout) {

            ////////////////////////////////////////////////////////////////////////////////
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.password_verification, null);

            final TextInputLayout passwordTextLayout = (TextInputLayout) view.findViewById(R.id.passwordTextLayout);
            final EditText password = (EditText) view.findViewById(R.id.password);
            final EditText new_pin = (EditText) view.findViewById(R.id.new_pin);
            final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(getActivity());

            dialog.setCancelable(false);
            dialog.setContentView(view);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
            final String passwordpref = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (password.getText() != null && password.getText().toString().trim().equalsIgnoreCase(passwordpref)) {
                        passwordTextLayout.setErrorEnabled(false);

                        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {
                            dialog.dismiss();
                            UtilMethods.INSTANCE.changePinPassword(getActivity(), password.getText().toString().trim(),"");
                        } else {
                            UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                                    getResources().getString(R.string.network_error_message), 2);
                        }

                        dialog.dismiss();

                    } else {
                        password.setError("Please enter a valid Password !!");
                        password.requestFocus();
                    }
                }
            });
            dialog.show();
            ////////////////////////////////////////////////////////////////////////////////

        }

        if (v == toggle || v == pinPasswordLayoutContainer) {

            //Log.e("here flag", "status : " + flagPasscode);
            /*if (flagPasscode) {
                toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_off));
                UtilMethods.INSTANCE.pinpasscode(getActivity(), "", false);
                flagPasscode = false;
            } else {
                toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_on));
                UtilMethods.INSTANCE.pinpasscode(getActivity(), "", true);
                flagPasscode = true;
            }*/

            ////////////////////////////////////////////////////////////////////////////////
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.password_verification, null);

            final TextInputLayout passwordTextLayout = (TextInputLayout) view.findViewById(R.id.passwordTextLayout);
            final EditText password = (EditText) view.findViewById(R.id.password);
            final EditText new_pin = (EditText) view.findViewById(R.id.new_pin);
            new_pin.setVisibility(View.GONE);
            final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(getActivity());

            dialog.setCancelable(false);
            dialog.setContentView(view);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
            final String passwordpref = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (password.getText() != null && password.getText().toString().trim().equalsIgnoreCase(passwordpref)) {
                        passwordTextLayout.setErrorEnabled(false);
                        if (flagPasscode) {
                            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_off));
                            UtilMethods.INSTANCE.pinpasscode(getActivity(), "", false);
                            flagPasscode = false;
                        } else {
                            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_on));
                            UtilMethods.INSTANCE.pinpasscode(getActivity(), "", true);
                            flagPasscode = true;
                        }

                        dialog.dismiss();

                    } else {
                        password.setError("Please enter a valid Password !!");
                        password.requestFocus();
                    }
                }
            });
            dialog.show();
            ////////////////////////////////////////////////////////////////////////////////
        }

        if (v == createUser) {
            Intent createIntent = new Intent(getActivity(), SignupScreen.class);
            createIntent.putExtra("from","profile");
            startActivity(createIntent);
        }
        if (v == changePin) {



            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.password_verification, null);

            final TextInputLayout passwordTextLayout = (TextInputLayout) view.findViewById(R.id.passwordTextLayout);
            final EditText password = (EditText) view.findViewById(R.id.password);
            final EditText new_pin = (EditText) view.findViewById(R.id.new_pin);
            final AppCompatButton okButton = (AppCompatButton) view.findViewById(R.id.okButton);
            final AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.cancelButton);

            final Dialog dialog = new Dialog(getActivity());

            dialog.setCancelable(false);
            dialog.setContentView(view);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
            final String passwordpref = myPrefs.getString(ApplicationConstant.INSTANCE.UPassword, null);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (password.getText() != null && password.getText().toString().trim().equalsIgnoreCase(passwordpref)) {
                        passwordTextLayout.setErrorEnabled(false);

                        if (UtilMethods.INSTANCE.isNetworkAvialable(getActivity())) {
                            dialog.dismiss();
                            UtilMethods.INSTANCE.changePinPassword(getActivity(), password.getText().toString().trim(),new_pin.getText().toString().trim());
                        } else {
                            UtilMethods.INSTANCE.dialogOk(getActivity(), getResources().getString(R.string.network_error_title),
                                    getResources().getString(R.string.network_error_message), 2);
                        }

                        dialog.dismiss();

                    } else {
                        password.setError("Please enter a valid Password !!");
                        password.requestFocus();
                    }
                }
            });
            dialog.show();

        }

        if (v == logout) {
           /* UtilMethods.INSTANCE.setLoginPref(getActivity(), "", "", "", "", "", "", "", "", "", "", "", "");
            Intent startIntent = new Intent(getActivity(), SplashScreen.class);
            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(startIntent);*/
            UtilMethods.INSTANCE.Logout(getActivity(), null);
        }
    }

    public void initialValues() {

        SharedPreferences myPrefs = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        String IsExist = myPrefs.getString(ApplicationConstant.INSTANCE.IsExist, null);
        String OTP = myPrefs.getString(ApplicationConstant.INSTANCE.OTP, null);
        String SessionID = myPrefs.getString(ApplicationConstant.INSTANCE.SessionID, null);
        String UMail = myPrefs.getString(ApplicationConstant.INSTANCE.UMail, null);
        String UMobile = myPrefs.getString(ApplicationConstant.INSTANCE.UMobile, null);
        String UName = myPrefs.getString(ApplicationConstant.INSTANCE.UName, null);

        if (UMobile != null && UMobile.length() > 0) {
            mobile.setText(" "+UMobile);
        }
        if (UMail != null && UMail.length() > 0) {
            email.setText(" "+UMail);
        }
        if (UName != null && UName.length() > 0) {
            name.setText(" "+UName);
        }
        //////////////////////////////////////////////////////////////////////////
        SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNameLoginPref, getActivity().MODE_PRIVATE);
        flagPasscode = myPreferences.getBoolean(ApplicationConstant.INSTANCE.PinPasscodeFlag, false);

        // Log.e("here", "flag : " + flagPasscode);

        if (flagPasscode) {
            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_on));
        } else {
            toggle.setBackground(getResources().getDrawable(R.drawable.ic_toggle_off));
        }

        if (!UtilMethods.INSTANCE.getRoleId(getActivity()).equalsIgnoreCase("3")) {
            createUser.setVisibility(View.VISIBLE);
        } else {
            createUser.setVisibility(View.GONE);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");
        //LinearLayout mLinearLayout=(LinearLayout)  ((AppCompatActivity) getActivity()).findViewById(R.id.layout_name1);
        // mLinearLayout.setVisibility(View.GONE);
        super.onActivityCreated(savedInstanceState);
    }
}