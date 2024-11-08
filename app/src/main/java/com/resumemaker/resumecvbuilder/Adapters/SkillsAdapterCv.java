package com.resumemaker.resumecvbuilder.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.List;

public class SkillsAdapterCv extends RecyclerView.Adapter<SkillsAdapterCv.SkillsViewHolder> {

    private List<SkillsData> educationList = new ArrayList<>();
    int lay_no = 1;
    Context context;
    private static final int PROGRESS_BEGINNER = 33;
    private static final int PROGRESS_INTERMEDIATE = 66;
    private static final int PROGRESS_EXPERT = 100;

    public SkillsAdapterCv(List<SkillsData> educationList, int lay_no, Context context) {
        this.educationList = educationList;
        this.lay_no = lay_no;
        this.context = context;
    }

    @NonNull
    @Override
    public SkillsAdapterCv.SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch (lay_no) {
            case 1:
            case 6:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_card, parent, false);
                break;
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_card_2, parent, false);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_card_3, parent, false);
                break;
            case 9:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_card_4, parent, false);
                break;
            default:
                Log.d("Adapter", "Invalid layout number: " + lay_no);
//                throw new IllegalArgumentException("Invalid layout number: " + lay_no);
        }
        Log.d("Adapter", "number: " + lay_no);
        return new SkillsAdapterCv.SkillsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SkillsAdapterCv.SkillsViewHolder holder, int position) {

        Log.d("Adapter", "List size: " + educationList.size());

        SkillsData education = educationList.get(position);
        holder.bind(education);
    }



    @Override
    public int getItemCount() {
        Toast.makeText(context, String.valueOf(educationList.size()), Toast.LENGTH_SHORT).show();
        return educationList.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {

        TextView skill_name;
        SeekBar seekBar;


        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);

            skill_name = itemView.findViewById(R.id.skill);

            switch (lay_no)
            {
                case 2:
                case 9:
                    seekBar = itemView.findViewById(R.id.seek_bar);
                    break;
                case 3:
                case 4:
                    seekBar = itemView.findViewById(R.id.seek_bar);
                    skill_name.setTextColor(context.getResources().getColor(R.color.white));
                    seekBar.setThumbTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.white)));
                    break;
                case 5:
                    seekBar = itemView.findViewById(R.id.seek_bar);
                    skill_name.setTextColor(context.getResources().getColor(R.color.white));
                    seekBar.setThumbTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.green)));
                    break;
                case 7:
                    seekBar = itemView.findViewById(R.id.seek_bar);
                    seekBar.setThumbTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.blue)));
                    break;
                case 8:
                    seekBar = itemView.findViewById(R.id.seek_bar);
                    skill_name.setTextColor(context.getResources().getColor(R.color.white));
                    seekBar.setThumbTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.red)));
                    break;
            }
        }

        public void bind(SkillsData education) {
            skill_name.setText(education.getSkillName());

            // Only set progress if seekBar is available
            if (seekBar != null) {
                int progressLevel = 0;
                switch (education.getSkillLevel()) {
                    case "Beginner":
                        progressLevel = PROGRESS_BEGINNER;
                        break;
                    case "Intermediate":
                        progressLevel = PROGRESS_INTERMEDIATE;
                        break;
                    case "Expert":
                        progressLevel = PROGRESS_EXPERT;
                        break;
                    default:
                        Log.d("Adapter", "Unknown skill level: " + education.getSkillLevel());
                }
                seekBar.setProgress(progressLevel);
            }
        }
    }

}
