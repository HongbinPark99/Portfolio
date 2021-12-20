package com.techtown.weatherwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView dateView;
    TextView clothTf2;
    TextView cityView;
    TextView weatherView;
    TextView tempView;

    static RequestQueue requestQueue;
    int set = 1;
    int menuBar = 0;
    int cloth = 0;
    int region = 0;
    double tem = 0;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = findViewById(R.id.dateView);
        clothTf2 = findViewById(R.id.clothTf2);
        cityView = findViewById(R.id.cityView);
        weatherView = findViewById(R.id.weatherView);
        tempView = findViewById(R.id.tempView);

        ImageButton button = findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentCall();
            }
        });

        ImageButton button2 = findViewById(R.id.imageButton2);
        button2.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               Menu();
           }
        });

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        boot();
    }

    public void Onclick1(View view){
        Cloth();
    }

    public void Onclick2(View view){
        Cloth_C();
    }

    public void Onclick3(View view){
        region();
    }

    public void Onclick4(View view){
        region_C();
    }

    public void Onclick_region_1(View view){
        set = 1;
        CurrentCall();
    }
    public void Onclick_region_2(View view){
        set = 5;
        CurrentCall();
    }
    public void Onclick_region_3(View view){
        set = 4;
        CurrentCall();
    }
    public void Onclick_region_4(View view){
        set = 6;
        CurrentCall();
    }
    public void Onclick_region_5(View view){
        set = 0;
        CurrentCall();
    }
    public void Onclick_region_6(View view){
        set = 3;
        CurrentCall();
    }
    public void Onclick_region_7(View view){
        set = 2;
        CurrentCall();
    }

    private void CurrentCall(){
        switch(set) {
            case 0 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 1 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Busan&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 2 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Daejeon&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 3 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Incheon&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 4 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Gwangju&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 5 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Ulsan&appid=b4db88a35b731ee3efdd0352862224d0";
                break;
            case 6 :
                url = "http://api.openweathermap.org/data/2.5/weather?q=Daegu&appid=b4db88a35b731ee3efdd0352862224d0";
        }


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {

                    //System의 현재 시간(년,월,일,시,분,초까지)가져오고 Date로 객체화함
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);

                    //년, 월, 일 형식으로. 시,분,초 형식으로 객체화하여 String에 형식대로 넣음
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
                    String getDay = simpleDateFormatDay.format(date);
                    String getTime = simpleDateFormatTime.format(date);

                    //getDate에 개행을 포함한 형식들을 넣은 후 dateView에 text설정
                    String getDate = getDay + "\n" + getTime;
                    dateView.setText(getDate);

                    //api로 받은 파일 jsonobject로 새로운 객체 선언
                    JSONObject jsonObject = new JSONObject(response);


                    //도시 키값 받기
                    String city = jsonObject.getString("name");
                    if(city.equals("Busan")){
                        cityView.setText("부산");
                    }
                    if(city.equals("Seoul")){
                        cityView.setText("서울");
                    }
                    if(city.equals("Daejeon")){
                        cityView.setText("대전");
                    }
                    if(city.equals("Incheon")){
                        cityView.setText("인천");
                    }
                    if(city.equals("Gwangju")){
                        cityView.setText("광주");
                    }
                    if(city.equals("Ulsan")){
                        cityView.setText("울산");
                    }
                    if(city.equals("Daegu")){
                        cityView.setText("대구");
                    }

                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);

                    String weather = weatherObj.getString("description");

                    if(weather.equals("clear sky")){
                        weatherView.setText("맑음");
                        findViewById(R.id.weather1).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("few clouds")){
                        weatherView.setText("조금흐림");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("scattered clouds")){
                        weatherView.setText("흐림");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("broken clouds")){
                        weatherView.setText("흐림");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("shower rain")){
                        weatherView.setText("소나기");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("rain")){
                        weatherView.setText("비");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("thunderstorm")){
                        weatherView.setText("번개");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("snow")){
                        weatherView.setText("눈");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.VISIBLE);
                        findViewById(R.id.weather9).setVisibility(View.GONE);
                    }
                    if(weather.equals("mist")){
                        weatherView.setText("안개");
                        findViewById(R.id.weather1).setVisibility(View.GONE);
                        findViewById(R.id.weather2).setVisibility(View.GONE);
                        findViewById(R.id.weather3).setVisibility(View.GONE);
                        findViewById(R.id.weather4).setVisibility(View.GONE);
                        findViewById(R.id.weather5).setVisibility(View.GONE);
                        findViewById(R.id.weather6).setVisibility(View.GONE);
                        findViewById(R.id.weather7).setVisibility(View.GONE);
                        findViewById(R.id.weather8).setVisibility(View.GONE);
                        findViewById(R.id.weather9).setVisibility(View.VISIBLE);
                    }

                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));

                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*10)/10.0);
                    tempView.setText(tempDo +  "°C");
                    tem = tempDo;


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
    private void Menu(){
        if(menuBar == 0){
            findViewById(R.id.menuImage1).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton3).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton4).setVisibility(View.VISIBLE);
            menuBar = 1;
        }
        else if(menuBar == 1 && cloth == 0){
            findViewById(R.id.menuImage1).setVisibility(View.GONE);
            findViewById(R.id.imageButton3).setVisibility(View.GONE);
            findViewById(R.id.imageButton4).setVisibility(View.GONE);
            menuBar = 0;
        }
    }
    private void Cloth(){
        if(cloth == 0){
            findViewById(R.id.clothBackground).setVisibility(View.VISIBLE);
            findViewById(R.id.clothTf1).setVisibility(View.VISIBLE);
            findViewById(R.id.clothTf2).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton5).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton3).setVisibility(View.GONE);
            findViewById(R.id.imageButton4).setVisibility(View.GONE);
            if(tem > 28){
                findViewById(R.id.cloth1).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("민소매, 반팔, 반바지, 짧은 치마, 린넨 옷을 추천드립니다.");
            }
            if(tem >= 23 && tem < 27){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("반팔, 얇은 셔츠, 반바지,\n 면바지를 추천드립니다.");
            }
            if(tem >= 20 && tem < 23){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("블라우스, 긴팔 티,\n 면바지, 슬렉스를 추천드립니다.");
            }
            if(tem >= 17 && tem < 20){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("얇은 가디건, 니트, 맨투맨,\n 후드, 긴 바지를 추천드립니다.");
            }
            if(tem >= 12 && tem < 17){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("자켓, 가디건, 청자켓, 니트,\n 스타킹, 청바지를 추천드립니다.");
            }
            if(tem >= 9 && tem < 12){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("트렌치 코트, 야상, 점퍼,\n 스타킹, 기모바지를 추천드립니다.");
            }
            if(tem >= 5 && tem < 9){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.VISIBLE);
                findViewById(R.id.cloth8).setVisibility(View.GONE);
                clothTf2.setText("울 코트, 히트텍,\n 가죽 옷, 기모를 추천드립니다.");
            }
            if(tem < 5){
                findViewById(R.id.cloth1).setVisibility(View.GONE);
                findViewById(R.id.cloth2).setVisibility(View.GONE);
                findViewById(R.id.cloth3).setVisibility(View.GONE);
                findViewById(R.id.cloth4).setVisibility(View.GONE);
                findViewById(R.id.cloth5).setVisibility(View.GONE);
                findViewById(R.id.cloth6).setVisibility(View.GONE);
                findViewById(R.id.cloth7).setVisibility(View.GONE);
                findViewById(R.id.cloth8).setVisibility(View.VISIBLE);
                clothTf2.setText("패딩, 두꺼운 코트, 누빔 옷, 기모, 목도리를 추천드립니다.");
            }
            cloth = 1;
        }
    }
    private void Cloth_C(){
        if(cloth == 1){
            findViewById(R.id.clothBackground).setVisibility(View.GONE);
            findViewById(R.id.clothTf1).setVisibility(View.GONE);
            findViewById(R.id.clothTf2).setVisibility(View.GONE);
            findViewById(R.id.imageButton5).setVisibility(View.GONE);
            findViewById(R.id.imageButton3).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton4).setVisibility(View.VISIBLE);
            findViewById(R.id.cloth1).setVisibility(View.GONE);
            findViewById(R.id.cloth2).setVisibility(View.GONE);
            findViewById(R.id.cloth3).setVisibility(View.GONE);
            findViewById(R.id.cloth4).setVisibility(View.GONE);
            findViewById(R.id.cloth5).setVisibility(View.GONE);
            findViewById(R.id.cloth6).setVisibility(View.GONE);
            findViewById(R.id.cloth7).setVisibility(View.GONE);
            findViewById(R.id.cloth8).setVisibility(View.GONE);
            clothTf2.setText("");
            cloth = 0;
        }
    }
    private void region(){
        if(region == 0){
            findViewById(R.id.regionBackground).setVisibility(View.VISIBLE);
            findViewById(R.id.region1).setVisibility(View.VISIBLE);
            findViewById(R.id.region2).setVisibility(View.VISIBLE);
            findViewById(R.id.region3).setVisibility(View.VISIBLE);
            findViewById(R.id.region4).setVisibility(View.VISIBLE);
            findViewById(R.id.region5).setVisibility(View.VISIBLE);
            findViewById(R.id.region6).setVisibility(View.VISIBLE);
            findViewById(R.id.region7).setVisibility(View.VISIBLE);
            findViewById(R.id.regionTf3).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton6).setVisibility(View.VISIBLE);
            findViewById(R.id.imageButton3).setVisibility(View.GONE);
            findViewById(R.id.imageButton4).setVisibility(View.GONE);
            region = 1;
        }
    }
    private void region_C(){
        findViewById(R.id.regionBackground).setVisibility(View.GONE);
        findViewById(R.id.region1).setVisibility(View.GONE);
        findViewById(R.id.region2).setVisibility(View.GONE);
        findViewById(R.id.region3).setVisibility(View.GONE);
        findViewById(R.id.region4).setVisibility(View.GONE);
        findViewById(R.id.region5).setVisibility(View.GONE);
        findViewById(R.id.region6).setVisibility(View.GONE);
        findViewById(R.id.region7).setVisibility(View.GONE);
        findViewById(R.id.regionTf3).setVisibility(View.GONE);
        findViewById(R.id.imageButton6).setVisibility(View.GONE);
        findViewById(R.id.imageButton3).setVisibility(View.VISIBLE);
        findViewById(R.id.imageButton4).setVisibility(View.VISIBLE);
        region = 0;
    }

    private void boot(){
        CurrentCall();
    }
}