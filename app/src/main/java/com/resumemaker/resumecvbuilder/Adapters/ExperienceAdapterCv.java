package com.resumemaker.resumecvbuilder.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.R;

import java.util.List;

public class ExperienceAdapterCv extends RecyclerView.Adapter<ExperienceAdapterCv.ExperienceViewHolder> {

    private List<ExperienceData> experienceList;
    private int layNo;
    private Context context;

    public ExperienceAdapterCv(List<ExperienceData> experienceList, int layNo, Context context) {
        this.experienceList = experienceList;
        this.layNo = layNo;
        this.context = context;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (layNo) {
            case 1:
            case 6:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card, parent, false);
                break;
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card_2, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card_3, parent, false);
                break;
            case 3:
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_4, parent, false);
                break;
            case 5:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.educatio_card_5, parent, false);
                break;
            case 7:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card_5, parent, false);
                break;
            case 8:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card_6, parent, false);
                break;
            case 9:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card_7, parent, false);
                break;
            default:
                Log.d("Adapter", "Invalid layout number: " + layNo);
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_card, parent, false);
                break;
        }
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        ExperienceData experience = experienceList.get(position);
        holder.bind(experience);
    }

    @Override
    public int getItemCount() {
        Toast.makeText(context, "Item count: " + experienceList.size(), Toast.LENGTH_SHORT).show();
        return experienceList.size();
    }

    public class ExperienceViewHolder extends RecyclerView.ViewHolder {
        TextView instituteName;
        TextView degreeName;
        TextView designation;
        TextView startDate;
        TextView endDate;
        TextView experienceDescription;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View itemView) {
            switch (layNo) {
                case 1:
                case 6:
                case 7:
                case 8:
                case 9:
                    startDate = itemView.findViewById(R.id.join_date);
                    experienceDescription = itemView.findViewById(R.id.job_des);
                    instituteName = itemView.findViewById(R.id.company_name);
                    designation = itemView.findViewById(R.id.designation);
                    endDate = itemView.findViewById(R.id.end_date);
                    break;
                case 10:
                    startDate = itemView.findViewById(R.id.join_date);
                    experienceDescription = itemView.findViewById(R.id.experience_des);
                    instituteName = itemView.findViewById(R.id.company_name);
                    designation = itemView.findViewById(R.id.designation);
                    endDate = itemView.findViewById(R.id.end_date);
                    break;
                case 2:
                    experienceDescription = itemView.findViewById(R.id.experience_des);
                    startDate = itemView.findViewById(R.id.join_date);
                    instituteName = itemView.findViewById(R.id.company_name);
                    designation = itemView.findViewById(R.id.designation);
                    endDate = itemView.findViewById(R.id.end_date);
                    break;
                case 3:
                case 4:
                case 5:
                    experienceDescription = itemView.findViewById(R.id.degreeDes);
                    instituteName = itemView.findViewById(R.id.instituteName);
                    designation = itemView.findViewById(R.id.digree);
                    endDate = itemView.findViewById(R.id.completeDate);
                    break;
                default:
                    Log.d("Adapter", "No matching layout for view initialization.");
                    break;
            }
        }

        public void bind(ExperienceData experience) {
            if (experience == null) return;

            if (startDate != null) startDate.setText(experience.getJoin_date());
            if (endDate != null) endDate.setText(experience.getEnd_date());
            if (instituteName != null) instituteName.setText(experience.getCompany_name());
            if (designation != null) designation.setText(experience.getDesignation());
            if (experienceDescription != null) experienceDescription.setText(experience.getExperience_des());
        }
    }
}
