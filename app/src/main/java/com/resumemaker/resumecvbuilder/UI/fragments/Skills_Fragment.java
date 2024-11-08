package com.resumemaker.resumecvbuilder.UI.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.SkillsDao;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.Adapters.SkillAdapter;
import com.resumemaker.resumecvbuilder.UI.Create_CV;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Skills_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageView addSkill;
    EditText edt_SkillOne;
    ImageView ivSaved;
    LinearLayout linearLayout_skill1;
    ArrayList<SkillsData> list;
    LinearLayout lySkills;
    SkillAdapter skillAdapter;
    SkillsDao skillDBHandler;
    String skillName;
    String skillLevel = "Beginner";
    RecyclerView skill_recyclerView;
    String[] skillsLevel = {"Beginner", "Intermediate", "Expert"};
    Spinner spin;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        skillLevel = adapterView.getSelectedItem().toString();
        Log.d("skill level" , skillLevel);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_skills_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.skill_recyclerView = (RecyclerView) inflate.findViewById(R.id.recyler_skill);
        this.list = new ArrayList<>();
        executor.execute(() -> {
            this.skillDBHandler = ResumeDatabase.getInstance(getContext()).skillsDao();
            this.list.addAll(skillDBHandler.getAllSkillsData());
            new Handler(Looper.getMainLooper()).post(() -> {
                this.skillAdapter = new SkillAdapter(getContext(), this.list , skillDBHandler);
                this.skill_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                this.skill_recyclerView.setAdapter(this.skillAdapter);
            });
        });
        this.ivSaved = (ImageView) inflate.findViewById(R.id.id_save_skill);
        this.spin = (Spinner) inflate.findViewById(R.id.spinner);
        this.spin.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), 17367048, this.skillsLevel);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spin.setAdapter(arrayAdapter);

        this.addSkill = (ImageView) inflate.findViewById(R.id.addskills);
        this.lySkills = (LinearLayout) inflate.findViewById(R.id.lymainSkills);
        this.linearLayout_skill1 = (LinearLayout) inflate.findViewById(R.id.lyskillone);
        this.edt_SkillOne = (EditText) inflate.findViewById(R.id.skills_one_id);

        this.edt_SkillOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Skills_Fragment.this.linearLayout_skill1.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Skills_Fragment.this.linearLayout_skill1.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });

        this.addSkill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Skills_Fragment.this.lySkills.setVisibility(View.VISIBLE);
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Skills_Fragment.this.edt_SkillOne.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Skills_Fragment.this.getActivity(), "Please provide your information", Toast.LENGTH_SHORT).show();
                    return;
                }

                skillName = edt_SkillOne.getText().toString();

                executor.execute(() -> {
                    // Insert new ExperienceData in background thread
                    skillDBHandler.insert(new SkillsData(skillName , skillLevel));

                    // Retrieve the updated data in background thread
                    List<SkillsData> updatedList = skillDBHandler.getAllSkillsData();

                    // Post UI update on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        list.clear();
                        list.addAll(updatedList);
                        skillAdapter.notifyDataSetChanged();
                        // Clear the input fields and update visibility
                        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                        Skills_Fragment.this.edt_SkillOne.setText("");
                    });
                });
            }
        });
        return inflate;
    }
}
