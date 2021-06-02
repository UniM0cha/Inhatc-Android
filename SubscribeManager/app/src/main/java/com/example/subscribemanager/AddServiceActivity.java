package com.example.subscribemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddServiceActivity extends AppCompatActivity {

    Button btnStartDate;
    Spinner spinServiceName;
    EditText textServicePrice;
    int selYear, selMonth, selDay;
//    ArrayList<String> names = new ArrayList<>();
//    ArrayList<Integer> images = new ArrayList<>();
//    ArrayList<Integer> prices = new ArrayList<>();
//    ArrayList<String> starts = new ArrayList<>();
//    ArrayList<String> nexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_service);
        super.onCreate(savedInstanceState);

//        Intent intent = getIntent();
//        names = intent.getStringArrayListExtra("names");
//        images = intent.getIntegerArrayListExtra("images");
//        prices = intent.getIntegerArrayListExtra("prices");
//        starts = intent.getStringArrayListExtra("starts");
//        nexts = intent.getStringArrayListExtra("nexts");

        btnStartDate = findViewById(R.id.btnStartDate);
        spinServiceName = findViewById(R.id.spinServiceName);
        textServicePrice = findViewById(R.id.textServicePrice);
        final Calendar c = Calendar.getInstance();
        selYear = c.get(Calendar.YEAR);
        selMonth = c.get(Calendar.MONTH);
        selDay = c.get(Calendar.DAY_OF_MONTH);
        btnStartDate.setText(selYear +". " + (selMonth+1) + ". " + selDay + ".");
    }

    //날짜 설정 버튼 클릭, datepicker 나오게 함
    public void onClickDate(View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btnStartDate.setText(year + ". " + (month+1) + ". " + dayOfMonth + ".");
            }
        }, selYear, selMonth, selDay);
        datePickerDialog.show();
    }

    public void onClickAdd(View v){
        String serviceName = spinServiceName.getSelectedItem().toString();
        int servicePrice = 0;
        if (textServicePrice.getText().toString().length() == 0) {
            Toast.makeText(this, "구독료를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            servicePrice = Integer.parseInt(textServicePrice.getText().toString());
        }
        String serviceStart = btnStartDate.getText().toString();

        MainActivity main = new MainActivity();
        MainActivity.names.add(serviceName);
        MainActivity.prices.add(servicePrice);
        MainActivity.starts.add(serviceStart);
        MainActivity.nexts.add(sdf.format(calculateNextDate(MainActivity.starts.size()-1).getTime()));
        switch(serviceName){
            case "Youtube Premium":
                MainActivity.images.add(R.drawable.logo_youtube);
                break;
            case "Netflix":
                MainActivity.images.add(R.drawable.logo_netflix);
                break;
            case "Apple Music":
                MainActivity.images.add(R.drawable.logo_applemusic);
                break;
        }
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("names", names);
//        intent.putExtra("images", images);
//        intent.putExtra("prices", prices);
//        intent.putExtra("starts", starts);
//        intent.putExtra("nexts", nexts);

        finish();
    }

    public void onClickCancel(View v){
        finish();
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. M. d.");
    Calendar today = Calendar.getInstance();
    private Calendar calculateNextDate(int position) {
        Calendar nextDay = Calendar.getInstance();
        try {
            nextDay.setTime(sdf.parse(MainActivity.starts.get(position)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while(nextDay.compareTo(today) < 0){
            nextDay.add(Calendar.MONTH, 1);
        }
        return nextDay;
    }
}