package com.example.fastmood2;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerDetails {
    private SimpleStringProperty fullname;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty day;
    private SimpleIntegerProperty tid;

    public CustomerDetails(String c_name, String c_phone, String c_email, String c_day, int c_tid) {
        this.fullname = new SimpleStringProperty(c_name);
        this.phone = new SimpleStringProperty(c_phone);
        this.email = new SimpleStringProperty(c_email);
        this.day = new SimpleStringProperty(c_day);
        this.tid = new SimpleIntegerProperty(c_tid);
    }

    public String getFullname() {
        return fullname.get();
    }

    public void setFullname(String c_name) {
        this.fullname = new SimpleStringProperty(c_name);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String c_phone) {
        this.phone = new SimpleStringProperty(c_phone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String c_email) {
        this.email = new SimpleStringProperty(c_email);
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String c_day) {
        this.day = new SimpleStringProperty(c_day);
    }

    public int getTid() {
        return tid.get();
    }

    public void setTid(int c_tid) {
        this.tid = new SimpleIntegerProperty(c_tid);
    }
}
