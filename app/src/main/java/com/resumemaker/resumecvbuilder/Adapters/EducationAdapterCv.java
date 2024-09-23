package com.resumemaker.resumecvbuilder.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class EducationAdapterCv extends RecyclerView.Adapter<EducationAdapterCv.EducationViewHolder> {

    private List<EducationData> educationList;

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_1, parent, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        EducationData education = educationList.get(position);
        holder.bind(education);
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {
        // Define your views (TextView, ImageView, etc.)

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
        }

        public void bind(EducationData education) {
            // Bind data to views
        }
    }
}
