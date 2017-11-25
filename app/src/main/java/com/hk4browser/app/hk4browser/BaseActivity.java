package com.hk4browser.app.hk4browser;

import android.support.v7.app.AppCompatActivity;

import com.hk4browser.app.hk4browser.http.Http;
import com.hk4browser.app.hk4browser.http.IHttp;


/**
 * Created by anton on 05.11.2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected IHttp getHttp() {
        return new Http();
    }
}