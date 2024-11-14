package com.example.hikemanagementapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME ="hiking_db";
    private static final String TABLE_NAME ="user";
    public static final String USER_ID ="_id";//primary key
    public static final String USER_NAME ="name";
    public static final String USER_EMAIL ="email";
    public static final String USER_GENDER ="gender";
    public static final String USER_LOCATION ="location";
    public static final String USER_DOB="dob";
    public static final String USER_PARKING ="parking";
    public static final String USER_LENGTH ="length";
    public static final String USER_LEVEL ="level";
    public static final String USER_DESCRIPTION="description";
    private static final String QUALIFICATION_TABLE_NAME = "observation";
    public static final String Q_ID ="_id";
    public static final String Q_NAME = "name";
    public static final String Q_Time ="time";
    public static final String Q_TTime ="ttime";
    public static final String Q_Comment ="comment";
    public static final String Q_USED_ID ="used_id";//foreign key
    public SQLiteDatabase database;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        database = getWritableDatabase();
    }//end of Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableCreate1 = "create table "+TABLE_NAME+"("+USER_ID+" integer primary key autoincrement,"
                              +USER_NAME+" text, "
                              +USER_EMAIL+" text,"
                              +USER_GENDER+" text,"
                              +USER_LOCATION+" text,"
                              +USER_DOB+" text,"
                              +USER_PARKING+" text,"
                              +USER_LENGTH+" text,"
                              +USER_LEVEL+" text, "
                              +USER_DESCRIPTION+" text)";
        db.execSQL(tableCreate1);

        //observation table create
        String tableCreate2 = "create table "+QUALIFICATION_TABLE_NAME+"("
                +Q_ID+" integer primary key autoincrement,"
                +Q_NAME+" text, "
                +Q_Time+" text,"
                +Q_TTime+" text,"
                +Q_Comment+" text,"
                +Q_USED_ID+" integer,"
                +" FOREIGN KEY ("+Q_USED_ID+")"
                +" REFERENCES "+TABLE_NAME+"("+USER_ID+")"
                +" ON DELETE CASCADE "+
                ")";
        db.execSQL(tableCreate2);
    }//end of onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable1 = "drop table if exists "+TABLE_NAME;
        db.execSQL(dropTable1);
        String dropTable2 = "drop table if exists "+QUALIFICATION_TABLE_NAME;
        db.execSQL(dropTable2);
        onCreate(db);
    }//end of onUpgrade

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }//end of onOpen

    public long saveUser(User user){
        ContentValues values = new ContentValues();
        values.put(USER_NAME,user.getName());//name
        values.put(USER_EMAIL,user.getEmail());//email
        values.put(USER_GENDER,user.getGender());//gender
        values.put(USER_LOCATION,user.getLocation());//location
        values.put(USER_DOB,user.getDob());//DOB
        values.put(USER_PARKING,user.getParking());//parking
        values.put(USER_LENGTH,user.getLength());//length
        values.put(USER_LEVEL,user.getLevel());//level
        values.put(USER_DESCRIPTION,user.getDescription());//description
        long user_id = database.insertOrThrow(TABLE_NAME,null,values);
        return user_id;
    }//end of saveUser

    public long addQualification(Qualification qualification){
        ContentValues values = new ContentValues();
        values.put(Q_NAME,qualification.getName());//name
        values.put(Q_Time,qualification.getTime());//time
        values.put(Q_TTime,qualification.getTtime());
        values.put(Q_Comment,qualification.getComment());//comment
        values.put(Q_USED_ID,qualification.getUser_id());//user_id(foreign key)

        long q_id = database.insertOrThrow(QUALIFICATION_TABLE_NAME,null,values);
        return q_id;
    }//end of addQualification

    public ArrayList<String> getAllUsers(){
       database = getReadableDatabase();
       Cursor results = database.query(TABLE_NAME,new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_GENDER,USER_LOCATION,USER_DOB,USER_PARKING,USER_LENGTH,USER_LEVEL,USER_DESCRIPTION},
               null,null,null,null,USER_NAME);
        ArrayList<String> user_array =new ArrayList<>();
        results.moveToFirst();
        while(!results.isAfterLast()){
           int id             = results.getInt(0);
           String name        = results.getString(1);
           String email       = results.getString(2);
           String gender      = results.getString(3);
           String location    = results.getString(4);
           String dob         = results.getString(5);
           String parking     = results.getString(6);
           String length      = results.getString(7);
           String level       = results.getString(8);
           String description = results.getString(9);
           String record  = id+": "+name+", "+email+", "+gender+", "+location+", "+dob+", "+parking+", "+length+", "+level+", "+description+"";
           user_array.add(record);
           results.moveToNext();//next row
       }//end of loop
        return user_array;
   }//end of getAllUsers

    public ArrayList<Qualification> getAllQualifications(int user_id){

        database = getReadableDatabase();

        Cursor results = database.query(QUALIFICATION_TABLE_NAME,
                         new String[]{Q_ID,Q_NAME,Q_Time,Q_TTime,Q_Comment,Q_USED_ID},
                 Q_USED_ID+"=?",new String[]{String.valueOf(user_id)},
                 null,null,null);

        ArrayList<Qualification> qualification_array =new ArrayList<>();
        results.moveToFirst();

        while(!results.isAfterLast()){
            int id             = results.getInt(0);
            String name        = results.getString(1);
            String time        = results.getString(2);
            String ttime        = results.getString(3);
            String comment     = results.getString(4);
            int uid            = results.getInt(5);

            Qualification q    = new Qualification(id,name,time,ttime,comment);
            qualification_array.add(q); // object array
            results.moveToNext();//next row
        }//end of loop
        return qualification_array;
    }//end of getAllQualification

    public void deleteAllUser(){
        database.delete(TABLE_NAME,null,null);
    }//end of deleteAllUser
    public void deleteUser(int user_id){
       database.delete(TABLE_NAME,USER_ID +"=?",new String[]{String.valueOf(user_id)});
    }//end of deleteUser

    public void deleteQualification(int q_id){
        database.delete(QUALIFICATION_TABLE_NAME,Q_ID +"=?",new String[]{String.valueOf(q_id)});
    }//end of deleteQualification

    public void updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(USER_NAME,user.getName());
        values.put(USER_EMAIL,user.getEmail());
        values.put(USER_GENDER,user.getGender());
        values.put(USER_LOCATION,user.getLocation());
        values.put(USER_DOB,user.getDob());
        values.put(USER_PARKING,user.getParking());
        values.put(USER_LENGTH,user.getLength());
        values.put(USER_LEVEL,user.getLevel());
        values.put(USER_DESCRIPTION,user.getDescription());
        database.update(TABLE_NAME,values,USER_ID+"=?",
                new String[]{String.valueOf(user.getId())});
    }//end of update user

    public void updateQualification(Qualification qualification){
        ContentValues values = new ContentValues();
        values.put(Q_NAME,qualification.getName());
        values.put(Q_Time,qualification.getTime());
        values.put(Q_Comment,qualification.getComment());
        //values.put(Q_USED_ID,qualification.getUser_id());
        database.update(QUALIFICATION_TABLE_NAME,values,Q_ID+"=?",
                new String[]{String.valueOf(qualification.get_id())});
    }//end of update qualification

    public ArrayList<String> getUser(String key){
        database = getReadableDatabase();

        Cursor results = database.query(TABLE_NAME,new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_GENDER,USER_LOCATION,USER_DOB,USER_PARKING,USER_LENGTH,USER_LEVEL,USER_DESCRIPTION},
                 USER_NAME+" LIKE ? OR "+USER_EMAIL+" LIKE ? OR "+USER_LOCATION+" LIKE ? OR "+USER_LENGTH+" LIKE ? OR "+USER_DOB+" LIKE ? ",
                         new String[]{"%"+key+"%","%"+key+"%","%"+key+"%","%"+key+"%","%"+key+"%"},
                 null,null,null);

        ArrayList<String> user_array =new ArrayList<>();
        results.moveToFirst();
        while(!results.isAfterLast()){
            int id             = results.getInt(0);
            String name        = results.getString(1);
            String email       = results.getString(2);
            String gender      = results.getString(3);
            String location    = results.getString(4);
            String dob         = results.getString(5);
            String parking     = results.getString(6);
            String length      = results.getString(7);
            String level       = results.getString(8);
            String description = results.getString(9);
            String record  = id+": "+name+", "+email+", "+gender+", "+location+", "+dob+", "+parking+", "+length+", "+level+", "+description+"";
            user_array.add(record);
            results.moveToNext();//next row
        }//end of loop
        return user_array;
    }//end of getUser(searchName)

    public ArrayList<String> getAdvanceSearch(String aName, String aLocation, String aDate){
        database = getReadableDatabase();

        Cursor results = database.query(
                TABLE_NAME,
                new String[]{
                        USER_ID,USER_NAME,USER_EMAIL,USER_GENDER,USER_LOCATION,USER_DOB,USER_PARKING,USER_LENGTH,USER_LEVEL,USER_DESCRIPTION
                },
                USER_NAME+" LIKE ? AND "+USER_LOCATION+" LIKE ? AND "+USER_DOB+" LIKE ?",
                new String[]{"%" + aName+ "%" , "%"+aLocation+"%","%"+aDate+"%"},
                null,null,null);

        ArrayList<String> user_array =new ArrayList<>();
        results.moveToFirst();
        while(!results.isAfterLast()){
            int id             = results.getInt(0);
            String name        = results.getString(1);
            String email       = results.getString(2);
            String gender      = results.getString(3);
            String location    = results.getString(4);
            String dob         = results.getString(5);
            String parking     = results.getString(6);
            String length      = results.getString(7);
            String level       = results.getString(8);
            String description = results.getString(9);
            String record  = id+": "+name+", "+email+", "+gender+", "+location+", "+dob+", "+parking+", "+length+", "+level+", "+description+"";
            user_array.add(record);
            results.moveToNext();//next row
        }//end of loop
        return user_array;
    }//end of getUser(searchName)

    public Cursor fetchAllUsers(){
        database          = getReadableDatabase();
        String[] columns  = new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_GENDER,USER_LOCATION,USER_DOB,USER_PARKING,USER_LENGTH,USER_LEVEL,USER_DESCRIPTION};
        Cursor cursor  = database.query(TABLE_NAME,columns,null,null,null,null,null);
        if (cursor!=null) cursor.moveToFirst();
        return cursor;
    }//end of fetchAllUsers (Cursor Adapter View)

}//end of DatabaseHelper class
