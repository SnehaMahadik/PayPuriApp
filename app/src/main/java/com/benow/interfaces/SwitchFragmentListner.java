package com.benow.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by swapnil13494 on 2/3/2016.
 */
public interface SwitchFragmentListner {

    void onReplaceFragment(Fragment fragment, String tag);

    void onAddFragment(Fragment fragment, String tag);
}
