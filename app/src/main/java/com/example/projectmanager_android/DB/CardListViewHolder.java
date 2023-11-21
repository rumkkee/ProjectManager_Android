package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.R;

public class CardListViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardListTitleTextView;
    private int cardListId;
    private String cardListName;

    public CardListViewHolder(@NonNull View itemView) {
        super(itemView);
        cardListTitleTextView = itemView.findViewById(R.id.cardList_title);
    }

    public void bind(int cardListId, String cardListName){
        cardListTitleTextView.setText(cardListName);
        this.cardListId = cardListId;
        this.cardListName = cardListName;
    }

    public static CardListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_card_list, parent, false);
        return new CardListViewHolder(view);
    }
}
