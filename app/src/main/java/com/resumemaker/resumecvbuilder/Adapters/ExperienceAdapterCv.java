package com.resumemaker.resumecvbuilder.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class ExperienceAdapterCv extends RecyclerView.Adapter<ExperienceAdapterCv.ExperienceViewHolder> {

    private List<ExperienceData> experienceList;


    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        ExperienceData experience = experienceList.get(position);
        holder.bind(experience);
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    public class ExperienceViewHolder extends RecyclerView.ViewHolder {
        // Define your views

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
        }

        public void bind(ExperienceData experience) {
            // Bind data to views
        }
    }
}
