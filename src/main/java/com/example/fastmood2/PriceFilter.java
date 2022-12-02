package com.example.fastmood2;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class PriceFilter {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleFloatProperty cost;

    public PriceFilter(String dname, String ddescription, float dprice) {
        this.name = new SimpleStringProperty(dname);
        this.description = new SimpleStringProperty(ddescription);
        this.cost = new SimpleFloatProperty(dprice);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String ddescription) {
        this.description = new SimpleStringProperty(ddescription);
    }

    public float getCost() {
        return cost.get();
    }

    public void setCost(float cost) {
        this.cost = new SimpleFloatProperty(cost);
    }


}
