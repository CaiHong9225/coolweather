package com.coolweather.android.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Shinelon on 2017/1/13.
 */

public class HttpUtil {
    public static void sendOhHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new  Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
