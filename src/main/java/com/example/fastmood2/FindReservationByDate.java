package com.example.fastmood2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FindReservationByDate {

    private SimpleStringProperty day;
    private SimpleIntegerProperty tid;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;

    public FindReservationByDate(String date, int t_id, String c_name, String c_phone) {
//        this.rid = new SimpleIntegerProperty(r_id);
        this.day = new SimpleStringProperty(date);
        this.tid = new SimpleIntegerProperty(t_id);
        this.name = new SimpleStringProperty(c_name);
        this.phone = new SimpleStringProperty(c_phone);

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

    public String getName() {
        return name.get();
    }

    public void setName(String c_name) {
        this.name = new SimpleStringProperty(c_name);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String c_phone) {
        this.phone = new SimpleStringProperty(c_phone);
    }



//    public int getCid() {
//        return cid.get();
//    }
//
//    public void setCid(int c_id) {
//        this.cid = new SimpleIntegerProperty(c_id);
//    }
}
