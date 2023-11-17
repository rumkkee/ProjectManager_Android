package com.example.projectmanager_android.DB;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class UserListAdapter extends ListAdapter<Users, UserViewHolder> {

    public UserListAdapter(@NonNull DiffUtil.ItemCallback<Users> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users current = getItem(position);
        holder.bind(current.getUsername());
    }

    public static class UserDiff extends DiffUtil.ItemCallback<Users>{

        @Override
        public boolean areItemsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Users oldItem, @NonNull Users newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }
    }

}
