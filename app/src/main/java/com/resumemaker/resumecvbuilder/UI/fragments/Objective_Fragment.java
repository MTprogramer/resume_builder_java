package com.resumemaker.resumecvbuilder.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.PersonalInfoRoom.PersonalInfoDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.PersonalInfo;
import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.UI.Create_CV;
import com.resumemaker.resumecvbuilder.callbackes.ObjectCallback;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Objective_Fragment extends Fragment {
    private PersonalInfoDao dbHandler;
    EditText edtObj;
    LinearLayout linearLayout_obj;
    String objObeective;
    PersonalInfo objectiveModelArrayList;
    private final Executor executor = Executors.newSingleThreadExecutor();


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_objective_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.edtObj = (EditText) inflate.findViewById(R.id.cvobj_id);
        this.dbHandler = ResumeDatabase.getInstance(getContext()).personalInfoDao();

        executor.execute(()->{
            this.objectiveModelArrayList = dbHandler.getPersonalInfo();
        });

        this.linearLayout_obj = (LinearLayout) inflate.findViewById(R.id.ly_editcv);
        this.edtObj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    Objective_Fragment.this.linearLayout_obj.setBackgroundResource(R.drawable.objectbg);
                } else {
                    Objective_Fragment.this.linearLayout_obj.setBackgroundResource(R.drawable.ivbg_recyl);
                }
            }
        });
        gettingInformationFromObjectFragment();
        return inflate;
    }

    private void gettingInformationFromObjectFragment() {

        ((Create_CV) requireActivity()).passMsgToObjectFrag(new ObjectCallback() {
            @Override
            public void callback(boolean z) {
                if (edtObj.getText().toString().trim().length() == 0) {
                    Create_CV.isFilled = false;
                    return;
                }
                insert();
                Create_CV.isFilled = true;
            }
        });

    }

    public void onResume() {
        super.onResume();
        if (this.objectiveModelArrayList != null) {
            this.edtObj.setText(this.objectiveModelArrayList.getObj());
        }
    }

    public void insert() {
//        if (this.dbHandler.getObj().isEmpty()) {
//            String cvobective = this.dbHandler.getObj();
//            this.objObeective = cvobective;
//            this.dbHandler.updateObj(objObeective);
//            return;
//        }
        executor.execute(() -> {
            String obj = this.edtObj.getText().toString();
            this.objObeective = obj;
            this.dbHandler.updateObj(obj);
            // Update UI on the main thread
            requireActivity().runOnUiThread(() -> this.edtObj.setText(""));
        });


    }
}
