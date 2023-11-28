package com.example.projectmanager_android.DB;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.BoardActivity;
import com.example.projectmanager_android.R;
import com.example.projectmanager_android.SharedPreferencesHelper;

public class BoardViewHolder extends RecyclerView.ViewHolder {
    private final TextView boardItemView;
    private int boardId;

    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);
        boardItemView = itemView.findViewById(R.id.textView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                System.out.println("Board Item # " + position + " Clicked!");
                Intent intent = BoardActivity.getIntent(v.getContext());
                Context context = v.getContext();
                SharedPreferencesHelper.setCurrentBoardId(boardId);
                context.startActivity(intent);
            }
        });

    }
    public void bind(String text, int boardId) {
        boardItemView.setText(text);
        this.boardId = boardId;
    }

    public static BoardViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new BoardViewHolder(view);
    }
}
