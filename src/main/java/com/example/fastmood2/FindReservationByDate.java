package com.example.fastmood2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FindReservationByDate {
//    private SimpleIntegerProperty rid;
    private SimpleStringProperty day;
    private SimpleIntegerProperty tid;
//    private SimpleIntegerProperty cid;

    public FindReservationByDate(String date, int t_id) {
//        this.rid = new SimpleIntegerProperty(r_id);
        this.day = new SimpleStringProperty(date);
        this.tid = new SimpleIntegerProperty(t_id);
//        this.cid = new SimpleIntegerProperty(c_id);
    }

//    public int getRid() {
//        return rid.get();
//    }
//
//    public void setRid(int r_id) {
//        this.rid = new SimpleIntegerProperty(r_id);
//    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String date) {
        this.day = new SimpleStringProperty(date);
    }

    public int getTid() {
        return tid.get();
    }

    public void setTid(int t_id) {
        this.tid = new SimpleIntegerProperty(t_id);
    }

//    public int getCid() {
//        return cid.get();
//    }
//
//    public void setCid(int c_id) {
//        this.cid = new SimpleIntegerProperty(c_id);
//    }
}
