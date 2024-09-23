package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.ProjectDBHandler;
import com.resumemaker.resumecvbuilder.ProjectRecylerviewModel;
import com.resumemaker.resumecvbuilder.R;

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

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder,  int i) {
        this.modelRecylerview = list.get(i);
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.project_name.setText(this.modelRecylerview.getProjectName());
        myExperenceViewHOlder.project_url.setText(this.modelRecylerview.getProjectUrl());
        Log.d("listsize", "listsize" + list.size());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deteteDialogForSkill();
            }

            private void deteteDialogForSkill() {
                ProjectAdapter.this.dialog = new Dialog(mContext);
                ProjectAdapter.this.dialog.requestWindowFeature(1);
                ProjectAdapter.this.dialog.setCancelable(false);
                ProjectAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ProjectAdapter.this.dialog.setContentView(R.layout.deletdialog);
                 dialog.findViewById(R.id.lay_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProjectAdapter.this.dialog.dismiss();
                    }
                });
                 dialog.findViewById(R.id.ly_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(ProjectAdapter.this.mContext, "Project Field Deleted", 0).show();
                        ProjectAdapter.this.projectDBHandler = new ProjectDBHandler(view.getContext());
                        ProjectAdapter.this.projectDBHandler.deleteCourse(myExperenceViewHOlder.project_name.getText().toString());
                        ProjectAdapter.list.remove(i);
                        notifyItemRemoved(i);
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
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(1);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setContentView(R.layout.project_layout);
                final EditText editText = (EditText) dialog.findViewById(R.id.project_name);
                final EditText editText2 = (EditText) dialog.findViewById(R.id.project_url);
                editText.setText(modelRecylerview.getProjectName());
                editText2.setText(modelRecylerview.getProjectUrl());
                dialog.findViewById(R.id.id_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.id_ok).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(mContext, "updating yor data", Toast.LENGTH_SHORT).show();
                        projectDBHandler = new ProjectDBHandler(view.getContext());
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
//                        ProjectAdapter.this.projectDBHandler.updateCourse(obj, obj2);
                        ProjectAdapter.list.set(i, new ProjectRecylerviewModel(obj, obj2));
                        ProjectAdapter.this.notifyItemChanged(i);;
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
        TextView project_name;
        TextView project_url;

        public MyExperenceViewHOlder(View view) {
            super(view);
            this.project_name = (TextView) view.findViewById(R.id.id_projnameone);
            this.project_url = (TextView) view.findViewById(R.id.id_projectoneurl);
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
