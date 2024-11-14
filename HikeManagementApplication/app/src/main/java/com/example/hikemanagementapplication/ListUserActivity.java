package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListUserActivity extends AppCompatActivity {

    ListView userListView;
    DatabaseHelper databaseHelper;
    String [] from = new String[]{DatabaseHelper.USER_ID,DatabaseHelper.USER_NAME,
                     DatabaseHelper.USER_EMAIL,DatabaseHelper.USER_GENDER,
                     DatabaseHelper.USER_LOCATION,DatabaseHelper.USER_DOB,
                     DatabaseHelper.USER_PARKING,DatabaseHelper.USER_LENGTH,
                     DatabaseHelper.USER_LEVEL,DatabaseHelper.USER_DESCRIPTION};//table attribute name
    int[] to       = new int[]{R.id.textViewUserID,R.id.textViewName,
                               R.id.textViewEmail,R.id.textViewGender,
                               R.id.textViewLocation,R.id.textDOB,
                               R.id.textViewParking,R.id.textViewLength,
                               R.id.textViewLevel,R.id.textViewDescription};//user_list_layout id name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        userListView                = findViewById(R.id.userListView);
        databaseHelper              = new DatabaseHelper(this);
        Cursor cursor               = databaseHelper.fetchAllUsers();

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.user_list_layout,cursor,from,to,0);
        cursorAdapter.notifyDataSetChanged();
        userListView.setAdapter(cursorAdapter);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewUserID            = view.findViewById(R.id.textViewUserID);
                TextView textViewName              = view.findViewById(R.id.textViewName);
                TextView textViewEmail             = view.findViewById(R.id.textViewEmail);
                TextView textViewGender            = view.findViewById(R.id.textViewGender);
                TextView textViewLocation          = view.findViewById(R.id.textViewLocation);
                TextView textDOB                   = view.findViewById(R.id.textDOB);
                TextView textViewParking           = view.findViewById(R.id.textViewParking);
                TextView textViewLength            = view.findViewById(R.id.textViewLength);
                TextView textViewLevel             = view.findViewById(R.id.textViewLevel);
                TextView textViewDescription       = view.findViewById(R.id.textViewDescription);

                String user_id                     = textViewUserID.getText().toString();
                String name                        = textViewName.getText().toString();
                String email                       = textViewEmail.getText().toString();
                String gender                      = textViewGender.getText().toString();
                String location                    = textViewLocation.getText().toString();
                String dob                         = textDOB.getText().toString();
                String parking                     = textViewParking.getText().toString();
                String length                      = textViewLength.getText().toString();
                String level                       = textViewLevel.getText().toString();
                String description                 = textViewDescription.getText().toString();

                Intent intent  = new Intent(ListUserActivity.this, ModifyUserActivity.class);
                intent.putExtra("id",user_id);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("gender",gender);
                intent.putExtra("location",location);
                intent.putExtra("dob",dob);
                intent.putExtra("parking",parking);
                intent.putExtra("length",length);
                intent.putExtra("level",level);
                intent.putExtra("description",description);
                startActivity(intent);


            }
        });
    }//end of onCreate
}//end of class






/* List View Code*/
//        ArrayList<String> user_array = databaseHelper.getAllUsers();
//        String[] user     = user_array.toArray(new String[user_array.size()]);
//        ArrayAdapter<String> userArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,user);
//
//        userListView.setTextFilterEnabled(true);
//        userListView.setAdapter(userArrayAdapter);
//
//        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),""+user[position],Toast.LENGTH_LONG).show();
//            }
//        });