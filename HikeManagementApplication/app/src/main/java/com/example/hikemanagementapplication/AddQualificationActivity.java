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
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
public class AddQualificationActivity extends AppCompatActivity {

    TextView textViewQualification,textViewTime,textViewTTime;
    EditText edit_q_name,edit_q_comment;
    Button  btnAddQualification;
    DatabaseHelper databaseHelper;
    int user_id =0;
    String[] m= {"January","February","March","April","May","June","July",
            "August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qualification);

        textViewQualification         = findViewById(R.id.textViewQualification);
        textViewTime                  = findViewById(R.id.textViewTime);
        textViewTTime                 = findViewById(R.id.textViewTTime);
        edit_q_name                   = findViewById(R.id.edit_q_name);
        edit_q_comment                = findViewById(R.id.edit_q_comment);
        btnAddQualification           = findViewById(R.id.btnAddQualification);

        databaseHelper                = new DatabaseHelper(this);
        Intent intent                 = getIntent();
        user_id  = intent.getExtras().getInt("user_id");

        btnAddQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQualification();
            }
        });

        Calendar calendar  = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });

        textViewTTime.setOnClickListener(new View.OnClickListener() {
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

                    textViewTTime.setText(selectedTime);
                }
            }, hour, minute, false);

            timePickerDialog.show();

    }

    private void saveQualification() {
       String qn =  edit_q_name.getText().toString();
       String qt =  textViewTime.getText().toString();
       String qc =  edit_q_comment.getText().toString();
       String qtt = textViewTTime.getText().toString();
       Log.d("Qualification",qn+"**********"+user_id);
       Qualification qualification = new Qualification(qn,qt,qtt,qc,user_id);
       long qid  = databaseHelper.addQualification(qualification);
       Toast.makeText(this,"Saved: "+qid,Toast.LENGTH_LONG).show();
    }//end of saveQualification

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
                textViewTime.setText(m[month]+" "+dayOfMonth+", "+year);
            }
        };
    }//end of DatePickerDialog
}//end of class