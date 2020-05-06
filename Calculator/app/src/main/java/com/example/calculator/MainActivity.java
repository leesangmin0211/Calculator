package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String CLEAR_INPUT_TEXT = "0";

    boolean isFirstInput = true;
    int resultNumber = 0;
    char operator = '+';

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.result_text);
    }

    public void buttonClick(View view) {

        switch (view.getId()) {

            case R.id.all_clear_button:
                resultNumber = 0;
                operator = '+';
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.clear_entry_button:
                setClearText(CLEAR_INPUT_TEXT);
                break;

            case R.id.back_space_button:
                if (resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length() - 1);
                    resultText.setText(subString);
                } else {
                    setClearText(CLEAR_INPUT_TEXT);
                }
                break;

            case R.id.decimal_button:
                Log.e("buttonClick", "decimal_button 버튼이 눌렸습니다.");
                break;

        }

    }

    public void setClearText(String clearText) {
        isFirstInput = true;
        resultText.setTextColor(0xFF666666);
        resultText.setText(clearText);
    }

    public void numButtonClick(View view) {

        Button getButton = findViewById(view.getId());

        if (isFirstInput) {
            resultText.setTextColor(0xFF000000);
            resultText.setText(getButton.getText().toString());
            isFirstInput = false;
        } else if (resultText.getText().toString().equals("0")) {
            Toast.makeText(getApplicationContext(), "0으로 시작하는 정수는 없습니다.", Toast.LENGTH_SHORT).show();
            setClearText(CLEAR_INPUT_TEXT);
        } else {
            resultText.append(getButton.getText().toString());
        }

    }

    public void operatorClick(View view) {

        Button getButton = findViewById(view.getId());

        if (view.getId() == R.id.result_button) {
            if (isFirstInput){
                resultNumber=0;
                operator = '+';
                setClearText("0");
            }else{
                resultNumber = intCal(resultNumber, Integer.parseInt(resultText.getText().toString()), operator);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }

        } else {
            if(isFirstInput){
                operator = getButton.getText().toString().charAt(0);
            }else{
                int lastNum = Integer.parseInt(resultText.getText().toString());
                resultNumber = intCal(resultNumber, lastNum, operator);
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
            }

        }

    }

    public int intCal(int result, int lastNum, char operator) {
        if (operator == '+') {
            result += lastNum;
        } else if (operator == '-') {
            result -= lastNum;
        } else if (operator == '*') {
            result *= lastNum;
        } else if (operator == '/') {
            result /= lastNum;
        }
        return result;
    }
}
