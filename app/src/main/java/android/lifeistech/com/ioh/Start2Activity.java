package android.lifeistech.com.ioh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutorial2);
    }

    public void nexttwo(View v){
        Intent intent = new Intent(this,Start3Activity.class);
        startActivity(intent);

    }

    public void back(View v){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);

    }

}
