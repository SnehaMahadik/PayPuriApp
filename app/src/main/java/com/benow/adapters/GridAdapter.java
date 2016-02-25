package com.benow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.benow.R;
import com.benow.models.GridItems;


import java.util.ArrayList;

/**
 * Created by sneha13498 on 2/4/2016.
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<GridItems> mGridItemsArrayList;
    int width = 0, height = 0;

    // Constructor
    public GridAdapter(Context c, ArrayList<GridItems> mGridItemsArrayList1, int dpWidth, int dpHeight) {
        mContext = c;
        mGridItemsArrayList=mGridItemsArrayList1;
        this.height=dpHeight;
        this.width=dpWidth;
    }

    public int getCount() {
        return mGridItemsArrayList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.grid_item , null);
            gridView.setLayoutParams(new AbsListView.LayoutParams(width,height));

            // set value into textview

            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);

            textView.setText(mGridItemsArrayList.get(position).getGridItemLabel());


            // set image based on selected text

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);
imageView.setImageResource(mGridItemsArrayList.get(position).getGridItemImage());
        } else {

            gridView = (View) convertView;
        }

        return gridView;
    }


}
