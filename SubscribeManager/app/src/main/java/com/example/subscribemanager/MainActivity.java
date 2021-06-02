package com.example.subscribemanager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Calendar today = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. M. dd.");
    ListView list;
    Vector<String> names = new Vector<>();
    Vector<Integer> images = new Vector<>();
    Vector<Integer> prices = new Vector<>();
    Vector<String> starts = new Vector<>();
    Vector<String> nexts = new Vector<>();
    Button btnStartDate;

    //옵션 메뉴 설정
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addService:
                addService();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //서비스 추가 커스텀 다이얼로그
    private void addService() {
        final Dialog addDialog = new Dialog(this);
        addDialog.setContentView(R.layout.custom_dialog);
        addDialog.show();

    }

    //DatePickerDialog 띄우기
    public void onClickDate(View v){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Toast.makeText(MainActivity.this, "오류 문제로 추가 기능을 구현하지 못했습니다.", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, year +". " + month + ". " + dayOfMonth + ".", Toast.LENGTH_SHORT).show();
                //왜 오류가 나는지 진짜 모르겠음

                btnStartDate.setText(year +". " + month + ". " + dayOfMonth + ".");
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartDate = findViewById(R.id.btnStartDate);

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        TextView title = findViewById(R.id.Title);
        title.setText(today.get(Calendar.MONTH) + 1 + "월의 남은 구독료");

        //값 초기화 부분
        String[] arrNames = {
                "Youtube Premium",
                "Netflix",
                "Apple Music"
        };
        for(String s : arrNames)
            names.add(s);

        int[] arrImages = {
                R.drawable.logo_youtube,
                R.drawable.logo_netflix,
                R.drawable.logo_applemusic
        };
        for(int i : arrImages)
            images.add(i);

        Integer[] arrPrices = {
                8690,
                9500,
                8900
        };
        for(Integer i : arrPrices)
            prices.add(i);

        String[] arrStarts = {
                "2020. 12. 15.",
                "2019. 8. 12.",
                "2015. 1. 28."
        };
        for(String s : arrStarts)
            starts.add(s);

        for(int i = 0; i < starts.size(); i++){
            nexts.add(sdf.format(calculateNextDate(i).getTime()));
        }
        //값 초기화 끝

        //이번달 남은 구독료 계산
        int sum = 0;
        Calendar nextDate = Calendar.getInstance();
        for (int i = 0; i < starts.size(); i++) {
            try {
                nextDate.setTime(sdf.parse(nexts.get(i)));
                if (nextDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                    sum += prices.get(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TextView priceSum = findViewById(R.id.Sum);
            priceSum.setText(NumberFormat.getInstance().format(sum) + "원");
        }
        //계산 끝

//        TextView testText = findViewById(R.id.testText);
    }

    private Calendar calculateNextDate(int position) {
        Calendar nextDay = Calendar.getInstance();
        try {
            nextDay.setTime(sdf.parse(starts.get(position)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while(nextDay.compareTo(today) < 0){
           nextDay.add(Calendar.MONTH, 1);
        }
        return nextDay;
    }

    private class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context){
            super(context, R.layout.listitem, names);
            this.context = context;
        }

        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null,true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.ServiceImage);
            TextView name = (TextView) rowView.findViewById(R.id.ServiceName);
            TextView price = (TextView) rowView.findViewById(R.id.ServicePrice);
            TextView start = (TextView) rowView.findViewById(R.id.ServiceStart);
            TextView next = (TextView) rowView.findViewById(R.id.ServiceNext);
            TextView Dday = (TextView) rowView.findViewById(R.id.ServiceDday);

            imageView.setImageResource(images.get(position));
            name.setText(names.get(position));
            price.setText(NumberFormat.getInstance().format(prices.get(position)) + "원");
            start.setText("가입일 : " + starts.get(position));
            next.setText("갱신일 : " + nexts.get(position));
            Date nextDate = null;
            try {
                nextDate = sdf.parse(nexts.get(position));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long diff = (nextDate.getTime() - today.getTime().getTime()) / (24*60*60*1000);
            diff = Math.abs(diff);
            Dday.setText("D-" + diff);

            return rowView;
        }
    }
}