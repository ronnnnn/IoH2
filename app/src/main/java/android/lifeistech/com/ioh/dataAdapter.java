package android.lifeistech.com.ioh;

import android.content.Context;
import android.icu.util.Calendar;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by fumiyauchiyama on 2017/08/08.
 */

public class dataAdapter extends ArrayAdapter<data> {

    List<data> mdatas;
    //Calendar cal = Calendar.getInstance();


    public dataAdapter(Context context, int layoutResourseId,List<data> objects){

        super(context,layoutResourseId,objects);
        mdatas = objects;

    }

    public int getount(){

        return mdatas.size();

    }

    public data getItem(int position){

        return mdatas.get(position);

    }

    //public int getContains(){

        //int i = cal.get(Calendar.DATE);

        //return mdatas.contains(i);

    //}

    public int getCount(){

        return mdatas.size();

    }

}
