package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val api_key = "66707a3e524c4b32817102214232310"
    private val exlude = "current"
    private var city = "Майкоп"
    var search=false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gg()
    }
    fun  CityEdit(view: View){
        search=!search;
        val edit:EditText = findViewById(R.id.tv6);
        val editBtn:ImageButton = findViewById(R.id.imageButton);
        edit.setEnabled(search);
        if(search==true)
        {
            editBtn.setImageResource(R.drawable.search)

        }else
        {
            editBtn.setImageResource(R.drawable.edit)
            city=edit.text.toString();
            gg();
        }

    }
    fun gg(){

        val client = OkHttpClient()

        val url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=$api_key&q=$city&num_of_days=4&tp=24&format=json&lang=ru"
        try {
            val request = Request.Builder()
                .url(url)
                .build()

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response: Response = client.newCall(request).execute()
                    val responseData = response.body()?.string()

                    if (response.isSuccessful && responseData != null) {
                        var weather=  Weather(responseData);
                        runOnUiThread {
                            val json = JSONObject(responseData)

                            val fact = json.getJSONObject("data")
                            val current=fact.getJSONArray("current_condition").getJSONObject(0)
                            val temperature=current.getDouble("temp_C");
                            val img_array=current.getJSONArray("weatherIconUrl")
                            var image_url=img_array.getJSONObject(0).getString("value")
                            val description=current.getJSONArray("lang_ru").getJSONObject(0).getString("value");
                            val wind_speed=current.getDouble("windspeedMiles")
                            val temp:TextView = findViewById(R.id.tv)
                            val descript:TextView = findViewById(R.id.tv2)
                            val wind:TextView = findViewById(R.id.tv3)
                            val web:ImageView = findViewById(R.id.web);
                            val city_text:TextView = findViewById(R.id.tv6);

                            city_text.text=city;
                            descript.text=description;
                            temp.setText("${temperature}°C")
                            when(descript.text){
                                "Переменная облачность"-> web.setImageResource(R.drawable.cloudy);
                                "Облачно"-> web.setImageResource(R.drawable.cloud);
                                "Ясно"-> web.setImageResource(R.drawable.sunny);
                                "Солнечно"-> web.setImageResource(R.drawable.sunny);
                                "Дождь"-> web.setImageResource(R.drawable.rain);
                                "Ливень"-> web.setImageResource(R.drawable.rain);
                                "Снег"-> web.setImageResource(R.drawable.snow)
                                "Местами дождь"->web.setImageResource(R.drawable.rain);
                            }
                            wind.text=wind_speed.toString()+" км/ч " +"༄";

                            val Weather1Date: TextView = findViewById(R.id.weatherDate1);
                            val Weather1Icon: ImageView = findViewById(R.id.weatherIcon1);
                            var Weather1C: TextView = findViewById(R.id.weatherC1);
                            Weather1C.text=weather.Weather1C.toString();
                            Weather1Date.text=weather.Weather1Date;
                            when(weather.Weather1Disc){
                                "Переменная облачность"-> Weather1Icon.setImageResource(R.drawable.cloudy);
                                "Облачно"-> Weather1Icon.setImageResource(R.drawable.cloud);
                                "Ясно"-> Weather1Icon.setImageResource(R.drawable.sunny);
                                "Солнечно"-> Weather1Icon.setImageResource(R.drawable.sunny);
                                "Дождь"-> Weather1Icon.setImageResource(R.drawable.rain);
                                "Ливень"-> Weather1Icon.setImageResource(R.drawable.rain);
                                "Снег"-> Weather1Icon.setImageResource(R.drawable.snow)
                                "Местами дождь"->Weather1Icon.setImageResource(R.drawable.rain);
                                else ->Weather1Icon.setImageResource(R.drawable.weather_circle);
                            }

                            val Weather2Date: TextView = findViewById(R.id.weatherDate2);
                            val Weather2Icon: ImageView = findViewById(R.id.weatherIcon2);
                            var Weather2C: TextView = findViewById(R.id.weatherC2);
                            Weather2C.text=weather.Weather2C.toString();
                            Weather2Date.text=weather.Weather2Date;
                            when(weather.Weather2Disc){
                                "Переменная облачность"-> Weather2Icon.setImageResource(R.drawable.cloudy);
                                "Облачно"-> Weather2Icon.setImageResource(R.drawable.cloud);
                                "Ясно"-> Weather2Icon.setImageResource(R.drawable.sunny);
                                "Солнечно"-> Weather2Icon.setImageResource(R.drawable.sunny);
                                "Дождь"-> Weather2Icon.setImageResource(R.drawable.rain);
                                "Ливень"-> Weather2Icon.setImageResource(R.drawable.rain);
                                "Снег"-> Weather2Icon.setImageResource(R.drawable.snow)
                                "Местами дождь"->Weather2Icon.setImageResource(R.drawable.rain);
                                else ->Weather2Icon.setImageResource(R.drawable.weather_circle);
                            }

                            val Weather3Date: TextView = findViewById(R.id.weatherDate3);
                            val Weather3Icon: ImageView = findViewById(R.id.weatherIcon3);
                            var Weather3C: TextView = findViewById(R.id.weatherC3);
                            Weather3C.text=weather.Weather3C.toString();
                            Weather3Date.text=weather.Weather3Date;
                            when(weather.Weather3Disc){
                                "Переменная облачность"-> Weather3Icon.setImageResource(R.drawable.cloudy);
                                "Облачно"-> Weather3Icon.setImageResource(R.drawable.cloud);
                                "Ясно"-> Weather3Icon.setImageResource(R.drawable.sunny);
                                "Солнечно"-> Weather3Icon.setImageResource(R.drawable.sunny);
                                "Дождь"-> Weather3Icon.setImageResource(R.drawable.rain);
                                "Ливень"-> Weather3Icon.setImageResource(R.drawable.rain);
                                "Снег"-> Weather3Icon.setImageResource(R.drawable.snow)
                                "Местами дождь"->Weather3Icon.setImageResource(R.drawable.rain);
                                else ->Weather3Icon.setImageResource(R.drawable.weather_circle);
                            }

                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }catch (e:IOException){
            val temp:TextView = findViewById(R.id.tv)
            temp.text=e.message;
        }
    }
}