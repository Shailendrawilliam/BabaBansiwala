package com.bababansiwalanew.Dashboard.ui;

import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentManagerHelper {
    FragmentManager fragmentManager;
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void StartFragment(int id, Fragment ft, String BackStack){
        getFragmentManager().beginTransaction().replace(id, ft).addToBackStack(BackStack).commit();
    }
}
