package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_result, tv_solution;

    MaterialButton btnC, btn_opn_bracate, btn_close_bracate, btn_divide, btn_multiply, btn_add, btn_substrac, btn_equal, btn_ac, btn_point,
            btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
        tv_solution = findViewById(R.id.tv_solution);

        assignIds(btnC, R.id.btn_c);
        assignIds(btn_ac, R.id.btn_ac);

        assignIds(btn_opn_bracate, R.id.btn_open_bracate);
        assignIds(btn_close_bracate, R.id.btn_close_bracate);

        assignIds(btn_divide, R.id.btn_division);
        assignIds(btn_multiply, R.id.btn_multiply);
        assignIds(btn_add, R.id.btn_addition);
        assignIds(btn_substrac, R.id.btn_substrac);
        assignIds(btn_equal, R.id.btn_equal);

        assignIds(btn_point, R.id.btn_point);
        assignIds(btn_1, R.id.btn_1);
        assignIds(btn_2, R.id.btn_2);
        assignIds(btn_3, R.id.btn_3);
        assignIds(btn_4, R.id.btn_4);
        assignIds(btn_5, R.id.btn_5);
        assignIds(btn_6, R.id.btn_6);
        assignIds(btn_7, R.id.btn_7);
        assignIds(btn_8, R.id.btn_8);
        assignIds(btn_9, R.id.btn_9);
        assignIds(btn_0, R.id.btn_0);

    }


    void assignIds(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton materialButton = (MaterialButton) v;
        String getBtnTxt = materialButton.getText().toString();
//        tv_solution.setText(getBtnTxt);
        String dataToCalculate = tv_solution.getText().toString();

        if (getBtnTxt.equals("AC")) {
            tv_solution.setText("");
            tv_result.setText("");
            return;
        }

        if (getBtnTxt.equals("=")) {
            tv_solution.setText(tv_result.getText());
            return;
        }

        if (getBtnTxt.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {

            dataToCalculate = dataToCalculate + getBtnTxt;

        }

        tv_solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);


        if (!finalResult.equals("error")){
            tv_result.setText(finalResult);
        }


    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);

            Scriptable scriptable = context.initStandardObjects();

           String finalResult =  context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0", "");
           }

           return finalResult;
        } catch (Exception e){
            return "error";
        }
    }

}