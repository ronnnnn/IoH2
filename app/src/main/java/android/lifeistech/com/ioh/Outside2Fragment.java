package android.lifeistech.com.ioh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Outside2Fragment extends Fragment {

    //CalendarView calendarView = new CalendarView(this.getContext());
    CalendarView calendarView;
    List<data> mdatas;
    dataAdapter mdataAdaoter;
    ListView mlistView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_outside2, container, false);



    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //calendarView = (CalendarView)view.findViewById(R.id.calendar) ;

        //calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

         //   @Override
         //   public void onSelectedDayChange(CalendarView view, int year, int month,
          //                                  int dayOfMonth) {
         //       Toast.makeText(getContext(), ""+dayOfMonth,Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub
//
         //   }
        //});
        mlistView = (ListView)view.findViewById(R.id.listview);
        mdatas = new ArrayList<data>();
        mlistView.setAdapter(mdataAdaoter);

        String list;
        Bundle bundle = new Bundle();  //保存用のバンドル
        Map<String, ?> prefKV = getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).getAll();
        Set<String> keys = prefKV.keySet();
        for(String key : keys){
            Object value = prefKV.get(key);
            if(value instanceof String){
                bundle.putString(key, (String) value);
            }else if(value instanceof Integer){
                // …略
            }
        }

        String stringList = bundle.getString("list");  //key名が"list"のものを取り出す
        mdatas = new ArrayList<data>();
        try {
            JSONArray array = new JSONArray(stringList);
            for(int i = 0, length = array.length(); i < length; i++){
                mdatas.add(array.optdata(i));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }


    //@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getContext(), DataActivity.class);
        // インテントにセット
        intent.putExtra("position", position);
        // Activity をスイッチする
        startActivity(intent);
    }




}
