package com.example.fitmate.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fitmate.Diet;
import com.example.fitmate.Exercise;
import com.example.fitmate.Meditation;
import com.example.fitmate.R;
import com.example.fitmate.Sleep;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0:
                Diet tab1=new Diet();
                return tab1;
            case 1:
                Exercise tab2=new Exercise();
                return tab2;
            case 2:
                Sleep tab3=new Sleep();
                return tab3;
            case 3:
                Meditation tab4 = new Meditation();
                return tab4;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Diet";
            case 1:
                return "Exercise";
            case 2:
                return "Sleep";
            case 3:
                return "Meditation";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}