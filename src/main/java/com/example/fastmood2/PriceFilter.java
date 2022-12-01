package com.example.fastmood2;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class PriceFilter {
    private SimpleStringProperty name;
    private SimpleFloatProperty cost;

    public PriceFilter(String dname, float dprice) {
        this.name = new SimpleStringProperty(dname);
        this.cost = new SimpleFloatProperty(dprice);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public float getCost() {
        return cost.get();
    }

    public void setCost(float cost) {
        this.cost = new SimpleFloatProperty(cost);
    }


}
