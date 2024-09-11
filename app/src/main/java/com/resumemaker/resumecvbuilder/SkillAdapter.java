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

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.MyExperenceViewHOlder> {
    static ArrayList<SkillRecylerviewModel> list;
    Dialog dialog;
    Context mContext;
    SkillRecylerviewModel modelRecylerview;
    SkillDBHandler skillDBHandler;

    public SkillAdapter(Context context, ArrayList<SkillRecylerviewModel> arrayList) {
        this.mContext = context;
        list = arrayList;
    }

    public MyExperenceViewHOlder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyExperenceViewHOlder(LayoutInflater.from(this.mContext).inflate(R.layout.skillitems, viewGroup, false));
    }

    public void onBindViewHolder(final MyExperenceViewHOlder myExperenceViewHOlder, final int i) {
        this.modelRecylerview = list.get(i);
        myExperenceViewHOlder.tv_title.setText("Skills");
        myExperenceViewHOlder.iv_update.setImageResource(R.drawable.edit);
        myExperenceViewHOlder.iv_delete.setImageResource(R.drawable.delete);
        myExperenceViewHOlder.txt_skillone.setText(this.modelRecylerview.getSkillOne());
        myExperenceViewHOlder.txt_skilltwo.setText(this.modelRecylerview.getSkillTwo());
        myExperenceViewHOlder.txt_skillthree.setText(this.modelRecylerview.getSkillThree());
        myExperenceViewHOlder.txt_skillfourth.setText(this.modelRecylerview.getSkillFourth());
        Log.d("listsize", "listsize" + list.size());
        myExperenceViewHOlder.iv_delete.setOnClickListener(new View.OnClickListener() {
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
                        SkillAdapter.this.skillDBHandler = new SkillDBHandler(view.getContext());
                        SkillAdapter.this.skillDBHandler.deleteCourse(myExperenceViewHOlder.txt_skillone.getText().toString());
                        SkillAdapter.list.remove(i);
                        SkillAdapter.this.notifyDataSetChanged();
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                SkillAdapter.this.dialog.show();
            }
        });
        myExperenceViewHOlder.iv_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateDialog();
            }

            private void updateDialog() {
                SkillAdapter.this.dialog = new Dialog(SkillAdapter.this.mContext);
                SkillAdapter.this.dialog.requestWindowFeature(1);
                SkillAdapter.this.dialog.setCancelable(false);
                SkillAdapter.this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                SkillAdapter.this.dialog.setContentView(R.layout.dialog_update_sill);
                TextView textView = (TextView) SkillAdapter.this.dialog.findViewById(R.id.id_ok);
                final EditText editText = (EditText) SkillAdapter.this.dialog.findViewById(R.id.skill_edt_org);
                final EditText editText2 = (EditText) SkillAdapter.this.dialog.findViewById(R.id.skill_edt_design);
                final EditText editText3 = (EditText) SkillAdapter.this.dialog.findViewById(R.id.skill_edt_jdate);
                final EditText editText4 = (EditText) SkillAdapter.this.dialog.findViewById(R.id.skil_edt_endate);
                editText.setText(SkillAdapter.this.modelRecylerview.getSkillOne());
                editText2.setText(SkillAdapter.this.modelRecylerview.getSkillTwo());
                editText3.setText(SkillAdapter.this.modelRecylerview.getSkillThree());
                editText4.setText(SkillAdapter.this.modelRecylerview.getSkillFourth());
                TextView textView2 = (TextView) SkillAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) SkillAdapter.this.dialog.findViewById(R.id.lay_ok)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                ((LinearLayout) SkillAdapter.this.dialog.findViewById(R.id.ly_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(SkillAdapter.this.mContext, "Updating Your Data", 0).show();
                        SkillAdapter.this.skillDBHandler = new SkillDBHandler(view.getContext());
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
                        String obj3 = editText3.getText().toString();
                        String obj4 = editText4.getText().toString();
                        SkillAdapter.this.skillDBHandler.updateCourse(myExperenceViewHOlder.txt_skillone.getText().toString(), obj, obj2, obj3, obj4);
                        SkillAdapter.list.set(i, new SkillRecylerviewModel(obj, obj2, obj3, obj4, "skill pehla level", "skill two level", "skill three level", "skill fourth level"));
                        SkillAdapter.this.notifyItemChanged(i);
                        SkillAdapter.this.notifyDataSetChanged();
                        SkillAdapter.this.dialog.dismiss();
                    }
                });
                SkillAdapter.this.dialog.show();
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
            this.txt_skillone = (TextView) view.findViewById(R.id.id_skillone);
            this.txt_skilltwo = (TextView) view.findViewById(R.id.id_skilltwo);
            this.txt_skillthree = (TextView) view.findViewById(R.id.id_skillthree);
            this.txt_skillfourth = (TextView) view.findViewById(R.id.id_skillfourth);
            this.iv_update = (ImageView) view.findViewById(R.id.edt_image_id);
            this.iv_delete = (ImageView) view.findViewById(R.id.delet_image_id);
        }
    }
}
