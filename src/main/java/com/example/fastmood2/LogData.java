package com.example.fastmood2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LogData {
    private SimpleStringProperty staff_id;
    private SimpleStringProperty customer_id;
    private SimpleStringProperty fullname;
    private SimpleStringProperty action;
    private SimpleStringProperty moment;
    private SimpleStringProperty table_name;

    public LogData(String staffid, String customerid, String name, String act, String mom, String tablename) {
        this.staff_id = new SimpleStringProperty(staffid);
        this.customer_id = new SimpleStringProperty(customerid);
        this.fullname = new SimpleStringProperty(name);
        this.action = new SimpleStringProperty(act);
        this.moment = new SimpleStringProperty(mom);
        this.table_name = new SimpleStringProperty(tablename);
    }

    public String getStaff_id() {
        return staff_id.get();
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = new SimpleStringProperty(staff_id);
    }

    public String getCustomer_id() {
        return customer_id.get();
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = new SimpleStringProperty(customer_id);
    }

    public String getFullname() {
        return fullname.get();
    }

    public void setFullname(String fullname) {
        this.fullname = new SimpleStringProperty(fullname);
    }

    public String getAction() {
        return action.get();
    }

    public void setAction(String action) {
        this.action = new SimpleStringProperty(action);
    }

    public String getMoment() {
        return moment.get();
    }

    public void setMoment(String moment) {
        this.moment = new SimpleStringProperty(moment);
    }

    public String getTable_name() {
        return table_name.get();
    }

    public void setTable_name(String table_name) {
        this.table_name = new SimpleStringProperty(table_name);
    }
}
