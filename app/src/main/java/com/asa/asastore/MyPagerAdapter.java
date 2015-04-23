/*
 * Copyright (c) 2015. Aprijal Pasaribu
 */

package com.asa.asastore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by APRIJAL_PASARIBU on 23/04/2015.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Home();
            case 1:
                return new Shopping();
            case 2:
                return new Favorite();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
