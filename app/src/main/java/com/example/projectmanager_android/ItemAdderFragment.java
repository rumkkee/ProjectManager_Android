package com.example.projectmanager_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ItemAdderFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ItemAdderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    protected EditText mItemNameEditText;
    protected Button mCancelButton;
    protected Button mAddButton;

    protected static boolean isOpen;

    public ItemAdderFragment() {
        // Required empty public constructor
        isOpen = true;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment ItemAdderFragment.
     */
//    public static ItemAdderFragment newInstance(/*String param1, String param2*/) {
//        ItemAdderFragment fragment = new ItemAdderFragment();
//        // Keeping this starter code for reference
////        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
////        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_adder, container, false);

        // Inflate the layout for this fragment
        mAddButton = view.findViewById(R.id.itemAdder_addButton);
        mCancelButton = view.findViewById(R.id.itemAdder_cancelButton);
        mItemNameEditText = view.findViewById(R.id.itemAdder_titleInput);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameInput = mItemNameEditText.getText().toString();
                if(!itemNameInput.isEmpty()){
                    addItem(itemNameInput);
                }
            }
        });

        return view;
    }


    protected void addItem(String itemName){
        System.out.println("Error: Default ItemAdderFragment called. \n Expected: overridden method call");
    }

    protected void removeFragment(){
        isOpen = false;
        getParentFragmentManager().beginTransaction().remove(ItemAdderFragment.this).commit();
    }

    public static boolean isOpen(){
        return isOpen;
    }
}