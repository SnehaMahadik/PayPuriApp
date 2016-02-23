package com.mastek.paypuriapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastek.paypuriapp.R;

public class TermsnConditionsActivity extends AppCompatActivity {
    TextView mTextViewTitle;
    static TextView mTextViewContent;
    ImageView imageCross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsn_conditions);
        Intent i = getIntent();
        Bundle extras=i.getExtras();

        imageCross= (ImageView) findViewById(R.id.buttonCross);
        imageCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        mTextViewContent= (TextView) findViewById(R.id.textViewContent);
        mTextViewTitle= (TextView) findViewById(R.id.textViewTitle);
        if(extras !=null) {
            // This is necessary for the retrv_value
            mTextViewContent.setText(extras.getString("DATA"));
            mTextViewTitle.setText(extras.getString("TITLE"));


        }
    }

}
