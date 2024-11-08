package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.SkillsDao;
import com.resumemaker.resumecvbuilder.DataModels.SkillsData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.MyExperenceViewHOlder> implements AdapterView.OnItemSelectedListener {
    static ArrayList<SkillsData> list;
    Dialog dialog;
    Context mContext;
    SkillsData modelRecylerview;
    SkillsDao skillDBHandler;
    boolean updateable = false;
    String selected_lvl;
    String[] skillsLevel = {"Beginner", "Intermediate", "Expert"};

    private final Executor executor = Executors.newSingleThreadExecutor();

    public SkillAdapter(Context context, ArrayList<SkillsData> arrayList , SkillsDao skillDBHandler) {
        this.mContext = context;
        list = arrayList;
        this.skillDBHandler = skillDBHandler;
    }

    @Override
    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.skillitems, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        this.modelRecylerview = list.get(i);
        myExperenceViewHOlder.skill_name.setText(this.modelRecylerview.getSkillName());
        myExperenceViewHOlder.skill_level.setText(this.modelRecylerview.getSkillLevel());

        myExperenceViewHOlder.delete.setOnClickListener(view -> deleteDialogForSkill(i));

        myExperenceViewHOlder.update.setOnClickListener(view -> updateDialog(i));
    }

    private void deleteDialogForSkill(int i) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.deletdialog);

        dialog.findViewById(R.id.lay_ok).setOnClickListener(v -> dialog.dismiss());

        dialog.findViewById(R.id.ly_cancel).setOnClickListener(view -> {
            executor.execute(() -> {
                // Get the correct item from the list at index i
                SkillsData itemToDelete = list.get(i);

                skillDBHandler.delete(itemToDelete);

                new Handler(Looper.getMainLooper()).post(() -> {
                    if (i < list.size()) {
                        SkillAdapter.list.remove(i);  // Remove the item safely
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(i, list.size());
                        Toast.makeText(mContext, "Skill Deleted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            });
        });

        dialog.show();
    }

    private void updateDialog(int i) {
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.dialog_update_sill);

        EditText skillName = dialog.findViewById(R.id.skills_name);
        Spinner skillLevelSpinner = dialog.findViewById(R.id.spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, skillsLevel);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillLevelSpinner.setAdapter(arrayAdapter);

        switch (list.get(i).getSkillLevel()) {
            case "Beginner":
                skillLevelSpinner.setSelection(0);
                break;
            case "Intermediate":
                skillLevelSpinner.setSelection(1);
                break;
            case "Expert":
                skillLevelSpinner.setSelection(2);
                break;
        }

        skillName.setText(list.get(i).getSkillName());
        selected_lvl = list.get(i).getSkillLevel();

        dialog.findViewById(R.id.lay_cencel).setOnClickListener(v -> dialog.dismiss());

        dialog.findViewById(R.id.ly_ok).setOnClickListener(view -> {
            String updatedSkillName = skillName.getText().toString().trim();
            String updatedSkillLevel = skillLevelSpinner.getSelectedItem().toString();

            executor.execute(() -> {
                if (!updatedSkillName.isEmpty()) {
                    SkillsData skillsData = new SkillsData(updatedSkillName, updatedSkillLevel);
                    skillsData.setId(modelRecylerview.getId());

                    skillDBHandler.update(skillsData);
                    updateable = true;

                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (i < list.size()) {
                            SkillAdapter.list.set(i, new SkillsData(updatedSkillName, updatedSkillLevel));
                            notifyItemChanged(i);
                        }
                        dialog.dismiss();
                    });
                } else {
                    updateable = false;
                    new Handler(Looper.getMainLooper()).post(() ->
                            Toast.makeText(mContext, "Field can't be empty", Toast.LENGTH_SHORT).show()
                    );
                }
            });
        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_lvl = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // No action needed
    }

    public class MyExperenceViewHOlder extends RecyclerView.ViewHolder {
        TextView skill_name;
        TextView skill_level;
        ImageView delete;
        ImageView update;

        public MyExperenceViewHOlder(View view) {
            super(view);
            skill_name = view.findViewById(R.id.skill_name);
            skill_level = view.findViewById(R.id.skill_level);
            delete = view.findViewById(R.id.delet_image_id);
            update = view.findViewById(R.id.edt_image_id);
        }
    }
}
