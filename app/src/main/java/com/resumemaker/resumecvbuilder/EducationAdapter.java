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

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyExperenceViewHOlder> {
    static ArrayList<EducationRecylerviewModel> list;
    Dialog dialog;
    EducationDBHandler educationDBHandler;
    Context mContext;

    public EducationAdapter(Context context, ArrayList<EducationRecylerviewModel> arrayList) {
        this.mContext = context;
        list = arrayList;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.educationitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        final EducationRecylerviewModel educationRecylerviewModel = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Education");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_organitation.setText(educationRecylerviewModel.getOrganizationname());
        myExperenceViewHOlder.txt_degreetitle.setText(educationRecylerviewModel.getDegreetitle());
        myExperenceViewHOlder.txt_scores.setText(educationRecylerviewModel.getScore());
        myExperenceViewHOlder.txtCompletion.setText(educationRecylerviewModel.getCompletiondate());
        Log.d("listsize", "listsize" + list.size());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EducationAdapter.this.dialog = new Dialog(EducationAdapter.this.mContext);
                EducationAdapter.this.dialog.requestWindowFeature(1);
                EducationAdapter.this.dialog.setCancelable(false);
                EducationAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                EducationAdapter.this.dialog.setContentView(R.layout.updatedialog_edu);
                TextView textView = (TextView) EducationAdapter.this.dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) EducationAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) EducationAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EducationAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) EducationAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        EducationAdapter.this.educationDBHandler = new EducationDBHandler(view.getContext());
                        EducationAdapter.this.educationDBHandler.deleteCourse(myExperenceViewHOlder.txt_organitation.getText().toString());
                        EducationAdapter.list.remove(i);
                        EducationAdapter.this.notifyDataSetChanged();
                        Toast.makeText(EducationAdapter.this.mContext, "Education Field Deleted", 0).show();
                        EducationAdapter.this.dialog.dismiss();
                    }
                });
                EducationAdapter.this.dialog.show();
            }
        });
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                EducationAdapter.this.dialog = new Dialog(EducationAdapter.this.mContext);
                EducationAdapter.this.dialog.requestWindowFeature(1);
                EducationAdapter.this.dialog.setCancelable(false);
                EducationAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                EducationAdapter.this.dialog.setContentView(R.layout.dialog_update_eduction);
                TextView textView = (TextView) EducationAdapter.this.dialog.findViewById(R.id.id_ok);
                final EditText editText = (EditText) EducationAdapter.this.dialog.findViewById(R.id.edu_edt_universty);
                final EditText editText2 = (EditText) EducationAdapter.this.dialog.findViewById(R.id.edu_edt_degree);
                final EditText editText3 = (EditText) EducationAdapter.this.dialog.findViewById(R.id.edu_edt_score);
                final EditText editText4 = (EditText) EducationAdapter.this.dialog.findViewById(R.id.edu_edt_compdate);
                editText.setText(educationRecylerviewModel.getOrganizationname());
                editText2.setText(educationRecylerviewModel.getDegreetitle());
                editText3.setText(educationRecylerviewModel.getScore());
                editText4.setText(educationRecylerviewModel.getCompletiondate());
                TextView textView2 = (TextView) EducationAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) EducationAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EducationAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) EducationAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(EducationAdapter.this.mContext, "Updating Your Data", 0).show();
                        EducationAdapter.this.educationDBHandler = new EducationDBHandler(view.getContext());
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
                        String obj3 = editText3.getText().toString();
                        String obj4 = editText4.getText().toString();
                        EducationAdapter.this.educationDBHandler.updateCourse(myExperenceViewHOlder.txt_organitation.getText().toString(), obj, obj2, obj3, obj4);
                        EducationAdapter.list.set(i, new EducationRecylerviewModel(obj, obj2, obj3, obj4));
                        EducationAdapter.this.notifyDataSetChanged();
                        EducationAdapter.this.dialog.dismiss();
                    }
                });
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
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
