package com.benow.models;

/**
 * Created by sneha13498 on 2/17/2016.
 */
public class DrawerChildItems implements DrawerItem {

    public  String mDrawerTitle;
    public  int mDrawerImage;

    public String getDrawerTitle() {
        return mDrawerTitle;
    }

    public void setDrawerTitle(String mDrawerTitle) {
        this.mDrawerTitle = mDrawerTitle;
    }

    public int getDrawerImage() {
        return mDrawerImage;
    }

    public void setDrawerImage(int mDrawerImage) {
        this.mDrawerImage = mDrawerImage;
    }

    public DrawerChildItems(String title,int  draweritemimage) {
        this.mDrawerTitle = title;
        this.mDrawerImage = draweritemimage;
    }



    @Override
    public boolean isSection() {
        return false;
    }
}
