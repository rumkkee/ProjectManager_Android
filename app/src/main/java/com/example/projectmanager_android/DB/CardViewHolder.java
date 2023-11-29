package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.CardExpandedFragment;
import com.example.projectmanager_android.R;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private final TextView cardTitleTextView;
    private int cardId;
    private String cardName;

    // TODO: Uncomment once I've created the CardDisplayer interface
    //private CardDisplayer mCardDisplayer;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);
         cardTitleTextView = itemView.findViewById(R.id.recyclerView_Card);

        cardTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call the method displayCardExpandedFragment(cardId) from this class' field of CardDisplayer interface.
                //CardExpandedFragment cardExpandedFragment = CardExpandedFragment.newInstance("title", "desc");

//                itemView.getRootView().getContext().getSupportFragmentManager().beginTransaction()
//                        .add(android.R.id.content, cardExpandedFragment)
//                        .addToBackStack(null)
//                        .commit();
            }
        });
    }

    // TODO: Add a param of Interface CardDisplayer
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
                .inflate(R.layout.recyclerview_card, parent, false);

        return new CardViewHolder(view);
    }

    private void showCardExpandedFragment(){

    }

}
