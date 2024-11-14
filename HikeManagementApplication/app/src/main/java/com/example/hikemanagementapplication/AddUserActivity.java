package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class AddUserActivity extends AppCompatActivity {

    TextView textViewHikeApp,textViewDOB;
    EditText editHikeName,editEmail,editGender,editLocation,editParking,editLength,editLevel,editDescription;
    Button btnAddHiker;
    DatabaseHelper databaseHelper;
    String[] m= {"January","February","March","April","May","June","July",
            "August","September","October","November","December"};

    private String hikeName, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        textViewHikeApp    = findViewById(R.id.textViewHikeApp);
        textViewDOB        = findViewById(R.id.textViewDOB);

        editHikeName       = findViewById(R.id.editHikeName);
        editEmail          = findViewById(R.id.editEmail);
        editGender         = findViewById(R.id.editGender);
        editLocation       = findViewById(R.id.editLocation);
        editParking        = findViewById(R.id.editParking);
        editLength         = findViewById(R.id.editLength);
        editLevel          = findViewById(R.id.editLevel);
        editDescription    = findViewById(R.id.editDescription);

        btnAddHiker        = findViewById(R.id.btnAddHiker);

        //databaseHelper     = new DatabaseHelper(this);

        Calendar calendar  = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });

        btnAddHiker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();

                if ((editHikeName.getText().toString().length() != 0) && (editEmail.getText().toString().length() != 0) && (editGender.getText().toString().length() != 0) &&
                (editLocation.getText().toString().length() != 0) && (editParking.getText().toString().length() != 0) && (editLength.getText().toString().length() != 0) && (editLevel.getText().toString().length() != 0)
                && (editDescription.getText().toString().length() != 0)){

                    addUser();
                }
//                addUser();

            }
        });
    }//end of onCreate

    private void validation() {
        hikeName = editHikeName.getText().toString().trim();
        if (hikeName.length() == 0) {
            editHikeName.setError("Hike name is required.");
        }else {
            editHikeName.setError(null);
        }

        if (editLocation.getText().toString().trim().length() == 0){
            editLocation.setError("Location is required.");
        }else {
            editLocation.setError(null);
        }

        if (editEmail.getText().toString().trim().length() == 0){
            editEmail.setError("Email is required.");
        }else {
            editEmail.setError(null);
        }

        if (editGender.getText().toString().trim().length() == 0){
            editGender.setError("Gender is required.");
        }else {
            editGender.setError(null);
        }

        if (textViewDOB.getText().toString().trim().toLowerCase() == "choosedateofthehike"){
            Toast.makeText(getApplicationContext(),"Choose Date of Hike",Toast.LENGTH_SHORT).show();
        }

        if (editParking.getText().toString().trim().length() == 0){
            editParking.setError("Parking available is required.");
        }else {
            editParking.setError(null);
        }

        if (editLength.getText().toString().trim().length() == 0){
            editLength.setError("Length of hike is required.");
        }else {
            editLength.setError(null);
        }

        if (editLevel.getText().toString().trim().length() == 0){
            editLevel.setError("Level of difficulty is required.");
        }else {
            editLevel.setError(null);
        }

        if (editDescription.getText().toString().trim().length() == 0){
            editDescription.setError("Description is required.");
        }else {
            editDescription.setError(null);
        }
    }

    private void addUser() {

        databaseHelper     = new DatabaseHelper(this);
        String name        = editHikeName.getText().toString();
        String email       = editEmail.getText().toString();
        String gender      = editGender.getText().toString();
        String location    = editLocation.getText().toString();
        String dob         = textViewDOB.getText().toString();
        String parking     = editParking.getText().toString();
        String length      = editLength.getText().toString();
        String level       = editLevel.getText().toString();
        String description = editDescription.getText().toString();

        User user          = new User(0,name,email,gender,location,dob,parking,length,level,description);
//        User user          = new User(Integer.parseInt(int id),name,email,gender,location,dob,parking,length,level,description);
        long user_id       = databaseHelper.saveUser(user);
        Toast.makeText(this,"Added Hiker: "+user_id,Toast.LENGTH_LONG).show();
    }//end of addHiker

    private void openDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year  = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day   = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSet(),year,month,day);
        datePickerDialog.show();
    }//end of openDateDialog

    private DatePickerDialog.OnDateSetListener dateSet(){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textViewDOB.setText(m[month]+" "+dayOfMonth+", "+year);
            }
        };
    }//end of dateSet

}//end of class