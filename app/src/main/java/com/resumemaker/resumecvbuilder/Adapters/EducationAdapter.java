package com.resumemaker.resumecvbuilder.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.DB.SkillsRoom.EducationRoom.EducationDao;
import com.resumemaker.resumecvbuilder.DataModels.EducationData;
import com.resumemaker.resumecvbuilder.R;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyExperenceViewHOlder> {
    static ArrayList<EducationData> list;
    Dialog dialog;
    EducationDao educationDBHandler;
    Context mContext;

    private final Executor executor = Executors.newSingleThreadExecutor();

    public EducationAdapter(Context context, ArrayList<EducationData> arrayList , EducationDao educationDao) {
        this.mContext = context;
        list = arrayList;
        this.educationDBHandler = educationDao;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.educationitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        final EducationData educationRecylerviewModel = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Education");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_organitation.setText(educationRecylerviewModel.getInstituteName());
        myExperenceViewHOlder.txt_degreetitle.setText(educationRecylerviewModel.getDegree());
        myExperenceViewHOlder.txt_scores.setText(educationRecylerviewModel.getDegreeDes());
        myExperenceViewHOlder.txtstart.setText(educationRecylerviewModel.getStart_date());
        myExperenceViewHOlder.txtCompletion.setText(educationRecylerviewModel.getCompleteDate());
        Log.d("listsize", "listsize" + list.size());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Initialize and configure the dialog
                EducationAdapter.this.dialog = new Dialog(EducationAdapter.this.mContext);
                EducationAdapter.this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                EducationAdapter.this.dialog.setCancelable(false);
                EducationAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EducationAdapter.this.dialog.setContentView(R.layout.updatedialog_edu);


                // OK button dismisses the dialog
                EducationAdapter.this.dialog.findViewById(R.id.lay_ok).setOnClickListener(v -> EducationAdapter.this.dialog.dismiss());

                // Cancel button triggers delete action
                EducationAdapter.this.dialog.findViewById(R.id.ly_cancel).setOnClickListener(view1 -> {
                    executor.execute(() -> {
                        EducationData itemToDelete = list.get(i);
                        EducationAdapter.this.educationDBHandler.delete(itemToDelete);

                        // Update UI on main thread after deletion
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (i < list.size()) {
                                list.remove(i);  // Remove the item safely
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, list.size());
                                Toast.makeText(mContext, "Education Field Deleted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    });
                });

                // Show the dialog
                EducationAdapter.this.dialog.show();
            }
        });

        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                // Initialize and configure dialog
                EducationAdapter.this.dialog = new Dialog(EducationAdapter.this.mContext);
                EducationAdapter.this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                EducationAdapter.this.dialog.setCancelable(false);
                EducationAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EducationAdapter.this.dialog.setContentView(R.layout.dialog_update_eduction);

                // Get and set dialog components
                TextView textView = EducationAdapter.this.dialog.findViewById(R.id.id_ok);
                final EditText editText = EducationAdapter.this.dialog.findViewById(R.id.edu_edt_universty);
                final EditText editText2 = EducationAdapter.this.dialog.findViewById(R.id.edu_edt_degree);
                final EditText editText3 = EducationAdapter.this.dialog.findViewById(R.id.edu_edt_score);
                final EditText editText4 = EducationAdapter.this.dialog.findViewById(R.id.edu_edt_compdate);
                final EditText editText5 = EducationAdapter.this.dialog.findViewById(R.id.edu_edt_startdate);

                // Set initial values
                editText.setText(list.get(i).getInstituteName());
                editText2.setText(list.get(i).getDegree());
                editText3.setText(list.get(i).getDegreeDes());
                editText4.setText(list.get(i).getCompleteDate());
                editText5.setText(list.get(i).getStart_date());

                // Handle 'OK' button click
                EducationAdapter.this.dialog.findViewById(R.id.lay_ok).setOnClickListener(v -> EducationAdapter.this.dialog.dismiss());

                // Handle 'Cancel' button click and update data
                EducationAdapter.this.dialog.findViewById(R.id.ly_cancel).setOnClickListener(view -> {
                    Toast.makeText(EducationAdapter.this.mContext, "Updating Your Data", Toast.LENGTH_SHORT).show();
                    String obj = editText.getText().toString();
                    String obj2 = editText2.getText().toString();
                    String obj3 = editText3.getText().toString();
                    String obj4 = editText4.getText().toString();
                    String obj5 = editText5.getText().toString();

                    executor.execute(() -> {
                        // Create and update data
                        EducationData data = new EducationData(obj4, obj5, obj2, obj, obj3);
                        data.setId(educationRecylerviewModel.getId());
                        EducationAdapter.this.educationDBHandler.update(data);

                        // Post UI update on the main thread
                        new Handler(Looper.getMainLooper()).post(() -> {
                            EducationAdapter.list.set(i, new EducationData(obj4, obj5, obj2, obj, obj3));
                            notifyItemChanged(i);
                            EducationAdapter.this.dialog.dismiss();
                        });
                    });
                });

                // Show dialog
                EducationAdapter.this.dialog.show();
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
        TextView txtCompletion;
        TextView txtstart;
        TextView txt_degreetitle;
        TextView txt_jobDescription;
        TextView txt_organitation;
        TextView txt_scores;

        public MyExperenceViewHOlder(View view) {
            super(view);
            this.tv_title = (TextView) view.findViewById(R.id.exp_tv_title);
            this.txt_organitation = (TextView) view.findViewById(R.id.tv_organization_id);
            this.txt_degreetitle = (TextView) view.findViewById(R.id.tv_degree_id);
            TextView textView = (TextView) view.findViewById(R.id.tv_score);
            this.txt_scores = textView;
            textView.setMovementMethod(new ScrollingMovementMethod());
            this.txtCompletion = (TextView) view.findViewById(R.id.tv_completiondate);
            this.txtstart = (TextView) view.findViewById(R.id.tv_start_date);
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
