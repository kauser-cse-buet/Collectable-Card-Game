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
public class BrowseCollectionFragment extends Fragment {


    public BrowseCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_collection, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_browse_collection);
        return view;

    }

}