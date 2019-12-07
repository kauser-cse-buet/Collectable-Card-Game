package com.example.homework4_mahmmed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardInfoAdapter extends RecyclerView.Adapter<CardInfoAdapter.ViewHolder> {
    private final List<Card> cardList;
    private OnNoteListener onNoteListener;

    public CardInfoAdapter(List<Card> cardList, OnNoteListener onNoteListener) {

        this.cardList = cardList;
        this.onNoteListener = onNoteListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CardView cardView;
        OnNoteListener onNoteListener;

        public ViewHolder(CardView cardView, OnNoteListener onNoteListener) {
            super(cardView);
            this.cardView = cardView;
            this.cardView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercard_card_profile, parent, false);
        return new ViewHolder(cv, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        final Card card = cardList.get(position);

        ImageView cardImage= (ImageView) cardView.findViewById(R.id.image_card);
        cardImage.setImageResource(card.getImageResourceId());

        TextView cardNameTextView = (TextView) cardView.findViewById(R.id.text_card_name);
        cardNameTextView.setText(card.getName());

        TextView cardPriceTextView = (TextView) cardView.findViewById(R.id.text_card_price);
        cardPriceTextView.setText("Price: " + card.getPrice());

//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(v.getContext(), card.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public interface OnNoteListener{
        void onNoteClicked(int position);
    }
}
