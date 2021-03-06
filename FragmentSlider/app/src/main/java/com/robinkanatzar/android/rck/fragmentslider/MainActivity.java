package com.robinkanatzar.android.rck.fragmentslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SimpleFragmentPagerAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize a list of three fragments
        List<SImpleFragment> fragmentList = new ArrayList<>();

        // Add three new Fragments to the list
        fragmentList.add(SImpleFragment.newInstance("1"));
        fragmentList.add(SImpleFragment.newInstance("2"));
        fragmentList.add(SImpleFragment.newInstance("3"));

        pageAdapter = new SimpleFragmentPagerAdapter (getFragmentManager(), fragmentList);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(pageAdapter);

    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        // A List to hold our fragments
        private List<Fragment> fragments;

        // A constructor to receive a fragment manager and a List
        public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            // Call the super class' version
            // of this constructor
            super(fm);
            this.fragments = fragments;
        }

        // Just two methods to override
        // to get the current position of
        // the adapter and the size of the List

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }



}
