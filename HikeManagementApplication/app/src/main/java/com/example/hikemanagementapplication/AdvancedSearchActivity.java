package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvancedSearchActivity extends AppCompatActivity {

    TextView textViewAdvancedSearch,textViewSearch;
    EditText SearchName,SearchLocation,SearchDate;
    Button   btnAdvSearch;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        textViewAdvancedSearch    = findViewById(R.id.textViewAdvancedSearch);
        textViewSearch            = findViewById(R.id.textViewSearch);
        SearchName                = findViewById(R.id.SearchName);
        SearchLocation            = findViewById(R.id.SearchLocation);
        SearchDate                = findViewById(R.id.SearchDate);
        btnAdvSearch              = findViewById(R.id.btnAdvSearch);

        databaseHelper = new DatabaseHelper(this);
        btnAdvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name      = SearchName.getText().toString();
                String location  = SearchLocation.getText().toString();
                String date      = SearchDate.getText().toString();

                ArrayList<String> searchUserArray = databaseHelper.getAdvanceSearch(name,location,date);
                if (searchUserArray.size()==0){
                    textViewSearch.setText("No Hiker found!");
                }
                else{
                    String user="";
                    for (int i=0;i<searchUserArray.size();i++)
                    {
                        user += searchUserArray.get(i)+"\n";
                    }
                    textViewSearch.setText(user);
                }


            }
        });
    }//end of on create

}//end of class