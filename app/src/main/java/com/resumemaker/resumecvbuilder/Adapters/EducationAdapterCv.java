package com.resumemaker.resumecvbuilder.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.List;

public class EducationAdapterCv extends RecyclerView.Adapter<EducationAdapterCv.EducationViewHolder> {

    private List<EducationData> educationList = new ArrayList<>();
    int lay_no = 1;
    int color = R.color.white;
    Context context;

    public EducationAdapterCv(List<EducationData> educationList, int lay_no, Context context) {
        this.educationList = educationList;
        this.lay_no = lay_no;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch (lay_no) {
            case 1:
            case 6:
            case 10:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_1, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_2, parent, false);
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_6, parent, false);
                break;
            case 9:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_card_7, parent, false);
                break;
            default:
                Log.d("Adapter", "Invalid layout number: " + lay_no);
//                throw new IllegalArgumentException("Invalid layout number: " + lay_no);
        }
        Log.d("Adapter", "number: " + lay_no);
        return new EducationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {

        Log.d("Adapter", "List size: " + educationList.size());

        EducationData education = educationList.get(position);
        holder.bind(education);
    }



    @Override
    public int getItemCount() {
        Toast.makeText(context, String.valueOf(educationList.size()), Toast.LENGTH_SHORT).show();
        return educationList.size();
    }

    public class EducationViewHolder extends RecyclerView.ViewHolder {
        TextView insitute_name;
        TextView digree_name;
        TextView stat_date;
        LinearLayout layout;
        TextView end_date;
        TextView digree_des;

        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);

            switch (lay_no)
            {
                case 3:
                case 4:
                case 5:
                    digree_des = itemView.findViewById(R.id.degreeDes);
                    break;
                case 6:
                    layout = itemView.findViewById(R.id.ly_eduone);
                    break;
                case 7:
                    digree_des = itemView.findViewById(R.id.job_des);
                    stat_date = itemView.findViewById(R.id.join_date);
                    break;
            }
            if (lay_no == 7)
            {
                insitute_name = itemView.findViewById(R.id.designation);
                digree_name = itemView.findViewById(R.id.company_name);
                end_date = itemView.findViewById(R.id.end_date);
            }
            else {
                insitute_name = itemView.findViewById(R.id.instituteName);
                digree_name = itemView.findViewById(R.id.digree);
                end_date = itemView.findViewById(R.id.completeDate);
            }
        }

        public void bind(EducationData education) {

            // Bind data to views
            switch (lay_no)
            {
                case 1:
                case 10:
                   insitute_name.setTextColor(context.getResources().getColor(color));
                   digree_name.setTextColor(context.getResources().getColor(color));
                   end_date.setTextColor(context.getResources().getColor(color));
                    break;
                case 3:
                case 4:
                case 5:
                    digree_des.setText(education.getDegreeDes());
                    break;
                case 6:
                    layout.setGravity(Gravity.END);
                    break;
                case 7:
                    digree_des.setText(education.getDegreeDes());
                    stat_date.setText(education.getStart_date());
                    break;


            }
            Log.d("Adapter", "List size: " + educationList.size());
            insitute_name.setText(education.getInstituteName());
            digree_name.setText(education.getDegree());
            if (lay_no == 8 || lay_no == 9)
                end_date.setText(education.getStart_date()+"-"+education.getCompleteDate());
            else
                end_date.setText(education.getCompleteDate());
        }
    }

    public void setLay_no (int no)
    {

        lay_no = no;
        notifyDataSetChanged();
    }
}
