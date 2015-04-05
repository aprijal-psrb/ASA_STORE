package com.asa.asastore;

/**
 * Created by APRIJAL_PASARIBU on 03/04/2015.
 */
public class FavoriteCategory {
    private String mCategoryName;
    private String mCategoryColor;

    public FavoriteCategory(){}

    public void setCategoryName(String arg){
        this.mCategoryName = arg;
    }

    public void setCategoryColor(String arg){
        this.mCategoryColor = arg;
    }

    public String getCategoryName(){
        return this.mCategoryName;
    }

    public String getCategoryColor(){
        return this.mCategoryColor;
    }
}
