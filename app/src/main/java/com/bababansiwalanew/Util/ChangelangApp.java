package com.bababansiwalanew.Util;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class ChangelangApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
         }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        }
}