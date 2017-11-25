package com.hk4browser.app.hk4browser.http;

import android.util.Log;
import android.widget.Toast;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anton on 14.11.2017.
 */

public class Http implements IHttp{
    String get_boards = "https://2ch.hk/makaba/mobile.fcgi?task=get_boards";
    String get_board = "https://2ch.hk/board_id/catalog.json";
    String get_thread = "https://2ch.hk/makaba/mobile.fcgi?task=get_thread&board=board_id&thread=thread_id&post=post_from";
//TODO сделать в отдельном потоке
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void getBoardsMenu(Http.Callback<String> callback) {
        getData(callback, get_boards);

    }

    @Override
    public void getBoard(Http.Callback<String> callback,String key) {
        String url = get_board.replace("board_id",key);
        getData(callback, url);
    }

    @Override
    public void getThread(Http.Callback<String> callback,String board_id, String thread_id, String post_from) {
        String url = get_thread.replace("board_id", board_id).replace("thread_id", thread_id).replace("post_from", post_from);
        getData(callback, url);

    }

    private void getData(final Callback<String> callback, String url){
        Flowable.just(url)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        Request request = new Request.Builder()
                                .url(s)
                                .build();

                        try (Response response = client.newCall(request).execute()) {
                            String strResponse = response.body().string();
                            Log.i("lol", "response getThread Http class: "+strResponse);
                            return strResponse;

                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String threadsList) throws Exception {
                        callback.onData(threadsList);
                    }
                });

    }


    public interface Callback<T> {
        void onData(T str);
    }

}


