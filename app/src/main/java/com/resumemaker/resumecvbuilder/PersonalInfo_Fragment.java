package com.resumemaker.resumecvbuilder;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PersonalInfo_Fragment extends Fragment {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    private static int RESULT_LOAD_IMAGE = 1;
    static final int SELECT_IMAGE = 1000;
    private static final int STORAGE_REQUEST_CODE = 101;
    public static boolean checkImage = false;
    public static boolean checkTab = false;
    public static CircleImageView imgcircularimage;
    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private EditText addressEditText;
    Button btn;
    Button btnNxt;
    Button btnview;
    private String[] cameraPermissions;
    private EditText contactEditText;
    SQLiteDatabase db;
    private PersonalInfoDBHandler dbHandler;
    ArrayList<PersonalDetailsModel> detailsModel;
    
    public EditText emailEditText;
    boolean emailvalid = false;
    String employeAddress;
    String employeEmail;
    String employeName;
    String employeeContact;
    Uri imageUri;
    private Uri imageuri;
    String imagevieProfile;
    boolean isFragmentLoaded = false;
    LinearLayout linearLayout_adress;
    LinearLayout linearLayout_email;
    LinearLayout linearLayout_fullname;
    LinearLayout linearLayout_phone;
    LinearLayout linearLayout_profession;
    private EditText nameEditText;
    String picturePath;
    Bitmap poto = null;
    private EditText profession;
    String professionEmploye;
    ImageView setprofiledimg;
    private String[] storagePermissions;
    ImageView uploadedimg;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_personal_info_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.nameEditText = (EditText) inflate.findViewById(R.id.id_name);
        this.profession = (EditText) inflate.findViewById(R.id.id_profession);
        this.addressEditText = (EditText) inflate.findViewById(R.id.emailadress);
        this.emailEditText = (EditText) inflate.findViewById(R.id.id_email);
        this.contactEditText = (EditText) inflate.findViewById(R.id.id_phone);
        imgcircularimage = (CircleImageView) inflate.findViewById(R.id.imgcircular);
        this.uploadedimg = (ImageView) inflate.findViewById(R.id.imgupload);
        this.cameraPermissions = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
        this.storagePermissions = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"};
        this.linearLayout_fullname = (LinearLayout) inflate.findViewById(R.id.ly_fullname);
        this.linearLayout_profession = (LinearLayout) inflate.findViewById(R.id.ly_profession);
        this.linearLayout_phone = (LinearLayout) inflate.findViewById(R.id.lyphoneno);
        this.linearLayout_email = (LinearLayout) inflate.findViewById(R.id.lyEmail);
        this.linearLayout_adress = (LinearLayout) inflate.findViewById(R.id.lyAddress);
        this.detailsModel = new ArrayList<>();
        PersonalInfoDBHandler personalInfoDBHandler = new PersonalInfoDBHandler(getContext());
        this.dbHandler = personalInfoDBHandler;
        this.detailsModel = personalInfoDBHandler.readCourses();
        try {
            Uri parse = Uri.parse(getActivity().getSharedPreferences("PREFS_NAME", 0).getString("imageURI", (String) null));
            this.imageUri = parse;
            imgcircularimage.setImageURI(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PersonalInfo_Fragment.this.linearLayout_fullname.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    PersonalInfo_Fragment.this.linearLayout_fullname.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.profession.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PersonalInfo_Fragment.this.linearLayout_profession.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    PersonalInfo_Fragment.this.linearLayout_profession.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.addressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PersonalInfo_Fragment.this.linearLayout_adress.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    PersonalInfo_Fragment.this.linearLayout_adress.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.contactEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PersonalInfo_Fragment.this.linearLayout_phone.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    PersonalInfo_Fragment.this.linearLayout_phone.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        this.emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PersonalInfo_Fragment.this.linearLayout_email.setBackgroundResource(R.drawable.ivinputbg);
                } else {
                    PersonalInfo_Fragment.this.linearLayout_email.setBackgroundResource(R.drawable.ivbg_recyl);
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
                    PersonalInfo_Fragment.this.emailEditText.setTextColor(PersonalInfo_Fragment.this.getResources().getColor(R.color.red));
                    PersonalInfo_Fragment.this.emailvalid = false;
                    return;
                }
                PersonalInfo_Fragment.this.emailvalid = true;
                PersonalInfo_Fragment.this.emailEditText.setTextColor(PersonalInfo_Fragment.this.getResources().getColor(R.color.black));
            }
        });
        this.uploadedimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImagePicker.Companion.with((Activity) PersonalInfo_Fragment.this.getActivity()).crop().maxResultSize(1080, 1080).start();
            }
        });
        gettingMessageFromParentActivity();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (this.detailsModel.size() > 0) {
            this.nameEditText.setText(this.detailsModel.get(0).getName());
            this.profession.setText(this.detailsModel.get(0).getProfession());
            this.addressEditText.setText(this.detailsModel.get(0).getAddress());
            this.emailEditText.setText(this.detailsModel.get(0).getEmail());
            this.contactEditText.setText(this.detailsModel.get(0).getContact());
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

    public void insert(ArrayList<PersonalDetailsModel> arrayList) {
        if (this.dbHandler.getDataCount() > 0) {
            String name = this.dbHandler.readCourses().get(0).getName();
            this.employeName = name;
            this.dbHandler.updateCourse(name, this.nameEditText.getText().toString(), this.profession.getText().toString(), this.addressEditText.getText().toString(), this.emailEditText.getText().toString(), this.contactEditText.getText().toString());
            return;
        }
        this.employeName = this.nameEditText.getText().toString();
        this.professionEmploye = this.profession.getText().toString();
        this.employeeContact = this.contactEditText.getText().toString();
        this.employeAddress = this.addressEditText.getText().toString();
        String obj = this.emailEditText.getText().toString();
        this.employeEmail = obj;
        this.dbHandler.addNewCourse(this.employeName, this.professionEmploye, this.employeeContact, obj, this.employeAddress);
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
                if (nameEditText.getText().toString().trim().length() == 0 || profession.getText().toString().trim().length() == 0 || addressEditText.getText().toString().trim().length() == 0 || emailEditText.getText().toString().trim().length() == 0 || contactEditText.getText().toString().trim().length() == 0 || !checkImage || !emailvalid) {
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
                    Toast.makeText(getContext(), "Storage and Storage Permission is required", 0).show();
                }
            }
        } else if (iArr.length > 0) {
            boolean z2 = iArr[0] == 0;
            if (iArr[1] != 0) {
                z = false;
            }
            if (!z2 || !z) {
                Toast.makeText(getContext(), "Camera and Storage Permissions are required", 0).show();
            } else {
                pickFromcamera();
            }
        }
    }
}
