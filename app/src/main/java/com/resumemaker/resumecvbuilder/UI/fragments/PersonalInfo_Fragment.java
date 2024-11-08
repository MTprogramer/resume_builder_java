package com.resumemaker.resumecvbuilder.UI.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.PersonalInfoRoom.PersonalInfoDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.PersonalInfo;
import com.resumemaker.resumecvbuilder.PersonalDetailsModel;
import com.resumemaker.resumecvbuilder.PersonalInfoDBHandler;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;
import com.resumemaker.resumecvbuilder.callbackes.MyCallback;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class PersonalInfo_Fragment extends Fragment {
    public static boolean checkImage = false;
    public static CircleImageView imgcircularimage;
    private EditText addressEditText;
    private String[] cameraPermissions;
    private EditText contactEditText;
    SQLiteDatabase db;
    private PersonalInfoDBHandler dbHandler;
    PersonalInfo detailsModel;

    PersonalInfoDao personalInfoDao;


    private final Executor executor = Executors.newSingleThreadExecutor();
    
    public EditText emailEditText;
    boolean emailvalid = false;
    String employeAddress;
    String employeEmail;
    String obj;
    String employeName;
    String employeeContact;
    Uri imageUri;
    private Uri imageuri;
    LinearLayout linearLayout_adress;
    LinearLayout linearLayout_email;
    LinearLayout linearLayout_fullname;
    LinearLayout linearLayout_phone;
    LinearLayout linearLayout_profession;
    private EditText nameEditText;
    private EditText profession;
    String professionEmploye;
    private String[] storagePermissions;
    ImageView uploadedimg;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_personal_info_, viewGroup, false);


        ResumeDatabase resumeDatabase = ResumeDatabase.getInstance(getContext());
        personalInfoDao = resumeDatabase.personalInfoDao();


        Create_CV.viewPager.setSwipeEnabled(false);
        nameEditText = inflate.findViewById(R.id.id_name);
        profession = inflate.findViewById(R.id.id_profession);
        addressEditText = inflate.findViewById(R.id.emailadress);
        emailEditText = inflate.findViewById(R.id.id_email);
        contactEditText = inflate.findViewById(R.id.id_phone);
        imgcircularimage = inflate.findViewById(R.id.imgcircular);
        uploadedimg = inflate.findViewById(R.id.imgupload);
        linearLayout_fullname = inflate.findViewById(R.id.ly_fullname);
        linearLayout_profession = inflate.findViewById(R.id.ly_profession);
        linearLayout_phone = inflate.findViewById(R.id.lyphoneno);
        linearLayout_email = inflate.findViewById(R.id.lyEmail);
        linearLayout_adress = inflate.findViewById(R.id.lyAddress);
        PersonalInfoDBHandler personalInfoDBHandler = new PersonalInfoDBHandler(getContext());
        dbHandler = personalInfoDBHandler;
        executor.execute(()->{
            detailsModel = personalInfoDao.getPersonalInfo();
        });
        try {
            Uri parse = Uri.parse(getActivity().getSharedPreferences("PREFS_NAME", 0).getString("imageURI", (String) null));
            imageUri = parse;
            imgcircularimage.setImageURI(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    linearLayout_fullname.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_fullname.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.profession.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    linearLayout_profession.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_profession.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.addressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    linearLayout_adress.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_adress.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.contactEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    linearLayout_phone.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_phone.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    linearLayout_email.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    linearLayout_email.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.emailEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (!Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", editable)) {
                    emailEditText.setTextColor(getResources().getColor(R.color.red));
                    emailvalid = false;
                    return;
                }
                emailvalid = true;
                emailEditText.setTextColor(getResources().getColor(R.color.black));
            }
        });
        this.uploadedimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImagePicker.Companion.with(getActivity()).crop().maxResultSize(1080, 1080).start();
            }
        });
        gettingMessageFromParentActivity();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (detailsModel != null) {
            this.nameEditText.setText(this.detailsModel.getFullName());
            this.profession.setText(this.detailsModel.getProfession());
            this.addressEditText.setText(this.detailsModel.getAddress());
            this.emailEditText.setText(this.detailsModel.getEmail());
            this.contactEditText.setText(this.detailsModel.getPhoneNo());
            obj = this.detailsModel.getObj();
            checkImage = true;
        }
    }

    private void pickFromGallery() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 103);
    }

    private void pickFromcamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", "Image Title");
        contentValues.put("description", "Image description");
        this.imageuri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.imageuri);
        startActivityForResult(intent, 102);
    }

    public void insert(PersonalInfo arrayList) {
//        if (personalInfoDao.getCount() > 0) {
//            String name = this.dbHandler.readCourses().get(0).getName();
//            this.employeName = name;
//            this.dbHandler.updateCourse(name, this.nameEditText.getText().toString(), this.profession.getText().toString(), this.addressEditText.getText().toString(), this.emailEditText.getText().toString(), this.contactEditText.getText().toString());
//            return;
//        }
        this.employeName = this.nameEditText.getText().toString();
        this.professionEmploye = this.profession.getText().toString();
        this.employeeContact = this.contactEditText.getText().toString();
        this.employeAddress = this.addressEditText.getText().toString();
        String email = this.emailEditText.getText().toString();
        this.employeEmail = email;
//        this.dbHandler.addNewCourse(this.employeName, this.professionEmploye, this.employeeContact, email, this.employeAddress);

        executor.execute(() -> {
            personalInfoDao.clearAll();
            personalInfoDao.insertOrUpdate(new PersonalInfo(this.imageUri.toString(), this.employeName, this.professionEmploye, email, this.employeeContact , this.employeAddress , obj ));
            // Check if data was inserted successfully
            if (personalInfoDao.getCount() > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data insertion failed.");
            }
        });

        this.nameEditText.setText("");
        this.profession.setText("");
        this.contactEditText.setText("");
        this.emailEditText.setText("");
        this.addressEditText.setText("");
    }

    private void gettingMessageFromParentActivity() {
        ((Create_CV) requireActivity()).passMsgToTextFrag(new MyCallback() {
            @Override
            public void callbackCall(boolean z) {
                if (nameEditText.getText().toString().trim().isEmpty() || profession.getText().toString().trim().isEmpty() || addressEditText.getText().toString().trim().isEmpty() || emailEditText.getText().toString().trim().length() == 0 || contactEditText.getText().toString().trim().length() == 0 || !checkImage || !emailvalid) {
                    Create_CV.isFilled = false;
                    return;
                }
                Create_CV.isFilled = true;
                insert(detailsModel);
            }
        });
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = true;
        if (i != 100) {
            if (i == 101 && iArr.length > 0) {
                if (iArr[0] != 0) {
                    z = false;
                }
                if (z) {
                    pickFromGallery();
                } else {
                    Toast.makeText(getContext(), "Storage and Storage Permission is required", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (iArr.length > 0) {
            boolean z2 = iArr[0] == 0;
            if (iArr[1] != 0) {
                z = false;
            }
            if (!z2 || !z) {
                Toast.makeText(getContext(), "Camera and Storage Permissions are required", Toast.LENGTH_SHORT).show();
            } else {
                pickFromcamera();
            }
        }
    }
}
