package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ProjectRoom.ProjectsDao;
import com.resumemaker.resumecvbuilder.DataModels.ProjectsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyExperenceViewHOlder> {
    static ArrayList<ProjectsData> list;
    Dialog dialog;
    Context mContext;
    ProjectsData modelRecylerview;
    ProjectsDao projectDBHandler;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public ProjectAdapter(Context context, ArrayList<ProjectsData> arrayList , ProjectsDao projectDBHandler) {
        this.mContext = context;
        list = arrayList;
        this.projectDBHandler = projectDBHandler;
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
                deleteDialogForSkill();
            }
            private void deleteDialogForSkill() {
                // Initialize dialog
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  // Use Window.FEATURE_NO_TITLE for clarity
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); // Transparent background
                dialog.setContentView(R.layout.deletdialog);

                // OK button (dismiss dialog)
                dialog.findViewById(R.id.lay_ok).setOnClickListener(v -> dialog.dismiss());

                // Cancel button (perform delete operation)
                dialog.findViewById(R.id.ly_cancel).setOnClickListener(v -> {
                    executor.execute(() -> {

                        ProjectsData itemToDelete = list.get(i);

                        projectDBHandler.delete(itemToDelete);

                        // Update UI on the main thread

                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (i < list.size()) {
                                list.remove(i);  // Remove the item safely
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, list.size());
                                Toast.makeText(mContext, "project Deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    });
                });
                // Show dialog
                dialog.show();
            }
        });
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }
            private void updateDialog() {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  // Improved constant readability
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); // Transparent background
                dialog.setContentView(R.layout.project_layout);

                // Initialize input fields
                final EditText editText = dialog.findViewById(R.id.project_name);
                final EditText editText2 = dialog.findViewById(R.id.project_url);
                editText.setText(list.get(i).getProjectName());
                editText2.setText(list.get(i).getProjectUrl());

                // Cancel button listener
                dialog.findViewById(R.id.id_cancel).setOnClickListener(v -> dialog.dismiss());

                // Confirm button listener
                dialog.findViewById(R.id.id_ok).setOnClickListener(view -> {
                    String projectName = editText.getText().toString().trim();
                    String projectUrl = editText2.getText().toString().trim();
                    if (projectName.isEmpty() || projectUrl.isEmpty()) {
                        Toast.makeText(mContext, "Field can't be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    executor.execute(() -> {
                        // Prepare updated data
                        ProjectsData projectData = new ProjectsData(projectName, projectUrl);
                        projectData.setId(modelRecylerview.getId());
                        // Update database
                        projectDBHandler.update(projectData);
                        // Update UI on main thread
                        new Handler(Looper.getMainLooper()).post(() -> {
                            list.set(i, projectData);
                            notifyItemChanged(i);
                            Toast.makeText(mContext, "Your data updated", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        });
                    });
                });
                dialog.show();
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
