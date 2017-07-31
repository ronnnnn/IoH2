package android.lifeistech.com.ioh;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    ImageButton good;
    ImageButton bad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        good = (ImageButton)findViewById(R.id.goodbutton);
        bad = (ImageButton)findViewById(R.id.badbutton);


    }

    public void good(View v){

    }

    public void bad(View v){

    }






}
