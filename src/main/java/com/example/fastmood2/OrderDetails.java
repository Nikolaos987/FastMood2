package com.example.fastmood2;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderDetails {
    private SimpleIntegerProperty tid;
    private SimpleStringProperty dname;
    private SimpleFloatProperty dprice;
    private SimpleStringProperty osid;

    public OrderDetails(int t_id, String d_name, float d_price, String o_sid) {
        this.tid = new SimpleIntegerProperty(t_id);
        this.dname = new SimpleStringProperty(d_name);
        this.dprice = new SimpleFloatProperty(d_price);
        this.osid = new SimpleStringProperty(o_sid);
    }

    public int getTid() {
        return tid.get();
    }

    public void setTid(int t_id) {
        this.tid = new SimpleIntegerProperty(t_id);
    }

    public String getDname() {
        return dname.get();
    }

    public void setDname(String d_name) {
        this.dname = new SimpleStringProperty(d_name);
    }

    public float getDprice() {
        return dprice.get();
    }

    public void setDprice(float d_price) {
        this.dprice = new SimpleFloatProperty(d_price);
    }

    public String getOsid() {
        return osid.get();
    }

    public void setOsid(String o_sid) {
        this.osid = new SimpleStringProperty(o_sid);
    }

}
