package com.example.subscribemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Date today = new Date();

    ListView list;
    String[] names = {
            "Youtube Premium",
            "Netflix",
            "Apple Music",
    };

    Integer[] images = {
            R.drawable.logo_youtube,
            R.drawable.logo_netflix,
            R.drawable.logo_applemusic
    };

    String[] prices = {
            "8,690원",
            "9,500원",
            "8,900원"
    };

    String[] starts = {
            "가입일 : 2020. 2. 19.",
            "가입일 : 2019. 8. 12.",
            "가입일 : 2015. 1. 28."
    };

    String[] nextDates = {
            "갱신일 : 2020. 5. 19.",
            "갱신일 : 2020. 6. 12.",
            "갱신일 : 2021. 1. 28."
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
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

            imageView.setImageResource(images[position]);
            name.setText(names[position]);
            price.setText(prices[position]);
            start.setText(starts[position]);
            next.setText(nextDates[position]);
            return rowView;
        }
    }
}