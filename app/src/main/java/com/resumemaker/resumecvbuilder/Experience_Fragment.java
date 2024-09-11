package com.resumemaker.resumecvbuilder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Experience_Fragment extends Fragment {
    ImageView addExperence;
    Calendar calendar = Calendar.getInstance();
    String designations;
    Dialog dialog;
    EditText edtDesignation;
    EditText edtJobdescrption;
    EditText edtOrganization;
    String endingDate;
    ExperienceAdapter experienceAdapter;
    ExperienceDBHandler experienceDBHandler;
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
    ArrayList<ExperienceRecylerviewModel> list;
    LinearLayout lyoutMain;
    final Calendar myCalendar = Calendar.getInstance();
    String organozationname;
    TextView txtEnddate;
    TextView txtjoinDate;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_experience_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.experience_recyclerView = (RecyclerView) inflate.findViewById(R.id.recylewview);
        this.list = new ArrayList<>();
        ExperienceDBHandler experienceDBHandler2 = new ExperienceDBHandler(getContext());
        this.experienceDBHandler = experienceDBHandler2;
        this.list = experienceDBHandler2.readCourses();
        this.experienceAdapter = new ExperienceAdapter(getContext(), this.list);
        this.experience_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.experienceAdapter.notifyDataSetChanged();
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
                Experience_Fragment.this.lyoutMain.setVisibility(0);
            }
        });
        this.ivSaved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Experience_Fragment.this.edtOrganization.getText().toString().trim().length() == 0 || Experience_Fragment.this.edtDesignation.getText().toString().trim().length() == 0 || Experience_Fragment.this.txtjoinDate.getText().toString().trim().length() == 0 || Experience_Fragment.this.txtEnddate.getText().toString().trim().length() == 0 || Experience_Fragment.this.edtJobdescrption.getText().toString().trim().length() == 0) {
                    Toast.makeText(Experience_Fragment.this.getActivity(), "Please Fill all Fields", 0).show();
                    Experience_Fragment.this.lyoutMain.setVisibility(0);
                    return;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.US);
                try {
                    Date parse = simpleDateFormat.parse(Experience_Fragment.this.txtjoinDate.getText().toString());
                    Date parse2 = simpleDateFormat.parse(Experience_Fragment.this.txtEnddate.getText().toString());
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse);
                    Calendar instance2 = Calendar.getInstance();
                    instance2.setTime(parse2);
                    int i = instance.get(1);
                    int i2 = instance.get(2);
                    int i3 = instance.get(5);
                    int i4 = ((instance2.get(1) - i) * 12) + (instance2.get(2) - i2);
                    if (instance2.get(5) < i3) {
                        i4--;
                    }
                    if (i4 >= 6) {
                        Experience_Fragment experience_Fragment = Experience_Fragment.this;
                        experience_Fragment.organozationname = experience_Fragment.edtOrganization.getText().toString();
                        Experience_Fragment experience_Fragment2 = Experience_Fragment.this;
                        experience_Fragment2.designations = experience_Fragment2.edtDesignation.getText().toString();
                        Experience_Fragment experience_Fragment3 = Experience_Fragment.this;
                        experience_Fragment3.joiningDate = experience_Fragment3.txtjoinDate.getText().toString();
                        Experience_Fragment experience_Fragment4 = Experience_Fragment.this;
                        experience_Fragment4.endingDate = experience_Fragment4.txtEnddate.getText().toString();
                        Experience_Fragment experience_Fragment5 = Experience_Fragment.this;
                        experience_Fragment5.jobdescriptions = experience_Fragment5.edtJobdescrption.getText().toString();
                        Experience_Fragment.this.experienceDBHandler.addNewCourse(Experience_Fragment.this.organozationname, Experience_Fragment.this.designations, Experience_Fragment.this.joiningDate, Experience_Fragment.this.endingDate, Experience_Fragment.this.jobdescriptions);
                        Experience_Fragment experience_Fragment6 = Experience_Fragment.this;
                        experience_Fragment6.list = experience_Fragment6.experienceDBHandler.readCourses();
                        Toast.makeText(Experience_Fragment.this.getActivity(), "Saved Successsfully", 0).show();
                        Experience_Fragment.this.edtOrganization.setText("");
                        Experience_Fragment.this.edtDesignation.setText("");
                        Experience_Fragment.this.txtjoinDate.setText("");
                        Experience_Fragment.this.txtEnddate.setText("");
                        Experience_Fragment.this.edtJobdescrption.setText("");
                        Experience_Fragment.this.lyoutMain.setVisibility(8);
                        Experience_Fragment.this.experience_recyclerView.setLayoutManager(new LinearLayoutManager(Experience_Fragment.this.getContext()));
                        Experience_Fragment.this.experienceAdapter = new ExperienceAdapter(Experience_Fragment.this.getContext(), Experience_Fragment.this.list);
                        Experience_Fragment.this.experienceAdapter.notifyDataSetChanged();
                        Experience_Fragment.this.experience_recyclerView.setAdapter(Experience_Fragment.this.experienceAdapter);
                        return;
                    }
                    Toast.makeText(Experience_Fragment.this.getActivity(), "You Enter Experience Less Than Sixth Months", 0).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                textView.setText(new SimpleDateFormat("dd/MM/yy", Locale.US).format(Experience_Fragment.this.myCalendar.getTime()));
            }
        }, this.myCalendar.get(1), this.myCalendar.get(2), this.myCalendar.get(5)).show();
    }
}
