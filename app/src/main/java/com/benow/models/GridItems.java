package com.benow.models;

/**
 * Created by sneha13498 on 2/4/2016.
 */
public class GridItems {

    private  String mGridItemLabel;
    private  int mGridItemImage;
    private static GridItems mGridItemInstance = null;



    public static GridItems getInstance(){
        if(mGridItemInstance == null)
        {
            mGridItemInstance = new GridItems();
        }
        return mGridItemInstance;
    }

    public String getGridItemLabel() {
        return mGridItemLabel;
    }

    public void setGridItemLabel(String mGridItemLabel) {
        this.mGridItemLabel = mGridItemLabel;
    }

    public int getGridItemImage() {
        return mGridItemImage;
    }

    public void setGridItemImage(int mGridItemImage) {
        this.mGridItemImage = mGridItemImage;
    }


}
