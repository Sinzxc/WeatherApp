package com.example.weather

import android.widget.ImageView
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class Weather {
    var temperature:Double?=null
    var description:String?=null
    var image_url:String?=null;
    var wind_speed:Double?=null

    var Weather1Disc:String
    var Weather1C:String
    var Weather1Date:String

    var Weather2Disc:String
    var Weather2C:String
    var Weather2Date:String

    var Weather3Disc:String
    var Weather3C:String
    var Weather3Date:String


    constructor(responseData: String){
            val json = JSONObject(responseData)
            val fact = json.getJSONObject("data")
            val current=fact.getJSONArray("current_condition").getJSONObject(0)
            temperature=current.getDouble("temp_C");
            val img_array=current.getJSONArray("weatherIconUrl")
            image_url=img_array.getJSONObject(0).getString("value")
            description=current.getJSONArray("lang_ru").getJSONObject(0).getString("value");
            wind_speed=current.getDouble("windspeedMiles")

            val wList= fact.getJSONArray("weather");
            val weather1=wList.getJSONObject(1);
            val weather2=wList.getJSONObject(2);
            val weather3=wList.getJSONObject(3);

            Weather1Disc=weather1.getJSONArray("hourly").getJSONObject(0).getJSONArray("lang_ru").getJSONObject(0).getString("value");
            Weather1Date=weather1.getString("date")
            Weather1C=weather1.getJSONArray("hourly").getJSONObject(0).getString("tempC")+"°C";

            Weather2Disc=weather2.getJSONArray("hourly").getJSONObject(0).getJSONArray("lang_ru").getJSONObject(0).getString("value");
            Weather2Date=weather2.getString("date")
            Weather2C=weather2.getJSONArray("hourly").getJSONObject(0).getString("tempC")+"°C";

            Weather3Disc=weather3.getJSONArray("hourly").getJSONObject(0).getJSONArray("lang_ru").getJSONObject(0).getString("value");
            Weather3Date=weather3.getString("date")
            Weather3C=weather3.getJSONArray("hourly").getJSONObject(0).getString("tempC")+"°C";

    }

}