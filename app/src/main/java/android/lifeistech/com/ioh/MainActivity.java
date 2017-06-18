package android.lifeistech.com.ioh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    boolean aBoolean;
    SharedPreferences plef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("天日干し"));
        tabLayout.addTab(tabLayout.newTab().setText("部屋干し"));
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar) ;

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);

        plef = getSharedPreferences("plef_start",MODE_PRIVATE);

        aBoolean = plef.getBoolean("key_tutorial",false);

        if (!aBoolean) {

            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);

        }else {
            SharedPreferences.Editor editor = plef.edit();
            editor.putBoolean("key_tutorial",false);

        }


    }

    public void help(View v){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);
    }

}
