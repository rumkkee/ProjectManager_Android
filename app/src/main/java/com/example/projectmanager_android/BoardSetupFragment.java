package com.example.projectmanager_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardSetupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardSetupFragment extends Fragment {

    private EditText mBoardName;
    private Button mBackButton;
    private Button mSubmitButton;

    private static boolean isOpen;

    public BoardSetupFragment() {
        // Required empty public constructor
        isOpen = true;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment BoardSetupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardSetupFragment newInstance() {
        BoardSetupFragment fragment = new BoardSetupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board_setup, container, false);
        // Inflate the layout for this fragment
        mBoardName = view.findViewById(R.id.BoardName_input);
        mBackButton = view.findViewById(R.id.BoardSetup_BackButton);
        mSubmitButton = view.findViewById(R.id.BoardSetupComplete_button);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boardNameInput = mBoardName.getText().toString();
                System.out.println("New Board's name: " + boardNameInput );
                removeFragment();
            }
        });

        return view;
    }

    private void removeFragment(){
        isOpen = false;
        getParentFragmentManager().beginTransaction().remove(BoardSetupFragment.this).commit();
    }

    public static boolean isOpen(){
        return isOpen;
    }

}