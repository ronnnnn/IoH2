package android.lifeistech.com.ioh;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class OutsideFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refMug = database.getReference();

    TextView texthan;
    TextView temTV;
    int wd;
    int ch0;
    int ch1;
    int tem;
    int hun;
    Button button;
    Handler mHandler;
    int mTime;
    Timer mTimer;

    boolean aBoolean = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_outside, container, false);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setMax(1000);


        texthan = (TextView) view.findViewById(R.id.texthan);
        temTV = (TextView)view.findViewById(R.id.texttem);
        button = (Button) view.findViewById(R.id.click);
        mHandler = new Handler();


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
                mTime = 10;

                mTimer = new Timer(false);


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


                if (wd <= 10) {

                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTime--;

                                }


                            });

                            if (mTime == 0 && !aBoolean) {

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

                                aBoolean = true;


                                mTimer.cancel();
                            }

                        }



                    }, 0, 1000);



                } else {

                    //android.support.v7.app.NotificationCompat.Builder builder = new android.support.v7.app.NotificationCompat.Builder(getContext());
                    //builder.setSmallIcon(R.mipmap.ic_launcher);
                    //builder.setContentTitle("ALL");
                    //builder.setDefaults(Notification.DEFAULT_ALL);
                    //builder.setWhen(System.currentTimeMillis());
                    //NotificationManager manager = (NotificationManager) getContext().getSystemService(Service.NOTIFICATION_SERVICE);
                    //manager.notify(0, builder.build());
                }

                Log.d("mTime=", String.valueOf(mTime));


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
                aBoolean = false;
                Toast.makeText(getContext(),"洗濯を終了しました",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
