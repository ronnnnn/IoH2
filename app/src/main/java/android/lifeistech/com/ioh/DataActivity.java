package android.lifeistech.com.ioh;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar) ;
        progressBar.setMax(100);
        progressBar.setProgress(60);
    }


}
