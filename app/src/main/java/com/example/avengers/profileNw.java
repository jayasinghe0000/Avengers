package com.example.avengers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profileNw extends AppCompatActivity {

    public static final String EXTRA_MESSAGE2="com.example.avengers";
    private TextView t1,t2,t3;
    private DbHandler dbHandler;
    private Button btn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_nw);
        t1=findViewById(R.id.tx1);
        t2=findViewById(R.id.tx2);
        t3=findViewById(R.id.textView3);
        btn=findViewById(R.id.btndeleteacc);
        context=this;
        dbHandler=new DbHandler(context);

        //catch the values coming from relavent d
        final String worker_id=getIntent().getStringExtra("worker_id");

        //System.out.println(worker_id);
        WorkerModel workerModel=dbHandler.getSingleWorker(Integer.parseInt(worker_id));//string to int
        t1.setText(workerModel.getWorkerName());
        t2.setText(workerModel.getCategory());
        t3.setText(String.valueOf(workerModel.getSalary()));



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HireDetail.class);
                intent.putExtra("data1",String.valueOf(workerModel.getSalary()));
                intent.putExtra("worker",workerModel.getWorkerName());


                startActivity(intent);

            }
        });

    }
}