package com.example.projectmanager_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardDescriptionEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardDescriptionEditorFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CARD_DESC_PARAM = "cardDesc";

    private String mCardDescParam;
    private CardExpandedFragment mCardExpandedFragment;


    private TextView mCancelButton;
    private TextView mDoneButton;
    private EditText mCardDescEditText;

    public CardDescriptionEditorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cardDesc Parameter 1.
     * @return A new instance of fragment CardDescriptionEditorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardDescriptionEditorFragment newInstance(String cardDesc, CardExpandedFragment cardExpandedFragment) {
        CardDescriptionEditorFragment fragment = new CardDescriptionEditorFragment();
        Bundle args = new Bundle();
        args.putString(CARD_DESC_PARAM, cardDesc);
        fragment.setArguments(args);
        fragment.setCardExpandedFragment(cardExpandedFragment);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCardDescParam = getArguments().getString(CARD_DESC_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_description_editor, container, false);

        mCancelButton = view.findViewById(R.id.cardDescFragment_cancelButton);
        mDoneButton = view.findViewById(R.id.cardDescFragment_DoneButton);
        mCardDescEditText = view.findViewById(R.id.cardDescFragment_description_EditText);

        mCardDescEditText.setText(mCardDescParam);


        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardDescChanged()){
                    mCardExpandedFragment.saveCardDesc(mCardDescEditText.getEditableText().toString());
                    removeFragment();
                }
            }
        });

        return view;
    }

    private void removeFragment(){
        getParentFragmentManager().beginTransaction().remove(CardDescriptionEditorFragment.this).commit();
    }

    private void setCardExpandedFragment(CardExpandedFragment cardExpandedFragment){
        mCardExpandedFragment = cardExpandedFragment;
    }

    private boolean cardDescChanged(){
        return !mCardDescEditText.getEditableText().toString().equals(mCardDescParam);
    }
}