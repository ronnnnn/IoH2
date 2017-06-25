package android.lifeistech.com.ioh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    SharedPreferences plef;
    private int closeEnterAnimationId;
    private int closeExitAnimationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutorial);

        plef = getSharedPreferences("plef_start",MODE_PRIVATE);

        SharedPreferences.Editor editor = plef.edit();
        editor.putBoolean("key_tutorial",true);
        editor.commit();

        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();

        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        closeEnterAnimationId = activityStyle.getResourceId(0, 0);
        closeExitAnimationId = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();

    }



    public void next(View v){
        Intent intent = new Intent(this,Start2Activity.class);
        startActivity(intent);

        overridePendingTransition(closeEnterAnimationId, closeExitAnimationId);

    }
}
