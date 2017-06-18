package android.lifeistech.com.ioh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start3);
    }

    public void backtwo(View v){
        Intent intent = new Intent(this,Start2Activity.class);
        startActivity(intent);

    }

    public void ok(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

}
