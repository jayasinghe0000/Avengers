package com.example.avengers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DbHandler extends SQLiteOpenHelper {

    public static final int VERSION=1;//when change db name/table name this version need to change.
    public static final String DB_NAME="Payment";
    public static final String TABLE_NAME="Bank";
    public static final String TABLE_NAME2="Worker";
    public static final String TABLE_NAME3="Hire";

    //column names
    public static final String BANK_ID= "bank_id";
    public static final String BANK_NAME="bank_name";
    public static final String HOLDER="account_holder";
    public static final String ACC_NO="account_no";
    public static final String ROU_NO="routing_no";

    //column names
    public static final String WORKER_ID= "worker_id";
    public static final String WORKER_NAME="worker_name";
    public static final String SALARY="salary";
    public static final String CATEGORY="category";

    //column names
    public static final String HIRE_ID= "hire_id";
    //public static final String WORKER_HIRE_ID= "worker_hire_id";
    public static final String CUSTOMER_NAME="customer_name";
    public static final String PHONE="phone";
    public static final String EMAIL="email";
    public static final String LOCATION="work_location";
    public static final String DATE="Starting_date";
    public static final String DURATION="Duration";
    public static final String DES="Description";



    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation query
        String TABLE_CREATE_QUERY="CREATE TABLE "+TABLE_NAME+" "+
                " ("
                +BANK_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                +BANK_NAME+ " TEXT,"
                +HOLDER+ " TEXT,"
                +ACC_NO+ " TEXT,"
                +ROU_NO+ " TEXT"+
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE_CREATE_QUERY);

        //table creation query
        String TABLE2_CREATE_QUERY="CREATE TABLE "+TABLE_NAME2+" "+
                " ("
                +WORKER_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                +WORKER_NAME+ " TEXT,"
                +SALARY+ " TEXT,"
                +CATEGORY+ " TEXT "+
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE2_CREATE_QUERY);

        //table creation query
        String TABLE3_CREATE_QUERY="CREATE TABLE "+TABLE_NAME3+" "+
                " ("
                +HIRE_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                //+WORKER_HIRE_ID + "INTEGER,"
                +CUSTOMER_NAME+ " TEXT,"
                +PHONE+ " TEXT,"
                +EMAIL+ " TEXT,"
                +LOCATION+ " TEXT,"
                +DATE+ " TEXT, "
                +DURATION+ " TEXT,"
                +DES+ " TEXT "+
                // +" FOREIGN KEY  ("+WORKER_HIRE_ID+") REFERENCES "+TABLE_NAME2+"("+WORKER_ID+")" +
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE3_CREATE_QUERY);

    }

    //this method automatically updated when change the version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //when change the version drop exists table
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop table if exited
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

        //when change the version drop exists table
        String DROP_TABLE2_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        //drop table if exited
        db.execSQL(DROP_TABLE2_QUERY);
        //create table again
        onCreate(db);

        //when change the version drop exists table
        String DROP_TABLE3_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME3;
        //drop table if exited
        db.execSQL(DROP_TABLE3_QUERY);
        //create table again
        onCreate(db);


    }


    /*insert Data*/

    public void addWorkerDetails(WorkerModel workerModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(WORKER_NAME,workerModel.getWorkerName());
        contentValues.put(SALARY,workerModel.getSalary());
        contentValues.put(CATEGORY,workerModel.getCategory());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }


    public void addBankDetails(BankModel bankModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(BANK_NAME,bankModel.getBank_name());
        contentValues.put(HOLDER,bankModel.getAccount_holder());
        contentValues.put(ACC_NO,bankModel.getAccount_no());
        contentValues.put(ROU_NO,bankModel.getRouting_no());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }

    public void addHireDetails(HireModel hireModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(CUSTOMER_NAME,hireModel.getCustomerName());
        contentValues.put(PHONE,hireModel.getPhone());
        contentValues.put(EMAIL,hireModel.getEmail());
        contentValues.put(LOCATION,hireModel.getLocation());
        contentValues.put(DATE,hireModel.getDate());
        contentValues.put(DURATION,hireModel.getDuration());
        contentValues.put(DES,hireModel.getDescription());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME3,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }

    /*get data*/

    //get all workers
    public List<WorkerModel > getAllWorkers(String c){
        List<WorkerModel> workers=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        //String query=" SELECT * FROM "+TABLE_NAME2 ;
        //Cursor cursor=db.rawQuery(query,null);
        Cursor cursor=db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},CATEGORY+ "=?",new String[]{c},null,null,null);

        //check whether the table has data.go to first raw.if empty return false
        if(cursor.moveToFirst()) {
            do {
                //create empty workerModel object
                WorkerModel workerModel=new WorkerModel();

                //set values
                workerModel.setWorkerId(cursor.getInt(0));
                workerModel.setWorkerName(cursor.getString(1));
                workerModel.setSalary(cursor.getInt(2));
                workerModel.setCategory(cursor.getString(3));


                //add todoModel to the list type object
                workers.add(workerModel);

            } while(cursor.moveToNext());
        }
        return workers;
    }

