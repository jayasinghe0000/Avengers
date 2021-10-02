package com.example.avengers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class computing extends AppCompatActivity {

//        ListView listView;
//        private Context context;
//        private DbHandler dbHandler;
//        private List<Worker> workerList;

    private ListView listView;
    private Context context;
    private DbHandler dbHandler;
    private List<Worker> listt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_computing);

        context = this;

        dbHandler = new DbHandler(context);
        listView = findViewById(R.id.listView2);
        listt = new ArrayList<>();
        //  listt = dbHandler.getAllWorkers();
        listt = dbHandler.getAllComputerWorkers("computing");
        //data =dbHandler.getAllWorkers();

        WorkerAddapter workerAddapter = new WorkerAddapter(context,R.layout.single_row,listt);
        listView.setAdapter(workerAddapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(computing.this,Activity2.class);

                String week= String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(computing.this, "next activity", Toast.LENGTH_SHORT).show();

                startActivity(i);
            }
        });









//            listView = findViewById(R.id.listView2);
//
//            context = this;
//            dbHandler = new DbHandler(this);
//
//            workerList= new ArrayList<>();
//
//            workerList = dbHandler.getAllWorkers();
//
//            WorkerAddapter adapter = new WorkerAddapter(context,R.layout.single_row,workerList);
//
//            listView.setAdapter(adapter);
//
//            System.out.println("Computing");






    }
}
