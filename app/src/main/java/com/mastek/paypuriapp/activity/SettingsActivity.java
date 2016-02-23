package com.mastek.paypuriapp.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mastek.paypuriapp.R;

public class SettingsActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageView imageViewChnageEmailid,imageViewChnagePassword;
    private EditText editTextEmailId;
    RelativeLayout mRelChangePassword,mRelChangeMPIN,mRelBankPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        imageViewChnageEmailid=(ImageView)findViewById(R.id.imageViewChnageEmailid);
        imageViewChnagePassword=(ImageView)findViewById(R.id.imageViewChangePassword);

        mRelChangePassword=(RelativeLayout)findViewById(R.id.relchangePassword);
        mRelChangeMPIN=(RelativeLayout)findViewById(R.id.relChangeMPIN);
        mRelBankPreferences=(RelativeLayout)findViewById(R.id.relBankPref);

        imageViewChnageEmailid.setOnClickListener(this);
        imageViewChnagePassword.setOnClickListener(this);
        mRelChangePassword.setOnClickListener(this);
        mRelChangeMPIN.setOnClickListener(this);
        mRelBankPreferences.setOnClickListener(this);


        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setTitle("Settings");

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.imageViewChangePassword:
                
                break;
            case R.id.imageViewChnageEmailid:
                ChangeEmailID();
                break;
            case R.id.relchangePassword:
               startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                break;
            case R.id.relChangeMPIN:
                startActivity(new Intent(getApplicationContext(),ChangeMPINActivity.class));
                break;
            default:break;
        }

    }

    private void ChangeEmailID() {
        /*final AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.layout_changeemailid);
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.layout_changeemailid, null));
      //  builder.create();
        //dialog.setTitle("NCPI");

        // set the custom dialog components - text, image and button
        //  editTextEmailId = (EditText) dialog.findViewById(R.id.mpin);
        //Button btnContinue = (Button) dialog.findViewById(R.id.image);
        //image.setImageResource(R.drawable.ic_launcher);

        // Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonContinue);
        // if button is clicked, close the custom dialog
        *//*dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextEmailId.getText().toString().equalsIgnoreCase("")) {
                    dialog.dismiss();
                   // addSuccessFragment();
                }
                else {
                    {
                        //Toast.makeText(getActivity(), getResources().getString(R.string.Please_Enter_MPIN), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });*//*
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                Toast.makeText(getApplicationContext(),"Email Id updated successfuly",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.cancel();
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {                    //
                Button positiveButton = ((AlertDialog) dialog)
                        .getButton(AlertDialog.BUTTON_POSITIVE);
                //positiveButton.setTextColor();

                Button negativeButton = ((AlertDialog) dialog)
                        .getButton(AlertDialog.BUTTON_NEGATIVE);
                positiveButton.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });


        builder.show();*/

        new MaterialDialog.Builder(this)

                .title("Change Email Id")
                .content("Please enter your new email id")
                .positiveColorRes(R.color.blue)
                .negativeColorRes(R.color.blue)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .positiveText("Update")
                .negativeText("cancle")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // TODO
                        Toast.makeText(getApplicationContext(),"Email Id updated successfuly", Toast.LENGTH_SHORT).show();
                    }
                })

                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // TODO
                    }
                })
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                    }
                })

                .show();
    }}
