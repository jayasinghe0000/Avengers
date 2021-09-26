package com.example.avengers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class updateHire extends AppCompatActivity {

    private TextInputLayout t1,t2,t3,t4,t5,t6,t7;
    private DbHandler dbHandler;
    private Button btn;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hire);

        t1=findViewById(R.id.update_name);
        t2=findViewById(R.id.update_email);
        t3=findViewById(R.id.update_phone);
        t4=findViewById(R.id.update_location);
        t5=findViewById(R.id.update_description);
        t6=findViewById(R.id.update_duration);
        t7=findViewById(R.id.update_date);
        btn=findViewById(R.id.update_btn);


        context=this;
        dbHandler=new DbHandler(context);
        Bundle b = getIntent().getExtras();
        String data1 = b.getString("cusName");
        String data2 = b.getString("email");
        String data3 = b.getString("phone");
        String data4 = b.getString("cusLoc");
        String data5 = b.getString("des");
        String data6 = b.getString("data2");
        String data7 = b.getString("uDate");

        t1.getEditText().setText(data1);
        t2.getEditText().setText(data2);
        t3.getEditText().setText(data3);
        t4.getEditText().setText(data4);
        t5.getEditText().setText(data5);
        t6.getEditText().setText(data6);
        t7.getEditText().setText(data7);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=t1.getEditText().getText().toString();
                String uemail=t2.getEditText().getText().toString();
                String uphone=t3.getEditText().getText().toString();
                String ulocation=t4.getEditText().getText().toString();
                String udes=t5.getEditText().getText().toString();
                String uduration=t6.getEditText().getText().toString();
                String udate=t7.getEditText().getText().toString();
              //String todoDes=editdes.getText().toString();


                //save object with updated values
                HireModel hire=new HireModel(uname,Integer.parseInt(uphone),uemail,ulocation,udate,Integer.parseInt(uduration),udes);
                int state=dbHandler.updateHire(hire);
                System.out.println(state);
                startActivity(new Intent(context,MainActivity.class));
            }
        });




    }
}