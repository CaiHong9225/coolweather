package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shinelon on 2017/1/13.
 */

public class Utility {

    /**
     * 解析处理服务器返回的省级数据
     * @param response
     * @return
     */
    public  static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i =0;i<allProvince.length();i++){
                    JSONObject jsonObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    Log.e("省份",jsonObject.getString("name"));
                    province.save();

                }
                Log.e("解析省份成功","解析省份成功");
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return  false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param response
     * @param provinId
     * @return
     */
    public static boolean handleCityResponse(String response,int provinId){

        if(!TextUtils.isEmpty(response)){
            JSONArray allCities = null;
            try {
                allCities = new JSONArray(response);
                for (int i =0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinId);
                    city.save();
                }
                Log.e("解析省会-成功","解析省会成功");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject countryObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countryObject.getString("name"));
                    county.setWeatherId(countryObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                Log.e("解析縣城成功","解析县城成功");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return  false;
    }

    /**
     * 解析天气信息
     * @param response
     * @return
     */
      public static Weather handleWeatherResponse(String response) {

          try {
              JSONObject jsonObject = new JSONObject(response);

              JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
              String weatherContent = jsonArray.getJSONObject(0).toString();
              return new Gson().fromJson(weatherContent, Weather.class);
          } catch (JSONException e) {
              e.printStackTrace();
          }
            return null;

      }
}
