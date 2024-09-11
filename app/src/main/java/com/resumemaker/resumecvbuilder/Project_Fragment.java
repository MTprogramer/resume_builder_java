package com.resumemaker.resumecvbuilder;

import android.app.Dialog;
import android.os.Bundle;
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
import java.util.ArrayList;

public class Project_Fragment extends Fragment {
    ImageView addSkill;
    Dialog dialog;
    EditText edt_ProjectNameOne;
    EditText edt_ProjectNameTwo;
    EditText edt_ProjectUrlOne;
    EditText edt_ProjectUrlTwo;
    ImageView ivDelete;
    ImageView ivSaved;
    LinearLayout layoutSkills;
    LinearLayout linearLayout_projectOne;
    LinearLayout linearLayout_projectTwo;
    LinearLayout linearLayout_projecturlone;
    LinearLayout linearLayout_projecturltwo;
    ArrayList<ProjectRecylerviewModel> list;
    ProjectAdapter projectAdapter;
    ProjectDBHandler projectDBHandler;
    String projectOneName;
    String projectOneUrl;
    String projectTwoName;
    String projectTwoUrl;
    RecyclerView skill_recyclerView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_project, viewGroup, false);
        this.skill_recyclerView = (RecyclerView) inflate.findViewById(R.id.recyler_proj);
        Create_CV.viewPager.setSwipeEnabled(false);
        this.list = new ArrayList<>();
        ProjectDBHandler projectDBHandler2 = new ProjectDBHandler(getContext());
        this.projectDBHandler = projectDBHandler2;
        this.list = projectDBHandler2.readCourses();
        this.skill_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProjectAdapter projectAdapter2 = new ProjectAdapter(getContext(), this.list);
        this.projectAdapter = projectAdapter2;
        projectAdapter2.notifyDataSetChanged();
        this.skill_recyclerView.setAdapter(this.projectAdapter);
        this.ivSaved = (ImageView) inflate.findViewById(R.id.id_save_skill);
        this.addSkill = (ImageView) inflate.findViewById(R.id.addskills);
        this.layoutSkills = (LinearLayout) inflate.findViewById(R.id.lymainSkills_id);
        this.linearLayout_projectOne = (LinearLayout) inflate.findViewById(R.id.lyprojectfirsttitle);
        this.linearLayout_projecturlone = (LinearLayout) inflate.findViewById(R.id.ly_projectFirsturl);
        this.linearLayout_projectTwo = (LinearLayout) inflate.findViewById(R.id.lyprojectsecondname);
        this.linearLayout_projecturltwo = (LinearLayout) inflate.findViewById(R.id.lyprojectsecondurl);
        this.edt_ProjectNameOne = (EditText) inflate.findViewById(R.id.projNem_one_id);
        this.edt_ProjectUrlOne = (EditText) inflate.findViewById(R.id.projUrl_one_id);
        this.edt_ProjectNameTwo = (EditText) inflate.findViewById(R.id.proj_Name_id);
        this.edt_ProjectUrlTwo = (EditText) inflate.findViewById(R.id.projUrlTwo_id);
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
        this.edt_ProjectNameTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Project_Fragment.this.linearLayout_projectTwo.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Project_Fragment.this.linearLayout_projectTwo.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edt_ProjectUrlTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Project_Fragment.this.linearLayout_projecturltwo.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Project_Fragment.this.linearLayout_projecturltwo.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.addSkill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Project_Fragment.this.layoutSkills.setVisibility(0);
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Project_Fragment.this.edt_ProjectNameOne.getText().toString().trim().length() == 0 || Project_Fragment.this.edt_ProjectUrlOne.getText().toString().trim().length() == 0 || Project_Fragment.this.edt_ProjectNameTwo.getText().toString().trim().length() == 0 || Project_Fragment.this.edt_ProjectUrlTwo.getText().toString().trim().length() == 0) {
                    Toast.makeText(Project_Fragment.this.getActivity(), "Please provide your information", 0).show();
                    return;
                }
                Project_Fragment project_Fragment = Project_Fragment.this;
                project_Fragment.projectOneName = project_Fragment.edt_ProjectNameOne.getText().toString();
                Project_Fragment project_Fragment2 = Project_Fragment.this;
                project_Fragment2.projectOneUrl = project_Fragment2.edt_ProjectUrlOne.getText().toString();
                Project_Fragment project_Fragment3 = Project_Fragment.this;
                project_Fragment3.projectTwoName = project_Fragment3.edt_ProjectNameTwo.getText().toString();
                Project_Fragment project_Fragment4 = Project_Fragment.this;
                project_Fragment4.projectTwoUrl = project_Fragment4.edt_ProjectUrlTwo.getText().toString();
                Project_Fragment.this.edt_ProjectNameOne.setText("");
                Project_Fragment.this.edt_ProjectUrlOne.setText("");
                Project_Fragment.this.edt_ProjectNameTwo.setText("");
                Project_Fragment.this.edt_ProjectUrlTwo.setText("");
                Project_Fragment.this.layoutSkills.setVisibility(8);
                Project_Fragment.this.projectDBHandler.addNewCourse(Project_Fragment.this.projectOneName, Project_Fragment.this.projectOneUrl, Project_Fragment.this.projectTwoName, Project_Fragment.this.projectTwoUrl);
                Project_Fragment project_Fragment5 = Project_Fragment.this;
                project_Fragment5.list = project_Fragment5.projectDBHandler.readCourses();
                Project_Fragment.this.skill_recyclerView.setLayoutManager(new LinearLayoutManager(Project_Fragment.this.getContext()));
                Project_Fragment.this.projectAdapter = new ProjectAdapter(Project_Fragment.this.getContext(), Project_Fragment.this.list);
                Project_Fragment.this.projectAdapter.notifyDataSetChanged();
                Project_Fragment.this.skill_recyclerView.setAdapter(Project_Fragment.this.projectAdapter);
            }
        });
        return inflate;
    }
}
