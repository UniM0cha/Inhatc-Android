package com.example.edittexttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText eText;
        Button btn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = (EditText) findViewById(R.id.edittext);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = eText.getText().toString();
                Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG).show();
            }
        });
    }
}