//    public Cursor getAllData(){
//        SQLiteDatabase db=getWritableDatabase();
//        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLE_NAME2,null);
//        return cursor;
//    }

    //get all hires





    public List<HireModel > getAllHireDetail(){
        List<HireModel> hires=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+TABLE_NAME3 ;
        Cursor cursor=db.rawQuery(query,null);
        //Cursor cursor=db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},CATEGORY+ "=?",new String[]{c},null,null,null);

        //check whether the table has data.go to first raw.if empty return false
        if(cursor.moveToLast()) {
            do {
                //create empty workerModel object
                HireModel hireModel=new HireModel();

                //set values
                hireModel.setId(cursor.getInt(0));
                hireModel.setCustomerName(cursor.getString(1));
                hireModel.setPhone(cursor.getInt(2));
                hireModel.setEmail(cursor.getString(3));
                hireModel.setLocation(cursor.getString(4));
                hireModel.setDate(cursor.getString(5));
                hireModel.setDuration(cursor.getInt(6));
                hireModel.setDescription(cursor.getString(7));

                //add todoModel to the list type object
                hires.add(hireModel);

            } while(cursor.moveToNext());
        }
        return hires;
    }

    //get singleHire
    public HireModel getSingleHire(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(TABLE_NAME3,new String[]{HIRE_ID,CUSTOMER_NAME,PHONE,EMAIL,LOCATION,DATE,DURATION,DES},HIRE_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        //inside this String array need gives only the coloums that need to display
        HireModel hireModel;
        if(cursor != null) {
            cursor.moveToLast();
            hireModel = new HireModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7));
            return hireModel;
        }
        return  null;
    }

    //get single Worker
    public WorkerModel getSingleWorker(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},WORKER_ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        WorkerModel workerModel;
        if(cursor != null) {
            cursor.moveToFirst();
            workerModel = new WorkerModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3));

            return workerModel;
        }
        return  null;
    }


    //delete hire
    public void deleteHire(String email){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME3,EMAIL +"=?",new String[]{email});//convert int id to string
        db.close();
    }



    //Update HireDetail
    public boolean updateHireDetail(String email, String cusname, String phone, String loc,
                                    String des, String dur, String s_date) {

        SQLiteDatabase db= getWritableDatabase();

        //create object is store all inserted data
        ContentValues contentValues =  new ContentValues();

        contentValues.put(EMAIL,email);
        contentValues.put(CUSTOMER_NAME,cusname);
        contentValues.put(PHONE,phone);
        contentValues.put(LOCATION,loc);
        contentValues.put(DES,des);
        contentValues.put(DURATION,dur);
        contentValues.put(DATE,s_date);

//        contentValues.put(CUSTOMER_NAME,hireModel.getCustomerName());
//        contentValues.put(PHONE,hireModel.getPhone());
//        contentValues.put(EMAIL,hireModel.getEmail());
//        contentValues.put(LOCATION,hireModel.getLocation());
//        contentValues.put(DATE,hireModel.getDate());
//        contentValues.put(DURATION,hireModel.getDuration());
//        contentValues.put(DES,hireModel.getDescription());

        db.update(TABLE_NAME3,contentValues,"email = ?", new String[]{email});

        return true;

    }



    // Add a single worker

    public void addWorker(Worker workerAdd){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(WORKER_NAME,workerAdd.getName());
        contentValues.put(EMAIL, workerAdd.getEmail());
        contentValues.put(SALARY,workerAdd.getPrice());
        contentValues.put(CATEGORY,workerAdd.getCategory());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
        System.out.println("Success");
    }

    public List<Worker> getAllComputerWorkers(String computing){
    //public List<Worker> getAllWorkers(){
        List <Worker> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

      //  String query ="SELECT * FROM " + TABLE_NAME;

       // Cursor cursor = db.rawQuery(query,null);
      Cursor cursor = db.query(TABLE_NAME,new String[]{WORKER_ID,WORKER_NAME,EMAIL,SALARY,CATEGORY},CATEGORY+ "=?", new String[]{computing},null,null,null);

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

