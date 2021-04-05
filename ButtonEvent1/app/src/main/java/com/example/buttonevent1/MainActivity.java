package com.example.buttonevent1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class MyListenerClass implements View.OnClickListener{
        public void onClick(View v){
            Toast.makeText(getApplicationContext(), "버튼이 눌러졌습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        MyListenerClass buttonListener = new MyListenerClass();
        button.setOnClickListener(buttonListener);
    }

}