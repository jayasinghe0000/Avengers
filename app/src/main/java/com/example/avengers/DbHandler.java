package com.example.avengers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//public class DbHandler extends SQLiteOpenHelper {
//
////    private static final int VERSION =1;
////    private static final String DB_NAME= "Worker";
////    private static final String TABLE_NAME ="Worker";
////
////    private static final String ID = "id ";
////    private static final String NAME = "name";
////    private static final String EMAIl = "email";
////    private static final String PRICE ="price";
////    private static final String CATEGORY = "category";
//
//    //description about database
//    private static final int VERSION =1;
//    private static final String DB_NAME ="w"; //database name
//    private static final String TABLE_NAME ="w"; // data  table name
//
//    //create column
//    private static final String ID = "id";
//    private static final String NAME ="name";
//    private static final String EMAIl ="email";
//    private static final String PRICE="price";
//    private static final String CATEGORY ="category";
//
//
//
//    //Default constructor
//    public DbHandler(@Nullable Context context) {
//        super(context,DB_NAME,null,VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
//                "("
//                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +NAME + " TEXT,"
//                +EMAIl+ " TEXT,"
//                +PRICE+ " TEXT,"
//                +CATEGORY+" TEXT" +
//                ");";
//
//        /*
//            CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
//            started TEXT,finished TEXT); */
//
//        db.execSQL(TABLE_CREATE_QUERY);
//        System.out.println("Susscess");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
//
//        //Drop older table if existed
//        db.execSQL(DROP_TABLE_QUERY);
//
//        //create table again
//        onCreate(db);
//    }
//
//
//    public void addWorker(Worker workerAdd) {
//
//        SQLiteDatabase  sqLiteDatabase = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(NAME, workerAdd.getName());
//        contentValues.put(EMAIl, workerAdd.getEmail());
//        contentValues.put(PRICE, workerAdd.getPrice());
//        contentValues.put(CATEGORY,workerAdd.getCategory());
//
//       //save to table
//        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
//        System.out.println("Susscess2");
//        // close database
//        sqLiteDatabase.close();
//
//
//    }
//}

public class DbHandler extends SQLiteOpenHelper {

    //description about database
    private static final int VERSION =1;
    private static final String DB_NAME ="workx"; //database name
    private static final String TABLE_NAME ="workxx"; // data  table name

    //create column
    private static final String ID = "id";
    private static final String NAME ="name";
    private static final String EMAILW ="emailw";
    private static final String PRICE ="price";
    private static final String CATEGORY ="category";


    //Default constructor
    public DbHandler(@Nullable Context context) {
        super(context,DB_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +NAME + " TEXT,"
                +EMAILW+ " TEXT,"
                +PRICE+ " TEXT,"
                +CATEGORY+" TEXT" +
                ");";

        /*
            CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
            started TEXT,finished TEXT); */

        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

        //Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);

        //create table again
        onCreate(db);

    }

    //Insert data to the database
//    public void addToDo(ToDoModelClass todo){
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues(); //setting data structure and insert to the data datadase
//
//        contentValues.put(TITLE,todo.getTitle());
//        contentValues.put(DESCRIPTION,todo.getDescription());
//        contentValues.put(STARTED, todo.getStarted());
//        contentValues.put(FINISHED,todo.getFinished());
//
//        //save data
//        //sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
//         sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
//        //closeTable
//        sqLiteDatabase.close();
//    }


    // Add a single worker

    public void addWorker(Worker workerAdd){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME,workerAdd.getName());
        contentValues.put(EMAILW, workerAdd.getEmail());
        contentValues.put(PRICE,workerAdd.getPrice());
        contentValues.put(CATEGORY,workerAdd.getCategory());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
        System.out.println("Success");
    }


//    //count the table record
//    public int countToDo(){
//        SQLiteDatabase db = getReadableDatabase();
//
//        String query = " SELECT * FROM " + TABLE_NAME;
//
//        Cursor cursor  = db.rawQuery(query,null);
//        int x = cursor.getCount();
//         System.out.println(x);
//         return 0;
//
//    }


//    public List<Worker> getAllWorkers(){
//
//        List<Worker> workerList = new ArrayList();
//
//        SQLiteDatabase db = getReadableDatabase();
//
//        String query = "SELECT * FROM "+TABLE_NAME;
//
//        Cursor cursor = db.rawQuery(query,null);
//
//        if(cursor.moveToFirst()){
//            do {
//                // Create new object
//                Worker work = new Worker();
//                // mmgby6hh
//                work.setId(cursor.getInt(0));
//                work.setName(cursor.getString(1));
//                work.setEmail(cursor.getString(2));
//                work.setPrice(cursor.getString(3));
//                work.setCategory(cursor.getString(4));
//
//                //toDos [obj,objs,asas,asa]
//               workerList.add(work);
//            }while (cursor.moveToNext());
//        }
//        return workerList;
//    }

    //Display list


   public List<Worker> getAllWorkers(String computing){
    //public List<Worker> getAllWorkers(){
        List <Worker> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

      //  String query ="SELECT * FROM " + TABLE_NAME;

       // Cursor cursor = db.rawQuery(query,null);
      Cursor cursor = db.query(TABLE_NAME,new String[]{ID,NAME,EMAILW,PRICE,CATEGORY},CATEGORY+ "=?", new String[]{computing},null,null,null);

        if(cursor.moveToFirst()){
            do{
                Worker w = new Worker();

                w.setId(cursor.getInt(0));
                w.setName(cursor.getString(1));
                w.setEmail(cursor.getString(2));
                w.setPrice(cursor.getString(3));
                w.setCategory(cursor.getString(4));

                data.add(w);

            }while(cursor.moveToNext());
        }
        return data;
    }


}

