package com.example.projectmanager_android.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager_android.R;

public class BoardViewHolder extends RecyclerView.ViewHolder {
    private final TextView boardItemView;

    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);
        boardItemView = itemView.findViewById(R.id.textView);
    }
    public void bind(String text) {
        boardItemView.setText(text);
    }

    public static UserViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(view);
    }
}
