package com.resumemaker.resumecvbuilder.UI.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.Adapters.ProjectAdapter;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ProjectRoom.ProjectsDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Project_Fragment extends Fragment {
    ImageView addSkill;
    Dialog dialog;
    EditText edt_ProjectNameOne;
    EditText edt_ProjectUrlOne;
    ImageView ivSaved;
    LinearLayout layoutSkills;
    LinearLayout linearLayout_projectOne;
    LinearLayout linearLayout_projecturlone;
    ArrayList<ProjectsData> list;
    ProjectAdapter projectAdapter;
    ProjectsDao projectDBHandler;
    String projectOneName;
    String projectOneUrl;
    RecyclerView skill_recyclerView;
    private final Executor executor = Executors.newSingleThreadExecutor();


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_project, viewGroup, false);
        this.skill_recyclerView = (RecyclerView) inflate.findViewById(R.id.recyler_proj);
        Create_CV.viewPager.setSwipeEnabled(false);
        this.list = new ArrayList<>();

        executor.execute(() -> {
            this.projectDBHandler = ResumeDatabase.getInstance(getContext()).projectsDao();
            this.list.addAll(projectDBHandler.getAllProjectsData());
            new Handler(Looper.getMainLooper()).post(() -> {
                projectAdapter = new ProjectAdapter(getContext(), list , projectDBHandler);
                skill_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                skill_recyclerView.setAdapter(projectAdapter);
            });
        });

        this.ivSaved = (ImageView) inflate.findViewById(R.id.id_save_skill);
        this.addSkill = (ImageView) inflate.findViewById(R.id.addskills);
        this.layoutSkills = (LinearLayout) inflate.findViewById(R.id.lymainSkills_id);
        linearLayout_projectOne = inflate.findViewById(R.id.lyprojectfirsttitle);
        linearLayout_projecturlone = inflate.findViewById(R.id.ly_projectFirsturl);
        this.edt_ProjectNameOne = (EditText) inflate.findViewById(R.id.projNem_one_id);
        this.edt_ProjectUrlOne = (EditText) inflate.findViewById(R.id.projUrl_one_id);
        this.edt_ProjectNameOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Project_Fragment.this.linearLayout_projectOne.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Project_Fragment.this.linearLayout_projectOne.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edt_ProjectUrlOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Project_Fragment.this.linearLayout_projecturlone.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Project_Fragment.this.linearLayout_projecturlone.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.addSkill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Project_Fragment.this.layoutSkills.setVisibility(View.VISIBLE);
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Project_Fragment.this.edt_ProjectNameOne.getText().toString().trim().isEmpty() || Project_Fragment.this.edt_ProjectUrlOne.getText().toString().trim().isEmpty() ) {
                    Toast.makeText(Project_Fragment.this.getActivity(), "Please provide your information", Toast.LENGTH_SHORT).show();
                    return;
                }
                projectOneName = edt_ProjectNameOne.getText().toString();
                projectOneUrl = edt_ProjectUrlOne.getText().toString();

                executor.execute(() -> {
                    // Insert new ExperienceData in background thread
                    projectDBHandler.insert(new ProjectsData(projectOneName , projectOneUrl));

                    // Retrieve the updated data in background thread
                    List<ProjectsData> updatedList = projectDBHandler.getAllProjectsData();

                    // Post UI update on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        list.clear();
                        list.addAll(updatedList);
                        edt_ProjectNameOne.setText("");
                        edt_ProjectUrlOne.setText("");
                        layoutSkills.setVisibility(View.GONE);
                        projectAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    });
                });

            }
        });
        return inflate;
    }
}
