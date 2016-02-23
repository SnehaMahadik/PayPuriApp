package com.mastek.paypuriapp.models;

/**
 * Created by sneha13498 on 2/17/2016.
 */
public class DrawerHeaderItem implements DrawerItem {

    private  String mDrawerHeader;

    public DrawerHeaderItem(String title) {
        this.mDrawerHeader = title;
    }

    public String getDrawerHeader() {
        return mDrawerHeader;
    }

    public void setDrawerHeader(String mDrawerHeader) {
        this.mDrawerHeader = mDrawerHeader;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
