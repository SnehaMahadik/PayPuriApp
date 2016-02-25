package com.benow.interfaces;


import android.support.v4.app.Fragment;

/**
 * Created by swapnil13494 on 2/2/2016.
 */
public interface OnMobileFragmentsListener {

    void fragmentChange(Fragment fragment, String tag);

    void otpConfirmation();
}
