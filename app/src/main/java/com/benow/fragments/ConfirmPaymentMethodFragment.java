package com.benow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.dialog.InvoiceDetailsDialog;
import com.benow.dialog.SelectBankDialog;


/**
 *
 */
public class ConfirmPaymentMethodFragment extends Fragment implements View.OnClickListener , Response.Listener, Response.ErrorListener{

    private Context mContext;
//    private Spinner mSpinner;
    private Button btnConfirmPayment;
    private Bundle bundle;
    private String strAmount;
    private TextView tvAmountPayable, tvShowBalance, btnViewInvoiceDetails;
    private TextView tvChangeBank;
    private Spinner mSpinner;

    public ConfirmPaymentMethodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
            strAmount = bundle.getString("amount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_bank, container, false);

        btnViewInvoiceDetails = (TextView) view.findViewById(R.id.btnInvoiceDetails);
        btnConfirmPayment = (Button) view.findViewById(R.id.btnConfirmPayment);
        tvAmountPayable = (TextView) view.findViewById(R.id.tvAmountPayable);
        tvShowBalance = (TextView) view.findViewById(R.id.tvShowBal);
        tvChangeBank = (TextView) view.findViewById(R.id.tvChangeAccount);
        btnConfirmPayment.setOnClickListener(this);
        btnViewInvoiceDetails.setOnClickListener(this);
        mSpinner = (Spinner) view.findViewById(R.id.spBanks);
        tvAmountPayable.setText(getString(R.string.rupee) + " " + strAmount);
        tvShowBalance.setOnClickListener(this);
        tvChangeBank.setOnClickListener(this);

        Spannable wordtoSpan = new SpannableString(getString(R.string.str_view_acc_bal));

        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new UnderlineSpan(), 0, wordtoSpan.length(), 0);
        tvShowBalance.setText(wordtoSpan);

        Spannable wordtoSpan1 = new SpannableString(getString(R.string.str_view_invoice_details));
        wordtoSpan1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, wordtoSpan1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan1.setSpan(new UnderlineSpan(), 0, wordtoSpan1.length(), 0);
        btnViewInvoiceDetails.setText(wordtoSpan1);

        final String[] banks = mContext.getResources().getStringArray(R.array.array_accountname);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                R.layout.support_simple_spinner_dropdown_item, banks);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle.putString("bank", banks[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bundle.putString("bank", banks[0]);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInvoiceDetails) {
            InvoiceDetailsDialog invoiceDetailsDialog = new InvoiceDetailsDialog();
            invoiceDetailsDialog.show(getActivity().getSupportFragmentManager(),"Invoice Details Fragment");

//            Intent intent = new Intent(mContext, InvoiceDetailsActivity.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
        } else if (v.getId() == R.id.btnConfirmPayment) {
            MpinFragment newFragment = new MpinFragment();
            newFragment.setArguments(bundle);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack("MpinFragment");
            transaction.commit();
        } else if (v.getId() == R.id.tvShowBal) {
            Toast.makeText(getActivity(), "Bank : " + (bundle.getString("bank").split("-"))[0] + "\n" + "Account No : " + (bundle.getString("bank").split("-"))[1] + "\n" + "Balance : " + getString(R.string.rupee) + "45,666", Toast.LENGTH_LONG).show();
        }else if(v.getId() == R.id.tvChangeAccount){
            SelectBankDialog selectBankDialog = new SelectBankDialog();
            selectBankDialog.show(getActivity().getSupportFragmentManager(),"SelectBankDialog");
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
}
