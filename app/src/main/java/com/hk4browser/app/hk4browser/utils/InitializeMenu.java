package com.hk4browser.app.hk4browser.utils;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.hk4browser.app.hk4browser.adapters.MenuExpandableListAdapter;
import com.hk4browser.app.hk4browser.fragments.BoardFragment;
import com.hk4browser.app.hk4browser.http.GetData;
import com.hk4browser.app.hk4browser.http.Http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anton on 18.11.2017.
 */

public class InitializeMenu {

    private HashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;
    String jsonMenu;

    public InitializeMenu(String jsonMenu) {
        this.jsonMenu = jsonMenu;
        expandableListDetail = new JsonUtils().getExpandableListDetail(jsonMenu);;
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
    }

    public HashMap<String, List<String>> getExpandableListDetail(){
        return expandableListDetail;
    }

    public List<String> getExpandableListTitle(){return expandableListTitle;}


    public void initialize(final View view) {
        Log.i("childs", "size of a first child in MainActivity" + expandableListDetail.get(expandableListTitle.get(0)).size());
        ExpandableListView expandableListView_threads = (ExpandableListView) view;
        MenuExpandableListAdapter expandableListAdapter = new MenuExpandableListAdapter(view.getContext(), expandableListTitle, expandableListDetail);

        expandableListView_threads.setAdapter(expandableListAdapter);
        expandableListView_threads.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(view.getContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView_threads.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(view.getContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

      /*  expandableListView_threads.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String threadsResponseStr = jsonMenu;
                JSONObject boards = new JSONObject();
                JSONArray childs = new JSONArray();
                JSONObject selectedBoard = new JSONObject();
                String selectedId = new String();


                try {
                    boards = new JSONObject(threadsResponseStr);
                    childs = boards.getJSONArray(expandableListTitle.get(groupPosition));
                    selectedBoard = (JSONObject) childs.get(childPosition);
                    selectedId = (String) selectedBoard.get("id");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
*//*
                GetData getDataBoard = new GetData(http, v.getContext());

                // During initial setup, plug in the details fragment.
                BoardFragment boardFragment = new BoardFragment();
                Log.i("boardid", "put boardId in bundule in mainActivity " + selectedId);
                getIntent().putExtra("boardId", selectedId);
                boardFragment.setArguments(getIntent().getExtras());
                getFragmentManager().beginTransaction().add(android.R.id.content, boardFragment).commit();*//*


                Toast.makeText(
                        view.getContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition) + " id= " + selectedId, Toast.LENGTH_SHORT
                ).show();

                GetData getData = new GetData(new Http(), view.getContext());
                getData.loadBoard(selectedId);

                return false;
            }
        });*/
    }
}
