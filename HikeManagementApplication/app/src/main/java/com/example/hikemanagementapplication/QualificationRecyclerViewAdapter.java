package com.example.hikemanagementapplication;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class QualificationRecyclerViewAdapter extends RecyclerView.Adapter<QualificationViewHolder> {

    Context context;
    ArrayList<Qualification> arrayList;
    DatabaseHelper databaseHelper;

    public QualificationRecyclerViewAdapter(Context context,ArrayList<Qualification> arrayList) {
            this.context   = context;
            this.arrayList = arrayList;
            databaseHelper = new DatabaseHelper(context);
    }//end of constructor

    @Override
    public QualificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qualification_list_item_layout,parent,false);
        QualificationViewHolder qview = new QualificationViewHolder(view);
        return qview;
    }//end of Qualification View Holder

    @Override
    public void onBindViewHolder(QualificationViewHolder holder, int position) {
        Qualification q_record = arrayList.get(position);

        holder.q_imageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.q_name_textView.setText(q_record.getName());
        holder.q_time_textView.setText(q_record.getTime());
        holder.q_tTime_textView.setText(q_record.getTtime());
        holder.q_comment_textView.setText(q_record.getComment());

        holder.btn_q_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editQualification(q_record);
            }
        });

        holder.btn_q_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteQualification(q_record);
            }
        });
    }//end of onBindViewHolder

    private void editQualification(Qualification q) {

        Intent intent  = new Intent(context, EditQualificationActivity.class);
        intent.putExtra("q_id",q.get_id());
        intent.putExtra("q_name",q.getName());
        intent.putExtra("q_time",q.getTime());
        intent.putExtra("q_tTime",q.getTtime());
        intent.putExtra("q_comment",q.getComment());
        context.startActivity(intent);
    }//end of editQualification

    private void deleteQualification(Qualification q) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteQualification(q.get_id());
                        ((Activity)context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .show();
    }//end of deleteQualification

    @Override
    public int getItemCount() {

        return arrayList.size();
    }//end of getItemCount


}//end of class
