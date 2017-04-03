package com.example.antoninom.test03;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ExecuteSum(View button)
    {
        EditText num1=(EditText)findViewById(R.id.txtNum1);
        /*
        R. È una classe generata dal compilatore ed è la classe delle resource. Tutte le resource
        vengono messe nella classe R
        è un identificativo (EditText) è un cast
         */
        EditText num2=(EditText)findViewById(R.id.txtNum2); //riferimento alla textbox

        int n1= Integer.parseInt(num1.getText().toString());// vedi la descrizione di getText per
        int n2= Integer.parseInt(num2.getText().toString());//capire perchè il toString è necessario

        int res=n1+n2;
        //TextView result =(TextView)findViewById(R.id.txtResult);
        //result.setText(Integer.toString(res));
        Intent i= new Intent(this,ResultActivity.class);
        i.putExtra("result",Integer.toString(res));
        startActivity(i);

    }
}
