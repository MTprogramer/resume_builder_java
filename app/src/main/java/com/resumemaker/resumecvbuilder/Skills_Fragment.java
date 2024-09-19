package com.resumemaker.resumecvbuilder;

import android.app.Dialog;
import android.os.Bundle;
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
import java.util.ArrayList;

public class Skills_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageView addSkill;
    EditText edt_SkillOne;
    ImageView ivSaved;
    LinearLayout linearLayout_skill1;
    ArrayList<SkillRecylerviewModel> list;
    LinearLayout lySkills;
    SkillAdapter skillAdapter;
    SkillDBHandler skillDBHandler;
    String skillName;
    String skillLevel = "Beginner";
    RecyclerView skill_recyclerView;
    String[] skillsLevel = {"Beginner", "Intermediate", "Expert"};
    Spinner spin;

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
        SkillDBHandler skillDBHandler2 = new SkillDBHandler(getContext());
        this.skillDBHandler = skillDBHandler2;
        this.list = skillDBHandler2.readCourses();
        this.skillAdapter = new SkillAdapter(getContext(), this.list);
        this.skill_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        this.skillAdapter.notifyDataSetChanged();
        this.skill_recyclerView.setAdapter(this.skillAdapter);
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

        this.skillDBHandler = new SkillDBHandler(getContext());
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
                Skills_Fragment skills_Fragment = Skills_Fragment.this;
                skills_Fragment.skillName = skills_Fragment.edt_SkillOne.getText().toString();
                Skills_Fragment.this.edt_SkillOne.setText("");
                Skills_Fragment.this.lySkills.setVisibility(View.GONE);
                list.add(new SkillRecylerviewModel(skillName,skillLevel));
//                Skills_Fragment.this.skillDBHandler.addNewCourse(skillName , skillLevel);
//                 list = skills_Fragment5.skillDBHandler.readCourses();
//                Skills_Fragment.this.skill_recyclerView.setLayoutManager(new LinearLayoutManager(Skills_Fragment.this.getContext()));
//                Skills_Fragment.this.skillAdapter = new SkillAdapter(Skills_Fragment.this.getContext(), Skills_Fragment.this.list);
//                Skills_Fragment.this.skill_recyclerView.setAdapter(Skills_Fragment.this.skillAdapter);
                Skills_Fragment.this.skillAdapter.notifyDataSetChanged();

            }
        });
        return inflate;
    }
}
