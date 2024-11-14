package com.example.hikemanagementapplication;
import java.io.Serializable;
public class Qualification implements Serializable {

    private int _id;
    private String name;
    private String time;

    private String ttime;
    private String comment;
    private int user_id;
    public Qualification(String name, String time, String ttime, String comment, int user_id) {
        this.name = name;
        this.time = time;
        this.ttime = ttime;
        this.comment = comment;
        this.user_id = user_id;
    }//end of constructor

    public Qualification(int _id, String name, String time, String ttime, String comment) {
        this._id = _id;
        this.name = name;
        this.time = time;
        this.ttime = ttime;
        this.comment = comment;
        this.user_id = user_id;
    }//end of constructor


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}//end of class

