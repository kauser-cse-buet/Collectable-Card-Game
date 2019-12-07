package com.example.homework4_mahmmed;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchasePacksFragment extends Fragment {


    public PurchasePacksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_packs, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_purchase_packs);
        return view;
    }

}
