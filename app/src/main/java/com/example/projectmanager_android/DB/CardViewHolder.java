package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.R;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardTitleTextView;
    private int cardId;
    private String cardName;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
         cardTitleTextView = itemView.findViewById(R.id.textView);
    }

    public void bind(int cardId, String cardName){
        cardTitleTextView.setText(cardName);
        this.cardId = cardId;
        this.cardName = cardName;
    }

    public static CardViewHolder create(ViewGroup parent){
        // TODO: Make a recyclerView specific for cards.
        //  Update the layout to a recyclerView.
        //  (For now, the recyclerView for boards will suffice.)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CardViewHolder(view);
    }

}
