package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
public class QualificationListActivity extends AppCompatActivity {
    TextView q_textView;
    RecyclerView q_recyclerview;
    DatabaseHelper databaseHelper;
    int user_id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_list);

        q_textView     = findViewById(R.id.q_textView);
        q_recyclerview = findViewById(R.id.q_recyclerview);

        databaseHelper = new DatabaseHelper(this);

        Intent intent     = getIntent();
        user_id           = intent.getExtras().getInt("user_id");

        ArrayList<Qualification> q_array_list = databaseHelper.getAllQualifications(user_id);

        if (q_array_list.size()!=0) {
            q_textView.setVisibility(View.GONE);
            q_recyclerview.setVisibility(View.VISIBLE);

            q_recyclerview.setLayoutManager(new LinearLayoutManager(this));
            q_recyclerview.setHasFixedSize(true);
            q_recyclerview.setAdapter(new QualificationRecyclerViewAdapter(this, q_array_list));
        }
        else {
            q_textView.setVisibility(View.VISIBLE);
            q_recyclerview.setVisibility(View.GONE);
            q_textView.setText("No Observation Record");
        }
    }//end of onCreate
}//end of class