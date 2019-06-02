package com.adrianjlane.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.forecastList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void getCurrTemp(View view) {

        String city = ((EditText) findViewById(R.id.cityInput)).getText().toString();

        if (city.length() > 0) {
            TextView tvCurrTemp = findViewById(R.id.currTemp);
            String message = "Please Wait...";
            tvCurrTemp.setText(message);
            GetCurrTempTask getCurrTempTask = new GetCurrTempTask(this);
            getCurrTempTask.execute(city);
        }
    }

    public void getForecast(View view) {

        String city = ((EditText) findViewById(R.id.cityInput)).getText().toString();

        if (city.length() > 0) {
            // TODO notify the user of pending action
            GetForecastTask getForecastTask = new GetForecastTask(this);
            getForecastTask.execute(city);
        }
    }
}
