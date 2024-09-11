package com.resumemaker.resumecvbuilder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
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
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Education_Fragment extends Fragment {
    ImageView addBtnEdu;
    String completiondate;
    String degreetitle;
    Dialog dialog;
    TextView edtCompletDate;
    EditText edtDegree;
    EditText edtOrganization;
    EditText edtdescrption;
    EducationAdapter educationAdapter;
    EducationDBHandler educationDBHandler;
    RecyclerView education_recyclerView;
    ImageView ivDelete;
    ImageView ivSaved;
    LinearLayout linearLayout_completionDate;
    LinearLayout linearLayout_degreedescp;
    LinearLayout linearLayout_degreetitle;
    LinearLayout linearLayout_univName;
    ArrayList<EducationRecylerviewModel> list;
    LinearLayout lyEducation;
    final Calendar myCalendar = Calendar.getInstance();
    String organizationname;
    String score;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_education_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.education_recyclerView = (RecyclerView) inflate.findViewById(R.id.recylewviewid);
        this.list = new ArrayList<>();
        EducationDBHandler educationDBHandler2 = new EducationDBHandler(getContext());
        this.educationDBHandler = educationDBHandler2;
        this.list = educationDBHandler2.readCourses();
        this.educationAdapter = new EducationAdapter(getContext(), this.list);
        this.education_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.educationAdapter.notifyDataSetChanged();
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
        final DatePickerDialog.OnDateSetListener r5 = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Education_Fragment.this.myCalendar.set(1, i);
                Education_Fragment.this.myCalendar.set(2, i2);
                Education_Fragment.this.myCalendar.set(5, i3);
                updateLabel();
            }

            private void updateLabel() {
                Education_Fragment.this.edtCompletDate.setText(new SimpleDateFormat("dd/MM/yy", Locale.US).format(Education_Fragment.this.myCalendar.getTime()));
            }
        };
        this.edtCompletDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(Education_Fragment.this.getActivity(), r5, Education_Fragment.this.myCalendar.get(1), Education_Fragment.this.myCalendar.get(2), Education_Fragment.this.myCalendar.get(5)).show();
            }
        });
        this.edtCompletDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Education_Fragment.this.linearLayout_completionDate.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    Education_Fragment.this.linearLayout_completionDate.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Education_Fragment.this.edtOrganization.getText().toString().trim().length() == 0 || Education_Fragment.this.edtDegree.getText().toString().trim().length() == 0 || Education_Fragment.this.edtdescrption.getText().toString().trim().length() == 0 || Education_Fragment.this.edtCompletDate.getText().toString().trim().length() == 0) {
                    Toast.makeText(Education_Fragment.this.getActivity(), "Please Fill all Fields", 0).show();
                    Create_CV.isFilled = false;
                    return;
                }
                Create_CV.isFilled = true;
                Education_Fragment education_Fragment = Education_Fragment.this;
                education_Fragment.organizationname = education_Fragment.edtOrganization.getText().toString();
                Education_Fragment education_Fragment2 = Education_Fragment.this;
                education_Fragment2.degreetitle = education_Fragment2.edtDegree.getText().toString();
                Education_Fragment education_Fragment3 = Education_Fragment.this;
                education_Fragment3.score = education_Fragment3.edtdescrption.getText().toString();
                Education_Fragment education_Fragment4 = Education_Fragment.this;
                education_Fragment4.completiondate = education_Fragment4.edtCompletDate.getText().toString();
                Education_Fragment.this.educationDBHandler = new EducationDBHandler(Education_Fragment.this.getContext());
                Education_Fragment.this.educationDBHandler.addNewCourse(Education_Fragment.this.organizationname, Education_Fragment.this.degreetitle, Education_Fragment.this.score, Education_Fragment.this.completiondate);
                Education_Fragment education_Fragment5 = Education_Fragment.this;
                education_Fragment5.list = education_Fragment5.educationDBHandler.readCourses();
                Toast.makeText(Education_Fragment.this.getActivity(), "Saved Successfully", 0).show();
                Education_Fragment.this.edtOrganization.setText("");
                Education_Fragment.this.edtDegree.setText("");
                Education_Fragment.this.edtdescrption.setText("");
                Education_Fragment.this.edtCompletDate.setText("");
                Education_Fragment.this.lyEducation.setVisibility(8);
                Education_Fragment.this.education_recyclerView.setLayoutManager(new LinearLayoutManager(Education_Fragment.this.getContext()));
                Education_Fragment.this.educationAdapter = new EducationAdapter(Education_Fragment.this.getContext(), Education_Fragment.this.list);
                Education_Fragment.this.educationAdapter.notifyDataSetChanged();
                Education_Fragment.this.education_recyclerView.setAdapter(Education_Fragment.this.educationAdapter);
            }
        });
        this.addBtnEdu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Education_Fragment.this.lyEducation.setVisibility(0);
            }
        });
        return inflate;
    }

    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (DatePickerDialog.OnDateSetListener) null, 2014, 1, 24);
        try {
            for (Field field : datePickerDialog.getClass().getDeclaredFields()) {
                if (field.getName().equals("mDatePicker")) {
                    field.setAccessible(true);
                    DatePicker datePicker = (DatePicker) field.get(datePickerDialog);
                    for (Field field2 : field.getType().getDeclaredFields()) {
                        Log.i("test", field2.getName());
                        if ("mDaySpinner".equals(field2.getName())) {
                            field2.setAccessible(true);
                            ((View) field2.get(datePicker)).setVisibility(8);
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return datePickerDialog;
    }
}
