package com.example.projectmanager_android.DB;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class BoardListAdapter extends ListAdapter<Board, BoardViewHolder> {

    public BoardListAdapter(@NonNull DiffUtil.ItemCallback<Board> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BoardViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        Board current = getItem(position);
        holder.bind(current.getTitle());
    }

    public static class BoardDiff extends DiffUtil.ItemCallback<Board>{

        @Override
        public boolean areItemsTheSame(@NonNull Board oldItem, @NonNull Board newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Board oldItem, @NonNull Board newItem) {
            // Later: Rework how contents will be checked for sameness
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
