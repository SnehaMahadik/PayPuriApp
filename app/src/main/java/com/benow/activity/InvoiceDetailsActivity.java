package com.benow.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.benow.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class InvoiceDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOrderNo, tvOrderDate, tvOrderTotal, tvAddr1, tvAddr2, tvAddr3, tvItemCount, tvDeliveryCharge, tvVat, tvTotal, tvFinalOrderTotal, tvItemPrice;
    ImageView ivClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoice_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle bundle = getIntent().getExtras();
        tvOrderNo= (TextView) findViewById(R.id.tvOrderNo);
        tvOrderDate = (TextView) findViewById(R.id.tvOrderDate);
        tvOrderTotal= (TextView) findViewById(R.id.tvOrderTotal);
        tvAddr1= (TextView) findViewById(R.id.tvAddr1);
        tvAddr2= (TextView) findViewById(R.id.tvAddr2);
        tvAddr3= (TextView) findViewById(R.id.tvCity);
        tvItemCount= (TextView) findViewById(R.id.tvItemsCount);
        tvDeliveryCharge= (TextView) findViewById(R.id.tvdelivaryCharege);
        tvVat= (TextView) findViewById(R.id.tvTotalVat);
        tvTotal= (TextView) findViewById(R.id.tvTotalAmount);
        tvFinalOrderTotal= (TextView) findViewById(R.id.tvOrderTotalAmount);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        tvItemPrice = (TextView) findViewById(R.id.tvProductPrice);

        tvOrderDate.setText(getString(R.string.str_order_date)+"  "+getCurrentDate());
        tvOrderNo.setText(getString(R.string.str_order_no)+"  "+bundle.getString("orderno"));
        tvOrderTotal.setText(getString(R.string.str_order_total)+"  "+getString(R.string.rupee)+" "+bundle.getString("amount"));

        tvAddr1.setText("1st floor, Building no 1");
        tvAddr2.setText("Millennium Business Park");
        tvAddr3.setText("Navi Mumbai");

        tvItemCount.setText("1");
        tvDeliveryCharge.setText(getString(R.string.rupee)+" 00");
        tvVat.setText(getString(R.string.rupee)+" 00.00");
        tvTotal.setText(getString(R.string.rupee)+" "+bundle.getString("amount"));
        tvItemPrice.setText(getString(R.string.rupee)+" "+bundle.getString("amount"));
        tvFinalOrderTotal.setText(getString(R.string.rupee)+" "+bundle.getString("amount"));

        ivClose.setOnClickListener(this);

    }

    private String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return  formattedDate;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ivClose){
            this.finish();
        }
    }
}
