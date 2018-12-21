package com.example.student.jsonactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);

        String json =
                "{" +
                        "\"user\": \"gildong\"," +
                        "\"color\": [\"red\",\"green\",\"blue\"]" +
                        "}";

        String json1 =
                "{"+
                        "\"weather\": [{\"id\": 721," +
                                "\"main\": \"Haze\"," +
                                "\"description\": \"haze\"," +
                                "\"icon\": 50n}]," +
                        "\"main\": {\"temp\": 10.14," +
                                "\"pressure\": 1020," +
                                "\"humidity\": 37," +
                                "\"temp_min\": 6," +
                                "\"temp_max\": 13},"+
                        "\"id\": 18392," +
                        "\"name\": \"Seoul\"," +
                        "\"cod\": 200" +
                        "}";


//            try {
//                JSONObject root = new JSONObject(json);
//                String user_name = root.getString("user");
//                JSONArray colors = root.getJSONArray("color");
//
//                String str1 = colors.getString(0);
//                String str2 = colors.getString(1);
//                String str3 = colors.getString(2);
//
//                for (int i = 0; i < colors.length(); i++) {
//                    Log.d("show color", colors.getString(i));
//                }
//
//                String result = "user : " + user_name + "\ncolor1 : " + str1 +
//                        "\ncolor2 : " + str2 + "\ncolor3 : " + str3;
//
//                tv1.setText(result);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            try {
                JSONObject root = new JSONObject(json1);


                JSONArray weather_name = root.getJSONArray("weather");
                JSONObject weather = weather_name.getJSONObject(0);
                int weather_id = weather.getInt("id");
                String weather_main = weather.getString("main");
                String weather_description = weather.getString("description");
                String weather_icon = weather.getString("icon");


                int id_name = root.getInt("id");
                String name_name = root.getString("name");
                int cod_name = root.getInt("cod");


                JSONObject main = root.getJSONObject("main");
                Double temp_name = main.getDouble("temp");
                int pressure_name = main.getInt("pressure");
                int humidity_name = main.getInt("humidity");
                int temp_min_name = main.getInt("temp_min");
                int temp_max_name = main.getInt("temp_max");





//                String result = "weather_id : " + str1 +
//                        "\nweatner_main : " + str2 +
//                        "\nweather_description : " + str3+
//                        "\nweatner_icon : " + str4;


                String result2 = "main.temp : "+temp_name+
                        "\nmain.pressure : "+pressure_name+
                        "\nmain.humidity : "+humidity_name+
                        "\nmain.min : "+temp_min_name+
                        "\nmain.max : "+temp_max_name;

                String result3 = "id : " +id_name+
                        "\nname : " +name_name+
                        "\ncod : " +cod_name;

                tv1.setText(result3);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
