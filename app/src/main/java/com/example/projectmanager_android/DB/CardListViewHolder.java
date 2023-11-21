package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.R;

public class CardListViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardListItemView;
    private int cardListId;

    public CardListViewHolder(@NonNull View itemView) {
        super(itemView);
        cardListItemView = itemView.findViewById(R.id.recyclerView);
    }

    public void bind(int cardListId){
        this.cardListId = cardListId;
    }

    public static CardListViewHolder create(ViewGroup parent){
        //TODO: Create a layout for the CardList,
        // then replace it with the current layout here
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CardListViewHolder(view);
    }
}
