package com.example.avengers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class displayHire extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private Button btn1,btn2;
    private DbHandler dbHandler;
    private Context context;
    private List<HireModel> hires;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hire);

        t1=findViewById(R.id.textView1_frnt);
        t2=findViewById(R.id.textView2_frnt);
        t3=findViewById(R.id.textView3_frnt);
        t4=findViewById(R.id.textView4_frnt);
        t5=findViewById(R.id.textView5_frnt);
        t6=findViewById(R.id.textView6_frnt);
        t7=findViewById(R.id.textView7_frnt);
        t8=findViewById(R.id.textView8_frnt);
         btn1=findViewById(R.id.edit);
        btn2=findViewById(R.id.cancal);
        context=this;
        dbHandler=new DbHandler(context);

        hires=dbHandler.getAllHireDetail();
        //catch the values coming from relavent d
        //final String id=getIntent().getStringExtra("hire_id");
       // HireModel hire=dbHandler.getSingleHire(Integer.parseInt(id));//string to int
       // System.out.println(id);
        //t1.setText(hire.getCustomerName());

        Bundle b = getIntent().getExtras();
        String tot = b.getString("total");
        String adv = b.getString("advance");
        String cus=b.getString("cusName");
        String loc=b.getString("cusLoc");
        String date=b.getString("uDate");
        String time=b.getString("data2");
        String worker=b.getString("worker");

        double bal=Double.parseDouble(tot)-Double.parseDouble(adv);

        t1.setText(cus);
        t2.setText(worker);
        t3.setText(loc);
        t4.setText(date);
        t5.setText(time);
        t6.setText(tot);
        t7.setText(adv);
        t8.setText(String.valueOf(bal));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,updateHire.class);
                i.putExtras(getIntent().getExtras());
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,com.example.avengers.disputes.class);
                intent.putExtras(getIntent().getExtras());

                startActivity(intent);
            }
        });






    }
}