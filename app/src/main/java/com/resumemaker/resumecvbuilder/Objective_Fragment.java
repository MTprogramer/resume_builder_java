package com.resumemaker.resumecvbuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class Objective_Fragment extends Fragment {
    private ObjectiveDBHandler dbHandler;
    EditText edtObj;
    LinearLayout linearLayout_obj;
    String objObeective;
    ArrayList<Objective_Model> objectiveModelArrayList;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_objective_, viewGroup, false);

        Create_CV.viewPager.setSwipeEnabled(false);
        this.edtObj = (EditText) inflate.findViewById(R.id.cvobj_id);
        this.objectiveModelArrayList = new ArrayList<>();
        ObjectiveDBHandler objectiveDBHandler = new ObjectiveDBHandler(getContext());
        this.dbHandler = objectiveDBHandler;
        this.objectiveModelArrayList = objectiveDBHandler.readCourses();
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
                insert(objectiveModelArrayList);
                Create_CV.isFilled = true;
            }
        });

    }

    public void onResume() {
        super.onResume();
        if (this.objectiveModelArrayList.size() > 0) {
            this.edtObj.setText(this.objectiveModelArrayList.get(0).getCvobective());
        }
    }

    public void insert(ArrayList<Objective_Model> arrayList) {
        if (this.dbHandler.getDataCount() > 0) {
            String cvobective = this.dbHandler.readCourses().get(0).getCvobective();
            this.objObeective = cvobective;
            this.dbHandler.updateCourse(cvobective, this.edtObj.getText().toString());
            return;
        }
        String obj = this.edtObj.getText().toString();
        this.objObeective = obj;
        this.dbHandler.addNewCourse(obj);
        this.edtObj.setText("");
    }
}
