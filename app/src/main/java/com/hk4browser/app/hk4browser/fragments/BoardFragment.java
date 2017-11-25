package com.hk4browser.app.hk4browser.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.adapters.ThreadAdapter;
import com.hk4browser.app.hk4browser.http.GetData;
import com.hk4browser.app.hk4browser.http.Http;
import com.hk4browser.app.hk4browser.http.IHttp;
import com.hk4browser.app.hk4browser.pojo.ThreadPojo;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by anton on 12.11.2017.
 */

public class BoardFragment extends BaseFragment {

    @Inject
    public IHttp http;

    private RecyclerView recyclerView;
    JSONObject boardJson;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.board_fragment, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.threads_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            recyclerView.setHasFixedSize(true);
            //http = new Http();
            Log.i("thread","http is "+http);
           // GetData getData = new GetData(http, rootView.getContext());

            try {
                Log.i("bundle"," String from bundle is "+getArguments().getString("boardId"));
                Log.i("args", "BoardFragment class getArguments().getString(\"boardId\") lenhth is "+getArguments().getString("boardId").length());
               boardJson =  new JSONObject(getArguments().getString("boardId"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
           ThreadAdapter adapter = new ThreadAdapter(boardJson, http, getFragmentManager(), "0");
            recyclerView.setAdapter(adapter);
            Log.i("jsonBoard", "BoardFragment class onCreateView set boardJson in adapter  "+boardJson);

            return rootView;
        }




}
