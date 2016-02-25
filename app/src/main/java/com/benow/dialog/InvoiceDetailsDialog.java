package com.benow.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benow.R;


/**
 * Created by swapnil13494 on 2/20/2016.
 */
public class InvoiceDetailsDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_invoice_details, container,
                false);
        getDialog().setTitle("DialogFragment Tutorial");
        // Do something else
        return rootView;
    }

}
