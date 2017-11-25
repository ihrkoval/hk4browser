package com.hk4browser.app.hk4browser.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.pojo.FilePojo;
import com.hk4browser.app.hk4browser.pojo.PostPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anton on 28.10.2017.
 */

public class PostAdapter<T> extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    JSONArray posts;

    public PostAdapter(JSONArray posts){

            this.posts = posts;
        Log.i("posts","Posts in PostAdapter consreuctor "+posts.length());
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.i("start","start onBindViewHolder in PostAdapter class");

        try {
            JSONObject jsonObject = (JSONObject) posts.get(position);
            PostPojo postPojo = new Gson().fromJson(jsonObject.toString(), PostPojo.class);
            Log.i("json", "Bind adapter try to get json Post  "+ jsonObject);
            Log.i("post","post comment is "+jsonObject.getString("comment"));
            // holder.itemtext.setText(threadPojo.getComment());//jsonObject.getString("comment"));
            if (postPojo.getClosed().equals("1")){
                // where myresource (without the extension) is the file
                holder.postClosedIcon.setImageResource(android.R.drawable.ic_secure); //"@android:drawable/ic_secure"
            }
            holder.postNum.setText("№"+postPojo.getNum());
            holder.name.setText(Html.fromHtml(postPojo.getName().replace("Аноним&nbsp;ID:","")));

            if (postPojo.getOp().equals("1")) {
                holder.isOp.setText("OP");
            }else{ holder.isOp.setText("");}

            if (!postPojo.getTrip_type().equals("normal")){
            holder.tripType.setText("t:"+postPojo.getTrip_type());}
            else {holder.tripType.setText("");}

            holder.emeailPost.setText(postPojo.getEmail());
            holder.datePost.setText(postPojo.getDate());
            holder.tripPost.setText(Html.fromHtml(postPojo.getTrip()));
            holder.subject_post.setText(Html.fromHtml(postPojo.getSubject()));
            holder.coment.setText(Html.fromHtml(postPojo.getComment()));
            holder.coment.setClickable(true);
            holder.coment.setMovementMethod(LinkMovementMethod.getInstance());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            holder.postRecyclerView.setLayoutManager(linearLayoutManager);
            holder.postRecyclerView.setHasFixedSize(true);
            FilesListAdapter filesListAdapter = new FilesListAdapter(new ArrayList<FilePojo>(postPojo.getFiles()));
            holder.postRecyclerView.setAdapter(filesListAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    @Override
    public int getItemCount() {
        Log.i("posts","posts string in POstsAdapter getItemCount size "+posts.length());
        return posts.length();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // public final TextView itemtext;
        public final TextView postNum;
        public final TextView name;
        public final TextView isOp;
        public final TextView tripType;
        public final TextView emeailPost;
        public final TextView datePost;
        public final TextView tripPost;
        public final TextView subject_post;
        public final TextView coment;
        public final RecyclerView postRecyclerView;
        public final ImageView postClosedIcon;
        ViewHolder(final View view) {
            super(view);
            postRecyclerView =  view.findViewById(R.id.filesPost_recycleview);
            postClosedIcon = view.findViewById(R.id.PostClosedImageView);
            //itemtext = view.findViewById(R.id.textView_item_bind);
            postNum = view.findViewById(R.id.post_num_textView);
            name = view.findViewById(R.id.postName_TexrView);
            isOp = view.findViewById(R.id.isOp_TextView);
            tripType = view.findViewById(R.id.tripType_TextView);
            emeailPost = view.findViewById(R.id.emailPost_TextView);
            datePost = view.findViewById(R.id.datePost_TextView);
            tripPost = view.findViewById(R.id.tripPost_TextView);
            subject_post = view.findViewById(R.id.subjectPost_TextView);
            coment = view.findViewById(R.id.commentPost_TextView);
        }

    }
}
