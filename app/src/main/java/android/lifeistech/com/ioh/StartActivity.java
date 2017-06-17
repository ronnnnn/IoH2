package android.lifeistech.com.ioh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.lifeistech.com.ioh.R.styleable.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void ok(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
