package com.example.fastmood2;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AvailableTables {
    private SimpleIntegerProperty tid;
    private SimpleStringProperty vip;
    private SimpleIntegerProperty capacity;

    public AvailableTables(int t_id, String t_vip, int t_capacity) {
        this.tid = new SimpleIntegerProperty(t_id);
        this.vip = new SimpleStringProperty(t_vip);
        this.capacity = new SimpleIntegerProperty(t_capacity);
    }

    public int getTid() {
        return tid.get();
    }

    public void setTid(int t_id) {
        this.tid = new SimpleIntegerProperty(t_id);
    }

    public String getVip() {
        return vip.get();
    }

    public void setVip(String t_vip) {
        this.vip = new SimpleStringProperty(t_vip);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public void setCapacity(int t_capacity) {
        this.capacity = new SimpleIntegerProperty(t_capacity);
    }

}
