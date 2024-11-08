package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ExperienceRoom.ExperienceDao;
import com.resumemaker.resumecvbuilder.DB.SkillsRoom.ResumeDatabase;
import com.resumemaker.resumecvbuilder.DataModels.ExperienceData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyExperenceViewHOlder> {
    static ArrayList<ExperienceData> list;
    Dialog dialog;
    ExperienceDao experienceDBHandler;
    Context mContext;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public ExperienceAdapter(Context context, ArrayList<ExperienceData> arrayList, ExperienceDao experienceDao) {
        this.mContext = context;
        list = arrayList;
        this.experienceDBHandler = experienceDao;
    }

    @Override
    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.experienceitems, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        final ExperienceData experienceRecylerviewModel = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Experience");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_organitation.setText(experienceRecylerviewModel.getCompany_name());
        myExperenceViewHOlder.txt_designation.setText(experienceRecylerviewModel.getDesignation());
        myExperenceViewHOlder.txt_joinDate.setText(experienceRecylerviewModel.getJoin_date());
        myExperenceViewHOlder.txtEndingDate.setText(experienceRecylerviewModel.getEnd_date());
        myExperenceViewHOlder.txt_jobDescription.setText(experienceRecylerviewModel.getExperience_des());

        // Handle Delete Action
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deleteDialog(i); // Pass index to deleteDialog method
            }

            private void deleteDialog(final int index) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(1);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setContentView(R.layout.deletdialog);
                TextView textView = (TextView) dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) dialog.findViewById(R.id.id_cancel);

                // OK Button Click Listener (Dismiss dialog)
                ((LinearLayout) dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // Cancel Button Click Listener (Perform Deletion)
                ((LinearLayout) dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        executor.execute(() -> {
                            // Perform database deletion on background thread
                            if (experienceDBHandler == null) {
                                experienceDBHandler = ResumeDatabase.getInstance(mContext).experienceDao();
                            }
                            ExperienceData itemToDelete = list.get(i);
                            experienceDBHandler.delete(itemToDelete);

                            // Update UI on main thread after deletion
                            new Handler(Looper.getMainLooper()).post(() -> {
                                // Ensure index is valid before removing
                                if (index >= 0 && index < list.size()) {
                                    list.remove(index); // Modify the correct list
                                    notifyItemRemoved(index);
                                    notifyItemRangeChanged(index, list.size());
                                    Toast.makeText(mContext, "Experience Field Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("ExperienceAdapter", "Invalid index: " + index);
                                }
                                dialog.dismiss();
                            });
                        });
                    }
                });
                dialog.show();
            }
        });

        // Handle Update Action
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog(i); // Pass index to updateDialog method
            }

            private void updateDialog(final int index) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setContentView(R.layout.dialog_update);

                TextView textView = dialog.findViewById(R.id.id_ok);
                final EditText editText = dialog.findViewById(R.id.experence_edt_org);
                final EditText editText2 = dialog.findViewById(R.id.expe_edt_design);
                final EditText editText3 = dialog.findViewById(R.id.experence_edt_jdate);
                final EditText editText4 = dialog.findViewById(R.id.experence_edt_endate);
                final EditText editText5 = dialog.findViewById(R.id.experence_edt_jobdescrp);

                // Set initial values
                editText.setText(list.get(i).getCompany_name());
                editText2.setText(list.get(i).getDesignation());
                editText3.setText(list.get(i).getJoin_date());
                editText4.setText(list.get(i).getEnd_date());
                editText5.setText(list.get(i).getExperience_des());

                // OK Button Click Listener (Dismiss dialog)
                ((LinearLayout) dialog.findViewById(R.id.lay_ok)).setOnClickListener(v -> dialog.dismiss());

                // Cancel Button Click Listener (Perform Update)
                ((LinearLayout) dialog.findViewById(R.id.ly_cancel)).setOnClickListener(view -> {
                    new Handler(Looper.getMainLooper()).post(() ->
                            Toast.makeText(mContext, "Updating your data", Toast.LENGTH_SHORT).show()
                    );

                    // Capture input values from EditText fields
                    String obj = editText.getText().toString();
                    String obj2 = editText2.getText().toString();
                    String obj3 = editText3.getText().toString();
                    String obj4 = editText4.getText().toString();
                    String obj5 = editText5.getText().toString();

                    executor.execute(() -> {
                        // Create and update data
                        ExperienceData data = new ExperienceData(obj2, obj3, obj4, obj, obj5, false);
                        data.setId(experienceRecylerviewModel.getId());
                        experienceDBHandler.update(data);

                        // Update UI on the main thread after background update
                        new Handler(Looper.getMainLooper()).post(() -> {
                            // Ensure index is valid before updating
                            if (index >= 0 && index < list.size()) {
                                list.set(index, data); // Update the correct list
                                notifyItemChanged(index);
                                dialog.dismiss();
                            } else {
                                Log.e("ExperienceAdapter", "Invalid index: " + index);
                            }
                        });
                    });
                });

                dialog.show();
            }
        });

        Log.d("listsize", "listsize" + list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyExperenceViewHOlder extends RecyclerView.ViewHolder {
        ImageView iv_delete;
        ImageView iv_update;
        TextView tv_title;
        TextView txtEndingDate;
        TextView txt_designation;
        TextView txt_jobDescription;
        TextView txt_joinDate;
        TextView txt_organitation;

        public MyExperenceViewHOlder(View view) {
            super(view);
            this.tv_title = view.findViewById(R.id.exp_tv_title);
            this.txt_organitation = view.findViewById(R.id.tv_organization);
            this.txt_designation = view.findViewById(R.id.tv_designation_id);
            this.txt_joinDate = view.findViewById(R.id.tv_joindate);
            this.txtEndingDate = view.findViewById(R.id.tv_enddate);
            TextView textView = (TextView) view.findViewById(R.id.tv_description_id);
            this.txt_jobDescription = textView;
            textView.setMovementMethod(new ScrollingMovementMethod());
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
