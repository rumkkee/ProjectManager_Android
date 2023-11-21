package com.example.projectmanager_android.DB;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CardListAdapter extends ListAdapter<CardList, CardListViewHolder> {


    public CardListAdapter(@NonNull DiffUtil.ItemCallback<CardList> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CardListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListViewHolder holder, int position) {
        CardList currentCardList = getItem(position);
        holder.bind(currentCardList.getListId(), currentCardList.getTitle());
    }

    public static class CardListDiff extends DiffUtil.ItemCallback<CardList>{

        @Override
        public boolean areItemsTheSame(@NonNull CardList oldItem, @NonNull CardList newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CardList oldItem, @NonNull CardList newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

}
