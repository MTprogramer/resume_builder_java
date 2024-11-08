package com.resumemaker.resumecvbuilder.UI.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.Adapters.EducationAdapterCv;
import com.resumemaker.resumecvbuilder.Adapters.ExperienceAdapterCv;
import com.resumemaker.resumecvbuilder.Adapters.ProjectsAdapterCv;
import com.resumemaker.resumecvbuilder.Adapters.SkillsAdapterCv;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.DataModels.PersonalInfo;
import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;
import com.resumemaker.resumecvbuilder.UI.CvViewer;
import com.resumemaker.resumecvbuilder.UI.MainActivity;
import com.resumemaker.resumecvbuilder.UI.SaveActivity;
import com.resumemaker.resumecvbuilder.ads.AdsCommon;
import com.resumemaker.resumecvbuilder.callbackes.TemplateCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Template_Fragment extends Fragment {
    Bitmap bitmap;
    Dialog dialog;
    SharedPreferences.Editor editor;
    EditText edtcvName;
    Uri imageUri;
    protected boolean mIsVisibleToUser;
    String path = "";
    SharedPreferences preferencescv;


    // adapters
    EducationAdapterCv educationAdapterCv;

    // included layouts
    RelativeLayout rl1;
    RelativeLayout rl2;
    RelativeLayout rl3;
    RelativeLayout rl4;
    RelativeLayout rl5;
    RelativeLayout rl6;
    RelativeLayout rl7;
    LinearLayout rl8;
    RelativeLayout rl9;
    RelativeLayout rl10;
    View currentRl;

    ArrayList<SkillsData> skills_data;
    PersonalInfo personalInfoData;
    ArrayList<ExperienceData> experienceData;
    ArrayList<ProjectsData> projectsData;
    ArrayList<EducationData> educationData;

    // dummy templates
    int tempe = 1;
    ImageView tmp1;
    ImageView tmp2;
    ImageView tmp3;
    ImageView tmp4;
    ImageView tmp5;
    ImageView tmp6;
    ImageView tmp7;
    ImageView tmp8;
    ImageView tmp9;
    ImageView tmp10;


    private final Executor executor = Executors.newSingleThreadExecutor();


    View view;
    View currentView;

    public void onInVisible() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.fragment_template_, viewGroup, false);

        educationData = new ArrayList<>();
        experienceData = new ArrayList<>();
        skills_data = new ArrayList<>();
        projectsData = new ArrayList<>();

        executor.execute(()->{
            ResumeDatabase resumeDatabase = ResumeDatabase.getInstance(getContext());
            educationData.addAll(resumeDatabase.educationDao().getAllEducationData());
            experienceData.addAll(resumeDatabase.experienceDao().getAllExperienceData());
            skills_data.addAll(resumeDatabase.skillsDao().getAllSkillsData());
            projectsData.addAll(resumeDatabase.projectsDao().getAllProjectsData());
            personalInfoData = resumeDatabase.personalInfoDao().getPersonalInfo();

        });
        
        Create_CV.viewPager.setSwipeEnabled(false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREFS_CV", 0);
        this.preferencescv = sharedPreferences;
        this.editor = sharedPreferences.edit();
        try {
            this.imageUri = Uri.parse(getActivity().getSharedPreferences("PREFS_NAME", 0).getString("imageURI", (String) null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rl1 =  view.findViewById(R.id.lay1);
        rl2 =  view.findViewById(R.id.lay2);
        rl3 =  view.findViewById(R.id.lay3);
        rl4 =  view.findViewById(R.id.lay4);
        rl5 =  view.findViewById(R.id.lay5);
        rl6 =  view.findViewById(R.id.lay6);
        rl7 =  view.findViewById(R.id.lay7);
        rl8 =  view.findViewById(R.id.lay8);
        rl9 =  view.findViewById(R.id.lay9);
        rl10 =  view.findViewById(R.id.lay10);

        currentRl = rl1;


        if (tempe == 1)
            settingData();


        tmp1 = view.findViewById(R.id.tamplet1);
        tmp2 = view.findViewById(R.id.tamplets2);
        tmp3 = view.findViewById(R.id.tamplet3);
        tmp4 = view.findViewById(R.id.tamplet4);
        tmp5 = view.findViewById(R.id.tamplet5);
        tmp6 = view.findViewById(R.id.tamplet6);
        tmp7 = view.findViewById(R.id.tamplet7);
        tmp8 = view.findViewById(R.id.tamplet8);
        tmp9 = view.findViewById(R.id.tamplet9);
        tmp10 = view.findViewById(R.id.tamplet10);

        tmp1.setOnClickListener(v -> manageClickedData(1));
        tmp2.setOnClickListener(v -> manageClickedData(2));
        tmp3.setOnClickListener(v -> manageClickedData(3));
        tmp4.setOnClickListener(v -> manageClickedData(4));
        tmp5.setOnClickListener(v -> manageClickedData(5));
        tmp6.setOnClickListener(v -> manageClickedData(6));
        tmp7.setOnClickListener(v -> manageClickedData(7));
        tmp8.setOnClickListener(v -> manageClickedData(8));
        tmp9.setOnClickListener(v -> manageClickedData(9));
        tmp10.setOnClickListener(v -> manageClickedData(10));


        gettingInformationFromParentActivity();
        return this.view;
    }

    private void manageClickedData(int number) {
        tempe = number;
        // Reset all buttons and layouts to non-selected and invisible
        resetSelection();

        switch (number) {
            case 1:
                setSelected(tmp1, rl1);
                break;
            case 2:
                setSelected(tmp2, rl2);
                break;
            case 3:
                setSelected(tmp3, rl3);
                break;
            case 4:
                setSelected(tmp4, rl4);
                break;
            case 5:
                setSelected(tmp5, rl5);
                break;
            case 6:
                setSelected(tmp6, rl6);
                break;
            case 7:
                setSelected(tmp7, rl7);
                break;
            case 8:
                setSelected(tmp8, rl8);
                break;
            case 9:
                setSelected(tmp9, rl9);
                break;
            case 10:
                setSelected(tmp10, rl10);
                break;
        }

        settingData();
    }

    // Helper method to reset all buttons and layouts
    private void resetSelection() {
        tmp1.setBackgroundResource(R.drawable.greyboder);
        tmp2.setBackgroundResource(R.drawable.greyboder);
        tmp3.setBackgroundResource(R.drawable.greyboder);
        tmp4.setBackgroundResource(R.drawable.greyboder);
        tmp5.setBackgroundResource(R.drawable.greyboder);
        tmp6.setBackgroundResource(R.drawable.greyboder);
        tmp7.setBackgroundResource(R.drawable.greyboder);
        tmp8.setBackgroundResource(R.drawable.greyboder);
        tmp9.setBackgroundResource(R.drawable.greyboder);
        tmp10.setBackgroundResource(R.drawable.greyboder);

        rl1.setVisibility(View.GONE);
        rl2.setVisibility(View.GONE);
        rl3.setVisibility(View.GONE);
        rl4.setVisibility(View.GONE);
        rl5.setVisibility(View.GONE);
        rl6.setVisibility(View.GONE);
        rl7.setVisibility(View.GONE);
        rl8.setVisibility(View.GONE);
        rl9.setVisibility(View.GONE);
        rl10.setVisibility(View.GONE);
    }

    // Helper method to set the selected button and layout visible
    private void setSelected(View button, View layout) {
        button.setBackgroundResource(R.drawable.bordrsv);
        layout.setVisibility(View.VISIBLE);
        currentRl = layout;
        Log.d("check", "manageClickedData: " + tempe);
    }


    public void settingData() {

        currentView = currentRl.findViewById(R.id.temp_view);
        ImageView imageView;
        TextView user_name;
        TextView user_email;
        TextView objective;
        TextView phoneNumber;
        TextView designation;
        TextView adress;
        if (tempe == 8)
        {
            Log.d("main view",String.valueOf(this.view));
                View view = this.view.findViewById(R.id.lay8);
                Log.d("view",String.valueOf(view));
            imageView = view.findViewById(R.id.image_view);
            user_name = view.findViewById(R.id.user_name);
            user_email = view.findViewById(R.id.email);
            phoneNumber = view.findViewById(R.id.phoneNumber);
            objective = view.findViewById(R.id.objective);
            designation = view.findViewById(R.id.designation);
            adress = view.findViewById(R.id.adress);
        }
        else
        {
            imageView = currentRl.findViewById(R.id.image_view);
            user_name = currentRl.findViewById(R.id.user_name);
            user_email = currentRl.findViewById(R.id.email);
            phoneNumber = currentRl.findViewById(R.id.phoneNumber);
            objective = currentRl.findViewById(R.id.objective);
            designation = currentRl.findViewById(R.id.designation);
            adress = currentRl.findViewById(R.id.adress);
        }



        RecyclerView experience_recycler = currentRl.findViewById(R.id.experience_recycler);
        RecyclerView education_recycler = currentRl.findViewById(R.id.education_recycler);
        RecyclerView skills_recyler = currentRl.findViewById(R.id.skills_recyler);
        RecyclerView project_recycler = currentRl.findViewById(R.id.project_recycler);


        imageView.setImageURI(this.imageUri);
        user_name.setText(personalInfoData.getFullName());
        user_email.setText(personalInfoData.getEmail());
        phoneNumber.setText(personalInfoData.getPhoneNo());
        adress.setText(personalInfoData.getAddress());
        objective.setText(personalInfoData.getObj());
        designation.setText(personalInfoData.getProfession());



        educationAdapterCv = new EducationAdapterCv(educationData , tempe , getContext());
        education_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        education_recycler.setAdapter(educationAdapterCv);

        ExperienceAdapterCv experienceAdapterCv = new ExperienceAdapterCv(experienceData , tempe , getContext());
        experience_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        experience_recycler.setAdapter(experienceAdapterCv);

        SkillsAdapterCv skillAdapter = new SkillsAdapterCv(skills_data , tempe , getContext());
        skills_recyler.setLayoutManager(new LinearLayoutManager(getContext()));
        skills_recyler.setAdapter(skillAdapter);

        ProjectsAdapterCv projectAdapter = new ProjectsAdapterCv(projectsData , tempe , getContext());
        project_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        project_recycler.setAdapter(projectAdapter);


//        List<EducationData> data = new ArrayList<>();
//        data.add(new EducationData("2022","ics","Shiblee collage","my collage name shiblee collage","2024"));
//        data.add(new EducationData("2022","ics","Shiblee collage","my collage name shiblee collage","2024"));



    }

    public void onStart() {
        super.onStart();
        if (this.mIsVisibleToUser) {
            onVisible();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mIsVisibleToUser) {
            onInVisible();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mIsVisibleToUser = z;
        if (!isResumed()) {
            return;
        }
        if (this.mIsVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    public void onVisible() {
        settingData();
    }

    private void gettingInformationFromParentActivity() {

        ((Create_CV) requireActivity()).passMsgToTempletFrag(new TemplateCallback() {
            @Override
            public void tempcallback(boolean z) {
                saveDialog();
            }
        });
    }

    private void saveDialog() {
        Dialog dialog2 = new Dialog(getActivity());
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setContentView(R.layout.savedialog);
        TextView textView = (TextView) this.dialog.findViewById(R.id.id_ok);
        TextView textView2 = (TextView) this.dialog.findViewById(R.id.id_cancel);
        this.edtcvName = (EditText) this.dialog.findViewById(R.id.eDtcvname);
        ((LinearLayout) this.dialog.findViewById(R.id.lay_ok_dialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Template_Fragment.this.dialog.dismiss();
            }
        });
        ((LinearLayout) this.dialog.findViewById(R.id.ly_cancel_dialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Template_Fragment.this.edtcvName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Template_Fragment.this.getContext(), "write cv name", Toast.LENGTH_SHORT).show();
                    return;
                }
                Template_Fragment.this.editor.putString("cvsaved", Template_Fragment.this.edtcvName.getText().toString());
                Template_Fragment.this.editor.commit();
                Template_Fragment.this.createPdf();
//                Template_Fragment.this.dbHandler.deleteData();
//                Template_Fragment.this.objectiveDBHandler.deleteData();
//                Template_Fragment.this.experienceDBHandler.deleteData();
//                Template_Fragment.this.educationDBHandler.deleteData();
//                Template_Fragment.this.skillDBHandler.deleteData();
//                Template_Fragment.this.projectDBHandler.deleteData();
                SharedPreferences sharedPreferences = Template_Fragment.this.getActivity().getSharedPreferences("PREFS_NAME", 0);
                String string = sharedPreferences.getString("imageURI", (String) null);
                try {
                    Template_Fragment.this.imageUri = Uri.parse(string);
                    if (Template_Fragment.this.imageUri != null) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("imageURI", (String) null);
                        edit.commit();
                        edit.remove("imageURI").commit();
                        PersonalInfo_Fragment.checkImage = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Template_Fragment.this.dialog.dismiss();
                Template_Fragment.this.saveCvDialog();
            }
        });
        this.dialog.show();
    }

    
    public void saveCvDialog() {
        Dialog dialog2 = new Dialog(getActivity());
        this.dialog = dialog2;
        dialog2.requestWindowFeature(1);
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setContentView(R.layout.exitdialogsave);
        TextView textView = (TextView) this.dialog.findViewById(R.id.id_ok);
        TextView textView2 = (TextView) this.dialog.findViewById(R.id.id_cancel);

        ((LinearLayout) this.dialog.findViewById(R.id.ly_later)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLATERcall(v);
            }
        });
        ((LinearLayout) this.dialog.findViewById(R.id.lay_view)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Template_Fragment.this.dialog.dismiss();

                Intent intent = new Intent(getActivity(), SaveActivity.class);
                AdsCommon.InterstitialAd(getActivity(), intent);
            }
        });
        this.dialog.show();
    }
    
    public  void dialogLATERcall(View view2) {

        this.path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/PdfCv/";
        Intent intent = new Intent(getActivity(), CvViewer.class);
        intent.putExtra("cvpath", this.path + "/" + this.edtcvName.getText().toString() + ".pdf");
        CallAds(intent);

        startActivity(new Intent(getActivity(), MainActivity.class));
        this.dialog.dismiss();
    }

    private void CallAds(Intent intent) {
        AdsCommon.InterstitialAd(getActivity(), intent);
    }


    public void createPdf() {
        int width = 0;
        int height = 0;
        ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());

        View linearLayout = this.currentView;
        this.bitmap = loadBitmapFromView(linearLayout, linearLayout.getWidth(), this.currentView.getHeight());
        height = this.currentView.getHeight();
        width = this.currentView.getWidth();


        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(width, height, 1).create());
        Canvas canvas = startPage.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        canvas.drawColor(-1);
        paint.setColor(-16776961);
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, (Paint) null);
        pdfDocument.finishPage(startPage);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/PdfCv/");
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            pdfDocument.writeTo(new FileOutputStream(new File(file, this.edtcvName.getText().toString() + ".pdf")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }

    public static Bitmap loadBitmapFromView(View view2, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        view2.draw(new Canvas(createBitmap));
        return createBitmap;
    }
}
