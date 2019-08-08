package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init_View();
    }

    private void Init_View() {
        listViewButton = findViewById(R.id.list_view_button);
        listViewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.list_view_button:     //点击listView button时
                intent = new Intent(MainActivity.this, ListViewDemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
