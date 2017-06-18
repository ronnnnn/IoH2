package android.lifeistech.com.ioh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

import static android.lifeistech.com.ioh.R.styleable.View;

public class StartActivity extends AppCompatActivity {

    SharedPreferences plef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        plef = getSharedPreferences("plef_start",MODE_PRIVATE);

        SharedPreferences.Editor editor = plef.edit();
        editor.putBoolean("key_tutorial",true);
        editor.commit();

    }



    public void next(View v){
        Intent intent = new Intent(this,Start2Activity.class);
        startActivity(intent);

    }
}
