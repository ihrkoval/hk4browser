package com.hk4browser.app.hk4browser.http;

/**
 * Created by anton on 14.11.2017.
 */

public interface IHttp {
    public void getBoardsMenu(Http.Callback<String> callback);
    public void getBoard(Http.Callback<String> callback, String key);
    public void getThread(Http.Callback<String> callback,String board_id, String thread_id, String post_from);
}
