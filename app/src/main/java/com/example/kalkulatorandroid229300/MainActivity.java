package com.example.kalkulatorandroid229300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black_background));

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            calcData = (Calculator) extras.getSerializable("calcData");
        }

    }

    public void openSimpleCalculator(View view) {
        Log.i("Button down", "simple");
        Intent intent = new Intent(getBaseContext(), SimpleCalculatorActivity.class);
        intent.putExtra("calculator", "SIMPLE");
        if(calcData != null)
            intent.putExtra("calcData", calcData);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void openAdvancedCalculator(View view) {
        Log.i("Button down", "advanced");
        Intent intent = new Intent(getBaseContext(), CalcActivity.class);
        intent.putExtra("calculator", "ADVANCED");
        if(calcData != null)
            intent.putExtra("calcData", calcData);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void openAboutPage(View view) {
        Intent intent = new Intent(getBaseContext(), AboutActivity.class);
        startActivity(intent);
    }

    public void closeApplication(View view){
        this.finishAffinity();
    }
}