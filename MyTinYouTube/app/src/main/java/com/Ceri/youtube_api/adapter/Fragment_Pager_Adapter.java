package com.Ceri.youtube_api.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.Ceri.youtube_api.Fragment.ConfigurationScreen;
import com.Ceri.youtube_api.Fragment.FavouriteScreen;
import com.Ceri.youtube_api.Fragment.HomeScreen;

public class Fragment_Pager_Adapter extends FragmentPagerAdapter {

    // This adapter is created to manage all the fragments
    public Fragment_Pager_Adapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
//Logic to setting fragments at their proper positon
        switch (position) {
            case 0:
                fragment = new HomeScreen();
                break;

            case 1:
                fragment = new FavouriteScreen();

                break;

            case 2:
                fragment = new ConfigurationScreen();
                break;


        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
