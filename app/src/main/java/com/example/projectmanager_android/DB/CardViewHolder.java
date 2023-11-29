package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.CardDisplayer;
import com.example.projectmanager_android.CardExpandedFragment;
import com.example.projectmanager_android.R;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardTitleTextView;
    private int cardId;
    private String cardName;
    private Card mCard;

    private CardDisplayer mCardDisplayer;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
         cardTitleTextView = itemView.findViewById(R.id.recyclerView_Card);

        cardTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardDisplayer.displayCardExpandedFragment(cardId);
            }
        });
    }


    public void bind(int cardId, String cardName, CardDisplayer cardDisplayer){
        cardTitleTextView.setText(cardName);
        this.cardId = cardId;
        this.cardName = cardName;
        this.mCardDisplayer = cardDisplayer;
    }

    public static CardViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_card, parent, false);

        return new CardViewHolder(view);
    }

}
