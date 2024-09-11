package com.resumemaker.resumecvbuilder;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyExperenceViewHOlder> {
    static ArrayList<ProjectRecylerviewModel> list;
    Dialog dialog;
    Context mContext;
    ProjectRecylerviewModel modelRecylerview;
    ProjectDBHandler projectDBHandler;

    public ProjectAdapter(Context context, ArrayList<ProjectRecylerviewModel> arrayList) {
        this.mContext = context;
        list = arrayList;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.projectitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        this.modelRecylerview = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Projects");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_skillone.setText(this.modelRecylerview.getProjectNameOne());
        myExperenceViewHOlder.txt_skilltwo.setText(this.modelRecylerview.getProjectOneUrl());
        myExperenceViewHOlder.txt_skillthree.setText(this.modelRecylerview.getProjectNameTwo());
        myExperenceViewHOlder.txt_skillfourth.setText(this.modelRecylerview.getProjectTwoUrl());
        Log.d("listsize", "listsize" + list.size());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deteteDialogForSkill();
            }

            private void deteteDialogForSkill() {
                ProjectAdapter.this.dialog = new Dialog(ProjectAdapter.this.mContext);
                ProjectAdapter.this.dialog.requestWindowFeature(1);
                ProjectAdapter.this.dialog.setCancelable(false);
                ProjectAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ProjectAdapter.this.dialog.setContentView(R.layout.deletdialog);
                TextView textView = (TextView) ProjectAdapter.this.dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) ProjectAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) ProjectAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProjectAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) ProjectAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(ProjectAdapter.this.mContext, "Project Field Deleted", 0).show();
                        ProjectAdapter.this.projectDBHandler = new ProjectDBHandler(view.getContext());
                        ProjectAdapter.this.projectDBHandler.deleteCourse(myExperenceViewHOlder.txt_skillone.getText().toString());
                        ProjectAdapter.list.remove(i);
                        ProjectAdapter.this.notifyDataSetChanged();
                        ProjectAdapter.this.dialog.dismiss();
                    }
                });
                ProjectAdapter.this.dialog.show();
            }

        });
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                ProjectAdapter.this.dialog = new Dialog(ProjectAdapter.this.mContext);
                ProjectAdapter.this.dialog.requestWindowFeature(1);
                ProjectAdapter.this.dialog.setCancelable(false);
                ProjectAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ProjectAdapter.this.dialog.setContentView(R.layout.dialog_update_sill);
                TextView textView = (TextView) ProjectAdapter.this.dialog.findViewById(R.id.id_ok);
                final EditText editText = (EditText) ProjectAdapter.this.dialog.findViewById(R.id.skill_edt_org);
                final EditText editText2 = (EditText) ProjectAdapter.this.dialog.findViewById(R.id.skill_edt_design);
                final EditText editText3 = (EditText) ProjectAdapter.this.dialog.findViewById(R.id.skill_edt_jdate);
                final EditText editText4 = (EditText) ProjectAdapter.this.dialog.findViewById(R.id.skil_edt_endate);
                editText.setText(ProjectAdapter.this.modelRecylerview.getProjectNameOne());
                editText2.setText(ProjectAdapter.this.modelRecylerview.getProjectOneUrl());
                editText3.setText(ProjectAdapter.this.modelRecylerview.getProjectNameTwo());
                editText4.setText(ProjectAdapter.this.modelRecylerview.getProjectTwoUrl());
                TextView textView2 = (TextView) ProjectAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) ProjectAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProjectAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) ProjectAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(ProjectAdapter.this.mContext, "updating yor data", 0).show();
                        ProjectAdapter.this.projectDBHandler = new ProjectDBHandler(view.getContext());
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
                        String obj3 = editText3.getText().toString();
                        String obj4 = editText4.getText().toString();
                        ProjectAdapter.this.projectDBHandler.updateCourse(myExperenceViewHOlder.txt_skillone.getText().toString(), obj, obj2, obj3, obj4);
                        ProjectAdapter.list.set(i, new ProjectRecylerviewModel(obj, obj2, obj3, obj4));
                        ProjectAdapter.this.notifyItemChanged(i);
                        ProjectAdapter.this.notifyDataSetChanged();
                        ProjectAdapter.this.dialog.dismiss();
                    }
                });
                ProjectAdapter.this.dialog.show();
            }

        });
    }

    public int getItemCount() {
        return list.size();
    }

    public class MyExperenceViewHOlder extends RecyclerView.ViewHolder {
        ImageView iv_delete;
        ImageView iv_update;
        TextView tv_title;
        TextView txt_skillfifth;
        TextView txt_skillfourth;
        TextView txt_skillone;
        TextView txt_skillthree;
        TextView txt_skilltwo;

        public MyExperenceViewHOlder(View view) {
            super(view);
            this.tv_title = (TextView) view.findViewById(R.id.exp_tv_title);
            this.txt_skillone = (TextView) view.findViewById(R.id.id_projnameone);
            this.txt_skilltwo = (TextView) view.findViewById(R.id.id_projectoneurl);
            this.txt_skillthree = (TextView) view.findViewById(R.id.id_projecttwoname);
            this.txt_skillfourth = (TextView) view.findViewById(R.id.id_projecttwourl);
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
