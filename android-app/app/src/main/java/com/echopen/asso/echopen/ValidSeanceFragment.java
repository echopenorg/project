package com.echopen.asso.echopen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValidSeanceFragment extends Fragment {


    public ValidSeanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.valid_seance, container, false);

        if (v.findViewById(R.id.button_valid_seance)!= null){
            v.findViewById(R.id.button_valid_seance).setOnClickListener((MainActivity) getActivity());
        }

        return v;
    }
}
