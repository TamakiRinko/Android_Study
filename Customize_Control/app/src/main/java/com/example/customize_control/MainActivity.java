package com.example.customize_control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button testViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init_View();

    }

    private void Init_View() {
        testViewButton = findViewById(R.id.test_view_button);
        testViewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.test_view_button:     //点击test_view_button时
                intent = new Intent(MainActivity.this, TestRedButtonActivity.class);
                startActivity(intent);
                break;
        }
    }
}
