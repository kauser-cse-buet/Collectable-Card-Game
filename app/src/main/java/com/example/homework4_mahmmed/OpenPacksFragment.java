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
public class OpenPacksFragment extends Fragment {


    private final Player player;

    public OpenPacksFragment(Player player) {
        // Required empty public constructor
        this.player = player;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_packs, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_open_packs);
        return view;
    }

}
