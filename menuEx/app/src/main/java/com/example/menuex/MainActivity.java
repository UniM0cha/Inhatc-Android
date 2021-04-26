package com.example.menuex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);

        menu.add(0,1,0,"사과");
        menu.add(0,2,0,"포도");
        menu.add(0,3,0,"바나나");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_game:
                newGame();
                return true;
            case R.id.help:
                showHelp();
                return true;
            case 1:
                Toast.makeText(this, "사과", Toast.LENGTH_SHORT).show();
            case 2:
                Toast.makeText(this, "포도", Toast.LENGTH_SHORT).show();
            case 3:
                Toast.makeText(this, "바나나", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void newGame(){
        Toast.makeText(this, "새로운 게임을 시작합니다", Toast.LENGTH_SHORT).show();
    }
    void showHelp(){
        Toast.makeText(this, "도움!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
    }
}