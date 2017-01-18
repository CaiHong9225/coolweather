package com.coolweather.android.gson;

/**
 * Created by Shinelon on 2017/1/18.
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
