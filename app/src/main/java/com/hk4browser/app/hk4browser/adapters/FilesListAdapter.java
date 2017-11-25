package com.hk4browser.app.hk4browser.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.pojo.FilePojo;
import com.hk4browser.app.hk4browser.pojo.ThreadPojo;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anton on 19.11.2017.
 */

public class FilesListAdapter<T> extends RecyclerView.Adapter<FilesListAdapter.ViewHolder> {
    ArrayList<FilePojo> files;
    String url = "https://2ch.hk";

    public FilesListAdapter(ArrayList<FilePojo> files){
       this.files = files;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fileitem_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        FilePojo filePojo = files.get(position);
        holder.fileTexte.setText(filePojo.getSize()+" kb");

        Picasso.with(holder.itemView.getContext()).load(url+filePojo.getThumbnail()).into(holder.fileThumb);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // public final TextView itemtext;
        public final ImageView fileThumb;
        public final TextView fileTexte;

        ViewHolder(final View view) {
            super(view);
            fileThumb = view.findViewById(R.id.thumbFileItem_imageView);
            //itemtext = view.findViewById(R.id.textView_item_bind);
            fileTexte = view.findViewById(R.id.file_textView);

        }

    }
}