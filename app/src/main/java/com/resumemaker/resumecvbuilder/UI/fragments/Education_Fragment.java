package com.resumemaker.resumecvbuilder.UI.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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

import com.resumemaker.resumecvbuilder.Adapters.EducationAdapter;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.EducationRoom.EducationDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Education_Fragment extends Fragment {
    ImageView addBtnEdu;
    String completiondate;
    String start_date;
    String degreetitle;
    Dialog dialog;
    private Calendar myCalendarStart = Calendar.getInstance(); // Calendar for start date
    private Calendar myCalendarCompletion = Calendar.getInstance(); // Calendar for completion date
    TextView edtCompletDate;
    TextView startEdit;
    EditText edtDegree;
    EditText edtOrganization;
    EditText edtdescrption;
    EducationAdapter educationAdapter;
    EducationDao educationDBHandler;
    RecyclerView education_recyclerView;
    ImageView ivDelete;
    ImageView ivSaved;
    LinearLayout linearLayout_completionDate;
    LinearLayout linearLayout_degreedescp;
    LinearLayout linearLayout_degreetitle;
    LinearLayout linearLayout_univName;
    ArrayList<EducationData> list;
    LinearLayout lyEducation;
    String organizationname;
    String score;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_education_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.education_recyclerView = (RecyclerView) inflate.findViewById(R.id.recylewviewid);
        this.list = new ArrayList<>();

        executor.execute(() -> {
            educationDBHandler = ResumeDatabase.getInstance(getContext()).educationDao();
            this.list.addAll(educationDBHandler.getAllEducationData());
        });
        Log.d("listsize", "listsize" + this.list.size());
        this.educationAdapter = new EducationAdapter(getContext(), this.list , educationDBHandler);
        this.education_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        this.education_recyclerView.setAdapter(this.educationAdapter);
        this.lyEducation = (LinearLayout) inflate.findViewById(R.id.ly_mainEducation);
        this.linearLayout_univName = (LinearLayout) inflate.findViewById(R.id.lyuniname);
        this.linearLayout_degreetitle = (LinearLayout) inflate.findViewById(R.id.lydegreetitle);
        this.linearLayout_degreedescp = (LinearLayout) inflate.findViewById(R.id.lydrgreedescrp);
        this.linearLayout_completionDate = (LinearLayout) inflate.findViewById(R.id.lyCompletionDate);
        this.addBtnEdu = (ImageView) inflate.findViewById(R.id.addEducation);
        this.ivSaved = (ImageView) inflate.findViewById(R.id.id_save_edu);
        this.edtOrganization = (EditText) inflate.findViewById(R.id.organaztionid);
        this.edtDegree = (EditText) inflate.findViewById(R.id.idDegree);
        this.edtdescrption = (EditText) inflate.findViewById(R.id.idScore);
        this.edtCompletDate = (TextView) inflate.findViewById(R.id.idComplet);
        this.startEdit = (TextView) inflate.findViewById(R.id.idStart);
        this.edtOrganization.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Education_Fragment.this.linearLayout_univName.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Education_Fragment.this.linearLayout_univName.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edtDegree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Education_Fragment.this.linearLayout_degreetitle.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Education_Fragment.this.linearLayout_degreetitle.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.edtdescrption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Education_Fragment.this.linearLayout_degreedescp.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Education_Fragment.this.linearLayout_degreedescp.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        // Listener for Completion Date (End Date)
        final DatePickerDialog.OnDateSetListener completionDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarCompletion.set(Calendar.YEAR, year); // Set only the year for completion date
                updateLabel(); // Update the label with the new year
            }

            private void updateLabel() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.US);
                edtCompletDate.setText(sdf.format(myCalendarCompletion.getTime()));  // For Completion Date
            }
        };

        // Listener for Start Date
        final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year); // Set only the year for start date
                updateLabel(); // Update the label with the new year
            }

            private void updateLabel() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.US);
                startEdit.setText(sdf.format(myCalendarStart.getTime()));  // For Start Date
            }
        };

        // Set the listeners for both fields (start and completion year)
        edtCompletDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), completionDateListener, myCalendarCompletion.get(Calendar.YEAR),
                        myCalendarCompletion.get(Calendar.MONTH), myCalendarCompletion.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), startDateListener, myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Handle focus changes for styling purposes (optional)
        edtCompletDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    linearLayout_completionDate.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_completionDate.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });

        startEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    linearLayout_completionDate.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_completionDate.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edtOrganization.getText().toString().trim().isEmpty() ||
                        edtDegree.getText().toString().trim().isEmpty() ||
                        edtdescrption.getText().toString().trim().isEmpty() ||
                        edtCompletDate.getText().toString().trim().isEmpty() ||
                        startEdit.getText().toString().trim().isEmpty()) {

                    Toast.makeText(getActivity(), "Please Fill all Fields", Toast.LENGTH_SHORT).show();
                    Create_CV.isFilled = false;
                    return;
                }

                Create_CV.isFilled = true;
                organizationname = edtOrganization.getText().toString();
                degreetitle = edtDegree.getText().toString();
                score = edtdescrption.getText().toString();
                completiondate = edtCompletDate.getText().toString();
                start_date = startEdit.getText().toString();

                executor.execute(() -> {
                    // Insert new EducationData in background thread
                    educationDBHandler.insert(new EducationData(completiondate, start_date, degreetitle, organizationname, score));

                    // Retrieve the updated data in background thread
                    List<EducationData> updatedList = educationDBHandler.getAllEducationData();

                    // Post UI update on the main thread
                    new Handler(Looper.getMainLooper()).post(() -> {
                        list.clear();
                        list.addAll(updatedList);
                        educationAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                        // Clear the input fields and update visibility
                        edtOrganization.setText("");
                        edtDegree.setText("");
                        edtdescrption.setText("");
                        edtCompletDate.setText("");
                        startEdit.setText("");
                        lyEducation.setVisibility(View.GONE);
                    });
                });
            }
        });

        this.addBtnEdu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Education_Fragment.this.lyEducation.setVisibility(View.VISIBLE);
            }
        });
        return inflate;
    }
}
