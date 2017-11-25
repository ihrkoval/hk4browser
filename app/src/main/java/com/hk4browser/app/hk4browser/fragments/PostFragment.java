package com.hk4browser.app.hk4browser.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.adapters.PostAdapter;
import com.hk4browser.app.hk4browser.adapters.ThreadAdapter;
import com.hk4browser.app.hk4browser.http.IHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by anton on 20.11.2017.
 */

public class PostFragment extends BaseFragment {
//todo threads D not loaded threads string in BindAdapter getItemCount size 0
    @Inject
    public IHttp http;

    private RecyclerView recyclerView;
    JSONArray postJson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.post_fragment, container, false);
        Log.i("rootView","rootView in PostFragment is "+rootView.getContext());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.posts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);
        //http = new Http();
        Log.i("thread","http is "+http);
        // GetData getData = new GetData(http, rootView.getContext());

        try {
            Log.i("bundle"," String from bundle is "+getArguments().getString("posts"));
            Log.i("args", "PoardFragment class getArguments().getString(\"boardId\") lenhth is "+getArguments().getString("posts").length());
           postJson  =  new JSONArray(getArguments().getString("posts"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        PostAdapter adapter = new PostAdapter(postJson);
        recyclerView.setAdapter(adapter);

        Log.i("jsonBoard", "BoardFragment class onCreateView set boardJson in adapter  "+postJson.length());
        return rootView;
    }

}
