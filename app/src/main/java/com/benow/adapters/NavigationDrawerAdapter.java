package com.benow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.benow.R;
import com.benow.models.DrawerChildItems;
import com.benow.models.DrawerHeaderItem;
import com.benow.models.DrawerItem;

import java.util.ArrayList;

/**
 * Created by sneha13498 on 2/17/2016.
 */
public class NavigationDrawerAdapter extends  ArrayAdapter<DrawerItem> {
    private Context context;
    private ArrayList<DrawerItem> items;
    private LayoutInflater vi;

    public NavigationDrawerAdapter(Context context,ArrayList<DrawerItem> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final DrawerItem i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                DrawerHeaderItem si = (DrawerHeaderItem)i;
                v = vi.inflate(R.layout.navigationdrawer_header_item, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.textviewDHeader);
                sectionView.setText(si.getDrawerHeader());

            }else{
                DrawerChildItems ei = (DrawerChildItems)i;
                v = vi.inflate(R.layout.navigationdrawer_child_item, null);
                final ImageView mImageDrawer = (ImageView)v.findViewById(R.id.imageViewdraweritem);
                final TextView title = (TextView)v.findViewById(R.id.textviewDchild);


                if (title != null)
                    title.setText(ei.getDrawerTitle());
                if(mImageDrawer != null)
                    mImageDrawer.setImageResource(ei.getDrawerImage());
            }
        }
        return v;
    }

}