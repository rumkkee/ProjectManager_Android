package com.example.projectmanager_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Card;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardExpandedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardExpandedFragment extends Fragment {

    private static final String CARD_TITLE_PARAM = "cardTitle";
    private static final String CARD_DESC_PARAM = "cardDesc";

    private String mCardTitleParam;
    private String mCardDescParam;

    private TextView mCardTitle;
    private TextView mCardDescription;

    private ImageButton mExitFragmentButton;
    private ImageButton mOptionsButton;

    private Card mCard;

    public CardExpandedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cardTitle Parameter 1.
     * @param cardDesc Parameter 2.
     * @return A new instance of fragment CardExpandedFragment.
     */
    public static CardExpandedFragment newInstance(String cardTitle, String cardDesc, Card card) {
        CardExpandedFragment fragment = new CardExpandedFragment();
        Bundle args = new Bundle();
        args.putString(CARD_TITLE_PARAM, cardTitle);
        args.putString(CARD_DESC_PARAM, cardDesc);
        fragment.setArguments(args);
        fragment.setCard(card);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCardTitleParam = getArguments().getString(CARD_TITLE_PARAM);
            mCardDescParam = getArguments().getString(CARD_DESC_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_card_expanded, container, false);

        mCardTitle = view.findViewById(R.id.cardFragment_title);
        mCardDescription = view.findViewById(R.id.cardFragment_descriptionTextView);

        mExitFragmentButton = view.findViewById(R.id.cardFragment_exitButton);
        mOptionsButton = view.findViewById(R.id.cardFragment_toolbarButton);

        mCardTitle.setText(mCardTitleParam);
        mCardDescription.setText(mCardDescParam);

        mExitFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        mCardDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardDescription();
            }
        });

        return view;
    }

    private void removeFragment(){
        getParentFragmentManager().beginTransaction().remove(CardExpandedFragment.this).commit();
    }

    private void openCardDescription(){
        CardDescriptionEditorFragment cardDescriptionEditorFragment = CardDescriptionEditorFragment.newInstance(mCardDescParam, this);
        getParentFragmentManager().beginTransaction()
                .add(android.R.id.content, cardDescriptionEditorFragment)
                .addToBackStack(null)
                .commit();
    }

    private void setCard(Card card){
        mCard = card;
    }

    public void saveCardDesc(String newCardDesc){
        // Updating the cardDesc of the current card.
        mCard.setDescription(newCardDesc);
        AppDataBase.getInstance(getContext()).CardDAO().update(mCard);
        // Updating the currently displayed cardDesc
        mCardDescription.setText(newCardDesc);
    }
}
