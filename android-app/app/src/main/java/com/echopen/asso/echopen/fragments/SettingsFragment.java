package com.echopen.asso.echopen.fragments;


import android.media.Image;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.echopen.asso.echopen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageButton btn_switch_to_main = (ImageButton) getView().findViewById(R.id.switch_page_settings);
        TextView textView = (TextView) getActivity().findViewById(R.id.param);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Lato-Bold.ttf");
        textView.setTypeface(typeface);

    }
}
