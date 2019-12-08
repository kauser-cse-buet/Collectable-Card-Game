package com.example.homework4_mahmmed;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowseCollectionFragment extends Fragment {
    private final Player player;
    private HashMap<Integer, Integer> cardCountMap;
    private List<Card> cardList;
    private List<Integer> cardCountList;

    public BrowseCollectionFragment(Player player) {
        // Required empty public constructor
        this.player = player;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_collection, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_browse_collection);


        cardCountMap = new HashMap<>();
        cardList = new ArrayList<>();
        cardCountList = new ArrayList<>();

        for (Card c: player.openedCards){
            Integer count = cardCountMap.get(c.getId());

            if (count == null){
                cardCountMap.put(c.getId(), 1);
            }
            else{
                cardCountMap.put(c.getId(), count + 1);
            }
        }

        for (Map.Entry<Integer, Integer> set: cardCountMap.entrySet()){
            Integer cardId = set.getKey();
            Card card = Card.getCardForCardId(cardId, player.openedCards);
            cardList.add(card);
            cardCountList.add(set.getValue());
        }

        ListView openedPackListContainer = (ListView) view.findViewById(R.id.opened_pack_container);
        MyAdapter adapter = new MyAdapter();
        openedPackListContainer.setAdapter(adapter);


        return view;

    }

    private class MyAdapter extends BaseAdapter {

        // override other abstract methods here


        @Override
        public int getCount() {
            return cardList.size();
        }

        @Override
        public Object getItem(int position) {
            return cardList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return cardList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.opened_pack_list_card, container, false);
            }

            ImageView imageView = ((ImageView) convertView.findViewById(R.id.opened_pack_card_image));
            imageView.setImageResource(cardList.get(position).getImageResourceId());

            ((TextView) convertView.findViewById(R.id.opened_pack_card_name))
                    .setText(cardList.get(position).getName());

            ((TextView) convertView.findViewById(R.id.opened_pack_card_count))
                    .setText("Count: " + cardCountList.get(position));
            return convertView;
        }
    }

}
