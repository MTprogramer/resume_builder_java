package com.resumemaker.resumecvbuilder.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.SkillsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class SkillsAdapterCv extends RecyclerView.Adapter<SkillsAdapterCv.SkillsViewHolder> {

    private List<SkillsData> skillsList;

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_card, parent, false);
        return new SkillsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        SkillsData skill = skillsList.get(position);
        holder.bind(skill);
    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {
        // Define your views

        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
        }

        public void bind(SkillsData skill) {
            // Bind data to views
        }
    }
}
