package com.example.securitydb;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty name;
    private SimpleStringProperty name2;
    private SimpleStringProperty dolzh;

    Person(String name, String name2, String dolzh){
        this.name = new SimpleStringProperty(name);
        this.name2 = new SimpleStringProperty(name2);
        this.dolzh = new SimpleStringProperty(dolzh);

    }

    public void setName(String value) {
        this.name.set(value);
    }

    public void setName2(String value) {
        this.name2.set(value);
    }

    public void setDolzh(String value) {
        this.dolzh.set(value);
    }

    public String getName() {
        return name.get();
    }


    public String getName2() {
        return name2.get();
    }


    public String getDolzh() {
        return dolzh.get();
    }

}
