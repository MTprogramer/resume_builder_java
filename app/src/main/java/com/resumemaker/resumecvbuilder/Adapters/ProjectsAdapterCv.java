package com.resumemaker.resumecvbuilder.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class ProjectsAdapterCv extends RecyclerView.Adapter<ProjectsAdapterCv.ProjectsViewHolder> {

    private List<ProjectsData> projectList;


    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        ProjectsData project = projectList.get(position);
        holder.bind(project);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectsViewHolder extends RecyclerView.ViewHolder {
        // Define your views

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
        }
        public void bind(ProjectsData project) {
            // Bind data to views
        }
    }
}
