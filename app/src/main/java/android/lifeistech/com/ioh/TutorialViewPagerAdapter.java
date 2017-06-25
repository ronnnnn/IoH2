package android.lifeistech.com.ioh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fumiyauchiyama on 2017/06/24.
 */

public class TutorialViewPagerAdapter extends FragmentPagerAdapter {

    public TutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return  new TutorialFragment1();

        }else if (position == 1){

            return  new TutorialFragment2();
        }else{

            return  new TutorialFragment3();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
