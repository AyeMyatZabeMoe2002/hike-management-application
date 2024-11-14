package com.example.hikemanagementapplication;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class QualificationViewHolder extends RecyclerView.ViewHolder {

    ImageView q_imageView;
    TextView  q_name_textView,q_time_textView,q_tTime_textView,q_comment_textView;
    ImageButton btn_q_edit,btn_q_delete;
    public QualificationViewHolder(View itemView) {
        super(itemView);

        q_imageView        = itemView.findViewById(R.id.q_imageView);
        q_name_textView    = itemView.findViewById(R.id.q_name_textView);
        q_time_textView    = itemView.findViewById(R.id.q_time_textView);
        q_tTime_textView   = itemView.findViewById(R.id.q_tTime_textView);
        q_comment_textView = itemView.findViewById(R.id.q_comment_textView);

        btn_q_edit         = itemView.findViewById(R.id.btn_q_edit);
        btn_q_delete       = itemView.findViewById(R.id.btn_q_delete);


    }//end of Qualification View Holder

}//end of class
