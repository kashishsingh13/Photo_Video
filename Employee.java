package com.example.crudsqlite.xmlAndjsonparsing;

import java.util.ArrayList;

public class Employee {

    String id,name,salary;

    ArrayList<String> hobbies;

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Employee(String id, String name, String salary, ArrayList<String> hobbies) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hobbies = hobbies;
    }

    public String getId() {
        return id;
    }



    public void setId(String id) {

        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
