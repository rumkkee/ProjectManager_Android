package com.example.projectmanager_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardExpandedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardExpandedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CARD_TITLE_PARAM = "cardTitle";
    private static final String CARD_DESC_PARAM = "cardDesc";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mCardTitle;
    private TextView mCardDescription;

    private ImageButton mExitFragmentButton;
    private ImageButton mOptionsButton;

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
    // TODO: Rename and change types and number of parameters
    public static CardExpandedFragment newInstance(String cardTitle, String cardDesc) {
        CardExpandedFragment fragment = new CardExpandedFragment();
        Bundle args = new Bundle();
        args.putString(CARD_TITLE_PARAM, cardTitle);
        args.putString(CARD_DESC_PARAM, cardDesc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(CARD_TITLE_PARAM);
            mParam2 = getArguments().getString(CARD_DESC_PARAM);
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

        mExitFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        return view;
    }

    private void removeFragment(){
        getParentFragmentManager().beginTransaction().remove(CardExpandedFragment.this).commit();
    }
}
