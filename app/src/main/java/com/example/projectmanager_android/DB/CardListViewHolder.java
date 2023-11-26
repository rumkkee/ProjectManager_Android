package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.R;

public class CardListViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardListTitleTextView;
    private int cardListId;
    private String cardListName;
    private CardViewModel mCardViewModel;

    public CardListViewHolder(@NonNull View itemView) {
        super(itemView);
        cardListTitleTextView = itemView.findViewById(R.id.cardList_title);
        RecyclerView recyclerView_cards = itemView.findViewById(R.id.recyclerView_Cards);
        CardAdapter cardAdapter = new CardAdapter(new CardAdapter.CardDiff());
        recyclerView_cards.setAdapter(cardAdapter);

        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) itemView.getRootView().getContext();
        LifecycleOwner lifecycleOwner = (LifecycleOwner) itemView.getRootView().getContext();

        mCardViewModel = new ViewModelProvider(viewModelStoreOwner).get(CardViewModel.class);
        mCardViewModel.getCardsByCardListId(cardListId).observe(lifecycleOwner, cards -> {
            cardAdapter.submitList(cards);
        });

        cardListTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Title was clicked!");
            }
        });

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
