package com.example.homework4_mahmmed;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardInfoAdapter extends RecyclerView.Adapter<CardInfoAdapter.ViewHolder> {
    private final List<Card> cardList;

    public CardInfoAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercard_card_profile, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        Card card = cardList.get(position);

        ImageView cardImage= (ImageView) cardView.findViewById(R.id.image_card);
        cardImage.setImageResource(card.getCardImageResourceId());

        TextView cardNameTextView = (TextView) cardView.findViewById(R.id.text_card_name);
        cardNameTextView.setText(card.getCardName());

        TextView cardPriceTextView = (TextView) cardView.findViewById(R.id.text_card_price);
        cardPriceTextView.setText("Price: " + card.getCardPrice());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
