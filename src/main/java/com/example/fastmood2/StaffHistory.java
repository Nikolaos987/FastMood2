package com.example.fastmood2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StaffHistory {
    private SimpleStringProperty name;
    private SimpleIntegerProperty tid;
    private SimpleIntegerProperty count;

    public StaffHistory(String s_fullname, int t_id, int s_count) {
        this.name = new SimpleStringProperty(s_fullname);
        this.tid = new SimpleIntegerProperty(t_id);
        this.count = new SimpleIntegerProperty(s_count);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String s_fullname) {
        this.name = new SimpleStringProperty(s_fullname);
    }

    public int getTid() {
        return tid.get();
    }

    public void setTid(int t_id) {
        this.tid = new SimpleIntegerProperty(t_id);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int s_count) {
        this.count = new SimpleIntegerProperty(s_count);
    }


}
