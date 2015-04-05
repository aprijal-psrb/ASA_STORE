package com.asa.asastore;

import android.widget.TextView;

/**
 * Created by APRIJAL_PASARIBU on 02/04/2015.
 */
public class MyItem {

    public TextView textView;

    private int itemId;
    private String itemCategory;
    private String itemDescription;

    public MyItem(){}

    public void setId(int id){
        this.itemId = id;
    }

    public void setCategory(String category){
        this.itemCategory = category;
    }

    public void setDescription(String description){
        this.itemDescription = description;
    }

    public int getId(){
        return itemId;
    }

    public String getCategory(){
        return itemCategory;
    }

    public String getDescription(){
        return itemDescription;
    }
}
