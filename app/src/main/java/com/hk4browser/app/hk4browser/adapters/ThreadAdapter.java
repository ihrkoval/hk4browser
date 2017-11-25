package com.hk4browser.app.hk4browser.adapters;

import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.http.GetData;
import com.hk4browser.app.hk4browser.http.Http;
import com.hk4browser.app.hk4browser.http.IHttp;
import com.hk4browser.app.hk4browser.pojo.FilePojo;
import com.hk4browser.app.hk4browser.pojo.ThreadPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anton on 28.10.2017.
 */

public class ThreadAdapter<T> extends RecyclerView.Adapter<ThreadAdapter.ViewHolder> {
     JSONArray threads;
     IHttp http;
     FragmentManager fragmentManager;
     String boardId;
     String postFrom;

    public ThreadAdapter(JSONObject threads, IHttp http, FragmentManager fragmentManager, String postFrom){
        this.http = http;
        this.fragmentManager = fragmentManager;
        this.postFrom = postFrom;
        try {
            this.boardId = threads.getString("Board");
            this.threads = new JSONArray(threads.getString("threads"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("threads","Threads in bindAdapter consreuctor "+threads);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bind, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = position;
        Log.i("start","start onBindViewHolder in BindAdapter class");

        try {
            JSONObject jsonObject = (JSONObject) threads.get(position);
            final ThreadPojo threadPojo = new Gson().fromJson(jsonObject.toString(), ThreadPojo.class);
            Log.i("json", "Bind adapter try to get object "+ jsonObject);
            Log.i("thread","thread comment is "+jsonObject.getString("comment").length());
           // holder.itemtext.setText(threadPojo.getComment());//jsonObject.getString("comment"));
            if (threadPojo.getClosed().equals("1")){
                // where myresource (without the extension) is the file
                holder.threadClosedIcon.setImageResource(android.R.drawable.ic_secure); //"@android:drawable/ic_secure"
            }
            holder.threadNum.setText(threadPojo.getNum());
            holder.name.setText(threadPojo.getName());
            holder.postcount.setText("posts:"+threadPojo.getPosts_count());
            holder.filescount.setText("files:"+threadPojo.getFiles_count());
            holder.email.setText(threadPojo.getEmail());
            holder.date.setText(threadPojo.getDate());
            holder.trip.setText(threadPojo.getTrip());
            holder.subject.setText(Html.fromHtml(threadPojo.getSubject()));
            holder.coment.setText(Html.fromHtml(threadPojo.getComment()));
            holder.coment.setClickable(true);
            holder.coment.setMovementMethod (LinkMovementMethod.getInstance());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(linearLayoutManager);
            holder.recyclerView.setHasFixedSize(true);
            FilesListAdapter filesListAdapter = new FilesListAdapter(new ArrayList<FilePojo>(threadPojo.getFiles()));
            holder.recyclerView.setAdapter(filesListAdapter);
            holder.threadItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("thread","http in Adapter onclick thread"+http);
                    GetData getDataThread = new GetData(new Http(), v.getContext());
                    getDataThread.loadThread(fragmentManager, boardId, threadPojo.getNum(), postFrom);
                    Log.i("thread","Calling loadThread in thread adapter onclick "+ fragmentManager+","+boardId+","+ threadPojo.getNum()+","+postFrom);
                }
            });
            /*LinearLayout onThreadclick = container.findViewById(R.id.thread_item_LinerLayout);
            onThreadclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetData getDataThread = new GetData(http, v.getContext());
                    getDataThread.loadThread(getFragmentManager(), selectedId, threadPojo.getNum(), postFrom);
                }
            });*/

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    @Override
    public int getItemCount() {
        Log.i("threads","threads string in BindAdapter getItemCount size "+threads.length());
        return threads.length();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
       // public final TextView itemtext;
        public final TextView threadNum;
        public final TextView name;
        public final TextView postcount;
        public final TextView filescount;
        public final TextView email;
        public final TextView date;
        public final TextView trip;
        public final TextView subject;
        public final TextView coment;
        public final RecyclerView recyclerView;
        public final ImageView threadClosedIcon;
        public final LinearLayout threadItem;
    ViewHolder(final View view) {
        super(view);
        recyclerView =  view.findViewById(R.id.files_recycleview);
        threadClosedIcon = view.findViewById(R.id.ThreadClosedImageView);
        //itemtext = view.findViewById(R.id.textView_item_bind);
        threadNum = view.findViewById(R.id.threadNumTextView);
        name = view.findViewById(R.id.name_TexrView);
        postcount = view.findViewById(R.id.postCountTextView);
       filescount = view.findViewById(R.id.filesCount_TextView);
       email = view.findViewById(R.id.email_TextView);
       date = view.findViewById(R.id.date_TextView);
       trip = view.findViewById(R.id.trip_TextView);
       subject = view.findViewById(R.id.subject_TextView);
       coment = view.findViewById(R.id.coment_TextView);
       threadItem = view.findViewById(R.id.thread_item_LinerLayout);


    }
    }}
