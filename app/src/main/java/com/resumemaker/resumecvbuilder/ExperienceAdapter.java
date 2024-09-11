package com.resumemaker.resumecvbuilder;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
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

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyExperenceViewHOlder> {
    static ArrayList<ExperienceRecylerviewModel> list;
    Dialog dialog;
    ExperienceDBHandler experienceDBHandler;
    Context mContext;

    public ExperienceAdapter(Context context, ArrayList<ExperienceRecylerviewModel> arrayList) {
        this.mContext = context;
        list = arrayList;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.experienceitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        final ExperienceRecylerviewModel experienceRecylerviewModel = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Experience");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_organitation.setText(experienceRecylerviewModel.getOrganizationname());
        myExperenceViewHOlder.txt_designation.setText(experienceRecylerviewModel.getDesignations());
        myExperenceViewHOlder.txt_joinDate.setText(experienceRecylerviewModel.getJoiningDate());
        myExperenceViewHOlder.txtEndingDate.setText(experienceRecylerviewModel.getEndingDate());
        myExperenceViewHOlder.txt_jobDescription.setText(experienceRecylerviewModel.getJobDescriptions());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deleteDialog();
            }

            private void deleteDialog() {
                ExperienceAdapter.this.dialog = new Dialog(ExperienceAdapter.this.mContext);
                ExperienceAdapter.this.dialog.requestWindowFeature(1);
                ExperienceAdapter.this.dialog.setCancelable(false);
                ExperienceAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ExperienceAdapter.this.dialog.setContentView(R.layout.deletdialog);
                TextView textView = (TextView) ExperienceAdapter.this.dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) ExperienceAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) ExperienceAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ExperienceAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) ExperienceAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ExperienceAdapter.this.experienceDBHandler = new ExperienceDBHandler(view.getContext());
                        ExperienceAdapter.this.experienceDBHandler.deleteCourse(myExperenceViewHOlder.txt_organitation.getText().toString());
                        ExperienceAdapter.list.remove(i);
                        ExperienceAdapter.this.notifyDataSetChanged();
                        ExperienceAdapter.this.dialog.dismiss();
                    }
                });
                ExperienceAdapter.this.dialog.show();
            }
        });
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                ExperienceAdapter.this.dialog = new Dialog(ExperienceAdapter.this.mContext);
                ExperienceAdapter.this.dialog.requestWindowFeature(1);
                ExperienceAdapter.this.dialog.setCancelable(false);
                ExperienceAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ExperienceAdapter.this.dialog.setContentView(R.layout.dialog_update);
                TextView textView = (TextView) ExperienceAdapter.this.dialog.findViewById(R.id.id_ok);
                final EditText editText = (EditText) ExperienceAdapter.this.dialog.findViewById(R.id.experence_edt_org);
                final EditText editText2 = (EditText) ExperienceAdapter.this.dialog.findViewById(R.id.expe_edt_design);
                final EditText editText3 = (EditText) ExperienceAdapter.this.dialog.findViewById(R.id.experence_edt_jdate);
                final EditText editText4 = (EditText) ExperienceAdapter.this.dialog.findViewById(R.id.experence_edt_endate);
                final EditText editText5 = (EditText) ExperienceAdapter.this.dialog.findViewById(R.id.experence_edt_jobdescrp);
                editText.setText(experienceRecylerviewModel.getOrganizationname());
                editText2.setText(experienceRecylerviewModel.getDesignations());
                editText3.setText(experienceRecylerviewModel.getJoiningDate());
                editText4.setText(experienceRecylerviewModel.getEndingDate());
                editText5.setText(experienceRecylerviewModel.getJobDescriptions());
                TextView textView2 = (TextView) ExperienceAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) ExperienceAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ExperienceAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) ExperienceAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(ExperienceAdapter.this.mContext, "updating your data", 0).show();
                        ExperienceAdapter.this.experienceDBHandler = new ExperienceDBHandler(view.getContext());
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
                        String obj3 = editText3.getText().toString();
                        String obj4 = editText4.getText().toString();
                        String obj5 = editText5.getText().toString();
                        ExperienceAdapter.this.experienceDBHandler.updateCourse(myExperenceViewHOlder.txt_organitation.getText().toString(), obj, obj2, obj3, obj4, obj5);
                        ExperienceAdapter.list.set(i, new ExperienceRecylerviewModel(obj, obj2, obj3, obj4, obj5));
                        ExperienceAdapter.this.notifyDataSetChanged();
                        ExperienceAdapter.this.dialog.dismiss();
                    }
                });
                ExperienceAdapter.this.dialog.show();
            }

        });
        Log.d("listsize", "listsize" + list.size());
    }

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
            this.tv_title = (TextView) view.findViewById(R.id.exp_tv_title);
            this.txt_organitation = (TextView) view.findViewById(R.id.tv_organization);
            this.txt_designation = (TextView) view.findViewById(R.id.tv_designation_id);
            this.txt_joinDate = (TextView) view.findViewById(R.id.tv_joindate);
            this.txtEndingDate = (TextView) view.findViewById(R.id.tv_enddate);
            TextView textView = (TextView) view.findViewById(R.id.tv_description_id);
            this.txt_jobDescription = textView;
            textView.setMovementMethod(new ScrollingMovementMethod());
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
