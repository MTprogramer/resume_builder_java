package com.resumemaker.resumecvbuilder.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class ProjectsAdapterCv extends RecyclerView.Adapter<ProjectsAdapterCv.ProjectsViewHolder> {

    private final List<ProjectsData> projectList;
    private final int lay_no;
    private final Context context;

    public ProjectsAdapterCv(List<ProjectsData> projectList, int lay_no, Context context) {
        this.projectList = projectList;
        this.lay_no = lay_no;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (lay_no) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false);
                break;
            case 2:
            case 6:
            case 7:
            case 9:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card_2, parent, false);
                break;
            case 3:
            case 4:
            case 5:
            case 8:
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card_3, parent, false);
                break;
            default:
                Log.e("ProjectsAdapterCv", "Invalid layout number: " + lay_no);
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card, parent, false);
                break;
        }

        Log.d("ProjectsAdapterCv", "Using layout number: " + lay_no);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        Log.d("ProjectsAdapterCv", "List size: " + projectList.size());
        ProjectsData project = projectList.get(position);
        holder.bind(project);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectsViewHolder extends RecyclerView.ViewHolder {
        TextView project_name;
        TextView project_url;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            project_name = itemView.findViewById(R.id.projectname);
            project_url = itemView.findViewById(R.id.projecturl);

            if (lay_no == 6) {
                if (project_name != null) project_name.setGravity(Gravity.END);
                if (project_url != null) project_url.setGravity(Gravity.END);
            }
        }

        public void bind(ProjectsData project) {
            if (project_name != null) project_name.setText(project.getProjectName());
            if (project_url != null) project_url.setText(project.getProjectUrl());
        }
    }
}
