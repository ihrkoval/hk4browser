package com.hk4browser.app.hk4browser.fragments;

import android.app.Fragment;

import com.hk4browser.app.hk4browser.http.Http;
import com.hk4browser.app.hk4browser.http.IHttp;


/**
 * Created by anton on 12.11.2017.
 */

public class BaseFragment extends Fragment {
    protected IHttp getHttp() {
        return new Http();
    }
}
