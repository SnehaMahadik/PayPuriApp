package com.benow.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.benow.R;


/**
 * Created by swapnil13494 on 2/23/2016.
 */
public class SelectBankDialog extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_select_bank, container,
                false);
        getDialog().setTitle("DialogFragment Tutorial");
        // Do something else
        return rootView;
    }

}
