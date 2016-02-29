package com.benow.utils;

import android.content.Context;
import android.widget.ImageView;


import com.benow.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by sneha13498 on 2/13/2016.
 */
public class CircularImageview {
    private Context mContext;

    public static void makeCircularImage(ImageView ivLogo, Context mContext, String uri) {


        // ----------------------------------------------------------------
        // apply rounding to image
        // see: https://github.com/vinc3m1/RoundedImageView
        // ----------------------------------------------------------------
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(mContext.getResources().getColor(R.color.colorPrimary))
                .borderWidthDp(1)
                .cornerRadiusDp(80)
                .oval(false)
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)
                .build();

        Picasso.with(mContext)
                .load(uri)

                .resize(250,250)
                .transform(transformation)
                .into(ivLogo);
    }
}
