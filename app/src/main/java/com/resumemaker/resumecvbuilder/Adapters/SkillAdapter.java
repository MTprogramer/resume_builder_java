package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;


import com.resumemaker.resumecvbuilder.R;
import com.resumemaker.resumecvbuilder.SkillDBHandler;
import com.resumemaker.resumecvbuilder.SkillRecylerviewModel;

import java.util.ArrayList;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.MyExperenceViewHOlder>  implements AdapterView.OnItemSelectedListener {
    static ArrayList<SkillRecylerviewModel> list;
    Dialog dialog;
    Context mContext;
    SkillRecylerviewModel modelRecylerview;
    SkillDBHandler skillDBHandler;
    String selected_lvl;
    String[] skillsLevel = {"Beginner", "Intermediate", "Expert"};

    public SkillAdapter(Context context, ArrayList<SkillRecylerviewModel> arrayList) {
        this.mContext = context;
        list = arrayList;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.skillitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        this.modelRecylerview = list.get(i);
        myExperenceViewHOlder.skill_name.setText(this.modelRecylerview.getSkillName());
        myExperenceViewHOlder.skill_level.setText(this.modelRecylerview.getSkillLevel());
        Log.d("listsize", "listsize" + list.size());


        myExperenceViewHOlder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deteteDialogForSkill();
            }

            private void deteteDialogForSkill() {
                SkillAdapter.this.dialog = new Dialog(SkillAdapter.this.mContext);
                SkillAdapter.this.dialog.requestWindowFeature(1);
                SkillAdapter.this.dialog.setCancelable(false);
                SkillAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                SkillAdapter.this.dialog.setContentView(R.layout.deletdialog);
                TextView textView = (TextView) SkillAdapter.this.dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) SkillAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) SkillAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) SkillAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(SkillAdapter.this.mContext, "Skill Field Deleted", 0).show();
//                        SkillAdapter.this.skillDBHandler = new SkillDBHandler(view.getContext());
//                        SkillAdapter.this.skillDBHandler.deleteCourse(myExperenceViewHOlder.txt_skillone.getText().toString());
                        SkillAdapter.list.remove(i);
                        notifyItemRemoved(i);
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                SkillAdapter.this.dialog.show();
            }
        });
        myExperenceViewHOlder.update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                SkillAdapter.this.dialog = new Dialog(SkillAdapter.this.mContext);
                SkillAdapter.this.dialog.requestWindowFeature(1);
                SkillAdapter.this.dialog.setCancelable(false);
                SkillAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                SkillAdapter.this.dialog.setContentView(R.layout.dialog_update_sill);
                EditText skill_name = (EditText) SkillAdapter.this.dialog.findViewById(R.id.skills_name);
                Spinner skill_level =  SkillAdapter.this.dialog.findViewById(R.id.spinner);

                ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, 17367048, skillsLevel);
                arrayAdapter.setDropDownViewResource(17367049);
                skill_level.setAdapter(arrayAdapter);

                switch (list.get(i).getSkillLevel())
                {
                    case "Beginner":
                        skill_level.setSelection(0);
                        break;
                    case "Intermediate":
                        skill_level.setSelection(1);
                        break;
                    case "Expert":
                        skill_level.setSelection(2);
                        break;
                }
                skill_name.setText(list.get(i).getSkillName());
                selected_lvl = list.get(i).getSkillLevel();

                dialog.findViewById(R.id.lay_cencel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.ly_ok).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        if (!skill_name.getText().toString().isEmpty()) {
//                        SkillAdapter.this.skillDBHandler = new SkillDBHandler(view.getContext());
//                        SkillAdapter.this.skillDBHandler.updateCourse(myExperenceViewHOlder.txt_skillone.getText().toString(), obj, obj2, obj3, obj4);

                            String obj = skill_name.getText().toString();
                            SkillAdapter.list.set(i, new SkillRecylerviewModel(obj, selected_lvl));
                            SkillAdapter.this.notifyItemChanged(i);
                            SkillAdapter.this.dialog.dismiss();
                        }
                        else
                            Toast.makeText(SkillAdapter.this.mContext, "Field Cant Be Empty", Toast.LENGTH_SHORT).show();
                    }
                });
                SkillAdapter.this.dialog.show();
            }
        });
    }



    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_lvl = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class MyExperenceViewHOlder extends RecyclerView.ViewHolder {
        TextView skill_name;
        TextView skill_level;
        ImageView delete;
        ImageView update;
        public MyExperenceViewHOlder(View view) {
            super(view);
            skill_name = (TextView) view.findViewById(R.id.skill_name);
            skill_level = (TextView) view.findViewById(R.id.skill_level);
            delete =  view.findViewById(R.id.delet_image_id);
            update =  view.findViewById(R.id.edt_image_id);
        }
    }
}
