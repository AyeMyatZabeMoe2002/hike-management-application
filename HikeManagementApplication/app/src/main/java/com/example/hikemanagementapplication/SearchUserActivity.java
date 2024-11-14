package com.example.hikemanagementapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
public class SearchUserActivity extends AppCompatActivity {
    TextView searchUserView,textViewSearchForm;
    EditText searchKey;
    Button btnSearch,btnAdvancedSearch;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textViewSearchForm = findViewById(R.id.textViewSearchForm);
        searchUserView     = findViewById(R.id.searchUserView);
        searchUserView     = findViewById(R.id.searchUserView);
        searchKey          = findViewById(R.id.searchKey);
        btnSearch          = findViewById(R.id.btnSearch);
        btnAdvancedSearch  = findViewById(R.id.btnAdvancedSearch);

        databaseHelper = new DatabaseHelper(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchName();
            }
        });

        btnAdvancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchUserActivity.this, AdvancedSearchActivity.class);
                startActivity(intent);
            }
        });

    }//end of on Create

    private void searchName() {
            String key = searchKey.getText().toString();
            ArrayList<String> searchUserArray = databaseHelper.getUser(key);
            if (searchUserArray.size()==0){
                searchUserView.setText("No Hiker found!");
            }
            else{
                String user="";
                for (int i=0;i<searchUserArray.size();i++)
                {
                    user += searchUserArray.get(i)+"\n";
                }
                searchUserView.setText(user);
            }
    }//end of searchName

}//end of class