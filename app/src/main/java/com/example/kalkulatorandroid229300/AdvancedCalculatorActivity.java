package com.example.kalkulatorandroid229300;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AdvancedCalculatorActivity extends AppCompatActivity {

    private TextView inputText, outputText;
    private CalculatorLogic calc = new CalculatorLogic(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black_background));

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
                setContentView(R.layout.advanced_mode);
        }

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        if(extras != null){

            if(extras.getSerializable("calcData") != null){
                CalculatorLogic tempCalc = (CalculatorLogic) extras.getSerializable("calcData");
                if(tempCalc != null){
                    calc = tempCalc;
                    updateTextViews(calc, inputText, outputText);
                }
            }
        }

        if(savedInstanceState != null){
            calc = (CalculatorLogic) savedInstanceState.getSerializable("calcData");
            updateTextViews(calc, inputText, outputText);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("calcData", calc);
    }

    protected void updateTextViews(CalculatorLogic calc, TextView inputText, TextView outputText){
        String inputString = "";
        if(Objects.equals(calc.getOperation(), "")){
            inputString = calc.getNumber(0).toString();
        } else {
            inputString = calc.getNumber(0).toString();
            inputString += calc.getOperation();
            if(!calc.getNumber(1).isEmpty())
                inputString += calc.getNumber(1).toString();
        }

        inputText.setText(inputString);
        outputText.setText(getResult());
    }

    protected String getResult(){
        try{
            return calc.getResult();
        } catch (IllegalArgumentException e){
            calc.getNumber(0).setError(true);
            return getResources().getString(R.string.error);
        } catch (ArithmeticException e){
            return getResources().getString(R.string.divisionByZero);
        }catch (OutOfMemoryError e){
            return getResources().getString(R.string.tooBig);
        }
    }

    protected void addDigit(String digit){
        Log.i("New digit", digit);

        calc.addDigit(digit);
        updateTextViews(calc, inputText, outputText);
    }

    protected void setOperation(String operation){
        Log.i("Button down", operation);

        calc.setOperation(operation);
        updateTextViews(calc, inputText, outputText);
    }

    void addFunction(String func){
        Log.i("Button down", func);

        calc.getCurrentNumber().setFunction(func);
        updateTextViews(calc, inputText, outputText);
    }

    public void useClearAllButton(View view){
        calc.clearAll();
        updateTextViews(calc, inputText, outputText);
    }

    public void useClearDigitButton(View view){
        Log.i("Button down", "Clear");

        if(calc.getCurrentNumber().isEmpty()){
            calc.setOperation("");
        } else {
            calc.getCurrentNumber().clearDigit();
        }

        updateTextViews(calc, inputText, outputText);
    }

    public void useChangeSignButton(View view){
        Log.i("Button down", "signChange");

        calc.getCurrentNumber().signChange();
        updateTextViews(calc, inputText, outputText);
    }

    public void usePointButton(View view){
        Log.i("Button down", "Point");

        calc.getCurrentNumber().addPoint();
        updateTextViews(calc, inputText, outputText);
    }

    public void useEqualsButton(View view){
        calc.getNumber(0).setNumber(getResult());
        calc.getNumber(1).reset();
        calc.setOperation("");

        updateTextViews(calc, inputText, outputText);
    }

    public void useProcentButton(View view) {
        addFunction("%");
    }

    public void usePlusButton(View view) {
        setOperation("+");
    }

    public void useMinusButton(View view) {
        setOperation("-");
    }

    public void useMultiplyButton(View view) {
        setOperation("×");
    }

    public void useDivideButton(View view) {
        setOperation("÷");
    }

    public void useZeroButton(View view) {
        addDigit("0");
    }

    public void useOneButton(View view) {
        addDigit("1");
    }

    public void useTwoButton(View view) {
        addDigit("2");
    }

    public void useThreeButton(View view) {
        addDigit("3");
    }

    public void useFourButton(View view) {
        addDigit("4");
    }

    public void useFiveButton(View view) {
        addDigit("5");
    }

    public void useSixButton(View view) {
        addDigit("6");
    }

    public void useSevenButton(View view) {
        addDigit("7");
    }

    public void useEightButton(View view) {
        addDigit("8");
    }

    public void useNineButton(View view) {
        addDigit("9");
    }


    public void useSinusButton(View view) {
        addFunction("SIN");
    }

    public void useCosinusButton(View view) {
        addFunction("COS");
    }

    public void useTangensButton(View view) {
        addFunction("TAN");
    }

    public void useNaturalLogButton(View view) {
        addFunction("LN");
    }

    public void useLogarithmButton(View view) {
        addFunction("LOG");
    }

    public void useSqrtButton(View view) {
        addFunction("√");
    }

    public void useSquarePowerButton(View view) {
        calc.setOperation("^");
        calc.getNumber(1).setNumber("2");

        updateTextViews(calc, inputText, outputText);
    }

    public void usePowerButton(View view) {
        setOperation("^");
    }

    public void usePiButton(View view) {
        calc.getCurrentNumber().setNumber(3.14159265d);
        updateTextViews(calc, inputText, outputText);
    }

    public void openHomePage(View view) {
        Log.i("Button down", "Back to home");
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("calcData", calc);
        startActivity(intent);
    }
}