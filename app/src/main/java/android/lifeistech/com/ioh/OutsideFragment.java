package android.lifeistech.com.ioh;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class OutsideFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMug = database.getReference();

    TextView texthan;
    TextView temTV;
    TextView textwea;
    int wd;
    int ch0;
    int ch1;
    int tem;
    int hun;
    Button button;
    JSONArray weatherArray;
    JSONObject weatherObj;

    boolean aBoolean;
    boolean nBoolean;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    LocationManager mLocationManager;
    private Handler mHandler;

    private void commit(boolean aBoolean, boolean nBoolean){
        editor.putBoolean("aboolean",aBoolean);
        editor.putBoolean("nboolean",nBoolean);
        editor.commit();
    }

    private boolean getBooleans(){

        aBoolean = pref.getBoolean("aboolean",false);
        nBoolean = pref.getBoolean("nboolean",false);

        return aBoolean & nBoolean;
    }

    private void getWeather() {
        // リクエストオブジェクトを作って
        Request request = new Request.Builder()
                // URLを生成
                .url("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010")
                .get()
                .build();
        // クライアントオブジェクトを作成する
        OkHttpClient client = new OkHttpClient();
        // 新しいリクエストを行う
        client.newCall(request).enqueue(new Callback() {
            // 通信が失敗した時
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            // 通信が成功した時
            @Override
            public void onResponse(Response response) throws IOException {
                // 通信結果をログに出力する
                Log.d("onResponse", response.toString());
                final String json = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        parseJson(json);
                    }
                });
            }
        });

    }


    private void parseJson(String json) {
        Log.d("Json", json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            // {forecasts[] -> 0 -> {dataLabel, telop, tem}}
            JSONArray forecastsArray = jsonObject.getJSONArray("forecasts");
            // 0番目のものが今日の天気なので取得する
            JSONObject todayWeatherJson = forecastsArray.getJSONObject(0);
            // 今日
            String date = todayWeatherJson.getString("date");

            String telop = todayWeatherJson.getString("telop");
            String dataLabel = todayWeatherJson.getString("dateLabel");
            textwea.setText(telop + "\n" + dataLabel);


            JSONObject temperatureJson = todayWeatherJson.getJSONObject("temperature");
            JSONObject minJson = temperatureJson.get("min") != null ? temperatureJson.getJSONObject("min") : null;
            String min = "";
            if (minJson != null) {
                min = minJson.getString("celsius");
            }
            JSONObject maxJson = temperatureJson.get("max") != null ? temperatureJson.getJSONObject("max") : null;
            String max = "";
            if (maxJson != null) {
                max = maxJson.getString("celsius");
            }
            //mTempTextView.setText("最低気温:" + min + "〜最高気温:" + max);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_outside, container, false);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getContext().getSharedPreferences("pref",Context.MODE_PRIVATE);
        editor = pref.edit();

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setMax(1000);


        texthan = (TextView) view.findViewById(R.id.texthan);
        textwea = (TextView) view.findViewById(R.id.textwea);
        temTV = (TextView) view.findViewById(R.id.texttem);
        button = (Button) view.findViewById(R.id.click);
        mHandler = new Handler();

        editor.putBoolean("aBoolean",aBoolean);
        editor.commit();

        getBooleans();
        getWeather();

        if (aBoolean) {
            button.setText("洗濯終了ボタン");
            Log.d("aboolean=",String.valueOf(aBoolean));

        }else {
            button.setText("洗濯開始ボタン");
            Log.d("aboolean=",String.valueOf(aBoolean));
        }



        refMug.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getKey().equals("number")) {
                    ch0 = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }

                if (dataSnapshot.getKey().equals("number1")) {
                    ch1 = dataSnapshot.getValue(Integer.class);

                }
                if (dataSnapshot.getKey().equals("temperature")) {
                    tem = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }
                if (dataSnapshot.getKey().equals("humidity")) {
                    hun = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }


                wd = (ch0 + ch1) / 2;

                texthan.setText(String.valueOf(wd));
                temTV.setText(tem + "℃、" + hun + "%");
                //texthan.setText("" + wd);
                progressBar.setProgress(wd);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.getKey().equals("number")) {
                    ch0 = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }

                if (dataSnapshot.getKey().equals("number1")) {
                    ch1 = dataSnapshot.getValue(Integer.class);

                }
                if (dataSnapshot.getKey().equals("temperature")) {
                    tem = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }
                if (dataSnapshot.getKey().equals("humidity")) {
                    hun = dataSnapshot.getValue(Integer.class);
                    //texthan.setText(String.valueOf(wd));
                    //progressBar.setProgress(wd);
                }

                wd = (ch0 + ch1) / 2;

                texthan.setText(String.valueOf(wd));
                temTV.setText(tem + "℃、" + hun + "%");
                progressBar.setProgress(wd);

                if (wd <= 5 && aBoolean && nBoolean) {

                    android.support.v7.app.NotificationCompat.Builder builder = new android.support.v7.app.NotificationCompat.Builder(getContext());
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setContentTitle("洗濯物を取り込みましょう");
                    builder.setContentText("取り込む時にスイッチを切ってください");
                    //builder.setContentInfo("情報欄");
                    builder.setTicker("乾燥しました！");
                    builder.setDefaults(Notification.DEFAULT_ALL);
                    builder.setWhen(System.currentTimeMillis());
                    NotificationManager manager = (NotificationManager) getContext().getSystemService(Service.NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());
                    nBoolean = false;
                    commit(aBoolean,nBoolean);
                    return;


                } else {

                }



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });





        View.OnClickListener link = new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://openweathermap.org/");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (aBoolean) {
                    aBoolean = false;
                    button.setText("洗濯開始ボタン");
                    Toast.makeText(getContext(), "洗濯を終了しました", Toast.LENGTH_SHORT).show();
                    commit(aBoolean,nBoolean);
                    Log.d("aboolean=",String.valueOf(aBoolean));

                }else {
                    aBoolean = true;
                    nBoolean = true;
                    button.setText("洗濯終了ボタン");
                    Log.d("aboolean=",String.valueOf(aBoolean));
                    commit(aBoolean,nBoolean);

                }

            }
        });


    }


    private void getData() {
        String requestURL = "http://api.openweathermap.org/data/2.5/find?lat=43.067885&lon=141.355539&cnt=1";
        String idescription;
        try {

            URL url = new URL(requestURL);

            InputStream is = url.openConnection().getInputStream();

            // JSON形式で結果が返るためパースのためにStringに変換する
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while (null != (line = reader.readLine())) {
                sb.append(line);
            }
            String data = sb.toString();

            try {
                JSONObject rootObj = new JSONObject(data);
                JSONArray listArray = rootObj.getJSONArray("list");

                JSONObject obj = listArray.getJSONObject(0);

                // 地点ID
                int id = obj.getInt("id");

                // 地点名
                String cityName = obj.getString("name");

                // 気温(Kから℃に変換)
                JSONObject mainObj = obj.getJSONObject("main");
                float currentTemp = (float) (mainObj.getDouble("temp") - 273.15f);

                float minTemp = (float) (mainObj.getDouble("temp_min") - 273.15f);

                float maxTemp = (float) (mainObj.getDouble("temp_max") - 273.15f);

                // 湿度
                if (mainObj.has("humidity")) {
                    int humidity = mainObj.getInt("humidity");
                }

                // 取得時間
                long time = obj.getLong("dt");

                // 天気
                weatherArray = obj.getJSONArray("weather");
                weatherObj = weatherArray.getJSONObject(0);
                idescription = weatherObj.getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //textwea.setText(idescription);

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }




}
