package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView expression, solution;
    MaterialButton one, two, three, four, five, six, seven, eight, nine, zero, add, multiply, divide, subtract, decimal, equals, clear, ac, openBracket, closeBracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        expression = findViewById(R.id.expression);
        solution = findViewById(R.id.solution);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        clear = findViewById(R.id.clear);
        ac = findViewById(R.id.ac);
        openBracket = findViewById(R.id.openBracket);
        closeBracket = findViewById(R.id.closeBracket);

        add = findViewById(R.id.add);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        subtract = findViewById(R.id.subtract);
        decimal = findViewById(R.id.decimal);
        equals = findViewById(R.id.equals);


    }


    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = expression.getText().toString();

        if(buttonText.equals("AC")){
            expression.setText("");
            solution.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            expression.setText(solution.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(dataToCalculate.length()>1)
            {
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }
            else {
                expression.setText("0");
                solution.setText("0");
            }

        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        expression.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            solution.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return " ";
        }
    }
}