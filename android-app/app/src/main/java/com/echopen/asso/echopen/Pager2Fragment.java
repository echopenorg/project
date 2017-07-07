package com.echopen.asso.echopen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pager2Fragment extends Fragment {


    public Pager2Fragment() {
        // Required empty public constructor
    }

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class MainPageAdapter extends FragmentStatePagerAdapter {
        private final List fragments;

        public MainPageAdapter(FragmentManager fm, List fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pager2, container, false);

        List fragments = new Vector<>();

        fragments.add(Fragment.instantiate(getContext(), SeanceListFragment.class.getName()));
        fragments.add(Fragment.instantiate(getContext(), ProfilFragment.class.getName()));
        fragments.add(Fragment.instantiate(getContext(), FaqFragment.class.getName()));

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) v.findViewById(R.id.pager2);
        mPagerAdapter = new MainPageAdapter(getActivity().getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);

        String buttonSelected = getArguments().getString("button_selected");
        switch (buttonSelected){
            case "seances":
                mPager.setCurrentItem(0);
                break;
            case "profil":
                mPager.setCurrentItem(1);
                break;
            case "faq":
                mPager.setCurrentItem(2);
                break;
        }

        return v;
    }

}