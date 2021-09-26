package com.example.avengers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WorkerAddapter extends ArrayAdapter<Worker> {

    private Context context;
    private int resource;
    List<Worker>listt;

    WorkerAddapter(Context context, int resource, List<Worker>listt){
        super(context,resource,listt);
        this.context=context;
        this.resource = resource;
        this.listt =listt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row  = inflater.inflate(resource,parent,false);

        TextView wname = row.findViewById(R.id.rowName);
        TextView wemail = row.findViewById(R.id.rowEmail);
        TextView wprice = row.findViewById(R.id.rowPrice);
        TextView wcategory = row.findViewById(R.id.rowCategory);

        Worker a = listt.get(position);
        wname.setText(a.getName());
        wemail.setText(a.getEmail());
        wprice.setText(a.getPrice());
        wcategory.setText(a.getCategory());

        return row;
    }

    //    private Context context;
//    private int resource;
//    List<Worker> workerList;
//
//    WorkerAddapter(Context context, int resource, List<Worker> workerList){
//        super(context,resource,workerList);
//        this.context = context;
//        this.resource = resource;
//        this.workerList = workerList;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View row = inflater.inflate(resource,parent,false);
//
////        TextView title = row.findViewById(R.id.title);
////        TextView description = row.findViewById(R.id.description);
////        ImageView imageView = row.findViewById(R.id.onGoing);
//        TextView name = row.findViewById(R.id.name);
//        TextView email = row.findViewById(R.id.email);
//        TextView price = row.findViewById(R.id.price);
//        TextView category = row.findViewById(R.id.category);
//        ImageView imageView = row.findViewById(R.id.image);
//
//
//        // todos [obj1,obj2,obj3]
//        Worker list = workerList.get(position);
//        name.setText(list.getName());
//        email.setText(list.getEmail());
//        price.setText(list.getPrice());
//        category.setText(list.getCategory());
//        imageView.setVisibility(row.getVisibility());
//
//        return row;
//    }


}
