package com.resumemaker.resumecvbuilder.UI.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.Adapters.ExperienceAdapter;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ExperienceRoom.ExperienceDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Experience_Fragment extends Fragment {
    ImageView addExperence;
    String designations;
    Dialog dialog;
    EditText edtDesignation;
    EditText edtJobdescrption;
    EditText edtOrganization;
    String endingDate;
    ExperienceAdapter experienceAdapter;
    ExperienceDao experienceDBHandler;
    RecyclerView experience_recyclerView;
    ImageView ivDelete;
    ImageView ivSaved;
    String jobdescriptions;
    String joiningDate;
    LinearLayout linearLayout_EndDate;
    LinearLayout linearLayout_designatione;
    LinearLayout linearLayout_jobdescrption;
    LinearLayout linearLayout_joinDate;
    LinearLayout linearLayout_orgName;
    ArrayList<ExperienceData> list;
    LinearLayout lyoutMain;
    final Calendar myCalendar = Calendar.getInstance();
    String organozationname;
    TextView txtEnddate;
    TextView txtjoinDate;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_experience_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.experience_recyclerView = (RecyclerView) inflate.findViewById(R.id.recylewview);
        this.list = new ArrayList<>();
        executor.execute(() -> {
            this.experienceDBHandler = ResumeDatabase.getInstance(getContext()).experienceDao();
            this.list.addAll(experienceDBHandler.getAllExperienceData());
        });
        this.experienceAdapter = new ExperienceAdapter(getContext(), this.list , experienceDBHandler);
        this.experience_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.experience_recyclerView.setAdapter(this.experienceAdapter);
        this.lyoutMain = (LinearLayout) inflate.findViewById(R.id.ly_mainEducation);
        this.linearLayout_orgName = (LinearLayout) inflate.findViewById(R.id.ly_exp_orgname);
        this.linearLayout_designatione = (LinearLayout) inflate.findViewById(R.id.ly_exp_designation);
        this.linearLayout_joinDate = (LinearLayout) inflate.findViewById(R.id.ly_exp_joinDate);
        this.linearLayout_EndDate = (LinearLayout) inflate.findViewById(R.id.ly_exp_endDate);
        this.linearLayout_jobdescrption = (LinearLayout) inflate.findViewById(R.id.ly_exp_jobDescrption);
        this.ivSaved = (ImageView) inflate.findViewById(R.id.id_saved);
        this.edtOrganization = (EditText) inflate.findViewById(R.id.experence_edt_organization);
        this.edtDesignation = (EditText) inflate.findViewById(R.id.experence_edt_designation);
        this.txtjoinDate = (TextView) inflate.findViewById(R.id.experence_edt_joindate);
        this.txtEnddate = (TextView) inflate.findViewById(R.id.experence_edt_enddate);
        this.edtJobdescrption = (EditText) inflate.findViewById(R.id.experence_edt_jobdescription);
        this.edtOrganization.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Experience_Fragment.this.linearLayout_orgName.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Experience_Fragment.this.linearLayout_orgName.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edtDesignation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Experience_Fragment.this.linearLayout_designatione.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Experience_Fragment.this.linearLayout_designatione.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.txtjoinDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Experience_Fragment.this.linearLayout_joinDate.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Experience_Fragment.this.linearLayout_joinDate.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.txtjoinDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Experience_Fragment experience_Fragment = Experience_Fragment.this;
                experience_Fragment.showDatePickerDialog(experience_Fragment.txtjoinDate);
            }
        });
        this.txtEnddate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Experience_Fragment experience_Fragment = Experience_Fragment.this;
                experience_Fragment.showDatePickerDialog(experience_Fragment.txtEnddate);
            }
        });
        this.txtEnddate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Experience_Fragment.this.linearLayout_EndDate.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Experience_Fragment.this.linearLayout_EndDate.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edtJobdescrption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Experience_Fragment.this.linearLayout_jobdescrption.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Experience_Fragment.this.linearLayout_jobdescrption.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        ImageView imageView = (ImageView) inflate.findViewById(R.id.addBtn_exp);
        this.addExperence = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Experience_Fragment.this.lyoutMain.setVisibility(View.VISIBLE);
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Experience_Fragment.this.edtOrganization.getText().toString().trim().isEmpty() ||
                        Experience_Fragment.this.edtDesignation.getText().toString().trim().isEmpty() ||
                        Experience_Fragment.this.txtjoinDate.getText().toString().trim().isEmpty() ||
                        Experience_Fragment.this.txtEnddate.getText().toString().trim().isEmpty() ||
                        Experience_Fragment.this.edtJobdescrption.getText().toString().trim().isEmpty()) {

                    Toast.makeText(Experience_Fragment.this.getActivity(), "Please Fill all Fields", Toast.LENGTH_SHORT).show();
                    Experience_Fragment.this.lyoutMain.setVisibility(View.VISIBLE);
                    return;
                }

                organozationname = edtOrganization.getText().toString();
                designations = edtDesignation.getText().toString();
                joiningDate = txtjoinDate.getText().toString(); // Should now be just a year
                endingDate = txtEnddate.getText().toString();   // Should now be just a year
                jobdescriptions = edtJobdescrption.getText().toString();

                executor.execute(() -> {
                    // Insert new ExperienceData in background thread
                    experienceDBHandler.insert(new ExperienceData(designations, joiningDate, endingDate, organozationname , jobdescriptions, false));

                    // Retrieve the updated data in background thread
                    List<ExperienceData> updatedList = experienceDBHandler.getAllExperienceData();

                    // Post UI update on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        list.clear();
                        list.addAll(updatedList);
                        experienceAdapter.notifyDataSetChanged();
                        // Clear the input fields and update visibility
                        Toast.makeText(Experience_Fragment.this.getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                        Experience_Fragment.this.edtOrganization.setText("");
                        Experience_Fragment.this.edtDesignation.setText("");
                        Experience_Fragment.this.txtjoinDate.setText("");
                        Experience_Fragment.this.txtEnddate.setText("");
                        Experience_Fragment.this.edtJobdescrption.setText("");
                        Experience_Fragment.this.lyoutMain.setVisibility(View.GONE);
                    });
                });
            }
        });

        return inflate;
    }

    
    public void showDatePickerDialog(final TextView textView) {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Experience_Fragment.this.myCalendar.set(1, i);
                Experience_Fragment.this.myCalendar.set(2, i2);
                Experience_Fragment.this.myCalendar.set(5, i3);
                updateLabel();
            }

            private void updateLabel() {
                textView.setText(new SimpleDateFormat("yyyy", Locale.US).format(Experience_Fragment.this.myCalendar.getTime()));
            }
        }, this.myCalendar.get(1), this.myCalendar.get(2), this.myCalendar.get(5)).show();
    }
}
