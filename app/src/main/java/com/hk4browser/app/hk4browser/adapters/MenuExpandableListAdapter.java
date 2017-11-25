package com.hk4browser.app.hk4browser.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.hk4browser.app.hk4browser.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by anton on 05.11.2017.
 */

public class MenuExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public MenuExpandableListAdapter(Context context, List<String> expandableListTitle,
                                     HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
       Log.i("lol", "EXPANDABLE LIST TITLE SIZE "+expandableListTitle.size() +";"+ expandableListDetail.keySet().size());
        for (String s : expandableListTitle){
            Log.i("lol","EXPANDABLE LIST TITLE "+s+" Key size is + "+expandableListDetail.get(s).size());

        }
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        Log.i("child", this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition) + " returned value in getChild metgod? menuadapter class");
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);

    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        Log.i("child","expandedListText String, getChildView, ExpListAdapter "+expandedListText);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        Log.i("TxtViwChld","set child textView in adapter "+expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }


    TextView listTitleTextView;
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        Log.i("getgroupView","MenuExpandableListAapter position is " + listPosition + " = "+listTitle);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        setText(listTitle);
        return convertView;
    }

    private void setText(String listTitle){

        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        //TODO notifiall
       // notifyAll();
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}