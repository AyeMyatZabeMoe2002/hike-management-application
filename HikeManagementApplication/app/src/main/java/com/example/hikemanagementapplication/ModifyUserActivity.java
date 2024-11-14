package com.example.hikemanagementapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyUserActivity extends AppCompatActivity {

    EditText modifyName;
    EditText modifyEmail;
    EditText modifyGender;
    EditText modifyLocation;
    TextView modifyDOB;
    EditText modifyParking;
    EditText modifyLength;
    EditText modifyLevel;
    EditText modifyDescription;
    Button   btnUpdate,btnDelete,btnSave,btnShow;
    String   id,name,email,gender,location,dob,parking,length,level,description;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        modifyName        = findViewById(R.id.modifyName);
        modifyEmail       = findViewById(R.id.modifyEmail);
        modifyGender      = findViewById(R.id.modifyGender);
        modifyLocation    = findViewById(R.id.modifyLocation);
        modifyDOB         = findViewById(R.id.modifyDOB);
        modifyParking     = findViewById(R.id.modifyParking);
        modifyLength      = findViewById(R.id.modifyLength);
        modifyLevel       = findViewById(R.id.modifyLevel);
        modifyDescription = findViewById(R.id.modifyDescription);

        btnUpdate         = findViewById(R.id.btnUpdate);
        btnDelete         = findViewById(R.id.btnDelete);
        btnSave           = findViewById(R.id.btnSave);
        btnShow           = findViewById(R.id.btnShow);


        databaseHelper    = new DatabaseHelper(this);

        Intent intent     = getIntent();
        id          = intent.getExtras().getString("id");
        name        = intent.getExtras().getString("name");
        email       = intent.getExtras().getString("email");
        gender      = intent.getExtras().getString("gender");
        location    = intent.getExtras().getString("location");
        dob         = intent.getExtras().getString("dob");
        parking     = intent.getExtras().getString("parking");
        length      = intent.getExtras().getString("length");
        level       = intent.getExtras().getString("level");
        description = intent.getExtras().getString("description");

        Log.d("HKJHKJH", "onCreate: "+dob);

        modifyName.setText(name);
        modifyEmail.setText(email);
        modifyGender.setText(gender);
        modifyLocation.setText(location);
        modifyDOB.setText(dob);
        modifyParking.setText(parking);
        modifyLength.setText(length);
        modifyLevel.setText(level);
        modifyDescription.setText(description);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("User ID","********"+id);
                Intent intent = new Intent(ModifyUserActivity.this, AddQualificationActivity.class);
                intent.putExtra("user_id",Integer.parseInt(id));
                startActivity(intent);
            }
        });//end of save observation

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("User ID","********"+id);
                Intent intent = new Intent(ModifyUserActivity.this, QualificationListActivity.class);
                intent.putExtra("user_id",Integer.parseInt(id));
                startActivity(intent);
            }
        });//end of show observation

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(Integer.parseInt(id),
                            modifyName.getText().toString(),
                            modifyEmail.getText().toString(),
                            modifyGender.getText().toString(),
                            modifyLocation.getText().toString(),
                            modifyDOB.getText().toString(),
                            modifyParking.getText().toString(),
                            modifyLength.getText().toString(),
                            modifyLevel.getText().toString(),
                            modifyDescription.getText().toString());
                databaseHelper.updateUser(user);
                Intent listIntent = new Intent(ModifyUserActivity.this, ListUserActivity.class);
                Log.d("Updated ","***++***"+user);
                startActivity(listIntent);
            }
        });//end of update button


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUser(Integer.parseInt(id));
                Intent listIntent = new Intent(ModifyUserActivity.this, ListUserActivity.class);
                startActivity(listIntent);
            }
        });//end of delete button


    }//end of onCreate
}//end of class