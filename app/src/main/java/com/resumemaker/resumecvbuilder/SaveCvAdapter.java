package com.resumemaker.resumecvbuilder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.ads.AdsCommon;

import java.io.File;
import java.util.ArrayList;

public class SaveCvAdapter extends RecyclerView.Adapter<SaveCvAdapter.ViewHolder> {
    static Context context;
    private final String MY_PDF = "1.jpg";
    ArrayList<File> arrylist;
    Dialog dialog;
    File path;
    SaveCVCalback saveCVCalback;

    public void removeLastItem() {
        ArrayList<File> arrayList = this.arrylist;
        arrayList.remove(arrayList.size() - 1);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView deletecv;
        ImageView sharecv;
        TextView tv_cvname;
        ImageView viewCv;
        ImageView viewcv;

        public ViewHolder(View view) {
            super(view);
            this.sharecv = (ImageView) view.findViewById(R.id.share_pdf);
            this.viewcv = (ImageView) view.findViewById(R.id.viewpdf);
            this.deletecv = (ImageView) view.findViewById(R.id.id_deletpdf);
            this.tv_cvname = (TextView) view.findViewById(R.id.cvnameshow);
        }
    }

    public SaveCvAdapter(Context context2, ArrayList<File> arrayList) {
        context = context2;
        this.arrylist = arrayList;
    }

    public void setOnItemClickListener(SaveCVCalback saveCVCalback2) {
        this.saveCVCalback = saveCVCalback2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.saveitems, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tv_cvname.setText(this.arrylist.get(i).getName());
        viewHolder.sharecv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                Context context = SaveCvAdapter.context;
                intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(context, SaveCvAdapter.context.getApplicationContext().getPackageName() + ".provider", new File(SaveCvAdapter.this.arrylist.get(i).getAbsolutePath())));
                intent.setType("image/*");
                SaveCvAdapter.context.startActivity(Intent.createChooser(intent, "Select"));
            }
        });
        viewHolder.viewcv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("mypath", "pppp" + (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/PdfCv/"));
                Intent intent = new Intent(SaveCvAdapter.context, CvViewer.class);
                intent.putExtra("cvpath", SaveCvAdapter.this.arrylist.get(i).getAbsolutePath());
                //SaveCvAdapter.context.startActivity(intent);

                AdsCommon.InterstitialAd((Activity) context, intent);

            }
        });
        viewHolder.deletecv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                deleteDialog();
            }

            private void deleteDialog() {
                SaveCvAdapter.this.dialog = new Dialog(SaveCvAdapter.context);
                SaveCvAdapter.this.dialog.requestWindowFeature(1);
                SaveCvAdapter.this.dialog.setCancelable(false);
                SaveCvAdapter.this.dialog.getWindow().setBackgroundDrawableResource(17170445);
                SaveCvAdapter.this.dialog.setContentView(R.layout.savedeletedilog);
                TextView textView = (TextView) SaveCvAdapter.this.dialog.findViewById(R.id.id_ok);
                TextView textView2 = (TextView) SaveCvAdapter.this.dialog.findViewById(R.id.id_cancel);
                ((LinearLayout) SaveCvAdapter.this.dialog.findViewById(R.id.ly_yesid)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        deletecvtemplet();
                        SaveCvAdapter.this.dialog.dismiss();
                    }

                    private void deletecvtemplet() {
                        SaveCvAdapter.this.arrylist.get(i).getAbsoluteFile().delete();
                        SaveCvAdapter.this.arrylist.remove(viewHolder.getAdapterPosition());
                        SaveCvAdapter.this.notifyItemRemoved(i);
                        SaveCvAdapter.this.notifyItemRangeChanged(i, SaveCvAdapter.this.arrylist.size());
                        SaveCvAdapter.this.saveCVCalback.sendSaveList(SaveCvAdapter.this.arrylist);
                    }
                });
                ((LinearLayout) SaveCvAdapter.this.dialog.findViewById(R.id.lay_no)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SaveCvAdapter.this.dialog.dismiss();
                    }
                });
                SaveCvAdapter.this.dialog.show();
            }
        });
    }

    public int getItemCount() {
        return this.arrylist.size();
    }
}
