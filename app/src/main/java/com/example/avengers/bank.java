package com.example.avengers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.List;

public class bank extends AppCompatActivity {


    private final String CHANNEL_ID = "Prsonal Notifcation";
    private final int NOTI_ID = 001;

    private EditText holder;
    private EditText name;
    private EditText acc_no;
    private EditText rou_no;
    //private TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private Button btn;
    private Context context;
    private DbHandler dbHandler;
    private int position;
    private List<HireModel> hires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        holder = findViewById(R.id.holder);
        name = findViewById(R.id.name);
        acc_no = findViewById(R.id.accNo);
        rou_no = findViewById(R.id.routNo);
        btn = findViewById(R.id.button_save);
//        t1=findViewById(R.id.bank_topText);
//        t2=findViewById(R.id.bank_holder);
//        t3=findViewById(R.id.bank_nme);
//        t4=findViewById(R.id.bank_accNo);
//        t5=findViewById(R.id.bank_routing);
//        t6=findViewById(R.id.bank_bottonText);
//        t7=findViewById(R.id.acc_no);
//        t8=findViewById(R.id.rout_no);
        context = this;
        dbHandler = new DbHandler(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when user click save store all the inserted data
                String bankHolder = holder.getText().toString();
                String bankName = name.getText().toString();
                int account = Integer.parseInt(acc_no.getText().toString());
                int routing = Integer.parseInt(rou_no.getText().toString());

                //create model type object and put all the inserted vales
                BankModel bankModel = new BankModel(account, routing, bankName, bankHolder);

                dbHandler.addBankDetails(bankModel);
                hires = dbHandler.getAllHireDetail();

                HireModel hire = hires.get(position);

                Intent intent = new Intent(context, displayHire.class);
                //intent.putExtra("hire_id", String.valueOf(hire.getId()));
               // intent.putExtras(getIntent().getExtras());
                intent.putExtras(getIntent().getExtras());
                startActivity(intent);
//                dbHandler.getSingleHire();
//
//                AlertDialog.Builder builder= new AlertDialog.Builder(context);//here canot use this.Because we
//                // need mainactivity.class content.when use this wiill invoke only this method
//                builder.setTitle("Hire Details");
//                builder.setMessage();
//                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //dbHandler.deleteItem(todo.getId());
//                        startActivity(new Intent(context,MainActivity.class));
//                    }
//                });
//                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Intent intent=new Intent(context,EditTodo.class);
////                        intent.putExtra("id",String.valueOf(todo.getId()));
////                        startActivity(intent);
//                    }
//                });
//                builder.show();
//
            }
        });
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_sms_24);
        builder.setContentTitle("Paymnet Sucssfull");
        builder.setContentText("Your advance payment make sucessfully.");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTI_ID,builder.build());

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Persianl Noti";
            String des="Inlcud all";
            int importantace= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importantace);
            notificationChannel.setDescription(des);

            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }


    }

}