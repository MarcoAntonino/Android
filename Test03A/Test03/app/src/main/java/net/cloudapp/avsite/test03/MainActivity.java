package net.cloudapp.avsite.test03;

import android.content.Intent;
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

    public void ExecuteSum(View pippo)
    {
        EditText num1=(EditText)findViewById(R.id.txtNum1);
        EditText num2=(EditText)findViewById(R.id.txtNum2);
        int n1=Integer.parseInt(num1.getText().toString());
        int n2=Integer.parseInt(num2.getText().toString());

        int res=n1+n2;
        //TextView result=(TextView)findViewById(R.id.txtResult);
        //result.setText(Integer.toString(res));

        Intent i=new Intent(this,ResultActivity.class);
        i.putExtra("result",Integer.toString(res));
        startActivity(i);
    }
}
