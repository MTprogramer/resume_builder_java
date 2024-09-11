package com.resumemaker.resumecvbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.resumemaker.resumecvbuilder.ads.AdsCommon;

import java.io.File;
import java.util.ArrayList;

public class EditCvAdapter extends RecyclerView.Adapter<EditCvAdapter.ViewHolder> {
    static Context context;
    private final String MY_PDF = "1.jpg";
    ArrayList<File> arrylist;
    File path;

    public void removeLastItem() {
        ArrayList<File> arrayList = this.arrylist;
        arrayList.remove(arrayList.size() - 1);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView deletecv;
        ImageView eDitcv;
        TextView tv_cvname;
        ImageView viewCv;
        ImageView viewcv;

        public ViewHolder(View view) {
            super(view);
            this.eDitcv = (ImageView) view.findViewById(R.id.share_pdf);
            this.viewcv = (ImageView) view.findViewById(R.id.viewpdf);
            this.deletecv = (ImageView) view.findViewById(R.id.id_deletpdf);
            this.tv_cvname = (TextView) view.findViewById(R.id.cvnameshow);
        }
    }

    public EditCvAdapter(Context context2, ArrayList<File> arrayList) {
        context = context2;
        this.arrylist = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.edititems, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tv_cvname.setText(this.arrylist.get(i).getName());
        viewHolder.eDitcv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        viewHolder.viewcv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContextWrapper contextWrapper = new ContextWrapper(EditCvAdapter.context);
                EditCvAdapter.this.path = contextWrapper.getDir("Created CV", 0);
                Log.d("mypath", "pppp" + EditCvAdapter.this.path);
                Intent intent = new Intent(EditCvAdapter.context, CvViewer.class);
                intent.putExtra("cvpath", EditCvAdapter.this.arrylist.get(i).getAbsolutePath());
                //EditCvAdapter.context.startActivity(intent);

                AdsCommon.InterstitialAd((Activity) context, intent);

            }
        });
        viewHolder.deletecv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditCvAdapter.this.arrylist.get(i).getAbsoluteFile().delete();
                EditCvAdapter.this.arrylist.remove(viewHolder.getAdapterPosition());
                EditCvAdapter.this.notifyItemRemoved(i);
                EditCvAdapter editCvAdapter = EditCvAdapter.this;
                editCvAdapter.notifyItemRangeChanged(i, editCvAdapter.arrylist.size());
            }
        });
    }

    public int getItemCount() {
        return this.arrylist.size();
    }
}
