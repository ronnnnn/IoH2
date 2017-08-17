package android.lifeistech.com.ioh;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public int getcount(){

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

    private class ViewHoler{
        ImageView imageView;
        TextView datetextview;
        TextView timetextview;
        LinearLayout Card;

        public ViewHoler(View view){
            imageView = (ImageView)view.findViewById(R.id.weaimageView);
            datetextview = (TextView)view.findViewById(R.id.datetextview);
            timetextview = (TextView)view.findViewById(R.id.timetextview);
            Card = (LinearLayout)view.findViewById(R.id.card);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHoler viewHoler;

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, null);
            viewHoler = new ViewHoler(convertView);
            convertView.setTag(viewHoler);

        }else {
            viewHoler = (ViewHoler)convertView.getTag();
        }

        final data item = getItem(position);

        if(item == null){

            if(item.weather == "Thunderstorm"){
                viewHoler.imageView.setImageResource(R.drawable.thunderstanp);
            }else if(item.weather ==  "Drizzle"){
                viewHoler.imageView.setImageResource(R.drawable.rainstamp);
            }else if(item.weather == "Rain") {
                viewHoler.imageView.setImageResource(R.drawable.rainstamp);
            }else if (item.weather == "Snow"){
                viewHoler.imageView.setImageResource(R.drawable.snowstamp);
            }else if (item.weather == "Atmosphere"){
                viewHoler.imageView.setImageResource(R.drawable.sunstamp);
            }else if (item.weather == "Clear"){
                viewHoler.imageView.setImageResource(R.drawable.sunstamp);
            }else if(item.weather == "Clouds") {
                 viewHoler.imageView.setImageResource(R.drawable.cloudstamp);
            }else{
                viewHoler.imageView.setImageResource(R.drawable.crownstamp);
            }

            viewHoler.datetextview.setText(item.month + "月" + item.day + "日");
            viewHoler.timetextview.setText(item.time);

            /*viewHoler.Card.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //Intent intent = new Intent(this,DataActivity.class);
                    //startActivity(intent);

                    Intent intent = new Intent(MainActivity.this, DataActivity.class);
                    startActivity(intent);
                }
            });
            */

        }

        return convertView;
    }



}
