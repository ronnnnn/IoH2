package android.lifeistech.com.ioh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    boolean aBoolean;
    SharedPreferences plef;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);



        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar) ;

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);

        plef = getSharedPreferences("plef_start",MODE_PRIVATE);

        aBoolean = plef.getBoolean("key_tutorial",false);

        if (!aBoolean) {

            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);

        }else {
            SharedPreferences.Editor editor = plef.edit();
            editor.putBoolean("key_tutorial",false);

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help:
                // ボタンをタップした際の処理を記述
                Intent intent = new Intent(this,TutorialActivity.class);
                startActivity(intent);
        }
        return true;
    }




    public void data(View v){
        Intent intent = new Intent(this,DataActivity.class);
        startActivity(intent);
    }



}
