package com.example.hikemanagementapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
public class WelcomeHikeActivity extends AppCompatActivity {
    TextView textView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_hike);
        textView       = findViewById(R.id.textView);
        databaseHelper = new DatabaseHelper(this);
    }//end of onCreate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }//end of onCreate

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemAdd){
            Intent intent   = new Intent(WelcomeHikeActivity.this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.itemShow){
            Intent intent = new Intent(WelcomeHikeActivity.this, ListUserActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.itemSearch){
            Intent intent = new Intent(WelcomeHikeActivity.this, SearchUserActivity.class);
            startActivity(intent);
            return true;
        }

        else if(item.getItemId()==R.id.itemDeleteAll){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmation")
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper.deleteAllUser();
                        }
                    })//set positive button
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })//set negative button
                    .show();
            databaseHelper.deleteAllUser();
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }//end of onOptions
}//end of class