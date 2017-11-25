package com.hk4browser.app.hk4browser.http;

import android.app.FragmentManager;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.hk4browser.app.hk4browser.R;
import com.hk4browser.app.hk4browser.adapters.MenuExpandableListAdapter;
import com.hk4browser.app.hk4browser.fragments.BoardFragment;
import com.hk4browser.app.hk4browser.fragments.PostFragment;
import com.hk4browser.app.hk4browser.utils.InitializeMenu;
import com.hk4browser.app.hk4browser.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anton on 14.11.2017.
 */

public class GetData extends BaseObservable {
    IHttp http;
    Context context;
    String resposeMenu;
    String resposeBoard;
    String resposeThread;
    View view;
    InitializeMenu initializeMenu;
    FragmentManager fragmentManager;

    public GetData(IHttp http, Context context) {
        Log.i("menu", "http to stroing(GetData) in getData constructor " + http.toString());
        this.http = http;
        this.context = context;
    }


    public void boardsMap(View view){//HashMap<String, List<String>> boardsMap() {
        this.view=view;
        http.getBoardsMenu(callback);
        //return  new JsonUtils().getExpandableListDetail(menuList);
    }

    public void loadBoard(String key, FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        http.getBoard(callbackBoard, key);
            }

    public void loadThread(FragmentManager fragmentManager, String board_id, String thread_id, String post_from)  {
        Log.i("thread","loadThread is called in GetData class: board_id = "+board_id+", thread_id= "+thread_id+", post_form = "+post_from);
        this.fragmentManager = fragmentManager;
        http.getThread(callbackThread, board_id, thread_id, post_from );
    }



    private Http.Callback callback = new Http.Callback<String>() {
        @Override
        public void onData(String dataStr) {
            Log.i("lol", " try to get responce in callback"+dataStr);
            resposeMenu=dataStr;
           initializeMenu =  new InitializeMenu(resposeMenu);
           initializeMenu.initialize(view);
        }
    };

    private Http.Callback callbackBoard = new Http.Callback<String>() {
        @Override
        public void onData(String dataStr) {
            Log.i("lol", " try to get responce in callback"+dataStr);
            resposeBoard=dataStr;
            BoardFragment boardFragment = new BoardFragment();
            Log.i("boardid", "put boardId in bundule in mainActivity " + resposeBoard);
            Bundle bundle = new Bundle();
            bundle.putString("boardId", resposeBoard);
            boardFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.container, boardFragment).addToBackStack(null).commit();
            //fragmentManager.beginTransaction().add(R.id.container, boardFragment).commit();
            //setBoard(view);
        }
    };

    private Http.Callback callbackThread = new Http.Callback<String>() {
        @Override
        public void onData(String dataStr) {
            Log.i("thread", " try to get responce in callback"+dataStr);
            resposeThread=dataStr;
            PostFragment postFragment = new PostFragment();
            Log.i("threadid", "put boardId in bundule in mainActivity " + resposeThread);
            Bundle bundle = new Bundle();
            bundle.putString("posts", resposeThread);
            postFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.container, postFragment).addToBackStack(null).commit();
            //fragmentManager.beginTransaction().add(R.id.container, boardFragment).commit();
            //setBoard(view);
        }
    };

    public String getResposeMenu(){return resposeMenu;}
    public HashMap<String, List<String>> getExpandableListMap(){
        return initializeMenu.getExpandableListDetail();
    }
    public List<String> getExpandableListTitle(){
        return initializeMenu.getExpandableListTitle();
    }



    /////////////////////////////////////////////////////////////// TODO create new class

   /* private void setBoard(View view){

        BoardFragment boardFragment = new BoardFragment();
        Log.i("boardid","put boardId in bundule in mainActivity "+resposeBoard);
        Bundle bundle = new Bundle();
        bundle.putString("boardId",resposeBoard);
        boardFragment.setArguments(bundle);
        fragmentManager.beginTransaction().add(android.R.id.content, boardFragment).commit();




    }*/


}
