package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class EditQualificationActivity extends AppCompatActivity {
    TextView textViewEditQualification,textViewDateTime,editTime;
    EditText editName,editComment;
    Button   btnUpdateQualification;
    DatabaseHelper databaseHelper;
    int q_id =0;
    String q_name,q_time,q_tTime,q_comment;
    String[] m= {"January","February","March","April","May","June","July",
            "August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_qualification);
        textViewEditQualification = findViewById(R.id.textViewEditQualification);
        textViewDateTime          = findViewById(R.id.textViewDateTime);

        editName                  = findViewById(R.id.editName);
        editComment               = findViewById(R.id.editComment);

        btnUpdateQualification    = findViewById(R.id.btnUpdateQualification);

        editTime                  = findViewById(R.id.editTime);

        databaseHelper            = new DatabaseHelper(this);

        Intent intent  = getIntent();
        q_id           = intent.getExtras().getInt("q_id");
        q_name         = intent.getExtras().getString("q_name");
        q_time         = intent.getExtras().getString("q_time");
        q_tTime        = intent.getExtras().getString("q_tTime");
        q_comment      = intent.getExtras().getString("q_comment");

        editName.setText(q_name);
        textViewDateTime.setText(q_time);
        editTime.setText(q_tTime);
        editComment.setText(q_comment);

        Calendar calendar  = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });
        
        btnUpdateQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQualification();
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeDialog();
            }
        });
    }//end of onCreate

    private void openTimeDialog() {


        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                String amPm;
                if (selectedHour >= 12) {
                    amPm = "PM";
                    if (selectedHour > 12) {
                        selectedHour -= 12;
                    }
                } else {
                    amPm = "AM";
                }

                String selectedTime = String.format("%02d:%02d %s", selectedHour, selectedMinute, amPm);

                editTime.setText(selectedTime);
            }
        }, hour, minute, false);

        timePickerDialog.show();

    }
    private void openDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year  = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day   = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSet(),year,month,day);
        datePickerDialog.show();
    }//end of openDateDialog

    private DatePickerDialog.OnDateSetListener dateSet() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textViewDateTime.setText(m[month]+" "+dayOfMonth+", "+year);
            }
        };
    }//end of date set

    private void updateQualification() {
       String qn = editName.getText().toString();
       String qt = textViewDateTime.getText().toString();
       String time = editTime.getText().toString();
       String qc = editComment.getText().toString();

       Log.d("Qualification", qn+"**********"+q_id);
       Qualification qualification = new Qualification(q_id,qn,qt,time,qc);
       databaseHelper.updateQualification(qualification);
       Intent intent = new Intent(EditQualificationActivity.this, QualificationListActivity.class);
       startActivity(intent);
    }//end of updateQualification
}//end of class