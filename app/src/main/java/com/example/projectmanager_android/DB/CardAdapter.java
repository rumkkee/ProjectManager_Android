package com.example.projectmanager_android.DB;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.projectmanager_android.CardDisplayer;

public class CardAdapter extends ListAdapter<Card, CardViewHolder> {
    private CardDisplayer mCardDisplayer;

    public CardAdapter(@NonNull DiffUtil.ItemCallback<Card> diffCallback, CardDisplayer cardDisplayer) {
        super(diffCallback);
        mCardDisplayer = cardDisplayer;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CardViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card currentCard = getItem(position);
        holder.bind(currentCard.getCardId(), currentCard.getTitle(), mCardDisplayer);
    }

    public static class CardDiff extends DiffUtil.ItemCallback<Card>{

        @Override
        public boolean areItemsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Card oldItem, @NonNull Card newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

}
