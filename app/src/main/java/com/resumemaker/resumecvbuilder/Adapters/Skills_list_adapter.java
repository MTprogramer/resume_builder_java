package com.resumemaker.resumecvbuilder.Adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class Skills_list_adapter extends RecyclerView.Adapter<Skills_list_adapter.skills_list_holder>
{


    @NonNull
    @Override
    public skills_list_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull skills_list_holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class skills_list_holder extends RecyclerView.ViewHolder
    {
        public skills_list_holder(View itemView)
        {
            super(itemView);
        }
    }
}
